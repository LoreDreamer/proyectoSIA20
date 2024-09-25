public class Expositor extends Persona{

    private String duracionExposicion;
    private String temaExposicion;

    public Expositor(String nombre, String rut, String duracionExposicion, String temaExposicion) {
        super(nombre, rut);
        this.duracionExposicion = duracionExposicion;
        this.temaExposicion = temaExposicion;
    }

    public void setDuracion(String duracionExposicion) {
        this.duracionExposicion = duracionExposicion;
    }

    public void setTema(String temaExposicion) {
        this.temaExposicion = temaExposicion;
    }

    public String getDuracion() {
        return duracionExposicion;
    }

    public String getTema() {
        return temaExposicion;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + getNombre());
        System.out.println("Rut: " + getRut());
        System.out.println("Rol: Expositor");
        System.out.println("Duración de la exposición: " + getDuracion());
        System.out.println("Tema a presentar: " + getTema());
    }
}
