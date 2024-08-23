import java.io.*;
import java.util.*;

/*
Programación de presentaciones en un Congreso Internacional.
*/

public class Main {
 
  public static void main(String[] args) throws IOException{
  
    Map <String, Presentacion> presentaciones = new HashMap<String, Presentacion>();
    
    presentaciones.put("acaponertitulo1", new Presentacion("acaponertitulo", "Tilin Lalo", "10:00 AM", "11:00 AM", "Sala FIN 3-2"));
    presentaciones.put("acaponertitulo2", new Presentacion("acaponertitulo", "Tilin Aburto", "11:00 AM", "13:00 PM", "Sala ING AU 1-2"));
    presentaciones.put("acaponertitulo3", new Presentacion("acaponertitulo", "Tilin Yamil", "13:00 PM", "14:00 PM", "Sala IBC 2-11"));

    for (Map.Entry<String, Presentacion> entry : presentaciones.entrySet()){
      entry.getValue().mostrarInformacion();
      System.out.println();
    }
    
    
  }
}