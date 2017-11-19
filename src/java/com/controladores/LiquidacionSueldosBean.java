/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controladores;

import com.clases.JasperReportUtil;
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
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletResponse;
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
import java.io.OutputStream;
import javax.servlet.ServletOutputStream;
import net.sf.jasperreports.engine.JasperExportManager;
import org.mariadb.jdbc.internal.logging.LoggerFactory;

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

    /*public void viewReportPDF() {
        try {
            
            
            //Fill Map with params values
            HashMap hm = new HashMap();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            //String valor = liquidacionSeleccionadaPDF.getIdEmpleado().getApellido()+" "+ liquidacionSeleccionadaPDF.getIdEmpleado().getNombre();
            hm.put("nombreApellido","federico" );
            //valor= String.valueOf(liquidacionSeleccionadaPDF.getIdEmpleado().getLegajo());
            hm.put("legajo","925" );
            //valor = getNombreMes(liquidacionSeleccionadaPDF.getMes());
            hm.put("mes", "OCTUBRE");
            //valor= sdf.format(liquidacionSeleccionadaPDF.getIdEgreso().getFecha());
            hm.put("fechaPago", "20/11/2012");
            //valor=String.valueOf(liquidacionSeleccionadaPDF.getIdEmpleado().getDni());
            hm.put("dni", "31376213");
            //valor=sdf.format(liquidacionSeleccionadaPDF.getIdEmpleado().getFechaAlta());
            hm.put("fechaIngreso", "22/1/2015");
           // hm.put("tipoEmpleado", liquidacionSeleccionadaPDF.getIdEmpleado().getIdTipoEmpleado().getRol());
            hm.put("tipoEmpleado", "gerente");
            //valor=String.format ("%.2f",liquidacionSeleccionadaPDF.getIdEgreso().getMonto());
            hm.put("total", "35.548");
            hm.put("id_liquidacion", 2 );
            
            //Connect with local datasource
            /*Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/Dam");
            conexion = null;
            conexion = ds.;*/
    //conexion.setAutoCommit(true);
    /*   ConexionBaseDatos conn = new ConexionBaseDatos();
            Connection conexion = conn.getConexion();
            
            JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\recibo.jasper", hm, conexion);
            
            byte[] bytes;
            
                bytes = JasperExportManager.exportReportToPdf(jasperPrint);
            
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context
                    .getExternalContext().getResponse();
            /**
             * *********************************************************************
             * Pour afficher une bo�te de dialogue pour enregistrer le fichier sous
             * le nom rapport.pdf
             * ********************************************************************
     */
 /* response.addHeader("Content-disposition",
                    "attachment;filename=reporte.pdf");
            response.setContentLength(bytes.length);
            response.getOutputStream().write(bytes);
            response.setContentType("application/pdf");
            context.responseComplete();
            
        } catch (IOException | JRException ex) {
           System.out.println("mensajewe "+ex.getMessage());
        }
    }*/
    public void printPDF() throws JRException, IOException {
        List<ReciboReporte> dataSource = new ArrayList<>();
        dataSource.add(new ReciboReporte("SueldoBase", 1.0, 1, 35.000));
        dataSource.add(new ReciboReporte("Presentismo", 0.05, 1, 1.750));

        String filename = "pdfNombre.pdf";
        String jasperPath = "/resources/report/recibo.jasper";

        Map<String, Object> hm = new HashMap<>();
        hm.put("nombreApellido", "federico");
        //valor= String.valueOf(liquidacionSeleccionadaPDF.getIdEmpleado().getLegajo());
        hm.put("legajo", "925");
        //valor = getNombreMes(liquidacionSeleccionadaPDF.getMes());
        hm.put("mes", "OCTUBRE");
        //valor= sdf.format(liquidacionSeleccionadaPDF.getIdEgreso().getFecha());
        hm.put("fechaPago", "20/11/2012");
        //valor=String.valueOf(liquidacionSeleccionadaPDF.getIdEmpleado().getDni());
        hm.put("dni", "31376213");
        //valor=sdf.format(liquidacionSeleccionadaPDF.getIdEmpleado().getFechaAlta());
        hm.put("fechaIngreso", "22/1/2015");
        // hm.put("tipoEmpleado", liquidacionSeleccionadaPDF.getIdEmpleado().getIdTipoEmpleado().getRol());
        hm.put("tipoEmpleado", "gerente");
        //valor=String.format ("%.2f",liquidacionSeleccionadaPDF.getIdEgreso().getMonto());
        hm.put("total", "35.548");
        hm.put("id_liquidacion", 2);
        this.PDF(hm, jasperPath, dataSource, filename);

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

    public void generateReport() {
        System.out.println("hola hola");

        try {
            //List<country> countries = getListCountriesDummy();

            Map<String, Object> hm = new HashMap<>();
            hm.put("nombreApellido", "federico");
            //valor= String.valueOf(liquidacionSeleccionadaPDF.getIdEmpleado().getLegajo());
            hm.put("legajo", "925");
            //valor = getNombreMes(liquidacionSeleccionadaPDF.getMes());
            hm.put("mes", "OCTUBRE");
            //valor= sdf.format(liquidacionSeleccionadaPDF.getIdEgreso().getFecha());
            hm.put("fechaPago", "20/11/2012");
            //valor=String.valueOf(liquidacionSeleccionadaPDF.getIdEmpleado().getDni());
            hm.put("dni", "31376213");
            //valor=sdf.format(liquidacionSeleccionadaPDF.getIdEmpleado().getFechaAlta());
            hm.put("fechaIngreso", "22/1/2015");
            // hm.put("tipoEmpleado", liquidacionSeleccionadaPDF.getIdEmpleado().getIdTipoEmpleado().getRol());
            hm.put("tipoEmpleado", "gerente");
            //valor=String.format ("%.2f",liquidacionSeleccionadaPDF.getIdEgreso().getMonto());
            hm.put("total", "35.548");
            hm.put("id_liquidacion", 2);

            outputStream = JasperReportUtil.getOutputStreamFromReport(hm, "c:\\recibo.jasper");
            media = JasperReportUtil.getStreamContentFromOutputStream(outputStream, "application/pdf", "recibo.pdf");
        } catch (Exception e) {
            System.out.println("mensajewe " + e.getMessage());
            //log.e error(e.getMessage(), e);
        }
    }

    public void downloadFile() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();

            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.reset();
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "attachment; filename=" + "recibo.pdf");

            OutputStream output = response.getOutputStream();
            output.write(outputStream.toByteArray());
            output.close();

            facesContext.responseComplete();
        } catch (Exception e) {
            //log.error(e.getMessage(), e);
        }
    }

    public StreamedContent getMedia() {
        return media;
    }

    public void setMedia(StreamedContent media) {
        this.media = media;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
