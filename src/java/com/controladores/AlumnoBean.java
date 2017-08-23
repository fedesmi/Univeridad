/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Alumno;
import com.repositorios.AlumnoFacade;
import java.io.Serializable;
import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author fmichel
 */
@Named(value = "alumnoBean")
@RequestScoped
public class AlumnoBean implements Serializable {

    private Alumno alumnoVar;
    private AlumnoFacade alumnoFacade;
    private String nombre;
    private String apellido;
    private int dni; 
    private Date fechaNacimiento;
    
    /**
     * Creates a new instance of AlumnoBean
     */
    public AlumnoBean() {
    }

    /**
     * @return the alumnoVar
     */
    public Alumno getAlumnoVar() {
        return alumnoVar;
    }

    /**
     * @param alumnoVar the alumnoVar to set
     */
    public void setAlumnoVar(Alumno alumnoVar) {
        this.alumnoVar = alumnoVar;
    }
    
    public void guardar() {
        alumnoVar = new Alumno();
        alumnoVar.setApellido(apellido);
        alumnoVar.setNombre(nombre);
        alumnoVar.setDni(dni);
        alumnoVar.setFechaNacimiento(fechaNacimiento);
        this.alumnoFacade.create(alumnoVar);

        alumnoVar = new Alumno();

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "El Alumno fue registrado exitosamente"));

    }

    /**
     * @return the alumnoFacade
     */
    public AlumnoFacade getAlumnoFacade() {
        return alumnoFacade;
    }

    /**
     * @param alumnoFacade the alumnoFacade to set
     */
    public void setAlumnoFacade(AlumnoFacade alumnoFacade) {
        this.alumnoFacade = alumnoFacade;
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
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
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
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
}
