/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Mantenimiento;
import com.entidades.Vehiculo;
import com.repositorios.MantenimientoFacade;
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
@Named(value = "mantenimientosBean")
@RequestScoped
public class MantenimientoBean implements Serializable {

    @Inject
    private MantenimientoFacade mantenimientoFacade;
    private Mantenimiento mantenimientoVar;

    private Date fecha;
    private String lugar;
    private int valor;
    private String factura;
    private int kilometraje;
    private String descripcion;

    private List<Mantenimiento> mantenimientosDeVehiculo;

    private List<Mantenimiento> mantenimientos;
    private List<Mantenimiento> mantenimientoSeleccionado;

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
     * @param idVehiculo the idVehiculo to set
     */
    public List<Mantenimiento> getMantenimientosDeVehiculo(Vehiculo idVehiculo) {
        return this.mantenimientoFacade.getMantenimientosDeVehiculo(idVehiculo);
    }

    /**
     * @param mantenimientosDeVehiculo the mantenimientosDeVehiculo to set
     */
    public void setMantenimientosDeVehiculo(List<Mantenimiento> mantenimientosDeVehiculo) {
        this.mantenimientosDeVehiculo = mantenimientosDeVehiculo;
    }

    public void guardar(Vehiculo vehiculo) {
        mantenimientoVar.setIdVehiculo(vehiculo);
        mantenimientoVar.setFactura(factura);
        mantenimientoVar.setFecha(fecha);
        mantenimientoVar.setLugar(lugar);
        mantenimientoVar.setDescripcion(descripcion);
        mantenimientoVar.setKilometraje(kilometraje);
        mantenimientoVar.setValor(valor);

        this.mantenimientoFacade.create(mantenimientoVar);

        mantenimientoVar = null;
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
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
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

}
