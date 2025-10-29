/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.ProductoDAO;
import Modelo.Producto;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Alonso
 */
public class ProductoController {

  
    private ProductoDAO productoDAO;

    public ProductoController() {
    this.productoDAO = new ProductoDAO(); // ← esto inicializa el objeto
}

    
    // Método para agregar un producto
    public boolean agregarProducto(String productoId, String nombre, String estado, 
                                   Double precio, Integer stock, String categoria) throws SQLException {
        Producto producto = new Producto(productoId, nombre, estado, precio, stock, categoria);
        return productoDAO.agregarProducto(producto);
    }

    // Método para editar un producto
    public boolean editarProducto(String productoId, String nombre, String estado, 
                                  Double precio, Integer stock, String categoria) throws SQLException {
        Producto producto = new Producto(productoId, nombre, estado, precio, stock, categoria);
        return productoDAO.editarProducto(producto);
    }

    // Método para eliminar un producto
    public boolean eliminarProducto(String productoId) throws SQLException {
        return productoDAO.eliminarProducto(productoId);
    }

    // Método para listar todos los productos
    public List<Producto> listarProductos() {
        return productoDAO.listar();
    }

    // Método para buscar un producto por ID
    public Producto buscarProducto(String productoId) {
        return productoDAO.buscarProductoPorId(productoId);
    }
    public String obtenerUltimoProductoId() {
        return productoDAO.obtenerUltimoProductoId();
    } 
    

    
}
