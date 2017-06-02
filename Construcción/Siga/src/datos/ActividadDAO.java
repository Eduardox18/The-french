package datos;

import java.sql.Date;
import javafx.collections.ObservableList;
import logica.Actividad;

/**
 * Interface para la clase Actividad del proyecto. Se declaran los métodos definidos en el diagrama
 * de clases y los utilizados en la implementación del programa. Algunos métodos fueron comentados
 * porque no se utilizarán en la parte funcional a presentar del programa.
 */
public interface ActividadDAO {

    /**
     *
     * Método declarado que recupera un ObservableList de objetos Actividad a partir del NRC de un
     * curso y el día interesado para consultar actividades.
     *
     * @param nrcCurso: NRC del curso que se desea buscar
     * @param diaActividad: Fecha en la que se desean buscar las actividades
     * @return ObservableList de objetos Actividad, donde se encuentran todas las coincidencias de
     * la búsqueda
     */
    public ObservableList<Actividad> consultarActividades(int nrcCurso, Date diaActividad);

    /**
     * Método declarado que recupera una lista de objetos Actividad a las que el alumno asistió en
     * el transcurso del curso. Se recuperan a partir del NRC del curso y la matrícula del alumno.
     *
     * @param nrcCurso: NRC del curso en el que está el alumno.
     * @param matriculaAlumno: Matrícula registrada del alumno del que se interesan recuperar las
     * actividades a las que asistió.
     * @return Lista de objetos Actividad, donde se encuentran las coincidencias de la búsqueda.
     */
    public ObservableList<Actividad> consultarActividadesAsistidas(int nrcCurso,
        String matriculaAlumno);

    /**
     * Lista de métodos declarados en el diagrama de clases pero que no se utilizarán en la versión
     * actual del programa.
     *
     */
    //public boolean agregarActividad(Actividad actividad);
    //public boolean editarActividad(int noActividad);
    //public boolean eliminarActividad(int noActividad);
}
