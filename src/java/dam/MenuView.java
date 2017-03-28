/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam;
 
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
 
 
@ManagedBean
public class MenuView {
     
    private String console;
   
   
     
    @PostConstruct
    public void init() {
      
    }
 
    public String getConsole() {
        return console;
    }
 
    public void setConsole(String console) {
        this.console = console;
    }
 
   
}