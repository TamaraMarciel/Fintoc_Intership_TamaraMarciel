package com.fintoc.fintoc.service;

import com.fintoc.fintoc.model.Product;
import com.fintoc.fintoc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public List<Product> getProductsByCategory(String categoria, String sortBy) {
        if (sortBy == null || sortBy.isEmpty() || "nombre".equals(sortBy)) {
            return productRepository.findByCategoria(categoria);
        } else if ("precio_asc".equals(sortBy)) {
            return productRepository.findByCategoriaOrderByPrecioAsc(categoria);
        } else if ("precio_desc".equals(sortBy)) {
            return productRepository.findByCategoriaOrderByPrecioDesc(categoria);
        } else if ("mas_vendidos".equals(sortBy)) {
            return productRepository.findByCategoriaOrderByVentasDesc(categoria);
        }
        return productRepository.findByCategoria(categoria);
    }
    
    public List<Product> getProductsByCategories(List<String> categorias, String sortBy) {
        if (categorias == null || categorias.isEmpty()) {
            return List.of();
        }
        
        if (sortBy == null || sortBy.isEmpty() || "nombre".equals(sortBy)) {
            return productRepository.findByCategorias(categorias);
        } else if ("precio_asc".equals(sortBy)) {
            return productRepository.findByCategoriasOrderByPrecioAsc(categorias);
        } else if ("precio_desc".equals(sortBy)) {
            return productRepository.findByCategoriasOrderByPrecioDesc(categorias);
        } else if ("mas_vendidos".equals(sortBy)) {
            return productRepository.findByCategoriasOrderByVentasDesc(categorias);
        }
        return productRepository.findByCategorias(categorias);
    }
    
    public List<String> getAllCategorias() {
        return productRepository.findAllCategorias();
    }
    
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }
    
    public void incrementProductVentas(Long productId, Integer cantidad) {
        Product product = getProductById(productId);
        if (product != null) {
            product.addVenta(cantidad);
            productRepository.save(product);
        }
    }
}
