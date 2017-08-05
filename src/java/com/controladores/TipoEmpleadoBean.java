/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.TipoEmpleado;
import com.repositorios.TipoEmpleadoFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author fmichel
 */
@Named(value = "tipoEmpleadoBean")
@RequestScoped
public class TipoEmpleadoBean {

     private List<TipoEmpleado> tipoDeEmpleados;
     @Inject
    private TipoEmpleadoFacade templeadoFacade;
    /**
     * Creates a new instance of TipoEmpleadoBean
     */
    public TipoEmpleadoBean() {
        
    }
    
     @PostConstruct
    public void init() {
       tipoDeEmpleados = getTipoDeEmpleadosDB();
    }


    /**
     * @return the tipoDeEmpleados
     */
    public List<TipoEmpleado> getTipoDeEmpleados() {
        return tipoDeEmpleados;
    }

    /**
     * @param tipoDeEmpleados the tipoDeEmpleados to set
     */
    public void setTipoDeEmpleados(List<TipoEmpleado> tipoDeEmpleados) {
        this.tipoDeEmpleados = tipoDeEmpleados;
    }

    private List<TipoEmpleado> getTipoDeEmpleadosDB() {
         return this.templeadoFacade.findAll();
    }
    
        public void onRowEdit(RowEditEvent event)  {
        TipoEmpleado tempVar = (TipoEmpleado) event.getObject();
        this.templeadoFacade.edit(tempVar);
        FacesMessage msg = new FacesMessage("Empleado Editado", "Tipo: "+String.valueOf(tempVar.getRol()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        TipoEmpleado tempVar = (TipoEmpleado) event.getObject();
        FacesMessage msg = new FacesMessage("Edición Cancelada",  "Tipo: "+String.valueOf(tempVar.getRol()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
