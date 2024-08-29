public class Persona {

    private String nombre;
    private String rut;
    private boolean esExpositor;

    public Persona(String nombre, String rut, boolean esExpositor) {
        this.nombre = nombre;
        this.rut = rut;
        this.esExpositor = esExpositor;
    }

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

    // Método original
    public void mostrarInformacion() {
        // JOptionPane.showMessageDialog(null, "Nombre: "+nombre);
        // int cosa = Integer.parseInt(JOptionPane.showInputDialog("test"));

        System.out.println("Nombre : " + nombre);
        System.out.println("Rut : " + rut);
        System.out.println(esExpositor ? "Expositor\n" : "Asistente\n");
    }

    // Método sobrecargado que incluye una descripción adicional
    public void mostrarInformacion(String descripcion) {
        System.out.println(descripcion);
        mostrarInformacion(); // Llama al método original
    }
}
