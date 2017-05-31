package presentacion.controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import logica.Actividad;
import logica.Curso;
import logica.Reservacion;
import logica.Usuario;
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
    private Button btCancelar;

    @FXML
    private ComboBox<Curso> cbIdiomas;

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
        llenarCombo();
        selectorFecha.valueProperty().addListener((ov, oldValue, newValue) -> {
            llenarTabla();
        });
        cbIdiomas.valueProperty().addListener((ov, oldValue, newValue) -> {
            llenarTabla();
        });

        //Listener para la tabla, se utiliza para saber cuando hay un item 
        //seleccionado
        tablaActividades.getSelectionModel().selectedIndexProperty().
                addListener((obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        btReservar.setDisable(false);
                    }
                });
    }

    /**
     *
     * Recupera los datos de la base de datos que coincidan con el idioma y la fecha indicados y los
     * muestra en la tabla
     */
    private void llenarTabla() {
        Actividad actividad = new Actividad();
        colActividad.setCellValueFactory(
                new PropertyValueFactory<>("nombreActividad"));
        colProfesor.setCellValueFactory(
                new PropertyValueFactory<>("asesorActividad"));
        colTipo.setCellValueFactory(
                new PropertyValueFactory<>("tipoActividad"));
        colHora.setCellValueFactory(
                new PropertyValueFactory<>("horaActividad"));
        LocalDate fecha;
        Date fechaSql;
        try {
            fecha = selectorFecha.getValue();
            fechaSql = Date.valueOf(fecha);
            tablaActividades.setItems(actividad.consultarActividades(
                    cbIdiomas.getSelectionModel().getSelectedItem().getNrcCurso(), fechaSql));
        } catch (NullPointerException e) {
            Dialogo dia = new Dialogo();
            dia.alertaCamposVacios();
        }
    }

    /**
     *
     *Obtiene los datos del combobox obtenidos del método obtenerCursos de la 
     * clase Curso
     */
    private void llenarCombo() {
        Curso curso = new Curso();
        Usuario usuario = new Usuario();
        cbIdiomas.setItems(curso.obtenerCursos(usuario.getUsuarioActual()));
        cbIdiomas.getSelectionModel().selectFirst();
    }

    /**
     * 
     * Método llamado por el botón Reservar actividd que guarda la reservación
     * en la base de datos
     */
    @FXML
    public void guardarActividad() {
        Usuario usuario = new Usuario();
        Reservacion reservacion = new Reservacion();
        Dialogo dialogo = new Dialogo();
        boolean existe = reservacion.comprobarReservaciones(usuario.
            getUsuarioActual(),
                tablaActividades.getSelectionModel().getSelectedItem().
                    obtenerIDActividad());
        if (existe == true) {
            boolean verificacion = reservacion.agregarReservacion(
                    tablaActividades.getSelectionModel().getSelectedItem().
                            obtenerIDActividad(),
                    usuario.getUsuarioActual());
            if (verificacion == true) {
                dialogo.alertaReservacionExistosa();
            }
        } else {
            dialogo.alertaExisteActividad();
        }
    }
    
    /**
     * 
     * Regresa a la ventana inicial en caso de que se desee cancelar la 
     * reservación
     * @param event 
     */
    @FXML
    private void cancelarReservacion(ActionEvent event) {
        try {
            URL principal = getClass().getResource("/presentacion/Inicial.fxml");
            AnchorPane panePrincipal = FXMLLoader.load(principal);
            
            BorderPane border = ControladorLogIn.getPrincipal();
            border.setCenter(panePrincipal);
        } catch (IOException ex) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
    }

}
