/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Empleado;
import com.entidades.Liquidacion;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author fmichel
 */
@Named(value = "recibosBean")
@SessionScoped
public class RecibosBean implements Serializable{
private Empleado empleadoSeleccionado;
private Liquidacion liquidacionSeleccionado;

    /**
     * Creates a new instance of RecibosBean
     */
    public RecibosBean() {
    }

    /**
     * @return the empleadoSeleccionado
     */
    public Empleado getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    /**
     * @param empleadoSeleccionado the empleadoSeleccionado to set
     */
    public void setEmpleadoSeleccionado(Empleado empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }

    /**
     * @return the liquidacionSeleccionado
     */
    public Liquidacion getLiquidacionSeleccionado() {
        return liquidacionSeleccionado;
    }

    /**
     * @param liquidacionSeleccionado the liquidacionSeleccionado to set
     */
    public void setLiquidacionSeleccionado(Liquidacion liquidacionSeleccionado) {
        this.liquidacionSeleccionado = liquidacionSeleccionado;
    }
    
}
