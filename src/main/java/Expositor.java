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
        System.out.println("Nombre: " + getNombre() + "\n");
        System.out.println("Rut: " + getRut() + "\n");
        System.out.println("Rol: Expositor\n");
        System.out.println("Duración de la exposición: " + getDuracion() + "\n");
        System.out.println("Tema a presentar: " + getTema() + "\n");
    }
}
