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
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import logica.*;
import presentacion.Dialogo;

/**
 * 
 * Clase controlador de la GUI RegistrarBitacora. Que almacena los métodos y componentes necesarios
 * para el archivo FXML y la funcionalidad del Sistema.
 *
 */
public class ControladorRegistrarBitacora implements Initializable {

    @FXML
    Label noBitacora;

    @FXML
    Label resultadoAutoevaluacion;

    @FXML
    Button botonRegistrar;

    @FXML
    Button botonCancelar;

    @FXML
    ComboBox<Curso> comboCursos;

    @FXML
    TextArea areaComentario;

    @FXML
    Spinner<Integer> selectorTiempo;

    @FXML
    DatePicker selectorFecha;

    @FXML
    TableView<Actividad> tablaAsistidas;

    @FXML
    TableColumn<String, Actividad> colNombreAsistido;

    @FXML
    TableColumn<Date, Actividad> colFechaAsistido;

    @FXML
    TableView<ActividadRealizada> tablaRealizadas;

    @FXML
    TableColumn<String, ActividadRealizada> colNombreRealizada;

    @FXML
    TableColumn<Date, ActividadRealizada> colFechaRealizada;

    @FXML
    TableColumn<Time, ActividadRealizada> colTiempoRealizada;

    /**
     * Método sobreescrito que inicializa la escena de RegistrarBitacora y todos los componentes que la componen. 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Bitacora bitacora = new Bitacora();
        llenarCursos();
        llenarTablaAsistidas();
        llenarTablaRealizadas();
        //Listener que actualiza la información de la ventana cada vez que se selecciona un curso diferente.
        comboCursos.valueProperty().addListener((ov, oldValue, newValue) -> {
            llenarTablaAsistidas();
            llenarTablaRealizadas();
            actualizarNoBitacora();
            actualizarCalAutoevaluacion();
            habilitarBotonRegistrar();
            limpiarCampos();
        });
        selectorTiempo.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0, 10));
        actualizarNoBitacora();
        actualizarCalAutoevaluacion();
        habilitarBotonRegistrar();
    }

    /**
     * Método asignado al botón "Registrar Bitácora" que manda a llamar a los métodos necesarios para registrar
     * una nueva bitácora en la base de datos.
     */
    @FXML
    public void guardarBitacora() {
        Bitacora bitacora = new Bitacora();
        Autoevaluacion autoevaluacion = new Autoevaluacion();
        Dialogo dialogo = new Dialogo();
        PortafolioEvidencias pEvidencias = new PortafolioEvidencias();
        LocalDate fecha;
        Date fechaSql;

        try {
            fecha = selectorFecha.getValue();
            fechaSql = Date.valueOf(fecha);
            bitacora.setFechaBitacora(fechaSql);

            //Comprobación de campos vacíos.
            if (selectorTiempo.getValue() == 0) {
                throw new NullPointerException();
            }
            if (areaComentario.getText().trim().isEmpty()) {
                throw new NullPointerException();
            }

            //Realización del objeto tipo Bitacora.
            bitacora.setIdAutoevaluacion(autoevaluacion.obtenerNoAutoevaluacion(
                comboCursos.getSelectionModel().getSelectedItem().getNrcCurso()));
            bitacora.setIdPortafolioEvidencias(pEvidencias.recuperarIDPortafolio(
                comboCursos.getSelectionModel().getSelectedItem().getNrcCurso()));
            bitacora.setComentario(areaComentario.getText());
            bitacora.setTiempoEmpleado(selectorTiempo.getValue() * 100);

            if (bitacora.registrarBitacora(bitacora) && bitacora.
                guardarActividadesAsistidas(
                    comboCursos.getSelectionModel().getSelectedItem().
                        getNrcCurso()) && bitacora.guardarActividadesEscritas(
                    comboCursos.getSelectionModel().getSelectedItem().
                        getNrcCurso())) {
                dialogo.alertaBitacoraRegistrada();
                actualizarNoBitacora();
                actualizarCalAutoevaluacion();
                habilitarBotonRegistrar();
                limpiarCampos();
            } else {
                dialogo.alertarBitacoraNoRegistrada();
            }
        } catch (NullPointerException e) {
            Dialogo dia = new Dialogo();
            dia.alertaCamposVacios();
        }

    }

