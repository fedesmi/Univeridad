/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositorios;

import com.controladores.Authorization;
import com.controladores.ConexionBaseDatos;
import com.entidades.Horario;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
        
        return getEntityManager().createNamedQuery("Horario.findByDiaSemana").setParameter("diaSemana", diaSemana).getResultList();

    }

    public List<Horario> getHorariosOcupados(Date fecha) {
        Horario horario;
        List<Horario> horarios = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String sql;

            String consulta = "SELECT h.id, h.inicio, h.fin, h.dia_semana "
                    + "FROM horario AS h "
                    + "LEFT JOIN clase AS c ON h.id=c.id_horario "
                    + "WHERE c.fecha = '" + sdf.format(fecha) + "' "
                    + "GROUP BY h.id "
                    + "HAVING COUNT(h.Id) = "
                    + "(SELECT COUNT(*) "
                    + "FROM empleado AS e "
                    + "WHERE e.fechaBaja  IS NULL "
                    + "AND e.autorizo IS NOT NULL "
                    + "AND e.idtipoempleado = 3 "
                    + "ORDER BY e.legajo)";
            
            ConexionBaseDatos connMysql = new ConexionBaseDatos();
            java.sql.ResultSet resultado = connMysql.ejecutarConsulta(consulta);
            try {
                while (resultado.next()) {
                    horario = new Horario();
                    horario.setId(resultado.getInt(1));
                    horario.setInicio(resultado.getDate(2));
                    horario.setFin(resultado.getDate(3));
                    horario.setDiaSemana(resultado.getShort(4));
                    horarios.add(horario);
                }
            } catch (java.sql.SQLException e) {
                System.err.print(e);
            }
            connMysql.cerrarConexion();

        } catch (IOException ex) {
            Logger.getLogger(Authorization.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return horarios;

    }

}
