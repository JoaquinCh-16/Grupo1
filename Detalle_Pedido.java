/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author josem
 */
public class Detalle_Pedido {
    private String ventaId;       
    private String productoId;    
    private int cantidad;        
    private double precioUnit;   
    private double subtotal;     

    public Detalle_Pedido() {}

    public Detalle_Pedido(String ventaId, String productoId, int cantidad, double precioUnit) {
        this.ventaId = ventaId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnit = precioUnit;
        this.subtotal = cantidad * precioUnit;
    }

    public String getVentaId() {
        return ventaId;
    }

    public void setVentaId(String ventaId) {
        this.ventaId = ventaId;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = this.cantidad * this.precioUnit; // recalcular subtotal
    }

    public double getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(double precioUnit) {
        this.precioUnit = precioUnit;
        this.subtotal = this.cantidad * this.precioUnit; // recalcular subtotal
    }

    public double getSubtotal() {
        return subtotal;
    }
}

