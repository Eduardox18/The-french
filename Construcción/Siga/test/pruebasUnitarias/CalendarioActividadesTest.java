package pruebasUnitarias;

import java.time.LocalDate;
import logica.CalendarioActividades;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Esmeralda Yamileth Hernández González
 */
public class CalendarioActividadesTest {
  
  @Test
  public void recuperarFechaLimiteExamenTest () {
    
      CalendarioActividades calendario = new CalendarioActividades();
      int nrcCurso = 37182;
      int idPortafolio = 1;
      int idCalendario = 1;
      LocalDate fecha = LocalDate.parse("2017-03-10");
      LocalDate resultado = null;
      
      if (idPortafolio == idCalendario) {
          fecha = resultado;
      }
      resultado = calendario.recuperarFechaLimiteExamen(nrcCurso);
      assertEquals(fecha, resultado);
  }
}
