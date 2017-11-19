/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.clases.ReciboReporte;
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
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.primefaces.model.StreamedContent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import javax.servlet.ServletOutputStream;
import net.sf.jasperreports.engine.JasperExportManager;

/**
 *
 * @author fmichel
 */
@Named(value = "liquidacionSueldosBean")
@SessionScoped
public class LiquidacionSueldosBean implements Serializable {

    //private static final Logger log = (Logger) LoggerFactory.getLogger(LiquidacionSueldosBean.class);
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
    private Liquidacion liquidacionSeleccionadaPDF;
    private StreamedContent media;
    private ByteArrayOutputStream outputStream;
    private String number;

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
                    ReciboSueldo recibo = new ReciboSueldo();

                    float subtotal = (item.getPorcentaje() * sueldoBase);
                    if (item.getItem().equals("Clase")) {
                        subtotal = subtotal * clasesDadas;
                        total = total + (subtotal);
                        recibo.setUnidades(clasesDadas);
                    } else {
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

                egreso.setMonto(total);
                egreso.setConcepto("SUELDO");

                egresoFacade.create(egreso);
                Usuario usuario = usuarioFacade.getUsuario(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());

                Liquidacion liquidacion = new Liquidacion();
                liquidacion.setIdEmpleado(empleado);
                liquidacion.setIdUsuario(usuario);
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

    public void printPDF(Liquidacion liqui) throws JRException, IOException {
        List<ReciboReporte> dataSource = new ArrayList<>();
        if (liqui!= null) {
            for (ReciboSueldo recibo : liqui.getReciboSueldoCollection()) {
                dataSource.add(new ReciboReporte(recibo.getIdItem().getItem(), recibo.getIdItem().getPorcentaje(), recibo.getUnidades(), recibo.getMonto()));
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String filename = getNombreMes(liqui.getMes())+"_"+liqui.getIdEmpleado().getApellido()+"_recibo.pdf";
            String jasperPath = "/resources/report/recibo.jasper";

            Map<String, Object> hm = new HashMap<>();
            String valor;
            valor= String.valueOf(liqui.getIdEmpleado().getApellido() +" "+liqui.getIdEmpleado().getNombre());
            hm.put("nombreApellido", valor);
            valor= String.valueOf(liqui.getIdEmpleado().getLegajo());
            hm.put("legajo", valor);
            valor = getNombreMes(liqui.getMes());
            hm.put("mes", valor);
            valor= sdf.format(liqui.getIdEgreso().getFecha());
            hm.put("fechaPago", valor);
            valor=String.valueOf(liqui.getIdEmpleado().getDni());
            hm.put("dni", valor);
            valor=sdf.format(liqui.getIdEmpleado().getFechaAlta());
            hm.put("fechaIngreso", valor);
            valor = liqui.getIdEmpleado().getIdTipoEmpleado().getRol();
            hm.put("tipoEmpleado", valor);
            valor=String.format ("%.2f",liqui.getIdEgreso().getMonto());
            hm.put("total", valor);
            URL url = this.getClass().getClassLoader().getResource("/img/logo.png");
            hm.put("logo", url);
            
            this.PDF(hm, jasperPath, dataSource, filename);
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "noooo"));

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

   

    /**
     * @return the liquidacionSeleccionadaPDF
     */
    public Liquidacion getLiquidacionSeleccionadaPDF() {
        return liquidacionSeleccionadaPDF;
    }

    /**
     * @param liquidacionSeleccionadaPDF the liquidacionSeleccionadaPDF to set
     */
    public void setLiquidacionSeleccionadaPDF(Liquidacion liquidacionSeleccionadaPDF) {
        this.liquidacionSeleccionadaPDF = liquidacionSeleccionadaPDF;
    }
}
