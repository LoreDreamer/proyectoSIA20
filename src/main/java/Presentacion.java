import java.util.ArrayList;

public class Presentacion {

    // Atributos de la clase Presentacion.
    private String titulo;
    private String horaInicio;
    private String horaFin;
    private String sala;
    private ArrayList<Persona> listaParticipante;

     // Constructor para inicializar una presentación con todos sus atributos.
    public Presentacion(String titulo, String horaInicio, String horaFin, String sala, ArrayList<Persona> listaParticipante) {
        this.titulo = titulo;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.sala = sala;
        this.listaParticipante = listaParticipante;
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

    public ArrayList<Persona> getLista() {
        return listaParticipante;
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

    public void setLista(ArrayList<Persona> listaParticipante) {
        this.listaParticipante = listaParticipante;
    }

    // Método para agregar un participante a la lista sin especificar si es expositor.
    public void agregarParticipante(String nombre, String rut) {
        Persona tempPersona = new Persona(nombre, rut);
        listaParticipante.add(tempPersona);
    }

    // Método para agregar un participante a la lista especificando si es expositor.
    public void agregarParticipante(String nombre, String rut, boolean esExpositor) {
        Persona tempPersona = new Persona(nombre, rut, esExpositor);
        listaParticipante.add(tempPersona);
    }

    // Método para buscar un participante por su RUT, solo si no es expositor.
    public Persona buscarParticipantePorRut(String rut) {
        for (Persona persona : listaParticipante) {
            if (persona.getRut().equalsIgnoreCase(rut) && !persona.isEsExpositor()) {
                return persona;
            }
        }
        return null;
    }

    // Método para modificar el nombre y RUT de un participante existente.
    public void modificarParticipante(Persona participante, String nuevoNombre, String nuevoRut) {
        participante.setNombre(nuevoNombre);
        participante.setRut(nuevoRut);
    }

    // Método para modificar el estado de expositor de un participante existente.
    public void modificarParticipante(Persona participante, boolean esExpositor) {
        participante.setEsExpositor(esExpositor);
    }
    
    // Método para eliminar un participante de la lista.
    public void eliminarParticipante(Persona nuevaPersona) {
        listaParticipante.remove(nuevaPersona);
    }

    // Método para mostrar la lista de participantes de la presentación.
    public void mostrarListaParticipantes() {

        System.out.println("\n======================");
        System.out.println("Lista de participantes");
        System.out.println("======================\n");

        for (Persona persona : listaParticipante) {
            persona.mostrarInformacion();
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
}