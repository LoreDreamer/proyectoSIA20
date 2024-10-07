package Classes;

public class Persona {

    private String nombre;
    private String rut;

    public Persona(String nombre, String rut) {
        this.nombre = nombre;
        this.rut = rut;
    }

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

    public boolean compararCon(String rut) {
        return this.rut.equals(rut);
    }

    public boolean compararCon(String nombre, String rut) {
        return this.nombre.equalsIgnoreCase(nombre) && this.rut.equals(rut);
    }

    public boolean verificarDatos(String verificarRut) {
    
        String rutPattern = "\\d{7,8}[-–][0-9Kk]";
    
        if (verificarRut.trim().isBlank() || !verificarRut.trim().matches(rutPattern)) {
            return true; 
        }

        return false;
    }
    
     
    public void mostrarInformacion() {
        
        System.out.println("Nombre : " + nombre);
        System.out.println("Rut : " + rut);
        
    }
}
