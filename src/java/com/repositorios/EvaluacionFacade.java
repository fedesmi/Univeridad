/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositorios;

import com.entidades.Alumno;
import com.entidades.Evaluacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fmichel
 */
@Stateless
public class EvaluacionFacade extends AbstractFacade<Evaluacion> {

    @PersistenceContext(unitName = "DAMPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvaluacionFacade() {
        super(Evaluacion.class);
    }
    
    
    public List<Evaluacion> getEvaluacionesByAlumno(Alumno alumno) {
        return getEntityManager().createNamedQuery("Evaluacion.findByIdAlumno").setParameter("alumno", alumno).getResultList();

    }
     
}
