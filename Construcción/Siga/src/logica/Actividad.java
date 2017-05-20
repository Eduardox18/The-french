package logica;

import datos.ActividadDAO;
import datos.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    @Override
    public ObservableList<Actividad> consultarActividades() {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        Actividad actividadResultado = new Actividad();
        ObservableList<Actividad> listaActividades = FXCollections.observableArrayList();
        
        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT * FROM actividad";
            sentencia = conexion.prepareStatement(consulta);
            rs = sentencia.executeQuery();
            
            while(rs.next() && rs != null) {
                actividadResultado.setNombre(rs.getString("nombreActividad"));
                actividadResultado.setProfesor(rs.getString("profesorActividad"));
                actividadResultado.setHoraActividad(rs.getTime("horaActividad"));
                actividadResultado.setDiaActividad(rs.getDate("diaActividad"));
                actividadResultado.setIdioma(rs.getString("idiomaActividad"));
                listaActividades.add(actividadResultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Actividad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaActividades;
    }
    
    public int obtenerIDActividad() {
        return 0;
    }
}