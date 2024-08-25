public class Persona {

  private String nombre;
  private int rut;
  private boolean esExpositor;

  public Persona(String nombre, int rut, boolean esExpositor) {
    this.nombre = nombre;
    this.rut = rut;
    this.esExpositor = esExpositor;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getRut() {
    return rut;
  }

  public void setRut(int rut) {
    this.rut = rut;
  }

  public boolean isEsExpositor() {
    return esExpositor;
  }

  public void setEsExpositor(boolean esExpositor) {
    this.esExpositor = esExpositor;
  }

  public void mostrarInformacion() {
    System.out.println("Nombre : " + nombre);
    System.out.println("Rut : " + rut);
    System.out.println("Expositor : " + esExpositor);
  }
  
}