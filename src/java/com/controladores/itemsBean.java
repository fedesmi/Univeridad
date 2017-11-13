/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Concepto;
import com.entidades.ItemRecibo;
import com.repositorios.ItemReciboFacade;
import java.io.Serializable;
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
 * @author Usuario
 */
@Named(value = "itemsBean")
@RequestScoped
public class itemsBean implements Serializable {

    @Inject
    private ItemReciboFacade itemReciboFacade;
    private List<ItemRecibo> items;

    /**
     * Creates a new instance of itemsBean
     */
    public itemsBean() {

    }

    @PostConstruct
    public void init() {
        items = itemReciboFacade.findAll();
    }

    public void onload() {

    }

    /**
     * @return the items
     */
    public List<ItemRecibo> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<ItemRecibo> items) {
        this.items = items;
    }

    public void onRowEdit(RowEditEvent event) {
        ItemRecibo var = (ItemRecibo) event.getObject();
        this.itemReciboFacade.edit(var);
        FacesMessage msg = new FacesMessage("Item Editado", String.valueOf(var.getItem()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        ItemRecibo var = (ItemRecibo) event.getObject();
        FacesMessage msg = new FacesMessage("Item Cancelado", String.valueOf(var.getItem()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
