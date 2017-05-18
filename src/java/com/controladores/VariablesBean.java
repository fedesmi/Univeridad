/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Variable;
import com.repositorios.VariableFacade;
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
    private VariableFacade variableFacade;
     
    private List<Variable> variables;
    /**
     * Creates a new instance of VariablesBean
     */
    public VariablesBean() {
    }
    
    // This is the required method to get the datatable list.
    @PostConstruct
    public void init() {
        variables= variableFacade.findAll();
    }


    /**
     * @return the variableFacade
     */
    public VariableFacade getVariableFacade() {
        return variableFacade;
    }

    /**
     * @param variableFacade the variableFacade to set
     */
    public void setVariableFacade(VariableFacade variableFacade) {
        this.variableFacade = variableFacade;
    }

    /**
     * @return the variables
     */
    public List<Variable> getVariables() {
        return variables;
    }

    /**
     * @param variables the variables to set
     */
    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }
    
        public void onRowEdit(RowEditEvent event)  {
        Variable var = (Variable) event.getObject();
        this.variableFacade.edit(var);
        //GuardarEdicion(empVar);
        FacesMessage msg = new FacesMessage("Variable Editada", String.valueOf(var.getDescripcion()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        Variable var = (Variable) event.getObject();
        FacesMessage msg = new FacesMessage("Edición Cancelada", String.valueOf(var.getDescripcion()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
