/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fmichel
 */
@Entity
@Cacheable(false)
@Table(name = "concepto_ingreso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConceptoIngreso.findAll", query = "SELECT c FROM ConceptoIngreso c")
    , @NamedQuery(name = "ConceptoIngreso.findById", query = "SELECT c FROM ConceptoIngreso c WHERE c.id = :id")
    , @NamedQuery(name = "ConceptoIngreso.findByDescripcion", query = "SELECT c FROM ConceptoIngreso c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "ConceptoIngreso.findByValor", query = "SELECT c FROM ConceptoIngreso c WHERE c.valor = :valor")})
public class ConceptoIngreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 20)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "valor")
    private Integer valor;
    @OneToMany(mappedBy = "idConcepto")
    private Collection<Ingreso> ingresoCollection;

    public ConceptoIngreso() {
    }

    public ConceptoIngreso(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    @XmlTransient
    public Collection<Ingreso> getIngresoCollection() {
        return ingresoCollection;
    }

    public void setIngresoCollection(Collection<Ingreso> ingresoCollection) {
        this.ingresoCollection = ingresoCollection;
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
        if (!(object instanceof ConceptoIngreso)) {
            return false;
        }
        ConceptoIngreso other = (ConceptoIngreso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.ConceptoIngreso[ id=" + id + " ]";
    }
    
}
