/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Detalle_Pedido;
import Modelo.Pedidos;
import Modelo.Producto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConexionBD;

/**
 *
 * @author josem
 */
public class PedidosDAO {

    public boolean insertarPedido(Pedidos pedido) {
        String sql = "INSERT INTO PEDIDO (VENTA_ID, FECHA_VENTA, TOTAL, METODO_PAGO, PERSONAL_ID, COMPRADOR) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pedido.getVentaId());
            ps.setDate(2, new java.sql.Date(pedido.getFechaVenta().getTime()));
            ps.setDouble(3, pedido.getTotal());
            ps.setString(4, pedido.getMetodoPago());
            ps.setString(5, pedido.getPersonalId());
            ps.setString(6, pedido.getComprador());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertarDetallePedido(List<Detalle_Pedido> detallePedido) {
    String sql = "INSERT INTO DETALLE_PEDIDO (VENTA_ID, PRODUCTO_ID, CANTIDAD, PRECIO_UNIT) VALUES (?, ?, ?, ?)";

    try (Connection conn = ConexionBD.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        for (Detalle_Pedido d : detallePedido) {
            ps.setString(1, d.getVentaId());       // VENTA_ID (FK hacia PEDIDO)
            ps.setString(2, d.getProductoId());    // PRODUCTO_ID (FK hacia PRODUCTO)
            ps.setInt(3, d.getCantidad());         // cantidad pedida
            ps.setDouble(4, d.getPrecioUnit());    // precio unitario en el momento de la venta
            ps.addBatch();
        }

        ps.executeBatch();
        return true;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    public List<Pedidos> listarPedidos() throws SQLException {
        List<Pedidos> listaPedidos = new ArrayList<>();
        Connection conn = ConexionBD.getConnection();
        String sql = "SELECT VENTA_ID, COMPRADOR, FECHA_VENTA, TOTAL FROM PEDIDO";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Pedidos p = new Pedidos();
                p.setVentaId(rs.getString("VENTA_ID"));
                p.setComprador(rs.getString("COMPRADOR"));
                p.setFechaVenta(rs.getDate("FECHA_VENTA"));
                p.setTotal(rs.getDouble("TOTAL"));

                listaPedidos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return listaPedidos;
    }

    public Pedidos obtenerPedidoPorID(String ventaId) throws SQLException {
        Pedidos pedido = null;
        Connection conn = ConexionBD.getConnection();
        String sql = "SELECT VENTA_ID, COMPRADOR, FECHA_VENTA,METODO_PAGO, TOTAL FROM PEDIDO WHERE VENTA_ID = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ventaId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pedido = new Pedidos();
                    pedido.setVentaId(rs.getString("VENTA_ID"));
                    pedido.setComprador(rs.getString("COMPRADOR"));
                    pedido.setMetodoPago(rs.getString("METODO_PAGO"));
                    pedido.setFechaVenta(rs.getDate("FECHA_VENTA"));
                    pedido.setTotal(rs.getDouble("TOTAL"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return pedido; // puede ser null si no se encontró
    }

    public boolean actualizarStock(String productoId, int cantidadVendida) throws SQLException {
        String sql = "UPDATE PRODUCTO SET STOCK = STOCK - ? WHERE PRODUCTO_ID = ?";
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cantidadVendida);
            ps.setString(2, productoId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String generarIdVenta() throws SQLException {
        String sql = "SELECT MAX(VENTA_ID) AS ultimo FROM PEDIDO";
        String ultimoId = "V000";

        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next() && rs.getString("ultimo") != null) {
                ultimoId = rs.getString("ultimo"); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        int numero = Integer.parseInt(ultimoId.substring(1));
        return String.format("V%03d", numero + 1);
    }

    public boolean eliminarPedido(String pedidoId) {
        Connection conn = ConexionBD.getConnection();
        if (conn == null) {
            System.out.println("Fallo: Conexión nula.");
            return false;
        }

        try {
            conn.setAutoCommit(false);

            // 1. Eliminar detalles
            try (PreparedStatement psDetalle = conn.prepareStatement("DELETE FROM DETALLE_PEDIDO WHERE VENTA_ID = ?")) {
                psDetalle.setString(1, pedidoId);
                psDetalle.executeUpdate();
                System.out.println("Detalles eliminados para ID: " + pedidoId);
            }

            // 2. Eliminar pedido
            try (PreparedStatement psPedido = conn.prepareStatement("DELETE FROM PEDIDO WHERE VENTA_ID = ?")) {
                psPedido.setString(1, pedidoId);
                psPedido.executeUpdate();
                System.out.println("Pedido eliminado para ID: " + pedidoId);
            }

            conn.commit(); 
            return true;

        } catch (SQLException e) {
            System.out.println("Error SQL al eliminar: " + e.getMessage());
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
