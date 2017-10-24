/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositorios;

import com.entidades.Alumno;
import com.entidades.Clase;
import com.entidades.Empleado;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fmichel
 */
@Stateless
public class ClaseFacade extends AbstractFacade<Clase> {

    @PersistenceContext(unitName = "DAMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClaseFacade() {
        super(Clase.class);
    }

    public List<Clase> getClasesByFecha(Date fecha) {
        return getEntityManager().createNamedQuery("Clase.findByFecha").setParameter("fecha", fecha).getResultList();

    }
    
    
    public List<Clase> getClasesImpagas() {
        return getEntityManager().createNamedQuery("Clase.findClasesImpagas").getResultList();

    }
    public List<Clase> getClasesImpagasAlumnos() {
        return getEntityManager().createNamedQuery("Clase.findClasesImpagasAlumnos").getResultList();

    }
      public List<Clase> getClasesImpagasAlumnos(Alumno alumno) {
        return getEntityManager().createNamedQuery("Clase.findClasesImpagasByAlumno").setParameter("alumnoPar", alumno).getResultList();

    }

    public List<Clase> getClasesByInstructor(Empleado instructor) {
        return getEntityManager().createNamedQuery("Clase.findByIdInstructor").setParameter("instructor", instructor).getResultList();

    }
    
    public List<Alumno> getAlumnosByInstructor(Empleado instructor) {
        return getEntityManager().createNamedQuery("Clase.findAlumnosByIdInstructor").setParameter("instructor", instructor).getResultList();

    }
    
    

}
