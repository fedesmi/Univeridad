/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Clase;
import com.repositorios.ClaseFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author fmichel
 */
@Named(value = "clasesListBean")
@RequestScoped
public class ClasesListBean {
private List<Clase> clases;
 @Inject
    private ClaseFacade claseFacade;
    /**
     * Creates a new instance of ClasesListBean
     */
    public ClasesListBean() {
    }
    
    @PostConstruct
    public void init() {
        actualizarListaClases();
               
    }

    private void actualizarListaClases() {
        clases = claseFacade.findAll();
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
}
