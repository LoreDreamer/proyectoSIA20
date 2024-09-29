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

    public void verificarRut(String rut) throws rutInvalidoException {
        int length = rut.length();

        if (length > 10 || length < 10) {
            throw new rutInvalidoException();
        }

        return;
    }
    
     // Método para mostrar la información de la persona.
    public void mostrarInformacion() {
        // Mostrar información en la consola.
        System.out.println("Nombre : " + nombre);
        System.out.println("Rut : " + rut);
    }
}