package datos;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 * Interface con los métodos de conexión de la clase Reservación
 */
public interface ReservacionDAO {
    
    /**
     * 
     * Agrega una reservación del alumno a la base de datos
     * @param idActividad ID de la Actividad que se desea reservar
     * @param matriculaAlumno Matrícula del Alumno que hace la reservación
     * @return true si la operación es exitosa, false en el caso contrario.
     */
    public boolean agregarReservacion(int idActividad, String matriculaAlumno);
}
