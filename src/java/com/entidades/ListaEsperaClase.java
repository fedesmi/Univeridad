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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Cacheable(false)
@Table(name = "lista_espera_clase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListaEsperaClase.findAll", query = "SELECT l FROM ListaEsperaClase l")
    , @NamedQuery(name = "ListaEsperaClase.findById", query = "SELECT l FROM ListaEsperaClase l WHERE l.id = :id")
    , @NamedQuery(name = "ListaEsperaClase.findByFechaInscripcion", query = "SELECT l FROM ListaEsperaClase l WHERE l.fechaInscripcion = :fechaInscripcion")
    , @NamedQuery(name = "ListaEsperaClase.findByHorario", query = "SELECT l FROM ListaEsperaClase l WHERE l.idHorario.id = :horarioVar AND l.fechaClase = :fechaClase ")
    , @NamedQuery(name = "ListaEsperaClase.findByFechaClase", query = "SELECT l FROM ListaEsperaClase l WHERE l.fechaClase = :fechaClase")

})
public class ListaEsperaClase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha_inscripcion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInscripcion;
    @Column(name = "fecha_clase")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaClase;
    
    @JoinColumn(name = "id_alumno", referencedColumnName = "id")
    @OneToOne
    private Alumno idAlumno;
    
    @JoinColumn(name = "id_horario", referencedColumnName = "id")
    @OneToOne
    private Horario idHorario;

    public ListaEsperaClase() {
    }

    public ListaEsperaClase(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Date getFechaClase() {
        return fechaClase;
    }

    public void setFechaClase(Date fechaClase) {
        this.fechaClase = fechaClase;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaEsperaClase)) {
            return false;
        }
        ListaEsperaClase other = (ListaEsperaClase) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.ListaEsperaClase[ id=" + id + " ]";
    }
    
}
