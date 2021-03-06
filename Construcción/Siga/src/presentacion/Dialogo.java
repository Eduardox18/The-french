package presentacion;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Clase que contiene los métodos que muestran alertas en pantalla. Perteneciente a capa de
 * presentación.
 */
public class Dialogo {

    /**
     * Método que muestra una alerta de error de conexión en pantalla.
     */
    public void alertaCerrarConexion() {
        Alert alertaUsuario = new Alert(Alert.AlertType.ERROR);
        alertaUsuario.setTitle("Error");
        alertaUsuario.setHeaderText("Error inesperado");
        alertaUsuario.setContentText("Ha ocurrido un error al cerrar las conexiones con la base "
            + "de datos, contacte a un programador del Sistema SIGA");
        alertaUsuario.showAndWait();
    }

    /**
     *
     * Método que muestra una alerta de error en pantalla.
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
     * Método que muestra una alerta de precaución que informa que existen campos sin llenar en los
     * formularios.
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
     * Método que muestra una alerta de precaución que informa que los datos del usuario ingresado
     * no son correctos o no existen.
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
     * Método que muestra una alerta que informa cuando la reservación a la actividad se realizó de
     * forma exitosa.
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
     * Método que muestra una alerta que informa que una actividad ya se ha reservado por el alumno.
     */
    public void alertaExisteActividad() {
        Alert alertaUsuario = new Alert(Alert.AlertType.WARNING);
        alertaUsuario.setTitle("Reservación existente");
        alertaUsuario.setHeaderText("Alerta");
        alertaUsuario.setContentText("No puedes realizar la misma reservación más de una vez");
        alertaUsuario.showAndWait();
    }

    /**
     * Método que muestra una alerta que informa que el registro de la bitácora se realizó
     * exitosamente.
     */
    public void alertaBitacoraRegistrada() {
        Alert alertaUsuario = new Alert(Alert.AlertType.INFORMATION);
        alertaUsuario.setTitle("¡Registro exitoso!");
        alertaUsuario.setHeaderText("Alerta");
        alertaUsuario.setContentText("El registro se realizó exitosamente");
        alertaUsuario.showAndWait();
    }

    /**
     * Método que muestra una alerta de precaución que informa que el registro de la bitácora no se
     * pudo realizar.
     */
    public void alertarBitacoraNoRegistrada() {
        Alert alertaUsuario = new Alert(Alert.AlertType.WARNING);
        alertaUsuario.setTitle("Ocurrió un error");
        alertaUsuario.setHeaderText("Alerta");
        alertaUsuario.setContentText("El registro no se pudo realizar, "
            + "intente nuevamente");
        alertaUsuario.showAndWait();
    }

    /**
     *
     * Método que muestra una alerta que informa que la fecha seleccionada aún no ha ocurrido
     */
    public void alertarFechaFutura() {
        Alert alertaUsuario = new Alert(Alert.AlertType.WARNING);
        alertaUsuario.setTitle("Fecha erronea");
        alertaUsuario.setHeaderText("Alerta");
        alertaUsuario.setContentText("La fecha ingresada aun no llega");
        alertaUsuario.showAndWait();
    }

    /**
     *
     * Método que muestra una alerta que informa que la fecha seleccionada es anterior a la actual.
     */
    public void alertarFechaPasada() {
        Alert alertaUsuario = new Alert(Alert.AlertType.WARNING);
        alertaUsuario.setTitle("Fecha erronea");
        alertaUsuario.setHeaderText("Alerta");
        alertaUsuario.setContentText("No puede seleccionar una fecha pasada");
        alertaUsuario.showAndWait();
    }

    /**
     * Método que muestra una alerta que informa que el periodo de entrega de la bitácora ha
     * expirado.
     */
    public void fechaLimiteExcedida() {
        Alert alertaUsuario = new Alert(Alert.AlertType.WARNING);
        alertaUsuario.setTitle("Fuera de tiempo");
        alertaUsuario.setHeaderText("Alerta");
        alertaUsuario.setContentText("El periodo de entrega ha terminado");
        alertaUsuario.showAndWait();
    }

    public ButtonType confirmacionCancelar() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmar cancelación");
        alert.setHeaderText("Confirmación");
        alert.setContentText("¿Seguro que desea cancelar la reservación de la Actividad?");

        Optional<ButtonType> result = alert.showAndWait();
        return result.get();
    }
}
