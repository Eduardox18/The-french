package datos;

import logica.Reservacion;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 */
public interface ReservacionDAO {
    
    public boolean agregarReservacion(Reservacion reservacion, int idActividad, int idUsuario);
}
