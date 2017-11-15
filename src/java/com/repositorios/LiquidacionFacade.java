/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repositorios;

import com.controladores.ConexionBaseDatos;
import com.entidades.Liquidacion;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fmichel
 */
@Stateless
public class LiquidacionFacade extends AbstractFacade<Liquidacion> {

    @PersistenceContext(unitName = "DAMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LiquidacionFacade() {
        super(Liquidacion.class);
    }

  public boolean isLiquidacion(int mes, int year) {
        
        int cantidad = 0;
        try {

            String consulta = "SELECT COUNT(*) FROM liquidacion as l WHERE l.mes = "+ mes +"  "
                    + "AND  l.year = "+year+" ; ";

            
            ConexionBaseDatos connMysql = new ConexionBaseDatos();
            java.sql.ResultSet resultado = connMysql.ejecutarConsulta(consulta);

            try {
                while (resultado.next()) {
                   cantidad = resultado.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LiquidacionFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
            connMysql.cerrarConexion();
        } catch (IOException ex) {
            Logger.getLogger(LiquidacionFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(cantidad>0) {
            return true;
        }
        else {
            return false;
        }
    }
    

}
