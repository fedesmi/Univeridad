package dam;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author FMichel
 */
@ManagedBean
@SessionScoped
public class authorization {

    private Usuario user;
    private String oldpass;
    private String newpass;
    private String newpass2;

    private String userName;
    private String userPass;

    /**
     * Creates a new instance of auth
     */
    public authorization() {

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
        Usuario uv = getLoggedUserData(userVar);

        return uv.getNombreYApellido();
    }

    public Usuario getLoggedUserData(String userVar) {

        Usuario usr = new Usuario();

        String sql;
        try {
            ConexionBaseDatos connMysql = new ConexionBaseDatos();
            sql = "SELECT e.nombre, e.apellido FROM empleado e, usuario u where usuario='" + userVar + "' and u.legajo=e.legajo";

            java.sql.ResultSet resultado = connMysql.ejecutarConsulta(sql);

            try {
                while (resultado.next()) {
                    usr.setNombre(resultado.getString(1));
                    usr.setApellido(resultado.getString(2));
                    usr.setUserName(userVar);
                }
            } catch (java.sql.SQLException e) {
                System.err.print(e);
            }

            sql = "SELECT rol FROM v_usuario_rol where usuario='" + userVar + "' ";
            resultado = connMysql.ejecutarConsulta(sql);
            try {
                while (resultado.next()) {
                    usr.setRol(resultado.getString(1));
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

    public void cambiarClave() {
        if (newpass.equals(newpass2)) {
            String sql;
            boolean passOk = false;
            try {
                ConexionBaseDatos connMysql = new ConexionBaseDatos();

                sql = "SELECT count(*) FROM usuario WHERE usuario.usuario='" + user.getUserName() + "' AND usuario.clave=sha2('" + oldpass + "',256) ";

                java.sql.ResultSet resultado = connMysql.ejecutarConsulta(sql);

                try {
                    while (resultado.next()) {
                        passOk = resultado.getInt(1) > 0;
                    }
                } catch (java.sql.SQLException e) {
                    System.err.print(e);
                }
                if (passOk) {
                    sql = "UPDATE usuario SET clave=sha2('" + newpass + "',256) WHERE usuario.usuario='" + user.getUserName() + "' ";

                    connMysql.ejecutarUpDate(sql);
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Su clave fue actualizada con éxito"));

                } else {
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!!", "La clave ingresada es incorrecta"));
                }
                connMysql.cerrarConexion();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
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
            user = getLoggedUserData(getLoggedUser());
            String redireccion=externalContext.getRequestContextPath() + "/faces/";
            if (user.getRol().equals("gerente")) {
                redireccion+="gerencia/gerenteIndex.xhtml";
            } else if (user.getRol().equals("administrativo")) {
                redireccion+="admin/administradorIndex.xhtml";
            } else if (user.getRol().equals("instructor")) {
                redireccion+="instructor/instructorIndex.xhtml";
            }

            externalContext.redirect(redireccion);
        } catch (ServletException ex) {
            Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
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
}
