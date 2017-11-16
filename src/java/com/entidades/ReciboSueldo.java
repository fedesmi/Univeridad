/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Cacheable(false)
@Table(name = "recibo_sueldo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReciboSueldo.findAll", query = "SELECT r FROM ReciboSueldo r")
    , @NamedQuery(name = "ReciboSueldo.findById", query = "SELECT r FROM ReciboSueldo r WHERE r.id = :id")
    , @NamedQuery(name = "ReciboSueldo.findByUnidades", query = "SELECT r FROM ReciboSueldo r WHERE r.unidades = :unidades")})
public class ReciboSueldo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "unidades")
    private int unidades;
    @JoinColumn(name = "id_item", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemRecibo idItem;
    @JoinColumn(name = "id_liquidacion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Liquidacion idLiquidacion;
     @Column(name = "monto")
    private Float monto;

    public ReciboSueldo() {
    }

    public ReciboSueldo(Integer id) {
        this.id = id;
    }

    public ReciboSueldo(Integer id, int unidades) {
        this.id = id;
        this.unidades = unidades;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public ItemRecibo getIdItem() {
        return idItem;
    }

    public void setIdItem(ItemRecibo idItem) {
        this.idItem = idItem;
    }

    public Liquidacion getIdLiquidacion() {
        return idLiquidacion;
    }

    public void setIdLiquidacion(Liquidacion idLiquidacion) {
        this.idLiquidacion = idLiquidacion;
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
        if (!(object instanceof ReciboSueldo)) {
            return false;
        }
        ReciboSueldo other = (ReciboSueldo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.ReciboSueldo[ id=" + id + " ]";
    }

    /**
     * @return the monto
     */
    public Float getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(Float monto) {
        this.monto = monto;
    }
    
}
