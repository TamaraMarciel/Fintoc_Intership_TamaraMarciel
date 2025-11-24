package com.fintoc.fintoc.service;

import com.fintoc.fintoc.model.CartItemSession;
import com.fintoc.fintoc.model.Product;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartSessionService {
    
    private static final String CART_SESSION_KEY = "shoppingCart";
    
    public void addToCart(HttpSession session, Product product, Integer cantidad) {
        List<CartItemSession> cart = getCart(session);
        
        Optional<CartItemSession> existingItem = cart.stream()
                .filter(item -> item.getProductId().equals(product.getId()))
                .findFirst();
        
        if (existingItem.isPresent()) {
            existingItem.get().setCantidad(existingItem.get().getCantidad() + cantidad);
        } else {
            CartItemSession newItem = new CartItemSession(
                    product.getId(),
                    product.getNombre(),
                    cantidad,
                    product.getPrecio(),
                    product.getFoto()
            );
            cart.add(newItem);
        }
        
        session.setAttribute(CART_SESSION_KEY, cart);
    }
    
    public void removeFromCart(HttpSession session, Long productId) {
        List<CartItemSession> cart = getCart(session);
        cart.removeIf(item -> item.getProductId().equals(productId));
        session.setAttribute(CART_SESSION_KEY, cart);
    }
    
    public void updateQuantity(HttpSession session, Long productId, Integer cantidad) {
        List<CartItemSession> cart = getCart(session);
        
        Optional<CartItemSession> item = cart.stream()
                .filter(i -> i.getProductId().equals(productId))
                .findFirst();
        
        if (item.isPresent()) {
            if (cantidad > 0) {
                item.get().setCantidad(cantidad);
            } else {
                cart.removeIf(i -> i.getProductId().equals(productId));
            }
        }
        
        session.setAttribute(CART_SESSION_KEY, cart);
    }
    
    @SuppressWarnings("unchecked")
    public List<CartItemSession> getCart(HttpSession session) {
        List<CartItemSession> cart = (List<CartItemSession>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }
    
    public Double getTotalPrice(HttpSession session) {
        return getCart(session).stream()
                .mapToDouble(CartItemSession::getSubtotal)
                .sum();
    }
    
    public int getCartCount(HttpSession session) {
        return getCart(session).size();
    }
    
    public void clearCart(HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
    }
}
