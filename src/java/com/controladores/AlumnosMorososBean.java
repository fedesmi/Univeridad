/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.AlquilerVehiculo;
import com.entidades.Clase;
import com.entidades.FormaPago;
import com.repositorios.AlquilerVehiculoFacade;
import com.repositorios.ClaseFacade;
import com.repositorios.FormaPagoFacade;
import javax.inject.Named;
import java.io.Serializable;
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
    private List<AlquilerVehiculo> alquilerVehiculoList;
    
    private AlquilerVehiculo alquilerVehiculoSeleccionado;
    
    @Inject
    private ClaseFacade claseFacade;

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

    public void onRowSelect(SelectEvent event) {
        System.out.println("hola");
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
     * @param clasesAlumnosMorososSeleccionada the clasesAlumnosMorososSeleccionada to set
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
     * @return the alquilerVehiculoList
     */
    public List<AlquilerVehiculo> getAlquilerVehiculoList() {
        return alquilerVehiculoList;
    }

    /**
     * @param alquilerVehiculoList the alquilerVehiculoList to set
     */
    public void setAlquilerVehiculoList(List<AlquilerVehiculo> alquilerVehiculoList) {
        this.alquilerVehiculoList = alquilerVehiculoList;
    }

    /**
     * @return the alquilerVehiculoSeleccionado
     */
    public AlquilerVehiculo getAlquilerVehiculoSeleccionado() {
        return alquilerVehiculoSeleccionado;
    }

    /**
     * @param alquilerVehiculoSeleccionado the alquilerVehiculoSeleccionado to set
     */
    public void setAlquilerVehiculoSeleccionado(AlquilerVehiculo alquilerVehiculoSeleccionado) {
        this.alquilerVehiculoSeleccionado = alquilerVehiculoSeleccionado;
    }

    

}
