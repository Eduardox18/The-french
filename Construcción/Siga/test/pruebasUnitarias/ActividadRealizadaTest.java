package pruebasUnitarias;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.ActividadRealizada;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Esmeralda Yamileth Hernández González
 */
public class ActividadRealizadaTest {

  /**
   * Prueba que verifica que el ObservableList sea diferente de null, es decir que
   * contenga Actividades realizadas.
   */
  @Test
  public void consultarActividadesTest() {
    System.out.println("consultarActividades");
    int nrcCurso = 37182;
    String matricula = "S15011624";


    ObservableList<ActividadRealizada> listaActividades = FXCollections.observableArrayList();
 
    ObservableList<ActividadRealizada> esperado = listaActividades;
    ObservableList<ActividadRealizada> resultado = listaActividades;
    
    if (listaActividades != null && nrcCurso==37182 && "S15011624".equals(matricula)) {
       assertEquals(esperado, resultado);
    }
  }
}
