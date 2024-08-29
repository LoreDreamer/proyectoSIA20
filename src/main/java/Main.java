import java.util.*;

public class Main {

  private final static Map<String, List<Presentacion>> presentaciones = new HashMap<String, List<Presentacion>>();
  private final static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {

    // Inicializar el mapa con los días de la semana
    presentaciones.put("lun", new ArrayList<Presentacion>());
    presentaciones.put("mar", new ArrayList<Presentacion>());
    presentaciones.put("mie", new ArrayList<Presentacion>());
    presentaciones.put("jue", new ArrayList<Presentacion>());
    presentaciones.put("vie", new ArrayList<Presentacion>());
    presentaciones.put("sab", new ArrayList<Presentacion>());

    Functions.inicializarDatos(presentaciones);

    while (true) {

      System.out.println("\n====================================================");
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
          Functions.limpiarPantalla();
          Functions.agregarPresentacion(presentaciones);
          System.out.println("Presione enter para continuar...");
          Functions.presioneTecla();
          Functions.limpiarPantalla();
          break;
          
        case 2:
          Functions.limpiarPantalla();
          Functions.mostrarPresentacion(presentaciones);
          System.out.println("Presione enter para continuar...");
          Functions.presioneTecla();
          Functions.limpiarPantalla();
          break;
          
        case 3:  
          Functions.limpiarPantalla();
          Functions.recalendarizarPresentacion(presentaciones);
          System.out.println("Presione enter para continuar...");
          Functions.presioneTecla();
          Functions.limpiarPantalla();
          break;
          
        case 4:
          Functions.limpiarPantalla();
          Functions.cambiarAsistente(presentaciones);


          System.out.println("Presione enter para continuar...");
          Functions.presioneTecla();
          Functions.limpiarPantalla();
          break;
          
        case 5:
          Functions.limpiarPantalla();
          System.out.println("Saliendo del programa...");
          return;
          
        default:
          System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
      }
    }
  }
}
