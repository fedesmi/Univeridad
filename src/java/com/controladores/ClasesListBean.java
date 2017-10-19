/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Clase;
import com.entidades.FormaPago;
import com.repositorios.ClaseFacade;
import com.repositorios.FormaPagoFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author fmichel
 */
@Named(value = "clasesListBean")
@SessionScoped
public class ClasesListBean implements Serializable {

    private List<Clase> clases;
    private List<Clase> clasesImpagas;
    private List<FormaPago> formasDePago;
    private FormaPago formaDePago;
    private Clase claseSeleccionada;

    @Inject
    private FormaPagoFacade formaPagoFacade;

    private boolean todasLasClases;
    private Date fechaConsulta;
    @Inject
    private ClaseFacade claseFacade;

    /**
     * Creates a new instance of ClasesListBean
     */
    public ClasesListBean() {
    }

    public void onload() {
        actualizarListaClases();

    }

    public void onloadImpagas() {
        actualizarListaClasesImpagas();
        formasDePago = formaPagoFacade.findAll();
        
    }

    public void actualizarListaClases() {
        if (todasLasClases) {
            clases = claseFacade.findAll();
        } else {
            clases = claseFacade.getClasesByFecha(fechaConsulta);
        }
    }

    /**
     * @return the clases
     */
    public List<Clase> getClases() {
        return clases;
    }

    /**
     * @param clases the clases to set
     */
    public void setClases(List<Clase> clases) {
        this.clases = clases;
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
     * @return the fechaConsulta
     */
    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    /**
     * @param fechaConsulta the fechaConsulta to set
     */
    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    /**
     * @return the todasLasClases
     */
    public boolean isTodasLasClases() {
        return todasLasClases;
    }

    /**
     * @param todasLasClases the todasLasClases to set
     */
    public void setTodasLasClases(boolean todasLasClases) {
        this.todasLasClases = todasLasClases;
    }

    private void actualizarListaClasesImpagas() {
        clasesImpagas = claseFacade.getClasesImpagas();
    }

    /**
     * @return the clasesImpagas
     */
    public List<Clase> getClasesImpagas() {
        return clasesImpagas;
    }

    /**
     * @param clasesImpagas the clasesImpagas to set
     */
    public void setClasesImpagas(List<Clase> clasesImpagas) {
        this.clasesImpagas = clasesImpagas;
    }

    /**
     * @return the claseSeleccionada
     */
    public Clase getClaseSeleccionada() {
        return claseSeleccionada;
    }

    /**
     * @param claseSeleccionada the claseSeleccionada to set
     */
    public void setClaseSeleccionada(Clase claseSeleccionada) {
        this.claseSeleccionada = claseSeleccionada;
    }

    private void getMedioDePago() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}
