/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import com.clases.HorarioCompuesto;
import com.entidades.Alumno;
import com.entidades.Empleado;
import com.entidades.Horario;
import com.repositorios.AlumnoFacade;
import com.repositorios.ClaseFacade;
import com.repositorios.EmpleadoFacade;
import com.repositorios.HorarioFacade;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author fmichel
 */
@Named(value = "claseBean")
@RequestScoped
public class ClaseBean implements Serializable {

    
    @Inject
    private EmpleadoFacade empleadoFacade;
    @Inject
    private AlumnoFacade alumnoFacade;
     @Inject
    private HorarioFacade horarioFacade;
     @Inject
    private ClaseFacade claseFacade;
    private Date fechaConsulta = new  Date();
    private List<HorarioCompuesto> disponibilidadHora;
    
    private HorarioCompuesto horarioSeleccionado;
    private Horario horarioSelected;
    
    
    private Empleado instructor;  
    private Alumno alumno;
    

    /**
     * Creates a new instance of ClaseBean
     */
    public ClaseBean() {
    }
    
     // This is the required method to get the datatable list.
    @PostConstruct
    public void init() {
        setDisponibilidadHora(getDisponibilidadHorariaPorFecha());

    }

    
    public void actualizarDisponibilidad(){
        setDisponibilidadHora(getDisponibilidadHorariaPorFecha());
    }
   
     public List<HorarioCompuesto> getDisponibilidadHorariaPorFecha() {
      disponibilidadHora = new ArrayList<>();
      disponibilidadHora = horarioFacade.getHorariosOcupados(fechaConsulta);
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

  
  public List<Alumno> getAlumnos() {
        return alumnoFacade.findAll();
    }
    
    
    public List<Empleado> getInstructores() {
        return empleadoFacade.getInstructores();
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
    
    
    public void crearClase(){
        
              
        System.out.println("horario" + horarioSelected.getId());
        //System.out.println("alu "+alumno.getApellido());   
    }

    /**
     * @return the horarioSelected
     */
    public Horario getHorarioSelected() {
        return horarioSelected;
    }

    /**
     * @param horarioSelected the horarioSelected to set
     */
    public void setHorarioSelected(Horario horarioSelected) {
        this.horarioSelected = horarioSelected;
    }
}
