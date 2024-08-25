
public class Presentacion{
  private String titulo;
  private String participante;
  private String horaInicio;
  private String horaFin;
  private String sala;
  private Persona[] listaParticipante;

  public Presentacion(String titulo, String participante, String horaInicio, String horaFin, String sala, Persona[] listaParticipante){
  this.titulo = titulo;
  this.participante = participante;
  this.horaInicio = horaInicio; 
  this.horaFin = horaFin;
  this.sala = sala;
  this.listaParticipante = listaParticipante;
  }

  public String getTitulo(){
    return titulo;
  }
  public void setTitulo(String titulo){
    this.titulo = titulo;
  }
  public String getParticipante(){
    return participante;
  }
  public void setParticipante(String participante){
    this.participante = participante;
  }
  public String getHoraInicio(){
    return horaInicio;
  }
  public void setHoraInicio(String horaInicio){
    this.horaInicio = horaInicio;
  }
  public String getHoraFin(){
    return horaFin;
  }
  public void setHoraFin(String horaFin){
    this.horaFin = horaFin;
  }
  public String getSala(){
    return sala;
  }
  public void setSala(String sala){
    this.sala = sala;
  }

  public void setLista(Persona[] listaParticipante) {

    this.listaParticipante = listaParticipante;
    
  }

  public Persona[] getLista() {
    return listaParticipante;
  }

  public void mostrarInformacion(){
    System.out.println("Titulo: " + titulo);
    System.out.println("Autor: " + participante);
    System.out.println("Hora de inicio: " + horaInicio);
    System.out.println("Hora de fin: " + horaFin);
    System.out.println("Sala: " + sala);
  }
  
}