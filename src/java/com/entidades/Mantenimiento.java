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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "mantenimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mantenimiento.findAll", query = "SELECT m FROM Mantenimiento m")
    , @NamedQuery(name = "Mantenimiento.findById", query = "SELECT m FROM Mantenimiento m WHERE m.id = :id")
    , @NamedQuery(name = "Mantenimiento.findByFecha", query = "SELECT m FROM Mantenimiento m WHERE m.fecha = :fecha")
    , @NamedQuery(name = "Mantenimiento.findByLugar", query = "SELECT m FROM Mantenimiento m WHERE m.lugar = :lugar")
    , @NamedQuery(name = "Mantenimiento.findByIdVehiculo", query = "SELECT d FROM Mantenimiento d WHERE d.idVehiculo = :idVehiculo")
    , @NamedQuery(name = "Mantenimiento.findByPresupuesto", query = "SELECT m FROM Mantenimiento m WHERE m.presupuesto = :presupuesto")
    , @NamedQuery(name = "Mantenimiento.findByFactura", query = "SELECT m FROM Mantenimiento m WHERE m.factura = :factura")
    , @NamedQuery(name = "Mantenimiento.findByKilometraje", query = "SELECT m FROM Mantenimiento m WHERE m.kilometraje = :kilometraje")
    , @NamedQuery(name = "Mantenimiento.findByDescripcion", query = "SELECT m FROM Mantenimiento m WHERE m.descripcion = :descripcion")})
public class Mantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 50)
    @Column(name = "lugar")
    private String lugar;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "presupuesto")
    private Float presupuesto;
    @Size(max = 20)
    @Column(name = "factura")
    private String factura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kilometraje")
    private int kilometraje;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "id_egreso", referencedColumnName = "id")
    @ManyToOne
    private Egreso idEgreso;
    @JoinColumn(name = "id_autorizacion", referencedColumnName = "id")
    @ManyToOne
    private Empleado idAutorizacion;
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Vehiculo idVehiculo;

    public Mantenimiento() {
    }

    public Mantenimiento(Integer id) {
        this.id = id;
    }

    public Mantenimiento(Integer id, int kilometraje, String descripcion) {
        this.id = id;
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

    public Float getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Float presupuesto) {
        this.presupuesto = presupuesto;
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

    public Egreso getIdEgreso() {
        return idEgreso;
    }

    public void setIdEgreso(Egreso idEgreso) {
        this.idEgreso = idEgreso;
    }

    public Empleado getIdAutorizacion() {
        return idAutorizacion;
    }

    public void setIdAutorizacion(Empleado idAutorizacion) {
        this.idAutorizacion = idAutorizacion;
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
    
}
