/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.AlquilerVehiculo;
import com.entidades.Alumno;
import com.entidades.Clase;
import com.entidades.FormaPago;
import com.entidades.Ingreso;
import com.repositorios.AlquilerVehiculoFacade;
import com.repositorios.AlumnoFacade;
import com.repositorios.ClaseFacade;
import com.repositorios.FormaPagoFacade;
import com.repositorios.IngresoFacade;
import com.repositorios.ConceptoFacade;
import com.repositorios.UsuarioFacade;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author fmichel
 */
@Named(value = "alumnosMorososBean")
@ViewScoped
public class AlumnosMorososBean implements Serializable {

    
    
    
    
    
    
    

    private int cuotas;
    private double montoFinal = 0;

    

    @Inject
    private ClaseFacade claseFacade;
    private List<Clase> clasesAlumnosMorososSeleccionada;
    
    @Inject
    private IngresoFacade ingresoFacade;
    
    @Inject
    private ConceptoFacade conceptoFacade;

    @Inject
    private AlquilerVehiculoFacade alquilerVehiculoFacade;
    private List<AlquilerVehiculo> alquilerVehiculoListSeleccionado;
    
     @Inject
    private AlumnoFacade alumnoFacade;
    private List<Alumno> alumnosMorosos;
    private Alumno alumnoSeleccionado;

    @Inject
    private FormaPagoFacade formaPagoFacade;
    private List<FormaPago> formasDePago;
    private FormaPago formaDePago;
    
    @Inject
    private UsuarioFacade usuarioFacade;
    

    /**
     * Creates a new instance of AlumnosMorososBean
     */
    public AlumnosMorososBean() {
    }

    public void onLoadMorosos() {
        
        setAlumnosMorosos(alumnoFacade.getAlumnosMorosos());
        formasDePago = formaPagoFacade.findAll();

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
     * @return the formaPagoFacade
     */
    public FormaPagoFacade getFormaPagoFacade() {
        return formaPagoFacade;
    }

    /**
     * @param formaPagoFacade the formaPagoFacade to set
     */
    public void setFormaPagoFacade(FormaPagoFacade formaPagoFacade) {
        this.formaPagoFacade = formaPagoFacade;
    }

    /**
     * @return the formasDePago
     */
    public List<FormaPago> getFormasDePago() {
        return formasDePago;
    }

    /**
     * @param formasDePago the formasDePago to set
     */
    public void setFormasDePago(List<FormaPago> formasDePago) {
        this.formasDePago = formasDePago;
    }

    /**
     * @return the formaDePago
     */
    public FormaPago getFormaDePago() {
        return formaDePago;
    }

    /**
     * @param formaDePago the formaDePago to set
     */
    public void setFormaDePago(FormaPago formaDePago) {
        this.formaDePago = formaDePago;
    }

    /**
     * @return the clasesAlumnosMorososSeleccionada
     */
    public List<Clase> getClasesAlumnosMorososSeleccionada() {
        return clasesAlumnosMorososSeleccionada;
    }

    /**
     * @param clasesAlumnosMorososSeleccionada the
     * clasesAlumnosMorososSeleccionada to set
     */
    public void setClasesAlumnosMorososSeleccionada(List<Clase> clasesAlumnosMorososSeleccionada) {
        this.clasesAlumnosMorososSeleccionada = clasesAlumnosMorososSeleccionada;
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

    /**
     * @return the alquilerVehiculoListSeleccionado
     */
    public List<AlquilerVehiculo> getAlquilerVehiculoListSeleccionado() {
        return alquilerVehiculoListSeleccionado;
    }

    /**
     * @param alquilerVehiculoListSeleccionado the
     * alquilerVehiculoListSeleccionado to set
     */
    public void setAlquilerVehiculoListSeleccionado(List<AlquilerVehiculo> alquilerVehiculoListSeleccionado) {
        this.alquilerVehiculoListSeleccionado = alquilerVehiculoListSeleccionado;
    }

    public void calcularMonto() {
      
        
        
     double valorClase = conceptoFacade.getValorHoraClase().getValor();
     double valorVehiculo = conceptoFacade.getValorHoraVehiculo().getValor();
     
     montoFinal = (clasesAlumnosMorososSeleccionada.size() * valorClase) + (alquilerVehiculoListSeleccionado.size() * valorVehiculo);
       
    
        
    if(formaDePago.getPorcentajeRecargo()>0){
        double porcentaje = (formaDePago.getPorcentajeRecargo()*0.01)+1;
        montoFinal = montoFinal * porcentaje;
     }
     montoFinal =(double) Math.round(montoFinal);
     
    }

    public void efectuarPago() {
        Ingreso ingreso = new Ingreso();
        ingreso.setIdFormaPago(formaDePago);
        ingreso.setCuotas(cuotas);
        ingreso.setMonto(montoFinal);
        ingreso.setFecha(new Date());
        ingreso.setClaseCollection(clasesAlumnosMorososSeleccionada);
        ingreso.setAlquilerVehiculoCollection(alquilerVehiculoListSeleccionado);
        ingreso.setIdUsuario(usuarioFacade.getUsuario(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()));
        ingresoFacade.create(ingreso);
        
        
        for (AlquilerVehiculo alquilerVehiculoListSeleccionado1 : alquilerVehiculoListSeleccionado) {
            alquilerVehiculoListSeleccionado1.setIdIngreso(ingreso);
            alquilerVehiculoFacade.edit(alquilerVehiculoListSeleccionado1);
        }
        
        for (Clase clasesAlumnosMorososSeleccionada1 : clasesAlumnosMorososSeleccionada) {
            clasesAlumnosMorososSeleccionada1.setIdIngreso(ingreso);
            claseFacade.edit(clasesAlumnosMorososSeleccionada1);
        }
        
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "El Pago fue registrado exitosamente"));

        
        
    }

