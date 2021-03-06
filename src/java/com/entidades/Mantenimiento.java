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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fmichel
 */
@Entity
@Cacheable(false)
@Table(name = "mantenimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mantenimiento.findAll", query = "SELECT m FROM Mantenimiento m")
    , @NamedQuery(name = "Mantenimiento.findById", query = "SELECT m FROM Mantenimiento m WHERE m.id = :id")
    , @NamedQuery(name = "Mantenimiento.findByFecha", query = "SELECT m FROM Mantenimiento m WHERE m.fecha = :fecha")
    , @NamedQuery(name = "Mantenimiento.findByLugar", query = "SELECT m FROM Mantenimiento m WHERE m.lugar = :lugar")
    , @NamedQuery(name = "Mantenimiento.findByValor", query = "SELECT m FROM Mantenimiento m WHERE m.valor = :valor")
    , @NamedQuery(name = "Mantenimiento.findByFactura", query = "SELECT m FROM Mantenimiento m WHERE m.factura = :factura")
    , @NamedQuery(name = "Mantenimiento.findByKilometraje", query = "SELECT m FROM Mantenimiento m WHERE m.kilometraje = :kilometraje")
    , @NamedQuery(name = "Mantenimiento.findByDescripcion", query = "SELECT m FROM Mantenimiento m WHERE m.descripcion = :descripcion")
    , @NamedQuery(name = "Mantenimiento.findByIdVehiculo", query = "SELECT d FROM Mantenimiento d WHERE d.idVehiculo = :idVehiculo")})
public class Mantenimiento implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "lugar")
    private String lugar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private int valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "factura")
    private String factura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kilometraje")
    private int kilometraje;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "id_vehiculo")
    private Integer idVehiculo;

    public Mantenimiento() {
    }

    public Mantenimiento(Integer id) {
        this.id = id;
    }

    public Mantenimiento(Integer id, Date fecha, String lugar, int valor, String factura, int kilometraje, String descripcion) {
        this.id = id;
        this.fecha = fecha;
        this.lugar = lugar;
        this.valor = valor;
        this.factura = factura;
        this.kilometraje = kilometraje;
        this.descripcion = descripcion;
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

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        if (!(object instanceof Mantenimiento)) {
            return false;
        }
        Mantenimiento other = (Mantenimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.Mantenimiento[ id=" + id + " ]";
    }

    /**
     * @return the idVehiculo
     */
    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    /**
     * @param idVehiculo the idVehiculo to set
     */
    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Egreso getIdEgreso() {
        return idEgreso;
    }

    public void setIdEgreso(Egreso idEgreso) {
        this.idEgreso = idEgreso;
    }


    
}
