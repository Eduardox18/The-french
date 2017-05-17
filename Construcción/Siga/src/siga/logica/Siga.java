package siga.logica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Domínguez Delgado Angel Eduardo
 * @author Domínguez González José Andrés
 * @author Hernández González Esmeralda Yamileth
 */
public class Siga extends Application {
  
  @Override
  public void start(Stage stage) throws Exception {
    Parent pane = FXMLLoader.load(getClass().getResource("/siga/presentacion/ReservarActividad.fxml"));
    Scene scene = new Scene(pane);
    stage.setTitle("Reservar Actividad");
    stage.setScene(scene);
    stage.show();   
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
  
}
