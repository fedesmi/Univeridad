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
import javax.inject.Inject;
import org.primefaces.event.RowEditEvent;


/**
 *
 * @author fmichel
 */
@Named(value = "empleadoBean")
@RequestScoped
public class EmpleadoBean implements Serializable {

    /**
     * @return the empleadoSeleccionado
     */
    public Empleado getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    /**
     * @param empleadoSeleccionado the empleadoSeleccionado to set
     */
    public void setEmpleadoSeleccionado(Empleado empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }

    private int legajo;
    private int dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private Date fechaBaja;
    private Date fechaAlta;
    private TipoEmpleado tipoEmpleado;
    @Inject
    private EmpleadoFacade empleadoFacade;
    @Inject
    private TipoEmpleadoFacade templeadoFacade;
    private List<Empleado> empleados;
    private List<Empleado> empleadosSinAutorizar;

    private Empleado empleadoSeleccionado;
    
    /**
     * Creates a new instance of empleadoBean
     */
    public EmpleadoBean() {
       
    }
    
    
// This is the required method to get the datatable list.
    @PostConstruct
    public void init() {
       empleados = getEmpleadosAutorizadosDB();
       empleadosSinAutorizar = getEmpleadosNoAutorizadosDB();
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
     * 
     */
    public void guardar() {
        Empleado e = new Empleado();
        // e.setLegajo(legajo);
        e.setDni(dni);
        e.setNombre(nombre);
        e.setApellido(apellido);
        e.setTelefono(telefono);
        e.setFechaAlta(new Date());
        e.setLegajo(this.empleadoFacade.buscarUltimoLegajo());
        try {
            e.setIdTipoEmpleado(tipoEmpleado);
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.empleadoFacade.create(e);

        limpiarCampos();

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "El Empleado fue registrado exitosamente"));

    }
    
    public List<Empleado> getEmpleadosDB() {
        return this.empleadoFacade.findAll();

    }
    
    public List<Empleado> getEmpleadosAutorizadosDB() { 
        return this.empleadoFacade.buscarAutorizados();
    }
    
    public List<Empleado> getEmpleadosNoAutorizadosDB() { 
        return this.empleadoFacade.buscarNoAutorizados();
    }

    public List<TipoEmpleado> getTipo_Empleados() {
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
        this.tipoEmpleado = e.getIdTipoEmpleado();
        return "EmpleadoEdit";
    }

    public String GuardarEdicion(Empleado bp) {
        bp.setAutorizo(null);
        this.empleadoFacade.edit(bp);
        return "EmpleadoLista";
    }

    /**
     * @return the tipoEmpleado
     */
    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    /**
     * @param tipoEmpleado the tipoEmpleado to set
     */
    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
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

    
     public void onRowEdit(RowEditEvent event)  {
        Empleado empVar = (Empleado) event.getObject();
         GuardarEdicion(empVar);
        FacesMessage msg = new FacesMessage("Empleado Editado", "Legajo: "+String.valueOf(empVar.getLegajo()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        Empleado empVar = (Empleado) event.getObject();
        FacesMessage msg = new FacesMessage("Edición Cancelada",  "Legajo: "+String.valueOf(empVar.getLegajo()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @return the empleados
     */
    public List<Empleado> getEmpleados() {
        return empleados;
    }
    
    
    /**
     * @return the empleados
     */
    public List<Empleado> getInstructores() {
        return this.empleadoFacade.getInstructores();
    }

    /**
     * @param empleados the empleados to set
     */
    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
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

    /**
     * @return the empleadosSinAutorizar
     */
    public List<Empleado> getEmpleadosSinAutorizar() {
        return empleadosSinAutorizar;
    }

    /**
     * @param empleadosSinAutorizar the empleadosSinAutorizar to set
     */
    public void setEmpleadosSinAutorizar(List<Empleado> empleadosSinAutorizar) {
        this.empleadosSinAutorizar = empleadosSinAutorizar;
    }

    public void autorizarEdicionEmpleado(int legajo) {
        this.empleadoFacade.autorizarCambiosEmpleado(legajo, empleadoSeleccionado.getId().longValue());
        FacesMessage msg = new FacesMessage("Autorizacion ", "Se ha Autorizado el cambio");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
   

}
