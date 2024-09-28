import java.util.*;

public class Functions {

  private final static Scanner scanner = new Scanner(System.in); // Escáner para la entrada del usuario.
  private static Map<String, Asistente> mapaAsistentes = new HashMap<>();

  // Método para agregar una nueva presentación.
  public static void agregarPresentacion(Map<String, List<Presentacion>> presentaciones) {
    
    System.out.println("======================================");
    System.out.println("          Agregar Presentación        ");
    System.out.println("======================================\n");
    
    // Solicita al usuario que ingrese el día de la presentación.
    System.out.print("Ingrese el día de la presentación (lun, mar, mie, jue, vie, sab): ");
    String dia = scanner.nextLine().toLowerCase();

    // Verifica que el día ingresado sea válido.
    if (!presentaciones.containsKey(dia)) {
      System.out.println("\n" + "Por favor, ingrese un día válido (lun, mar, mie, jue, vie, sab)." + "\n\n");
      return;
    }
    
    // Solicita los detalles de la presentación.
    System.out.print("Ingrese el título de la presentación: ");
    String titulo = scanner.nextLine();

    System.out.print("Ingrese la hora de inicio en formato 24hrs (Ej: 13:00, 04:00, etc): ");
    String horaInicio = scanner.nextLine();

    System.out.print("Ingrese la hora de fin en formato 24hrs (Ej: 13:00, 04:00, etc): ");
    String horaFin = scanner.nextLine();

    System.out.print("Ingrese la sala: ");
    String sala = scanner.nextLine();

    System.out.print("Ingrese número de asistentes: ");
    int numParticipants = Integer.parseInt(scanner.nextLine());
    
    ArrayList<Persona> participantes = new ArrayList<Persona>(); // Crea una lista para los participantes.

    limpiarPantalla(); // Limpia la pantalla antes de agregar los participantes.

    System.out.println("======================================");
    System.out.println("         Agregar Participantes        ");
    System.out.println("======================================\n");

    // Crea una nueva presentación con los detalles proporcionados.
    Presentacion nuevaPresentacion = new Presentacion(titulo, horaInicio, horaFin, sala, participantes);

    // Solicita los detalles de cada participante.
    for (int i = 0; i < numParticipants; i++) {

      if (i == 0) {

        System.out.print("Ingrese el nombre del expositor: ");
        String newExpositor = scanner.nextLine();

        System.out.print("Ingrese el rut del expositor: ");
        String rutExpositor = scanner.nextLine();

        String duracionExposicion = nuevaPresentacion.calcularDuracion(horaInicio, horaFin);

        nuevaPresentacion.agregarPersona(newExpositor, rutExpositor, duracionExposicion, rutExpositor);
        System.out.println();

      } else {

        System.out.print("Ingrese el nombre del asistente: ");
        String newName = scanner.nextLine();

        System.out.print("Ingrese el rut del asistente: ");
        String newRut = scanner.nextLine();

        int duracion = nuevaPresentacion.calcularDuracionMinutos(horaInicio, horaFin);
        
        Asistente tempA = mapaAsistentes.get(newRut);

        if (tempA != null) {

          tempA.setPresentaciones(tempA.getPresentaciones() + 1);
          tempA.setTiempoTotal(tempA.getTiempoTotal() + duracion);
          nuevaPresentacion.agregarPersona(newName, newRut, tempA.getPresentaciones() + 1, tempA.getTiempoTotal() + duracion);  // Agrega el participante a la presentación.

        } else {

          mapaAsistentes.put(newRut, new Asistente(newName, newRut, 1, duracion));
          nuevaPresentacion.agregarPersona(newName, newRut, 1, duracion);  // Agrega el participante a la presentación.

        }
        System.out.println();
      }
    }

    nuevaPresentacion.actualizarAsistentes(mapaAsistentes);
    presentaciones.get(dia).add(nuevaPresentacion); // Añade la nueva presentación a la lista del día seleccionado.
    
    System.out.println("\nPresentación agregada con éxito para el día " + dia + "." + "\n");
  }

