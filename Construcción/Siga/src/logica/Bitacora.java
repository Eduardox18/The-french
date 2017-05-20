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

    @Override
    public boolean insertarBitacora(Bitacora bitacora) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarBitacora(int noBitacora) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editarBitacora(int noBitacora) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Bitacora> consultarBitacora(int noBitacora) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
