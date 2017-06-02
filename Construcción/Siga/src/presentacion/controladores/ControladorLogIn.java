/**
 * Nombre del programa: SIGA (Sistema de Idiomas Gratificante para el Alumno)
 * Versión: 0.1
 * Autores:
 *          Domínguez Delgado Ángel Eduardo
 *          Domínguez González José Andrés
 *          Hernández González Esmeralda Yamileth
 * Fecha de inicio del desarrollo: 16 de Mayo de 2017
 * Descripción: Programa encargado de todas las actividades que se llevan a cabo en el CADI.
 * Realizado para optimizar los procesos que requiere el alumno a lo largo de su curso de idiomas.
 */
package presentacion.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logica.Usuario;
import presentacion.Dialogo;

/**
 *
 * Clase controlador principal del programa. Encargada de establecer y cargar la GUI desde los
 * archivos FXML.
 */
public class ControladorLogIn extends Application {

    private static BorderPane root = new BorderPane();
    private static BorderPane panePrincipal = new BorderPane();

    @FXML
    private Button botonIngresar;
    @FXML
    private TextField tfUsuario;
    @FXML
    private TextField tfPassword;

    /**
     * Método que permite obtener el pane principal de la ventana.
     *
     * @return BorderPane donde se cargarán las escenas del programa.
     */
    public static BorderPane getPrincipal() {
        return panePrincipal;
    }

    /**
     * Método sobreescrito que permite cargar la escena del login. Es la primera escena que se carga
     * al iniciar el programa.
     *
     * @param primaryStage Primer Stage del programa.
     */
    @Override
    public void start(Stage primaryStage) {

        AnchorPane paneLogin = null;

        URL login = getClass().getResource("/presentacion/LogIn.fxml");
        try {
            paneLogin = FXMLLoader.load(login);
        } catch (IOException ex) {
            Logger.getLogger(ControladorLogIn.class.getName()).log(Level.SEVERE, null, ex);
        }

        root.setCenter(paneLogin);

        Scene scene = new Scene(root, 249, 358);
        primaryStage.setTitle("LogIn");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     *
     * Método asignado al botón "Ingresar". Si el usuario y contraseña se encuentran en la base de
     * datos, inicia la nueva ventana cargando la interfaz FXML Inicial, que es la primera
     * visualizable del programa, además de la barra principal de acciones.
     */
    @FXML
    public void abrirInicio() {
        Usuario usuario = new Usuario();
        Dialogo dialogo = new Dialogo();

        if (tfUsuario.getText().trim().isEmpty() || tfPassword.getText().trim().isEmpty()) {
            dialogo.alertaCamposVacios();
            tfPassword.setText("");
        } else {
            if (usuario.consultaUsuario(tfUsuario.getText(), tfPassword.getText())) {
                try {
                    usuario.obtenerMatriculaAlumno(tfUsuario.getText());
                    Stage stagePrincipal = new Stage();
                    URL menuBarURL = getClass().getResource("/presentacion/Principal.fxml");
                    MenuBar bar = FXMLLoader.load(menuBarURL);
                    URL paneInicialURL = getClass().getResource("/presentacion/Inicial.fxml");
                    AnchorPane paneInicial = FXMLLoader.load(paneInicialURL);

                    Stage stage = (Stage) botonIngresar.getScene().getWindow();
                    stage.close();

                    panePrincipal.setTop(bar);
                    panePrincipal.setCenter(paneInicial);
                    Scene sceneDos = new Scene(panePrincipal, 602, 429);
                    stagePrincipal.setTitle("SIGA. Usuario: " + tfUsuario.getText());
                    stagePrincipal.setScene(sceneDos);
                    stagePrincipal.show();
                } catch (IOException ex) {
                    dialogo.alertaError();
                }
            } else {
                dialogo.alertaInformacionErronea();
                tfPassword.setText("");
            }
        }
    }

    /**
     * Método principal que inicia todo el programa.
     *
     * @param args String necesaria.
     */
    public static void main(String[] args) {
        launch(args);
    }

}
