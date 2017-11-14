/*
 * books.java
 *
 * Created on 11 de diciembre de 2004, 05:30 PM
 */

package pkfactura;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.text.SimpleDateFormat;


/**
 *
 * @author Administrador
 */
public class books  {
String error;
public Connection conn;
private String Fecha;
private String condicionFecha;
private String FiltroInstitucion;
private String FiltroStatus;
private String sql;
private String servidorbd="PLUTON";
private String instanciabd="ORCL9I";
private String useroracle="DINAMICAS";
private String pwdoracle="DINAMICAS";

 

    /** Creates a new instance of books */
    public books() {
    }
    
     public void setCondicionFecha(String condicion)throws Exception{
          try{
                this.condicionFecha=condicion;
          }catch(Exception e){
            error="setCondicionFecha Hubo una excepcion:" + e;
            throw new Exception(error);
          }      
    }
          
     public String getCondicionFecha()throws Exception{
         try{
           return this.condicionFecha;
         }
         catch(Exception e){
              error="getCondicionFecha Hubo una excepcion:" + e;
              throw new Exception(error);
         }
    }      
     
    public void setFecha(String fecha)throws Exception{
          try{
                this.Fecha=fecha;
          }catch(Exception e){
              error="setFecha Hubo una excepcion:" + e;
              throw new Exception(error);          }      
    }
    
    
    public String getFecha(){
           return this.Fecha;
    }
    public void setFiltroInstituto(String filtroinstituto){
                this.FiltroInstitucion=filtroinstituto;
    }
    
    public String getFiltroInstituto(){
                return this.FiltroInstitucion;
    }
    public void setFiltroStatus(String status_id){
           this.FiltroStatus=status_id;
    }
    public String getFiltroStatus(){
            return this.FiltroStatus;
    }
    public void connect () throws ClassNotFoundException, SQLException,Exception{
           try {
              //   Class.forName("org.gjt.mm.mysql.Driver").newInstance();
              //   conn = DriverManager.getConnection("jdbc:mysql://localhost/prueba","root","root"); 
               Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
//               conn = DriverManager.getConnection("jdbc:oracle:thin:@PLUTON:1521:ORCL9I","DINAMICAS","DINAMICAS"); 
               conn = DriverManager.getConnection("jdbc:oracle:thin:@" + servidorbd + ":1521:" + instanciabd  , useroracle , pwdoracle); 

            
           }
           catch(ClassNotFoundException e){
                error="No se puede locxalizar el controlador:" + e;
                throw new ClassNotFoundException(error);
           }
           catch(SQLException e){
                error="Imposible establecer la conexion on BD:" + e;
                throw new SQLException(error);
           }
           catch(Exception e){
               error="Hubo un error inseperado:" + e;
               throw new Exception(error);
           
           }
    }
    
    public void disconnect () throws SQLException{
            try{
                if (conn  != null){
                    conn.close();
                }
            }
            catch(SQLException e){
              error = "Impòsible cerrar la conexion:" + e;
              throw new SQLException(error);
            }
    }
    public ResultSet viewbooks()throws SQLException, Exception {
           ResultSet rs=null;
           books d = new books();
           String sql1=GenerarSQL();
           try{
                
                //String sql="SELECT CONCEPTO FROM FACTURAS_EMITIDAS";
                
               /// String sql="select * from book;";
                Statement stmt= conn.createStatement();
                rs=stmt.executeQuery(sql1);
           }
           catch(SQLException e){
                error="class book viewbooks No se puede ejecutar la consulta:" + sql1 + " " + e;
           }
           catch(Exception e){
                error="class book viewbooks Hubo un error:" + sql1 + " " + e;
                throw new Exception(error);
           }
          return rs;
    }
    
    
     public ResultSet viewscualquiera(String sql1)throws SQLException, Exception {
           ResultSet rs=null;
           String sq=sql1;
           //books d = new books();
           try{
                
                //String sql="SELECT CONCEPTO FROM FACTURAS_EMITIDAS";
                
               /// String sql="select * from book;";
                Statement stmt= conn.createStatement();
                rs=stmt.executeQuery(sql1);
           }
           catch(SQLException e){
                error="class book viewscualquiera No se puede ejecutar la consulta:" + sq + " " + e;
           }
           catch(Exception e){
                error="class book viewscualquiera Hubo un error:" + sq + " " + e;
                throw new Exception(error);
           }
          return rs;
    }
     
     
     public ResultSet viewInstitucion()throws SQLException, Exception {
           ResultSet rs=null;
           String sql1="SELECT INSTITUCION_ID,NOMBRE FROM INSTITUCION ORDER BY NOMBRE";

           //books d = new books();
           try{
                
               /// String sql="select * from book;";
                Statement stmt= conn.createStatement();
                rs=stmt.executeQuery(sql1);
           }
           catch(SQLException e){
                error="class book viewInstitucion No se puede ejecutar la consulta:" + sql1 + " " + e;
           }
           catch(Exception e){
                error="class book viewInstitucion Hubo un error:" + sql1 + " " + e;
                throw new Exception(error);
           }
          return rs;
    }
    
