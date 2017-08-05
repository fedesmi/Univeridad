/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositorios;

import com.entidades.TipoEmpleado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fmichel
 */
@Stateless
public class TipoEmpleadoFacade extends AbstractFacade<TipoEmpleado> {

    @PersistenceContext(unitName = "DAMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoEmpleadoFacade() {
        super(TipoEmpleado.class);
    }
    
}
