/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clases;

import com.entidades.Empleado;

/**
 *
 * @author fmichel
 */
class EmpleadoAgenda {
    private Empleado empleado;
    private int Ocupado;

    /**
     * @return the empleado
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
     * @return the Ocupado
     */
    public int getOcupado() {
        return Ocupado;
    }

    /**
     * @param Ocupado the Ocupado to set
     */
    public void setOcupado(int Ocupado) {
        this.Ocupado = Ocupado;
    }
}
