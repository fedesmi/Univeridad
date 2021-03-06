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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fmichel
 */
@Entity
@Cacheable(false)
@Table(name = "solicitud_reparacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudReparacion.findAll", query = "SELECT s FROM SolicitudReparacion s")
    , @NamedQuery(name = "SolicitudReparacion.findById", query = "SELECT s FROM SolicitudReparacion s WHERE s.id = :id")
    , @NamedQuery(name = "SolicitudReparacion.findByFecha", query = "SELECT s FROM SolicitudReparacion s WHERE s.fecha = :fecha")
    , @NamedQuery(name = "SolicitudReparacion.findByAutorizado", query = "SELECT s FROM SolicitudReparacion s WHERE s.autorizado = :autorizado")})
public class SolicitudReparacion implements Serializable {

    @Column(name = "autorizado")
    private Integer autorizado;
    @JoinColumn(name = "id_egreso", referencedColumnName = "id")
    @ManyToOne
    private Egreso idEgreso;


    

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
   

    public SolicitudReparacion() {
    }

    public SolicitudReparacion(Integer id) {
        this.id = id;
    }

    public SolicitudReparacion(Integer id, Date fecha, int autorizado) {
        this.id = id;
        this.fecha = fecha;
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

    public Integer getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(Integer autorizado) {
        this.autorizado = autorizado;
    }

    public Egreso getIdEgreso() {
        return idEgreso;
    }

    public void setIdEgreso(Egreso idEgreso) {
        this.idEgreso = idEgreso;
    }

    



   
    
}
