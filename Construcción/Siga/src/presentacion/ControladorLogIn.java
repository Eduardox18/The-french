package presentacion;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Angel Eduardo Domínguez Delgado
 */
public class ControladorLogIn extends Application {

    @FXML
    Button botonIngresar;

    private static BorderPane root = new BorderPane();
    private static BorderPane panePrincipal = new BorderPane();

    public static BorderPane getPrincipal() {
        return panePrincipal;
    }

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

    @FXML
    public void abrirInicio() {
        Stage stagePrincipal = new Stage();
        try {
            URL menuBarURL = getClass().getResource("/presentacion/Principal.fxml");
            MenuBar bar = FXMLLoader.load(menuBarURL);
            URL paneInicialURL = getClass().getResource("/presentacion/Inicial.fxml");
            AnchorPane paneInicial = FXMLLoader.load(paneInicialURL);
            
            Stage stage = (Stage) botonIngresar.getScene().getWindow();
            stage.close();

            panePrincipal.setTop(bar);
            panePrincipal.setCenter(paneInicial);
            Scene sceneDos = new Scene(panePrincipal, 602, 429);
            stagePrincipal.setTitle("Inicial");
            stagePrincipal.setScene(sceneDos);
            stagePrincipal.show();
        } catch (IOException ex) {
            //Alerta de excepción
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
