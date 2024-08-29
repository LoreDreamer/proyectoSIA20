import java.util.*;

public class Functions {

  private final static Scanner scanner = new Scanner(System.in);

  public static void agregarPresentacion(Map<String, List<Presentacion>> presentaciones) {
    
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

    ArrayList<Persona> participantes = new ArrayList<Persona>();

    Presentacion nuevaPresentacion = new Presentacion(titulo, horaInicio, horaFin, sala, participantes);

    for (int i = 0; i < numParticipants; i++) {
      System.out.print("\n" + "Ingrese el nombre del asistente: ");
      String newName = scanner.nextLine();

      System.out.print("\n" + "Ingrese el rut del asistente: ");
      String newRut = scanner.nextLine();

      System.out.print("\n" + "Ingrese si es expositor (true/false): ");
      boolean esExpositor = Boolean.parseBoolean(scanner.nextLine());

      Persona tempPersona = new Persona(newName, newRut, esExpositor);
      nuevaPresentacion.addParticipant(tempPersona);
    }

    presentaciones.get(dia).add(nuevaPresentacion);

    limpiarPantalla();

    System.out.println("Presentación agregada con éxito para el día " + dia + "." + "\n");
  }

  public static void mostrarPresentacion(Map<String, List<Presentacion>> presentaciones) {

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

  public static void recalendarizarPresentacion(Map<String, List<Presentacion>> presentaciones) {
    
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
    }

    System.out.print("Ingrese el título de la presentación: ");
    String titulo = scanner.nextLine();

    Presentacion newPresentacion = null;

    for (Presentacion presentacion : listaPresentaciones) {
      if (presentacion.getTitulo().equalsIgnoreCase(titulo)) {
        newPresentacion = presentacion;
        break;
      }
    } 

    if (newPresentacion == null) {
      System.out.println("No se encontró ninguna presentación con el título " + titulo + ".");
      return;
    }

    System.out.println("Detalles actuales de la presentación:");
    newPresentacion.mostrarInformacion();
    
    System.out.print("Ingrese el nuevo día para la presentación (lun, mar, mie, jue, vie, sab): ");
    String nuevoDia = scanner.nextLine().toLowerCase();

    if (!presentaciones.containsKey(nuevoDia)) {
      System.out.println("Día inválido. Por favor, ingrese un día válido" + "\n" + "(lun, mar, mie, jue, vie, sab).");
      return;
    }

    presentaciones.get(dia).remove(newPresentacion);

    System.out.println("Ingrese la nueva hora de inicio para la presentación: ");
    String nuevaHoraInicio = scanner.nextLine();

    System.out.println("Ingrese la nueva hora de fin para la presentación: ");
    String nuevaHoraFin = scanner.nextLine();

    newPresentacion.setHoraInicio(nuevaHoraInicio);
    newPresentacion.setHoraFin(nuevaHoraFin);

    presentaciones.get(nuevoDia).add(newPresentacion);
    System.out.println("Presentación cambiada con éxito");


  }

  // Métodos auxiliares

  public static void limpiarPantalla() {
    System.out.print("\033[H\033[2J");  
    System.out.flush();
  }
  
  @SuppressWarnings("resource")
  public static void presioneTecla() {
    Scanner inputScanner = new Scanner(System.in);
    inputScanner.nextLine();
  }

  public static void inicializarDatos(Map<String, List<Presentacion>> presentaciones) {

    Persona expositor1 = new Persona("Carlos Pérez", "12345678-9", true);
    Persona asistente1 = new Persona("Ana González", "11765432-1", false);
    Persona expositor2 = new Persona("María Rodríguez", "21456789-0", true);

    Presentacion presentacion1 = new Presentacion("Introducción a la IA", "10:00", "11:00", "IBC 2-12", new ArrayList<>(Arrays.asList(expositor1, asistente1)));
    Presentacion presentacion2 = new Presentacion("Avances en Machine Learning", "12:00", "13:00", "IBC 2-4", new ArrayList<>(Arrays.asList(expositor2)));

    presentaciones.get("lun").add(presentacion1);
    presentaciones.get("mar").add(presentacion2);
  }

  
}
