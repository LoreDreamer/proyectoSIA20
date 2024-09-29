import java.util.*;

public class Main {

  private final static Scanner scanner = new Scanner(System.in); // Escáner para la entrada del usuario.

  public static void main(String[] args) {
    
    Congreso congresoInternacional = new Congreso();

    congresoInternacional.inicializarPresentaciones();
    congresoInternacional.inicializarDatosPresentaciones();
    
    // Bucle principal del menú.
    while (true) {
      
      // Muestra el menú de opciones al usuario.
      System.out.println("\n====================================================");
      System.out.println("                MENU DE PRESENTACIONES              ");
      System.out.println("====================================================\n");
      System.out.println("1. Registrar presentación");
      System.out.println("2. Mostrar información de presentación");
      System.out.println("3. Recalendarizar presentación");
      System.out.println("4. Cambiar asistente de presentación");
      System.out.println("5. Salir" + "\n\n");

      System.out.print("Ingrese su opción: "); // Pide al usuario que ingrese una opción.
      
      // Lee la opción seleccionada por el usuario.
      int opcion = scanner.nextInt();
      scanner.nextLine(); // Limpiar el buffer del escáner.

      // Ejecuta la acción correspondiente según la opción seleccionada.
      switch (opcion) {
        case 1:
          // Limpia la pantalla, agrega una nueva presentación, y espera que el usuario presione una tecla para continuar.
          Functions.limpiarPantalla();
          Functions.agregarPresentacion(congresoInternacional);
          System.out.println("Presione enter para continuar...");
          Functions.presioneTecla();
          Functions.limpiarPantalla();
          break;
          
        case 2:
          // Limpia la pantalla, muestra la información de las presentaciones y espera que el usuario presione una tecla para continuar.
          Functions.limpiarPantalla();
          Functions.mostrarPresentacion(congresoInternacional);
          System.out.println("Presione enter para continuar...");
          Functions.presioneTecla();
          Functions.limpiarPantalla();
          break;
          
        case 3:  
          // Limpia la pantalla, recalendariza una presentación y espera que el usuario presione una tecla para continuar.
          Functions.limpiarPantalla();
          //Functions.recalendarizarPresentacion(congresoInternacional);
          System.out.println("Presione enter para continuar...");
          Functions.presioneTecla();
          Functions.limpiarPantalla();
          break;
          
        case 4:
          // Limpia la pantalla, cambia el asistente de una presentación y espera que el usuario presione una tecla para continuar.
          Functions.limpiarPantalla();
          //Functions.cambiarAsistente(congresoInternacional);


          System.out.println("Presione enter para continuar...");
          Functions.presioneTecla();
          Functions.limpiarPantalla();
          break;
          
        case 5:
          // Limpia la pantalla, muestra un mensaje de salida y termina el programa.
          Functions.limpiarPantalla();
          System.out.println("Saliendo del programa...");
          return;
          
        default:
          // Muestra un mensaje de error si la opción ingresada no es válida.
          System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
      }
    }
  }
}
