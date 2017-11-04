/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author fmichel
 */
@Named(value = "liquidacionSueldosBean")
@SessionScoped
public class LiquidacionSueldosBean implements Serializable {
    
    
    private int mesSeleccionado;
    private int yearSeleccionado=Calendar.getInstance().get(Calendar.YEAR);

    /**
     * Creates a new instance of LiquidacionSueldosBean
     */
    public LiquidacionSueldosBean() {
        System.out.println("anio "+yearSeleccionado);
    }

    /**
     * @return the mesSeleccionado
     */
    public int getMesSeleccionado() {
        return mesSeleccionado;
    }

    /**
     * @param mesSeleccionado the mesSeleccionado to set
     */
    public void setMesSeleccionado(int mesSeleccionado) {
        this.mesSeleccionado = mesSeleccionado;
    }

    /**
     * @return the yearSeleccionado
     */
    public int getYearSeleccionado() {
        return yearSeleccionado;
    }

    /**
     * @param yearSeleccionado the yearSeleccionado to set
     */
    public void setYearSeleccionado(int yearSeleccionado) {
        this.yearSeleccionado = yearSeleccionado;
    }
    
}
