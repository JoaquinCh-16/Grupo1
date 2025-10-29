/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Personal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.ConexionBD;

/**
 *
 * @author Joaquin Chavez Flore
 */
public class LoginDAO {
    public Personal login(String email, String contraseña) {
        Personal personal = null;
        Connection conn = ConexionBD.getConnection();
        if (conn == null) return null;

        String sql = "SELECT PERSONAL_ID, NOMBRE, EMAIL, CONTRASENA, TELEFONO, ROL " +
                 "FROM PERSONAL WHERE EMAIL = ? AND CONTRASENA = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, contraseña);
            ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Integer tel = rs.getInt("TELEFONO");
            if (rs.wasNull()) tel = null;

            personal = new Personal(
                rs.getString("PERSONAL_ID"),
                rs.getString("NOMBRE"),
                rs.getString("EMAIL"),
                rs.getString("CONTRASENA"),
                tel,
                rs.getString("ROL")
            );
        }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return personal;
    }
}
