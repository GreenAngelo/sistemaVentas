package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private final String URL = "jdbc:sqlserver://localhost:1433;databaseName=DIHAVI_AZUA;user=Diangelo_Aybar;password=diangelo11;";

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return con;
    }
}



