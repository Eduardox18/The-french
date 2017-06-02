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
                + "autoevaluacion_idAutoevaluacion) VALUES (?, ?, ?, ?, ?);";
            sentencia = conexion.prepareStatement(consultaBitacora);
            sentencia.setInt(1, bitacora.getTiempoEmpleado());
            sentencia.setString(2, bitacora.getComentario());
            sentencia.setDate(3, bitacora.getFechaBitacora());
            sentencia.setInt(4, bitacora.getIdPortafolioEvidencias());
            sentencia.setInt(5, bitacora.getIdAutoevaluacion());

            sentencia.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
        return false;
    }

    public int recuperarNoActualBitacora() {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT IFNULL((SELECT MAX(noBitacora) FROM "
                + "bitacora), 0) AS noBitacora;";
            sentencia = conexion.prepareStatement(consulta);
            rs = sentencia.executeQuery();

            if (rs.next()) {
                return rs.getInt("noBitacora") + 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
        return 0;
    }
    
    public int recuperarUltimoNoBitacora() {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT MAX(noBitacora) FROM bitacora";
            sentencia = conexion.prepareStatement(consulta);
            rs = sentencia.executeQuery();

            if (rs.next()) {
                return rs.getInt("MAX(noBitacora)");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
        return 0;
    }
    
    public boolean guardarActividadesAsistidas(int nrcCurso) {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        Usuario usuario = new Usuario();
        
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
            sentencia.setInt(1, nrcCurso);
            sentencia.setString(2, usuario.getUsuarioActual());
            rs = sentencia.executeQuery();
            
            while(rs.next()) {
                updateActividadesAsistidas(rs.getInt("idasistenciaActividad"));
            }
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
        return false;
    }
    
    private void updateActividadesAsistidas(int idAAsistidas) {
        Connection conexion;
        PreparedStatement sentencia;
        
        try {
            conexion = new Conexion().connection();
            String update = "INSERT INTO actividadesPresencialesBitacora "
                + "VALUES (?, ?)";
            sentencia = conexion.prepareStatement(update);
            sentencia.setInt(1, recuperarUltimoNoBitacora());
            sentencia.setInt(2, idAAsistidas);
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
    }
    
    public boolean guardarActividadesEscritas(int nrcCurso) {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        Usuario usuario = new Usuario();
        PortafolioEvidencias pEvidencias = new PortafolioEvidencias();
        
        try {
            conexion = new Conexion().connection();
            String update = "SELECT idARealizada FROM actividadRealizada "
                + "WHERE alumno_matriculaAlumno = ? AND "
                + "portafolioEvidencias_idportafolioEvidencias = ?";
            sentencia = conexion.prepareStatement(update);
            sentencia.setString(1, usuario.getUsuarioActual());
            sentencia.setInt(2, pEvidencias.recuperarIDPortafolio(nrcCurso));
            rs = sentencia.executeQuery();
            
            while(rs.next()) {
                updateActividadesEscritas(rs.getInt("idARealizada"));
            }
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
        return false;
    }
    
    private void updateActividadesEscritas(int AEscritas) {
        Connection conexion;
        PreparedStatement sentencia;
        
        try {
            conexion = new Conexion().connection();
            String update = "INSERT INTO actividadesEscritasBitacora "
                + "VALUES (?, ?)"; 
            sentencia = conexion.prepareStatement(update);
            sentencia.setInt(1, AEscritas);
            sentencia.setInt(2, recuperarUltimoNoBitacora());
            sentencia.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
    }
    
    public boolean comprobarBitacoraExistente(int nrcCurso) {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        Usuario usuario = new Usuario();
        
        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT DISTINCT noBitacora FROM bitacora, "
                + "portafolioEvidencias, inscripcion, grupoAlumno, curso "
                + "WHERE bitacora.portafolioEvidencias_idportafolioEvidencias "
                + "= portafolioEvidencias.idportafolioEvidencias AND "
                + "portafolioEvidencias.inscripcion_idinscripcion = "
                + "inscripcion.idinscripcion AND "
                + "inscripcion.alumno_matriculaAlumno = ? AND "
                + "inscripcion.grupoAlumno_idGrupoAlumno = "
                + "grupoAlumno.idGrupoAlumno AND grupoAlumno.curso_nrcCurso "
                + "= ?";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, usuario.getUsuarioActual());
            sentencia.setInt(2, nrcCurso);
            rs = sentencia.executeQuery();
            
            return rs.next();
            
        } catch (SQLException ex) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
        return false;
    }
    
}
