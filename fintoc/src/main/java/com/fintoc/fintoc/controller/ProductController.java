package com.fintoc.fintoc.controller;

import com.fintoc.fintoc.model.Product;
import com.fintoc.fintoc.service.ProductService;
import com.fintoc.fintoc.service.CartSessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CartSessionService cartSessionService;
    
    @GetMapping
    public String index(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String sortBy,
            HttpSession session,
            Model model) {
        
        List<String> categorias = productService.getAllCategorias();
        if (categorias == null) {
            categorias = List.of();
        }
        
        List<Product> productos = List.of();
        
        if (categoria != null && !categoria.isEmpty()) {
            productos = productService.getProductsByCategory(categoria, sortBy);
            if (productos == null) {
                productos = List.of();
            }
        }
        
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("selectedCategoria", categoria);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("cartCount", cartSessionService.getCartCount(session));
        
        return "index";
    }
    
    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        model.addAttribute("cartItems", cartSessionService.getCart(session));
        model.addAttribute("totalPrice", cartSessionService.getTotalPrice(session));
        model.addAttribute("cartCount", cartSessionService.getCartCount(session));
        return "cart";
    }
    
    @PostMapping("/add-to-cart")
    public String addToCart(
            @RequestParam Long productId,
            @RequestParam(defaultValue = "1") Integer cantidad,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String sortBy,
            HttpSession session) {
        
        Product product = productService.getProductById(productId);
        if (product != null) {
            cartSessionService.addToCart(session, product, cantidad);
        }
        
        String redirectUrl = "/?";
        if (categoria != null && !categoria.isEmpty()) {
            redirectUrl += "categoria=" + categoria;
            if (sortBy != null && !sortBy.isEmpty()) {
                redirectUrl += "&sortBy=" + sortBy;
            }
        }
        return "redirect:" + redirectUrl;
    }
    
    @PostMapping("/remove-from-cart")
    public String removeFromCart(@RequestParam Long productId, HttpSession session) {
        cartSessionService.removeFromCart(session, productId);
        return "redirect:/cart";
    }
    
    @PostMapping("/update-cart")
    public String updateCart(@RequestParam Long productId, @RequestParam Integer cantidad, HttpSession session) {
        cartSessionService.updateQuantity(session, productId, cantidad);
        return "redirect:/cart";
    }
}
