/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositorios;

import com.entidades.Horario;
import com.entidades.ListaEsperaClase;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fmichel
 */
@Stateless
public class ListaEsperaClaseFacade extends AbstractFacade<ListaEsperaClase> {

    @PersistenceContext(unitName = "DAMPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ListaEsperaClaseFacade() {
        super(ListaEsperaClase.class);
    }
    
    
       public <List>ListaEsperaClase getListaInscriptosClase(Horario horario, Date fechaClase) {  
        return (ListaEsperaClase)getEntityManager().createNamedQuery("ListaEsperaClase.findByHorario").setParameter("horarioVar", horario).setParameter("fechaClase", fechaClase).getResultList();
     
    }
        
}
