/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Personal
 */
public class ControladorPrincipal {
  
    @FXML // fx:id="displayOne"
  private MenuItem displayOne; // Value injected by FXMLLoader

  @FXML // fx:id="displayTwo"
  private MenuItem displayTwo; // Value injected by FXMLLoader

  /**
   * Event handler for MenuItem one
   */
  @FXML
  void switchToOne(ActionEvent event) {
    
    try {
      
      URL paneOneUrl = getClass().getResource("/presentacion/Inicial.fxml");
      AnchorPane paneOne = FXMLLoader.load( paneOneUrl );
      
      BorderPane border = Main.getRoot();
      border.setCenter(paneOne);
      
    } catch (IOException e) {
      e.printStackTrace();
    }


  }

  /**
   * Event handler for MenuItem two
   */
  @FXML
  void switchToTwo(ActionEvent event) {

    try {
      
      URL paneTwoUrl = getClass().getResource("/presentacion/ReservarActividad.fxml");
      AnchorPane paneTwo = FXMLLoader.load( paneTwoUrl );
      
      BorderPane border = Main.getRoot();
      border.setCenter(paneTwo);
    
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
  
}
