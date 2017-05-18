/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author fmichel
 */
@Entity
@Table(name = "solicitud_reparacion")
@NamedQueries({
    @NamedQuery(name = "SolicitudReparacion.findAll", query = "SELECT s FROM SolicitudReparacion s")
    , @NamedQuery(name = "SolicitudReparacion.findById", query = "SELECT s FROM SolicitudReparacion s WHERE s.id = :id")
    , @NamedQuery(name = "SolicitudReparacion.findByFecha", query = "SELECT s FROM SolicitudReparacion s WHERE s.fecha = :fecha")
    , @NamedQuery(name = "SolicitudReparacion.findByDescripcion", query = "SELECT s FROM SolicitudReparacion s WHERE s.descripcion = :descripcion")
    , @NamedQuery(name = "SolicitudReparacion.findByAutorizado", query = "SELECT s FROM SolicitudReparacion s WHERE s.autorizado = :autorizado")})
public class SolicitudReparacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "autorizado")
    private int autorizado;
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Vehiculo idVehiculo;

    public SolicitudReparacion() {
    }

    public SolicitudReparacion(Integer id) {
        this.id = id;
    }

    public SolicitudReparacion(Integer id, Date fecha, String descripcion, int autorizado) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.autorizado = autorizado;
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

    public int getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(int autorizado) {
        this.autorizado = autorizado;
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
        if (!(object instanceof SolicitudReparacion)) {
            return false;
        }
        SolicitudReparacion other = (SolicitudReparacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.SolicitudReparacion[ id=" + id + " ]";
    }
    
}
