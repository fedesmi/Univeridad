/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Horario;
import com.repositorios.HorarioFacade;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
@Named(value = "horarioBean")
@RequestScoped
public class HorarioBean implements Serializable {

    @Inject
    private HorarioFacade horarioFacade;

    private List<Horario> horarios;

    /**
     * Creates a new instance of HorarioBean
     */
    public HorarioBean() {
    }

    public void onloadHorarios() {
        horarios = horarioFacade.findAll();
    }

    /**
     * @return the horarioFacade
     */
    public HorarioFacade getHorarioFacade() {
        return horarioFacade;
    }

    /**
     * @param horarioFacade the horarioFacade to set
     */
    public void setHorarioFacade(HorarioFacade horarioFacade) {
        this.horarioFacade = horarioFacade;
    }

    /**
     * @return the horarios
     */
    public List<Horario> getHorarios() {
        return horarios;
    }

    /**
     * @param horarios the horarios to set
     */
    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public String getNombreFromNumberDate(int numero) {
        switch (numero) {
            case 1:
                return "Domingo";
            case 2:
                return "Lunes";
            case 3:
                return "Martes";
            case 4:
                return "Miercoles";
            case 5:
                return "Jueves";
            case 6:
                return "Viernes";
            case 7:
                return "Sabado";
            default:
                break;
        }
        return "";
    }
}
