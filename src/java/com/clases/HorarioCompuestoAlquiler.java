/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clases;

import com.entidades.Horario;
import com.entidades.Vehiculo;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author fmichel
 */
public class HorarioCompuestoAlquiler implements Serializable {
    
    private Horario horario;
    private List<Vehiculo> vehiculosOcupados;
    private List<Vehiculo> vehiculosLibres;
    

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
     * @return the vehiculosOcupados
     */
    public List<Vehiculo> getVehiculosOcupados() {
        return vehiculosOcupados;
    }

    /**
     * @param vehiculosOcupados the vehiculosOcupados to set
     */
    public void setVehiculosOcupados(List<Vehiculo> vehiculosOcupados) {
        this.vehiculosOcupados = vehiculosOcupados;
    }

    /**
     * @return the vehiculosLibres
     */
    public List<Vehiculo> getVehiculosLibres() {
        return vehiculosLibres;
    }

    /**
     * @param vehiculosLibres the vehiculosLibres to set
     */
    public void setVehiculosLibres(List<Vehiculo> vehiculosLibres) {
        this.vehiculosLibres = vehiculosLibres;
    }

    
  
    
    
}
