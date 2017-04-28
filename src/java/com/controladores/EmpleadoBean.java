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
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;


/**
 *
 * @author fmichel
 */
@Named(value = "empleadoBean")
@RequestScoped
public class EmpleadoBean implements Serializable {

    private Long legajo;
    private int dni;
    private String nombre;
    private String apellido;
    @Inject
    private EmpleadoFacade empleadoFacade;
    
    /**
     * Creates a new instance of empleadoBean
     */
    public EmpleadoBean() {
        
    }
    
    
    
    /**
     * @return the legajo
     */
    public Long getLegajo() {
        return legajo;
    }

    /**
     * @param legajo the legajo to set
     */
    public void setLegajo(Long legajo) {
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
    
 
    
    
    
    
    /***/
    
     public String guardar()
    {
           Empleado e = new Empleado();
           e.setLegajo(legajo);
           e.setDni(dni);
           e.setNombre(nombre);
           e.setApellido(apellido);
           this.empleadoFacade.create(e);
           return "EmpleadoCreate";
          
    }
     
    public List<Empleado> getEmpleados()
    {
         
        return this.empleadoFacade.refreshCollection(this.empleadoFacade.findAll());
        
    }  
    
    
    

    public String prepareList() {
        return "EmpleadoLista";
    }
    public String prepareCreate() {
        return "EmpleadoCreate";
    }
    public String Eliminar(Long id)
    {
        Empleado e =  this.empleadoFacade.find(id);       
        this.empleadoFacade.remove(e);
       return "EmpleadoLista";
    }
    public String Editar(Long id)
    {
        Empleado e =  this.empleadoFacade.find(id);       
        this.legajo=e.getLegajo();
        this.dni=e.getDni();
        this.nombre=e.getNombre();
        this.apellido=e.getApellido();
        return "EmpleadoEdit";
    }
    public String GuardarEdicion(EmpleadoBean bp, Long legajo)
    {
       Empleado e = new Empleado();
       
       e.setLegajo(legajo);
       e.setDni(bp.dni);
       e.setNombre(bp.nombre);
       e.setApellido(bp.apellido);
       this.empleadoFacade.edit(e);
       return "EmpleadoLista";
    }

   
    
    
}
