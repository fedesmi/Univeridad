/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositorios;

import com.controladores.Authorization;
import com.controladores.ConexionBaseDatos;
import com.entidades.Empleado;
import com.entidades.Horario;
import com.entidades.TipoEmpleado;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class EmpleadoFacade extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "DAMPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }
    
    public List<Empleado> buscarAutorizados() {
         return getEntityManager().createNamedQuery("Empleado.todosAutorizados").getResultList();
    }
 
     public List<Empleado> buscarNoAutorizados() {
         return getEntityManager().createNamedQuery("Empleado.todosSinAutorizar").getResultList();
    }
     
      public List<Empleado> getInstructores() {
         return getEntityManager().createNamedQuery("Empleado.instructores").setParameter("instructor", "instructor").getResultList();
    }
      
        public List<Empleado> getInstructoresLibres(Date fecha, Horario horario) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<Empleado> instructores = new ArrayList<>();
         try {

                    String consulta = "SELECT e.id, e.legajo, e.dni, e.nombre, e.apellido, e.telefono, "
                                      + "e.fechaAlta, e.fechaBaja, e.autorizo, te.id, te.rol, te.sueldoBase "
                                      +"FROM empleado as e, tipo_empleado as te  "
                                      +"WHERE  e.idTipoEmpleado = te.id "
                                      +"AND e.fechaBaja  IS NULL "
                                      +"AND e.autorizo IS NOT NULL "
                                      +"AND e.idtipoempleado = 3 "
                                      +"AND e.id NOT IN "
                                                + "(SELECT c.id_instructor "
                                                + "FROM clase as c WHERE c.fecha = '"+sdf.format(fecha)+"' "
                                                + "AND c.id_horario= "+horario.getId()+" ) "
                                       + "ORDER BY e.legajo";
                    
                
                ConexionBaseDatos connMysql = new ConexionBaseDatos();
                java.sql.ResultSet resultado = connMysql.ejecutarConsulta(consulta);
                try {
                    Empleado emp;
                    TipoEmpleado temp;
                while (resultado.next()) {
                    emp = new Empleado();
                    emp.setId(resultado.getInt(1));
                    emp.setLegajo(resultado.getInt(2));
                    emp.setDni(resultado.getInt(3));
                    emp.setNombre(resultado.getString(4));
                    emp.setApellido(resultado.getString(5));
                    emp.setTelefono(resultado.getString(6));
                    emp.setFechaAlta(resultado.getDate(7));
                    emp.setFechaBaja(resultado.getDate(8));
                    emp.setAutorizo(resultado.getInt(9));
                    
                    temp = new TipoEmpleado();
                    temp.setId(resultado.getInt(10));
                    temp.setRol(resultado.getString(11));
                    temp.setSueldoBase(resultado.getFloat(12));
                    emp.setIdTipoEmpleado(temp);
                    
                    instructores.add(emp);
                       
                }
                 } catch (java.sql.SQLException e) {
                System.err.print(e);
            }
            connMysql.cerrarConexion();

        } catch (IOException ex) {
            Logger.getLogger(Authorization.class.getName()).log(Level.SEVERE, null, ex);
        }
         return instructores;
    }
     
       public void autorizarCambiosEmpleado(int legajo, Long id) {  
         getEntityManager().createNamedQuery("Empleado.autorizarCambio").setParameter("legajo", legajo).setParameter("id", id).executeUpdate();
     
    }
     
    public int buscarUltimoLegajo() {
         return (int) getEntityManager().createNamedQuery("Empleado.ultimoLegajo").getSingleResult();
    }
    
   
}
