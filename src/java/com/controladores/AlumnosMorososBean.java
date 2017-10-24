/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.AlquilerVehiculo;
import com.entidades.Clase;
import com.entidades.FormaPago;
import com.entidades.Ingreso;
import com.repositorios.AlquilerVehiculoFacade;
import com.repositorios.ClaseFacade;
import com.repositorios.FormaPagoFacade;
import com.repositorios.IngresoFacade;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author fmichel
 */
@Named(value = "alumnosMorososBean")
@ViewScoped
public class AlumnosMorososBean implements Serializable {

    private List<Clase> clasesAlumnosMorosos;
    private List<Clase> clasesAlumnosMorososSeleccionada;
    private Clase claseAlumnoSeleccionado;

    private int cuotas;
    private double montoFinal = 0;

    private List<AlquilerVehiculo> alquilerVehiculoListSeleccionado;

    @Inject
    private ClaseFacade claseFacade;
    @Inject
    private IngresoFacade ingresoFacade;

    @Inject
    private AlquilerVehiculoFacade alquilerVehiculoFacade;

    @Inject
    private FormaPagoFacade formaPagoFacade;
    private List<FormaPago> formasDePago;
    private FormaPago formaDePago;

    /**
     * Creates a new instance of AlumnosMorososBean
     */
    public AlumnosMorososBean() {
    }

    public void onLoadMorosos() {
        clasesAlumnosMorosos = claseFacade.getClasesImpagasAlumnos();
        formasDePago = formaPagoFacade.findAll();

    }

    /**
     * @return the clasesAlumnosMorosos
     */
    public List<Clase> getClasesAlumnosMorosos() {
        return clasesAlumnosMorosos;
    }

    /**
     * @param clasesAlumnosMorosos the clasesAlumnosMorosos to set
     */
    public void setClasesAlumnosMorosos(List<Clase> clasesAlumnosMorosos) {
        this.clasesAlumnosMorosos = clasesAlumnosMorosos;
    }

    /**
     * @return the claseAlumnoSeleccionado
     */
    public Clase getClaseAlumnoSeleccionado() {
        return claseAlumnoSeleccionado;
    }

    /**
     * @param claseAlumnoSeleccionado the claseAlumnoSeleccionado to set
     */
    public void setClaseAlumnoSeleccionado(Clase claseAlumnoSeleccionado) {
        this.claseAlumnoSeleccionado = claseAlumnoSeleccionado;
    }

    /**
     * @return the claseFacade
     */
    public ClaseFacade getClaseFacade() {
        return claseFacade;
    }

    /**
     * @param claseFacade the claseFacade to set
     */
    public void setClaseFacade(ClaseFacade claseFacade) {
        this.claseFacade = claseFacade;
    }

    /**
     * @return the formaPagoFacade
     */
    public FormaPagoFacade getFormaPagoFacade() {
        return formaPagoFacade;
    }

    /**
     * @param formaPagoFacade the formaPagoFacade to set
     */
    public void setFormaPagoFacade(FormaPagoFacade formaPagoFacade) {
        this.formaPagoFacade = formaPagoFacade;
    }

    /**
     * @return the formasDePago
     */
    public List<FormaPago> getFormasDePago() {
        return formasDePago;
    }

    /**
     * @param formasDePago the formasDePago to set
     */
    public void setFormasDePago(List<FormaPago> formasDePago) {
        this.formasDePago = formasDePago;
    }

    /**
     * @return the formaDePago
     */
    public FormaPago getFormaDePago() {
        return formaDePago;
    }

    /**
     * @param formaDePago the formaDePago to set
     */
    public void setFormaDePago(FormaPago formaDePago) {
        this.formaDePago = formaDePago;
    }

    /**
     * @return the clasesAlumnosMorososSeleccionada
     */
    public List<Clase> getClasesAlumnosMorososSeleccionada() {
        return clasesAlumnosMorososSeleccionada;
    }

    /**
     * @param clasesAlumnosMorososSeleccionada the
     * clasesAlumnosMorososSeleccionada to set
     */
    public void setClasesAlumnosMorososSeleccionada(List<Clase> clasesAlumnosMorososSeleccionada) {
        this.clasesAlumnosMorososSeleccionada = clasesAlumnosMorososSeleccionada;
    }

    /**
     * @return the alquilerVehiculoFacade
     */
    public AlquilerVehiculoFacade getAlquilerVehiculoFacade() {
        return alquilerVehiculoFacade;
    }

    /**
     * @param alquilerVehiculoFacade the alquilerVehiculoFacade to set
     */
    public void setAlquilerVehiculoFacade(AlquilerVehiculoFacade alquilerVehiculoFacade) {
        this.alquilerVehiculoFacade = alquilerVehiculoFacade;
    }

    /**
     * @return the alquilerVehiculoListSeleccionado
     */
    public List<AlquilerVehiculo> getAlquilerVehiculoListSeleccionado() {
        return alquilerVehiculoListSeleccionado;
    }

    /**
     * @param alquilerVehiculoListSeleccionado the
     * alquilerVehiculoListSeleccionado to set
     */
    public void setAlquilerVehiculoListSeleccionado(List<AlquilerVehiculo> alquilerVehiculoListSeleccionado) {
        this.alquilerVehiculoListSeleccionado = alquilerVehiculoListSeleccionado;
    }

    public void calcularMonto() {
        montoFinal = (clasesAlumnosMorososSeleccionada.size() * 400) + (alquilerVehiculoListSeleccionado.size() * 400);
        if (formaDePago.getDescripcion().equals("TARJETA")) {
            montoFinal = montoFinal * 1.1;
        }

    }

    public void efectuarPago() {
        Ingreso ingreso = new Ingreso();
        ingreso.setIdFormaPago(formaDePago);
        ingreso.setCuotas(cuotas);
        ingreso.setMonto(montoFinal);
        ingreso.setFecha(new Date());
        ingreso.setClaseCollection(clasesAlumnosMorososSeleccionada);
        ingreso.setAlquilerVehiculoCollection(alquilerVehiculoListSeleccionado);
        ingresoFacade.create(ingreso);
        for (Clase clasesAlumnosMorososSeleccionada1 : clasesAlumnosMorososSeleccionada) {
            clasesAlumnosMorososSeleccionada1.setIdIngreso(ingreso);
            claseFacade.edit(clasesAlumnosMorososSeleccionada1);
        }
        for (AlquilerVehiculo alquilerVehiculoListSeleccionado1 : alquilerVehiculoListSeleccionado) {
            alquilerVehiculoListSeleccionado1.setIdIngreso(ingreso);
            alquilerVehiculoFacade.edit(alquilerVehiculoListSeleccionado1);
        }
        
        
    }

    /**
     * @return the ingresoFacade
     */
    public IngresoFacade getIngresoFacade() {
        return ingresoFacade;
    }

    /**
     * @param ingresoFacade the ingresoFacade to set
     */
    public void setIngresoFacade(IngresoFacade ingresoFacade) {
        this.ingresoFacade = ingresoFacade;
    }

    /**
     * @return the cuotas
     */
    public int getCuotas() {
        return cuotas;
    }

    /**
     * @param cuotas the cuotas to set
     */
    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    /**
     * @return the montoFinal
     */
    public Double getMontoFinal() {
        return montoFinal;
    }

    /**
     * @param montoFinal the montoFinal to set
     */
    public void setMontoFinal(Double montoFinal) {
        this.montoFinal = montoFinal;
    }

}
