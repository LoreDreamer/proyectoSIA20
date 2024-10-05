package Classes;

public class Persona {

    // Atributos de la clase Persona.
    private String nombre;
    private String rut;

    // Constructor para inicializar una persona con nombre, RUT y estado de expositor.
    public Persona(String nombre, String rut) {
        this.nombre = nombre;
        this.rut = rut;
    }

    // Métodos getter y setter para acceder y modificar los atributos.
    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

     // Método para comparar el RUT de la persona con un RUT dado.
    public boolean compararCon(String rut) {
        return this.rut.equals(rut);
    }

    // Método para comparar el nombre y RUT de la persona con los valores dados.
    public boolean compararCon(String nombre, String rut) {
        return this.nombre.equalsIgnoreCase(nombre) && this.rut.equals(rut);
    }

    public boolean verificarDatos(String verificarRut) {
    // Regular expression to match a valid RUT
    // This will match numbers followed by a dash and a single digit or 'K'
    String rutPattern = "\\d{7,8}-[0-9Kk]";
    
    // Check if the RUT is blank or doesn't match the pattern
    if (verificarRut.isBlank() || !verificarRut.matches(rutPattern)) {
        return true; // Return true if invalid
    }

    return false; // Return false if valid
}
    
     
    public void mostrarInformacion() {
        System.out.println("Nombre : " + nombre);
        System.out.println("Rut : " + rut);
    }
}
