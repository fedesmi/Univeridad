/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositorios;

import com.entidades.AlquilerVehiculo;
import com.entidades.Alumno;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fmichel
 */
@Stateless
public class AlquilerVehiculoFacade extends AbstractFacade<AlquilerVehiculo> {

    @PersistenceContext(unitName = "DAMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlquilerVehiculoFacade() {
        super(AlquilerVehiculo.class);
    }

    
    
    
    public List<AlquilerVehiculo> getAlquileresImpagos(Alumno alumnoPar) {
        return getEntityManager().createNamedQuery("AlquilerVehiculo.findAlquileresImpagos").setParameter("alumno", alumnoPar).getResultList();

    }
    
     public List<AlquilerVehiculo> getAlumnosAlquileresImpagos() {
        return getEntityManager().createNamedQuery("AlquilerVehiculo.findAlquileresImpagosAlumnos").getResultList();

    }
    
        public void cancelarClase(AlquilerVehiculo alquiler) {
       getEntityManager().createNamedQuery("AlquilerVehiculo.").setParameter("alquiler", alquiler).executeUpdate();

    }
    

}
