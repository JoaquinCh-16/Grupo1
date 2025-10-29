/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Producto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.ConexionBD;

/**
 *
 * @author Usuario
 */
public class ProductoDAO {
    public boolean agregarProducto(Producto producto) throws SQLException{
        Connection conn = ConexionBD.getConnection();
        if(conn == null){
            return false;
        }else{
            String sql = "{CALL SP_AGREGAR_PRODUCTO(?, ?, ?, ?, ?, ?)}";
            try(CallableStatement cs = conn.prepareCall(sql)){
                cs.setString(1, producto.getProductoId());
                cs.setString(2, producto.getNombre());
                cs.setString(3, producto.getEstado());
                cs.setDouble(4, producto.getPrecio());
                cs.setInt(5, producto.getStock());
                cs.setString(6, producto.getCategoria());
                cs.execute();
                return true;
            }catch(SQLException e){
                e.printStackTrace();
                return false;
            }finally{
                conn.close();
            }
        }
    }

    
    public boolean editarProducto(Producto producto) throws SQLException {
        Connection conn = ConexionBD.getConnection();
        if (conn == null) {
            return false;
        } else {
            String sql = "{CALL SP_ACTUALIZARPRODUCTO(?, ?, ?, ?, ?, ?)}";
            try (CallableStatement cs = conn.prepareCall(sql)) {
                cs.setString(1, producto.getProductoId());
                cs.setString(2, producto.getNombre() != null ? producto.getNombre() : "");
                cs.setString(3, producto.getEstado() != null ? producto.getEstado() : "");
                cs.setDouble(4, producto.getPrecio());
                cs.setInt(5, producto.getStock());
                cs.setString(6, producto.getCategoria() != null ? producto.getCategoria() : "");
                cs.execute();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                conn.close();
            }
        }
    }

    public boolean eliminarProducto(String productoId) throws SQLException {
        Connection conn = ConexionBD.getConnection();
        if (conn == null) {
            return false;
        } else {
            String sql = "{CALL SP_ELIMINARPRODUCTO(?)}";
            try (CallableStatement cs = conn.prepareCall(sql)) {
                cs.setString(1, productoId);
                cs.execute();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                conn.close();
            }
        }
    }


    public Producto buscarProductoPorId(String productoId) {
        Producto producto = null;
        String sql = "SELECT PRODUCTO_ID, NOMBRE, ESTADO, PRECIO, STOCK, CATEGORIA FROM PRODUCTO WHERE PRODUCTO_ID = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, productoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto();
                    producto.setProductoId(rs.getString("PRODUCTO_ID"));
                    producto.setNombre(rs.getString("NOMBRE"));
                    producto.setEstado(rs.getString("ESTADO"));
                    producto.setPrecio(rs.getDouble("PRECIO"));
                    producto.setStock(rs.getInt("STOCK"));
                    producto.setCategoria(rs.getString("CATEGORIA"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }
    
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();

        String sql = "SELECT PRODUCTO_ID, NOMBRE, ESTADO, PRECIO, STOCK, CATEGORIA FROM Producto";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto();
                p.setProductoId(rs.getString("PRODUCTO_ID"));
                p.setNombre(rs.getString("NOMBRE"));
                p.setEstado(rs.getString("ESTADO"));
                p.setPrecio(rs.getDouble("PRECIO"));
                p.setStock(rs.getInt("STOCK"));
                p.setCategoria(rs.getString("CATEGORIA"));

                productos.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al listar productos");
        }
        return productos;
    }
    public String obtenerUltimoProductoId() {
    String ultimoId = "PR000"; // Valor inicial por defecto
    Connection conn = ConexionBD.getConnection();
    if (conn == null) return ultimoId;

    try (Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT MAX(PRODUCTO_ID) AS ultimo FROM PRODUCTO")) {

        if (rs.next() && rs.getString("ultimo") != null) {
            ultimoId = rs.getString("ultimo");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
    }

    return ultimoId;
}



    
    
 
    
    
    
    
    
}