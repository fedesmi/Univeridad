/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Vehiculo;
import com.repositorios.VehiculoFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author fmichel
 */
@Named(value = "vehiculoBean")
@RequestScoped
public class VehiculoBean {

@Inject
    private VehiculoFacade vehiculoFacade;

    
     private List<Vehiculo> vehiculos;
    /**
     * Creates a new instance of VehiculoBean
     */
    public VehiculoBean() {
    }

    
    // This is the required method to get the datatable list.
    @PostConstruct
    public void init() {
      vehiculos = getVehiculosDB();

    }
    
    /**
     * @return the vehiculos
     */
    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    /**
     * @param vehiculos the vehiculos to set
     */
    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    private List<Vehiculo> getVehiculosDB() {
        return this.vehiculoFacade.findAll();
    }
    
    
    
}
