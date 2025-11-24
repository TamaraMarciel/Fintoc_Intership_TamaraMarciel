package com.fintoc.fintoc.model;

import java.io.Serializable;

public class CartItemSession implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long productId;
    private String productName;
    private Integer cantidad;
    private Double precio;
    private String foto;

    public CartItemSession() {
    }

    public CartItemSession(Long productId, String productName, Integer cantidad, Double precio, String foto) {
        this.productId = productId;
        this.productName = productName;
        this.cantidad = cantidad;
        this.precio = precio;
        this.foto = foto;
    }

    // Getters y Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Double getSubtotal() {
        return precio * cantidad;
    }

    @Override
    public String toString() {
        return "CartItemSession{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", foto='" + foto + '\'' +
                '}';
    }
}
