package datos;

import java.sql.Date;
import javafx.collections.ObservableList;
import logica.Actividad;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 * @version 1.0
 * Interface de conexión con la clase actividad
 */
public interface ActividadDAO {

    /**
     * 
     * Interface para recuperar las actividades que coincidan con los parámetros
     * @param nrcCurso NRC del curso que se desea buscar
     * @param diaActividad Fecha en la que se desean buscar las actividades
     * @return ObservableList de objetos Actividad, donde se encuentran todas las
     * coincidencias de la búsqueda
     */
    public ObservableList<Actividad> consultarActividades(int nrcCurso, Date diaActividad);
}
