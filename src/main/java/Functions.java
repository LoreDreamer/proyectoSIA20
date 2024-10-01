import java.util.*;

public class Functions {

  private final static Scanner scanner = new Scanner(System.in); // Escáner para la entrada del usuario.

  // Método para agregar una nueva presentación.
  public static void agregarPresentacion(Congreso congresoInternacional) {
    
    System.out.println("======================================");
    System.out.println("          Agregar Presentación        ");
    System.out.println("======================================\n");
    
    // Solicita al usuario que ingrese el día de la presentación.
    System.out.print("Ingrese el día de la presentación (lun, mar, mie, jue, vie, sab): ");
    String dia = scanner.nextLine().toLowerCase();

    // Verifica que el día ingresado sea válido.
    boolean flag = Congreso.verficacionDia(dia);

    if (!flag) {
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

    // Crea una nueva presentación con los detalles proporcionados
    Presentacion nuevaPresentacion = new Presentacion(titulo, horaInicio, horaFin, sala, participantes);

    try {
      nuevaPresentacion.verificarHora();
    } catch (Exception e) {
      System.out.println("Las horas ingresadas no son válidas, se reiniciará el proceso nuevamente...");
      System.out.println("Presione Enter para continuar...");

      presioneTecla();
      limpiarPantalla();
      agregarPresentacion(congresoInternacional);
      return;
    }
    
    // Solicita los detalles de cada participante.
    for (int i = 0; i < numParticipants; i++) {

      if (i == 0) {

        System.out.print("Ingrese el nombre del expositor: ");
        String newExpositor = scanner.nextLine();

        System.out.print("Ingrese el rut del expositor: ");
        String rutExpositor = scanner.nextLine();
        
        int duracionExposicion = nuevaPresentacion.calcularDuracionMinutos(horaInicio, horaFin);

        try {
          nuevaPresentacion.agregarPersona(newExpositor, rutExpositor, duracionExposicion, titulo);
        } catch (Exception e) {
          System.out.println("\nRut inválido, se reiniciará el proceso nuevamente...");
          System.out.println("Presione Enter para continuar...");

          presioneTecla();
          limpiarPantalla();
          agregarPresentacion(congresoInternacional);
          return;
        }

        congresoInternacional.anadirExpositor(rutExpositor, new Expositor(newExpositor, rutExpositor, duracionExposicion, titulo));

        System.out.println();

      } else {

        System.out.print("Ingrese el nombre del asistente: ");
        String newName = scanner.nextLine();

        System.out.print("Ingrese el rut del asistente: ");
        String newRut = scanner.nextLine();

        int duracion = nuevaPresentacion.calcularDuracionMinutos(horaInicio, horaFin);
        Asistente tempA = null;

        try {
          tempA = congresoInternacional.searchAsistente(newRut);
        } catch(Exception e) {
          System.out.println("\nRut inválido, se reiniciará el proceso nuevamente...");
          System.out.println("Presione Enter para continuar...");

          presioneTecla();
          limpiarPantalla();
          agregarPresentacion(congresoInternacional);
          return;
        }
        
        if (tempA != null) {

          congresoInternacional.addingAsistenteToPresentacion(newRut, duracion);
          nuevaPresentacion.agregarPersona(newName, newRut, tempA.getPresentaciones() + 1, tempA.getTiempoTotal() + duracion);  // Agrega el participante a la presentación.

        } else {

          congresoInternacional.addingAsistenteToPresentacion(newRut, newName, duracion);
          nuevaPresentacion.agregarPersona(newName, newRut, 1, duracion);  // Agrega el participante a la presentación.

        }
        System.out.println(); 
      }
    }

    congresoInternacional.actualizarAsistentes(nuevaPresentacion);
    congresoInternacional.anadirPresentacion(nuevaPresentacion, dia); // Añade la nueva presentación a la lista del día seleccionado.
    
    System.out.println("\nPresentación agregada con éxito para el día " + dia + "." + "\n");
  }
  
  // Método para mostrar las presentaciones de un día específico.
  public static void mostrarPresentacion(Congreso congresoInternacional) {

    System.out.println("======================================");
    System.out.println("         Mostrar Presentaciones       ");
    System.out.println("======================================\n");
    
     // Solicita al usuario el día de las presentaciones que desea ver.
    System.out.print("Ingrese el día de la presentación que desea ver (lun, mar, mie, jue, vie, sab): ");
    String dia = scanner.nextLine().toLowerCase();

    // Verifica que el día ingresado sea válido.
    boolean flag = Congreso.verficacionDia(dia);

    if (!flag) {
      return;
    }

    congresoInternacional.mostrarPresentacionesPorDia(dia);

  }
   
  // Método para recalendarizar una presentación.
  public static void recalendarizarPresentacion(Congreso congresoInternacional) {
    
    System.out.println("======================================");
    System.out.println("       Recalendarizar Presentación    ");
    System.out.println("======================================\n");

    // Solicita al usuario el día de la presentación que desea reprogramar.
    System.out.print("Ingrese el día de la presentación que desea reprogramar (lun, mar, mie, jue, vie, sab): ");
    String dia = scanner.nextLine().toLowerCase();

    // Verifica que el día ingresado sea válido.
    boolean flaga = Congreso.verficacionDia(dia);

    if (!flaga) {
      return;
    }

    System.out.print("Ingrese el título de la presentación: "); // Solicita el título de la presentación que se desea recalendarizar.
    String titulo = scanner.nextLine();

    Presentacion nuevaPresentacion = congresoInternacional.encontrarPresentacion(dia, titulo);

    // Verifica si hay presentaciones registradas para el día.
    if (nuevaPresentacion == null){
      System.out.println("No se ha encontrado su presentación.");
      return;
    }

    System.out.println("\n");
    nuevaPresentacion.mostrarInformacion();
    congresoInternacional.removerPresentacion(nuevaPresentacion, dia); // Elimina la presentación del día actual y agrega la presentación con la nueva información.
    
    // Solicita el nuevo día para la presentación.
    System.out.print("\nIngrese el nuevo día para la presentación (lun, mar, mie, jue, vie, sab): ");
    String nuevoDia = scanner.nextLine().toLowerCase();

    // Verifica que el día ingresado sea válido.
    boolean flagb = Congreso.verficacionDia(dia);

    if (!flagb) {
      return;
    }

    System.out.print("Ingrese la nueva hora de inicio para la presentación: ");
    String nuevaHoraInicio = scanner.nextLine();

    System.out.print("Ingrese la nueva hora de fin para la presentación: ");
    String nuevaHoraFin = scanner.nextLine();

    Presentacion tempPresentacion = new Presentacion("titulo", nuevaHoraInicio, nuevaHoraFin, "sala", null);

    try {
      tempPresentacion.verificarHora();
    } catch (Exception e) {
      System.out.println("Las horas ingresadas no son válidas, se reiniciará el proceso nuevamente...");
      System.out.println("Presione Enter para continuar...");

      presioneTecla();
      limpiarPantalla();
      recalendarizarPresentacion(congresoInternacional);
      return;
    }

    nuevaPresentacion.setHoraInicio(nuevaHoraInicio);
    nuevaPresentacion.setHoraFin(nuevaHoraFin);

    congresoInternacional.anadirPresentacion(nuevaPresentacion, nuevoDia);
    System.out.println("\nPresentación cambiada con éxito.");

  }
   

  // Método para gestionar los asistentes de una presentación.
  public static void cambiarAsistente(Congreso congresoInternacional) {

    System.out.println("======================================");
    System.out.println("         Gestión de Asistentes        ");
    System.out.println("======================================\n");

    // Solicita al usuario el día de la presentación que desea modificar.
    System.out.print("Ingrese el día de la presentación (lun, mar, mie, jue, vie, sab): ");
    String dia = scanner.nextLine().toLowerCase();

    // Verifica que el día ingresado sea válido.
    boolean flaga = Congreso.verficacionDia(dia);

    if (!flaga) {
      return;
    }

    System.out.print("Ingrese el título de la presentación: ");  // Solicita el título de la presentación que desea modificar.
    String titulo = scanner.nextLine();

    Presentacion presentacionEncontrada = congresoInternacional.encontrarPresentacion(dia, titulo);

    if (presentacionEncontrada == null) { // Verifica si se encontró la presentación.
      System.out.println("No se ha encontrado su presentación.");
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

          Asistente temp = null;

          try {
            temp = congresoInternacional.searchAsistente(nuevoRutA);
          } catch(Exception e) {
            System.out.println("\nRut inválido, se reiniciará el proceso nuevamente...");
            System.out.println("Presione Enter para continuar...");

            presioneTecla();
            limpiarPantalla();
            break;
          }

          if (temp != null) {

            congresoInternacional.addingAsistenteToPresentacion(nuevoRutA, duracion);
            presentacionEncontrada.agregarPersona(temp);

          } else {

            congresoInternacional.addingAsistenteToPresentacion(nuevoRutA, nuevoNombreA, duracion);
            presentacionEncontrada.agregarPersona(nuevoNombreA, nuevoRutA, 1, duracion);  // Agrega el participante a la presentación.

          }

          System.out.println("\nAsistente añadido con éxito.");
          System.out.println("Presione enter para continuar...");
          presioneTecla();

          break;

        case 2: // Cambiar datos del asistente.
                
          System.out.print("Ingrese el RUT del asistente que desea cambiar: ");
          String rutAsistente = scanner.nextLine();
          Asistente tempPersona = null;

          try {
            tempPersona = (Asistente) presentacionEncontrada.buscarParticipantePorEspecificacion(rutAsistente);
          } catch (Exception e) {
            System.out.println("\nRut inválido, se reiniciará el proceso nuevamente...");
            System.out.println("Presione Enter para continuar...");

            presioneTecla();
            limpiarPantalla();
            break;
          }

          // Verifica si se encontró al asistente.
          if (tempPersona == null) {
            System.out.println("\nNo se encontró ningún asistente con el RUT " + rutAsistente + ".");
            System.out.println("Presione Enter para continuar.");

            presioneTecla();
            break;
          }

          System.out.print("Ingrese el nuevo nombre del asistente: ");
          String nuevoNombreB = scanner.nextLine();

          System.out.print("Ingrese el nuevo RUT del asistente: ");
          String nuevoRutB = scanner.nextLine();

          // Verifica si ya existe un asistente con el nuevo nombre y RUT.

          if (presentacionEncontrada.buscarParticipantePorEspecificacion(nuevoRutB, nuevoNombreB) != null) {
            System.out.println("\nYa existe un asistente con el nombre y RUT proporcionados.");
            System.out.println("Presione Enter para continuar.");

            presioneTecla();
            break;
          }

          Asistente tempAsistenteA = null;
          Asistente tempAsistenteB = null;

          try {
            tempAsistenteA = congresoInternacional.searchAsistente(rutAsistente);
            tempAsistenteB = congresoInternacional.searchAsistente(nuevoRutB);
          } catch (Exception e) {
            System.out.println("\nUno de los ruts es inválido, se reiniciará el proceso nuevamente...");
            System.out.println("Presione Enter para continuar...");

            presioneTecla();
            limpiarPantalla();
            break;
          }

          if (!tempAsistenteA.compararCon(nuevoRutB, nuevoNombreB) && tempAsistenteA != null && tempAsistenteB != null) {

            congresoInternacional.removingAsistenteFromPresentacion(rutAsistente, duracion);
            congresoInternacional.addingAsistenteToPresentacion(nuevoRutB, duracion);

            presentacionEncontrada.modificarParticipante(tempPersona, nuevoNombreB, nuevoRutB);

          } else if (!tempAsistenteA.compararCon(nuevoRutB, nuevoNombreB) && tempAsistenteA != null && tempAsistenteB == null) {

            congresoInternacional.removingAsistenteFromPresentacion(rutAsistente, duracion);
            congresoInternacional.addingAsistenteToPresentacion(nuevoRutB, nuevoNombreB, duracion);
            
            presentacionEncontrada.modificarParticipante(tempPersona, nuevoNombreB, nuevoRutB);
          }

          System.out.println("\n\nAsistente actualizado con éxito.");
          System.out.println("Presione enter para continuar...");
          presioneTecla();

          break;

          case 3: // Sacar asistente.
                
            System.out.print("Ingrese el RUT del asistente que desea sacar: ");
            String rutSacar = scanner.nextLine();

            Persona asistenteASacar = null;

            try {
              asistenteASacar = presentacionEncontrada.buscarParticipantePorEspecificacion(rutSacar);
            } catch (Exception e) {
              System.out.println("\nRut inválido, se reiniciará el proceso nuevamente...");
              System.out.println("Presione Enter para continuar...");

              presioneTecla();
              limpiarPantalla();
              break;
            }

            if (asistenteASacar == null) {
              System.out.println("No se encontró ningún asistente con el RUT " + rutSacar + ".");
              System.out.println("Presione enter para continuar...");
              presioneTecla();
              
              break;
            }

            congresoInternacional.removingAsistenteFromPresentacion(rutSacar, duracion);
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

  public static void mostrarPorFiltro(Congreso congresoInternacional) {

    System.out.println("======================================");
    System.out.println("      Filitrar asistentes/expositores");
    System.out.println("======================================\n");
    System.out.println("1. Filtrar por presentaciones atendidas (asistentes)");
    System.out.println("2. Filtrar por horas atendidas (asistentes)");
    System.out.println("3. Filtrar por duración exposición (expositores)");
    System.out.println("4. Volver al menú principal\n");

    System.out.print("Ingrese su opción: ");
    int opcion = scanner.nextInt();

    System.out.print("Ingrese su valor a filtrar: ");
    int valor = scanner.nextInt();

    System.out.println();

    switch (opcion) {

      case 1:

        congresoInternacional.showPresentacionesAtendidas(valor);
        break;
      
      case 2:

        congresoInternacional.showHorasAtendidas(valor);
        break;

      case 3:

        congresoInternacional.showDuracionExposiciones(opcion);
        break;

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
}