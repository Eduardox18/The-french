package datos;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 */
public interface UsuarioDAO {
    public boolean consultaUsuario(String nombre, String password);
    
    public String obtenerPassword(String password);
}
