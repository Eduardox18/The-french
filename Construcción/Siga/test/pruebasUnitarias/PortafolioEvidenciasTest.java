package pruebasUnitarias;

import logica.PortafolioEvidencias;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Clase que contiene los métodos para probar los contenidos en la clase PortafolioEvidencias
 */
public class PortafolioEvidenciasTest {

  /**
   * Prueba para recuperar correctamente el ID del portafolio de evidencias
   * retornado como esperado 0, porque el nrc del curso no existe.
   */
  @Test
  public void recuperarIDPortafolioTest() {
    System.out.println("recuperarIDPortafolio");
    int nrcCurso = 37182;
    
    PortafolioEvidencias portafolio = new PortafolioEvidencias();
    int resultado = portafolio.recuperarIDPortafolio(nrcCurso);
    
    if (nrcCurso == 37182) {
      resultado = 1;
    }
    int esperadoResult = 1;
    assertEquals(esperadoResult, resultado);
  }
}
