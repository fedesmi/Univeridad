/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Empleado;
import com.entidades.Liquidacion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author fmichel
 */
@Named(value = "recibosBean")
@SessionScoped
public class RecibosBean implements Serializable {

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
     * @return the liquidacionSeleccionada
     */
    public Liquidacion getLiquidacionSeleccionado() {
        return liquidacionSeleccionado;
    }

    /**
     * @param liquidacionSeleccionada the liquidacionSeleccionada to set
     */
    public void setLiquidacionSeleccionado(Liquidacion liquidacionSeleccionada) {
        this.liquidacionSeleccionado = liquidacionSeleccionada;
    }

    
    
    
    
    public String getNombreMes(int mes) {

        switch (mes) {
            case 1:
                return "ENERO";
            case 2:
                return "FEBRERO";
            case 3:
                return "MARZO";
            case 4:
                return "ABRIL";
            case 5:
                return "MAYO";
            case 6:
                return "JUNIO";
            case 7:
                return "JULIO";
            case 8:
                return "AGOSTO";
            case 9:
                return "SEPTIEMBRE";
            case 10:
                return "OCTUBRE";
            case 11:
                return "NOVIEMBRE";
            case 12:
                return "DICIEMBRE";

        }
        return ""; 
    }

   
}
