package logica;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import presentacion.Dialogo;

/**
 *
 * @author lalo
 */
public class PortafolioEvidencias {
    
    private String reglamento;

    public String getReglamento() {
        return reglamento;
    }

    public void setReglamento(String reglamento) {
        this.reglamento = reglamento;
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
