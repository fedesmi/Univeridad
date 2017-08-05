/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fmichel
 */
@Entity
@Table(name = "tipo_empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEmpleado.findAll", query = "SELECT t FROM TipoEmpleado t")
    , @NamedQuery(name = "TipoEmpleado.findById", query = "SELECT t FROM TipoEmpleado t WHERE t.id = :id")
    , @NamedQuery(name = "TipoEmpleado.findByRol", query = "SELECT t FROM TipoEmpleado t WHERE t.rol = :rol")
    , @NamedQuery(name = "TipoEmpleado.findBySueldoBase", query = "SELECT t FROM TipoEmpleado t WHERE t.sueldoBase = :sueldoBase")
    , @NamedQuery(name = "TipoEmpleado.findByPorcentajePorClase", query = "SELECT t FROM TipoEmpleado t WHERE t.porcentajePorClase = :porcentajePorClase")})
public class TipoEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 20)
    @Column(name = "rol")
    private String rol;
    @Column(name = "sueldoBase")
    private Integer sueldoBase;
    @Column(name = "porcentajePorClase")
    private Short porcentajePorClase;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoEmpleado")
    private Collection<Empleado> empleadoCollection;

    public TipoEmpleado() {
    }

    public TipoEmpleado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(Integer sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public Short getPorcentajePorClase() {
        return porcentajePorClase;
    }

    public void setPorcentajePorClase(Short porcentajePorClase) {
        this.porcentajePorClase = porcentajePorClase;
    }

    @XmlTransient
    public Collection<Empleado> getEmpleadoCollection() {
        return empleadoCollection;
    }

    public void setEmpleadoCollection(Collection<Empleado> empleadoCollection) {
        this.empleadoCollection = empleadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEmpleado)) {
            return false;
        }
        TipoEmpleado other = (TipoEmpleado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.TipoEmpleado[ id=" + id + " ]";
    }
    
}
