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
 * @author Angel Eduardo Domínguez 
 */
public class ControladorPrincipal {

    @FXML 
    private MenuItem barReservarActividad;

    @FXML 
    private MenuItem barRegistrarBitacora; 

    @FXML
    void abrirReservarActividad(ActionEvent event) {
        try {
            URL reservarActividad = getClass().getResource("/presentacion/ReservarActividad.fxml");
            AnchorPane paneReservarActividad = FXMLLoader.load(reservarActividad);

            BorderPane border = ControladorLogIn.getRoot();
            border.setCenter(paneReservarActividad);
        } catch (IOException e) {
            //Alerta de excepción
        }
    }
    
    @FXML
    void abrirRegistrarBitacora(ActionEvent event) {
        try {
            URL registrarBitacora = getClass().getResource("/presentacion/RegistrarBitacora.fxml");
            AnchorPane paneRegistrarBitacora = FXMLLoader.load(registrarBitacora);
            
            BorderPane border = ControladorLogIn.getRoot();
            border.setCenter(paneRegistrarBitacora);
        } catch (IOException e) {
            //Alerta de excepción
        }
    }

}
