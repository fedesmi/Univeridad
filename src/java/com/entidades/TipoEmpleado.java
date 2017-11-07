/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Cacheable(false)
@Table(name = "tipo_empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEmpleado.findAll", query = "SELECT t FROM TipoEmpleado t")
    , @NamedQuery(name = "TipoEmpleado.findById", query = "SELECT t FROM TipoEmpleado t WHERE t.id = :id")
    , @NamedQuery(name = "TipoEmpleado.findByRol", query = "SELECT t FROM TipoEmpleado t WHERE t.rol = :rol")
    , @NamedQuery(name = "TipoEmpleado.findBySueldoBase", query = "SELECT t FROM TipoEmpleado t WHERE t.sueldoBase = :sueldoBase")
})
public class TipoEmpleado implements Serializable {

    @ManyToMany(mappedBy = "tipoEmpleadoCollection")
    private Collection<ItemRecibo> itemReciboCollection;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sueldoBase")
    private Float sueldoBase;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRol")
    private Collection<Usuario> usuarioCollection1;

    @JoinTable(name = "usuario_rol", joinColumns = {
        @JoinColumn(name = "idRol", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "idUsuario", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Usuario> usuarioCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 20)
    @Column(name = "rol")
    private String rol;
   
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

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection1() {
        return usuarioCollection1;
    }

    public void setUsuarioCollection1(Collection<Usuario> usuarioCollection1) {
        this.usuarioCollection1 = usuarioCollection1;
    }

    public Float getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(Float sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    @XmlTransient
    public Collection<ItemRecibo> getItemReciboCollection() {
        return itemReciboCollection;
    }

    public void setItemReciboCollection(Collection<ItemRecibo> itemReciboCollection) {
        this.itemReciboCollection = itemReciboCollection;
    }
    
}
