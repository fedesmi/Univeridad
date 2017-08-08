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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fmichel
 */
@Entity
@Cacheable(false)
@Table(name = "desperfecto", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Desperfecto.findAll", query = "SELECT d FROM Desperfecto d")
    , @NamedQuery(name = "Desperfecto.findById", query = "SELECT d FROM Desperfecto d WHERE d.id = :id")
    , @NamedQuery(name = "Desperfecto.findByFecha", query = "SELECT d FROM Desperfecto d WHERE d.fecha = :fecha")
    , @NamedQuery(name = "Desperfecto.findByIdVehiculo", query = "SELECT d FROM Desperfecto d WHERE d.idVehiculo.id = :idVehiculo")
    , @NamedQuery(name = "Desperfecto.findByDescripcion", query = "SELECT d FROM Desperfecto d WHERE d.descripcion = :descripcion")})
public class Desperfecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 255)
    @Column(name = "descripcion", length = 255)
    private String descripcion;
    @JoinColumn(name = "id_solicitudRep", referencedColumnName = "id")
    @ManyToOne
    private SolicitudReparacion idsolicitudRep;
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id")
    @ManyToOne
    private Vehiculo idVehiculo;

    public Desperfecto() {
    }

    public Desperfecto(Integer id) {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public SolicitudReparacion getIdsolicitudRep() {
        return idsolicitudRep;
    }

    public void setIdsolicitudRep(SolicitudReparacion idsolicitudRep) {
        this.idsolicitudRep = idsolicitudRep;
    }

    public Vehiculo getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Vehiculo idVehiculo) {
        this.idVehiculo = idVehiculo;
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
        if (!(object instanceof Desperfecto)) {
            return false;
        }
        Desperfecto other = (Desperfecto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.Desperfecto[ id=" + id + " ]";
    }
    
}
