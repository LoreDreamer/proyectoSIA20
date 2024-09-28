import java.util.*;

public class Congreso {

    private Map<String, Asistente> mapaAsistentes;
    // private Map<String, List<Presentacion>> presentaciones;

    public Asistente searchAsistente(String rut) {
        return mapaAsistentes.get(rut);
    }

    public void modifyAsistente(String rut, String nombre) {
        Asistente searching = searchAsistente(rut);
        searching.setRut(rut);
        searching.setNombre(nombre);
    }

    public void removingAsistenteFromPresentacion(String rut, int duracionRestar) {
        Asistente searching = searchAsistente(rut);
        searching.setTiempoTotal(searching.getTiempoTotal() - duracionRestar);
        searching.setPresentaciones(searching.getPresentaciones() - 1);
    }

    public void addingAsistenteToPresentacion(String rut, int duracionAnadir) {
        Asistente searching = searchAsistente(rut);
        searching.setTiempoTotal(searching.getTiempoTotal() + duracionAnadir);
        searching.setPresentaciones(searching.getPresentaciones() + 1);
    }


    
}
