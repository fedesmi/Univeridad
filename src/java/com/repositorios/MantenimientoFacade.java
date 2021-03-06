/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositorios;

import com.entidades.Mantenimiento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fmichel
 */
@Stateless
public class MantenimientoFacade extends AbstractFacade<Mantenimiento> {

    @PersistenceContext(unitName = "DAMPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MantenimientoFacade() {
        super(Mantenimiento.class);
    }
    
      public List<Mantenimiento> getMantenimientosDeVehiculo(int idVehiculo) { 
         return getEntityManager().createNamedQuery("Mantenimiento.findByIdVehiculo").setParameter("idVehiculo", idVehiculo).getResultList();
    }
   
}
