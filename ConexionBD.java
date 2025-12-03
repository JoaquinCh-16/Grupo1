/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author josem
 */
public class ConexionBD {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=VENTA_ALEYUMY_FINAL;encrypt=true;trustServerCertificate=true";
    private static final String USUARIO = "root";
    private static final String CLAVE = "root";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("Conexión exitosa a SQL Server");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Hay error al conectar a la BD");
        }
        return conn;
    }
}