    /**
     * Método encargado de llenar el comboBox de los curso a los que pertenece el usuario, 
     * utilizando los métodos asignados en las clases de lacapa lógica del programa
     */
    private void llenarCursos() {
        Curso curso = new Curso();
        Usuario usuario = new Usuario();
        comboCursos.setItems(curso.obtenerCursos(usuario.getUsuarioActual()));
        comboCursos.getSelectionModel().selectFirst();
    }

    /**
     * Método encargado de llenar la tabla de las actividades a las que asistió el usuario, 
     * utilizando los métodos asignados en las clases de lacapa lógica del programa
     */
    private void llenarTablaAsistidas() {
        Actividad actividadAsistida = new Actividad();
        Usuario usuario = new Usuario();
        colNombreAsistido.setCellValueFactory(
            new PropertyValueFactory<>("nombreActividad"));
        colFechaAsistido.setCellValueFactory(
            new PropertyValueFactory<>("diaActividad"));
        tablaAsistidas.setItems(actividadAsistida.consultarActividadesAsistidas(
            comboCursos.getSelectionModel().getSelectedItem().getNrcCurso(),
            usuario.getUsuarioActual()));
    }

    /**
     * Método encargado de llenar la tabla de las actividades escritas que realizó el usuario, 
     * utilizando los métodos asignados en las clases de lacapa lógica del programa
     * 
     */
    private void llenarTablaRealizadas() {
        ActividadRealizada act = new ActividadRealizada();
        Usuario usuario = new Usuario();
        colNombreRealizada.setCellValueFactory(
            new PropertyValueFactory<>("nombreARealizada"));
        colFechaRealizada.setCellValueFactory(
            new PropertyValueFactory<>("fechaARealizada"));
        colTiempoRealizada.setCellValueFactory(
            new PropertyValueFactory<>("tiempoEmpleado"));
        tablaRealizadas.setItems(act.obtenerActividadRealizada(
            comboCursos.getSelectionModel().getSelectedItem().getNrcCurso(),
            usuario.getUsuarioActual()));
    }

    /**
     * Método encargado de mandar a llamar a la función que recupera el número de bitácora y 
     * actualizarlo en la etiqueta que lo almacena en la escena.
     */
    private void actualizarNoBitacora() {
        Bitacora bitacora = new Bitacora();
        noBitacora.setText(Integer.toString(bitacora.recuperarNoActualBitacora()));
    }

    /**
     * Método encargado de mandar a llamar al método que recupera la calificación de la
     * autoevaluación del alumno y mostrarla en la etiquete correspondiente de la escena.
     */
    private void actualizarCalAutoevaluacion() {
        Autoevaluacion autoevaluacion = new Autoevaluacion();
        int resultado = autoevaluacion.obtenerResultadoAutoevaluacion(
            comboCursos.getSelectionModel().getSelectedItem().getNrcCurso());
        resultadoAutoevaluacion.setText(Integer.toString(resultado));
    }

    /**
     * Método encargador de habilitar o deshabilitar el botón "Registrar Bitácora", según el método que confirma si el alumno ya ha registrado anteriormente
     * su bitácora del curso.
     */
    private void habilitarBotonRegistrar() {
        Bitacora bitacora = new Bitacora();
        if (bitacora.comprobarBitacoraExistente(comboCursos.getSelectionModel().getSelectedItem().getNrcCurso())) {
            botonRegistrar.setDisable(true);
        } else {
            botonRegistrar.setDisable(false);
        }
    }

    /**
     * Método encargado de reestablecer los campos después de agregar una bitácora o cambiar de idioma en el comboBox.
     */
    private void limpiarCampos() {
        areaComentario.setText("");
        selectorTiempo.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0, 10));
        selectorFecha.getEditor().clear();

    }
    
    /**
     *
     * Método asignado al botón "Cancelar" que permite regresar al usuario a la ventana Inicial en caso de que se desee cancelar el registro
     *
     * @param event
     */
    @FXML
    private void cancelarRegistro(ActionEvent event) {
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
