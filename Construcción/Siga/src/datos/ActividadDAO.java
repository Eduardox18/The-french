package datos;

import javafx.collections.ObservableList;
import logica.Actividad;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 */
public interface ActividadDAO {
    
    public boolean agregarActividad(Actividad actividad);
    public boolean eliminarActividad(int idActividad);
    public boolean editarActividad(int idActividad);
    public ObservableList<Actividad> consultarActividad(int idActividad);
}
