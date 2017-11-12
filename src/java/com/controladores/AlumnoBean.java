/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import javax.inject.Named;
import java.io.Serializable;
import com.repositorios.*;
import com.entidades.*;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author fmichel
 */
@Named(value = "alumnoBean")
@SessionScoped
public class AlumnoBean implements Serializable {

    private Alumno alumnoVar;
    @Inject
    private AlumnoFacade alumnoFacade;
    private String nombre;
    private String apellido;
    private int dni; 
    private Date fechaNacimiento ;
    private String telefono;
    private String email;
    
   
  
    
    
     private List<Alumno> alumnos;
    /**
     * Creates a new instance of AlumnoBean
     */
    public AlumnoBean() {
    }
    
    
     // This is the required method to get the datatable list.
    /*@PostConstruct
    public void init() {
        alumnos = getAlumnosDB();
    }*/

    /**
     * @return the alumnoVar
     */
    public Alumno getAlumnoVar() {
        return alumnoVar;
    }
    
    public void onload() {
        alumnos = getAlumnosDB();
               
    }

     public void onloadInstructor() {
        alumnos = getAlumnosDB();
               
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
        alumnoVar.setTelefono(telefono);
        alumnoVar.setEmail(email);
        this.alumnoFacade.create(alumnoVar);
        
        apellido="";
        nombre="";
        dni=0;
        fechaNacimiento = null;
        alumnoVar = null;
        telefono="";
        email="";

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

    /**
     * @return the alumnos
     */
    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    /**
     * @param alumnos the alumnos to set
     */
    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
    
    
    public List<Alumno> getAlumnosDB(){
        return this.alumnoFacade.findAll();
        
    }
    
   
     public void onRowEdit(RowEditEvent event)  {
        Alumno alumnoLocalVar = (Alumno) event.getObject();
        this.alumnoFacade.edit(alumnoLocalVar);
        FacesMessage msg = new FacesMessage("Alumno Editado", "DNI: "+String.valueOf(alumnoLocalVar.getDni()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        Alumno alumnoLocalVar = (Alumno) event.getObject();
        FacesMessage msg = new FacesMessage("Edición Cancelada",  "DNI: "+String.valueOf(alumnoLocalVar.getDni()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
