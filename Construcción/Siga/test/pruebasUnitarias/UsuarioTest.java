package pruebasUnitarias;

import logica.Usuario;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Esmeralda Yamileth Hernández González
 */
public class UsuarioTest {
  
  /**
   * Prueba para verificar que se realice correctamente la consulta del
   * usuario deseado.
   */
  @Test
  public void consultaUsuarioTest() {
    System.out.println("consultaUsuario");
    String nombre = "zS15011624";
    String pass = "angeldominguez";
    
    Usuario user = new Usuario();
    boolean esperadoResult = true;
    boolean resultado = user.consultaUsuario(nombre, pass);
    assertEquals(esperadoResult, resultado);
  }
  
  /**
   * Prueba que verifica que la contraseña cifrada es la que se desea utilizar
   * para el Usuario que está accediendo.
   */
  @Test 
  public void obtenerPasswordTest() {
    System.out.println("obtenerPassword");
    String pass = "angeldominguez";
    
   Usuario user = new Usuario();
   String esperadoResult = "c45bb4815941a3722ca2182bf4e707afc74bbdc745077722cbeff4db3fa4ce7c";
   String resultado = user.obtenerPassword(pass);
   assertEquals (esperadoResult, resultado);
  }
  
  /**
   * Prueba que verifica la existencia del usuario ingresado, regresando true
   * si existe o false si no existe.
   */
  @Test 
  public void existeUsuarioTest () {
    System.out.println("existeUsuario");
   String nombre = "zS15011624";
   
   Usuario user = new Usuario();
   boolean esperadoResult = true;
   boolean resultado = user.existeUsuario(nombre);
   assertEquals(esperadoResult, resultado);
  }
}


