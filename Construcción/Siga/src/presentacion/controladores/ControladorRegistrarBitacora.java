/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import logica.Actividad;
import logica.ActividadRealizada;
import logica.Bitacora;
import logica.Curso;
import logica.Usuario;
import presentacion.Dialogo;

/**
 * FXML Controller class
 *
 * @author andres
 */
public class ControladorRegistrarBitacora implements Initializable {

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarCursos();
        llenarTablaAsistidas();
        llenarTablaRealizadas();
        comboCursos.valueProperty().addListener((ov, oldValue, newValue) -> {
            llenarTablaAsistidas();
            llenarTablaRealizadas();
        });
        selectorTiempo.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0, 10));
    }
    
    @FXML
    public void guardarBitacora() {
        Bitacora bitacora = new Bitacora();
        LocalDate fecha;
        Date fechaSql;
        
        try {
            fecha = selectorFecha.getValue();
            fechaSql = Date.valueOf(fecha);
            bitacora.setFechaBitacora(fechaSql);
        } catch (NullPointerException e) {
            Dialogo dia = new Dialogo();
            dia.alertaCamposVacios();
        }
        
        bitacora.setComentario(areaComentario.getText());
        bitacora.setNrcCurso(comboCursos.getSelectionModel().getSelectedItem().getNrcCurso());
        bitacora.setTiempoEmpleado(selectorTiempo.getValue());
        
        bitacora.registrarBitacora(bitacora);
    }
    
    private void llenarCursos () {
        Curso curso = new Curso();
        Usuario usuario = new Usuario();
        comboCursos.setItems(curso.obtenerCursos(usuario.getUsuarioActual()));
        comboCursos.getSelectionModel().selectFirst();
    }
    
    private void llenarTablaAsistidas () {
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
    
    private void llenarTablaRealizadas () {
        ActividadRealizada act = new ActividadRealizada();
        Usuario usuario = new Usuario();
        colNombreRealizada.setCellValueFactory(
            new PropertyValueFactory<>("nombreARealizada"));
        colFechaRealizada.setCellValueFactory(
            new PropertyValueFactory<>("fechaARealizada"));
        colTiempoRealizada.setCellValueFactory(
            new PropertyValueFactory<>("tiempoEmpleado"));
        tablaRealizadas.setItems(act.obtenerActividades(
            comboCursos.getSelectionModel().getSelectedItem().getNrcCurso(), 
            usuario.getUsuarioActual()));
    }
    
    /**
     * 
     * Regresa a la ventana inicial en caso de que se desee cancelar el registro
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
