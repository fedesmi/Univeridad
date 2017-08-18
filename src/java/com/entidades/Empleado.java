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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fmichel
 */
@Entity
@Cacheable(false)
@Table(name = "empleado", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
    , @NamedQuery(name = "Empleado.findById", query = "SELECT e FROM Empleado e WHERE e.id = :id")
    , @NamedQuery(name = "Empleado.findByLegajo", query = "SELECT e FROM Empleado e WHERE e.legajo = :legajo")
    , @NamedQuery(name = "Empleado.findByDni", query = "SELECT e FROM Empleado e WHERE e.dni = :dni")
    , @NamedQuery(name = "Empleado.findByNombre", query = "SELECT e FROM Empleado e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Empleado.findByApellido", query = "SELECT e FROM Empleado e WHERE e.apellido = :apellido")
    , @NamedQuery(name = "Empleado.findByTelefono", query = "SELECT e FROM Empleado e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "Empleado.findByFechaAlta", query = "SELECT e FROM Empleado e WHERE e.fechaAlta = :fechaAlta")
    , @NamedQuery(name = "Empleado.findByFechaBaja", query = "SELECT e FROM Empleado e WHERE e.fechaBaja = :fechaBaja")
    , @NamedQuery(name = "Empleado.findByAutorizo", query = "SELECT e FROM Empleado e WHERE e.autorizo = :autorizo")
    , @NamedQuery(name = "Empleado.todosAutorizados",
            query = "SELECT m "
            + "FROM Empleado m "
            + "LEFT JOIN Empleado b "
            + "ON m.legajo = b.legajo "
            + "AND m.fechaAlta < b.fechaAlta "
            + "WHERE b.fechaAlta IS NULL  "
            + "AND m.autorizo IS NOT NULL "
            + "ORDER BY m.legajo ")
    , @NamedQuery(name = "Empleado.todosSinAutorizar",
            query = "SELECT m "
            + "FROM Empleado m "
            + "LEFT JOIN Empleado b "
            + "ON m.legajo = b.legajo "
            + "AND m.fechaAlta < b.fechaAlta "
            + "WHERE b.fechaAlta IS NULL and m.autorizo IS NULL")
    , @NamedQuery(name = "Empleado.ultimoLegajo",
            query = "SELECT MAX(m.legajo)+1 FROM Empleado m")
    ,@NamedQuery(name = "Empleado.autorizarCambio",
            query = "UPDATE Empleado t SET t.autorizo=:legajo WHERE t.id=:id")
    , @NamedQuery(name = "Empleado.instructores",
            query = "SELECT m "
            + "FROM Empleado m "
            + "LEFT JOIN Empleado b "
            + "ON m.legajo = b.legajo "
            + "AND m.fechaAlta < b.fechaAlta "
            + "WHERE b.fechaAlta IS NULL  "
            + "AND m.autorizo IS NOT NULL "
            + "AND m.idTipoEmpleado.rol =:instructor "
            + "ORDER BY m.legajo ")
})


public class Empleado implements Serializable {

    @OneToMany(mappedBy = "idEmpleado")
    private Collection<Vehiculo> vehiculoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "legajo", nullable = false)
    private int legajo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dni", nullable = false)
    private int dni;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nombre", nullable = false, length = 25)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "apellido", nullable = false, length = 25)
    private String apellido;
    @Size(max = 25)
    @Column(name = "telefono", length = 25)
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaAlta", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Column(name = "fechaBaja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;
    @Column(name = "autorizo")
    private Integer autorizo;
    @JoinColumn(name = "idTipoEmpleado", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private TipoEmpleado idTipoEmpleado;

    public Empleado() {
    }

    public Empleado(Integer id) {
        this.id = id;
    }

    public Empleado(Integer id, int legajo, int dni, String nombre, String apellido, Date fechaAlta) {
        this.id = id;
        this.legajo = legajo;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaAlta = fechaAlta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Integer getAutorizo() {
        return autorizo;
    }

    public void setAutorizo(Integer autorizo) {
        this.autorizo = autorizo;
    }

    public TipoEmpleado getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(TipoEmpleado idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
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
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.Empleado[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Vehiculo> getVehiculoCollection() {
        return vehiculoCollection;
    }

    public void setVehiculoCollection(Collection<Vehiculo> vehiculoCollection) {
        this.vehiculoCollection = vehiculoCollection;
    }

 

}
