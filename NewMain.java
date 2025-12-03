/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author josem
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Connection conn = ConexionBD.getConnection();

        if (conn != null) {
            System.out.println("Conexión establecida correctamente.");
            try {
                conn.close(); 
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }
    }
    
}