    public ResultSet viewfacturasmodificar(String sql1)throws SQLException, Exception {
            String sq=sql1;
            ResultSet rs=null;
            books d = new books();
           try{
                Statement stmt= conn.createStatement();
                rs=stmt.executeQuery(sql1);
           }
           catch(SQLException e){
                error="class book viewfacturasmodificar No se puede ejecutar la consulta:" + sq + " " + e;
           }
           catch(Exception e){
                error="class book viewfacturasmodificar Hubo un error:" + sq + " " + e;
                throw new Exception(error);
           }
          return rs;   
    }
    
    public void cargarsql(String sql1)throws Exception{
           String sq=sql1;
           try{
               this.sql=sql1;
           }
           catch(Exception e){
                 error="class book cargarsql Cargar string sql error:" + sq + " " + e;
                 throw new Exception(error);
           }
    
    }
    
    public ResultSet cargar_combo()throws Exception{
           ResultSet rs=null;
           try{
               Statement stmt= conn.createStatement();
               rs=stmt.executeQuery(sql);
           }
           catch(Exception e){
                error="class book cargar_combo Cargar combo error:" + sql + " " + e;
                throw new Exception(error);
           }
           return rs;        
    }

  


        
     //,String fecha,float dolares,String status,String codigocontable,String fechapago,String anio_canc )
    //public void addbook(int institucion_id, int tipocambio_id, String contrato,String concepto,Date nro,Date fecha,float dolares,String status,String codigocontable,Date fechapago,String anio_canc )throws SQLException,Exception{
    public void addbook(int institucion_id, int tipocambio_id, String contrato,String concepto,String nro,String fecha,double dolares,String status,String codigocontable,String fechapago,String anio_canc)throws SQLException,Exception{
            if (conn != null ){
               try{
                  PreparedStatement updatebook;
                  updatebook=conn.prepareStatement("insert into FACTURAS_EMITIDAS (INSTITUCION_ID,TIPOCAMBIO_ID,Contrato, Concepto,Nro,FECHA_FACTURA,Dolares,Status_id,CODIGOCONTABLE,FECHA_PAGO,ANIO_CANC) values(?,?,?,?,?,?,?,?,?,?,?)");
                  updatebook.setInt(1,(int)institucion_id);
                  updatebook.setInt(2,(int)tipocambio_id);
                  updatebook.setString(3,contrato);
                  updatebook.setString(4,concepto);
                  updatebook.setString(5,nro);
                  updatebook.setString(6,fecha);
                  updatebook.setDouble(7,dolares);
                  updatebook.setString(8,status);
                  updatebook.setString(9,codigocontable);
                  updatebook.setString(10,fechapago);
                  updatebook.setString(11,anio_canc);
                  updatebook.execute();
                  
                  
               }catch(SQLException e){
                   error="class book addbook Hubo un error SQL:" + e;
                   throw new SQLException(error);
               }
           }else{
                   error="class book addbook se perdio lña conexion a la bd:";
                   throw new Exception(error);
           }
               
    }

    
      //public void updatefactura(int institucion_id,int tipocambio_id,String contrato,String concepto,String nro,String fecha_factura,String dolares,String status_id,String codigocontable,String fecha_pago,String anio_canc,int facturasemi_id)throws SQLException,Exception{
    public void updatee(String sql)throws SQLException,Exception{
      if (conn != null ){
               try{
                   
                 PreparedStatement prepStmt = conn.prepareStatement(sql);
                 int rowCount = prepStmt.executeUpdate();
                 prepStmt.close();
            } catch(SQLException e){
                   error="class book updatee Hubo un error SQL:" + e;
                   throw new SQLException(error);
               }
           }else{
                   error="class book updatee se perdio lña conexion a la bd:" ;
                   throw new Exception(error);
           }
               
    }
    
    
    //public void updatefactura(int institucion_id,int tipocambio_id,String contrato,String concepto,String nro,String fecha_factura,String dolares,String status_id,String codigocontable,String fecha_pago,String anio_canc,int facturasemi_id)throws SQLException,Exception{
    public void updatefactura(int institucion_id,int tipocambio_id,String concepto,double dolares,String codigocontable,String anio_canc ,int status_id,String fecha_pago,String fecha_factura,int facturasemi_id)throws SQLException,Exception{
      if (conn != null ){
               try{
                     String fecha_factura1 = fecha_factura;
                  SimpleDateFormat formatter= new SimpleDateFormat ("dd/mm/yyyy");
                   Date fecha_facturas = formatter.parse(fecha_factura1);
                   
                   
                 PreparedStatement prepStmt = conn.prepareStatement("update facturas_emitidas set institucion_id=?,tipocambio_id=?,concepto=?,dolares=?,codigocontable=?,anio_canc=?,status_id=?,fecha_pago=?,fecha_factura=? where facturasemi_id=?");
                 prepStmt.setInt(1,(int)institucion_id);
                 prepStmt.setInt(2,(int)tipocambio_id);
                 prepStmt.setString(3, concepto);
                 //float dolaresfloat= Float.parseFloat(dolares);
                 double dolaresfloat=dolares;
                 //prepStmt.setFloat (4,dolaresfloat);
                 prepStmt.setDouble(4,dolaresfloat);
                 prepStmt.setString(5,codigocontable);                 
                      
                prepStmt.setString(6,anio_canc);                 
                prepStmt.setInt(7,(int)status_id);
                 prepStmt.setString(8,fecha_pago);   
                prepStmt.setString(9,fecha_factura);   
                prepStmt.setInt(10, facturasemi_id);                                                                                         
                 int rowCount = prepStmt.executeUpdate();
                 prepStmt.close();
            } catch(SQLException e){
                   error="class book updatefactura Hubo un error SQL:" + e;
                   throw new SQLException(error);
               }
           }else{
                   error="class book updatefactura se perdio lña conexion a la bd:";
                   throw new Exception(error);
           }
               
    }
   
