/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import datos.ActividadDAO;
import java.sql.Date;
import java.sql.Time;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Angel Eduardo Domínguez Delgado
 */
public class Actividad implements ActividadDAO{
    
    private SimpleStringProperty nombre;
    private SimpleStringProperty profesor;
    private Time horaActividad;
    private Date diaActividad;
    
    public Actividad(String xNombre, String xProfesor, Time horaActividad, Date diaActividad) {
        this.nombre = new SimpleStringProperty(xNombre);
        this.profesor = new SimpleStringProperty(xProfesor);
        this.horaActividad = horaActividad;
        this.diaActividad = diaActividad;
    }
    
    public String getNombre() {
        return nombre.get();
    }
    
    public void setNombre(String xNombre) {
        nombre.set(xNombre);
    }
    
    public String getProfesor() {
        return profesor.get();
    }
    
    public void setProfesor(String xProfesor) {
        profesor.set(xProfesor);
    }
    
    public Time getHoraActividad() {
        return horaActividad;
    }
    
    public void setHoraActividad(Time horaActividad) {
        this.horaActividad = horaActividad;
    }
    
    public Date getDiaActividad() {
        return diaActividad;
    }
    
    public void setDiaActividad(Date diaActividad) {
        this.diaActividad = diaActividad;
    }

    @Override
    public boolean agregarActividad(Actividad actividad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarActividad(int idActividad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editarActividad(int idActividad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Actividad> consultarActividad(int idActividad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
