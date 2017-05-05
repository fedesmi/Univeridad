/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author fmichel
 */
@Entity
@Cacheable(false)
@Table(name = "Empleado")
public class Empleado implements Serializable {

   
    
    @EmbeddedId
    private MiPk id;
    

    @Column(name = "legajo")
    private int legajo;
    @Column(name = "dni")
    private int dni;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "Apellido")
    private String apellido;
    @Column(name = "Telefono")
    private String telefono;
     @Column(name = "FechaBaja")
    private Date fechaBaja;
    
    
    @OneToOne
    @JoinColumn(name = "idTipoEmpleado")
    private Tipo_empleado tipo_empleado;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
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
    public int getLegajo() {
        return legajo;
    }

    /**
     * @param legajo the legajo to set
     */
    public void setLegajo(int legajo) {
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
        return apellido;
    }

    /**
     * @param Apellido the Apellido to set
     */
    public void setApellido(String Apellido) {
        this.apellido = Apellido;
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

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the fechaBaja
     */
    public Date getFechaBaja() {
        return fechaBaja;
    }

    /**
     * @param fechaBaja the fechaBaja to set
     */
    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /**
     * @return the id
     */
    public MiPk getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(MiPk id) {
        this.id = id;
    }

   

}
 @Embeddable
    class MiPk { 
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long legajo;

    @Column(name = "fechaAlta")
    private Date fechaAlta;
}
