/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositorios;

import com.entidades.Ingreso;
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
public class IngresoFacade extends AbstractFacade<Ingreso> {

    @PersistenceContext(unitName = "DAMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IngresoFacade() {
        super(Ingreso.class);
    }
    public List<Ingreso> getIngresosByFechas(Date fechaDesde, Date fechaHasta) {
        return getEntityManager().createNamedQuery("Ingreso.findDesdeHasta").setParameter("fechaD", fechaDesde).setParameter("fechaH", fechaHasta).getResultList();
    }

    public double getTotalIngresos(Date fechaDesde, Date fechaHasta) {
        Object total;
        total = getEntityManager().createNamedQuery("Ingreso.findTotal").setParameter("fechaD", fechaDesde).setParameter("fechaH", fechaHasta).getSingleResult();
        if (total != null) {
            return (double) total;
        } else {
            return 0;
        } 

    }

}
