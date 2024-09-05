import java.util.ArrayList;

public class Presentacion {

    private String titulo;
    private String horaInicio;
    private String horaFin;
    private String sala;
    private ArrayList<Persona> listaParticipante;

    public Presentacion(String titulo, String horaInicio, String horaFin, String sala, ArrayList<Persona> listaParticipante) {
        this.titulo = titulo;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.sala = sala;
        this.listaParticipante = listaParticipante;
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

    public ArrayList<Persona> getLista() {
        return listaParticipante;
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

    public void setLista(ArrayList<Persona> listaParticipante) {
        this.listaParticipante = listaParticipante;
    }

    public void addParticipant(String nombre, String rut) {
        Persona tempPersona = new Persona(nombre, rut);
        listaParticipante.add(tempPersona);
    }

    public void addParticipant(String nombre, String rut, boolean esExpositor) {
        Persona tempPersona = new Persona(nombre, rut, esExpositor);
        listaParticipante.add(tempPersona);
    }

    public Persona findParticipantByRut(String rut) {
        for (Persona persona : listaParticipante) {
            if (persona.getRut().equalsIgnoreCase(rut) && !persona.isEsExpositor()) {
                return persona;
            }
        }
        return null;
    }

    public void modifyParticipant(Persona participante, String nuevoNombre, String nuevoRut) {
        participante.setNombre(nuevoNombre);
        participante.setRut(nuevoRut);
    }

    public void modifyParticipant(Persona participante, boolean esExpositor) {
        participante.setEsExpositor(esExpositor);
    }
    

    public void removeParticipant(Persona newPersona) {
        listaParticipante.remove(newPersona);
    }

    public void mostrarListaParticipantes() {

        System.out.println("\n======================");
        System.out.println("Lista de participantes");
        System.out.println("======================\n");

        for (Persona persona : listaParticipante) {
            persona.mostrarInformacion();
        }
    }

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
}