package datos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Angel Eduardo Domínguez Delgado
 */
public class Conexion {
    
    private Connection conexion;
    private String username;
    private String password;
    private static Conexion connect;
    
    public Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            username = "frances";
            password = "construccionSW";
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/siga", username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Conexion(String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.username = username;
            this.password = password;
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/siga",
                    username,password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.print("Error: " + ex.getMessage());
        }
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Connection connection() {
        try {
            return conexion;
        } finally {}
    }
    
    public void close() {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.err.println ("Error: " + e.getMessage () + "\n" + e.getErrorCode ());
        } 
    }
    
    public static Conexion getConnect() {
        return connect;
    }
    
    public static void setConnect(Conexion conexion) {
        Conexion.connect = conexion;
    }
}
