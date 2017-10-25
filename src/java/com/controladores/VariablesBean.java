/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Concepto;
import com.repositorios.ConceptoFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author fmichel
 */
@Named(value = "variablesBean")
@RequestScoped
public class VariablesBean {

     @Inject
    private ConceptoFacade conceptoFacade;
     
    private List<Concepto> conceptos;
    /**
     * Creates a new instance of VariablesBean
     */
    public VariablesBean() {
    }
    
    // This is the required method to get the datatable list.
    @PostConstruct
    public void init() {
        conceptos= conceptoFacade.findAll();
    }


    /**
     * @return the conceptoFacade
     */
    public ConceptoFacade getConceptoFacade() {
        return conceptoFacade;
    }

    /**
     * @param conceptoFacade the conceptoFacade to set
     */
    public void setConceptoFacade(ConceptoFacade conceptoFacade) {
        this.conceptoFacade = conceptoFacade;
    }

    /**
     * @return the conceptos
     */
    public List<Concepto> getConceptos() {
        return conceptos;
    }

    /**
     * @param conceptos the variables to set
     */
    public void setConceptos(List<Concepto> conceptos) {
        this.conceptos = conceptos;
    }
    
        public void onRowEdit(RowEditEvent event)  {
        Concepto var = (Concepto) event.getObject();
        this.conceptoFacade.edit(var);
        FacesMessage msg = new FacesMessage("Concepto Editada", String.valueOf(var.getDescripcion()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        Concepto var = (Concepto) event.getObject();
        FacesMessage msg = new FacesMessage("Edición Cancelada", String.valueOf(var.getDescripcion()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
