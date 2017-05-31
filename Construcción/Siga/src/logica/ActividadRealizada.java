package logica;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author lalo
 */
public class ActividadRealizada {
    
    private String codigoARealizada;
    private String nombreARealizada;
    private Date fechaARealizada;
    private Time tiempoEmpleado;
    
    public String getCodigoARealizada() {
        return codigoARealizada;
    }

    public void setCodigoARealizada(String codigoARealizada) {
        this.codigoARealizada = codigoARealizada;
    }

    public String getNombreARealizada() {
        return nombreARealizada;
    }

    public void setNombreARealizada(String nombreARealizada) {
        this.nombreARealizada = nombreARealizada;
    }

    public Date getFechaARealizada() {
        return fechaARealizada;
    }

    public void setFechaARealizada(Date fechaARealizada) {
        this.fechaARealizada = fechaARealizada;
    }
    
    public boolean guardarARealizada(ActividadRealizada aRealizada) {
        return false;
    }
    
    public Time getTiempoEmpleado() {
        return tiempoEmpleado;
    }

    public void setTiempoEmpleado(Time tiempoEmpleado) {
        this.tiempoEmpleado = tiempoEmpleado;
    }
}
