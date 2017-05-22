package datos;

import java.sql.Date;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 */
public interface ReservacionDAO {
    
    public boolean agregarReservacion(int idActividad, String matriculaAlumno);
}
