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
 *
 * @author Angel Eduardo Domínguez Delgado
 */
public class Reservacion implements ReservacionDAO {
    
    private Date fechaReservacion;

    public Date getFechaReservacion() {
        return fechaReservacion;
    }

    public void setFechaReservacion(Date fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }

    /**
     * 
     * Agrega una reservación del alumno a la base de datos
     * @param idActividad ID de la Actividad que se desea reservar
     * @param matriculaAlumno Matrícula del Alumno que hace la reservación
     * @return true si la operación es exitosa, false en el caso contrario.
     */
    @Override
    public boolean agregarReservacion(int idActividad, String matriculaAlumno) {
        Connection conexion;
        PreparedStatement sentencia;
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
        }
        return false;
    }
    
    /**
     * 
     * Comprueba que el alumno no se encuntre ya inscrito en el curso
     * @param matriculaAlumno La matrícula del alumno del que se desea consultar
     * @param idActividad El ID de la actividad de la que se desea comprobar
     * @return True si el alumno no se encuentra inscrito y false si aun no
     * ha inscrito esa actividad
     */
    public boolean comprobarReservaciones(String matriculaAlumno, int idActividad) {
        Connection conexion;
        PreparedStatement sentencia;
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
        }
        return false;
    }
}
