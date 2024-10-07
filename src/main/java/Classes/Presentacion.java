package Classes;
import java.util.*;

public class Presentacion {

    private String titulo;
    private String horaInicio;
    private String horaFin;
    private String sala;
    private ArrayList<Persona> listaParticipante;

    public Presentacion(String titulo, String horaInicio, String horaFin, String sala) {
        this.titulo = titulo;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.sala = sala;
        this.listaParticipante = new ArrayList<Persona>();
    }

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

    public void agregarPersona(String nombre, String rut, int duracionExposicion, String temaExposicion) throws datoInvalidosException {
                
        Expositor tempPersona = new Expositor(nombre, rut, duracionExposicion, temaExposicion);
        if (tempPersona.verificarDatos(rut, nombre, duracionExposicion)) {
            throw new datoInvalidosException();
        }
        listaParticipante.add(tempPersona);
    }

    public void agregarPersona(String nombre, String rut, int presentacionesAtendidas, int tiempoTotalPresentaciones) {
        Persona tempPersona = new Asistente(nombre, rut, presentacionesAtendidas, tiempoTotalPresentaciones);
        listaParticipante.add(tempPersona);
    }

    public void agregarPersona(Asistente asistente) throws datoInvalidosException {
        
        if (asistente.verificarDatos(asistente.getRut())) {
            throw new datoInvalidosException();
        }
        listaParticipante.add(asistente);
    }

    public Persona buscarParticipantePorEspecificacion(String rut) throws datoInvalidosException {
        Persona revision = new Persona("placeholder", rut);
        if (revision.verificarDatos(rut)) {
            throw new datoInvalidosException();
        }
        for (Persona persona : listaParticipante) {
            if (persona.compararCon(rut) && (persona instanceof Asistente)) {
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

    public void modificarParticipante(Asistente participante, String nuevoNombre, String nuevoRut) {
        participante.setNombre(nuevoNombre);
        participante.setRut(nuevoRut);
    }

    public void eliminarParticipante(String rut) {
        
        Persona persona = null;
        
        try {
            persona = buscarParticipantePorEspecificacion(rut); 
        } catch (Exception e) {
            return; // siempre se que va a estar.
        }
       
        listaParticipante.remove(persona);
    }
 
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
