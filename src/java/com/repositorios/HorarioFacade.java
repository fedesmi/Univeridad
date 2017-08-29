/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositorios;


import com.entidades.Horario;
import java.util.Calendar;
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
public class HorarioFacade extends AbstractFacade<Horario> {

    @PersistenceContext(unitName = "DAMPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HorarioFacade() {
        super(Horario.class);
    }
    
    
    public List<Horario> getHorariosByDiaSemana(Date fecha) {  
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        return getEntityManager().createNamedQuery("Horario.findByDiaSemana").setParameter("diaSemana", Calendar.DAY_OF_WEEK).getResultList();
     
    }
    
    public List<Horario> getHorariosOcupados(Date fecha) {  
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        String consulta = "SELECT h.id  FROM Horario h "
                + "LEFT JOIN Clase c ON h.id=c.id_horario "
                + "WHERE dia_semana = :diaSemana GROUP BY horario.id "
                + "HAVING COUNT(horario.Id) = (SELECT COUNT(e.id) "
                + "FROM Empleado e "
                + "WHERE e.fechaBaja  IS NULL  "
                + "AND e.autorizo IS NOT NULL "
                + "AND e.idtipoempleado = 3 ORDER BY e.legajo)";
        return getEntityManager().createQuery(consulta).setParameter("diaSemana", Calendar.DAY_OF_WEEK).getResultList();
     
    }
    
    
     
}
