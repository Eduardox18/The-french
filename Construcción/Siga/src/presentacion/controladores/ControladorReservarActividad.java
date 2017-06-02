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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import logica.Actividad;
import logica.Bitacora;
import logica.Curso;
import logica.Reservacion;
import logica.Usuario;
import presentacion.Dialogo;

/**
 *
 * Clase controlador de la GUI ReservarActividad. Que almacena los métodos y componentes necesarios
 * para el archivo FXML y la funcionalidad del Sistema.
 *
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
     * Clase que inicializa los componentes de la escena.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarCombo();
        Bitacora bit = new Bitacora();
        selectorFecha.valueProperty().addListener((ov, oldValue, newValue) -> {
            llenarTabla();
        });
        cbIdiomas.valueProperty().addListener((ov, oldValue, newValue) -> {
            llenarTabla();
        });

        //Listener para la tabla, se utiliza para saber cuando hay un item seleccionado
        tablaActividades.getSelectionModel().selectedIndexProperty().
            addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    btReservar.setDisable(false);
                }
            });
    }

    /**
     *
     * Método que manda a llamar a la función que recupera los datos de la base de datos que
     * coincidan con el idioma y la fecha indicados. Para posteriormente mostrarlos en la tabla
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
     * Método que llena el comboBox de cursos, el cual recupera la información del método almacenado
     * en la clase Curso.
     */
    private void llenarCombo() {
        Curso curso = new Curso();
        Usuario usuario = new Usuario();
        cbIdiomas.setItems(curso.obtenerCursos(usuario.getUsuarioActual()));
        cbIdiomas.getSelectionModel().selectFirst();
    }

    /**
     *
     * Método llamado por el botón "Reservar" que manda a llamar al método que guarda la reservación
     * en la base de datos del Sistema.
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

        if (selectorFecha.getValue().isBefore(LocalDate.now())) {
            dialogo.alertarFechaPasada();
        } else {
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

    }

    /**
     * Método que comprueba si la fecha proporcionada es anterior a la actual.
     *
     * @param fechaIngresada Fecha proporcionada para comparar con la actual.
     * @return Regresa verdadero(true) si la fecha proporcionada es anterior a la actual o regresa
     * falso(false) en caso contrario.
     */
    private boolean comprobarFecha(LocalDate fechaIngresada) {
        return fechaIngresada.isBefore(LocalDate.now());
    }

    /**
     *
     * Método asignado al botón "Cancelar" que regresa a la escena Inicial en caso de que el usuario
     * desee cancelar la reservación de la actividad.
     *
     * @param event Evento del botón asignado.
     */
    @FXML
    private void cancelarReservacion(ActionEvent event) {
        Dialogo dialogo = new Dialogo();
        try {
            if(dialogo.confirmacionCancelar() == ButtonType.OK) {
                URL principal = getClass().getResource("/presentacion/Inicial.fxml");
            AnchorPane panePrincipal = FXMLLoader.load(principal);

            BorderPane border = ControladorLogIn.getPrincipal();
            border.setCenter(panePrincipal);
            }
        } catch (IOException ex) {
            dialogo.alertaError();
        }
    }
}
