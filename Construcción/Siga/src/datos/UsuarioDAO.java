package datos;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 * Clase con las interfaces de conexión de la clase Usuario
 */
public interface UsuarioDAO {

    /**
     * 
     * Muestra los datos de un Usuario seleccionado
     * @param nombre El nombre del Usuario
     * @param password Contraseña del usuario
     * @return true si la operación es exitosa, false en el caso contrario.
     */
    public boolean consultaUsuario(String nombre, String password);
}
