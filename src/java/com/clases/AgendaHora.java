/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clases;

/**
 *
 * @author fmichel
 */
public class AgendaHora {
    
    private int idHorario;
    private String horario;
    private boolean disponible;
    

 
    
   
    public AgendaHora(int idHorarioPar, String horarioPar, boolean disponiblePar){
        this.idHorario=idHorarioPar;
        this.horario=horarioPar;
        this.disponible=disponiblePar;
    }
    
    
      public AgendaHora(){
       
    }
    
    
    /**
     * @return the idHorario
     */
    public int getIdHorario() {
        return idHorario;
    }

    /**
     * @param idHorario the idHorario to set
     */
    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    

    /**
     * @return the horario
     */
    public String getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(String horario) {
        this.horario = horario;
    }

    /**
     * @return the disponible
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * @param disponible the disponible to set
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    
    
}
