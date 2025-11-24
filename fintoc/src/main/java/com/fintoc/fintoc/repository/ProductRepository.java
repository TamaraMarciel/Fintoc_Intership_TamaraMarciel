package com.fintoc.fintoc.repository;

import com.fintoc.fintoc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByCategoria(String categoria);
    
    List<Product> findByCategoriaOrderByPrecioAsc(String categoria);
    
    List<Product> findByCategoriaOrderByPrecioDesc(String categoria);
    
    List<Product> findByCategoriaOrderByVentasDesc(String categoria);
    
    List<Product> findByCategorias(List<String> categorias);
    
    List<Product> findByCategoriasOrderByPrecioAsc(List<String> categorias);
    
    List<Product> findByCategoriasOrderByPrecioDesc(List<String> categorias);
    
    List<Product> findByCategoriasOrderByVentasDesc(List<String> categorias);
    
    List<String> findAllCategorias();
}

