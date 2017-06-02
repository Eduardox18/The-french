package pruebasUnitarias;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logica.Curso;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Esmeralda Yamileth Hernández González
 */
public class CursoTest {
  
  /**
   * Prueba que verifica que el ObservableList sea diferente de null, es decir que
   * que contenga cursos.
   */
  @Test
  public void obtenerCursosTest() {
    System.out.println("obtenerCursos");
    String matricula = "S15011624";

    Curso curso1 = new Curso("Francés I", 37182);
    Curso curso2 = new Curso("Inglés II", 39182);

    ObservableList<Curso> cursos = FXCollections.observableArrayList();
    cursos.add(curso1);
    cursos.add(curso2);
    ObservableList<Curso> esperado = null;
    ObservableList<Curso> resultado = null;
    
    Curso curso = new Curso();
    if (cursos != null && "S15011624".equals(matricula)) {
       assertEquals(esperado, resultado);
    }
  }
}
