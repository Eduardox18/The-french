package pruebasUnitarias;

import logica.Bitacora;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Esmeralda Yamileth Hernández González
 */
public class BitacoraTest {
  
  /**
   * Prueba para verificar que el resultado esperado de la recuperacion
   * del número actual de la bitácora sea igual al resultado del método.
   */
  @Test 
  public void recuperarNoActualBitacoraTest(){
    System.out.println("recuperarNoActualBitacora");
    int noBitacora = 2;
    Bitacora prueba = new Bitacora();
    int esperadoResult = 3;
    int resultado = prueba.recuperarNoActualBitacora();
    assertEquals(esperadoResult,resultado);
  }
  
  /**
   * Prueba para verificar que el número esperado de la recuperación del último
   * número de bitácora sea igual al resultado del método.
   */
  @Test
  public void recuperarUltimoNoBitacoraTest(){
    System.out.println("recuperarUltimoNoBitacora");
    int noBitacora = 2;
    Bitacora instance = new Bitacora();
    int esperadoResult = 2;
    int resultado = instance.recuperarUltimoNoBitacora();
    assertEquals(esperadoResult,resultado);
  }
  
  /**
   * Prueba para verificar que se guardan correctamente las actividades a las que 
   * asistió el alumno.
   */
  @Test
  public void guardarActividadesAsistidas() {
    System.out.println("guardarActividadesAsistidas");
    int nrcCurso = 37182;
    Bitacora instance = new Bitacora();
    boolean esperadoResult = true;
    boolean resultado = instance.guardarActividadesAsistidas(nrcCurso);
    assertEquals(esperadoResult, resultado);
  }
  
  /**
   * Prueba para verificar que se guardan correctamente las actividades escritas 
   * en la base de datos.
   */
  @Test
  public void guardarActividadesEscritasTest() {
    System.out.println("guardarActividadesEscritas");
    int nrcCurso = 1;
    Bitacora instance = new Bitacora();
    boolean esperadoResult = true;
    boolean resultado = instance.guardarActividadesAsistidas(nrcCurso);
    assertEquals(esperadoResult, resultado); 
  } 
  
  /**
   * Prueba que realiza una comprobación para que el alumno no ingrese una bitácora
   * ya existente en la base de datos, se espera como resultado false, que significa
   * que no se encuentra repetida.
   */
  @Test
   public void comprobarBitacoraExistenteTest() {
     System.out.println("comprobarBitacoraExistente");
     int nrcCurso = 39182;
     Bitacora existente = new Bitacora();
     boolean esperadoResult = false;
     boolean resultado = existente.comprobarBitacoraExistente(nrcCurso);
     assertEquals(esperadoResult, resultado);
   }
}
