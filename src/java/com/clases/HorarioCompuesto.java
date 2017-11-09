/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clases;

import com.entidades.AlquilerVehiculo;
import com.entidades.Alumno;
import com.entidades.Clase;
import com.entidades.Empleado;
import com.entidades.Horario;
import com.entidades.ListaEsperaClase;
import com.entidades.Vehiculo;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author fmichel
 */
public class HorarioCompuesto implements Serializable {
    
    private Horario horario;
    private List<Empleado> instructoresDispoinibles;
    private List<Alumno> alumnosDisponibles;
    private List<ListaEsperaClase> listaEsperaClase;
    private List<Clase> clases;
    private List<AlquilerVehiculo> alquileresVehiculo;
    
    

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

    /**
     * @return the listaEsperaClase
     */
    public List<ListaEsperaClase> getListaEsperaClase() {
        return listaEsperaClase;
    }

    /**
     * @param listaEsperaClase the listaEsperaClase to set
     */
    public void setListaEsperaClase(List<ListaEsperaClase> listaEsperaClase) {
        this.listaEsperaClase = listaEsperaClase;
    }

    /**
     * @return the clases
     */
    public List<Clase> getClases() {
        return clases;
    }

    /**
     * @param clases the clases to set
     */
    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }

    /**
     * @return the alquileresVehiculo
     */
    public List<AlquilerVehiculo> getAlquileresVehiculo() {
        return alquileresVehiculo;
    }

    /**
     * @param alquileresVehiculo the alquileresVehiculo to set
     */
    public void setAlquileresVehiculo(List<AlquilerVehiculo> alquileresVehiculo) {
        this.alquileresVehiculo = alquileresVehiculo;
    }

    
    
}
