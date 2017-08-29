/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import com.clases.EmpleadoAgenda;
import com.clases.AgendaHora;
import com.entidades.Horario;
import com.repositorios.ClaseFacade;
import com.repositorios.EmpleadoFacade;
import com.repositorios.HorarioFacade;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;

/**
 *
 * @author fmichel
 */
@Named(value = "claseBean")
@RequestScoped
public class ClaseBean implements Serializable {

    
    private EmpleadoFacade empleadoFacade;
    private HorarioFacade horarioFacade;
    private ClaseFacade claseFacade;
    private Date fechaConsulta;
    private List<AgendaHora> disponibilidadHora;

    /**
     * Creates a new instance of ClaseBean
     */
    public ClaseBean() {
    }
    
     // This is the required method to get the datatable list.
    @PostConstruct
    public void init() {
        //setDisponibilidadHora(getDisponibilidadHorariaPorFecha());

    }

  

    public List<AgendaHora> getDisponibilidadHorariaPorFecha() {
        fechaConsulta = new Date();
        List<AgendaHora> agendaHoraList = new ArrayList<>();
        if (fechaConsulta!=null) {
            //TRAER HORARIOS EN FECHA
            
            List<Horario> horarios = horarioFacade.getHorariosByDiaSemana(fechaConsulta);
            
            List<Horario> horariosOcupados = horarioFacade.getHorariosOcupados(fechaConsulta);

            boolean disponible;

            for (Horario horario : horarios) {
                if (horariosOcupados.contains(horario)) {
                    disponible = false;
                } else {
                    disponible = true;
                }

                agendaHoraList.add(new AgendaHora(horario.getId(), horario.getInicio().toString() + "-" + horario.getFin().toString(), disponible));
            }
        }
        return agendaHoraList;

    }

    /**
     * @return the empleadoFacade
     */
    public EmpleadoFacade getEmpleadoFacade() {
        return empleadoFacade;
    }

    /**
     * @param empleadoFacade the empleadoFacade to set
     */
    public void setEmpleadoFacade(EmpleadoFacade empleadoFacade) {
        this.empleadoFacade = empleadoFacade;
    }

    /**
     * @return the claseFacade
     */
    public ClaseFacade getClaseFacade() {
        return claseFacade;
    }

    /**
     * @param claseFacade the claseFacade to set
     */
    public void setClaseFacade(ClaseFacade claseFacade) {
        this.claseFacade = claseFacade;
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
     * @return the fechaConsulta
     */
    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    /**
     * @param fechaConsulta the fechaConsulta to set
     */
    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    /**
     * @return the disponibilidadHora
     */
    public List<AgendaHora> getDisponibilidadHora() {
        return disponibilidadHora;
    }

    /**
     * @param disponibilidadHora the disponibilidadHora to set
     */
    public void setDisponibilidadHora(List<AgendaHora> disponibilidadHora) {
        this.disponibilidadHora = disponibilidadHora;
    }

}
