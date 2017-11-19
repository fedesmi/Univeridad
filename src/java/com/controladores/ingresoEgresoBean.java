/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.clases.EgresoReporte;
import com.clases.IngresoReporte;
import com.clases.ReciboReporte;
import com.entidades.Egreso;
import com.entidades.Ingreso;
import com.entidades.Liquidacion;
import com.entidades.ReciboSueldo;
import com.repositorios.EgresoFacade;
import com.repositorios.IngresoFacade;
import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Usuario
 */
@Named(value = "ingresoEgresoBean")
@SessionScoped
public class ingresoEgresoBean implements Serializable {

    @Inject
    private IngresoFacade ingresoFacade;
    @Inject
    private EgresoFacade egresoFacade;
    private List<Ingreso> ingresosDia;
    private List<Egreso> egresosDia;
    private double totalIngresos;
    private double totalEgresos;

    private Date fechaDesde;
    private Date fechaHasta;

    /**
     * Creates a new instance of ingresoEgresoBean
     */
    public ingresoEgresoBean() {
    }

    public void onLoadIngresosEgresos() {
        fechaDesde = new Date();
        fechaHasta = new Date();

        filtrar();
    }

    /**
     * @return the ingresosDia
     */
    public List<Ingreso> getIngresosDia() {
        return ingresosDia;
    }

    /**
     * @param ingresosDia the ingresosDia to set
     */
    public void setIngresosDia(List<Ingreso> ingresosDia) {
        this.ingresosDia = ingresosDia;
    }

    /**
     * @return the egresosDia
     */
    public List<Egreso> getEgresosDia() {
        return egresosDia;
    }

    /**
     * @param egresosDia the egresosDia to set
     */
    public void setEgresosDia(List<Egreso> egresosDia) {
        this.egresosDia = egresosDia;
    }

    /**
     * @return the totalIngresos
     */
    public double getTotalIngresos() {
        return totalIngresos;
    }

    /**
     * @param totalIngresos the totalIngresos to set
     */
    public void setTotalIngresos(double totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    /**
     * @return the totalEgresos
     */
    public double getTotalEgresos() {
        return totalEgresos;
    }

    /**
     * @param totalEgresos the totalEgresos to set
     */
    public void setTotalEgresos(double totalEgresos) {
        this.totalEgresos = totalEgresos;
    }

    /**
     * @return the fechaDesde
     */
    public Date getFechaDesde() {
        return fechaDesde;
    }

    /**
     * @param fechaDesde the fechaDesde to set
     */
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * @return the fechaHasta
     */
    public Date getFechaHasta() {
        return fechaHasta;
    }

    /**
     * @param fechaHasta the fechaHasta to set
     */
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public void filtrar() {
        Calendar cal= Calendar.getInstance();
        cal.setTime(fechaHasta);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        fechaHasta = cal.getTime();
        cal.setTime(fechaDesde);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        fechaDesde = cal.getTime();
   
        ingresosDia = ingresoFacade.getIngresosByFechas(fechaDesde, fechaHasta);
        egresosDia = egresoFacade.getEgresosByFechas(fechaDesde, fechaHasta);
        setTotalEgresos(egresoFacade.getTotalEgresos(fechaDesde, fechaHasta));
        setTotalIngresos(ingresoFacade.getTotalIngresos(fechaDesde, fechaHasta));
    }
    
    
    
    
    public void printPDFIngresos() throws JRException, IOException {
        List<IngresoReporte> dataSource = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        if (ingresosDia!= null) {
         for (Ingreso ingreso : ingresosDia) {
                dataSource.add(new IngresoReporte(sdf.format(ingreso.getFecha()),
                        ingreso.getIdFormaPago().getDescripcion(), String.valueOf(ingreso.getCuotas()), 
                        String.valueOf(ingreso.getMonto()), String.valueOf(ingreso.getClaseCollection().size()), 
                        String.valueOf(ingreso.getAlquilerVehiculoCollection().size())));
            }
            
            
            String filename = "Ingresos_"+sdf.format(fechaDesde)+"_"+sdf.format(fechaHasta)+".pdf";
            String jasperPath = "/resources/report/Ingresos.jasper";

            Map<String, Object> hm = new HashMap<>();
           
            hm.put("fechaDesde", sdf.format(fechaDesde));
            hm.put("fechaHasta", sdf.format(fechaHasta));
           
            this.PDF(hm, jasperPath, dataSource, filename);
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Error al Genrara PDF"));

        }
    }
    
     public void printPDFEgresos() throws JRException, IOException {
        List<EgresoReporte> dataSource = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        if (ingresosDia!= null) {
         for (Egreso egreso : egresosDia) {
                dataSource.add(new EgresoReporte(sdf.format(egreso.getFecha()),
                        String.valueOf(egreso.getMonto()), egreso.getConcepto()));
            }
            
            
            String filename = "Egresos_"+sdf.format(fechaDesde)+"_"+sdf.format(fechaHasta)+".pdf";
            String jasperPath = "/resources/report/Egresos.jasper";

            Map<String, Object> hm = new HashMap<>();
           
            hm.put("fechaDesde", sdf.format(fechaDesde));
            hm.put("fechaHasta", sdf.format(fechaHasta));
           
            this.PDF(hm, jasperPath, dataSource, filename);
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Error al Genrara PDF"));

        }
    }

    public void PDF(Map<String, Object> params, String jasperPath, List<?> dataSource, String fileName) throws JRException, IOException {
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
        File file = new File(relativeWebPath);
        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSource, false);
        JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, source);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment;filename=" + fileName);
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(print, stream);
        FacesContext.getCurrentInstance().responseComplete();

    }

}
