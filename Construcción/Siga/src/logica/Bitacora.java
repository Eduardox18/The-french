package logica;

import datos.BitacoraDAO;
import datos.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion.Dialogo;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 */
public class Bitacora implements BitacoraDAO {

    private int tiempoEmpleado;
    private String comentario;
    private Date fechaBitacora;
    private int idPortafolioEvidencias;
    private int idAutoevaluacion;

    public Bitacora() {
    }

    public Bitacora(Integer tiempoEmpleado, String comentario, 
        Date fechaBitacora, int idPortafolioEvidencias) {
        this.tiempoEmpleado = tiempoEmpleado;
        this.comentario = comentario;
        this.fechaBitacora = fechaBitacora;
        this.idPortafolioEvidencias = idPortafolioEvidencias;
    }

    public Integer getTiempoEmpleado() {
        return tiempoEmpleado;
    }

    public void setTiempoEmpleado(Integer tiempoEmpleado) {
        this.tiempoEmpleado = tiempoEmpleado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaBitacora() {
        return fechaBitacora;
    }

    public void setFechaBitacora(Date fechaBitacora) {
        this.fechaBitacora = fechaBitacora;
    }

    public int getIdPortafolioEvidencias() {
        return idPortafolioEvidencias;
    }

    public void setIdPortafolioEvidencias(int idPortafolioEvidencias) {
        this.idPortafolioEvidencias = idPortafolioEvidencias;
    }
    
    public int getIdAutoevaluacion() {
        return idAutoevaluacion;
    }
    
    public void setIdAutoevaluacion(int idAutoevaluacion) {
        this.idAutoevaluacion = idAutoevaluacion;
    }
     
    

    /**
     *
     * Agrega una nueva bitácora a la base de datos.
     *
     * @param bitacora Objeto de tipo Bitacora de donde se obtienen todos los
     * datos.
     * @return true si la operación es exitoda o false en caso de que ocurra
     * algún error.
     */
    @Override
    public boolean registrarBitacora(Bitacora bitacora) {
        Connection conexion;
        PreparedStatement sentencia;

        try {
            conexion = new Conexion().connection();
            String consultaBitacora = "INSERT INTO Bitacora (tiempoEmpleado, "
                + "comentario, fechaBitacora, "
                + "portafolioEvidencias_idportafolioEvidencias, "
                + "autoevaluacion_idAutoevaluacion) VALUES (?, ?, ?, ?);";
            sentencia = conexion.prepareStatement(consultaBitacora);
            sentencia.setInt(1, bitacora.getTiempoEmpleado());
            sentencia.setString(2, bitacora.getComentario());
            sentencia.setDate(3, bitacora.getFechaBitacora());
            sentencia.setInt(4, bitacora.getIdPortafolioEvidencias());
            sentencia.setInt(5, bitacora.getIdAutoevaluacion());

            sentencia.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
        return false;
    }

    public int recuperarNoBitacora() {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT MAX(noBitacora) FROM Bitacora";
            sentencia = conexion.prepareStatement(consulta);
            rs = sentencia.executeQuery();

            if (rs.next()) {
                return rs.getInt("MAX(noBitacora)") + 1;
            } else {
                return 1;
            }
        } catch (SQLException ex) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
        return 0;
    }
    
    private boolean guardarActividadesAsistidas(int nrcCurso, int noBitacora) {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        
        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT idasistenciaActividad FROM actividad, "
                + "asistenciaActividad, reservacion WHERE "
                + "asistenciaActividad.reservacion_noReservacion = "
                + "reservacion.noReservacion AND actividad.idActividad = "
                + "reservacion.actividad_idActividad AND "
                + "actividad.curso_nrcCurso = ? AND "
                + "reservacion.alumno_matriculaAlumno = ? AND "
                + "asistenciaActividad.presencia = 1";
            sentencia = conexion.prepareStatement(consulta);
            rs = sentencia.executeQuery();
            
            while(rs.next() && rs != null) {
                //Llamada a la función que hacer el INSERT
            }
            return true;
        } catch (SQLException ex) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
        return false;
    }
}
