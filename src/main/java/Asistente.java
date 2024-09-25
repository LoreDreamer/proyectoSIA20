public class Asistente extends Persona{

    private int presentacionesAtendidas;
    private int tiempoTotalPresentaciones;

    public Asistente(String nombre, String rut, int presentacionesAtendidas, int tiempoTotalPresentaciones) {
        super(nombre, rut);
        this.presentacionesAtendidas = presentacionesAtendidas;
        this.tiempoTotalPresentaciones = tiempoTotalPresentaciones;
    }

    public void setPresentaciones(int presentacionesAtendidas) {
        this.presentacionesAtendidas = presentacionesAtendidas;
    }

    public void setTiempoTotal(int tiempoTotalPresentaciones) {
        this.tiempoTotalPresentaciones = tiempoTotalPresentaciones;
    }

    public int getPresentaciones() {
        return presentacionesAtendidas;
    }

    public int getTiempoTotal() {
        return tiempoTotalPresentaciones;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + getNombre() + "\n");
        System.out.println("Rut: " + getRut() + "\n");
        System.out.println("Rol: Asistente\n");
        System.out.println("Presentaciones atendidas: " + getPresentaciones() + "\n");
        System.out.println("Tiempo total de las presentaciones atendidas: " + getTiempoTotal() + "\n");
    }
}
