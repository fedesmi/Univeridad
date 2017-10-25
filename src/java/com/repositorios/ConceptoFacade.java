/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositorios;

import com.entidades.Concepto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fmichel
 */
@Stateless
public class ConceptoFacade extends AbstractFacade<Concepto> {

    @PersistenceContext(unitName = "DAMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConceptoFacade() {
        super(Concepto.class);
    }

    public Concepto getValorHoraClase() {
        return (Concepto)  getEntityManager().createNamedQuery("Concepto.findByDescripcion").setParameter("descripcion", "Hora_Clase").getSingleResult();
    }

    public Concepto getValorHoraVehiculo() {
        return (Concepto) getEntityManager().createNamedQuery("Concepto.findByDescripcion").setParameter("descripcion", "Hora_Vehiculo").getSingleResult();
    }
}
