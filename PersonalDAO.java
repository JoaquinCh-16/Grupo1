/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Personal;
import util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonalDAO {
     public boolean agregarPersonal(Personal personal) {
        Connection conn = ConexionBD.getConnection();
        if (conn == null) return false;
        try (CallableStatement stmt = conn.prepareCall("{CALL SP_AgregarPersonal(?, ?, ?, ?, ?, ?)}")) {
            stmt.setString(1, personal.getPersonalId());
            stmt.setString(2, personal.getNombre());
            stmt.setString(3, personal.getEmail());
            stmt.setString(4, personal.getContrasena() != null ? personal.getContrasena() : "");
            
            if (personal.getTelefono() != null) {
                stmt.setInt(5, personal.getTelefono());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }

            stmt.setString(6, personal.getRol());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public boolean editarPersonal(Personal personal) {
        Connection conn = ConexionBD.getConnection();
        if (conn == null) {
            System.out.println("Fallo: Conexión nula.");
            return false;
        }
        try (CallableStatement stmt = conn.prepareCall("{CALL SP_EditarPersonal(?, ?, ?, ?, ?, ?)}")) {
            stmt.setString(1, personal.getPersonalId());
            stmt.setString(2, personal.getNombre());
            stmt.setString(3, personal.getEmail());
            stmt.setString(4, personal.getContrasena() != null ? personal.getContrasena() : "");

            if (personal.getTelefono() != null) {
                stmt.setInt(5, personal.getTelefono());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }

            stmt.setString(6, personal.getRol());

            System.out.println("Ejecutando SP con - ID: " + personal.getPersonalId() + ", Contraseña: " + (personal.getContrasena() != null ? personal.getContrasena() : "No cambiada"));
            stmt.execute();
            System.out.println("Edición ejecutada correctamente.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error SQL al editar: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public boolean eliminarPersonal(String personalId) {
        Connection conn = ConexionBD.getConnection();
        if (conn == null) {
            System.out.println("Fallo: Conexión nula.");
            return false;
        }
        try (CallableStatement stmt = conn.prepareCall("{CALL SP_EliminarPersonal(?)}")) {
            stmt.setString(1, personalId);
            System.out.println("Ejecutando eliminación para ID: " + personalId);
            stmt.execute();
            System.out.println("Eliminación ejecutada correctamente.");
            return true;
        } catch (SQLException e) {
            System.out.println("Error SQL al eliminar: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public List<Personal> listarPersonal() {
        List<Personal> lista = new ArrayList<>();
        Connection conn = ConexionBD.getConnection();
        if (conn == null) return lista;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT PERSONAL_ID, NOMBRE, EMAIL, TELEFONO, ROL FROM PERSONAL")) {
            while (rs.next()) {
                Integer tel = rs.getInt("TELEFONO");
                if (rs.wasNull()) tel = null;
                lista.add(new Personal(
                        rs.getString("PERSONAL_ID"),
                        rs.getString("NOMBRE"),
                        rs.getString("EMAIL"),
                        null,
                        tel,
                        rs.getString("ROL")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return lista;
    }



    public String obtenerUltimoPersonalId() {
        String ultimoId = "P000";
        Connection conn = ConexionBD.getConnection();
        if (conn == null) return ultimoId;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT MAX(PERSONAL_ID) AS ultimo FROM PERSONAL")) {
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

    
        public Personal getPersonalById(String personalId) {
        Personal p = null;
        Connection conn = ConexionBD.getConnection();
        if (conn == null) return null;
        String sql = "SELECT PERSONAL_ID, NOMBRE, EMAIL, CONTRASENA, TELEFONO, ROL FROM PERSONAL WHERE PERSONAL_ID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, personalId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Integer tel = rs.getInt("TELEFONO");
                    if (rs.wasNull()) tel = null;
                    p = new Personal(
                            rs.getString("PERSONAL_ID"),
                            rs.getString("NOMBRE"),
                            rs.getString("EMAIL"),
                            rs.getString("CONTRASENA"),
                            tel,
                            rs.getString("ROL")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return p;
    }

}

