package logica;

import datos.ActividadDAO;
import datos.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentacion.Dialogo;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 */
public class Actividad implements ActividadDAO{
    
//    private SimpleStringProperty nombreActividad;
//    private SimpleStringProperty profesorActividad;
//    private SimpleStringProperty idiomaActividad;
//    private Time horaActividad;
//    private Date diaActividad;
//    private SimpleStringProperty tipoActividad;
    private String nombreActividad;
    private String profesorActividad;
    private String idiomaActividad;
    private Time horaActividad;
    private Date diaActividad;
    private String tipoActividad;
    
    public Actividad() {}
    
    public Actividad(String xNombre, String xProfesor, Time horaActividad, Date diaActividad, 
            String xIdioma, String xTipo) {
//        this.nombreActividad = new SimpleStringProperty(xNombre);
//        this.profesorActividad = new SimpleStringProperty(xProfesor);
//        this.idiomaActividad = new SimpleStringProperty(xIdioma);
        nombreActividad = xNombre;
        profesorActividad = xProfesor;
        idiomaActividad = xIdioma;
        tipoActividad = xTipo;
        this.horaActividad = horaActividad;
        this.diaActividad = diaActividad;
        
    }
    
    public String getNombreActividad() {
        //return nombreActividad.get();
        return nombreActividad;
    }
    
    public void setNombreActividad(String xNombre) {
        //nombreActividad.set(xNombre);
        nombreActividad = xNombre;
    }
    
    public String getIdiomaActividad() {
        //return idiomaActividad.get();
        return idiomaActividad;
    }
    
    public void setIdiomaActividad(String xIdioma) {
        //idiomaActividad.set(xIdioma);
        idiomaActividad = xIdioma;
    }
    
    public String getProfesorActividad() {
        //return profesorActividad.get();
        return profesorActividad;
    }
    
    public void setProfesorActividad(String xProfesor) {
        //profesorActividad.set(xProfesor);
        profesorActividad = xProfesor;
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
    
    public String getTipoActividad() {
        //return tipoActividad.get();
        return tipoActividad;
    }
    
    public void setTipoActividad(String xTipo) {
        //tipoActividad.set(xTipo);
        tipoActividad = xTipo;
    } 

    @Override
    public ObservableList<Actividad> consultarActividades(String idiomaActividad, 
            Date diaActividad) {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        Actividad actividadResultado;
        ObservableList<Actividad> listaActividades = FXCollections.observableArrayList();
        
        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT nombreActividad, profesorActividad, tipoActividad, horaActividad FROM actividad WHERE idiomaActividad = ? AND diaActividad = ?";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, idiomaActividad);
            sentencia.setDate(2, diaActividad);
            rs = sentencia.executeQuery();
            
            while(rs.next() && rs != null) {
                actividadResultado = new Actividad();
                actividadResultado.setNombreActividad(rs.getString("nombreActividad"));
                actividadResultado.setProfesorActividad(rs.getString("profesorActividad"));
                actividadResultado.setTipoActividad(rs.getString("tipoActividad"));
                actividadResultado.setHoraActividad(rs.getTime("horaActividad"));
                listaActividades.add(actividadResultado);
            }
        } catch (SQLException ex) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
        return listaActividades;
    }
    
    public int obtenerIDActividad() {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        
        try {
            conexion = new Conexion().connection();
            String consulta = " SELECT idActividad FROM actividad WHERE nombreActividad = ? AND profesorActividad = ? AND horaActividad = ?;";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, nombreActividad);
            sentencia.setString(2, profesorActividad);
            sentencia.setTime(3, horaActividad);
            rs = sentencia.executeQuery();
            
            if(rs.next() && rs != null) {
                return rs.getInt("idActividad");
            }
            
        } catch (SQLException e) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
            return 0;
        }
        return 0;
    }
}