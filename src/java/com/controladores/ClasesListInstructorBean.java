/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Alumno;
import com.entidades.Clase;
import com.entidades.Usuario;
import com.repositorios.ClaseFacade;
import com.repositorios.UsuarioFacade;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
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
    
    private Alumno alumnoSeleccionado;

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

    public void actualizarListaClases() {
        String nombreUsuario = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        Usuario usuario = usuarioFacade.getUsuario(nombreUsuario);
        clases = claseFacade.getClasesByInstructor(usuario.getIdEmpleado());

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

}
