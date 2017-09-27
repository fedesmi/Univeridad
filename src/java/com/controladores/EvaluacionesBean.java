/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Alumno;
import com.entidades.Evaluacion;
import com.repositorios.EvaluacionFacade;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author fmichel
 */
@Named(value = "evaluacionesBean")
@RequestScoped
public class EvaluacionesBean {

    /**
     * Creates a new instance of EvaluacionesBean
     */
    public EvaluacionesBean() {
    }
    
    private List<Evaluacion> evaluaciones;
     @Inject
    private EvaluacionFacade evaluacionFacade;

     
     
   
   
      
    /**
     * @return the evaluaciones
     */
    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    
    
    /**
     * @param evaluaciones the evaluaciones to set
     */
    public void setEvaluaciones(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
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

    public void actualizarListaEvaluaciones(Alumno alumno) {
        evaluaciones = evaluacionFacade.getEvaluacionesByAlumno(alumno);
    }
    
    
}
