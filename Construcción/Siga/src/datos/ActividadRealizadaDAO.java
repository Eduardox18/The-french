/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import javafx.collections.ObservableList;
import logica.ActividadRealizada;

/**
 *
 * @author José Andrés Domínguez González
 */
public interface ActividadRealizadaDAO {
    
    public ObservableList<ActividadRealizada> obtenerActividades (int nrcCurso, String matriculaAlumno);
    
}
