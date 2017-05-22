/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentacion.Dialogo;

/**
 *
 * @author José Andrés Domínguez González
 */
public class Curso {
    private String nombreCurso;
    private Integer nrcCurso;
    
    public Curso () {}
    
    public Curso(String nombreCurso, Integer nrcCurso) {
        this.nombreCurso = nombreCurso;
        this.nrcCurso = nrcCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public Integer getNrcCurso() {
        return nrcCurso;
    }

    public void setNrcCurso(Integer nrcCurso) {
        this.nrcCurso = nrcCurso;
    }
    
    @Override
    public String toString () {
        return nombreCurso;
    }
    
    public ObservableList<Curso> obtenerCursos (String matriculaAlumno) {
        ObservableList<Curso> cursosAlumno = FXCollections.observableArrayList();
        Connection conexion;
        PreparedStatement sentencia;
        ResultSet rs;
        Curso curso;
        
        try {
            conexion = new Conexion().connection();
            String consulta ="SELECT DISTINCT curso.nrcCurso,curso.nombreCurso "
                + "FROM inscripcion, curso, grupoAlumno, alumno WHERE "
                + "inscripcion.alumno_matriculaAlumno = ? AND "
                + "inscripcion.grupoAlumno_idGrupoAlumno = "
                + "grupoAlumno.idGrupoAlumno AND grupoAlumno.curso_nrcCurso = "
                + "curso.nrcCurso;";
            sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, matriculaAlumno);
            rs = sentencia.executeQuery();
            
            while (rs.next()) {
                curso = new Curso();
                curso.setNombreCurso(rs.getString("nombreCurso"));
                curso.setNrcCurso(rs.getInt("nrcCurso"));
                cursosAlumno.add(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
        return cursosAlumno;
    }
    
}
