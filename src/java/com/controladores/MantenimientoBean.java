/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Egreso;
import com.entidades.Mantenimiento;
import com.entidades.Usuario;
import com.entidades.Vehiculo;
import com.repositorios.EgresoFacade;
import com.repositorios.MantenimientoFacade;
import com.repositorios.UsuarioFacade;
import java.io.Serializable;

import java.util.Date;
import javax.inject.Named;
import javax.inject.Inject;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Usuario
 */
@Named(value = "mantenimientosBean")
@SessionScoped
public class MantenimientoBean implements Serializable {

    @Inject
    private MantenimientoFacade mantenimientoFacade;
    private Mantenimiento mantenimientoVar;

    private Date fecha;
    private String lugar;
    private float valor;
    private String factura;
    private int kilometraje;
    private String descripcion;

    private List<Mantenimiento> mantenimientosDeVehiculo;

    private List<Mantenimiento> mantenimientos;
    private List<Mantenimiento> mantenimientoSeleccionado;

    private Mantenimiento MantenimientoSelected;

    private Vehiculo vehiculoSeleccionado;
    @Inject
    private UsuarioFacade usuarioFacade;

    @Inject
    private EgresoFacade egresoFacade;

    /**
     * Creates a new instance of MantenimientosBean
     */
    public MantenimientoBean() {
    }

    @PostConstruct
    public void init() {
        mantenimientoVar = new Mantenimiento();

    }

    public void onLoadMantenimientos() {
        mantenimientos = mantenimientoFacade.findAll();

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

    /**
     * @return the mantenimientosDeVehiculo
     */
    public List<Mantenimiento> getMantenimientosDeVehiculoDB() {
        return this.mantenimientoFacade.getMantenimientosDeVehiculo(vehiculoSeleccionado);
    }

    /**
     * @param mantenimientosDeVehiculo the mantenimientosDeVehiculo to set
     */
    public void setMantenimientosDeVehiculo(List<Mantenimiento> mantenimientosDeVehiculo) {
        this.mantenimientosDeVehiculo = mantenimientosDeVehiculo;
    }

    public void guardar() {
        mantenimientoVar.setIdVehiculo(vehiculoSeleccionado);
        mantenimientoVar.setDescripcion(descripcion);
        mantenimientoVar.setKilometraje(kilometraje);
        mantenimientoVar.setFecha(new Date());
        this.mantenimientoFacade.create(mantenimientoVar);

        mantenimientoVar = null;
        borrarVariables();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "El mantenimiento fue registrado exitosamente"));

    }

    public void guardarPresupuesto() {
        MantenimientoSelected.setLugar(lugar);
        MantenimientoSelected.setPresupuesto(valor);
        this.mantenimientoFacade.edit(MantenimientoSelected);

        borrarVariables();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "El mantenimiento fue registrado exitosamente"));

    }

    public void guardarEgreso() {
        MantenimientoSelected.setFactura(factura);
        Egreso egreso = new Egreso();
        egreso.setConcepto("MANTENIMIENTO");
        egreso.setFecha(new Date());
        egreso.setMonto(MantenimientoSelected.getPresupuesto());
        egresoFacade.create(egreso);
        MantenimientoSelected.setIdEgreso(egreso);
        this.mantenimientoFacade.edit(MantenimientoSelected);

        borrarVariables();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "El mantenimiento fue registrado exitosamente"));

    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * @param lugar the lugar to set
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * @return the valor
     */
    public float getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(float valor) {
        this.valor = valor;
    }

    /**
     * @return the factura
     */
    public String getFactura() {
        return factura;
    }

    /**
     * @param factura the factura to set
     */
    public void setFactura(String factura) {
        this.factura = factura;
    }

    /**
     * @return the kilometraje
     */
    public int getKilometraje() {
        return kilometraje;
    }

    /**
     * @param kilometraje the kilometraje to set
     */
    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the mantenimientosDeVehiculo
     */
    public List<Mantenimiento> getMantenimientosDeVehiculo() {
        return mantenimientosDeVehiculo;
    }

    private void borrarVariables() {
        this.setFactura("");
        this.setFecha(null);
        this.setLugar("");
        this.setDescripcion("");
        this.setKilometraje(0);
        this.setValor(0);
    }

    /**
     * @return the mantenimientos
     */
    public List<Mantenimiento> getMantenimientos() {
        return mantenimientos;
    }

    /**
     * @param mantenimientos the mantenimientos to set
     */
    public void setMantenimientos(List<Mantenimiento> mantenimientos) {
        this.mantenimientos = mantenimientos;
    }

    /**
     * @return the mantenimientoSeleccionado
     */
    public List<Mantenimiento> getMantenimientoSeleccionado() {
        return mantenimientoSeleccionado;
    }

    /**
     * @param mantenimientoSeleccionado the mantenimientoSeleccionado to set
     */
    public void setMantenimientoSeleccionado(List<Mantenimiento> mantenimientoSeleccionado) {
        this.mantenimientoSeleccionado = mantenimientoSeleccionado;
    }

    /**
     * @return the vehiculoSeleccionado
     */
    public Vehiculo getVehiculoSeleccionado() {
        return vehiculoSeleccionado;
    }

    /**
     * @param vehiculoSeleccionado the vehiculoSeleccionado to set
     */
    public void setVehiculoSeleccionado(Vehiculo vehiculoSeleccionado) {
        this.vehiculoSeleccionado = vehiculoSeleccionado;
    }

    /**
     * @return the MantenimientoSelected
     */
    public Mantenimiento getMantenimientoSelected() {
        return MantenimientoSelected;
    }

    /**
     * @param MantenimientoSelected the MantenimientoSelected to set
     */
    public void setMantenimientoSelected(Mantenimiento MantenimientoSelected) {
        this.MantenimientoSelected = MantenimientoSelected;
    }

    public void autorizarMantenimiento() {
        String usuario = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        Usuario usr = this.usuarioFacade.getUsuario(usuario);
        MantenimientoSelected.setIdAutorizacion(usr.getIdEmpleado());
        this.mantenimientoFacade.edit(MantenimientoSelected);

        borrarVariables();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "El mantenimiento fue registrado exitosamente"));

    }

    public boolean puedePresupuestar() {
        return MantenimientoSelected != null && MantenimientoSelected.getPresupuesto() == null;
    }

    public boolean puedeAutorizar() {
        return (MantenimientoSelected != null) && (MantenimientoSelected.getPresupuesto() != null) && (MantenimientoSelected.getIdEgreso() == null) && (MantenimientoSelected.getIdAutorizacion() == null);
    }

    public boolean puedeRegistrarPago() {
        return (MantenimientoSelected != null) && (MantenimientoSelected.getPresupuesto() != null) && (MantenimientoSelected.getIdAutorizacion() != null) && (MantenimientoSelected.getIdEgreso() == null);
    }

}
