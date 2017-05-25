package logica;

import datos.BitacoraDAO;
import java.sql.Date;
import java.sql.Time; 
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 */
public class Bitacora implements BitacoraDAO{
    
    private SimpleIntegerProperty noBitacora;
    private Time tiempoEmpleado;
    private SimpleIntegerProperty codigoActividad;
    private SimpleStringProperty comentario;
    private SimpleIntegerProperty desempenoSeccion;
    private Date fechaBitacora;
    private SimpleStringProperty materialesSugeridos;
    private SimpleStringProperty observacionesAsesor;
    private SimpleIntegerProperty resultadoAutoevaluacion;
    private SimpleIntegerProperty idPortafolioEvidencias;

    /**
     * 
     * Agrega una nueva bitácora a la base de datos.
     * @param bitacora Objeto de tipo Bitacora de donde se obtienen todos los 
     * datos.
     * @return true si la operación es exitoda o false en caso de que ocurra 
     * algún error.
     */
    @Override
    public boolean insertarBitacora(Bitacora bitacora) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * Regresa los datos de una bitácora
     * @param noBitacora El número de la bitácora que se desea consultar
     * @return true si la operación es exitosa, false en el caso contrario.
     */
    @Override
    public ObservableList<Bitacora> consultarBitacora(int noBitacora) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
