package datos;

import javafx.collections.ObservableList;
import logica.Curso;

/**
 * Interface para la clase Curso del proyecto. Se declaran los métodos definidos en el diagrama
 * de clases y los utilizados en la implementación del programa. Algunos métodos fueron comentados
 * porque no se utilizarán en la parte funcional a presentar del programa. 
 */
public interface CursoDAO {
    
    /**
     * Método declarado que recupera un ObservableList de objetos tipo Curso, los cuales 
     * son los que el alumno está cursando actualmente.
     * @param matriculaAlumno: Matrícula del alumno del que se desean recuperar los cursos.
     * @return Regresa un ObservableList de objetos tipo Curso. Son los cursos a los que está
     * inscrito el alumno.
     */
    public ObservableList<Curso> obtenerCursos (String matriculaAlumno);
    
    /**
     * Lista de métodos declarados en el diagrama de clases pero que no se utilizarán en la versión
     * actual del programa.
     */
    //public boolean agregarCurso(Curso curso);
    //public boolean bajaCurso(int nrcCurso);
    //public boolean editarCurso(int nrcCurso);
    //public Curso consultarCurso(int nrcCurso);
}
