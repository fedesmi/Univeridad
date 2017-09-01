/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clases;

import com.entidades.Horario;

/**
 *
 * @author fmichel
 */
public class HorarioCompuesto {
    
    private Horario horario;
    private int cantidadOcupado;
    private int cantidadInstructores;

    /**
     * @return the horario
     */
    public Horario getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    /**
     * @return the cantidadOcupado
     */
    public int getCantidadOcupado() {
        return cantidadOcupado;
    }

    /**
     * @param cantidadOcupado the cantidadOcupado to set
     */
    public void setCantidadOcupado(int cantidadOcupado) {
        this.cantidadOcupado = cantidadOcupado;
    }

    /**
     * @return the cantidadInstructores
     */
    public int getCantidadInstructores() {
        return cantidadInstructores;
    }

    /**
     * @param cantidadInstructores the cantidadInstructores to set
     */
    public void setCantidadInstructores(int cantidadInstructores) {
        this.cantidadInstructores = cantidadInstructores;
    }
    
}
