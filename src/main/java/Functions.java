import java.util.*;

public class Functions {

  private final static Scanner scanner = new Scanner(System.in);

  public static void agregarPresentacion(Map<String, List<Presentacion>> presentaciones) {
    
    System.out.println("======================================");
    System.out.println("          Agregar Presentación        ");
    System.out.println("======================================\n");
    
    System.out.print("Ingrese el día de la presentación (lun, mar, mie, jue, vie, sab): ");
    String dia = scanner.nextLine().toLowerCase();

    if (!presentaciones.containsKey(dia)) {
      System.out.println("\n" + "Por favor, ingrese un día válido (lun, mar, mie, jue, vie, sab)." + "\n\n");
      return;
    }

    System.out.print("Ingrese el título de la presentación: ");
    String titulo = scanner.nextLine();

    System.out.print("Ingrese la hora de inicio: ");
    String horaInicio = scanner.nextLine();

    System.out.print("Ingrese la hora de fin: ");
    String horaFin = scanner.nextLine();

    System.out.print("Ingrese la sala: ");
    String sala = scanner.nextLine();

    System.out.print("Ingrese número de asistentes: ");
    int numParticipants = Integer.parseInt(scanner.nextLine());

    ArrayList<Persona> participantes = new ArrayList<Persona>();

    limpiarPantalla();

    System.out.println("======================================");
    System.out.println("         Agregar Participantes        ");
    System.out.println("======================================\n");


    Presentacion nuevaPresentacion = new Presentacion(titulo, horaInicio, horaFin, sala, participantes);

    for (int i = 0; i < numParticipants; i++) {
      System.out.print("Ingrese el nombre del asistente: ");
      String newName = scanner.nextLine();

      System.out.print("Ingrese el rut del asistente: ");
      String newRut = scanner.nextLine();

      System.out.print("Ingrese si es expositor (true/false): ");
      boolean esExpositor = Boolean.parseBoolean(scanner.nextLine());

      nuevaPresentacion.addParticipant(newName, newRut, esExpositor);
    }

    presentaciones.get(dia).add(nuevaPresentacion);

    System.out.println("\nPresentación agregada con éxito para el día " + dia + "." + "\n");
  }

