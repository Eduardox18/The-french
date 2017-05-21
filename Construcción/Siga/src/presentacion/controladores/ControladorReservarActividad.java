package presentacion.controladores;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logica.Actividad;
import logica.Reservacion;
import presentacion.Dialogo;

/**
 * FXML Controller class
 *
 * @author Angel Eduardo Domínguez Delgado
 */
public class ControladorReservarActividad implements Initializable {

    @FXML
    private Button btReservar;
    
    @FXML
    private ComboBox<String> cbIdiomas;
    
    @FXML
    private TableView<Actividad> tablaActividades;
    
    @FXML
    private TableColumn<Actividad, String> colActividad;
    
    @FXML
    private TableColumn<Actividad, String> colProfesor;
    
    @FXML
    private TableColumn<Actividad, String> colTipo;
    
    @FXML
    private TableColumn<Actividad, Time> colHora;
    
    @FXML
    private DatePicker selectorFecha;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbIdiomas.getItems().setAll("Inglés", "Francés");
        cbIdiomas.getSelectionModel().selectFirst();
        selectorFecha.valueProperty().addListener((ov, oldValue, newValue) -> {
            llenarTabla();
        });
        cbIdiomas.valueProperty().addListener((ov, oldValue, newValue) -> {
            llenarTabla();
        });
        
        //Listener para la tabla, se utiliza para saber cuando hay un item seleccionado
        
        tablaActividades.getSelectionModel().selectedIndexProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btReservar.setDisable(false);
            }
        });
    }    
   
    /**
     * 
     * Recupera los datos de la base de datos que coincidan con el idioma y la
     * fecha indicados y los muestra en la tabla
     */
    private void llenarTabla () {
        Actividad actividad = new Actividad();
        colActividad.setCellValueFactory(new PropertyValueFactory<>("nombreActividad"));
        colProfesor.setCellValueFactory(new PropertyValueFactory<>("profesorActividad"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoActividad"));
        colHora.setCellValueFactory(new PropertyValueFactory<>("horaActividad"));
        LocalDate fecha;
        Date fechaSql;
        try {
            fecha = selectorFecha.getValue();
            fechaSql = Date.valueOf(fecha);
            tablaActividades.setItems(actividad.consultarActividades(
            cbIdiomas.getSelectionModel().getSelectedItem(), fechaSql));
        } catch (NullPointerException e) {
            Dialogo dia = new Dialogo();
            dia.alertaCamposVacios();
        }
    }
    
    @FXML
    public void guardarActividad() {
        Reservacion reservacion = new Reservacion();
        reservacion.agregarReservacion(fechaReservacion, 
            tablaActividades.getSelectionModel().getSelectedItem().getDiaActividad(), 
            0);
    }
    
}
