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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;

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
    private String telefono;
    private Date fechaBaja;
    private Tipo_empleado tipoEmpleado;
    @Inject
    private EmpleadoFacade empleadoFacade;
    @Inject
    private Tipo_empleadoFacade templeadoFacade;

    /**
     * Creates a new instance of empleadoBean
     */
    public EmpleadoBean() {

    }
    
    
// This is the required method to get the datatable list.
    @PostConstruct
    public void init() {
       
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

    /**
     * 
     */
    public void guardar() {
        Empleado e = new Empleado();
        // e.setLegajo(legajo);
        e.setDni(dni);
        e.setNombre(nombre);
        e.setApellido(apellido);
        e.setTelefono(telefono);
        try {
            e.setTipo_empleado(tipoEmpleado);
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("tipo de empleado!!!   " + tipoEmpleado.getDescripcion());

        this.empleadoFacade.create(e);

        limpiarCampos();

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "El Empleado fue registrado exitosamente"));

    }
    
    public List<Empleado> getEmpleados() {
        return this.empleadoFacade.findAll();

    }
    
    public List<Empleado> getEmpleadosAlta() {
        return this.empleadoFacade.findWhere("t.fechaBaja  is null");

    }

    public List<Tipo_empleado> getTipo_Empleados() {
        return this.templeadoFacade.findAll();

    }

    public String prepareList() {
        return "EmpleadoLista";
    }

    public String prepareCreate() {
        return "EmpleadoCreate";
    }

    public String Eliminar(Long id) {
        Empleado e = this.empleadoFacade.find(id);
        this.empleadoFacade.remove(e);
        return "EmpleadoLista";
    }

    public String Editar(Long id) {
        Empleado e = this.empleadoFacade.find(id);
        this.legajo = e.getLegajo();
        this.dni = e.getDni();
        this.nombre = e.getNombre();
        this.apellido = e.getApellido();
        this.telefono = e.getTelefono();
        this.tipoEmpleado = e.getTipo_empleado();
        return "EmpleadoEdit";
    }

    public String GuardarEdicion(EmpleadoBean bp, Long legajo) {
        Empleado e = new Empleado();

        e.setLegajo(legajo);
        e.setDni(this.dni);
        e.setNombre(this.nombre);
        e.setApellido(this.apellido);
        e.setTelefono(bp.telefono);
        e.setTipo_empleado(bp.tipoEmpleado);
        this.empleadoFacade.edit(e);
        return "EmpleadoLista";
    }

    /**
     * @return the tipoEmpleado
     */
    public Tipo_empleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    /**
     * @param tipoEmpleado the tipoEmpleado to set
     */
    public void setTipoEmpleado(Tipo_empleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    private void limpiarCampos() {
        dni = 0;
        apellido = "";
        nombre = "";
        telefono="";
        tipoEmpleado = null;
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

    
     public void onRowEdit(RowEditEvent event)  throws ValidatorException {
        Empleado empVar = (Empleado) event.getObject();
         //Editar(empVar.getLegajo());
         System.out.println("nombre ahora "+empVar.getNombre());
         //GuardarEdicion(, empVar.getLegajo());
        FacesMessage msg = new FacesMessage("Empleado Editado", empVar.getLegajo().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edición Cancelada", ((Empleado) event.getObject()).getLegajo().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
   

}
