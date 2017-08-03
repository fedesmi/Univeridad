/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Desperfecto;
import com.repositorios.DesperfectoFacade;

import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Usuario
 */
@Named(value = "desperfectosBean")
@RequestScoped
public class DesperfectoBean {

    /**
     * @return the idVehiculoSeleccionado
     */
    public int getIdVehiculoSeleccionado() {
        return idVehiculoSeleccionado;
    }

    /**
     * @param idVehiculoSeleccionado the idVehiculoSeleccionado to set
     */
    public void setIdVehiculoSeleccionado(int idVehiculoSeleccionado) {
        this.idVehiculoSeleccionado = idVehiculoSeleccionado;
    }

    @Inject
    private DesperfectoFacade desperfectoFacade;
    private Desperfecto desperfectoVar=new Desperfecto();
    private int idVehiculoSeleccionado;

    private List<Desperfecto> desperfectosDeVehiculo;

    /**
     * Creates a new instance of DesperfectosBean
     */
    public DesperfectoBean() {
    }

   

    /**
     * @return the desperfectoFacade
     */
    public DesperfectoFacade getDesperfectoFacade() {
        return desperfectoFacade;
    }

    /**
     * @param desperfectoFacade the desperfectoFacade to set
     */
    public void setDesperfectoFacade(DesperfectoFacade desperfectoFacade) {
        this.desperfectoFacade = desperfectoFacade;
    }

    /**
     * @return the desperfectoVar
     */
    public Desperfecto getDesperfectoVar() {
        return desperfectoVar;
    }

    /**
     * @param desperfectoVar the desperfectoVar to set
     */
    public void setDesperfectoVar(Desperfecto desperfectoVar) {
        this.desperfectoVar = desperfectoVar;
    }

    /**
     * @return the desperfectosDeVehiculo
     * @param idVehiculo the idVehiculo to set
     */
    public List<Desperfecto> getDesperfectosDeVehiculo(int idVehiculo) {
        return this.desperfectoFacade.getDesperfectosDeVehiculo(idVehiculo);
    }

    /**
     * @param desperfectosDeVehiculo the desperfectosDeVehiculo to set
     */
    public void setDesperfectosDeVehiculo(List<Desperfecto> desperfectosDeVehiculo) {
        this.desperfectosDeVehiculo = desperfectosDeVehiculo;
    }

    public void guardar(int idvehiculo) {
        
        
        desperfectoVar.setFecha(new Date());
        desperfectoVar.setIdVehiculo(idVehiculoSeleccionado);
       
        
        
        this.desperfectoFacade.create(desperfectoVar);

        desperfectoVar = null;

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "El desperfecto fue registrado exitosamente"));

    }

    public void guardarId(int id) {
        System.out.println("id guar"+id );
        setIdVehiculoSeleccionado(id);
    }


 
}
