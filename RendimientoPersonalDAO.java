package DAO;


import Modelo.RendimientoPersonal;
import util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RendimientoPersonalDAO {

    public List<RendimientoPersonal> obtenerRendimiento() {
        List<RendimientoPersonal> lista = new ArrayList<>();

        String sql = "EXEC SP_RendimientoPersonal";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String vendedor = rs.getString("Vendedor");
                int numeroPedidos = rs.getInt("NumeroPedidos");
                double ingresoTotal = rs.getDouble("IngresoTotal");

                lista.add(new RendimientoPersonal(vendedor, numeroPedidos, ingresoTotal));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
