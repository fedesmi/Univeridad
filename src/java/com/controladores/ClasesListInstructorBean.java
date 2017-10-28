/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Alumno;
import com.entidades.Clase;
import com.entidades.Empleado;
import com.entidades.Evaluacion;
import com.entidades.FormaCobro;
import com.entidades.Usuario;
import com.entidades.Vehiculo;
import com.repositorios.ClaseFacade;
import com.repositorios.EmpleadoFacade;
import com.repositorios.EvaluacionFacade;
import com.repositorios.FormaCobroFacade;
import com.repositorios.UsuarioFacade;
import com.repositorios.VehiculoFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author fmichel
 */
@Named(value = "clasesListInstructorBean")
@SessionScoped
public class ClasesListInstructorBean implements Serializable {

    private List<Clase> clases;
    private List<Alumno> alumnos;
    @Inject
    private ClaseFacade claseFacade;
    @Inject
    private UsuarioFacade usuarioFacade;
    @Inject
    private EvaluacionFacade evaluacionFacade;
    @Inject
    private VehiculoFacade vehiculoFacade;

    @Inject
    private FormaCobroFacade formaCobroFacade;
    
     @Inject
    private EmpleadoFacade empleadoFacade;

    private Alumno alumnoSeleccionado;

    private String evaluacion;

    private Usuario usuarioVar;
    private Vehiculo vehiculoVar;
    private List<FormaCobro> formasCobro;

    private boolean editar = false;

    /**
     * Creates a new instance of ClasesListInstructorBean
     */
    public ClasesListInstructorBean() {
    }

    public void onload() {
        actualizarListaClases();
    }

    public void onloadAlumnos() {
        actualizarListaAlumnos();
    }

    public void onLoadDatosPersonales() {
        String nombreUsuario = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        usuarioVar = usuarioFacade.getUsuario(nombreUsuario);
        vehiculoVar = vehiculoFacade.buscarVehiculoPorEmpleado(usuarioVar.getIdEmpleado());
        formasCobro = formaCobroFacade.findAll();
    }

    public void actualizarListaClases() {
        String nombreUsuario = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        Usuario usuario = usuarioFacade.getUsuario(nombreUsuario);
        clases = claseFacade.getClasesByInstructor(usuario.getIdEmpleado());

    }

    public void editarComponentes() {
        editar = true;
    }

    /**
     * @return the clases
     */
    public List<Clase> getClases() {
        return clases;
    }

    /**
     * @param clases the clases to set
     */
    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }

    private void actualizarListaAlumnos() {
        String nombreUsuario = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        Usuario usuario = usuarioFacade.getUsuario(nombreUsuario);
        alumnos = claseFacade.getAlumnosByInstructor(usuario.getIdEmpleado());

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

    /**
     * @return the alumnoSeleccionado
     */
    public Alumno getAlumnoSeleccionado() {
        return alumnoSeleccionado;
    }

    /**
     * @param alumnoSeleccionado the alumnoSeleccionado to set
     */
    public void setAlumnoSeleccionado(Alumno alumnoSeleccionado) {
        this.alumnoSeleccionado = alumnoSeleccionado;
    }

    /**
     * @return the Evaluacion
     */
    public String getEvaluacion() {
        return evaluacion;
    }

    /**
     * @param evaluacion the Evaluacion to set
     */
    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public void guardarEvaluacion() {
        Evaluacion evaluacionVar = new Evaluacion();
        evaluacionVar.setDescripcion(evaluacion);
        evaluacionVar.setFecha(new Date());
        evaluacionVar.setIdAlumno(alumnoSeleccionado);

        String nombreUsuario = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        Usuario usuario = usuarioFacade.getUsuario(nombreUsuario);

        evaluacionVar.setIdInstructor(usuario.getIdEmpleado());
        evaluacionFacade.create(evaluacionVar);
        evaluacion = "";

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "La evaluación fue registrada exitosamente"));
    }

    /**
     * @return the evaluacionFacade
     */
    public EvaluacionFacade getEvaluacionFacade() {
        return evaluacionFacade;
    }

    /**
     * @param evaluacionFacade the evaluacionFacade to set
     */
    public void setEvaluacionFacade(EvaluacionFacade evaluacionFacade) {
        this.evaluacionFacade = evaluacionFacade;
    }

    /**
     * @return the vehiculoFacade
     */
    public VehiculoFacade getVehiculoFacade() {
        return vehiculoFacade;
    }

    /**
     * @param vehiculoFacade the vehiculoFacade to set
     */
    public void setVehiculoFacade(VehiculoFacade vehiculoFacade) {
        this.vehiculoFacade = vehiculoFacade;
    }

    /**
     * @return the usuarioVar
     */
    public Usuario getUsuarioVar() {
        return usuarioVar;
    }

    /**
     * @param usuarioVar the usuarioVar to set
     */
    public void setUsuarioVar(Usuario usuarioVar) {
        this.usuarioVar = usuarioVar;
    }

    /**
     * @return the vehiculoVar
     */
    public Vehiculo getVehiculoVar() {
        return vehiculoVar;
    }

    /**
     * @param vehiculoVar the vehiculoVar to set
     */
    public void setVehiculoVar(Vehiculo vehiculoVar) {
        this.vehiculoVar = vehiculoVar;
    }

    /**
     * @return the editar
     */
    public boolean isEditar() {
        return editar;
    }

    /**
     * @param editar the editar to set
     */
    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    /**
     * @return the formaCobroFacade
     */
    public FormaCobroFacade getFormaCobroFacade() {
        return formaCobroFacade;
    }

    /**
     * @param formaCobroFacade the formaCobroFacade to set
     */
    public void setFormaCobroFacade(FormaCobroFacade formaCobroFacade) {
        this.formaCobroFacade = formaCobroFacade;
    }

    /**
     * @return the formasCobro
     */
    public List<FormaCobro> getFormasCobro() {
        return formasCobro;
    }

    /**
     * @param formasCobro the formasCobro to set
     */
    public void setFormasCobro(List<FormaCobro> formasCobro) {
        this.formasCobro = formasCobro;
    }

    public void guardarDatos() {
        
        empleadoFacade.edit(usuarioVar.getIdEmpleado());
        editar = false;
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Los datos se han guardado exitosamente"));

    }

}
