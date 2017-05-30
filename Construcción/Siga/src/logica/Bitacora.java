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
    public boolean registrarBitacora(Bitacora bitacora) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
