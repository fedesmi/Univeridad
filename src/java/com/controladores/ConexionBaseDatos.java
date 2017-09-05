

package com.controladores;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class ConexionBaseDatos {
    
    //----------- Atributos ---------------
    
   // private FileConfiguration fc;
    private String url="";//camino + nombre de la base de datos
    private String driver="";  // nombre del driver
    private String nombreBD="dam"; // nombre fisico de la BD
    private Connection conexion;   // Objeto de conexion a la base de datos
    private Statement sentencia;   // Objeto que permite ejecutar comando SQL
    private ResultSet resultado;   // (tabla)Objeto con el resultado de la consulta SQL
    private boolean conectado;
    
    public String ipHostServidor="localhost"; // almacena la direccion (ip) de la maquina en donde se ejecuta el Servidor BD
   
    
  
       
    // ---------- cosntructores --------------
    public ConexionBaseDatos() throws FileNotFoundException, IOException {


      
        //url="jdbc:mysql://"+ipHostServidor+":3306/"+nombreBD; //cambiar por servidor1 para q vuelva andar para el logueo
        url="jdbc:mariadb://"+ipHostServidor+":3306/"+nombreBD; //cambiar por servidor1 para q vuelva andar para el logueo
        //driver="com.mysql.jdbc.Driver"; //driver(programa)q se encarga de interactuar con el driver del motor de la bd
        driver="org.mariadb.jdbc.Driver"; //driver(programa)q se encarga de interactuar con el driver del motor de la bd
        
	    conectado=false;
        conectarBD("root","untdf");
        
    }
 public ConexionBaseDatos(String bdName) throws FileNotFoundException, IOException {


      
        //url="jdbc:mysql://"+ipHostServidor+":3306/"+nombreBD; //cambiar por servidor1 para q vuelva andar para el logueo
        url="jdbc:mariadb://"+ipHostServidor+":3306/"+bdName; //cambiar por servidor1 para q vuelva andar para el logueo
        //driver="com.mysql.jdbc.Driver"; //driver(programa)q se encarga de interactuar con el driver del motor de la bd
        driver="org.mariadb.jdbc.Driver"; //driver(programa)q se encarga de interactuar con el driver del motor de la bd
        
	    conectado=false;
        conectarBD("webauth" ,"corp" );
        
    }
    
    
    public void conectarBD( String userp,String pwp ){ 
        try {
            Class.forName(driver);//CARGA EL DRIVER SUN.JDBC.... PERMITE LA COMUNICACION ENTRE EL PROG JAVA Y EL DRIVER DEL MOTOR
            conexion = DriverManager.getConnection(url,userp, pwp);//se crea una conexion con bd especificada
            sentencia = conexion.createStatement(); //se crea una sentencia p ejecutar comandos sql y se asocia con conexion
           
            conectado=true;   
            //System.out.println("conecto con la BD");
        }catch( Exception e ) {            
             javax.swing.JOptionPane.showMessageDialog(null,"Error al conectar con la Base de Datos !\n"+e.getMessage(),"Mensaje !!!",javax.swing.JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    public boolean estaConectado(){
        return conectado;
    }
    
    public ResultSet ejecutarConsulta(String con){
       //System.out.println(con);
       ResultSet resultado2=null;
       
       try{
           sentencia = conexion.createStatement();
           
           resultado2 = sentencia.executeQuery(con);   
       }catch(SQLException e){
           javax.swing.JOptionPane.showMessageDialog(null,"Error al ejecutar la siguiente consulta: \n"+con+"\n"+e.getMessage(),"Mensaje !!!",javax.swing.JOptionPane.ERROR_MESSAGE);
       }
       return resultado2; 
    }
    
     public boolean ejecutarUpDate(String con){
         
        // System.out.println(con);
         boolean operacionOK=false;
       
       try{
            sentencia.executeUpdate(con);
            operacionOK=true;
                  
       }catch(SQLException e){
            System.out.println(e.getMessage());
       }
       
       
       
       return operacionOK;
    }
     
     public int ejecutarUpDateGK(String con){
         
          System.out.println(con);
       int generatedKey=0;
       
       try{
           
            sentencia.executeUpdate(con);
            ResultSet rs = sentencia.executeQuery("select last_insert_id() ");
            if(rs.next())
            generatedKey = rs.getInt(1);
                  
       }catch(SQLException e){
            System.out.println(e.getMessage());
       }
       return generatedKey;
    }
     

     
     
    
    public void cerrarConexion(){
        try{
            sentencia.close();
            conexion.close();
            if(resultado!=null)
                resultado.close();
              
	  }catch( Exception e ) {
            System.out.println( e.toString() );
          }
    }

}
