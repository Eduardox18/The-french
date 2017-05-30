package datos;

import javafx.collections.ObservableList;
import logica.Bitacora;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 * Interface con los métodos de la clase bitácora
 */
public interface BitacoraDAO {
    /**
     * 
     * Agrega una nueva bitácora a la base de datos.
     * @param bitacora Objeto de tipo Bitacora de donde se obtienen todos los 
     * datos.
     * @return true si la operación es exitoda o false en caso de que ocurra 
     * algún error.
     */
    public boolean registrarBitacora(Bitacora bitacora);
    
    /**
     * 
     * Regresa los datos de una bitácora
     * @param noBitacora El número de la bitácora que se desea consultar
     * @return true si la operación es exitosa, false en el caso contrario.
     */
    //public ObservableList<Bitacora> consultarBitacora(int noBitacora);
}
