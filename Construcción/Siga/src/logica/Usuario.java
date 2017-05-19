package logica;

import datos.Conexion;
import datos.UsuarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 */
public class Usuario implements UsuarioDAO {
    
    private String nombre;
    private String password;
    
    public Usuario() {}
    
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

    @Override
    public boolean consultaUsuario(String nombre, String password) {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        String passRecuperado = null;
        
        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT passwordUsuario FROM Usuario WHERE nombreUsuario = ?";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, nombre);
            rs = sentencia.executeQuery();
            
            if(rs.next()) {
                passRecuperado = rs.getString("passwordUsuario");
            }
        } catch (SQLException e) {
            
        }
        return passRecuperado.equals(obtenerPassword(password));
    }
    
    @Override
    public String obtenerPassword(String password) {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        String passCifrado = null;
        
        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT sha2('?', 256) AS cifrado";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, nombre);
            rs = sentencia.executeQuery();
            
            if(rs.next()) {
                passCifrado = rs.getString("cifrado");
            }
        } catch (SQLException e) {
            //AlertaGUI
        }
        return passCifrado;
    }
    
    
    
    
}
