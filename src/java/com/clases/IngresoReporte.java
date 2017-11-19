/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clases;

/**
 *
 * @author Usuario
 */
public class IngresoReporte {
    private String fecha;
    private String formaPago;
    private String cuotas;
    private String monto;
    private String clases;
    private String alquileres;

    public IngresoReporte(String fecha, String formaPago, String cuotas, String monto, String clases, String alquileres){
        this.fecha=fecha;
        this.formaPago=formaPago;
        this.cuotas=cuotas;
        this.monto=monto;
        this.clases=clases;
        this.alquileres=alquileres;
    
    }
    
    
    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the formaPago
     */
    public String getFormaPago() {
        return formaPago;
    }

    /**
     * @param formaPago the formaPago to set
     */
    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }



    /**
     * @return the monto
     */
    public String getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(String monto) {
        this.monto = monto;
    }

    /**
     * @return the clases
     */
    public String getClases() {
        return clases;
    }

    /**
     * @param clases the clases to set
     */
    public void setClases(String clases) {
        this.clases = clases;
    }

    /**
     * @return the cuotas
     */
    public String getCuotas() {
        return cuotas;
    }

    /**
     * @param cuotas the cuotas to set
     */
    public void setCuotas(String cuotas) {
        this.cuotas = cuotas;
    }

    /**
     * @return the alquileres
     */
    public String getAlquileres() {
        return alquileres;
    }

    /**
     * @param alquileres the alquileres to set
     */
    public void setAlquileres(String alquileres) {
        this.alquileres = alquileres;
    }

     
    
}
