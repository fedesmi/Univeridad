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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Cacheable(false)
@Table(name = "ingreso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingreso.findAll", query = "SELECT i FROM Ingreso i")
    , @NamedQuery(name = "Ingreso.findById", query = "SELECT i FROM Ingreso i WHERE i.id = :id")
    , @NamedQuery(name = "Ingreso.findByFecha", query = "SELECT i FROM Ingreso i WHERE i.fecha = :fecha")
    , @NamedQuery(name = "Ingreso.findByMonto", query = "SELECT i FROM Ingreso i WHERE i.monto = :monto")
    , @NamedQuery(name = "Ingreso.findByConcepto", query = "SELECT i FROM Ingreso i WHERE i.concepto = :concepto")})
public class Ingreso implements Serializable {

    @Column(name = "cuotas")
    private Integer cuotas;
    
    @JoinColumn(name = "id_concepto", referencedColumnName = "id")
    @OneToOne
    private ConceptoIngreso idConcepto;
   
    
    @JoinColumn(name = "id_forma_pago", referencedColumnName = "id")
    @OneToOne
    private FormaPago idFormaPago;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "monto")
    private Integer monto;
    @Size(max = 50)
    @Column(name = "concepto")
    private String concepto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIngreso")
    private Collection<AlquilerVehiculo> alquilerVehiculoCollection;
    @OneToMany(mappedBy = "idIngreso")
    private Collection<Clase> claseCollection;

    public Ingreso() {
    }

    public Ingreso(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    @XmlTransient
    public Collection<AlquilerVehiculo> getAlquilerVehiculoCollection() {
        return alquilerVehiculoCollection;
    }

    public void setAlquilerVehiculoCollection(Collection<AlquilerVehiculo> alquilerVehiculoCollection) {
        this.alquilerVehiculoCollection = alquilerVehiculoCollection;
    }

    @XmlTransient
    public Collection<Clase> getClaseCollection() {
        return claseCollection;
    }

    public void setClaseCollection(Collection<Clase> claseCollection) {
        this.claseCollection = claseCollection;
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
        if (!(object instanceof Ingreso)) {
            return false;
        }
        Ingreso other = (Ingreso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.Ingreso[ id=" + id + " ]";
    }

    public Integer getCuotas() {
        return cuotas;
    }

    public void setCuotas(Integer cuotas) {
        this.cuotas = cuotas;
    }

    public ConceptoIngreso getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(ConceptoIngreso idConcepto) {
        this.idConcepto = idConcepto;
    }

    public FormaPago getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(FormaPago idFormaPago) {
        this.idFormaPago = idFormaPago;
    }
    
}
