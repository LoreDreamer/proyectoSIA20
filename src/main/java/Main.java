import java.io.*;
import java.util.*;

/*
Programación de presentaciones en un Congreso Internacional: Manejo del registro de
presentaciones con sus respectivos expositores y asistentes. Considerar la posible
recalendarización de las presentaciones y cambio de asistentes.
*/

public class Main {
 
  public static void main(String[] args) throws IOException{

    Map <String, Presentacion> presentaciones = new HashMap<String, Presentacion>();
    
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    int opcion;

    while (true) {
      System.out.println("====================================================");
      System.out.println("=                  MENU DE OPCIONES                =");
      System.out.println("====================================================" + "\n");
      System.out.println("1. Registrar presentación");
      System.out.println("2. Mostrar información de presentación");
      System.out.println("3. Recalendarizar presentación");
      System.out.println("4. Cambiar asistente de presentación");
      System.out.println("5. Salir");

      opcion = Integer.parseInt(input.readLine());

      switch (opcion) 
      {
        case 1:
          //agregarPresentacion();
          break;
        case 2:
          //agregarExpositor();
          break;
        case 3:
          //agregarAsistente();

          break;
        case 4:
          //mostrarPresentacion();
        case 5:
          System.out.println("Saliendo del programa...");
          break;
          
        default:
          System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
      }
    } while (opcion != 5);
    
    presentaciones.put("acaponertitulo1", new Presentacion("acaponertitulo", "Tilin Lalo", "10:00 AM", "11:00 AM", "Sala FIN 3-2"));
    presentaciones.put("acaponertitulo2", new Presentacion("acaponertitulo", "Tilin Aburto", "11:00 AM", "13:00 PM", "Sala ING AU 1-2"));
    presentaciones.put("acaponertitulo3", new Presentacion("acaponertitulo", "Tilin Yamil", "13:00 PM", "14:00 PM", "Sala IBC 2-11"));
    
    for (Map.Entry<String, Presentacion> entry : presentaciones.entrySet()){
      entry.getValue().mostrarInformacion();
      System.out.println();
    }

  }
}