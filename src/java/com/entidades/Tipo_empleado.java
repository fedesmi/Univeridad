/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author fmichel
 */
@Entity
@Cacheable(false)
public class Tipo_empleado implements Serializable {

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the sueldoBase
     */
    public int getSueldoBase() {
        return sueldoBase;
    }

    /**
     * @param sueldoBase the sueldoBase to set
     */
    public void setSueldoBase(int sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    /**
     * @return the porcentajePorClase
     */
    public int getPorcentajePorClase() {
        return porcentajePorClase;
    }

    /**
     * @param porcentajePorClase the porcentajePorClase to set
     */
    public void setPorcentajePorClase(int porcentajePorClase) {
        this.porcentajePorClase = porcentajePorClase;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String descripcion;
    private int sueldoBase;
    private int porcentajePorClase;

    @OneToOne(mappedBy = "tipo_empleado")
    private Empleado empleado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipo_empleado)) {
            return false;
        }
        Tipo_empleado other = (Tipo_empleado) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.tipo_empleado[ id=" + id + " ]";
    }

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

}
