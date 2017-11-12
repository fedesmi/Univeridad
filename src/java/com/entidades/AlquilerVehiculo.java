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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Cacheable(false)
@Table(name = "alquiler_vehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AlquilerVehiculo.findAll", query = "SELECT a FROM AlquilerVehiculo a")
    , @NamedQuery(name = "AlquilerVehiculo.findById", query = "SELECT a FROM AlquilerVehiculo a WHERE a.id = :id")
    ,@NamedQuery(name = "AlquilerVehiculo.findAlquileresImpagosAlumnos", query = "SELECT a FROM AlquilerVehiculo a WHERE a.idIngreso IS NULL GROUP BY a.idAlumno")
    , @NamedQuery(name = "AlquilerVehiculo.findAlquileresImpagos", query = "SELECT a FROM AlquilerVehiculo a  WHERE a.idAlumno = :alumno AND a.idIngreso IS NULL")
    , @NamedQuery(name = "AlquilerVehiculo.findByFecha", query = "SELECT a FROM AlquilerVehiculo a WHERE a.fecha = :fecha")
    , @NamedQuery(name = "AlquilerVehiculo.findByFechaYHorario", query = "SELECT a FROM AlquilerVehiculo a WHERE a.fecha = :fecha AND a.idHorario = :horario AND a.fechaCancelado IS NULL ")
         , @NamedQuery(name = "AlquilerVehiculo.cancelarAlquiler", query = "UPDATE AlquilerVehiculo av SET av.fechaCancelado = FUNC('CURDATE()')  WHERE av = :alquiler")
})

public class AlquilerVehiculo implements Serializable {

    @Column(name = "fecha_cancelado")
    @Temporal(TemporalType.DATE)
    private Date fechaCancelado;

    @JoinColumn(name = "id_alumno", referencedColumnName = "id")
    @ManyToOne
    private Alumno idAlumno;
    @JoinColumn(name = "id_horario", referencedColumnName = "id")
    @ManyToOne
    private Horario idHorario;

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
    @JoinColumn(name = "id_ingreso", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Ingreso idIngreso;
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Vehiculo idVehiculo;

    public AlquilerVehiculo() {
    }

    public AlquilerVehiculo(Integer id) {
        this.id = id;
    }

    public AlquilerVehiculo(Integer id, Date fecha) {
        this.id = id;
        this.fecha = fecha;
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

    public Ingreso getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(Ingreso idIngreso) {
        this.idIngreso = idIngreso;
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
        if (!(object instanceof AlquilerVehiculo)) {
            return false;
        }
        AlquilerVehiculo other = (AlquilerVehiculo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.AlquilerVehiculo[ id=" + id + " ]";
    }

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Horario getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Horario idHorario) {
        this.idHorario = idHorario;
    }

    public Date getFechaCancelado() {
        return fechaCancelado;
    }

    public void setFechaCancelado(Date fechaCancelado) {
        this.fechaCancelado = fechaCancelado;
    }
    
}
