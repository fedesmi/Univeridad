/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clases;

import com.entidades.Empleado;
import java.util.List;

/**
 *
 * @author fmichel
 */
class EmpleadoAgenda {
    private Empleado empleado;
    private List<AgendaHora> agendaDia;
    

    /**
     * @return the empleadok
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

     /**
     * @return the agendadia
     */
    public List<AgendaHora> getAgendadia() {
        return agendaDia;
    }

    /**
     * @param agendadia the agendadia to set
     */
    public void setAgendadia(List<AgendaHora> agendadia) {
        this.agendaDia = agendadia;
    }
}
