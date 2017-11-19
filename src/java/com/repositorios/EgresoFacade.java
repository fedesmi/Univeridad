/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositorios;

import com.entidades.Egreso;
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
public class EgresoFacade extends AbstractFacade<Egreso> {

    @PersistenceContext(unitName = "DAMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EgresoFacade() {
        super(Egreso.class);
    }
    public List<Egreso> getEgresosByFechas(Date fechaDesde, Date fechaHasta) {
        return getEntityManager().createNamedQuery("Egreso.findDesdeHasta").setParameter("fechaD", fechaDesde).setParameter("fechaH", fechaHasta).getResultList();
    }
    
    public double getTotalEgresos(Date fechaDesde, Date fechaHasta) {
       Object total;
        total = getEntityManager().createNamedQuery("Egreso.findTotal").setParameter("fechaD", fechaDesde).setParameter("fechaH", fechaHasta).getSingleResult();
        if (total != null) {
            return (double) total;
        } else {
            return 0;
        } 

    }

    

}
