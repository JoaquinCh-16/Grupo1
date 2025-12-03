/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Usuario
 */
public class Producto {
    private String productoId;
    private String nombre;
    private String estado;
    private double precio;
    private int stock;
    private String categoria;

    public Producto() {}

   
    public Producto(String productoId, String nombre, String estado, double precio, int stock, String categoria) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.estado = estado;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    // Getters y Setters
    public String getProductoId() { 
        return productoId; 
    }
    
    public void setProductoId(String productoId) {
        this.productoId = productoId; 
    }
    
    public String getNombre() { 
        return nombre; 
    }
    
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public String getEstado() { 
        return estado; 
    }
    
    public void setEstado(String estado) {
        this.estado = estado; 
    }

    public double getPrecio() { 
        return precio; 
    }
    
    public void setPrecio(double precio) { 
        this.precio = precio; 
    }

    public int getStock() { 
        return stock; 
    }
    
    public void setStock(int stock) { 
        this.stock = stock; 
    }

    public String getCategoria() {
        return categoria; 
    }
    
    public void setCategoria(String categoria) { 
        this.categoria = categoria; 
    }
}

