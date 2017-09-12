/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositorios;

import com.clases.HorarioCompuesto;
import com.controladores.Authorization;
import com.controladores.ConexionBaseDatos;
import com.entidades.Alumno;
import com.entidades.Empleado;
import com.entidades.Horario;
import com.entidades.TipoEmpleado;
import java.io.IOException;
import java.sql.SQLException;
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

    public List<HorarioCompuesto> getHorariosOcupados(Date fecha) {
        HorarioCompuesto horarioCompuesto;

        Horario horario;
        List<HorarioCompuesto> horarios = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
   
            String consulta = "SELECT h.id, h.inicio, h.fin, h.dia_semana "
                    + "FROM horario AS h "
                    + "LEFT JOIN clase AS c ON h.id=c.id_horario AND '" + sdf.format(fecha) + "'=c.fecha "
                    + "WHERE dia_semana = DAYOFWEEK('" + sdf.format(fecha) + "') "
                    + "GROUP BY h.id; ";

            
            ConexionBaseDatos connMysql = new ConexionBaseDatos();
            java.sql.ResultSet resultado = connMysql.ejecutarConsulta(consulta);
            try {
                while (resultado.next()) {
                    horario = new Horario();
                    horario.setId(resultado.getInt(1));
                    horario.setInicio(resultado.getDate(2));
                    horario.setFin(resultado.getDate(3));
                    horario.setDiaSemana(resultado.getShort(4));

                    horarioCompuesto = new HorarioCompuesto();
                    horarioCompuesto.setHorario(horario);
                    horarioCompuesto.setInstructoresDispoinibles(getInstructoresDisponiblesHorario(fecha, horario.getId()));
                    horarioCompuesto.setAlumnosDisponibles(getAlumnosDisponiblesHorario(fecha, horario.getId()));
                    horarios.add(horarioCompuesto);
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

    private List<Empleado> getInstructoresDisponiblesHorario(Date fecha, Integer id) {
        List<Empleado> empleados = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {

            String consulta = "SELECT e.id, e.legajo, e.dni, e.nombre, e.apellido, e.telefono, "
                    + "e.fechaAlta, e.fechaBaja, e.autorizo, te.id, te.rol, te.sueldoBase, "
                    + "te.porcentajePorClase FROM empleado as e, tipo_empleado as te "
                    + "WHERE e.fechaBaja  IS NULL AND e.autorizo IS NOT NULL AND e.idtipoempleado = 3 "
                    + "AND e.id NOT IN (SELECT c.id_instructor FROM clase as c, horario as ho  "
                    + "WHERE c.id_horario=ho.id AND c.fecha='" + sdf.format(fecha) + "' AND ho.id = " + id + " ) "
                    + "AND e.idTipoEmpleado=te.id  ";

            ConexionBaseDatos connMysql = new ConexionBaseDatos();
            java.sql.ResultSet resultado = connMysql.ejecutarConsulta(consulta);

            Empleado empVar;
            TipoEmpleado tipoEmpleadoVar;
            try {
                while (resultado.next()) {
                    empVar = new Empleado();
                    tipoEmpleadoVar = new TipoEmpleado();
                    empVar.setId(resultado.getInt(1));
                    empVar.setLegajo(resultado.getInt(2));
                    empVar.setDni(resultado.getInt(3));
                    empVar.setNombre(resultado.getString(4));
                    empVar.setApellido(resultado.getString(5));
                    empVar.setTelefono(resultado.getString(6));
                    empVar.setFechaAlta(resultado.getDate(7));
                    empVar.setFechaBaja(resultado.getDate(8));
                    empVar.setAutorizo(resultado.getInt(9));
                    tipoEmpleadoVar.setId(resultado.getInt(10));
                    tipoEmpleadoVar.setRol(resultado.getString(11));
                    tipoEmpleadoVar.setSueldoBase(resultado.getInt(12));
                    tipoEmpleadoVar.setPorcentajePorClase(resultado.getShort(13));
                    empVar.setIdTipoEmpleado(tipoEmpleadoVar);
                    empleados.add(empVar);
                }
            } catch (SQLException ex) {
                Logger.getLogger(HorarioFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
            connMysql.cerrarConexion();
        } catch (IOException ex) {
            Logger.getLogger(HorarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return empleados;
    }

    private List<Alumno> getAlumnosDisponiblesHorario(Date fecha, Integer id) {
        List<Alumno> alumnos = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {

            String //ALUMNOS DISPONIBLES
                    consulta = "SELECT al.id, al.dni, al.nombre, al.apellido, al.fecha_nacimiento  "
                    + "FROM alumno AS al where al.id NOT IN "
                    + "(SELECT c.id_alumno FROM clase as c, horario as ho  "
                    + "WHERE c.id_horario=ho.id AND c.fecha='" + sdf.format(fecha) + "' AND ho.id = " + id + " ) ";

            ConexionBaseDatos connMysql = new ConexionBaseDatos();
            java.sql.ResultSet resultado = connMysql.ejecutarConsulta(consulta);
            Alumno alumnoVar;
            try {
                while (resultado.next()) {
                    alumnoVar = new Alumno();
                    alumnoVar.setId(resultado.getInt(1));
                    alumnoVar.setDni(resultado.getInt(2));
                    alumnoVar.setNombre(resultado.getString(3));
                    alumnoVar.setApellido(resultado.getString(4));
                    alumnoVar.setFechaNacimiento(resultado.getDate(5));
                    alumnos.add(alumnoVar);
                }
            } catch (SQLException ex) {
                Logger.getLogger(HorarioFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
            connMysql.cerrarConexion();
        } catch (IOException ex) {
            Logger.getLogger(HorarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alumnos;
    }

}
