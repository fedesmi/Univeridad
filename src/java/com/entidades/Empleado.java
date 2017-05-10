/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author fmichel
 */
@Entity
@Cacheable(false)
@Table(name = "Empleado")

@NamedQueries({
    @NamedQuery(name = "Empleado.todosAutorizados",
            query = "SELECT m "
            + "FROM Empleado m "
            + "LEFT JOIN Empleado b "
            + "ON m.legajo = b.legajo "
            + "AND m.autorizo = b.autorizo "
            + "AND m.fechaAlta < b.fechaAlta "
            + "WHERE b.fechaAlta IS NULL  "
            + "AND m.autorizo IS NOT NULL "
            + "ORDER BY m.legajo "
    ),
    @NamedQuery(name = "Empleado.todosSinAutorizar",
            query = "SELECT m "
            + "FROM Empleado m "
            + "LEFT JOIN Empleado b "
            + "ON m.legajo = b.legajo "
            + "AND m.fechaAlta < b.fechaAlta "
            + "WHERE b.fechaAlta IS NULL and m.autorizo IS NULL"
    ),

    @NamedQuery(name = "Empleado.ultimoLegajo",
            query = "SELECT MAX(m.legajo)+1 FROM Empleado m"
    ),
})


public class Empleado implements Serializable {

    @Column(name = "id")
    @Id
    private Long id;

    @Column(name = "legajo")
    private int legajo;
    @Column(name = "dni")
    private int dni;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "fechaBaja")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaBaja;
    @Column(name = "autorizo")
    private String autorizo;
    @Column(name = "fechaAlta")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @OneToOne
    @JoinColumn(name = "idTipoEmpleado")
    private Tipo_empleado tipo_empleado;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.Empleado[ id=" + legajo + " ]";
    }

    /**
     * @return the legajo
     */
    public int getLegajo() {
        return legajo;
    }

    /**
     * @param legajo the legajo to set
     */
    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    /**
     * @return the dni
     */
    public int getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(int dni) {
        this.dni = dni;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the Apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param Apellido the Apellido to set
     */
    public void setApellido(String Apellido) {
        this.apellido = Apellido;
    }

    /**
     * @return the tipo_empleado
     */
    public Tipo_empleado getTipo_empleado() {
        return tipo_empleado;
    }

    /**
     * @param tipo_empleado the tipo_empleado to set
     */
    public void setTipo_empleado(Tipo_empleado tipo_empleado) {
        this.tipo_empleado = tipo_empleado;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the fechaBaja
     */
    public Date getFechaBaja() {
        return fechaBaja;
    }

    /**
     * @param fechaBaja the fechaBaja to set
     */
    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /**
     * @return the autorizo
     */
    public String getAutorizo() {
        return autorizo;
    }

    /**
     * @param autorizo the autorizo to set
     */
    public void setAutorizo(String autorizo) {
        this.autorizo = autorizo;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the fechaAlta
     */
    public Date getFechaAlta() {
        return fechaAlta;
    }

    /**
     * @param fechaAlta the fechaAlta to set
     */
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

}
