package logica;

import datos.Conexion;
import datos.ReservacionDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import presentacion.Dialogo;

/**
 * Clase que contiene los métodos relacionados con las actividades del Sistema. La clase implementa
 * los métodos de la interface ReservacionDAO e implementa otros utilizados para la funcionalidad
 * correcta del programa.
 */
public class Reservacion implements ReservacionDAO {

    /**
     * Atributo de la clase
     */
    private Date fechaReservacion;

    /**
     * Constructor vacío de la clase. Permite crear objetos tipo Reservacion.
     */
    public Reservacion() {}

    /**
     * Constructor completo de la clase. Permite crear objetos tipo Reservacion.
     *
     * @param fechaReservacion
     */
    public Reservacion(Date fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }

    /**
     * Bloque de Getters y Setter de la clase. Su documentación no es necesaria.
     */
    public Date getFechaReservacion() {
        return fechaReservacion;
    }

    public void setFechaReservacion(Date fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }

    /**
     *
     * Método sobreescrito que agrega una reservación de una actividad hecha por el alumno a la base
     * de datos del Sistema.
     *
     * @param idActividad ID de la Actividad que se desea reservar
     * @param matriculaAlumno Matrícula del Alumno que hace la reservación
     * @return Regresa verdadero (true) si la reservación es exitosa o falso(false) en el caso
     * contrario.
     */
    @Override
    public boolean agregarReservacion(int idActividad, String matriculaAlumno) {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        LocalDate fecha;
        fecha = LocalDate.now();
        Date fechasql = Date.valueOf(fecha);

        try {
            conexion = new Conexion().connection();
            String consulta = "INSERT INTO reservacion (fechaReservacion, "
                + "actividad_idActividad, alumno_matriculaAlumno) VALUES "
                + "(?, ?, ?);";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setDate(1, fechasql);
            sentencia.setInt(2, idActividad);
            sentencia.setString(3, matriculaAlumno);
            sentencia.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        } finally {
            Dialogo dialogo = new Dialogo();
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
     * Método que comprueba que el alumno no tenga ya una reservación de la actividad proporcionada.
     *
     * @param matriculaAlumno La matrícula del alumno del que se desea consultar.
     * @param idActividad El ID de la actividad de la que se desea comprobar
     * @return Regresa verdadero(true) si el alumno no ha reservado esa actividad o falso(false) si
     * ya reservó esa actividad.
     */
    public boolean comprobarReservaciones(String matriculaAlumno, int idActividad) {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;

        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT actividad_idActividad, alumno_matriculaAlumno FROM "
                + "reservacion WHERE alumno_matriculaAlumno = ? AND actividad_idActividad = ?";
            sentencia = conexion.prepareCall(consulta);
            sentencia.setString(1, matriculaAlumno);
            sentencia.setInt(2, idActividad);
            rs = sentencia.executeQuery();

            return !rs.next();
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
        return false;
    }
}
