/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositorios;

import com.controladores.ConexionBaseDatos;
import com.entidades.Alumno;
import com.entidades.Clase;
import com.entidades.Empleado;
import java.io.IOException;
import java.sql.SQLException;
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
public class ClaseFacade extends AbstractFacade<Clase> {

    @PersistenceContext(unitName = "DAMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClaseFacade() {
        super(Clase.class);
    }

    public List<Clase> getClasesByFecha(Date fecha) {
        return getEntityManager().createNamedQuery("Clase.findByFecha").setParameter("fecha", fecha).getResultList();

    }
    
    
    public List<Clase> getClasesImpagas() {
        return getEntityManager().createNamedQuery("Clase.findClasesImpagas").getResultList();

    }
    public List<Clase> getClasesImpagasAlumnos() {
        return getEntityManager().createNamedQuery("Clase.findClasesImpagasAlumnos").getResultList();

    }
      public List<Clase> getClasesImpagasAlumnos(Alumno alumno) {
        return getEntityManager().createNamedQuery("Clase.findClasesImpagasByAlumno").setParameter("alumnoPar", alumno).getResultList();

    }

    public List<Clase> getClasesByInstructor(Empleado instructor) {
        return getEntityManager().createNamedQuery("Clase.findByIdInstructor").setParameter("instructor", instructor).getResultList();

    }
    public List<Clase> getClasesByInstructorAndMes(Empleado instructor, int mes) {
        return getEntityManager().createNamedQuery("Clase.findByIdInstructorAndMes").setParameter("instructor", instructor).setParameter("mes", mes).getResultList();

    }
     public int getCantidadClasesByInstructorAndMes(Empleado instructor, int mes) {
        //return (int)getEntityManager().createNamedQuery("Clase.findByIdInstructorAndMesCantidad").setParameter("instructor", instructor).setParameter("mes", mes).getSingleResult();
        int cantidad = 0;
        try {

            String consulta = "SELECT COUNT(*) FROM clase as c WHERE c.id_instructor = "+ instructor.getId() +"  "
                    + "AND  MONTH(c.fecha) = "+mes+" ; ";

            
            ConexionBaseDatos connMysql = new ConexionBaseDatos();
            java.sql.ResultSet resultado = connMysql.ejecutarConsulta(consulta);

            try {
                while (resultado.next()) {
                   cantidad = resultado.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClaseFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
            connMysql.cerrarConexion();
        } catch (IOException ex) {
            Logger.getLogger(ClaseFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidad;
    }
    
    public List<Alumno> getAlumnosByInstructor(Empleado instructor) {
        return getEntityManager().createNamedQuery("Clase.findAlumnosByIdInstructor").setParameter("instructor", instructor).getResultList();

    }
    
      public void cancelarClase(Clase clase) {
       getEntityManager().createNamedQuery("Clase.cancelarClase").setParameter("clase", clase).executeUpdate();

    }

}
