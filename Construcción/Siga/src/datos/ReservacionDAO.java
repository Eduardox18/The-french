package datos;

import java.sql.Date;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 */
public interface ReservacionDAO {
    
    public boolean agregarReservacion(Date fechaReservacion, int idActividad, 
            String matriculaAlumno);
}
