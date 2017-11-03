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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fmichel
 */
@Entity
@Table(name = "liquidacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Liquidacion.findAll", query = "SELECT l FROM Liquidacion l")
    , @NamedQuery(name = "Liquidacion.findById", query = "SELECT l FROM Liquidacion l WHERE l.id = :id")
    , @NamedQuery(name = "Liquidacion.findByMes", query = "SELECT l FROM Liquidacion l WHERE l.mes = :mes")
    , @NamedQuery(name = "Liquidacion.findByYear", query = "SELECT l FROM Liquidacion l WHERE l.year = :year")})
public class Liquidacion implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SueldoBase")
    private Float sueldoBase;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLiquidacion")
    private Collection<ReciboSueldo> reciboSueldoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "mes")
    private Integer mes;
    @Column(name = "year")
    private Integer year;
    @JoinColumn(name = "id_egreso", referencedColumnName = "id")
    @ManyToOne
    private Egreso idEgreso;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id")
    @ManyToOne
    private Empleado idEmpleado;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne
    private Usuario idUsuario;

    public Liquidacion() {
    }

    public Liquidacion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Egreso getIdEgreso() {
        return idEgreso;
    }

    public void setIdEgreso(Egreso idEgreso) {
        this.idEgreso = idEgreso;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
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
        if (!(object instanceof Liquidacion)) {
            return false;
        }
        Liquidacion other = (Liquidacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.Liquidacion[ id=" + id + " ]";
    }

    public Float getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(Float sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    @XmlTransient
    public Collection<ReciboSueldo> getReciboSueldoCollection() {
        return reciboSueldoCollection;
    }

    public void setReciboSueldoCollection(Collection<ReciboSueldo> reciboSueldoCollection) {
        this.reciboSueldoCollection = reciboSueldoCollection;
    }
    
}
