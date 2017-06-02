package datos;

/**
 * Interface para la clase Reservacion del proyecto. Se declaran los métodos definidos en el
 * diagrama de clases. Algunos métodos fueron comentados porque no se utilizarán en la parte
 * funcional a presentar del programa.
 */
public interface ReservacionDAO {

    /**
     *
     * Método que agrega una reservación de una actividad a la base de datos del Sistema. Un alumno
     * no podrá reservar la misma actividad dos veces.
     *
     * @param idActividad: ID de la Actividad que se desea reservar
     * @param matriculaAlumno: Matrícula del Alumno que hace la reservación
     * @return Regresa verdadero (true) si la reservación fue exitosa o falso (false) si no se pudo
     * completar la reservación.
     */
    public boolean agregarReservacion(int idActividad, String matriculaAlumno);

    /**
     * Lista de métodos declarados en el diagrama de clases pero que no se utilizarán en la versión
     * actual del programa.
     */
    //public Reservacion consultarReservacion(int noReservacion);
    //public boolean eliminarReservacionAlumno(int noReservacion);
}
