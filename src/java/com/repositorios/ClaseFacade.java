/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositorios;

import com.entidades.Clase;
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
    
     
}
