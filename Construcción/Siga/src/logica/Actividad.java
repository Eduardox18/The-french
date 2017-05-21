package logica;

import datos.ActividadDAO;
import datos.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentacion.Dialogo;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 */
public class Actividad implements ActividadDAO{
    
    private SimpleStringProperty nombreActividad;
    private SimpleStringProperty profesorActividad;
    private SimpleStringProperty idiomaActividad;
    private Time horaActividad;
    private Date diaActividad;
    private SimpleStringProperty tipoActividad;
    
    public Actividad() {}
    
    public Actividad(String xNombre, String xProfesor, Time horaActividad, Date diaActividad, 
            String xIdioma) {
        this.nombreActividad = new SimpleStringProperty(xNombre);
        this.profesorActividad = new SimpleStringProperty(xProfesor);
        this.idiomaActividad = new SimpleStringProperty(xIdioma);
        this.horaActividad = horaActividad;
        this.diaActividad = diaActividad;
    }
    
    public String getNombre() {
        return nombreActividad.get();
    }
    
    public void setNombre(String xNombre) {
        nombreActividad.set(xNombre);
    }
    
    public String getIdioma() {
        return idiomaActividad.get();
    }
    
    public void setIdioma(String xIdioma) {
        idiomaActividad.set(xIdioma);
    }
    
    public String getProfesor() {
        return profesorActividad.get();
    }
    
    public void setProfesor(String xProfesor) {
        profesorActividad.set(xProfesor);
    }
    
    public Time getHoraActividad() {
        return horaActividad;
    }
    
    public void setHoraActividad(Time horaActividad) {
        this.horaActividad = horaActividad;
    }
    
    public Date getDiaActividad() {
        return diaActividad;
    }
    
    public void setDiaActividad(Date diaActividad) {
        this.diaActividad = diaActividad;
    }
    
    public String getTipo() {
        return tipoActividad.get();
    }
    
    public void setTipo(String xTipo) {
        tipoActividad.set(xTipo);
    } 

    @Override
    public ObservableList<Actividad> consultarActividades(String idiomaActividad, 
            Date diaActividad) {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        Actividad actividadResultado = new Actividad();
        ObservableList<Actividad> listaActividades = FXCollections.observableArrayList();
        
        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT nombreActividad, profesorActividad, tipoActividad, horaActividad FROM actividad WHERE idiomaActividad = ? AND diaActividad = ?";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, idiomaActividad);
            sentencia.setDate(2, diaActividad);
            rs = sentencia.executeQuery();
            
            while(rs.next() && rs != null) {
                actividadResultado.setNombre(rs.getString("nombreActividad"));
                actividadResultado.setProfesor(rs.getString("profesorActividad"));
                actividadResultado.setTipo(rs.getString("tipoActividad"));
                actividadResultado.setHoraActividad(rs.getTime("horaActividad"));
                listaActividades.add(actividadResultado);
            }
        } catch (SQLException ex) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
        return listaActividades;
    }
    
    public int obtenerIDActividad(String nombreActividad, String profesorActividad, 
            Date horaActividad) {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        return 0;
        //EN CONSTRUCCIÓN
    }
}