/*
 * crear_archivos.java
 *
 * Created on 14 de febrero de 2005, 05:06 PM
 */

package pkfactura;
import java.io.*;
import java.sql.*;
import java.util.*;


/**
 *
 * @author SRodriguez
 */
public class crear_archivos extends books  {
         private String archivotxt="archivoingreso.txt";
         private String servidor="pluton";
         private String carpetaSambaRaiz="dinamicas_excel";
         private String sub_carpeta="empresas";


//linux         
//--------------------------------------------------------         
         private String carpeta_raiz="usr/local/simon";
//--------------------------------------------------------         
//windows         
        // private String carpeta_raiz="c:\\probandosaymon";
//--------------------------------------------------------         
         private String carpetaLinux="/" + carpeta_raiz +"/" + sub_carpeta + "/";
         private String carpetaWindows="\\" + carpeta_raiz +"\\" + sub_carpeta + "\\";
         private String carpetaUrlWindows= "\\" + "\\" + servidor + "/" + carpeta_raiz + "/" + sub_carpeta + "/";
         private String carpetaUrlSamba="\\" + "\\" + servidor + "\\" + carpetaSambaRaiz + "\\" + sub_carpeta + "\\";
      
//windows
//--------------------------------------------------------         
        // private String carpeta=carpetaWindows; 
//--------------------------------------------------------
//linux
         private String carpeta=carpetaLinux;
//--------------------------------------------------------         

         
//si es windows 
        // private String  carpetaUrl=carpetaUrlWindows;
//--------------------------------------------------------         
//si es linux
            private String  carpetaUrl=carpetaUrlSamba;    
//--------------------------------------------------------         
         
    //creamos la carpeta en caso que no exista     
    public crear_archivos(){
           CrearDirectorio d = new CrearDirectorio(carpeta);
    }
    /** Creates a new instance of crear_archivos */
    public void crea_archivo(String[] pkeys)throws Exception {
        books b = new books();  
        FileWriter salida=null;
       try  {
             b.connect();
             salida=new FileWriter(carpeta + archivotxt);
             ResultSet rs=null;
             String sql="";
             String str="";
             String fecha1;
             String fecha2;
             String fecha3;
             String fecha_af1;
             String fecha_af2;                     
             String fecha_af3;        
             String cedula="";
             Statement stmt;
             String reg_empresa;
             String division;
         //    salida.write("antes de entrar"); 
          for(int i=0; i<pkeys.length;i++){
           //      salida.write("entro"); 
               sql= "select arch_ingresos_id,nacionalidad,cedula,nombre,fecha_nacimiento,sexo,fecha_afiliacion,rif_empresa,codigo_division from ";
               sql = sql + " TBL_ARCHI_NGRESOS where arch_ingresos_id=" + pkeys[i] ;  
               /// String sql="select * from book;";
              stmt= b.conn.createStatement();
                rs=stmt.executeQuery(sql);
                if (rs.next()){
                    cedula="";
                    str="";
                    reg_empresa="";
                    division="";
                    fecha1=rs.getString("fecha_nacimiento").substring(0, 2);
                    fecha2= rs.getString("fecha_nacimiento").substring(3, 5);
                    fecha3=  rs.getString("fecha_nacimiento").substring(6,10);
                    fecha_af1=rs.getString("fecha_afiliacion").substring(0, 2);
                    fecha_af2= rs.getString("fecha_afiliacion").substring(3, 5);                     
                    fecha_af3=  rs.getString("fecha_afiliacion").substring(6, 10);                     
                    int j=rs.getString("cedula").length();
                    cedula=rs.getString("cedula").trim();
                        while (j<8){
                              cedula="0" + cedula;
                              j++;
                        }
                    
                    j=rs.getString("rif_empresa").length();
                    reg_empresa=rs.getString("rif_empresa").trim();
                    while (j<12){
                              reg_empresa +=" ";
                              j++;
                        }
                    
                    
                    j=rs.getString("codigo_division").length();
                    division=rs.getString("codigo_division").trim();
                    while (j<2){
                              division +=" ";
                              j++;
                        }
                    
                    
                    
                    str=rs.getString("nacionalidad") + cedula + getNombre(rs.getString("nombre")) + fecha1+fecha2 +fecha3 + rs.getString("sexo") + fecha_af1 + fecha_af2 + fecha_af3 + reg_empresa + division + "\r\n";      
                    salida.write(str);
               } //end if
                rs.close();
             } //end for  
            } //end try
           catch(Exception e){
                error="Ocurrio un error" + e;
                throw new Exception(error);
           }
          finally{
                 try{
                    b.disconnect();
                    salida.close();
                 }
                  catch(IOException ex){
                  }

          }
    } 
     public String getCarpetaUrlArchivoIngresoFactura()throws Exception{
                 
             try{
                   return this.carpetaUrl  + archivotxt;
             }
            catch(Exception e){
                 error ="Error en getArchivoExcel:" + e;
                 throw new Exception(error);
            } 
        }
    private String getNombre(String nombre)throws Exception{
          try{
                 char[] st=nombre.toCharArray();
                 String nom="";
                 int j=0;
                 boolean sw=true;
                 for (int i=0;i<st.length;i++){
                      /* if ( (st[i]==',') || (st[i]==' ') ){
                            continue;
                       }    */ 
                          nom +=st[i];
                 }
                 j=nom.length();
                 while (j<60){
                              nom +=" ";
                              j++;
                        }
                 
                return nom; 
          }
          catch(Exception e){
              throw new Exception(e); 
          }
          
    }
}
