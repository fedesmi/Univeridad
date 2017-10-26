/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Vehiculo;
import com.repositorios.VehiculoFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author fmichel
 */
@Named(value = "alquilerVehiculoBean")
@RequestScoped
public class AlquilerVehiculoBean {
    private List<Vehiculo> vehiculosDisponibles;
    
    @Inject
    private VehiculoFacade vehiculofacade;

     private Date fechaConsulta = new Date();
     
    /**
     * Creates a new instance of AlquilerVehiculoBean
     */
    public AlquilerVehiculoBean() {
    }
    
    public void onload() {

        fechaConsulta = new Date();
        vehiculosDisponibles = vehiculofacade.getVehiculosOcupados(fechaConsulta);

    }
   

    /**
     * @return the vehiculosDisponibles
     */
    public List<Vehiculo> getVehiculosDisponibles() {
        return vehiculosDisponibles;
    }

    /**
     * @param vehiculosDisponibles the vehiculosDisponibles to set
     */
    public void setVehiculosDisponibles(List<Vehiculo> vehiculosDisponibles) {
        this.vehiculosDisponibles = vehiculosDisponibles;
    }
    
}
