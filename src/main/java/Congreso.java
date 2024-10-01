import java.util.*;

public class Congreso {

    private static Map<String, Asistente> mapaAsistentes;
    private static Map<String, Expositor> mapaExpositores;
    private static Map<String, List<Presentacion>> presentaciones;

    public Congreso() {
        mapaAsistentes = new HashMap<String, Asistente>();
        presentaciones = new HashMap<String, List<Presentacion>>();
    }
    
    public Asistente searchAsistente(String rut) throws rutInvalidoException{ 

        Asistente revision = mapaAsistentes.get(rut);

        if (revision == null) {
            return null;
        }

        if (!revision.verificarDatos()) {
            throw new rutInvalidoException();
        }

        return revision;
    }

    public void removingAsistenteFromPresentacion(String rut, int duracionRestar) {

        Asistente searching = null;

        try {
            searching = searchAsistente(rut);
        } catch (Exception e) {
            return;
        }

        searching.setTiempoTotal(searching.getTiempoTotal() - duracionRestar);
        searching.setPresentaciones(searching.getPresentaciones() - 1);
    }

    public void addingAsistenteToPresentacion(String rut, int duracionAnadir) {
        
        Asistente searching = null;

        try {
            searching = searchAsistente(rut);
        } catch (Exception e) {
            return;
        }
        
        searching.setTiempoTotal(searching.getTiempoTotal() + duracionAnadir);
        searching.setPresentaciones(searching.getPresentaciones() + 1);
    }

    public void addingAsistenteToPresentacion(String rut, String nombre, int duracionAnadir) {
        Asistente searching = new Asistente(nombre, rut, 1, duracionAnadir);
        mapaAsistentes.put(rut, searching);
    }

    public void actualizarAsistentes(Presentacion nuevaPresentacion) {
        nuevaPresentacion.actualizarAsistentes(mapaAsistentes);
    }

    public void inicializarPresentaciones() {

        presentaciones.put("lun", new ArrayList<Presentacion>());
        presentaciones.put("mar", new ArrayList<Presentacion>());
        presentaciones.put("mie", new ArrayList<Presentacion>());
        presentaciones.put("jue", new ArrayList<Presentacion>());
        presentaciones.put("vie", new ArrayList<Presentacion>());
        presentaciones.put("sab", new ArrayList<Presentacion>());

    }

    public void inicializarDatosPresentaciones() {

        Persona expositor1 = new Expositor("Carlos Perez", "12345678-9", 60, "Introduccion a la IA");
        Persona asistente1 = new Asistente("Ana Gonzalez", "11111111-1", 1, 60);
        Persona expositor2 = new Expositor("Maria Rodriguez", "21456789-0", 60, "Avances en Machine Learning");
        Persona asistente2 = new Asistente("Eloisa Cortes", "11111111-2", 1, 60);

        Presentacion presentacion1 = new Presentacion("Introduccion a la IA", "10:00", "11:00", "IBC 2-12", new ArrayList<>(Arrays.asList(expositor1, asistente1)));
        Presentacion presentacion2 = new Presentacion("Avances en Machine Learning", "12:00", "13:00", "IBC 2-4", new ArrayList<>(Arrays.asList(expositor2, asistente2)));

        presentaciones.get("lun").add(presentacion1);
        presentaciones.get("mar").add(presentacion2);

        mapaAsistentes.put(asistente1.getRut(), (Asistente) asistente1);
        mapaAsistentes.put(asistente2.getRut(), (Asistente) asistente2);

        mapaExpositores.put(expositor1.getRut(), (Expositor) expositor1);
        mapaExpositores.put(expositor2.getRut(), (Expositor) expositor2);

    }

    public static boolean  verficacionDia(String dia) {
        if (!presentaciones.containsKey(dia)) {
            System.out.println("\n" + "Por favor, ingrese un día válido (lun, mar, mie, jue, vie, sab)." + "\n\n");
            return false;
        }
        return true;
    }

    public void anadirPresentacion(Presentacion nuevaPresentacion, String dia) {
        presentaciones.get(dia).add(nuevaPresentacion);
    }

    public void removerPresentacion(Presentacion antiguaPresentacion, String dia) {
        presentaciones.get(dia).remove(antiguaPresentacion);
    }

    public void mostrarPresentacionesPorDia(String dia) {
        List<Presentacion> listaPresentaciones = presentaciones.get(dia);
    
        Functions.limpiarPantalla(); // Limpia la pantalla antes de mostrar la información.
    
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

    public Presentacion encontrarPresentacion(String dia, String titulo) {
        List<Presentacion> listaPresentaciones = presentaciones.get(dia);

        Functions.limpiarPantalla();

        if (listaPresentaciones.isEmpty()) {
            return null;
        } else {
            for (Presentacion presentacion : listaPresentaciones) {
                if (presentacion.getTitulo().equals(titulo)) {
                    return presentacion;
                }
            }
        }
        return null;
    }

    public void anadirExpositor(String rut, Expositor newExpositor) {
        mapaExpositores.put(rut, newExpositor);
    }

    public static ArrayList<Asistente> asistenteMapValuesToArrayList(Map<String, Asistente> map) {
        // Create a new ArrayList from the values of the HashMap
        return new ArrayList<>(map.values());
    }

    public static ArrayList<Expositor> expositorMapValuesToArrayList(Map<String, Expositor> map) {
        // Create a new ArrayList from the values of the HashMap
        return new ArrayList<>(map.values());
    }
    
    public void showPresentacionesAtendidas(int presentacionesAtendidas) {
        ArrayList<Asistente> newArrayList = asistenteMapValuesToArrayList(mapaAsistentes);
        
        // Now you can iterate over newArrayList
        for (Asistente asistente : newArrayList) {
            // Your logic for handling the asistente

            if (asistente.getPresentaciones() == presentacionesAtendidas) {
                asistente.mostrarInformacion();  // Example: print the asistente's name
                System.out.println();
            }
        }
    }

    public void showHorasAtendidas(int minutosAtendidos) {
        ArrayList<Asistente> newArrayList = asistenteMapValuesToArrayList(mapaAsistentes);
        
        for (Asistente asistente : newArrayList) {
            
            if (asistente.getTiempoTotal() == minutosAtendidos) {
                asistente.mostrarInformacion();  
                System.out.println();
            }
        }

    }

    public void showDuracionExposiciones(int duracionExposicion) {
        ArrayList<Expositor> newArrayList = expositorMapValuesToArrayList(mapaExpositores);

        for (Expositor expositor : newArrayList) {

            if (expositor.getDuracion() == duracionExposicion) {
                expositor.mostrarInformacion();
                System.out.println();
            }
        }
    }
    
}
