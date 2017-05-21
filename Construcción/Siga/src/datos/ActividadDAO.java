package datos;

import java.sql.Date;
import javafx.collections.ObservableList;
import logica.Actividad;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 */
public interface ActividadDAO {

    public ObservableList<Actividad> consultarActividades(String idiomaActividad, 
            Date diaActividad);
}
