/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Egreso;
import com.entidades.Ingreso;
import com.repositorios.EgresoFacade;
import com.repositorios.IngresoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
@Named(value = "ingresoEgresoBean")
@SessionScoped
public class ingresoEgresoBean implements Serializable {

    @Inject
    private IngresoFacade ingresoFacade;
    @Inject
    private EgresoFacade egresoFacade;
    private List<Ingreso> ingresosDia;
    private List<Egreso> egresosDia;
    private double totalIngresos;
    private double totalEgresos;

    private Date fechaDesde;
    private Date fechaHasta;

    /**
     * Creates a new instance of ingresoEgresoBean
     */
    public ingresoEgresoBean() {
    }

    public void onLoadIngresosEgresos() {
        fechaDesde = new Date();
        fechaHasta = new Date();

        filtrar();
    }

    /**
     * @return the ingresosDia
     */
    public List<Ingreso> getIngresosDia() {
        return ingresosDia;
    }

    /**
     * @param ingresosDia the ingresosDia to set
     */
    public void setIngresosDia(List<Ingreso> ingresosDia) {
        this.ingresosDia = ingresosDia;
    }

    /**
     * @return the egresosDia
     */
    public List<Egreso> getEgresosDia() {
        return egresosDia;
    }

    /**
     * @param egresosDia the egresosDia to set
     */
    public void setEgresosDia(List<Egreso> egresosDia) {
        this.egresosDia = egresosDia;
    }

    /**
     * @return the totalIngresos
     */
    public double getTotalIngresos() {
        return totalIngresos;
    }

    /**
     * @param totalIngresos the totalIngresos to set
     */
    public void setTotalIngresos(double totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    /**
     * @return the totalEgresos
     */
    public double getTotalEgresos() {
        return totalEgresos;
    }

    /**
     * @param totalEgresos the totalEgresos to set
     */
    public void setTotalEgresos(double totalEgresos) {
        this.totalEgresos = totalEgresos;
    }

    /**
     * @return the fechaDesde
     */
    public Date getFechaDesde() {
        return fechaDesde;
    }

    /**
     * @param fechaDesde the fechaDesde to set
     */
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * @return the fechaHasta
     */
    public Date getFechaHasta() {
        return fechaHasta;
    }

    /**
     * @param fechaHasta the fechaHasta to set
     */
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public void filtrar() {
        Calendar cal= Calendar.getInstance();
        cal.setTime(fechaHasta);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        fechaHasta = cal.getTime();
        cal.setTime(fechaDesde);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        fechaDesde = cal.getTime();
   
        ingresosDia = ingresoFacade.getIngresosByFechas(fechaDesde, fechaHasta);
        egresosDia = egresoFacade.getEgresosByFechas(fechaDesde, fechaHasta);
        setTotalEgresos(egresoFacade.getTotalEgresos(fechaDesde, fechaHasta));
        setTotalIngresos(ingresoFacade.getTotalIngresos(fechaDesde, fechaHasta));
    }

}
