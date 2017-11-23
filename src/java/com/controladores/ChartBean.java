/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Usuario
 */
@Named(value = "chartBean")
@SessionScoped
public class ChartBean implements Serializable {

    private PieChartModel pieModel1;
    private BarChartModel barModel;

    /**
     * Creates a new instance of ChartBean
     */
    public ChartBean() {
    }

    public void onLoadCharts() {
        createPieModel1();
        barModel = initBarModel(); 
    }

    private void createPieModel1() {
        pieModel1 = new PieChartModel();

        pieModel1.set("Clases", 100);
        pieModel1.set("Alquileres", 15);

        pieModel1.setTitle("Clases y Alquileres");
        pieModel1.setLegendPosition("w");
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries Horarios = new ChartSeries();
        Horarios.setLabel("Horarios");
        Horarios.set("10hs", 5);
        Horarios.set("11hs", 2);
        Horarios.set("12hs", 1);
        Horarios.set("16hs", 7);
        Horarios.set("17hs", 7);
        Horarios.set("18hs", 6);
        Horarios.set("19hs", 5);
        
        model.addSeries(Horarios);
        model.setTitle("Clases por Horario");
        return model;
    }

    /**
     * @return the pieModel1
     */
    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    /**
     * @param pieModel1 the pieModel1 to set
     */
    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }

    /**
     * @return the barModel
     */
    public BarChartModel getBarModel() {
        return barModel;
    }

    /**
     * @param barModel the barModel to set
     */
    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

}
