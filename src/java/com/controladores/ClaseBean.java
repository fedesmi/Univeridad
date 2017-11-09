/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import com.clases.HorarioCompuesto;
import com.entidades.Alumno;
import com.entidades.Clase;
import com.entidades.Empleado;
import com.entidades.ListaEsperaClase;
import com.repositorios.AlumnoFacade;
import com.repositorios.ClaseFacade;
import com.repositorios.EmpleadoFacade;
import com.repositorios.FormaPagoFacade;
import com.repositorios.HorarioFacade;
import com.repositorios.ListaEsperaClaseFacade;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author fmichel
 */
@Named(value = "claseBean")
@SessionScoped
public class ClaseBean implements Serializable {

    @Inject
    private EmpleadoFacade empleadoFacade;
    @Inject
    private AlumnoFacade alumnoFacade;
    @Inject
    private HorarioFacade horarioFacade;
    @Inject
    private ClaseFacade claseFacade;
    @Inject
    private ListaEsperaClaseFacade listaEsperaClaseFacade;
 

    private Date fechaConsulta = new Date();
    private List<HorarioCompuesto> disponibilidadHora;
    private List<Alumno> alumnos;
    private HorarioCompuesto horarioSeleccionado;
    private HorarioCompuesto horarioSeleccionadoVer;
    private HorarioCompuesto horarioSeleccionadoEspera;

    private Empleado instructor;
    private Alumno alumno;
    private Alumno alumnoEspera;

    private List<ListaEsperaClase> listaDeEspera;

    /**
     * Creates a new instance of ClaseBean
     */
    public ClaseBean() {
    }

    // This is the required method to get the datatable list.
    @PostConstruct
    public void init() {

    }

    public void onload() {
        fechaConsulta = new Date();
        actualizarDisponibilidad();

    }

    public void actualizarDisponibilidad() {
        disponibilidadHora = new ArrayList<>();
        disponibilidadHora = horarioFacade.getHorariosOcupados(fechaConsulta);
        buscarListaAlumnos();
    }

    public void buscarListaAlumnos() {
        alumnos = alumnoFacade.findAll();
    }

    public List<HorarioCompuesto> getDisponibilidadHorariaPorFecha() {
        return disponibilidadHora;
    }

    /**
     * @return the empleadoFacade
     */
    public EmpleadoFacade getEmpleadoFacade() {
        return empleadoFacade;
    }

    /**
     * @param empleadoFacade the empleadoFacade to set
     */
    public void setEmpleadoFacade(EmpleadoFacade empleadoFacade) {
        this.empleadoFacade = empleadoFacade;
    }

    /**
     * @return the claseFacade
     */
    public ClaseFacade getClaseFacade() {
        return claseFacade;
    }

    /**
     * @param claseFacade the claseFacade to set
     */
    public void setClaseFacade(ClaseFacade claseFacade) {
        this.claseFacade = claseFacade;
    }

    /**
     * @return the horarioFacade
     */
    public HorarioFacade getHorarioFacade() {
        return horarioFacade;
    }

    /**
     * @param horarioFacade the horarioFacade to set
     */
    public void setHorarioFacade(HorarioFacade horarioFacade) {
        this.horarioFacade = horarioFacade;
    }

