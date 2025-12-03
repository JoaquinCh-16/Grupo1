/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Detalle_Pedido;
import Modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.ConexionBD;

/**
 *
 * @author josem
 */
public class Detalle_PedidoDAO {

    public List<Detalle_Pedido> obtenerDetallesPorVentaId(String ventaId) throws SQLException {
    List<Detalle_Pedido> detalles = new ArrayList<>();
    String sql = """
        SELECT VENTA_ID, PRODUCTO_ID, CANTIDAD, PRECIO_UNIT
        FROM DETALLE_PEDIDO
        WHERE VENTA_ID = ?
    """;

    try (Connection conn = ConexionBD.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, ventaId);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Detalle_Pedido detalle = new Detalle_Pedido(
                    rs.getString("VENTA_ID"),
                    rs.getString("PRODUCTO_ID"),
                    rs.getInt("CANTIDAD"),
                    rs.getDouble("PRECIO_UNIT")
                );
                detalles.add(detalle);
            }
        }
    }
    return detalles;
}
    public String obtenerNombreProductoPorID(String productoId) throws SQLException {
    String nombre = null;
    String sql = "SELECT NOMBRE FROM PRODUCTO WHERE PRODUCTO_ID = ?";

    try (Connection conn = ConexionBD.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, productoId);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                nombre = rs.getString("NOMBRE");
            }
        }
    }
    return nombre;
}




}
