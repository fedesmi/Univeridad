/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositorios;

import com.entidades.Desperfecto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fmichel
 */
@Stateless
public class DesperfectoFacade extends AbstractFacade<Desperfecto> {

    @PersistenceContext(unitName = "DAMPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DesperfectoFacade() {
        super(Desperfecto.class);
    }
    
      public List<Desperfecto> getDesperfectosDeVehiculo(int idVehiculo) { 
         return getEntityManager().createNamedQuery("Desperfecto.findByIdVehiculo").setParameter("id", idVehiculo).getResultList();
    }
   
}
