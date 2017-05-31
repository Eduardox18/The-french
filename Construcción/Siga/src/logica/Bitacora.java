package logica;

import datos.BitacoraDAO;
import datos.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time; 
import presentacion.Dialogo;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 */
public class Bitacora implements BitacoraDAO {
    
    private Time tiempoEmpleado;
    private String comentario;
    private Date fechaBitacora;
    private int idPortafolioEvidencias;
    private int nrcCurso;
    
    public Bitacora() {}
    
    public Bitacora(Time tiempoEmpleado, String comentario, Date fechaBitacora, 
            int idPortafolioEvidencias, int nrcCurso) {
        this.tiempoEmpleado = tiempoEmpleado;
        this.comentario = comentario;
        this.fechaBitacora = fechaBitacora;
        this.idPortafolioEvidencias = idPortafolioEvidencias;
        this.nrcCurso = nrcCurso;
    }

    public Time getTiempoEmpleado() {
        return tiempoEmpleado;
    }

    public void setTiempoEmpleado(Time tiempoEmpleado) {
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

    public int getNrcCurso() {
        return nrcCurso;
    }

    public void setNrcCurso(int nrcCurso) {
        this.nrcCurso = nrcCurso;
    }

    /**
     * 
     * Agrega una nueva bitácora a la base de datos.
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
            String consulta = "INSERT INTO Bitacora (tiempoEmpleado, "
                    + "comentario, fechaBitacora, "
                    + "portafolioEvidencias_idportafolioEvidencias, "
                    + "curso_nrcCurso) VALUES (?, ?, ?, ?, ?);";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setTime(1, bitacora.getTiempoEmpleado());
            sentencia.setString(2, bitacora.getComentario());
            sentencia.setDate(3, bitacora.getFechaBitacora());
            sentencia.setInt(4, bitacora.getIdPortafolioEvidencias());
            sentencia.setInt(5, bitacora.getNrcCurso());
            
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
            
            if(rs.next()) {
                return rs.getInt("MAX(noBitacora)");
            } else {
                return 1;
            }
        } catch (SQLException ex) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
        return 0;
    }
    
    public int recuperarIDPortafolio(int nrcCurso) {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        Usuario usuario = new Usuario();
        String matriculaAlumno = usuario.getUsuarioActual();
        
        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT idportafolioEvidencias FROM "
                    + "portafolioEvidencias, inscripcion, alumno, inscripcion, "
                    + "grupoAlumno WHERE "
                    + "portafolioEvidencias.inscripcion_idinscripcion = "
                    + "inscripcion.idinscripcion AND "
                    + "inscripcion.alumno_matriculaAlumno = ?  AND "
                    + "inscripcion.grupoAlumno_idGrupoAlumno = "
                    + "grupoAlumno.idGrupoAlumno AND "
                    + "grupoAlumno.curso_nrcCurso = ?;";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, matriculaAlumno);
            sentencia.setInt(2, nrcCurso);
            rs = sentencia.executeQuery();
            
            if(rs.next()) {
                return rs.getInt("idportafolioEvidencias");
            }
        } catch (SQLException ex) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
        return 0;
    }
}
