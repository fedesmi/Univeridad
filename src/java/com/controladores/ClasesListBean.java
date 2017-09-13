/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Clase;
import com.repositorios.ClaseFacade;
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

    public void actualizarListaClases() {
         if(todasLasClases){
            clases = claseFacade.findAll();
        }else{
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
}
