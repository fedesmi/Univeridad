/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositorios;

import com.clases.HorarioCompuesto;
import com.clases.HorarioCompuestoAlquiler;
import com.controladores.ConexionBaseDatos;
import com.entidades.AlquilerVehiculo;
import com.entidades.Alumno;
import com.entidades.Clase;
import com.entidades.Empleado;
import com.entidades.Horario;
import com.entidades.ListaEsperaClase;
import com.entidades.TipoEmpleado;
import com.entidades.Vehiculo;
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
import javax.inject.Inject;
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
    @Inject
    private ListaEsperaClaseFacade lef;

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

        List<HorarioCompuesto> horarios = new ArrayList<>();
        List<Horario> horariosS = getHorariosByDiaSemana(fecha);

        for (Horario horariosS1 : horariosS) {
            horarioCompuesto = new HorarioCompuesto();
            horarioCompuesto.setHorario(horariosS1);
            horarioCompuesto.setAlquileresVehiculo(getAlquilerByFechaHorario(fecha, horariosS1));
            horarioCompuesto.setClases(getClasesHorario(fecha, horariosS1.getId()));
            horarioCompuesto.setListaEsperaClase(getListaEsperaHorario(fecha, horariosS1.getId()));
            horarioCompuesto.setInstructoresDispoinibles(getInstructoresDisponiblesHorario(fecha, horariosS1.getId()));
            
            
            horarioCompuesto.setAlumnosDisponibles(getAlumnosDisponiblesHorario(fecha, horariosS1.getId()));
            
            
            
            horarios.add(horarioCompuesto);
        }

        return horarios;

    }

    public List<Empleado> getInstructoresDisponiblesHorario(Date fecha, Integer id) {
        List<Empleado> empleados = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {

            String consulta = "SELECT e.id, e.legajo, e.dni, e.nombre, e.apellido, e.telefono, "
                    + "e.fechaAlta, e.fechaBaja, e.autorizo, te.id, te.rol, te.sueldoBase "
                    + "FROM empleado as e, tipo_empleado as te "
                    + "WHERE e.fechaBaja  IS NULL AND e.autorizo IS NOT NULL AND e.idtipoempleado = 3 "
                    + "AND e.id NOT IN (SELECT c.id_instructor FROM clase as c, horario as ho  "
                    + "WHERE c.id_horario=ho.id AND c.fecha='" + sdf.format(fecha) + "' AND c.fecha_cancelado IS NULL  AND ho.id = " + id + " ) " 
                    + "AND e.id NOT IN (SELECT ve.id_empleado FROM vehiculo AS ve, alquiler_vehiculo AS av "
                                    + "WHERE ve.id = av.id_vehiculo AND av.fecha='" + sdf.format(fecha) + "' AND av.fecha_cancelado IS NULL  AND av.id_horario = " + id + "  )"
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
                    tipoEmpleadoVar.setSueldoBase(resultado.getFloat(12));
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

    public List<Alumno> getAlumnosDisponiblesHorario(Date fecha, Integer id) {
        List<Alumno> alumnos = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {

            String //ALUMNOS DISPONIBLES
                    consulta = "SELECT al.id, al.dni, al.nombre, al.apellido, al.fecha_nacimiento, al.telefono, al.email  "
                    + "FROM alumno AS al where al.id NOT IN "
                    + "(SELECT c.id_alumno FROM clase as c, horario as ho  "
                    + "WHERE c.id_horario=ho.id AND c.fecha='" + sdf.format(fecha) + "' AND c.fecha_cancelado IS NULL AND ho.id = " + id + " ) "
                    + "AND al.id NOT IN (SELECT av.id_alumno FROM alquiler_vehiculo AS av  "
                                    + "WHERE av.fecha='" + sdf.format(fecha) + "' AND av.id_horario = " + id + "  ) ";

           
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
                    alumnoVar.setTelefono(resultado.getString(6));
                    alumnoVar.setEmail(resultado.getString(7));
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

    private List<ListaEsperaClase> getListaEsperaHorario(Date fecha, int horarioPar) {
        List<ListaEsperaClase> espera = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {

            String //LISTA DE ESPERA DE ALUMNOS EN UN HORARIO
                    consulta = "SELECT lec.id, lec.fecha_inscripcion, lec.fecha_clase, "
                    + "al.id, al.dni, al.nombre, al.apellido, al.fecha_nacimiento, al.telefono, al.email, "
                    + "hor.id, hor.inicio, hor.fin, hor.dia_semana "
                    + "FROM lista_espera_clase AS lec, alumno as al, horario as hor "
                    + "WHERE lec.fecha_clase = '" + sdf.format(fecha) + "' "
                    + "AND lec.id_horario = '" + horarioPar + "'  "
                    + "AND lec.id_alumno = al.id "
                    + "AND lec.id_horario = hor.id "
                    + "ORDER BY lec.fecha_inscripcion";

            ConexionBaseDatos connMysql = new ConexionBaseDatos();
            java.sql.ResultSet resultado = connMysql.ejecutarConsulta(consulta);
            ListaEsperaClase lec;
            try {
                while (resultado.next()) {
                    lec = new ListaEsperaClase();
                    lec.setId(resultado.getInt(1));
                    lec.setFechaInscripcion(resultado.getDate(2));
                    lec.setFechaClase(resultado.getDate(3));

                    Alumno alumnoVar = new Alumno();
                    alumnoVar.setId(resultado.getInt(4));
                    alumnoVar.setDni(resultado.getInt(5));
                    alumnoVar.setNombre(resultado.getString(6));
                    alumnoVar.setApellido(resultado.getString(7));
                    alumnoVar.setFechaNacimiento(resultado.getDate(8));
                    alumnoVar.setTelefono(resultado.getString(9));
                    alumnoVar.setEmail(resultado.getString(10));

                    Horario horarioVar = new Horario();
                    horarioVar.setId(resultado.getInt(11));
                    horarioVar.setInicio(resultado.getDate(12));
                    horarioVar.setFin(resultado.getDate(13));
                    horarioVar.setDiaSemana(resultado.getShort(14));

                    lec.setIdAlumno(alumnoVar);
                    lec.setIdHorario(horarioVar);
                    espera.add(lec);
                }
            } catch (SQLException ex) {
                Logger.getLogger(HorarioFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
            connMysql.cerrarConexion();
        } catch (IOException ex) {
            Logger.getLogger(HorarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return espera;
    }

    private List<Clase> getClasesHorario(Date fecha, int horarioPar) {
        List<Clase> clases = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {

            String 
                    consulta = "SELECT lec.id, lec.fecha, "
                    + "al.id, al.nombre, al.apellido, al.telefono, al.email, "
                    + "hor.id, hor.inicio, hor.fin, hor.dia_semana, "
                    + "ins.id, ins.nombre, ins.apellido "
                    + "FROM clase AS lec, alumno as al, horario as hor, empleado as ins "
                    + "WHERE lec.fecha = '" + sdf.format(fecha) + "' "
                    + "AND lec.id_horario = '" + horarioPar + "'  "
                    + "AND lec.fecha_cancelado IS NULL "
                    + "AND lec.id_alumno = al.id "
                    + "AND lec.id_horario = hor.id "
                    + "AND lec.id_instructor = ins.id ";

            ConexionBaseDatos connMysql = new ConexionBaseDatos();
            java.sql.ResultSet resultado = connMysql.ejecutarConsulta(consulta);
            Clase lec;
            try {
                while (resultado.next()) {
                    lec = new Clase();
                    lec.setId(resultado.getInt(1));
                    lec.setFecha(resultado.getDate(2));

                    Alumno alumnoVar = new Alumno();
                    alumnoVar.setId(resultado.getInt(3));
                    alumnoVar.setNombre(resultado.getString(4));
                    alumnoVar.setApellido(resultado.getString(5));
                    alumnoVar.setTelefono(resultado.getString(6));
                    alumnoVar.setEmail(resultado.getString(7));

                    Horario horarioVar = new Horario();
                    horarioVar.setId(resultado.getInt(8));
                    horarioVar.setInicio(resultado.getDate(9));
                    horarioVar.setFin(resultado.getDate(10));
                    horarioVar.setDiaSemana(resultado.getShort(11));

                    Empleado empleadoVar = new Empleado();
                    empleadoVar.setId(resultado.getInt(12));
                    empleadoVar.setNombre(resultado.getString(13));
                    empleadoVar.setApellido(resultado.getString(14));

                    lec.setIdAlumno(alumnoVar);
                    lec.setIdHorario(horarioVar);
                    lec.setIdInstructor(empleadoVar);
                    clases.add(lec);
                }
            } catch (SQLException ex) {
                Logger.getLogger(HorarioFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
            connMysql.cerrarConexion();
        } catch (IOException ex) {
            Logger.getLogger(HorarioFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clases;
    }

    public List<HorarioCompuestoAlquiler> getHorarioVehiculosOcupados(Date fecha) {

        HorarioCompuestoAlquiler horarioCompuestoA;
        List<HorarioCompuestoAlquiler> horariosC = new ArrayList<>();
        List<Horario> horarios = getHorariosByFecha(fecha);

        for (Horario horario1 : horarios) {
            horarioCompuestoA = new HorarioCompuestoAlquiler();
            horarioCompuestoA.setHorario(horario1);
            horarioCompuestoA.setVehiculosOcupados(getVehiculosOcupados(fecha, horario1));
            horarioCompuestoA.setVehiculosLibres(getVehiculosAlquilerLibres(fecha, horario1));
            horarioCompuestoA.setAlumnosDisponibles(getAlumnosDisponiblesHorario(fecha, horario1.getId()));
            horarioCompuestoA.setAlquileres(getAlquilerByFechaHorario(fecha, horario1));
            horariosC.add(horarioCompuestoA);
        }

        return horariosC;
    }

    public List<Horario> getHorariosByFecha(Date fecha) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        int diaSemana = c.get(Calendar.DAY_OF_WEEK);
        return getEntityManager().createNamedQuery("Horario.findByDiaSemana").setParameter("diaSemana", diaSemana).getResultList();
    }

    public List<Vehiculo> getVehiculosOcupados(Date fecha, Horario horario) {
        return getEntityManager().createNamedQuery("Vehiculo.findOcupadosByFechayHorario").setParameter("fecha", fecha).setParameter("horario", horario).getResultList();
    }

    public List<Vehiculo> getVehiculosAlquilerLibres(Date fecha, Horario horario) {
        return getEntityManager().createNamedQuery("Vehiculo.findbyAlquilerLibres").setParameter("fecha", fecha).setParameter("horario", horario).getResultList();
    }

    public List<AlquilerVehiculo> getAlquilerByFechaHorario(Date fecha, Horario horario) {
        return getEntityManager().createNamedQuery("AlquilerVehiculo.findByFechaYHorario").setParameter("fecha", fecha).setParameter("horario", horario).getResultList();
    }



}
