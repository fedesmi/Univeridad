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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author fmichel
 */
@Entity
@Cacheable(false)
@Table(name = "Empleado")
public class Empleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long legajo;

    @Column(name = "dni")
    private int dni;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "Apellido")
    private String Apellido;

    @OneToOne
    @JoinColumn(name = "idTipoEmpleado")
    private Tipo_empleado tipo_empleado;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (legajo != null ? legajo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.legajo == null && other.legajo != null) || (this.legajo != null && !this.legajo.equals(other.legajo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.Empleado[ id=" + legajo + " ]";
    }

    /**
     * @return the legajo
     */
    public Long getLegajo() {
        return legajo;
    }

    /**
     * @param legajo the legajo to set
     */
    public void setLegajo(Long legajo) {
        this.legajo = legajo;
    }

    /**
     * @return the dni
     */
    public int getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(int dni) {
        this.dni = dni;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the Apellido
     */
    public String getApellido() {
        return Apellido;
    }

    /**
     * @param Apellido the Apellido to set
     */
    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    /**
     * @return the tipo_empleado
     */
    public Tipo_empleado getTipo_empleado() {
        return tipo_empleado;
    }

    /**
     * @param tipo_empleado the tipo_empleado to set
     */
    public void setTipo_empleado(Tipo_empleado tipo_empleado) {
        this.tipo_empleado = tipo_empleado;
    }

}
