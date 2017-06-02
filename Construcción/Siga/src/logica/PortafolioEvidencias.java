package logica;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import presentacion.Dialogo;

/**
 * Clase que contiene los métodos relacionados con los portafolios de evidencias del Sistema. La
 * clase implementa métodos utilizados para la funcionalidad correcta del programa.
 */
public class PortafolioEvidencias {

    /**
     * Atributo de la clase
     */
    private String reglamento;

    /**
     * Constructor vacío de la clase. Permite crear objetos tipo PortafolioEvidencias.
     */
    public PortafolioEvidencias() {}

    /**
     * Constructor completo de la clase. Permite crear objetos tipo PortafolioEvidencias.
     *
     * @param reglamento
     */
    public PortafolioEvidencias(String reglamento) {
        this.reglamento = reglamento;
    }

    /**
     * Bloque de Getters y Setters. Su documentación no es necesaria.
     *
     * @return
     */
    public String getReglamento() {
        return reglamento;
    }

    public void setReglamento(String reglamento) {
        this.reglamento = reglamento;
    }

    /**
     * Método encargador de recuperar el ID del protafolio de evidencias de un alumno según su
     * curso.
     *
     * @param nrcCurso: NRC del curso que se quiere recuperar el portafolio.
     * @return Regresa el ID del portafolio de evidencias del alumno según el curso buscado.
     */
    public int recuperarIDPortafolio(int nrcCurso) {
        Usuario usuario = new Usuario();
        String matriculaAlumno = usuario.getUsuarioActual();
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;

        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT DISTINCT idportafolioEvidencias FROM "
                + "portafolioEvidencias, inscripcion, alumno, "
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

            if (rs.next()) {
                return rs.getInt("idportafolioEvidencias");
            }
        } catch (SQLException ex) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        } finally {
            Dialogo dialogo = new Dialogo();
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    dialogo.alertaCerrarConexion();
                }
            }
            if (sentencia != null) {
                try {
                    sentencia.close();
                } catch (SQLException ex) {
                    dialogo.alertaCerrarConexion();
                }
            }
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException ex) {
                    dialogo.alertaCerrarConexion();
                }
            }
        }
        return 0;
    }
}