  public static void mostrarPresentacion(Map<String, List<Presentacion>> presentaciones) {

    System.out.println("======================================");
    System.out.println("         Mostrar Presentaciones       ");
    System.out.println("======================================\n");
    
    System.out.print("Ingrese el día de la presentación que desea ver (lun, mar, mie, jue, vie, sab): ");
    String dia = scanner.nextLine().toLowerCase();

    if (!presentaciones.containsKey(dia)) {
      System.out.println("Día inválido. Por favor, ingrese un día válido(lun, mar, mie, jue, vie, sab).");
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

    System.out.print("Ingrese el día de la presentación que desea reprogramar (lun, mar, mie, jue, vie, sab): ");
    String dia = scanner.nextLine().toLowerCase();

    if (!presentaciones.containsKey(dia)) {
      System.out.println("Día inválido. Por favor, ingrese un día válido (lun, mar, mie, jue, vie, sab).\n");
      return;
    }

    List<Presentacion> listaPresentaciones = presentaciones.get(dia);

    if (listaPresentaciones.isEmpty()){
      System.out.println("No hay presentaciones registradas para el día " + dia + ".\n");
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
      System.out.println("No se encontró ninguna presentación con el título " + titulo + ".\n");
      return;
    }

    System.out.println("\n");
    newPresentacion.mostrarInformacion();
    
    System.out.print("\nIngrese el nuevo día para la presentación (lun, mar, mie, jue, vie, sab): ");
    String nuevoDia = scanner.nextLine().toLowerCase();

    if (!presentaciones.containsKey(nuevoDia)) {
      System.out.println("Día inválido. Por favor, ingrese un día válido (lun, mar, mie, jue, vie, sab).\n");
      return;
    }

    presentaciones.get(dia).remove(newPresentacion);

    System.out.print("Ingrese la nueva hora de inicio para la presentación: ");
    String nuevaHoraInicio = scanner.nextLine();

    System.out.print("Ingrese la nueva hora de fin para la presentación: ");
    String nuevaHoraFin = scanner.nextLine();

    newPresentacion.setHoraInicio(nuevaHoraInicio);
    newPresentacion.setHoraFin(nuevaHoraFin);

    presentaciones.get(nuevoDia).add(newPresentacion);

    System.out.println("\nPresentación cambiada con éxito.");

  }

  public static void cambiarAsistente(Map<String, List<Presentacion>> presentaciones) {

    System.out.println("======================================");
    System.out.println("         Gestión de Asistentes        ");
    System.out.println("======================================\n");

    System.out.print("Ingrese el día de la presentación (lun, mar, mie, jue, vie, sab): ");
    String dia = scanner.nextLine().toLowerCase();

    if (!presentaciones.containsKey(dia)) {
        System.out.println("Día inválido. Por favor, ingrese un día válido (lun, mar, mie, jue, vie, sab).");
        return;
    }

    List<Presentacion> listaPresentaciones = presentaciones.get(dia);

    if (listaPresentaciones.isEmpty()) {
      System.out.println("No hay presentaciones registradas para el día " + dia + ".");
      return;
    }

    System.out.print("Ingrese el título de la presentación: ");
    String titulo = scanner.nextLine();

    Presentacion presentacionEncontrada = null;

    for (Presentacion presentacion : listaPresentaciones) {
      if (presentacion.getTitulo().equalsIgnoreCase(titulo)) {
        presentacionEncontrada = presentacion;
        break;
      }
    }

    if (presentacionEncontrada == null) {
      System.out.println("No se encontró ninguna presentación con el título " + titulo + ".");
      return;
    }

    limpiarPantalla();

    while (true) {
        
      System.out.println("======================================");
      System.out.println("      Opciones de Asistentes");
      System.out.println("======================================\n");
      System.out.println("1. Añadir un nuevo asistente");
      System.out.println("2. Cambiar los datos de un asistente");
      System.out.println("3. Sacar a un asistente");
      System.out.println("4. Volver al menú principal\n");

      System.out.print("Ingrese su opción: ");

      int opcion = scanner.nextInt();
      scanner.nextLine();

      switch (opcion) {
        case 1: // Añadir nuevo asistente
          
          System.out.print("Ingrese el nombre del nuevo asistente: ");
          String nombre = scanner.nextLine();

          System.out.print("Ingrese el RUT del nuevo asistente: ");
          String rut = scanner.nextLine();

          for (Persona persona : presentacionEncontrada.getLista()) {
            if (persona.compararCon(nombre, rut)) {
                System.out.println("Ya existe un asistente con el nombre y RUT proporcionados.");
                break;
            }
          }
          
          presentacionEncontrada.addParticipant(nombre, rut);

          System.out.println("\nAsistente añadido con éxito.");
          System.out.println("Presione enter para continuar...");
          presioneTecla();

          break;

        case 2: // Cambiar datos del asistente
                
          System.out.print("Ingrese el RUT del asistente que desea cambiar: ");
          String rutAsistente = scanner.nextLine();

          Persona tempPersona = null;

          for (Persona persona : presentacionEncontrada.getLista()) {
            if (persona.compararCon(rutAsistente)) {
                tempPersona = persona;
                break;
            }
          }

          if (tempPersona == null) {
            System.out.println("No se encontró ningún asistente con el RUT " + rutAsistente + ".");
            break;
          }

          System.out.print("Ingrese el nuevo nombre del asistente: ");
          String nuevoNombre = scanner.nextLine();

          System.out.print("Ingrese el nuevo RUT del asistente: ");
          String nuevoRut = scanner.nextLine();

          for (Persona persona : presentacionEncontrada.getLista()) {
            if (persona.compararCon(nuevoNombre, nuevoRut)) {
                System.out.println("Ya existe un asistente con el nombre y RUT proporcionados.");
                return;
            }
          }

          presentacionEncontrada.modifyParticipant(tempPersona, nuevoNombre, nuevoRut);

          System.out.println("\n\nAsistente actualizado con éxito.");
          System.out.println("Presione enter para continuar...");
          presioneTecla();

          break;

          case 3: // Sacar asistente
                
            System.out.print("Ingrese el RUT del asistente que desea sacar: ");
            String rutSacar = scanner.nextLine();

            Persona asistenteASacar = null;
            for (Persona persona : presentacionEncontrada.getLista()) {
              if (persona.compararCon(rutSacar)) {
                asistenteASacar = persona;
                break;
              }
            }

            if (asistenteASacar == null) {
              System.out.println("No se encontró ningún asistente con el RUT " + rutSacar + ".");
              System.out.println("Presione enter para continuar...");
              presioneTecla();
              
              break;
            }

            presentacionEncontrada.removeParticipant(asistenteASacar);
            System.out.println("\nAsistente sacado con éxito.");
            System.out.println("Presione enter para continuar...");
            presioneTecla();

            break;

            case 4: // Volver al menú principal
              return;

            default:
              System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
        }
        limpiarPantalla();
    }
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