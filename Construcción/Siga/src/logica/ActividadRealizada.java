package logica;

import datos.ActividadRealizadaDAO;
import datos.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentacion.Dialogo;

/**
 *
 * @author lalo
 */
public class ActividadRealizada implements ActividadRealizadaDAO {
    
    private String tipoARealizada;
    private String nombreARealizada;
    private Date fechaARealizada;
    private Integer tiempoEmpleado;

    public String getTipoARealizada() {
        return tipoARealizada;
    }

    public void setTipoARealizada(String tipoARealizada) {
        this.tipoARealizada = tipoARealizada;
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
    
    public int getTiempoEmpleado() {
        return tiempoEmpleado;
    }

    public void setTiempoEmpleado(int tiempoEmpleado) {
        this.tiempoEmpleado = tiempoEmpleado;
    }

    @Override
    public ObservableList<ActividadRealizada> obtenerActividades(int nrcCurso, String matriculaAlumno) {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        ActividadRealizada actividadResultado;
        ObservableList<ActividadRealizada> listaActividadesRealizadas = FXCollections.observableArrayList();
        
        try {
            conexion = new Conexion().connection();
            String consulta = "select nombreAEscrita, tipoAEscrita, "
                + "fechaRealizada, tiempoDedicado from actividadEscrita, "
                + "actividadRealizada, portafolioEvidencias where "
                + "actividadRealizada.actividadEscrita_codigoAEscrita = "
                + "actividadEscrita.codigoAEscrita and "
                + "portafolioEvidencias.idPortafolioEvidencias = "
                + "actividadRealizada.portafolioEvidencias_idportafolioEvidencias "
                + "and actividadRealizada.alumno_matriculaAlumno = ? "
                + "and actividadEscrita.curso_nrcCurso = ?;";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, matriculaAlumno);
            sentencia.setInt(2, nrcCurso);
            rs = sentencia.executeQuery();
            
            while(rs.next()) {
                actividadResultado = new ActividadRealizada();
                actividadResultado.setNombreARealizada(rs.getString("nombreAEscrita"));
                actividadResultado.setTipoARealizada(rs.getString("tipoAEscrita"));
                actividadResultado.setTiempoEmpleado(rs.getInt("tiempoDedicado"));
                actividadResultado.setFechaARealizada(rs.getDate("fechaRealizada"));
                listaActividadesRealizadas.add(actividadResultado);
            }
        } catch (SQLException ex) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
        return listaActividadesRealizadas;
    }
    
    
}
