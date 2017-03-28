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
    private String oldpass;
    private String newpass;
    private String newpass2;

    /**
     * Creates a new instance of auth
     */
    public authorization() {

        user = getLoggedUserData(getLoggedUser());
    }

    public void logout() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getRequestContextPath() + "/faces/users/userIndex.xhtml");
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
            String pass = "";
            try {
                ConexionBaseDatos connMysql = new ConexionBaseDatos();

                sql = "SELECT clave FROM usuario WHERE usuario.usuario='" + user + "' ";

                java.sql.ResultSet resultado = connMysql.ejecutarConsulta(sql);

                try {
                    while (resultado.next()) {
                        pass = resultado.getString(1);
                    }
                } catch (java.sql.SQLException e) {
                    System.err.print(e);
                }
                if (oldpass.equals(pass)) {
                    sql = "UPDATE usuario SET clave=sha2('" + newpass + "',256) WHERE usuario.usuario='" + user + "' ";

                    connMysql.ejecutarUpDate(sql);

                } else {
                    //cartelito
                }
                connMysql.cerrarConexion();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(authorization.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            //cartelito
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

}
