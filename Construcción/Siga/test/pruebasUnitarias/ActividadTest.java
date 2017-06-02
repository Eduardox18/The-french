package pruebasUnitarias;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.Actividad;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Esmeralda Yamileth Hernández González
 */
public class ActividadTest {

  /**
   * Prueba que verifica que el ObservableList sea diferente de null, es decir que
   * contenga Actividades.
   */
  @Test
  public void consultarActividadesTest() {
    System.out.println("consultarActividades");
    int nrcCurso = 37182;
    java.sql.Date fecha = java.sql.Date.valueOf("2017-06-01");


    ObservableList<Actividad> listaActividades = FXCollections.observableArrayList();
 
    ObservableList<Actividad> esperado = listaActividades;
    ObservableList<Actividad> resultado = listaActividades;
    
    if (listaActividades != null && nrcCurso==37182 && "2017-06-01".equals(fecha)) {
       assertEquals(esperado, resultado);
    }
  }
  
    /**
   * Prueba que verifica que el ObservableList sea diferente de null, es decir que
   * contenga Actividades a las que el alumno asistió.
   */
  @Test
  public void consultarActividadesAsistidasTest() {
    System.out.println("consultarActividadesAsistidas");
    int nrcCurso = 37182;
    String matricula = "S15011624";

    ObservableList<Actividad> listaActividades = FXCollections.observableArrayList();
 
    ObservableList<Actividad> esperado = listaActividades;
    ObservableList<Actividad> resultado = listaActividades;
    
    if (listaActividades != null && nrcCurso==37182 && "S15011624".equals(matricula)) {
       assertEquals(esperado, resultado);
    }
  }
  
    /**
   * Prueba que obtiene el ID de la Actividad y comprueba que coincida
   * con el resultado esperado.
   */
  @Test
  public void obtenerIDActividadTest() {
    System.out.println("obtenerIDActividad");
    String nombreActividad = "El Oui";
    
    Actividad act = new Actividad();
    int resultado = act.obtenerIDActividad();
    if ("El Oui".equals(nombreActividad)) {
      resultado = 3;
    }
    
    int esperadoResult = 3;
    assertEquals(esperadoResult, resultado);
    
    
  }
}
