/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Mantenimiento;
import com.repositorios.MantenimientoFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author fmichel
 */
@Named(value = "mantenimientosBean")
@RequestScoped
public class MantenimientoBean {

    @Inject
    private MantenimientoFacade mantenimientoFacade;
    private Mantenimiento mantenimientoVar;
    
     private List<Mantenimiento> mantenimientosDeVehiculo;
    /**
     * Creates a new instance of Mantenimientos
     */
    public MantenimientoBean() {
    }
    
     // This is the required method to get the datatable list.
    @PostConstruct
    public void init() {

    }

   

    /**
     * @return the mantenimientosDeVehiculo
     */
    public List<Mantenimiento> getMantenimientosDeVehiculo(int idVehiculo) {
        return  this.mantenimientoFacade.getMantenimientosDeVehiculo(idVehiculo);
    }

    /**
     * @param mantenimientosDeVehiculo the mantenimientosDeVehiculo to set
     */
    public void setMantenimientosDeVehiculo(List<Mantenimiento> mantenimientosDeVehiculo) {
        this.mantenimientosDeVehiculo = mantenimientosDeVehiculo;
    }

    /**
     * @return the mantenimientoFacade
     */
    public MantenimientoFacade getMantenimientoFacade() {
        return mantenimientoFacade;
    }

    /**
     * @param mantenimientoFacade the mantenimientoFacade to set
     */
    public void setMantenimientoFacade(MantenimientoFacade mantenimientoFacade) {
        this.mantenimientoFacade = mantenimientoFacade;
    }

    /**
     * @return the mantenimientoVar
     */
    public Mantenimiento getMantenimientoVar() {
        return mantenimientoVar;
    }

    /**
     * @param mantenimientoVar the mantenimientoVar to set
     */
    public void setMantenimientoVar(Mantenimiento mantenimientoVar) {
        this.mantenimientoVar = mantenimientoVar;
    }
    
}
