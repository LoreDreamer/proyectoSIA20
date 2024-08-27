import java.util.*;

public class Main {

  private static Map<String, List<Presentacion>> presentaciones = new HashMap<String, List<Presentacion>>();
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {

    // Inicializar el mapa con los días de la semana
    
    presentaciones.put("lun", new ArrayList<Presentacion>());
    presentaciones.put("mar", new ArrayList<Presentacion>());
    presentaciones.put("mie", new ArrayList<Presentacion>());
    presentaciones.put("jue", new ArrayList<Presentacion>());
    presentaciones.put("vie", new ArrayList<Presentacion>());
    presentaciones.put("sab", new ArrayList<Presentacion>());

    while (true) {

      System.out.println("====================================================");
      System.out.println("                MENU DE PRESENTACIONES              ");
      System.out.println("====================================================\n");
      System.out.println("1. Registrar presentación");
      System.out.println("2. Mostrar información de presentación");
      System.out.println("3. Recalendarizar presentación");
      System.out.println("4. Cambiar asistente de presentación");
      System.out.println("5. Salir" + "\n\n");

      System.out.print("Ingrese su opción: ");

      int opcion = scanner.nextInt();
      scanner.nextLine();

      switch (opcion) {
          
        case 1:
          limpiarPantalla();
          agregarPresentacion();
          System.out.println("Presione enter para continuar...");
          presioneTecla();
          limpiarPantalla();
          break;
          
        case 2:
          limpiarPantalla();
          mostrarPresentacion();
          System.out.println("Presione enter para continuar...");
          presioneTecla();
          limpiarPantalla();
          break;
          
        case 3:  
          limpiarPantalla();
          recalendarizarPresentacion();
          break;
          
        case 4:
          limpiarPantalla();
          // cambiarAsistente();
          break;
          
        case 5:
          limpiarPantalla();
          System.out.println("Saliendo del programa...");
          return;
          
        default:
          System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
      }
    }
  }

  private static void agregarPresentacion() {
    
    System.out.println("======================================");
    System.out.println("          Agregar Presentación        ");
    System.out.println("======================================" + "\n");
    
    System.out.print("Ingrese el día de la presentación " + "\n" + "(lun, mar, mie, jue, vie, sab): ");
    String dia = scanner.nextLine().toLowerCase();

    if (!presentaciones.containsKey(dia)) {
      limpiarPantalla();
      System.out.println("\n" + "Por favor, ingrese un día válido " + "\n" + "(lun, mar, mie, jue, vie, sab)." + "\n\n");
      return;
    }

    System.out.print("\n" + "Ingrese el título de la presentación: ");
    String titulo = scanner.nextLine();

    System.out.print("\n" + "Ingrese la hora de inicio: ");
    String horaInicio = scanner.nextLine();

    System.out.print("\n" + "Ingrese la hora de fin: ");
    String horaFin = scanner.nextLine();

    System.out.print("\n" + "Ingrese la sala: ");
    String sala = scanner.nextLine();

    System.out.print("\n" + "Ingrese número de asistentes: ");
    int numParticipants = Integer.parseInt(scanner.nextLine());

    Persona[] participantes = new Persona[numParticipants];

    for (int i = 0; i < numParticipants; i++) {
      System.out.print("\n" + "Ingrese el nombre del asistente: ");
      String participante = scanner.nextLine();

      System.out.print("\n" + "Ingrese el rut del asistente: ");
      int rut = Integer.parseInt(scanner.nextLine());

      System.out.print("\n" + "Ingrese si es expositor (true/false): ");
      boolean esExpositor = Boolean.parseBoolean(scanner.nextLine());

      participantes[i] = new Persona(participante, rut, esExpositor);
    }

    Presentacion nuevaPresentacion = new Presentacion(titulo, horaInicio, horaFin, sala, participantes);
    presentaciones.get(dia).add(nuevaPresentacion);

    limpiarPantalla();

    System.out.println("Presentación agregada con éxito para el día " + dia + "." + "\n");
  }

  private static void mostrarPresentacion() {

    System.out.println("======================================");
    System.out.println("         Mostrar Presentaciones       ");
    System.out.println("======================================\n");
    
    System.out.print("Ingrese el día de la presentación que desea ver" + "\n" + "(lun, mar, mie, jue, vie, sab): ");
    String dia = scanner.nextLine().toLowerCase();

    if (!presentaciones.containsKey(dia)) {
      System.out.println("Día inválido. Por favor, ingrese un día válido" + "\n" + "(lun, mar, mie, jue, vie, sab).");
      return;
    }

    List<Presentacion> listaPresentaciones = presentaciones.get(dia);

    limpiarPantalla();
    
    if (listaPresentaciones.isEmpty()) {
      System.out.println("No hay presentaciones registradas para el día " + dia + ".");
    } else {
      for (Presentacion presentacion : listaPresentaciones) {
        presentacion.mostrarInformacion();
        System.out.println();
      }
    }   
  }

  private static void recalendarizarPresentacion() {
    
    System.out.println("======================================");
    System.out.println("       Recalendarizar Presentación    ");
    System.out.println("======================================\n");

    System.out.print("Ingrese el día de la presentación que desea reprogramar " + "\n" + "(lun, mar, mie, jue, vie, sab).");
    String dia = scanner.nextLine().toLowerCase();

    if (!presentaciones.containsKey(dia)) {
      System.out.println("Día inválido. Por favor, ingrese un día válido" + "\n" + "(lun, mar, mie, jue, vie, sab).");
      return;
    }

    List<Presentacion> listaPresentaciones = presentaciones.get(dia);

    if (listaPresentaciones.isEmpty()){
      System.out.println("No hay presentaciones registradas para el día " + dia + ".");
      return;
    } else {
      
    }
    
  }

  // Auxiliares
  
  public static void limpiarPantalla() {
      System.out.print("\033[H\033[2J");  
      System.out.flush();
  }
  
  public static void presioneTecla() {
      Scanner scanner = new Scanner(System.in);
      scanner.nextLine(); 
  }
}