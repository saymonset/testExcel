/*
 * CrearDirectorio.java
 *
 * Created on 10 de febrero de 2005, 04:35 PM
 */

package pkfactura;
import java.io.*;
/**
 *
 * @author SRodriguez
 */
public class CrearDirectorio {
    
    /** Creates a new instance of CrearDirectorio */
    public CrearDirectorio() {
    }
     public CrearDirectorio(String Directorio) {
          // Creamos el nombre de un directorio mediante un objeto File
          // El directorio raiz debe de existir -- mkdir --
         /* File directorio = new File("c:\\temp\\directorio");
          if (directorio.mkdir())
              System.out.println("Se ha creado directorio");
          else
              System.out.println("NOOOO Se ha creado directorio"); */

          // El directorio raiz no tiene pq existir -- mkdirs --
          File directorio2 = new File(Directorio);
          if (directorio2.mkdirs())
              System.out.println("Se ha creado directorio");
      //    else
        //      System.out.println("NOOOO Se ha creado directorio"); 

    }
}
