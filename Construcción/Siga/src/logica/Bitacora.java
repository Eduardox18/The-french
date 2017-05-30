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
    private int codigoActividad;
    private String comentario;
    private int desempenoSeccion;
    private Date fechaBitacora;
    private int resultadoAutoevaluacion;
    private int idPortafolioEvidencias;
    private int nrcCurso;
    
    public Bitacora() {}
    
    public Bitacora(Time tiempoEmpleado, int codigoActividad, 
            String comentario, int desempenoSeccion, Date fechaBitacora, 
            int resultadoAutoevaluacion, int idPortafolioEvidencias, 
            int nrcCurso) {
        this.tiempoEmpleado = tiempoEmpleado;
        this.codigoActividad = codigoActividad;
        this.comentario = comentario;
        this.desempenoSeccion = desempenoSeccion;
        this.fechaBitacora = fechaBitacora;
        this.resultadoAutoevaluacion = resultadoAutoevaluacion;
        this.idPortafolioEvidencias = idPortafolioEvidencias;
        this.nrcCurso = nrcCurso;
    }

    public Time getTiempoEmpleado() {
        return tiempoEmpleado;
    }

    public void setTiempoEmpleado(Time tiempoEmpleado) {
        this.tiempoEmpleado = tiempoEmpleado;
    }

    public int getCodigoActividad() {
        return codigoActividad;
    }

    public void setCodigoActividad(int codigoActividad) {
        this.codigoActividad = codigoActividad;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getDesempenoSeccion() {
        return desempenoSeccion;
    }

    public void setDesempenoSeccion(int desempenoSeccion) {
        this.desempenoSeccion = desempenoSeccion;
    }

    public Date getFechaBitacora() {
        return fechaBitacora;
    }

    public void setFechaBitacora(Date fechaBitacora) {
        this.fechaBitacora = fechaBitacora;
    }

    public int getResultadoAutoevaluacion() {
        return resultadoAutoevaluacion;
    }

    public void setResultadoAutoevaluacion(int resultadoAutoevaluacion) {
        this.resultadoAutoevaluacion = resultadoAutoevaluacion;
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
                    + "codigoActividad, comentario, desempenoSeccion, "
                    + "fechaBitacora, resultadoAutoevaluacion, "
                    + "portafolioEvidencias_idportafolioEvidencias, "
                    + "curso_nrcCurso) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setTime(1, bitacora.getTiempoEmpleado());
            sentencia.setInt(2, bitacora.getCodigoActividad());
            sentencia.setString(3, bitacora.getComentario());
            sentencia.setInt(4, bitacora.getDesempenoSeccion());
            sentencia.setDate(5, bitacora.getFechaBitacora());
            sentencia.setInt(6, bitacora.getResultadoAutoevaluacion());
            sentencia.setInt(7, bitacora.getIdPortafolioEvidencias());
            sentencia.setInt(8, bitacora.getNrcCurso());
            
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
