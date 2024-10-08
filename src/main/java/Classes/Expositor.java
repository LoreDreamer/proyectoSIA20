package Classes;

public class Expositor extends Persona{

    private int duracionExposicion;
    private String temaExposicion;

    public Expositor(String nombre, String rut, int duracionExposicion, String temaExposicion) {
        super(nombre, rut);
        this.duracionExposicion = duracionExposicion;
        this.temaExposicion = temaExposicion;
    }

    public void setDuracion(int duracionExposicion) {
        this.duracionExposicion = duracionExposicion;
    }

    public void setTema(String temaExposicion) {
        this.temaExposicion = temaExposicion;
    }

    public int getDuracion() {
        return duracionExposicion;
    }

    public String getTema() {
        return temaExposicion;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + getNombre());
        System.out.println("Rut: " + getRut());
        System.out.println("Rol: Expositor");
        System.out.println("Duración de la exposición: " + getDuracion() + " minuto(s).");
        System.out.println("Tema a presentar: " + getTema());
    }
    
    public boolean verificarDatos(String rut, String name, int duracionExposicion) {
        
        String rutPattern = "\\d{7,8}[-–][0-9Kk]";
        String namePattern = "[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+";
    
        if (rut.trim().isBlank() || !rut.trim().matches(rutPattern)) {
            return true; 
        } else if (duracionExposicion < 0) {
            return true;
        } else if (name.trim().isBlank() || !name.trim().matches(namePattern)) {
            return true;  
        }

        return false;
    }
}
