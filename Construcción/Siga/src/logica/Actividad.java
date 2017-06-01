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
    
    private String nombreActividad;
    private String tipoActividad;
    private Time horaActividad;
    private Date diaActividad;
    private String asesorActividad;
    
    /**
     * 
     * Constructor vacio de la clase
     */
    public Actividad() {}
    
    public Actividad(String nombre, String tipoActividad, Time horaActividad, Date diaActividad,
    String asesorActividad) {
        this.nombreActividad = nombre;
        this.tipoActividad = tipoActividad;
        this.horaActividad = horaActividad;
        this.diaActividad = diaActividad;
        this.asesorActividad = asesorActividad;
    }
    
    public String getNombreActividad() {
        return nombreActividad;
    }
    
    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }
    
    public String getTipoActividad() {
        return tipoActividad;
    }
    
    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
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
    
    public String getAsesorActividad() {
        return asesorActividad;
    }

    public void setAsesorActividad(String asesorActividad) {
        this.asesorActividad = asesorActividad;
    }
    
    /**
     * 
     * Método que devuelve un observable list con todos las actividades que cumplen
     * con los parámetros
     * @param nrcCurso NRC del curso que se desea buscar
     * @param diaActividad Fecha en la que se desean buscar las actividades
     * @return ObservableList de objetos Actividad, donde se encuentran todas las
     * coincidencias de la búsqueda
     */
    @Override
    public ObservableList<Actividad> consultarActividades(int nrcCurso, Date diaActividad) {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        Actividad actividadResultado;
        ObservableList<Actividad> listaActividades = FXCollections.observableArrayList();
        
        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT actividad.nombreActividad, CONCAT (asesor.nombreAsesor,' ', "
                    + "asesor.apPaternoAsesor, ' ', asesor.apMaternoAsesor) AS asesorActividad, "
                    + "actividad.tipoActividad, actividad.horaActividad FROM actividad, curso, "
                    + "asesor WHERE curso.nrcCurso = ? AND curso.nrcCurso = "
                    + "actividad.curso_nrcCurso AND curso.asesor_noPersonalAsesor = "
                    + "asesor.noPersonalAsesor AND actividad.diaActividad = ?";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, nrcCurso);
            sentencia.setDate(2, diaActividad);
            rs = sentencia.executeQuery();
            
            while(rs.next()) {
                actividadResultado = new Actividad();
                actividadResultado.setNombreActividad(rs.getString("nombreActividad"));
                actividadResultado.setAsesorActividad(rs.getString("asesorActividad"));
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
    
    @Override
    public ObservableList<Actividad> consultarActividadesAsistidas(int nrcCurso, 
        String matriculaAlumno) {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        Actividad actividadResultado;
        ObservableList<Actividad> listaActividadesAsistidas = FXCollections.
            observableArrayList();
        try {
            conexion = new Conexion().connection();
            String consulta = "select nombreActividad, diaActividad from "
                + "actividad, asistenciaActividad, reservacion where "
                + "asistenciaActividad.reservacion_noReservacion = "
                + "reservacion.noReservacion and actividad.idActividad = "
                + "reservacion.actividad_idActividad and "
                + "actividad.curso_nrcCurso = ? and "
                + "reservacion.alumno_matriculaAlumno = ? AND "
                + "asistenciaActividad.presencia = 1";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, nrcCurso);
            sentencia.setString(2, matriculaAlumno);
            rs = sentencia.executeQuery();
            
            while(rs.next()) {
                actividadResultado = new Actividad();
                actividadResultado.setNombreActividad(rs.getString(
                    "nombreActividad"));
                actividadResultado.setDiaActividad(rs.getDate("diaActividad"));
                listaActividadesAsistidas.add(actividadResultado);
            }
        } catch (SQLException ex) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
        return listaActividadesAsistidas;
    }
    
    /**
     * Devuelve el id del objeto Actividad que lo invoca
     * @return ID de la actividad
     */
    public int obtenerIDActividad() {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        
        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT idActividad FROM actividad WHERE nombreActividad = ? AND horaActividad = ?";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, nombreActividad);
            sentencia.setTime(2, horaActividad);
            rs = sentencia.executeQuery();
            
            if(rs.next()) {
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