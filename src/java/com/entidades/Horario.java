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
import javax.persistence.OneToMany;
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
@Table(name = "horario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h")
    , @NamedQuery(name = "Horario.findById", query = "SELECT h FROM Horario h WHERE h.id = :id")
    , @NamedQuery(name = "Horario.findByInicio", query = "SELECT h FROM Horario h WHERE h.inicio = :inicio")
    , @NamedQuery(name = "Horario.findByFin", query = "SELECT h FROM Horario h WHERE h.fin = :fin")
     
    , @NamedQuery(name = "Horario.findByDiaSemana", query = "SELECT h FROM Horario h WHERE h.diaSemana = :diaSemana")
  
  
}) 
public class Horario implements Serializable {

    @OneToMany(mappedBy = "idHorario")
    private Collection<AlquilerVehiculo> alquilerVehiculoCollection;

    @OneToMany(mappedBy = "idHorario")
    private Collection<ListaEsperaClase> listaEsperaClaseCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inicio")
    @Temporal(TemporalType.TIME)
    private Date inicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fin")
    @Temporal(TemporalType.TIME)
    private Date fin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dia_semana")
    private short diaSemana;
    
    @OneToMany(mappedBy = "idHorario")
    private Collection<Clase> claseCollection;

    

    
    public Horario() {
    }

    public Horario(Integer id) {
        this.id = id;
    }

    public Horario(Integer id, Date inicio, Date fin, short diaSemana) {
        this.id = id;
        this.inicio = inicio;
        this.fin = fin;
        this.diaSemana = diaSemana;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public short getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(short diaSemana) {
        this.diaSemana = diaSemana;
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
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.Horario[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<ListaEsperaClase> getListaEsperaClaseCollection() {
        return listaEsperaClaseCollection;
    }

    public void setListaEsperaClaseCollection(Collection<ListaEsperaClase> listaEsperaClaseCollection) {
        this.listaEsperaClaseCollection = listaEsperaClaseCollection;
    }

    @XmlTransient
    public Collection<AlquilerVehiculo> getAlquilerVehiculoCollection() {
        return alquilerVehiculoCollection;
    }

    public void setAlquilerVehiculoCollection(Collection<AlquilerVehiculo> alquilerVehiculoCollection) {
        this.alquilerVehiculoCollection = alquilerVehiculoCollection;
    }
    
}
