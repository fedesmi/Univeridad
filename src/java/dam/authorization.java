package dam;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author FMichel
 */
@ManagedBean
@SessionScoped
public class authorization {
    private Usuario user;
    

    /** Creates a new instance of auth */
    public authorization() {
        
        user = getLoggedUserData(getLoggedUser());
    }

    
    public void logout() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath() + "/faces/users/userindex.xhtml");
        user = new Usuario();
    }

    
    public String getLoggedUser() {
        return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        
    }
    
    
    public String getNombreyApellido(String userVar){
        Usuario uv = getLoggedUserData(userVar);
        
        return uv.getNombreYApellido();
    }

    
    public Usuario getLoggedUserData(String userVar) {
        
        Usuario usr = new Usuario();
        
        String sql;
         try {
            ConexionBaseDatos connMysql = new ConexionBaseDatos();
             sql = "SELECT nombre, apellido FROM users u where username='"+userVar+"' ";
            
            java.sql.ResultSet resultado = connMysql.ejecutarConsulta(sql);
            
            try {
                while (resultado.next()) {
                    usr.setNombre(resultado.getString(1));
                    usr.setApellido(resultado.getString(2));
                }
            } catch (java.sql.SQLException e) {
                System.err.print(e);
            }
            
          connMysql.cerrarConexion();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return usr;
         
    }
    
    
    
    

    /**
     * @return the user
     */
    public Usuario getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Usuario user) {
        this.user = user;
    }
    
    
}
