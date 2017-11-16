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
import com.entidades.ReciboSueldo;
import com.entidades.Usuario;
import com.repositorios.ClaseFacade;
import com.repositorios.EgresoFacade;
import com.repositorios.EmpleadoFacade;
import com.repositorios.LiquidacionFacade;
import com.repositorios.ReciboSueldoFacade;
import com.repositorios.UsuarioFacade;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author fmichel
 */
@Named(value = "liquidacionSueldosBean")
@SessionScoped
public class LiquidacionSueldosBean implements Serializable {

    private int mesSeleccionado;
    private int yearSeleccionado = Calendar.getInstance().get(Calendar.YEAR);

    @Inject
    private EmpleadoFacade empleadoFacade;
    @Inject
    private UsuarioFacade usuarioFacade;
    @Inject
    private EgresoFacade egresoFacade;
    @Inject
    private LiquidacionFacade liquidacionFacade;
    @Inject
    private ClaseFacade claseFacade;
    
    @Inject
    private ReciboSueldoFacade reciboSueldoFacade;

    private List<Empleado> empleados;
    private List<Liquidacion> liquidaciones;

    private Liquidacion liquidacionSeleccionada;
    /**
     * Creates a new instance of LiquidacionSueldosBean
     */
    public LiquidacionSueldosBean() {
        
    }

    public void onLoadLiquidacion() {
        liquidaciones = liquidacionFacade.findAll();
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

    public void liquidar() {

        if (!isLiquidado(mesSeleccionado, yearSeleccionado)) {
            empleados = empleadoFacade.todosSinFechaBaja();
            for (Empleado empleado : empleados) {
                int clasesDadas = claseFacade.getCantidadClasesByInstructorAndMes(empleado, mesSeleccionado);
                float sueldoBase = empleado.getIdTipoEmpleado().getSueldoBase();
                float total = 0;
                List<ReciboSueldo> recibos = new ArrayList<>();
                
                
                for (ItemRecibo item : empleado.getIdTipoEmpleado().getItemReciboCollection()) {
                    ReciboSueldo recibo= new ReciboSueldo();
                    
                     float subtotal = (item.getPorcentaje() * sueldoBase);
                    if (item.getItem().equals("Clase")) {
                        subtotal = subtotal * clasesDadas;
                        total = total + (subtotal);
                        recibo.setUnidades(clasesDadas);   
                    }else{
                        recibo.setUnidades(1);
                        total = total + subtotal;
                    }
                      recibo.setMonto(subtotal);
                      recibo.setIdItem(item);
                      recibos.add(recibo);
                      //reciboSueldoFacade.
                }
               
                Egreso egreso = new Egreso();
                egreso.setFecha(new Date());
                Usuario usuario = usuarioFacade.getUsuario(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
                egreso.setIdUsuario(usuario);
                egreso.setMonto(total);
                egreso.setConcepto("SUELDO");
     

                egresoFacade.create(egreso);

                Liquidacion liquidacion = new Liquidacion();
                liquidacion.setIdEmpleado(empleado);
                liquidacion.setIdUsuario(empleado.getUsuario());
                liquidacion.setIdEgreso(egreso);
                liquidacion.setSueldoBase(sueldoBase);
                liquidacion.setYear(yearSeleccionado);
                liquidacion.setMes(mesSeleccionado);
               
                liquidacionFacade.create(liquidacion);
                for (ReciboSueldo recibo : recibos) {
                    recibo.setIdLiquidacion(liquidacion);
                    reciboSueldoFacade.create(recibo);
                }
 
                

            }
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "La liquidacion se realizó exitosamente"));

        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "la liquidacion solicitada ya existe"));

        }
        onLoadLiquidacion();
    }

    /**
     * @return the liquidaciones
     */
    public List<Liquidacion> getLiquidaciones() {
        return liquidaciones;
    }

    /**
     * @param liquidaciones the liquidaciones to set
     */
    public void setLiquidaciones(List<Liquidacion> liquidaciones) {
        this.liquidaciones = liquidaciones;
    }

    public String getNombreMes(int mes) {

        switch (mes) {
            case 1:
                return "ENERO";
            case 2:
                return "FEBRERO";
            case 3:
                return "MARZO";
            case 4:
                return "ABRIL";
            case 5:
                return "MAYO";
            case 6:
                return "JUNIO";
            case 7:
                return "JULIO";
            case 8:
                return "AGOSTO";
            case 9:
                return "SEPTIEMBRE";
            case 10:
                return "OCTUBRE";
            case 11:
                return "NOVIEMBRE";
            case 12:
                return "DICIEMBRE";

        }
        return "";
    }

    private boolean isLiquidado(int mesSeleccionado, int yearSeleccionado) {
        return liquidacionFacade.isLiquidacion(mesSeleccionado, yearSeleccionado);
    }

    /**
     * @return the liquidacionSeleccionada
     */
    public Liquidacion getLiquidacionSeleccionada() {
        return liquidacionSeleccionada;
    }

    /**
     * @param liquidacionSeleccionada the liquidacionSeleccionada to set
     */
    public void setLiquidacionSeleccionada(Liquidacion liquidacionSeleccionada) {
        this.liquidacionSeleccionada = liquidacionSeleccionada;
    }

}
