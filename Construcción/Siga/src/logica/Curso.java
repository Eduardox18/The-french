package logica;

import datos.Conexion;
import datos.CursoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import presentacion.Dialogo;

/**
 * Clase que contiene los métodos relacionados con los cursos del Sistema. La clase mplementa
 * métodos utilizados para la funcionalidad correcta del programa.
 */
public class Curso implements CursoDAO {

    /**
     * Atributos de la clase
     */
    private String nombreCurso;
    private Integer nrcCurso;

    /**
     * Constructor vacío de la clase. Permite crear objetos tipo Curso.
     */
    public Curso() {}

    /**
     * Constructor completo de la clase. Permite crear objetos tipo Curso.
     *
     * @param nombreCurso Nombre del curso.
     * @param nrcCurso NRC (clave) del curso.
     */
    public Curso(String nombreCurso, Integer nrcCurso) {
        this.nombreCurso = nombreCurso;
        this.nrcCurso = nrcCurso;
    }

    /**
     * Bloque de Getters y Setter de la clase. Su documentación no es necesaria.
     */
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

    /**
     * Método que sobreescribe el método toString para recuperar el nombre del curso.
     *
     * @return Regresa el nombre del curso.
     */
    @Override
    public String toString() {
        return nombreCurso;
    }

    /**
     *
     * Método sobreescrito que recupera un ObservableList con objetos tipo Curso, los cuales son los
     * cursos que está llevando actualmente el alumno.
     *
     * @param matriculaAlumno Matrícula del alumno del que se desean consultar los cursos.
     * @return Regresa un ObservableList con todos los cursos en los que se encuentra inscrito el
     * alumno
     */
    @Override
    public ObservableList<Curso> obtenerCursos(String matriculaAlumno) {
        ObservableList<Curso> cursosAlumno = FXCollections.observableArrayList();
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet rs = null;
        Curso curso;

        try {
            conexion = new Conexion().connection();
            String consulta = "SELECT DISTINCT curso.nrcCurso,curso.nombreCurso "
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
        return cursosAlumno;
    }

}
