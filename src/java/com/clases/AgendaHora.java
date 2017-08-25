/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clases;

import java.util.List;

/**
 *
 * @author fmichel
 */
public class AgendaHora {
    
    private int idHorario;
    private List<EmpleadoAgenda> empleadoAgenda;

    /**
     * @return the idHorario
     */
    public int getIdHorario() {
        return idHorario;
    }

    /**
     * @param idHorario the idHorario to set
     */
    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    /**
     * @return the empleadoAgenda
     */
    public List<EmpleadoAgenda> getEmpleadoAgenda() {
        return empleadoAgenda;
    }

    /**
     * @param empleadoAgenda the empleadoAgenda to set
     */
    public void setEmpleadoAgenda(List<EmpleadoAgenda> empleadoAgenda) {
        this.empleadoAgenda = empleadoAgenda;
    }
    
    
    
}