    public void removee(String sql,String[] pkeys)throws SQLException, Exception{
                   if (conn != null ){
                       try{
                           PreparedStatement ps= conn.prepareStatement(sql);
                           for(int i=0; i<pkeys.length;i++){
                                ps.setInt(1, Integer.parseInt(pkeys[i]));
                                ps.execute();
                           }
                           ps.close();
                       }catch(SQLException e){
                           error="class book removee Hubo un sql exceptiln:" + e;
                           throw new SQLException(error);
                       }catch(Exception e){
                              error="class book removee se produjo un error:" + e;
                              throw new Exception(error);
                        }
                   }else{
                        error="class book removee se ha perdido la conexion con la bd:";
                        throw new Exception(error);
                   }
    
    }
    
    public void removebook (String[] pkeys)throws SQLException, Exception{
                   if (conn != null ){
                       try{
                           PreparedStatement ps= conn.prepareStatement("delete  from facturas_emitidas where facturasemi_id=?");
                           for(int i=0; i<pkeys.length;i++){
                                ps.setInt(1, Integer.parseInt(pkeys[i]));
                                ps.execute();
                           }
                           ps.close();
                       }catch(SQLException e){
                           error="class book removebook Hubo un sql exceptiln:" + e;
                           throw new SQLException(error);
                       }catch(Exception e){
                              error="class book removebook se produjo un error :" + e;
                              throw new Exception(error);
                        }
                   }else{
                        error="class book removebook se ha perdido la conexion con la bd.";
                        throw new Exception(error);
                   }
    
    }
    
