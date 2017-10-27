/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.clases.HorarioCompuestoAlquiler;
import com.repositorios.HorarioFacade;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author fmichel
 */
@Named(value = "alquilerVehiculoBean")
@RequestScoped
public class AlquilerVehiculoBean {
    private List<HorarioCompuestoAlquiler> horarioCompuestoAlquiler;
    
    @Inject
    private HorarioFacade horarioFacade;

     private Date fechaConsulta = new Date();
     
    /**
     * Creates a new instance of AlquilerVehiculoBean
     */
    public AlquilerVehiculoBean() {
    }
    
    public void onload() {

        fechaConsulta = new Date();
        horarioCompuestoAlquiler = horarioFacade.getHorarioVehiculosOcupados(fechaConsulta);

    }
   

    

    /**
     * @return the horarioCompuestoAlquiler
     */
    public List<HorarioCompuestoAlquiler> getHorarioCompuestoAlquiler() {
        return horarioCompuestoAlquiler;
    }

    /**
     * @param horarioCompuestoAlquiler the horarioCompuestoAlquiler to set
     */
    public void setHorarioCompuestoAlquiler(List<HorarioCompuestoAlquiler> horarioCompuestoAlquiler) {
        this.horarioCompuestoAlquiler = horarioCompuestoAlquiler;
    }
    
}
