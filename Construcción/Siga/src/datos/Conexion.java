package datos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion.Dialogo;

/**
 * Clase que permite hacer la conexión entre el programa y la base de datos del Sistema. La base de
 * datos está registrada en la herramienta mySQL.
 */
public class Conexion {

    private Connection conexion;
    private String username;
    private String password;
    private static Conexion connect;

    /**
     * Constructor vacío de la clase que permite la conexión con la base de datos registrada y
     * predetermina una usuario y una contraseña ya registrados en las bases de datos de los
     * ordenadores personales de las personas que utilizarán el Sistema.
     */
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

    /**
     * Constructor que permite la conexión con la base de datos registrada.
     *
     * @param username: Usuario de mySQL que tiene permisos para ingresar a la base de datos.
     * @param password: Contraseña del usuario de mySQL que tiene permisos para ingresar a la base
     * de datos.
     */
    public Conexion(String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.username = username;
            this.password = password;
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/siga",
                username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.print("Error: " + ex.getMessage());
        }
    }

    /**
     * Bloque de Getters y Setters de la clase. Su documentación no es necesaria.
     */
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

    /**
     * Función que abre la conexión con la base de datos del Sistema.
     *
     * @return
     */
    public Connection connection() {
        try {
            return conexion;
        } finally {
        }
    }

    /**
     * Función que permite cerrar la conexión con la base de datos del Sistema.
     */
    public void close() {
        try {
            conexion.close();
        } catch (SQLException e) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
    }

    public static Conexion getConnect() {
        return connect;
    }

    public static void setConnect(Conexion conexion) {
        Conexion.connect = conexion;
    }
}
