package datos;

import javafx.collections.ObservableList;
import logica.ActividadRealizada;

/**
 * Interface para la clase ActividadRealizada del proyecto. Declara los métodos definidos en el
 * diagrama de clases y los utilizados en la implementación del programa.
 */
public interface ActividadRealizadaDAO {

    /**
     * Método declarado que recupera un ObservableList de objetos ActividadRealizada a partir del
     * NRC de un curso y la matrícula del alumno del que se desean obtener las actividades.
     *
     * @param nrcCurso NRC del curso en el que está el alumno.
     * @param matriculaAlumno Matrícula registrada del alumno interesado.
     * @return ObservableList de objetos ActividadRealizada, donde se encuentran las coincidencias
     * de la búsqueda.
     */
    public ObservableList<ActividadRealizada> obtenerActividadRealizada(int nrcCurso,
        String matriculaAlumno);

}
