package datos;

/**
 * Interface para la clase Usuario del proyecto. Se declaran los métodos definidos en el
 * diagrama de clases y los utilizados en la implementación del programa. Algunos métodos fueron 
 * comentados porque no se utilizarán en la parte
 * funcional a presentar del programa.
 */
public interface UsuarioDAO {

    /**
     * 
     * Método que se utiliza para consultar la información del usuario y comprobar si existe en 
     * la base de datos del Sistema.
     * @param nombre: Nombre de usuario.
     * @param password: Contraseña de usuario
     * @return Regresa verdadero(true) si el usuario existe en la base de datos y su contraseña
     * coincide con la proporcionada o regresa falso(false) si el usuario no existe en el Sistema
     * o la contraseña proporcionada no coincide con la registrada.
     */
    public boolean consultaUsuario(String nombre, String password);
    
    /**
     * Lista de métodos declarados en el diagrama de clases pero que no se utilizarán en la versión
     * actual del programa.
     */
    //public boolean agregarUsuario(String nombre, String password);
}
