package Classes;
import java.util.*;

public class Congreso {

    private static Map<String, Asistente> mapaAsistentes;
    private static Map<String, Expositor> mapaExpositores;
    private static Map<String, List<Presentacion>> presentaciones;

    public Congreso() {
        mapaAsistentes = new HashMap<String, Asistente>();
        mapaExpositores = new HashMap<String, Expositor>();
        presentaciones = new HashMap<String, List<Presentacion>>();
    }
    
    public Asistente searchAsistente(String rut) throws datoInvalidosException{ 

        Asistente revision = mapaAsistentes.get(rut);
        Persona validPerson = new Persona("Revisor", rut);
        
        if (revision != null && revision.verificarDatos(rut, revision.getNombre(), revision.getTiempoTotal())) { 
            throw new datoInvalidosException(); // Verificacion en más detalle si se encuentra
        } else if (validPerson.verificarDatos(rut)) {
            throw new datoInvalidosException();
        }
        
        return revision;
    }

    public void removingAsistenteFromPresentacion(String rut, int duracionRestar) throws asistenteException{

        Asistente searching = null;
        
        if ((searching = mapaAsistentes.get(rut)) == null)
            throw new asistenteException();
       

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

        Persona expositor1 = new Expositor("Carlos Perez", "12345678-9", 120, "Introduccion a la IA");
        Persona asistente1 = new Asistente("Ana Gonzalez", "11111111-1", 1, 120);
        Persona expositor2 = new Expositor("Maria Rodriguez", "21456789-0", 60, "Avances en Machine Learning");
        Persona asistente2 = new Asistente("Eloisa Cortes", "11111111-2", 1, 60);

        Presentacion presentacion1 = new Presentacion("Introduccion a la IA", "10:00", "12:00", "IBC 2-12");
        Presentacion presentacion2 = new Presentacion("Avances en Machine Learning", "12:00", "13:00", "IBC 2-4");
        
        try {
        
            presentacion1.agregarPersona("Carlos Perez", "12345678-9", 120, "Introduccion a la IA");
            presentacion1.agregarPersona((Asistente) asistente1);
        
            presentacion2.agregarPersona("Maria Rodriguez", "21456789-0", 60, "Avances en Machine Learning");
            presentacion2.agregarPersona((Asistente) asistente2);
            
        
        } catch (Exception e) {
            System.out.println("L");
        }
        presentaciones.get("lun").add(presentacion1);
        presentaciones.get("mar").add(presentacion2);

        mapaAsistentes.put(asistente1.getRut(), (Asistente) asistente1);
        mapaAsistentes.put(asistente2.getRut(), (Asistente) asistente2);

        mapaExpositores.put(expositor1.getRut(), (Expositor) expositor1);
        mapaExpositores.put(expositor2.getRut(), (Expositor) expositor2);
     
    }

    public static boolean  verficacionDia(String dia) {
        if (!presentaciones.containsKey(dia)) {
            return false;
        }
        return true;
    }

    public void anadirPresentacion(Presentacion nuevaPresentacion, String dia) throws diaInvalidoException{
        
        dia = dia.toLowerCase().trim();
        
        if (!presentaciones.containsKey(dia)) {
            throw new diaInvalidoException();
        }
        
        presentaciones.get(dia).add(nuevaPresentacion);
    }

    public void removerPresentacion(Presentacion antiguaPresentacion, String dia) {
        presentaciones.get(dia).remove(antiguaPresentacion);
    }

    public Presentacion encontrarPresentacion(String dia, String titulo) throws presentacionNoEncontradaException, diaInvalidoException {
        
        if (!verficacionDia(dia)) {
            throw new diaInvalidoException();
        }
        
        List<Presentacion> listaPresentaciones = presentaciones.get(dia);

        if (listaPresentaciones.isEmpty()) {
            throw new presentacionNoEncontradaException();
        } else {
            for (Presentacion presentacion : listaPresentaciones) {
                if (presentacion.getTitulo().equals(titulo)) {
                    return presentacion;
                }
            }
        }
        throw new presentacionNoEncontradaException();
    }

    public void anadirExpositor(String rut, Expositor newExpositor) {
        mapaExpositores.put(rut, newExpositor);
    }

    public static ArrayList<Asistente> asistenteMapValuesToArrayList(Map<String, Asistente> map) {
        return new ArrayList<>(map.values());
    }

    public static ArrayList<Expositor> expositorMapValuesToArrayList(Map<String, Expositor> map) {
        return new ArrayList<>(map.values());
    }
    
    public Object[][] showPresentacionesAtendidas(int presentacionesAtendidas) {
        ArrayList<Asistente> newArrayList = asistenteMapValuesToArrayList(mapaAsistentes);
        List<Object[]> resultList = new ArrayList<>();

        for (Asistente temp : newArrayList) {
            if (temp.getPresentaciones() == presentacionesAtendidas) {
                resultList.add(new Object[]{temp.getNombre(), temp.getRut()});
            }
        }

        if (resultList.isEmpty()) {
            return new Object[0][0];  
        }

        return resultList.toArray(new Object[resultList.size()][]);
    }

    public Object[][] showMinutosAtendidas(int minutosAtendidos) {
        
        ArrayList<Asistente> newArrayList = asistenteMapValuesToArrayList(mapaAsistentes);
        List<Object[]> resultList = new ArrayList<>();

        for (Asistente temp : newArrayList) {
            if (temp.getTiempoTotal() == minutosAtendidos) {
                resultList.add(new Object[]{temp.getNombre(), temp.getRut()});
            }
        }

        if (resultList.isEmpty()) {
            return new Object[0][0];
        }

        return resultList.toArray(new Object[resultList.size()][]);
    }

    public Object[][] showDuracionExposiciones(int duracionExposicion) {
        ArrayList<Expositor> newArrayList = expositorMapValuesToArrayList(mapaExpositores);
        List<Object[]> resultList = new ArrayList<>();

        for (Expositor temp : newArrayList) {
            if (temp.getDuracion() == duracionExposicion) {
                resultList.add(new Object[]{temp.getNombre(), temp.getRut()});
            }
        }

        if (resultList.isEmpty()) {
            return new Object[0][0];
        }

        return resultList.toArray(new Object[resultList.size()][]);
    }

    public Object[][] getPresentacionesData(String dia) {
    
        List<Presentacion> listaPresentaciones = presentaciones.get(dia);
    

        if (listaPresentaciones == null || listaPresentaciones.isEmpty()) {
            return new Object[0][0];
        }

        Object[][] data = new Object[listaPresentaciones.size()][4]; 

        for (int i = 0; i < listaPresentaciones.size(); i++) {
        
            Presentacion p = listaPresentaciones.get(i);
            data[i][0] = p.getTitulo();
            data[i][1] = p.getHoraInicio() + " - " + p.getHoraFin();
            data[i][2] = p.getSala();

            StringBuilder participantes = new StringBuilder();

            for (Persona persona : p.getAsistentes()) {
                if (persona instanceof Asistente) {
                    Asistente asistente = (Asistente) persona;
                    participantes.append(asistente.getNombre())
                             .append(" (Asistente)")
                             .append(", ");
                } else if (persona instanceof Expositor) {
                    Expositor expositor = (Expositor) persona;
                    participantes.append(expositor.getNombre())
                             .append(" (Expositor)")
                             .append(", ");
                }
            }

            if (participantes.length() > 0) {
                participantes.setLength(participantes.length() - 2);
            }

            data[i][3] = participantes.toString();
        }

        return data;
    }     
  
}