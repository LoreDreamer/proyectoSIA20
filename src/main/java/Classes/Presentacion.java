package Classes;
import java.util.*;

public class Presentacion {

    // Atributos de la clase Presentacion.
    private String titulo;
    private String horaInicio;
    private String horaFin;
    private String sala;
    private ArrayList<Persona> listaParticipante;

    // Constructor para inicializar una presentación con todos sus atributos.
    public Presentacion(String titulo, String horaInicio, String horaFin, String sala) {
        this.titulo = titulo;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.sala = sala;
        this.listaParticipante = new ArrayList<Persona>();
    }

    // Métodos getter para acceder a los atributos de la presentación.
    public String getTitulo() {
        return titulo;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public String getSala() {
        return sala;
    }

    // Métodos setter para modificar los atributos de la presentación.
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public void agregarPersona(String nombre, String rut, int duracionExposicion, String temaExposicion) throws rutInvalidoException {
                
        Persona tempPersona = new Expositor(nombre, rut, duracionExposicion, temaExposicion);
        if (tempPersona.verificarDatos(rut)) {
            throw new rutInvalidoException();
        }
        listaParticipante.add(tempPersona);
    }

    public void agregarPersona(String nombre, String rut, int presentacionesAtendidas, int tiempoTotalPresentaciones) {
        Persona tempPersona = new Asistente(nombre, rut, presentacionesAtendidas, tiempoTotalPresentaciones);
        listaParticipante.add(tempPersona);
    }

    public void agregarPersona(Asistente asistente) throws rutInvalidoException {
        
        if (asistente.verificarDatos(asistente.getRut())) {
            throw new rutInvalidoException();
        }
        listaParticipante.add(asistente);
    }

    public Persona buscarParticipantePorEspecificacion(String rut) throws rutInvalidoException {
        Persona revision = new Persona("placeholder", rut);
        if (revision.verificarDatos(rut)) {
            throw new rutInvalidoException();
        }
        for (Persona persona : listaParticipante) {
            if (persona.getRut().equalsIgnoreCase(rut) && (persona instanceof Asistente)) {
                return persona;
            }
        }
        return null;
    }

    public Persona buscarParticipantePorEspecificacion(String rut, String nombre) {
        for (Persona persona : listaParticipante) {
            if (persona.getRut().equalsIgnoreCase(rut) && persona.getNombre().equalsIgnoreCase(nombre) && (persona instanceof Asistente)) {
                return persona;
            }
        }
        return null;
    }

    // Método para modificar el nombre y RUT de un participante existente.
    public void modificarParticipante(Asistente participante, String nuevoNombre, String nuevoRut) {
        participante.setNombre(nuevoNombre);
        participante.setRut(nuevoRut);
    }

    // Método para eliminar un participante de la lista.
    public void eliminarParticipante(String rut) {
        
        Persona persona = null;
        
        try {
            persona = buscarParticipantePorEspecificacion(rut); 
        } catch (Exception e) {
            return; // siempre se que va a estar.
        }
       
        listaParticipante.remove(persona);
    }
    /* 
    public String calcularDuracion(String horaInicio, String horaFin) {
        String[] inicio = horaInicio.split(":");
        String[] fin = horaFin.split(":");

        int inicialHora = Integer.parseInt(inicio[0]);
        int inicialMinuto = Integer.parseInt(inicio[1]);

        int finHora = Integer.parseInt(fin[0]);
        int finMinuto = Integer.parseInt(fin[1]);

        int minutosInicio = (inicialHora * 60) + inicialMinuto;
        int minutosFinal = (finHora * 60) + finMinuto;

        if (minutosFinal < minutosInicio) {
            minutosFinal += 24 * 60;
        }

        int duracionMinutos = minutosFinal - minutosInicio;
        int duracionHoras = duracionMinutos / 60;
        int duracionRestanteMinutos = duracionMinutos % 60;

        return String.format("%02d:%02d", duracionHoras, duracionRestanteMinutos);
    }
    */

    public int calcularDuracionMinutos() {
        String[] inicio = horaInicio.split(":");
        String[] fin = horaFin.split(":");

        int inicialHora = Integer.parseInt(inicio[0]);
        int inicialMinuto = Integer.parseInt(inicio[1]);

        int finHora = Integer.parseInt(fin[0]);
        int finMinuto = Integer.parseInt(fin[1]);

        int minutosInicio = (inicialHora * 60) + inicialMinuto;
        int minutosFinal = (finHora * 60) + finMinuto;

        if (minutosFinal < minutosInicio) {
            minutosFinal += 24 * 60;
        }

        return minutosFinal - minutosInicio;
    }

    public Persona encontrarExpositor() {
        return listaParticipante.get(0);
    }

    public void actualizarAsistentes(Map<String, Asistente> mapaAsistentes) {
        for (Persona persona : listaParticipante) {
            Asistente temp = mapaAsistentes.get(persona.getRut());
            if (temp != null) {
                ((Asistente) persona).setPresentaciones(temp.getPresentaciones());
                ((Asistente) persona).setTiempoTotal(temp.getTiempoTotal());
            }
        }
    }

    // Método para mostrar la lista de participantes de la presentación.
    public void mostrarListaParticipantes() {
        System.out.println("\n======================");
        System.out.println("Lista de participantes");
        System.out.println("======================\n");

        for (Persona persona : listaParticipante) {
            persona.mostrarInformacion();
            System.out.println();
        }
    }

    // Método para mostrar toda la información de la presentación.
    public void mostrarInformacion() {
        System.out.println("\n==============================");
        System.out.println("Información de la presentación");
        System.out.println("==============================\n\n");

        System.out.println("Titulo: " + titulo);
        System.out.println("Hora de inicio: " + horaInicio);
        System.out.println("Hora de fin: " + horaFin);
        System.out.println("Sala: " + sala);

        mostrarListaParticipantes();
    }
    
    public ArrayList<Persona> getAsistentes() {
        return listaParticipante;
    }

    public boolean comprobacionHora(String horaTemp) {

    int largoHora = horaTemp.length();
    
    if (largoHora != 5 || horaTemp.charAt(2) != ':') {
        return true;
    }

    String[] timeParts = horaTemp.split(":");
    
    if (timeParts.length != 2) {
        return true;
    }

    try {
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);

        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
            return true;
        }
    } catch (NumberFormatException e) {
        return true;
    }

    return false;
}


    public void verificarHora() throws tiempoInvalidoException {
        if (comprobacionHora(horaInicio) || comprobacionHora(horaFin)) {
            throw new tiempoInvalidoException();
        }
    }
}
