package com.fintoc.fintoc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 1, max = 255, message = "El nombre debe tener entre 1 y 255 caracteres")
    @Column(nullable = false)
    private String nombre;

    @Size(max = 5, message = "Un producto puede tener máximo 5 categorías")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_categories", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "categoria", length = 20)
    private List<String> categorias = new ArrayList<>();

    @Size(max = 200, message = "La descripción debe tener máximo 200 caracteres")
    @Column(length = 200)
    private String descripcion;

    @NotNull(message = "El precio es requerido")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    @Column(nullable = false)
    private Double precio;

    @NotNull(message = "El stock es requerido")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(nullable = false)
    private Integer stock;

    @Column(columnDefinition = "LONGTEXT")
    private String foto;

    @Min(value = 0, message = "Las ventas no pueden ser negativas")
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer ventas = 0;

    // Constructores
    public Product() {
    }

    public Product(String nombre, List<String> categorias, String descripcion, Double precio, Integer stock, String foto) {
        this.nombre = nombre;
        this.categorias = categorias;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.foto = foto;
        this.ventas = 0;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    public void addCategoria(String categoria) {
        if (this.categorias.size() < 5) {
            this.categorias.add(categoria);
        }
    }

    public void removeCategoria(String categoria) {
        this.categorias.remove(categoria);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getVentas() {
        return ventas;
    }

    public void setVentas(Integer ventas) {
        this.ventas = ventas;
    }

    public void addVenta(Integer cantidad) {
        this.ventas += cantidad;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", categorias=" + categorias +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", foto='" + foto + '\'' +
                ", ventas=" + ventas +
                '}';
    }
}