    /**
     * @return the ingresoFacade
     */
    public IngresoFacade getIngresoFacade() {
        return ingresoFacade;
    }

    /**
     * @param ingresoFacade the ingresoFacade to set
     */
    public void setIngresoFacade(IngresoFacade ingresoFacade) {
        this.ingresoFacade = ingresoFacade;
    }

    /**
     * @return the cuotas
     */
    public int getCuotas() {
        return cuotas;
    }

    /**
     * @param cuotas the cuotas to set
     */
    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    /**
     * @return the montoFinal
     */
    public Double getMontoFinal() {
        return montoFinal;
    }

    /**
     * @param montoFinal the montoFinal to set
     */
    public void setMontoFinal(Double montoFinal) {
        this.montoFinal = montoFinal;
    }

    /**
     * @return the variableFacade
     */
    public ConceptoFacade getConceptoFacade() {
        return conceptoFacade;
    }

    /**
     * @param conceptoFacade the variableFacade to set
     */
    public void setConceptoFacade(ConceptoFacade conceptoFacade) {
        this.conceptoFacade = conceptoFacade;
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

    /**
     * @return the alumnosMorosos
     */
    public List<Alumno> getAlumnosMorosos() {
        return alumnosMorosos;
    }

    /**
     * @param alumnosMorosos the alumnosMorosos to set
     */
    public void setAlumnosMorosos(List<Alumno> alumnosMorosos) {
        this.alumnosMorosos = alumnosMorosos;
    }

    /**
     * @return the alumnoSeleccionado
     */
    public Alumno getAlumnoSeleccionado() {
        return alumnoSeleccionado;
    }

    /**
     * @param alumnoSeleccionado the alumnoSeleccionado to set
     */
    public void setAlumnoSeleccionado(Alumno alumnoSeleccionado) {
        this.alumnoSeleccionado = alumnoSeleccionado;
    }

}
