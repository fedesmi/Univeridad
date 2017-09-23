/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Clase;
import com.entidades.Usuario;
import com.repositorios.ClaseFacade;
import com.repositorios.EmpleadoFacade;
import com.repositorios.UsuarioFacade;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author fmichel
 */
@Named(value = "clasesListInstructorBean")
@RequestScoped
public class ClasesListInstructorBean {
    private List<Clase> clases;
    @Inject
    private ClaseFacade claseFacade;
    @Inject
    private UsuarioFacade usuarioFacade;

    /**
     * Creates a new instance of ClasesListInstructorBean
     */
    public ClasesListInstructorBean() {
    }
    
     public void onload() {
        actualizarListaClases();
               
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
    
}