    /**
     * @return the fechaConsulta
     */
    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    /**
     * @return the fechaConsulta
     */
    public String getFechaConsultaFormato() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fechaConsulta);
    }

    /**
     * @param fechaConsulta the fechaConsulta to set
     */
    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    /**
     * @return the disponibilidadHora
     */
    public List<HorarioCompuesto> getDisponibilidadHora() {
        return disponibilidadHora;
    }

    /**
     * @param disponibilidadHora the disponibilidadHora to set
     */
    public void setDisponibilidadHora(List<HorarioCompuesto> disponibilidadHora) {
        this.disponibilidadHora = disponibilidadHora;
    }

    /**
     * @return the horarioSeleccionado
     */
    public HorarioCompuesto getHorarioSeleccionado() {
        return horarioSeleccionado;
    }

    /**
     * @param horarioSeleccionado the horarioSeleccionado to set
     */
    public void setHorarioSeleccionado(HorarioCompuesto horarioSeleccionado) {
        this.horarioSeleccionado = horarioSeleccionado;

    }

    /**
     * @return the instructor
     */
    public Empleado getInstructor() {
        return instructor;
    }

    /**
     * @param instructor the instructor to set
     */
    public void setInstructor(Empleado instructor) {
        this.instructor = instructor;
    }

    /**
     * @return the alumno
     */
    public Alumno getAlumno() {
        return alumno;
    }

    /**
     * @param alumno the alumno to set
     */
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    /**
     * @return the alumnoFacade
     */
    public AlumnoFacade getAlumnoFacade() {
        return alumnoFacade;
    }

    /**
     * @param alumnoFacade the alumnoFacade to set
     */
    public void setAlumnoFacade(AlumnoFacade alumnoFacade) {
        this.alumnoFacade = alumnoFacade;
    }

    public void crearClaseEspera() {
        ListaEsperaClase espera = new ListaEsperaClase();
        espera.setIdHorario(horarioSeleccionadoEspera.getHorario());
        espera.setIdAlumno(alumnoEspera);
        espera.setFechaClase(fechaConsulta);
        espera.setFechaInscripcion(new Date());

        listaEsperaClaseFacade.create(espera);
        horarioSeleccionadoEspera=null;
        actualizarDisponibilidad();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se agrego al alumno a la lista de espera"));

    }

    public void crearClase() {
        Clase clase = new Clase();
        clase.setIdHorario(horarioSeleccionado.getHorario());

        clase.setIdAlumno(alumno);
        clase.setIdInstructor(instructor);
        clase.setFecha(fechaConsulta);
        claseFacade.create(clase);
        actualizarDisponibilidad();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "La clase fue dada de alta Exitosamente"));
    }

    /**
     * @return the listaEsperaClaseFacade
     */
    public ListaEsperaClaseFacade getListaEsperaClaseFacade() {
        return listaEsperaClaseFacade;
    }

    /**
     * @param listaEsperaClaseFacade the listaEsperaClaseFacade to set
     */
    public void setListaEsperaClaseFacade(ListaEsperaClaseFacade listaEsperaClaseFacade) {
        this.listaEsperaClaseFacade = listaEsperaClaseFacade;
    }

    /**
     * @return the alumnos
     */
    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    /**
     * @param alumnos the alumnos to set
     */
    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    /**
     * @return the horarioSeleccionadoEspera
     */
    public HorarioCompuesto getHorarioSeleccionadoEspera() {
        return horarioSeleccionadoEspera;
    }

    /**
     * @param horarioSeleccionadoEspera the horarioSeleccionadoEspera to set
     */
    public void setHorarioSeleccionadoEspera(HorarioCompuesto horarioSeleccionadoEspera) {
        this.horarioSeleccionadoEspera = horarioSeleccionadoEspera;
    }

    /**
     * @return the alumnoEspera
     */
    public Alumno getAlumnoEspera() {
        return alumnoEspera;
    }

    /**
     * @param alumnoEspera the alumnoEspera to set
     */
    public void setAlumnoEspera(Alumno alumnoEspera) {
        this.alumnoEspera = alumnoEspera;
    }

    /**
     * @return the listaDeEspera
     */
    public List<ListaEsperaClase> getListaDeEspera() {
        return listaDeEspera;
    }

    /**
     * @param listaDeEspera the listaDeEspera to set
     */
    public void setListaDeEspera(List<ListaEsperaClase> listaDeEspera) {
        this.listaDeEspera = listaDeEspera;
    }

    /**
     * @return the horarioSeleccionadoVer
     */
    public HorarioCompuesto getHorarioSeleccionadoVer() {
        return horarioSeleccionadoVer;
    }

    /**
     * @param horarioSeleccionadoVer the horarioSeleccionadoVer to set
     */
    public void setHorarioSeleccionadoVer(HorarioCompuesto horarioSeleccionadoVer) {
        this.horarioSeleccionadoVer = horarioSeleccionadoVer;
    }


}
