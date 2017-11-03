/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fmichel
 */
@Entity
@Table(name = "item_recibo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemRecibo.findAll", query = "SELECT i FROM ItemRecibo i")
    , @NamedQuery(name = "ItemRecibo.findById", query = "SELECT i FROM ItemRecibo i WHERE i.id = :id")
    , @NamedQuery(name = "ItemRecibo.findByItem", query = "SELECT i FROM ItemRecibo i WHERE i.item = :item")
    , @NamedQuery(name = "ItemRecibo.findByPorcentaje", query = "SELECT i FROM ItemRecibo i WHERE i.porcentaje = :porcentaje")})
public class ItemRecibo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "item")
    private String item;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje")
    private float porcentaje;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idItem")
    private Collection<ReciboSueldo> reciboSueldoCollection;

    public ItemRecibo() {
    }

    public ItemRecibo(Integer id) {
        this.id = id;
    }

    public ItemRecibo(Integer id, String item, float porcentaje) {
        this.id = id;
        this.item = item;
        this.porcentaje = porcentaje;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    @XmlTransient
    public Collection<ReciboSueldo> getReciboSueldoCollection() {
        return reciboSueldoCollection;
    }

    public void setReciboSueldoCollection(Collection<ReciboSueldo> reciboSueldoCollection) {
        this.reciboSueldoCollection = reciboSueldoCollection;
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
        if (!(object instanceof ItemRecibo)) {
            return false;
        }
        ItemRecibo other = (ItemRecibo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.ItemRecibo[ id=" + id + " ]";
    }
    
}
