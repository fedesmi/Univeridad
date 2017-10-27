/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.clases.HorarioCompuestoAlquiler;
import com.entidades.AlquilerVehiculo;
import com.entidades.Alumno;
import com.entidades.Vehiculo;
import com.repositorios.AlquilerVehiculoFacade;
import com.repositorios.HorarioFacade;
import static com.sun.xml.bind.util.CalendarConv.formatter;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author fmichel
 */
@Named(value = "alquilerVehiculoBean")
@SessionScoped
public class AlquilerVehiculoBean implements Serializable {

    private List<HorarioCompuestoAlquiler> horarioCompuestoAlquiler;
    private HorarioCompuestoAlquiler horarioSeleccionado;
    private HorarioCompuestoAlquiler horarioSeleccionadoVer;
    private Vehiculo vehiculoSeleccionado;
    private Alumno alumno;

    @Inject
    private HorarioFacade horarioFacade;

    @Inject
    private AlquilerVehiculoFacade alquilerVehiculoFacade;
    private Date fechaConsulta = new Date();

    /**
     * Creates a new instance of AlquilerVehiculoBean
     */
    public AlquilerVehiculoBean() {
    }

    public void onload() {

       // fechaConsulta = new Date();
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fechaConsulta = sdf.parse(sdf.format(new Date()));
        } catch (ParseException ex) {
            Logger.getLogger(AlquilerVehiculoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        actualizarDisponibilidad();

    }

    public void actualizarDisponibilidad() {
        horarioCompuestoAlquiler = new ArrayList<>();
        horarioCompuestoAlquiler = horarioFacade.getHorarioVehiculosOcupados(getFechaConsulta());

    }

    /**
     * @return the horarioCompuestoAlquiler
     */
    public List<HorarioCompuestoAlquiler> getHorarioCompuestoAlquiler() {
        return horarioCompuestoAlquiler;
    }

    /**
     * @param horarioCompuestoAlquiler the horarioCompuestoAlquiler to set
     */
    public void setHorarioCompuestoAlquiler(List<HorarioCompuestoAlquiler> horarioCompuestoAlquiler) {
        this.horarioCompuestoAlquiler = horarioCompuestoAlquiler;
    }
    
     /**
     * @return the fechaConsulta
     */
    public String getFechaConsultaFormato() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(getFechaConsulta());
    }

    /**
     * @return the horarioSeleccionado
     */
    public HorarioCompuestoAlquiler getHorarioSeleccionado() {
        return horarioSeleccionado;
    }

    /**
     * @param horarioSeleccionado the horarioSeleccionado to set
     */
    public void setHorarioSeleccionado(HorarioCompuestoAlquiler horarioSeleccionado) {
        System.out.println("ouuu shit");
        this.horarioSeleccionado = horarioSeleccionado;
    }

    /**
     * @return the horarioSeleccionadoVer
     */
    public HorarioCompuestoAlquiler getHorarioSeleccionadoVer() {
        return horarioSeleccionadoVer;
    }

    /**
     * @param horarioSeleccionadoVer the horarioSeleccionadoVer to set
     */
    public void setHorarioSeleccionadoVer(HorarioCompuestoAlquiler horarioSeleccionadoVer) {
        this.horarioSeleccionadoVer = horarioSeleccionadoVer;
    }

    /**
     * @return the vehiculoSeleccionado
     */
    public Vehiculo getVehiculoSeleccionado() {
        return vehiculoSeleccionado;
    }

    /**
     * @param vehiculoSeleccionado the vehiculoSeleccionado to set
     */
    public void setVehiculoSeleccionado(Vehiculo vehiculoSeleccionado) {
        this.vehiculoSeleccionado = vehiculoSeleccionado;
    }

    /**
     * @return the fechaConsulta
     */
    public Date getFechaConsulta() {
        return fechaConsulta;
    }

    /**
     * @param fechaConsulta the fechaConsulta to set
     */
    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }
    
    
      public void crearAlquiler() {
        AlquilerVehiculo av = new AlquilerVehiculo();
        av.setIdHorario(horarioSeleccionado.getHorario());
        av.setIdAlumno(alumno);
        av.setIdVehiculo(vehiculoSeleccionado);
        av.setFecha(fechaConsulta);
        alquilerVehiculoFacade.create(av);
        actualizarDisponibilidad();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "El alquiler fue dado de alta Exitosamente"));
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
     * @return the alquilerVehiculoFacade
     */
    public AlquilerVehiculoFacade getAlquilerVehiculoFacade() {
        return alquilerVehiculoFacade;
    }

    /**
     * @param alquilerVehiculoFacade the alquilerVehiculoFacade to set
     */
    public void setAlquilerVehiculoFacade(AlquilerVehiculoFacade alquilerVehiculoFacade) {
        this.alquilerVehiculoFacade = alquilerVehiculoFacade;
    }

}
