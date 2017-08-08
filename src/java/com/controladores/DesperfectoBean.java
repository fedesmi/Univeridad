/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Desperfecto;
import com.entidades.SolicitudReparacion;
import com.entidades.Vehiculo;
import com.repositorios.DesperfectoFacade;
import com.repositorios.SolicitudReparacionFacade;
import java.io.Serializable;

import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Usuario
 */
@Named(value = "desperfectosBean")
@RequestScoped
public class DesperfectoBean implements Serializable {

    

    @Inject
    private DesperfectoFacade desperfectoFacade;
    private Desperfecto desperfectoVar;
     private SolicitudReparacionFacade solicitudReparacionFacade;


    private List<Desperfecto> desperfectosDeVehiculo;

    /**
     * Creates a new instance of DesperfectosBean
     */
    public DesperfectoBean() {
    }

    @PostConstruct
    public void init() {
        desperfectoVar = new Desperfecto();

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

    public void guardar(Vehiculo vehiculoPar) {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String  propiedad= request.getParameter("propiedadVal");
  
       
        desperfectoVar.setFecha(new Date());
        Vehiculo vehiculoVar = new Vehiculo();
        vehiculoVar.setId(Integer.valueOf(propiedad));
        desperfectoVar.setIdVehiculo(vehiculoVar);
        
        this.desperfectoFacade.create(desperfectoVar);
        
        desperfectoVar = null;
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "El desperfecto fue registrado exitosamente"));
        
    }


    public void crearSolicitudReparacion(Desperfecto desp) {
        System.out.println("holaaaaaaaaaaaaa");
        SolicitudReparacion solicitud = new SolicitudReparacion();
        solicitud.setFecha(new Date());
        solicitud.setAutorizado(0);
        solicitud.setIdDesperfecto(desp);
        solicitudReparacionFacade.create(solicitud);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "La solicitud fue generada"));
    }
    
    
}
