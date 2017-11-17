/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Empleado;
import com.entidades.Vehiculo;
import com.repositorios.VehiculoFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author fmichel
 */
@Named(value = "vehiculoBean")
@SessionScoped
public class VehiculoBean implements Serializable {

    @Inject
    private VehiculoFacade vehiculoFacade;
   
    private Vehiculo vehiculoVar;
    private Vehiculo vehiculoSelected;
    private Vehiculo vehiculoSelectedMantenimiento;
    private List<Vehiculo> vehiculos;

    /**
     * Creates a new instance of VehiculoBean
     */
    public VehiculoBean() {
    }

    // This is the required method to get the datatable list.
    @PostConstruct
    public void init() {
    //vehiculoVar = new Vehiculo();

    }
    
      public void onloadLista() {

        vehiculos = getVehiculosAll();

    }
        public void onloadAsignar() {

        vehiculos = getVehiculosParaClase();
   
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

    private List<Vehiculo> getVehiculosParaClase() {
        return this.vehiculoFacade.getVehiculosParaClases();
    }
    private List<Vehiculo> getVehiculosAll() {
        return this.vehiculoFacade.findAll();
    }

    public void onRowEdit(RowEditEvent event) {
        Vehiculo vehVar = (Vehiculo) event.getObject();
        this.vehiculoFacade.edit(vehVar);
        FacesMessage msg = new FacesMessage("Vehiculo Editado", "Patente: " + vehVar.getPatente());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        Vehiculo vehVar = (Vehiculo) event.getObject();
        FacesMessage msg = new FacesMessage("Edición Cancelada", "Patente: " + vehVar.getPatente());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @return the vehiculoVar
     */
    public Vehiculo getVehiculoVar() {
        return vehiculoVar;
    }

    /**
     * @param vehiculoPar the vehiculoVar to set
     */
    public void setVehiculoVar(Vehiculo vehiculoPar) {

        this.vehiculoVar = vehiculoPar;
    }

    public void guardar() {

        this.vehiculoFacade.create(vehiculoVar);

        vehiculoVar = null;

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "El Vehiculo fue registrado exitosamente"));

    }

    /**
     * @return the vehiculoSelected
     */
    public Vehiculo getVehiculoSelected() {
        return vehiculoSelected;
    }

    /**
     * @param vehiculoSelected the vehiculoSelected to set
     */
    public void setVehiculoSelected(Vehiculo vehiculoSelected) {
        this.vehiculoSelected = vehiculoSelected;
    }

    public void asignarEmpleadoVehiculo(Empleado emp) {
        this.vehiculoFacade.asignarEmpleadoVehiculo(emp, vehiculoSelected.getId());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "El vehiculo fue asignado Correctamente"));

    }

    /**
     * @return the vehiculoSelectedMantenimiento
     */
    public Vehiculo getVehiculoSelectedMantenimiento() {
        return vehiculoSelectedMantenimiento;
    }

    /**
     * @param vehiculoSelectedMantenimiento the vehiculoSelectedMantenimiento to set
     */
    public void setVehiculoSelectedMantenimiento(Vehiculo vehiculoSelectedMantenimiento) {
        this.vehiculoSelectedMantenimiento = vehiculoSelectedMantenimiento;
    }

    
 
    
    

}
