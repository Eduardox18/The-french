package presentacion.controladores;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import presentacion.Dialogo;

/**
 * Clase controlador de la barra de acciones del programa. Carga las escenas según la opción
 * seleccionada.
 */
public class ControladorPrincipal {

    @FXML
    private MenuItem barReservarActividad;

    @FXML
    private MenuItem barRegistrarBitacora;

    /**
     *
     * Método encargado de cargar la escena ReservarActividad junto con todos los elementos de su
     * interfaz FXML
     *
     * @param event Evento del botón encargado.
     */
    @FXML
    void abrirReservarActividad(ActionEvent event) {
        try {
            URL reservarActividad = getClass().getResource("/presentacion/ReservarActividad.fxml");
            AnchorPane paneReservarActividad = FXMLLoader.load(reservarActividad);

            BorderPane border = ControladorLogIn.getPrincipal();
            border.setCenter(paneReservarActividad);
        } catch (IOException e) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
    }

    /**
     *
     * Método encargador de cargar la escena RegistrarBitacora junto con todos los elementos de su
     * interfaz FXML.
     *
     * @param event Evento del botón encargado.
     */
    @FXML
    void abrirRegistrarBitacora(ActionEvent event) {
        try {
            URL registrarBitacora = getClass().getResource("/presentacion/RegistrarBitacora.fxml");
            AnchorPane paneRegistrarBitacora = FXMLLoader.load(registrarBitacora);

            BorderPane border = ControladorLogIn.getPrincipal();
            border.setCenter(paneRegistrarBitacora);
        } catch (IOException e) {
            Dialogo dialogo = new Dialogo();
            dialogo.alertaError();
        }
    }

}
