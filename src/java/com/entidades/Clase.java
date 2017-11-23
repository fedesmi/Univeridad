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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fmichel
 */
@Entity
@Cacheable(false)
@Table(name = "clase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clase.findAll", query = "SELECT c FROM Clase c ORDER BY c.fecha DESC")
    , @NamedQuery(name = "Clase.findById", query = "SELECT c FROM Clase c WHERE c.id = :id")
    , @NamedQuery(name = "Clase.findByFecha", query = "SELECT c FROM Clase c WHERE c.fecha = :fecha")
    ,@NamedQuery(name = "Clase.findClasesImpagas", query = "SELECT c FROM Clase c WHERE c.idIngreso IS NULL")
    ,@NamedQuery(name = "Clase.findClasesImpagasAlumnos", query = "SELECT c FROM Clase c WHERE c.idIngreso IS NULL  GROUP BY c.idAlumno")
    , @NamedQuery(name = "Clase.findByIdInstructor", query = "SELECT c FROM Clase c WHERE c.idInstructor = :instructor ORDER BY c.fecha DESC")
    ,@NamedQuery(name = "Clase.findByIdInstructorAndMes", query = "SELECT c FROM Clase c WHERE c.idInstructor = :instructor AND   FUNC('MONTH', c.fecha) = :mes  ORDER BY c.fecha DESC")
    ,@NamedQuery(name = "Clase.findByIdInstructorAndMesCantidad", query = "SELECT COUNT(c) FROM Clase c WHERE c.idInstructor = :instructor ")
    , @NamedQuery(name = "Clase.findAlumnosByIdInstructor", query = "SELECT c.idAlumno FROM Clase c WHERE c.idInstructor = :instructor GROUP BY c.idAlumno")
    , @NamedQuery(name = "Clase.cancelarClase", query = "UPDATE Clase c SET c.fechaCancelado = FUNC('CURDATE')  WHERE c = :clase")
    , @NamedQuery(name = "Clase.findClasesImpagasByAlumno", query = "SELECT c FROM Clase c WHERE c.idAlumno = :alumno "
            + "AND c.idIngreso IS NULL AND c.fechaCancelado IS NULL OR (c.fechaCancelado IS NOT NULL AND  c.fecha-c.fechaCancelado<2) ")
})
public class Clase implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Double valor;

    @Column(name = "fecha_cancelado")
    @Temporal(TemporalType.DATE)
    private Date fechaCancelado;

      @JoinColumn(name = "id_ingreso", referencedColumnName = "id")
    @ManyToOne
    private Ingreso idIngreso;

    @JoinColumn(name = "id_horario", referencedColumnName = "id")
    @ManyToOne
    private Horario idHorario;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "id_alumno", referencedColumnName = "id")
    @ManyToOne
    private Alumno idAlumno;
    @JoinColumn(name = "id_instructor", referencedColumnName = "id")
    @ManyToOne
    private Empleado idInstructor;

    public Clase() {
    }

    public Clase(Integer id) {
        this.id = id;
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

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Empleado getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(Empleado idInstructor) {
        this.idInstructor = idInstructor;
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
        if (!(object instanceof Clase)) {
            return false;
        }
        Clase other = (Clase) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.Clase[ id=" + id + " ]";
    }

    public Horario getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Horario idHorario) {
        this.idHorario = idHorario;
    }

    public Ingreso getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(Ingreso idIngreso) {
        this.idIngreso = idIngreso;
    }

    public Date getFechaCancelado() {
        return fechaCancelado;
    }

    public void setFechaCancelado(Date fechaCancelado) {
        this.fechaCancelado = fechaCancelado;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

   

}
