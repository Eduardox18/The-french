package presentacion;

import javafx.scene.control.Alert;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 */
public class Dialogo {

    public void alertaError() {
        Alert alertaUsuario = new Alert(Alert.AlertType.ERROR);
        alertaUsuario.setTitle("Error");
        alertaUsuario.setHeaderText("Error inesperado");
        alertaUsuario.setContentText("Ha ocurrido un error inesperado, consulte "
                + "al programador del Sistema SIGA");
        alertaUsuario.showAndWait();
    }

    public void alertaCamposVacios() {
        Alert alertaCampos = new Alert(Alert.AlertType.WARNING);
        alertaCampos.setTitle("Campos incompletos");
        alertaCampos.setHeaderText("Alerta");
        alertaCampos.setContentText("Por favor completa todos los campos");
        alertaCampos.showAndWait();
    }

    public void alertaInformacionErronea() {
        Alert alertaUsuario = new Alert(Alert.AlertType.WARNING);
        alertaUsuario.setTitle("Usuario no encontrado");
        alertaUsuario.setHeaderText("Alerta");
        alertaUsuario.setContentText("El usuario que ingresó no existe o la información "
                + "ingresada es incorrecta, favor de verificarla.");
        alertaUsuario.showAndWait();
    }
    
    public void alertaReservacionExistosa() {
        Alert alertaUsuario = new Alert(Alert.AlertType.INFORMATION);
        alertaUsuario.setTitle("¡Reservación exitosa!");
        alertaUsuario.setHeaderText("Alerta");
        alertaUsuario.setContentText("La reservación se realizó exitosamente");
        alertaUsuario.showAndWait();
    }
    
    public void alertaExisteActividad() {
        Alert alertaUsuario = new Alert(Alert.AlertType.WARNING);
        alertaUsuario.setTitle("Reservación existente");
        alertaUsuario.setHeaderText("Alerta");
        alertaUsuario.setContentText("No puedes realizar la misma reservación más de una vez");
        alertaUsuario.showAndWait();
    }
}
