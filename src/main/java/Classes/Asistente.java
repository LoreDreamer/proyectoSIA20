package Classes;

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
        System.out.println("Nombre: " + getNombre());
        System.out.println("Rut: " + getRut());
        System.out.println("Rol: Asistente");
        System.out.println("Presentaciones atendidas: " + getPresentaciones() + " presentacion(es).");
        System.out.println("Tiempo total de las presentaciones atendidas: " + getTiempoTotal()+ " minuto(s).");
    }
    
    public boolean verificarDatos(String rut, String name, int tiempoTotalPresentaciones) {
        
        String rutPattern = "\\d{7,8}[-–][0-9Kk]";
        String namePattern = "[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+";
    
        if (rut.trim().isBlank() || !rut.trim().matches(rutPattern)) {
            return true; 
        } else if (tiempoTotalPresentaciones < 0) {
            return true;
        } else if (name.trim().isBlank() || !name.trim().matches(namePattern)) {
            return true;  
        }

        return false;
    }
}
