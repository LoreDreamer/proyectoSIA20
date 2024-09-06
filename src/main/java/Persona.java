public class Persona {

    // Atributos de la clase Persona.
    private String nombre;
    private String rut;
    private boolean esExpositor;

    // Constructor para inicializar una persona con nombre, RUT y estado de expositor.
    public Persona(String nombre, String rut, boolean esExpositor) {
        this.nombre = nombre;
        this.rut = rut;
        this.esExpositor = esExpositor;
    }

     // Constructor sobrecargado para inicializar una persona solo con nombre y RUT, por defecto no es expositor.
    public Persona(String nombre, String rut) {
        this.nombre = nombre;
        this.rut = rut;
        esExpositor = false;
    }

    // Métodos getter y setter para acceder y modificar los atributos.
    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public boolean isEsExpositor() {
        return esExpositor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setEsExpositor(boolean esExpositor) {
        this.esExpositor = esExpositor;
    }

     // Método para comparar el RUT de la persona con un RUT dado.
    public boolean compararCon(String rut) {
        return this.rut.equals(rut);
    }

    // Método para comparar el nombre y RUT de la persona con los valores dados.
    public boolean compararCon(String nombre, String rut) {
        return this.nombre.equalsIgnoreCase(nombre) && this.rut.equals(rut);
    }
    
     // Método para mostrar la información de la persona.
    public void mostrarInformacion() {
        // Mostrar información en la consola.
        System.out.println("Nombre : " + nombre);
        System.out.println("Rut : " + rut);
        System.out.println(esExpositor ? "Expositor\n" : "Asistente\n"); // Indicar si la persona es expositor o asistente.
    }
}