/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clases;

import com.entidades.Alumno;
import com.entidades.Empleado;
import com.entidades.Horario;
import java.util.List;

/**
 *
 * @author fmichel
 */
public class HorarioCompuesto {
    
    private Horario horario;
    private List<Empleado> instructoresDispoinibles;
    private List<Alumno> alumnosDisponibles;
    
    

    /**
     * @return the horario
     */
    public Horario getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    
    /**
     * @return the instructoresDispoinibles
     */
    public List<Empleado> getInstructoresDispoinibles() {
        return instructoresDispoinibles;
    }

    /**
     * @param instructoresDispoinibles the instructoresDispoinibles to set
     */
    public void setInstructoresDispoinibles(List<Empleado> instructoresDispoinibles) {
        this.instructoresDispoinibles = instructoresDispoinibles;
    }

    /**
     * @return the alumnosDisponibles
     */
    public List<Alumno> getAlumnosDisponibles() {
        return alumnosDisponibles;
    }

    /**
     * @param alumnosDisponibles the alumnosDisponibles to set
     */
    public void setAlumnosDisponibles(List<Alumno> alumnosDisponibles) {
        this.alumnosDisponibles = alumnosDisponibles;
    }

    
    
}
