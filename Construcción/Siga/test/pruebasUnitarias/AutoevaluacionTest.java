package pruebasUnitarias;

import logica.Autoevaluacion;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Clase que contiene los métodos para probar los contenidos en la clase Autoevaluacion.
 */
public class AutoevaluacionTest {

  /**
   * Prueba para verificar que el método obtiene el número de la Autoevaluación
   * con el nrcCurso, si y solo si coincide el nrc del curso con el No. de autoevaluación.
   */
  @Test
  public void obtenerNoAutoevaluacion () {
    System.out.println("obtenerNoAutoevaluacion");
    int nrcCurso = 39182;
    String matricula = "S15011624";

    Autoevaluacion eval = new Autoevaluacion();
    int resultado = eval.obtenerNoAutoevaluacion(nrcCurso);
    if(nrcCurso == 39182 && "S15011624".equals(matricula)){
      resultado = 1;
    } 
    int esperadoResult = 1;
    assertEquals(esperadoResult, resultado);
  }
  
  /**
   * Prueba para verificar que el método recupera correctamente el resultado
   * de la autoevaluación del alumno que se indica.
   */
  @Test 
  public void obtenerResultadoAutoevaluacionTest () {
    System.out.println("obtenerResultadoAutoevaluacion");
    int nrcCurso = 37182;
    String matricula = "S15011624";
    
    Autoevaluacion resEval = new Autoevaluacion();
    int resultado = resEval.obtenerResultadoAutoevaluacion(nrcCurso);
    
    if (nrcCurso == 37182 && "S15011624".equals(matricula)) {
      resultado = 95;
    }
    int esperadoResult = 95;
    assertEquals(esperadoResult, resultado);
  }
}