    public String getFiltro()throws Exception {
        try{
            String StrFiltro="";
           
            if ( (this.Fecha!=null) && (!(this.Fecha.equalsIgnoreCase("Fecha"))) && (!(this.Fecha.equalsIgnoreCase("dd/mm/yyyy")))){
                   StrFiltro= StrFiltro + " AND F.FECHA_FACTURA =TO_DATE('" + Fecha + "','DD/MM/YYYY')";     
            }
                
            if ((this.FiltroInstitucion!=null) && (!(this.FiltroInstitucion.equalsIgnoreCase("null")))){
                 StrFiltro = StrFiltro + " AND F.INSTITUCION_ID ='" + this.FiltroInstitucion + "'";
            }
            
             if ((this.FiltroStatus!=null) && (!(this.FiltroStatus.equalsIgnoreCase("null")))){
                 StrFiltro = StrFiltro + " AND F.STATUS ='" + this.FiltroStatus + "'";
            }
      

            return StrFiltro;  
        
        }catch(Exception e){
            error="Erro en getFiltro:" + e;
            throw new Exception(error + e);
        }   
    }
    public String  GenerarSQL()throws Exception{
        try{
        books book = new books();    
        String Sql1 ="";
        String StrFiltro="";
        
           
            if ( (this.Fecha!=null) && (!(this.Fecha.equalsIgnoreCase("Fecha Factura"))) && (!(this.Fecha.equalsIgnoreCase("dd/mm/yyyy")))){
                   StrFiltro= StrFiltro + " and f.fecha_factura " + this.condicionFecha +" TO_DATE('" + Fecha + "','DD/MM/YYYY')";     
                // this.condicionFecha= StrFiltro + " and f.fecha_factura " + this.condicionFecha +" TO_DATE('" + Fecha + "','DD/MM/YYYY')";     
                // StrFiltro= StrFiltro + " and f.fecha_factura = TO_DATE('" + Fecha + "','DD/MM/YYYY')";     
           
            }
                
            if ((this.FiltroInstitucion!=null) && (!(this.FiltroInstitucion.equalsIgnoreCase("null")))){
                 StrFiltro = StrFiltro + " and f.institucion_id ='" + this.FiltroInstitucion + "'";
            }
            
             if ((this.FiltroStatus!=null) && (!(this.FiltroStatus.equalsIgnoreCase("null")))){
                 StrFiltro = StrFiltro + " and f.status_id ='" + this.FiltroStatus + "'";
            }
        
        
       
        Sql1 = "select f.contrato,f.concepto,f.nro,f.fecha_factura,f.dolares,t.cambio ,";
        Sql1=Sql1 + "i.nombre,f.tipocambio_id,f.institucion_id,f.codigocontable,s.nombre status,f.fecha_pago,f.anio_canc,f.facturasemi_id from ";
        Sql1 = Sql1 + " facturas_emitidas f,institucion  i,tipo_cambio t,status s where s.status_id=f.status_id and t.tipocambio_id=f.tipocambio_id and i.institucion_id=f.institucion_id ";
        Sql1=Sql1 + StrFiltro;
        Sql1 += " order by i.nombre,f.fecha_factura";
        return Sql1;
        }
        catch(Exception e){
        error="Ocurrio una excepcion en GenerarSql:" + e;
        throw new Exception (error);
        }
    }
    public String[] separarString(String cadena){
        // String arreglo[] = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
          int longitud=(cadena.length()/50) +1;
          
          String arreglo[] = new String[longitud];
        try{
            
            int j=0;
            int k=0;
             //inicializamos arreglo
             for (int i=0;i<longitud;i++)
                        arreglo[i] ="";
                         
            for (int i=0;i<=cadena.length();i++){
                        arreglo[j] +=cadena.charAt(i);
            if (k>50){
                     k=0;
                     j +=1;
                 } //end if
             k++;     
             }//end for
        }  //end try
      catch(Exception e){
           error="Hubo en separarString:" + e; 
          // throw new Exception(error);
      }  
       return arreglo;     
    }  //end del metodo
}

