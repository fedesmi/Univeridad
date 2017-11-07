/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.entidades.Egreso;
import com.entidades.Empleado;
import com.entidades.ItemRecibo;
import com.entidades.Liquidacion;
import com.entidades.Usuario;
import com.repositorios.ClaseFacade;
import com.repositorios.EgresoFacade;
import com.repositorios.EmpleadoFacade;
import com.repositorios.LiquidacionFacade;
import com.repositorios.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;

/**
 *
 * @author fmichel
 */
@Named(value = "liquidacionSueldosBean")
@SessionScoped
public class LiquidacionSueldosBean implements Serializable {
    
    
    private int mesSeleccionado;
    private int yearSeleccionado=Calendar.getInstance().get(Calendar.YEAR);
    private EmpleadoFacade  empleadoFacade;
    private UsuarioFacade usuarioFacade;
    private EgresoFacade egresoFacade;
    private LiquidacionFacade liquidacionFacade;
    private ClaseFacade claseFacade;

    /**
     * Creates a new instance of LiquidacionSueldosBean
     */
    public LiquidacionSueldosBean() {
        System.out.println("anio "+yearSeleccionado);
    }

    /**
     * @return the mesSeleccionado
     */
    public int getMesSeleccionado() {
        return mesSeleccionado;
    }

    /**
     * @param mesSeleccionado the mesSeleccionado to set
     */
    public void setMesSeleccionado(int mesSeleccionado) {
        this.mesSeleccionado = mesSeleccionado;
    }

    /**
     * @return the yearSeleccionado
     */
    public int getYearSeleccionado() {
        return yearSeleccionado;
    }

    /**
     * @param yearSeleccionado the yearSeleccionado to set
     */
    public void setYearSeleccionado(int yearSeleccionado) {
        this.yearSeleccionado = yearSeleccionado;
    }
    
    public void liquidar(){
        List<Empleado> empleados = empleadoFacade.todosSinFechaBaja();
        
        
        for (Empleado empleado : empleados) {
            
             int clasesDadas = claseFacade.getCantidadClasesByInstructorAndMes(empleado, mesSeleccionado);
            
             
             float sueldoBase=empleado.getIdTipoEmpleado().getSueldoBase();
             float total = sueldoBase;
             for (ItemRecibo item : empleado.getIdTipoEmpleado().getItemReciboCollection()) {
                 if(item.getItem().equals("Clase")){
                     total = total + ((item.getPorcentaje() * sueldoBase)*clasesDadas);
                 }
                 total = total +(item.getPorcentaje() * sueldoBase);
             }
             
             
             
             Egreso egreso = new Egreso();
             egreso.setFecha(new Date());        
             egreso.setIdUsuario(usuarioFacade.getUsuario(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()));
             egreso.setMonto(total);
             egreso.setConcepto("SUELDO");
             egreso.setMonto(empleado.getIdTipoEmpleado().getSueldoBase());
             
             egresoFacade.create(egreso);
             
             
             
             Liquidacion liquidacion = new Liquidacion();
             liquidacion.setIdEmpleado(empleado);
             liquidacion.setIdUsuario(empleado.getUsuario());
             liquidacion.setIdEgreso(egreso);
             liquidacion.setSueldoBase(sueldoBase);
             liquidacion.setYear(yearSeleccionado);
             liquidacion.setMes(mesSeleccionado);
             
             liquidacionFacade.create(liquidacion);
             
             
             
        }
    }
    
}
