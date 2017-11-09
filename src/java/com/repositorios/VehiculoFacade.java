/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositorios;

import com.entidades.Empleado;
import com.entidades.Vehiculo;
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

    public Vehiculo buscarVehiculoPorEmpleado(Empleado emp) {
        return (Vehiculo) getEntityManager().createNamedQuery("Vehiculo.findByEmpleado").setParameter("empleado", emp).getSingleResult();
    }

    public List<Vehiculo> getVehiculosOcupados(Date fecha) {
        return getEntityManager().createNamedQuery("Vehiculo.findOcupadosByFecha").setParameter("fecha", fecha).getResultList();
    }

    public List<Vehiculo> getVehiculosParaAlquiler() {
        return getEntityManager().createNamedQuery("Vehiculo.findParaAlquiler").getResultList();
    }

    public List<Vehiculo> getVehiculosParaClases() {
        return getEntityManager().createNamedQuery("Vehiculo.findbyAptos").getResultList();
    }

}
