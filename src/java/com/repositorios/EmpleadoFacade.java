/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositorios;

import com.entidades.Empleado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fmichel
 */
@Stateless
public class EmpleadoFacade extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "DAMPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }
    
    public List<Empleado> buscarAutorizados() {
         return getEntityManager().createNamedQuery("Empleado.todosAutorizados").getResultList();
    }
 
     public List<Empleado> buscarNoAutorizados() {
         return getEntityManager().createNamedQuery("Empleado.todosSinAutorizar").getResultList();
    }
     
      public List<Empleado> getInstructores() {
         return getEntityManager().createNamedQuery("Empleado.instructores").setParameter("instructor", "instructor").getResultList();
    }
     
       public void autorizarCambiosEmpleado(int legajo, Long id) {  
         getEntityManager().createNamedQuery("Empleado.autorizarCambio").setParameter("legajo", legajo).setParameter("id", id).executeUpdate();
     
    }
     
    public int buscarUltimoLegajo() {
         return (int) getEntityManager().createNamedQuery("Empleado.ultimoLegajo").getSingleResult();
    }
    
}
