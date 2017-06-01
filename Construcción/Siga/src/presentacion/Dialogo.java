package presentacion;

import javafx.scene.control.Alert;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 */
public class Dialogo {

    /**
     * 
     * Muestra una alerta de error en la pantalla
     */
    public void alertaError() {
        Alert alertaUsuario = new Alert(Alert.AlertType.ERROR);
        alertaUsuario.setTitle("Error");
        alertaUsuario.setHeaderText("Error inesperado");
        alertaUsuario.setContentText("Ha ocurrido un error inesperado, consulte "
                + "al programador del Sistema SIGA");
        alertaUsuario.showAndWait();
    }

    /**
     * 
     * Muestra una alerta informando que fltan campos por llenar
     */
    public void alertaCamposVacios() {
        Alert alertaCampos = new Alert(Alert.AlertType.WARNING);
        alertaCampos.setTitle("Campos incompletos");
        alertaCampos.setHeaderText("Alerta");
        alertaCampos.setContentText("Por favor completa todos los campos");
        alertaCampos.showAndWait();
    }

    /**
     * 
     * Muestra una alerta informando que los datos ingresados no son correctos
     */
    public void alertaInformacionErronea() {
        Alert alertaUsuario = new Alert(Alert.AlertType.WARNING);
        alertaUsuario.setTitle("Usuario no encontrado");
        alertaUsuario.setHeaderText("Alerta");
        alertaUsuario.setContentText("El usuario que ingresó no existe o la información "
                + "ingresada es incorrecta, favor de verificarla.");
        alertaUsuario.showAndWait();
    }
    
    /**
     * 
     * Alerta que informa cuando la reservación se realiza de forma exitosa
     */
    public void alertaReservacionExistosa() {
        Alert alertaUsuario = new Alert(Alert.AlertType.INFORMATION);
        alertaUsuario.setTitle("¡Reservación exitosa!");
        alertaUsuario.setHeaderText("Alerta");
        alertaUsuario.setContentText("La reservación se realizó exitosamente");
        alertaUsuario.showAndWait();
    }
    
    /**
     * 
     * Alerta que informa si una actividad ya se encunetra reservada por un 
     * alumno
     */
    public void alertaExisteActividad() {
        Alert alertaUsuario = new Alert(Alert.AlertType.WARNING);
        alertaUsuario.setTitle("Reservación existente");
        alertaUsuario.setHeaderText("Alerta");
        alertaUsuario.setContentText("No puedes realizar la misma reservación más de una vez");
        alertaUsuario.showAndWait();
    }
    
    public void alertaBitacoraRegistrada() {
        Alert alertaUsuario = new Alert(Alert.AlertType.INFORMATION);
        alertaUsuario.setTitle("¡Registro exitoso!");
        alertaUsuario.setHeaderText("Alerta");
        alertaUsuario.setContentText("El registro se realizó exitosamente");
        alertaUsuario.showAndWait();
    }
    
    public void alertarBitacoraNoRegistrada() {
        Alert alertaUsuario = new Alert(Alert.AlertType.WARNING);
        alertaUsuario.setTitle("Ocurrió un error");
        alertaUsuario.setHeaderText("Alerta");
        alertaUsuario.setContentText("El registro no se pudo realizar, "
            + "intente nuevamente");
        alertaUsuario.showAndWait();
    }
}
