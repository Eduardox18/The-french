package logica;

import datos.Conexion;
import datos.UsuarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import presentacion.Dialogo;

/**
 * Clase Usuario encargada de verificar la existencia de un usuario en la base de datos.
 *
 * @author Angel Eduardo Domínguez Delgado
 */
public class Usuario implements UsuarioDAO {

    private String nombre;
    private String password;

    public Usuario() {
    }

    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Método que verifica que coincida la contraseña del usuario ingresado con la registrada en la
     * base de datos.
     *
     * @param nombre Nombre de usuario
     * @param password Contraseña de usuario
     * @return Regresa verdadero o falso, según la coincidencia de la contraseña del usuario en la
     * base de datos.
     */
    @Override
    public boolean consultaUsuario(String nombre, String password) {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        String passRecuperado = null;

        if (existeUsuario(nombre)) {
            try {
                conexion = new Conexion().connection();
                String consulta = "SELECT passwordUsuario FROM usuario WHERE nombreUsuario = ?";
                sentencia = conexion.prepareStatement(consulta);
                sentencia.setString(1, nombre);
                rs = sentencia.executeQuery();

                if (rs.next()) {
                    passRecuperado = rs.getString("passwordUsuario");
                }
            } catch (SQLException e) {
                Dialogo dialogo = new Dialogo();
                dialogo.alertaError();
            }
            return passRecuperado.equals(obtenerPassword(password));
        }
        return false;
    }

    /**
     * Método que se encarga de obtener el cifrado de la contraseña ingresada para compararla con la
     * registrada en la base de datos.
     *
     * @param password Contraseña del usuario.
     * @return Regresa la contraseña ya cifrada.
     */
    public String obtenerPassword(String password) {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        String passCifrado = null;

        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT sha2(?, 256) AS cifrado";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, password);
            rs = sentencia.executeQuery();

            if (rs.next()) {
                passCifrado = rs.getString("cifrado");
            }
        } catch (SQLException e) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
        return passCifrado;
    }

    /**
     * Método encargado de verificar que el usuario exista en la base de datos.
     *
     * @param nombre Nombre de usuario ingresado
     * @return Regresa verdadero o falso según la existencia del usuario en la base de datos.
     */
    public boolean existeUsuario(String nombre) {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        String usuario;

        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT nombreUsuario FROM usuario";
            sentencia = conexion.prepareStatement(consulta);
            rs = sentencia.executeQuery();

            if (rs.next() && rs != null) {
                usuario = rs.getString("nombreUsuario");
                if (nombre.equals(usuario)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
        return false;
    }

}
