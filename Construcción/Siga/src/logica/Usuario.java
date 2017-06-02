package logica;

import datos.Conexion;
import datos.UsuarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import presentacion.Dialogo;

/**
 * Clase que contiene los métodos relacionados con los usuarios registrados en el Sistema. La clase
 * implementa los métodos de la interface UsuarioDAO e implementa otros utilizados para la
 * funcionalidad correcta del programa.
 *
 */
public class Usuario implements UsuarioDAO {

    /**
     * Atributos de la clase
     */
    private String nombre;
    private String password;
    private static String USUARIOACTUAL; //Constante que almacena el usuario logueado en el Sistema.

    /**
     * Constructor vacío de la clase. Permite crear objetos tipo Usuario.
     */
    public Usuario() {
    }

    /**
     * Constructor completo de la clase. Permite crear objetos tipo Usuario.
     *
     * @param nombre
     * @param password
     */
    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    /**
     * Bloque de Getters y Setters de la clase. Su documentación no es necesaria.
     *
     * @return
     */
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

    public String getUsuarioActual() {
        return USUARIOACTUAL;
    }

    public void setUsuarioActual(String xUsuarioActual) {
        USUARIOACTUAL = xUsuarioActual;
    }

    /**
     *
     * Método sobreescrito que recupera la constraseña del usuario que quiere ingresar al Sistema y
     * la compara con la registrada (recuperada por medio de otro método)
     *
     * @param nombre: El nombre del Usuario
     * @param password: Contraseña del usuario
     * @return Regresa verdadero(true) si el password de la base de datos del Sistema coincide con
     * el proporcionado o regresa falso(false) si no coinciden.
     */
    @Override
    public boolean consultaUsuario(String nombre, String password) {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        String passRecuperado = null;

        if (existeUsuario(nombre)) {
            try {
                conexion = new Conexion().connection();
                String consulta = "SELECT passwordUsuario FROM usuario WHERE nombreUsuario = ?";
                sentencia = conexion.prepareStatement(consulta);
                sentencia.setString(1, nombre);
                rs = sentencia.executeQuery();

                while (rs.next()) {
                    passRecuperado = rs.getString("passwordUsuario");
                }
            } catch (SQLException e) {
                Dialogo dialogo = new Dialogo();
                dialogo.alertaError();
            } finally {
                Dialogo dialogo = new Dialogo();
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        dialogo.alertaCerrarConexion();
                    }
                }
                if (sentencia != null) {
                    try {
                        sentencia.close();
                    } catch (SQLException ex) {
                        dialogo.alertaCerrarConexion();
                    }
                }
                if (conexion != null) {
                    try {
                        conexion.close();
                    } catch (SQLException ex) {
                        dialogo.alertaCerrarConexion();
                    }
                }
            }
            return passRecuperado.equals(obtenerPassword(password));
        }
        return false;
    }

    /**
     * Método que se encarga de obtener el cifrado de 256 bits del String proporcionado (contraseña
     * ingresada por el usuario).
     *
     * @param password: String proporcionado (Contraseña del usuario).
     * @return Regresa el String cifrado.
     */
    public String obtenerPassword(String password) {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        String passCifrado = null;

        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT sha2(?, 256) AS cifrado";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, password);
            rs = sentencia.executeQuery();

            while (rs.next()) {
                passCifrado = rs.getString("cifrado");
            }
        } catch (SQLException e) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        } finally {
            Dialogo dialogo = new Dialogo();
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    dialogo.alertaCerrarConexion();
                }
            }
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException ex) {
                    dialogo.alertaCerrarConexion();
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException ex) {
                    dialogo.alertaCerrarConexion();
                }
            }
        }
        return passCifrado;
    }

    /**
     * Método encargado de verificar que el usuario exista en la base de datos.
     *
     * @param nombre: Nombre de usuario ingresado
     * @return Regresa verdadero(true) si el usuario existe en la base de datos o falso(false) en
     * caso contrario.
     */
    public boolean existeUsuario(String nombre) {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;

        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT nombreUsuario FROM usuario";
            sentencia = conexion.prepareStatement(consulta);
            rs = sentencia.executeQuery();

            while (rs.next()) {
                if (nombre.equals(rs.getString("nombreUsuario"))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        } finally {
            Dialogo dialogo = new Dialogo();
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    dialogo.alertaCerrarConexion();
                }
            }
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException ex) {
                    dialogo.alertaCerrarConexion();
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException ex) {
                    dialogo.alertaCerrarConexion();
                }
            }
        }
        return false;
    }

    /**
     *
     * Método que establece la variable global y constante USUARIOACTUAL con la matrícula del alumno
     * que ingresó al sistema.
     *
     * @param nombre: Nombre de Usuario del alumno.
     */
    public void obtenerMatriculaAlumno(String nombre) {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;

        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT alumno.matriculaAlumno FROM alumno, usuario WHERE "
                + "alumno.usuario_idusuario = usuario.idusuario AND usuario.nombreUsuario = ?";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, nombre);
            rs = sentencia.executeQuery();

            if (rs.next()) {
                USUARIOACTUAL = rs.getString("matriculaAlumno");
            }
        } catch (SQLException ex) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        } finally {
            Dialogo dialogo = new Dialogo();
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    dialogo.alertaCerrarConexion();
                }
            }
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException ex) {
                    dialogo.alertaCerrarConexion();
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException ex) {
                    dialogo.alertaCerrarConexion();
                }
            }
        }
    }

}
