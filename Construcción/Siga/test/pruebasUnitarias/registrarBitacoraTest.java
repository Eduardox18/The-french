package pruebasUnitarias;

import logica.Bitacora;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Clase que contiene los métodos para probar los contenidos en la clase RegistrarBitacora.
 */
public class registrarBitacoraTest {
  
 /**
  * Prueba que verifica que se registre correctamente una bitácora, comparando los 
  * resultados esperados con los obtenidos del método.
  */
  @Test
  public void registrarBitacoraTest() {
    System.out.println("registrarBitacora");
    Integer tiempoEmpleado = 100;
    String comentario = "ejercicios de audio";
    java.sql.Date fecha = java.sql.Date.valueOf("2017-06-01");
    int idPortafolio = 1;
    int idAutoevaluacion = 1;
    
    Bitacora bitacora = new Bitacora(tiempoEmpleado, comentario, fecha, idPortafolio, idAutoevaluacion);
    boolean esperadoResult = true;
    boolean resultado = bitacora.registrarBitacora(bitacora); 
    assertEquals(esperadoResult,resultado);
  }
}
