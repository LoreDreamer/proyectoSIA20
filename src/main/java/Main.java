import java.io.*;
import java.util.*;

public class Main {

  private static Map <String, Presentacion> presentaciones = new HashMap<String, Presentacion>();
  private static Scanner scanner = new Scanner(System.in);
 
  public static void main(String[] args) {

    while (true) {
      System.out.println("====================================================");
      System.out.println("                   MENU DE OPCIONES                 ");
      System.out.println("====================================================" + "\n");
      System.out.println("1. Registrar presentación");
      System.out.println("2. Mostrar información de presentación");
      System.out.println("3. Recalendarizar presentación");
      System.out.println("4. Cambiar asistente de presentación");
      System.out.println("5. Salir");

      int opcion = scanner.nextInt();
      scanner.nextLine();

      switch (opcion) 
      {
        case 1:
          agregarPresentacion();
          break;
          
        case 2:
          mostrarPresentacion();
          break;
          
        case 3:
          //recalendarizarPresentacion();
          break;
          
        case 4:
          //cambiarAsistente();
          break;
          
        case 5:
          System.out.println("Saliendo del programa...");
          return;
          
        default:
          System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
      }
    }
  }

  private static void agregarPresentacion() {
    System.out.print("Ingrese el título de la presentación: ");
    String titulo = scanner.nextLine();
    System.out.print("Ingrese el nombre del participante: ");
    String participante = scanner.nextLine();
    System.out.print("Ingrese la hora de inicio: ");
    String horaInicio = scanner.nextLine();
    System.out.print("Ingrese la hora de fin: ");
    String horaFin = scanner.nextLine();
    System.out.print("Ingrese la sala: ");
    String sala = scanner.nextLine();

    String id = "acaponertitulo" + (presentaciones.size() + 1);
    Presentacion nuevaPresentacion = new Presentacion(titulo, participante, horaInicio, horaFin, sala);
    presentaciones.put(id, nuevaPresentacion);

    System.out.println("Presentación agregada con éxito.");
  }

  private static void mostrarPresentacion() {
    for (Presentacion presentacion : presentaciones.values()) {
      presentacion.mostrarInformacion();
      System.out.println();
    }
  }
}



/*
presentaciones.put("acaponertitulo1", new Presentacion("acaponertitulo", "Tilin Lalo", "10:00 AM", "11:00 AM", "Sala FIN 3-2"));
presentaciones.put("acaponertitulo2", new Presentacion("acaponertitulo", "Tilin Aburto", "11:00 AM", "13:00 PM", "Sala ING AU 1-2"));
presentaciones.put("acaponertitulo3", new Presentacion("acaponertitulo", "Tilin Yamil", "13:00 PM", "14:00 PM", "Sala IBC 2-11"));

for (Map.Entry<String, Presentacion> entry : presentaciones.entrySet()){
  entry.getValue().mostrarInformacion();
  System.out.println();

  */