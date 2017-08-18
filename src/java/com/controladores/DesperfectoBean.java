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
import org.primefaces.event.SelectEvent;

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
    private Desperfecto desperfectoSeleccionado;
    private List<Desperfecto> desperfectos;

    private List<Desperfecto> desperfectosDeVehiculo;

    /**
     * Creates a new instance of DesperfectosBean
     */
    public DesperfectoBean() {
        desperfectoVar = new Desperfecto();
        desperfectoSeleccionado = new Desperfecto();

    }

    @PostConstruct
    public void init() {
        desperfectoSeleccionado = new Desperfecto();
        desperfectos = getDesperfectosDB();
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
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String propiedad = request.getParameter("propiedadVal");

        desperfectoVar.setFecha(new Date());
        Vehiculo vehiculoVar = new Vehiculo();
        vehiculoVar.setId(Integer.valueOf(propiedad));
        desperfectoVar.setIdVehiculo(vehiculoVar);

        this.desperfectoFacade.create(desperfectoVar);

        desperfectoVar = null;
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "El desperfecto fue registrado exitosamente"));

    }

   

    public void mostrarHola() {
        System.out.println("mostrar hola");
    }

    /**
     * @return the desperfectoSeleccionado
     */
    public Desperfecto getDesperfectoSeleccionado() {
        return desperfectoSeleccionado;
    }

    /**
     * @param desperfectoSeleccionado the desperfectoSeleccionado to set
     */
    public void setDesperfectoSeleccionado(Desperfecto desperfectoSeleccionado) {
        this.desperfectoSeleccionado = desperfectoSeleccionado;
    }

    public void onRowSelect(SelectEvent event) {
        System.out.println("seleccionado " + ((Desperfecto) event.getObject()).getDescripcion());
    }

    /**
     * @return the desperfectos
     */
    public List<Desperfecto> getDesperfectos() {
        return desperfectos;
    }

    /**
     * @param desperfectos the desperfectos to set
     */
    public void setDesperfectos(List<Desperfecto> desperfectos) {
        this.desperfectos = desperfectos;
    }

    public List<Desperfecto> getDesperfectosDB() {
        return desperfectoFacade.findAll();
    }

    public void autorizarReparacion() {

        if (desperfectoSeleccionado.getIdSolicitud() != null) {
            
            desperfectoSeleccionado.getIdSolicitud().setAutorizado(1);
            desperfectoFacade.edit(desperfectoSeleccionado);
            FacesMessage msg = new FacesMessage("Autorizacion", "Se ha autorizado la solicitud ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage("Autorizacion NOk", "no hay solicitud cargada");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
    }

    public void solicitarReparacion() {
        SolicitudReparacion solRep = new SolicitudReparacion();
        solRep.setFecha(new Date());
        solRep.setAutorizado(0);
        desperfectoSeleccionado.setIdSolicitud(solRep);
        desperfectoFacade.edit(desperfectoSeleccionado);

        FacesMessage msg = new FacesMessage("Solicitud", "Se ha generado una solicitud de reparación");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
