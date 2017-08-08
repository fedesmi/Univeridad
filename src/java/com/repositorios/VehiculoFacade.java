/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositorios;

import com.entidades.Empleado;
import com.entidades.Vehiculo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fmichel
 */
@Stateless
public class VehiculoFacade extends AbstractFacade<Vehiculo> {

    @PersistenceContext(unitName = "DAMPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VehiculoFacade() {
        super(Vehiculo.class);
    }
    
    
      public void asignarEmpleadoVehiculo(Empleado emp, int idVehiculo) {  
         getEntityManager().createNamedQuery("Vehiculo.asignarEmpleado").setParameter("empleadoPar", emp).setParameter("id", idVehiculo).executeUpdate();
    }
      
      
     
}
