package logica;

import datos.ReservacionDAO;
import java.sql.Date;

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

    @Override
    public boolean agregarReservacion(Reservacion reservacion, int idActividad, int idUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
