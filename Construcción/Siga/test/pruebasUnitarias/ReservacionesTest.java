package pruebasUnitarias;

import logica.Reservacion;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Clase que contiene los métodos para probar los contenidos en la clase Reservacion.
 */
public class ReservacionesTest {

  /**
   * Prueba que verifica que se agregue correctamente una reservación a la base de datos.
   */
  @Test
  public void agregarReservacionTest() {
    System.out.println("reservacion");
    int idActividad = 2;
    String matricula = "S15011624";
    
    Reservacion reservacion = new Reservacion();
    boolean esperadoResult = true;
    boolean resultado = reservacion.agregarReservacion(idActividad, matricula);
    assertEquals(esperadoResult,resultado);
  }
  
  /**
   * Prueba que realiza una comprobación para que el alumno no pueda reservar una actividad
   * que ya ha reservado anteriormente.
   */
  @Test
   public void comprobarReservacionesTest() {
     System.out.println("comprobarReservaciones");
     String matricula = "S15011624";
     int idActividad = 2;
     Reservacion existente = new Reservacion();
     boolean esperadoResult = false;
     boolean resultado = existente.comprobarReservaciones(matricula, idActividad);
     assertEquals(esperadoResult, resultado);
   }
}
