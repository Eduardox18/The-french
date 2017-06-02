/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author lalo
 */
public class Autoevaluacion implements AutoevaluacionDAO {

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

    public int obtenerNoAutoevaluacion(int nrcCurso) {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
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
            
            if(rs.next()) {
                return rs.getInt("noAutoevaluacion");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        } 
        return 0;
    }
    
    public int obtenerResultadoAutoevaluacion(int nrcCurso) {
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
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
            
            if(rs.next()) {
                return rs.getInt("resultadoAutoevaluacion");
            }
        } catch (SQLException ex) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        } 
        return 0;
    }
}
