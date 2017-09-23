package com.controladores;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.entidades.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import com.repositorios.*;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author FMichel
 */
@SessionScoped
@Named(value = "authorization")
public class Authorization implements Serializable {

    private Usuario user;
    private String oldpass;
    private String newpass;
    private String newpass2;
    private int legajo;
    private String userName;
    private String userPass;
    
    @Inject
    private UsuarioFacade usuarioFacade;

    /**
     * Creates a new instance of auth
     */
    public Authorization() {

        //user = getLoggedUserData(getLoggedUser());
    }

    public void logout() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath() + "/faces/users/usuarioIndex.xhtml");
        user = new Usuario();
    }

    public String getLoggedUser() {
        return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

    }

    public String getNombreyApellido(String userVar) {
        return user.getIdEmpleado().getNombre()+" "+user.getIdEmpleado().getNombre();
    }

 
    public void cambiarClave() {
        if (newpass.equals(newpass2)) {
            try {
                String sql;
                boolean passOk = false;
                ConexionBaseDatos connMysql = new ConexionBaseDatos();
                sql = "SELECT count(*) FROM usuario WHERE usuario.usuario='" + user.getUsuario()+ "' AND usuario.clave=sha2('" + oldpass + "',256) ";
                java.sql.ResultSet resultado = connMysql.ejecutarConsulta(sql);
                try {
                    while (resultado.next()) {
                        passOk = resultado.getInt(1) > 0;
                    }
                } catch (java.sql.SQLException e) {
                    System.err.print(e);
                }
                if (passOk) {
                    sql = "UPDATE usuario SET clave=sha2('" + newpass + "',256) WHERE usuario.usuario='" + user.getUsuario()+ "' ";
                    
                    connMysql.ejecutarUpDate(sql);
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Su clave fue actualizada con éxito"));
                    
                } else {
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!!", "La clave ingresada es incorrecta"));
                }
                connMysql.cerrarConexion();
            } catch (IOException ex) {
                Logger.getLogger(Authorization.class.getName()).log(Level.SEVERE, null,ex);
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!!", "Las claves no coinciden"));
        }

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

    /**
     * @return the oldpass
     */
    public String getOldpass() {
        return oldpass;
    }

    /**
     * @param oldpass the oldpass to set
     */
    public void setOldpass(String oldpass) {
        this.oldpass = oldpass;
    }

    /**
     * @return the newpass
     */
    public String getNewpass() {
        return newpass;
    }

    /**
     * @param newpass the newpass to set
     */
    public void setNewpass(String newpass) {
        this.newpass = newpass;
    }

    /**
     * @return the newpass2
     */
    public String getNewpass2() {
        return newpass2;
    }

    /**
     * @param newpass2 the newpass2 to set
     */
    public void setNewpass2(String newpass2) {
        this.newpass2 = newpass2;
    }

    public void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            request.login(userName, userPass);
            user = new Usuario();
            user = this.usuarioFacade.getUsuario(getLoggedUser());
            String redireccion=externalContext.getRequestContextPath() + "/faces/";
            if (user.getIdEmpleado().getIdTipoEmpleado().getRol().equals("gerente")) {
                redireccion+="gerencia/gerenteIndex.xhtml";
            } else if (user.getIdEmpleado().getIdTipoEmpleado().getRol().equals("administrativo")) {
                redireccion+="admin/administradorIndex.xhtml";
            } else if (user.getIdEmpleado().getIdTipoEmpleado().getRol().equals("instructor")) {
                redireccion+="instructor/instructorIndex.xhtml";
            }

            externalContext.redirect(redireccion);
        } catch (ServletException ex) {
            Logger.getLogger(Authorization.class.getName()).log(Level.SEVERE, null, ex);
            externalContext.redirect(externalContext.getRequestContextPath()+"/faces/loginError.xhtml");
        }
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the userPass
     */
    public String getUserPass() {
        return userPass;
    }

    /**
     * @param userPass the userPass to set
     */
    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    /**
     * @return the legajo
     */
    public int getLegajo() {
        return legajo;
    }

    /**
     * @param legajo the legajo to set
     */
    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    /**
     * @return the usuarioFacade
     */
    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    /**
     * @param usuarioFacade the usuarioFacade to set
     */
    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }
}
