package logica;

import datos.AutoevaluacionDAO;
import datos.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import presentacion.Dialogo;

/**
 * Clase que contiene los métodos relacionados con las autoevaluaciones del Sistema. La clase
 * implementa los métodos de la interface AutoevaluacionDAO e implementa otros utilizados para la
 * funcionalidad correcta del programa.
 */
public class Autoevaluacion implements AutoevaluacionDAO {

    /**
     * Atributos de la clase.
     */
    private String nombreElaborador;
    private String nombreMaterial;
    private String habilidadesRequeridas;
    private Date fechaElaboracion;
    private String contenidosEvaluados;
    private String codificacion;
    private String objetivo;
    private String sala;
    private int nivel;
    private int resultadoAutoevaluacion;

    /**
     * Constructor vacío de la clase. Permite crear objetos tipo Autoevaluacion.
     */
    public Autoevaluacion() {}

    /**
     * Constructor completo de la clase. Permite crear objetos tipo Autoevaluacion.
     *
     * @param nombreElaborador Nombre del elaborador de la autoevaluación.
     * @param nombreMaterial Material utilizado en la autoevaluación.
     * @param habilidadesRequeridas Habilidades requeridas de la autoevaluación.
     * @param fechaElaboracion Fecha en que se elaboró la autoevaluación.
     * @param contenidosEvaluados Contenidos que se evaluaron en la autoevaluación.
     * @param codificacion Codificación de la autoevaluación.
     * @param objetivo Objetivo de la autoevaluación.
     * @param sala Sala en que se realizó la autoevaluación.
     * @param nivel Nivel de la autoevaluación.
     * @param resultadoAutoevaluacion Resultado obtenido en la autoevaluación.
     */
    public Autoevaluacion(String nombreElaborador, String nombreMaterial,
        String habilidadesRequeridas, Date fechaElaboracion, String contenidosEvaluados,
        String codificacion, String objetivo, String sala, int nivel, int resultadoAutoevaluacion) {
        this.nombreElaborador = nombreElaborador;
        this.nombreMaterial = nombreMaterial;
        this.habilidadesRequeridas = habilidadesRequeridas;
        this.fechaElaboracion = fechaElaboracion;
        this.contenidosEvaluados = contenidosEvaluados;
        this.codificacion = codificacion;
        this.objetivo = objetivo;
        this.sala = sala;
        this.nivel = nivel;
        this.resultadoAutoevaluacion = resultadoAutoevaluacion;
    }

    /**
     * Bloque de Getters y Setters de la clase. Su documentación no es necesaria.
     */
    public String getNombreElaborador() {
        return nombreElaborador;
    }

    public void setNombreElaborador(String nombreElaborador) {
        this.nombreElaborador = nombreElaborador;
    }

    public String getNombreMaterial() {
        return nombreMaterial;
    }

    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }

    public String getHabilidadesRequeridas() {
        return habilidadesRequeridas;
    }

    public void setHabilidadesRequeridas(String habilidadesRequeridas) {
        this.habilidadesRequeridas = habilidadesRequeridas;
    }

    public Date getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(Date fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public String getContenidosEvaluados() {
        return contenidosEvaluados;
    }

    public void setContenidosEvaluados(String contenidosEvaluados) {
        this.contenidosEvaluados = contenidosEvaluados;
    }

    public String getCodificacion() {
        return codificacion;
    }

    public void setCodificacion(String codificacion) {
        this.codificacion = codificacion;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getResultadoAutoevaluacion() {
        return resultadoAutoevaluacion;
    }

    public void setResultadoAutoevaluacion(int resultadoAutoevaluacion) {
        this.resultadoAutoevaluacion = resultadoAutoevaluacion;
    }

    /**
     * Método encargado de obtener el número de autoevaluación correspondiente al curso y alumno que
     * lo solicita. El alumno es el logueado en el Sistema.
     *
     * @param nrcCurso NRC del curso en el que se encuentra el alumno.
     * @return Regresa el número de autoevaluación que corresponde al curso y alumno solicitados.
     */
    public int obtenerNoAutoevaluacion(int nrcCurso) {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        Usuario usuario = new Usuario();
        String matriculaAlumno = usuario.getUsuarioActual();

        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT noAutoevaluacion FROM autoevaluacion, "
                + "portafolioEvidencias, inscripcion, grupoAlumno WHERE "
                + "autoevaluacion.portafolioEvidencias_idportafolioEvidencias "
                + "= portafolioEvidencias.idportafolioEvidencias AND "
                + "portafolioEvidencias.inscripcion_idinscripcion = "
                + "inscripcion.idinscripcion AND "
                + "inscripcion.alumno_matriculaAlumno "
                + "= ? AND inscripcion.grupoAlumno_idGrupoAlumno = "
                + "grupoAlumno.idGrupoAlumno AND "
                + "grupoAlumno.curso_nrcCurso = ?";

            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, matriculaAlumno);
            sentencia.setInt(2, nrcCurso);
            rs = sentencia.executeQuery();

            if (rs.next()) {
                return rs.getInt("noAutoevaluacion");
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

    /**
     * Método encaragdo de obtener el resultado de la autoevaluación del alumno según el curso que
     * se solicita. El alumno es el logueado en el Sistema.
     *
     * @param nrcCurso NRC del curso en el que se encuentra el alumno.
     * @return Regresa el resultado de la autoevaluación que obtuvo el alumno en el curso
     * especificado.
     */
    public int obtenerResultadoAutoevaluacion(int nrcCurso) {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        Usuario usuario = new Usuario();
        String matriculaAlumno = usuario.getUsuarioActual();

        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT resultadoAutoevaluacion FROM "
                + "autoevaluacion, portafolioEvidencias, inscripcion, "
                + "grupoAlumno WHERE "
                + "autoevaluacion.portafolioEvidencias_idportafolioEvidencias "
                + "= portafolioEvidencias.idportafolioEvidencias AND "
                + "portafolioEvidencias.inscripcion_idinscripcion = "
                + "inscripcion.idinscripcion AND "
                + "inscripcion.alumno_matriculaAlumno "
                + "= ? AND inscripcion.grupoAlumno_idGrupoAlumno = "
                + "grupoAlumno.idGrupoAlumno AND "
                + "grupoAlumno.curso_nrcCurso = ?";

            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, matriculaAlumno);
            sentencia.setInt(2, nrcCurso);
            rs = sentencia.executeQuery();

            if (rs.next()) {
                return rs.getInt("resultadoAutoevaluacion");
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