  // Método para mostrar las presentaciones de un día específico.
  public static void mostrarPresentacion(Map<String, List<Presentacion>> presentaciones) {

    System.out.println("======================================");
    System.out.println("         Mostrar Presentaciones       ");
    System.out.println("======================================\n");
    
     // Solicita al usuario el día de las presentaciones que desea ver.
    System.out.print("Ingrese el día de la presentación que desea ver (lun, mar, mie, jue, vie, sab): ");
    String dia = scanner.nextLine().toLowerCase();

    // Verifica que el día ingresado sea válido.
    if (!presentaciones.containsKey(dia)) {
      System.out.println("Día inválido. Por favor, ingrese un día válido(lun, mar, mie, jue, vie, sab).");
      return;
    }

    List<Presentacion> listaPresentaciones = presentaciones.get(dia);
    
    limpiarPantalla(); // Limpia la pantalla antes de mostrar la información.
    
    // Muestra las presentaciones si hay alguna registrada para el día.
    if (listaPresentaciones.isEmpty()) {
      System.out.println("No hay presentaciones registradas para el día " + dia + ".");
    } else {

      for (Presentacion presentacion : listaPresentaciones) {
        presentacion.actualizarAsistentes(mapaAsistentes);
        presentacion.mostrarInformacion();
        System.out.println();
      }
    }
  }

  // Método para recalendarizar una presentación.
  public static void recalendarizarPresentacion(Map<String, List<Presentacion>> presentaciones) {
    
    System.out.println("======================================");
    System.out.println("       Recalendarizar Presentación    ");
    System.out.println("======================================\n");

    // Solicita al usuario el día de la presentación que desea reprogramar.
    System.out.print("Ingrese el día de la presentación que desea reprogramar (lun, mar, mie, jue, vie, sab): ");
    String dia = scanner.nextLine().toLowerCase();

    // Verifica que el día ingresado sea válido.
    if (!presentaciones.containsKey(dia)) {
      System.out.println("Día inválido. Por favor, ingrese un día válido (lun, mar, mie, jue, vie, sab).\n");
      return;
    }

    List<Presentacion> listaPresentaciones = presentaciones.get(dia);

    // Verifica si hay presentaciones registradas para el día.
    if (listaPresentaciones.isEmpty()){
      System.out.println("No hay presentaciones registradas para el día " + dia + ".\n");
      return;
    }

    System.out.print("Ingrese el título de la presentación: "); // Solicita el título de la presentación que se desea recalendarizar.
    String titulo = scanner.nextLine();

    Presentacion nuevaPresentacion = null;

    // Busca la presentación con el título proporcionado.
    for (Presentacion presentacion : listaPresentaciones) {
      if (presentacion.getTitulo().equalsIgnoreCase(titulo)) {
        nuevaPresentacion = presentacion;
        break;
      }
    } 

    // Verifica si se encontró la presentación.
    if (nuevaPresentacion == null) {
      System.out.println("No se encontró ninguna presentación con el título " + titulo + ".\n");
      return;
    }

    System.out.println("\n");
    nuevaPresentacion.mostrarInformacion();
    
    // Solicita el nuevo día para la presentación.
    System.out.print("\nIngrese el nuevo día para la presentación (lun, mar, mie, jue, vie, sab): ");
    String nuevoDia = scanner.nextLine().toLowerCase();

    // Verifica que el nuevo día sea válido.
    if (!presentaciones.containsKey(nuevoDia)) {
      System.out.println("Día inválido. Por favor, ingrese un día válido (lun, mar, mie, jue, vie, sab).\n");
      return;
    }

    // Elimina la presentación del día actual y agrega la presentación con la nueva información.
    presentaciones.get(dia).remove(nuevaPresentacion);

    System.out.print("Ingrese la nueva hora de inicio para la presentación: ");
    String nuevaHoraInicio = scanner.nextLine();

    System.out.print("Ingrese la nueva hora de fin para la presentación: ");
    String nuevaHoraFin = scanner.nextLine();

    nuevaPresentacion.setHoraInicio(nuevaHoraInicio);
    nuevaPresentacion.setHoraFin(nuevaHoraFin);

    presentaciones.get(nuevoDia).add(nuevaPresentacion);
    System.out.println("\nPresentación cambiada con éxito.");

  }

