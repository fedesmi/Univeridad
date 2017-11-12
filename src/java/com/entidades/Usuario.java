/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fmichel
 */
@Entity
@Cacheable(false)
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id")
    , @NamedQuery(name = "Usuario.findByUsuario", query = "SELECT u FROM Usuario u WHERE u.usuario = :usuario")
    , @NamedQuery(name = "Usuario.findByClave", query = "SELECT u FROM Usuario u WHERE u.clave = :clave")
    , @NamedQuery(name = "Usuario.findByFechaBaja", query = "SELECT u FROM Usuario u WHERE u.fechaBaja = :fechaBaja")})
public class Usuario implements Serializable {

    @OneToMany(mappedBy = "idUsuario")
    private Collection<Egreso> egresoCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<Liquidacion> liquidacionCollection;

    @JoinColumn(name = "id_rol", referencedColumnName = "id")
    @OneToOne(optional = false)
    private TipoEmpleado idRol;

    

    @ManyToMany(mappedBy = "usuarioCollection")
    private Collection<TipoEmpleado> tipoEmpleadoCollection;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id")
    @OneToOne(cascade= CascadeType.PERSIST, optional = false)
    private Empleado idEmpleado;

    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "clave")
    private String clave;
    @Column(name = "fechaBaja")
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
   
    

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(Integer id, String usuario, String clave) {
        this.id = id;
        this.usuario = usuario;
        this.clave = clave;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.Usuario[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<TipoEmpleado> getTipoEmpleadoCollection() {
        return tipoEmpleadoCollection;
    }

    public void setTipoEmpleadoCollection(Collection<TipoEmpleado> tipoEmpleadoCollection) {
        this.tipoEmpleadoCollection = tipoEmpleadoCollection;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

  

    public void setIdRol(TipoEmpleado idRol) {
        this.idRol = idRol;
    }

    /**
     * @return the idRol
     */
    public TipoEmpleado getIdRol() {
        return idRol;
    }

    @XmlTransient
    public Collection<Egreso> getEgresoCollection() {
        return egresoCollection;
    }

    public void setEgresoCollection(Collection<Egreso> egresoCollection) {
        this.egresoCollection = egresoCollection;
    }

    @XmlTransient
    public Collection<Liquidacion> getLiquidacionCollection() {
        return liquidacionCollection;
    }

    public void setLiquidacionCollection(Collection<Liquidacion> liquidacionCollection) {
        this.liquidacionCollection = liquidacionCollection;
    }

  
    
}