  // Método para gestionar los asistentes de una presentación.
  public static void cambiarAsistente(Map<String, List<Presentacion>> presentaciones) {

    System.out.println("======================================");
    System.out.println("         Gestión de Asistentes        ");
    System.out.println("======================================\n");

    // Solicita al usuario el día de la presentación que desea modificar.
    System.out.print("Ingrese el día de la presentación (lun, mar, mie, jue, vie, sab): ");
    String dia = scanner.nextLine().toLowerCase();

    // Verifica que el día ingresado sea válido.
    if (!presentaciones.containsKey(dia)) {
        System.out.println("Día inválido. Por favor, ingrese un día válido (lun, mar, mie, jue, vie, sab).");
        return;
    }

    List<Presentacion> listaPresentaciones = presentaciones.get(dia);

     // Verifica si hay presentaciones registradas para el día.
    if (listaPresentaciones.isEmpty()) {
      System.out.println("No hay presentaciones registradas para el día " + dia + ".");
      return;
    }

    System.out.print("Ingrese el título de la presentación: ");  // Solicita el título de la presentación que desea modificar.
    String titulo = scanner.nextLine();

    Presentacion presentacionEncontrada = null;

    // Busca la presentación con el título proporcionado.
    for (Presentacion presentacion : listaPresentaciones) {
      if (presentacion.getTitulo().equalsIgnoreCase(titulo)) {
        presentacionEncontrada = presentacion;
        break;
      }
    }

    // Verifica si se encontró la presentación.
    if (presentacionEncontrada == null) {
      System.out.println("No se encontró ninguna presentación con el título " + titulo + ".");
      return;
    }

    int duracion = presentacionEncontrada.calcularDuracionMinutos(presentacionEncontrada.getHoraInicio(), presentacionEncontrada.getHoraFin());

    limpiarPantalla();  // Limpia la pantalla antes de mostrar el menú de gestión de asistentes.

    // Menú para gestionar asistentes.
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
        case 1: // Añadir nuevo asistente.
          
          System.out.print("Ingrese el nombre del nuevo asistente: ");
          String nuevoNombreA = scanner.nextLine();

          System.out.print("Ingrese el RUT del nuevo asistente: ");
          String nuevoRutA = scanner.nextLine();

          if (presentacionEncontrada.buscarParticipantePorEspecificacion(nuevoRutA, nuevoNombreA) != null) {
            System.out.println("Ya existe un asistente con el nombre y RUT proporcionados.");
            return;
          }

          Asistente temp = mapaAsistentes.get(nuevoRutA);

          if (temp != null) {

            temp.setPresentaciones(temp.getPresentaciones() + 1);
            temp.setTiempoTotal(temp.getTiempoTotal() + duracion);
            presentacionEncontrada.agregarPersona(temp);

          } else {

            mapaAsistentes.put(nuevoRutA, new Asistente(nuevoNombreA, nuevoRutA, 1, duracion));
            presentacionEncontrada.agregarPersona(nuevoNombreA, nuevoRutA, 1, duracion);

          }

          System.out.println("\nAsistente añadido con éxito.");
          System.out.println("Presione enter para continuar...");
          presioneTecla();

          break;

        case 2: // Cambiar datos del asistente.
                
          System.out.print("Ingrese el RUT del asistente que desea cambiar: ");
          String rutAsistente = scanner.nextLine();

          Asistente tempPersona = (Asistente) presentacionEncontrada.buscarParticipantePorEspecificacion(rutAsistente);

          // Verifica si se encontró al asistente.
          if (tempPersona == null) {
            System.out.println("No se encontró ningún asistente con el RUT " + rutAsistente + ".");
            break;
          }

          System.out.print("Ingrese el nuevo nombre del asistente: ");
          String nuevoNombreB = scanner.nextLine();

          System.out.print("Ingrese el nuevo RUT del asistente: ");
          String nuevoRutB = scanner.nextLine();

          // Verifica si ya existe un asistente con el nuevo nombre y RUT.

          if (presentacionEncontrada.buscarParticipantePorEspecificacion(nuevoRutB, nuevoNombreB) != null) {
            System.out.println("Ya existe un asistente con el nombre y RUT proporcionados.");
            break;
          }

          Asistente tempAsistenteA = mapaAsistentes.get(rutAsistente);
          Asistente tempAsistenteB = mapaAsistentes.get(nuevoRutB);
     
          if (!tempAsistenteA.compararCon(nuevoRutB, nuevoNombreB) && tempAsistenteA != null && tempAsistenteB != null) {

            tempAsistenteA.setPresentaciones(tempAsistenteA.getPresentaciones() - 1);
            tempAsistenteA.setTiempoTotal(tempAsistenteA.getTiempoTotal() - duracion);
            
            tempAsistenteB.setPresentaciones(tempAsistenteB.getPresentaciones() +  1);
            tempAsistenteB.setTiempoTotal(tempAsistenteB.getTiempoTotal() + duracion);

            presentacionEncontrada.modificarParticipante(tempPersona, nuevoNombreB, nuevoRutB);

          } else if (!tempAsistenteA.compararCon(nuevoRutB, nuevoNombreB) && tempAsistenteA != null && tempAsistenteB == null) {

            tempAsistenteA.setPresentaciones(tempAsistenteA.getPresentaciones() - 1);
            tempAsistenteA.setTiempoTotal(tempAsistenteA.getTiempoTotal() - duracion);
            
            mapaAsistentes.put(nuevoRutB, new Asistente(nuevoNombreB, nuevoRutB, 1, duracion));

            presentacionEncontrada.modificarParticipante(tempPersona, nuevoNombreB, nuevoRutB);
          }

          System.out.println("\n\nAsistente actualizado con éxito.");
          System.out.println("Presione enter para continuar...");
          presioneTecla();

          break;

          case 3: // Sacar asistente.
                
            System.out.print("Ingrese el RUT del asistente que desea sacar: ");
            String rutSacar = scanner.nextLine();

            Persona asistenteASacar = presentacionEncontrada.buscarParticipantePorEspecificacion(rutSacar);

            // Verifica si se encontró al asistente.

            if (asistenteASacar == null) {
              System.out.println("No se encontró ningún asistente con el RUT " + rutSacar + ".");
              System.out.println("Presione enter para continuar...");
              presioneTecla();
              
              break;
            }

            Asistente asistenteMapa = mapaAsistentes.get(rutSacar);

            asistenteMapa.setPresentaciones(asistenteMapa.getPresentaciones() - 1);
            asistenteMapa.setTiempoTotal(asistenteMapa.getTiempoTotal() - duracion);

            // Elimina al asistente de la lista.
            presentacionEncontrada.eliminarParticipante(asistenteASacar);
            System.out.println("\nAsistente sacado con éxito.");
            System.out.println("Presione enter para continuar...");
            presioneTecla();

            break;

            case 4: // Volver al menú principal.
              return;

            default:
              System.out.println("Opción inválida. Por favor, seleccione una opción válida."); // Muestra un mensaje de error si la opción ingresada no es válida.
        }
        limpiarPantalla();
    }
  }

  // -------------------- Métodos auxiliares -------------------- //
  
  // Método auxiliar para limpiar la pantalla.
  public static void limpiarPantalla() {
    System.out.print("\033[H\033[2J");  
    System.out.flush();
  }
  
  // Método auxiliar para esperar que el usuario presione una tecla.
  @SuppressWarnings("resource")
  public static void presioneTecla() {
    Scanner inputScanner = new Scanner(System.in);
    inputScanner.nextLine();
  }

  // Método para inicializar algunos datos de prueba.
  public static void inicializarDatos(Map<String, List<Presentacion>> presentaciones) {

    Persona expositor1 = new Expositor("Carlos Perez", "12345678-9", "1:00", "Introduccion a la IA");
    Persona asistente1 = new Asistente("Ana Gonzalez", "11111111-1", 1, 60);
    Persona expositor2 = new Expositor("Maria Rodriguez", "21456789-0", "1:00", "Avances en Machine Learning");
    Persona asistente2 = new Asistente("Eloisa Cortes", "11111111-2", 1, 60);

    Presentacion presentacion1 = new Presentacion("Introduccion a la IA", "10:00", "11:00", "IBC 2-12", new ArrayList<>(Arrays.asList(expositor1, asistente1)));
    Presentacion presentacion2 = new Presentacion("Avances en Machine Learning", "12:00", "13:00", "IBC 2-4", new ArrayList<>(Arrays.asList(expositor2, asistente2)));

    presentaciones.get("lun").add(presentacion1);
    presentaciones.get("mar").add(presentacion2);

    mapaAsistentes.put(asistente1.getRut(), (Asistente) asistente1);
    mapaAsistentes.put(asistente2.getRut(), (Asistente) asistente2);
    
  }
}