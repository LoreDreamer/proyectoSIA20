package Controller;

import java.awt.event.*;
import javax.swing.*;
import Classes.*;
import Windows.*;

public class Controller implements ActionListener{
    
    private mainMenu menuPrincipal;
    private VentanaAnadir agregarPresentacion;
    private VentanaMostrar mostrarPresentaciones;
    private VentanaRecalendarizar recalendarizarPresentacion;
    private VentanaAsistentes gestionarAsistentes;
    private Congreso congresoInternacional;
    
    public void iniciar() {
        menuPrincipal = new mainMenu();
        congresoInternacional = new Congreso();
        
        congresoInternacional.inicializarPresentaciones();
        congresoInternacional.inicializarDatosPresentaciones();
        
        menuPrincipal = new mainMenu();
        menuPrincipal.getAgregar().addActionListener(this);
        menuPrincipal.getMostrar().addActionListener(this);
        menuPrincipal.getRecalendarizar().addActionListener(this);
        menuPrincipal.getAsistentes().addActionListener(this);
        
        menuPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuPrincipal.setVisible(true);
       
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        // Handle Agregar Presentacion
        if (ae.getSource() == menuPrincipal.getAgregar()) {
            agregarPresentacion = new VentanaAnadir();
            agregarPresentacion.getAgregar().addActionListener(this);
            agregarPresentacion.getVolver().addActionListener(this);
            agregarPresentacion.setAlwaysOnTop(true);
            agregarPresentacion.setVisible(true);
            return;
        }
        
        // Handle Mostrar Presentaciones
        if (ae.getSource() == menuPrincipal.getMostrar()) {
            if (mostrarPresentaciones == null) {  // Initialize only once
                mostrarPresentaciones = new VentanaMostrar(congresoInternacional);
                mostrarPresentaciones.getVolver().addActionListener(this);
                mostrarPresentaciones.getSearch().addActionListener(this);
                mostrarPresentaciones.setAlwaysOnTop(true);
            }
            mostrarPresentaciones.setVisible(true);
            return;
        }

        // Handle Recalendarizar Presentaciones
        if (ae.getSource() == menuPrincipal.getRecalendarizar()) {
            if (recalendarizarPresentacion == null) {  // Initialize only once
                recalendarizarPresentacion = new VentanaRecalendarizar();
                recalendarizarPresentacion.getVolver().addActionListener(this);
                recalendarizarPresentacion.getRecalendarizar().addActionListener(this);
                recalendarizarPresentacion.setAlwaysOnTop(true);
            }
            recalendarizarPresentacion.setVisible(true);
            return;
        }
    
        // Handle Agregar actions in VentanaAnadir
        if (agregarPresentacion != null && ae.getSource() == agregarPresentacion.getAgregar()) {
            try {
                Presentacion tempPresentacion = new Presentacion(
                    agregarPresentacion.getInputB().getText(),
                    agregarPresentacion.getInputC().getText(),
                    agregarPresentacion.getInputD().getText(),
                    agregarPresentacion.getInputE().getText()
                );
            
                tempPresentacion.verificarHora();
                
                tempPresentacion.agregarPersona(
                    agregarPresentacion.getNombre().getText(),
                    agregarPresentacion.getRut().getText(),
                    tempPresentacion.calcularDuracionMinutos(),
                    agregarPresentacion.getInputB().getText()
                );
                
                Persona expositor = new Expositor(
                    agregarPresentacion.getNombre().getText(),
                    agregarPresentacion.getRut().getText(),
                    tempPresentacion.calcularDuracionMinutos(),
                    agregarPresentacion.getInputB().getText()
                );
            
                congresoInternacional.anadirPresentacion(tempPresentacion, agregarPresentacion.getInputA().getText());
                congresoInternacional.anadirExpositor(expositor.getRut(), (Expositor) expositor);
                
            } catch (tiempoInvalidoException e) {
                // Show error popup
                JOptionPane.showMessageDialog(agregarPresentacion, "Error: Tiempo inválido", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (rutInvalidoException e) {
                // Show error popup
                JOptionPane.showMessageDialog(agregarPresentacion, "Error: Rut inválido", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (diaInvalidoException e) {
                // Show error popup
                JOptionPane.showMessageDialog(agregarPresentacion, "Error: Día inválido", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                // Catch-all for any other exceptions
                JOptionPane.showMessageDialog(agregarPresentacion, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            agregarPresentacion.dispose();
            return;
        }

        // Handle VentanaMostrar Search button actions
        if (mostrarPresentaciones != null && ae.getSource() == mostrarPresentaciones.getSearch()) {
            try {
                mostrarPresentaciones.loadPresentacionesData(mostrarPresentaciones.getInput().getText());
            } catch (diaInvalidoException e) {
                JOptionPane.showMessageDialog(mostrarPresentaciones, "Error: Día inválido", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(mostrarPresentaciones, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }

        // Handle Volver buttons for different windows
        if (agregarPresentacion != null && ae.getSource() == agregarPresentacion.getVolver()) {
            agregarPresentacion.dispose();
            return;
        }

        if (mostrarPresentaciones != null && ae.getSource() == mostrarPresentaciones.getVolver()) {
            mostrarPresentaciones.dispose();
            return;
        }
    
        if (recalendarizarPresentacion != null && ae.getSource() == recalendarizarPresentacion.getVolver()) {
            recalendarizarPresentacion.dispose();
            return;
        }

        // Handle Recalendarizar Presentacion actions
        if (recalendarizarPresentacion != null && ae.getSource() == recalendarizarPresentacion.getRecalendarizar()) {
            try {
                Presentacion oldPresentacion = congresoInternacional.encontrarPresentacion(
                    recalendarizarPresentacion.getOldDia().getText(),
                    recalendarizarPresentacion.getTitlePresentacion().getText()
                );
                congresoInternacional.removerPresentacion(oldPresentacion, recalendarizarPresentacion.getOldDia().getText());

                oldPresentacion.setHoraInicio(recalendarizarPresentacion.getHoraInicio().getText());
                oldPresentacion.setHoraFin(recalendarizarPresentacion.getHoraFin().getText());
            
                Expositor temp = (Expositor) oldPresentacion.encontrarExpositor();
                temp.setDuracion(oldPresentacion.calcularDuracionMinutos());

                congresoInternacional.anadirPresentacion(oldPresentacion, recalendarizarPresentacion.getNuevoDia().getText());
                congresoInternacional.actualizarAsistentes(oldPresentacion);
            
            
            } catch (presentacionNoEncontradaException e) {
                JOptionPane.showMessageDialog(recalendarizarPresentacion, "Error: No se ha encontrado la presentación.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (diaInvalidoException e) {
                JOptionPane.showMessageDialog(recalendarizarPresentacion, "Error: Día inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                //e.printStackTrace();
                JOptionPane.showMessageDialog(recalendarizarPresentacion, "Error: Error indeterminado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        
            recalendarizarPresentacion.dispose();
            return;
        }
        
        if (ae.getSource() == menuPrincipal.getAsistentes()) {
            if (gestionarAsistentes == null) {
                gestionarAsistentes = new VentanaAsistentes();
                
                gestionarAsistentes.getVolver().addActionListener(this);
                gestionarAsistentes.getGestionar().addActionListener(this);
                
                gestionarAsistentes.setAlwaysOnTop(true);
            }
            
            gestionarAsistentes.setVisible(true);
            return;
        }
        
        if (ae.getSource() == gestionarAsistentes.getVolver()) {
            gestionarAsistentes.dispose();
            return;
        }
        
        if (ae.getSource() == gestionarAsistentes.getGestionar() && gestionarAsistentes != null) {
            
            try {
                
                String selectedOption = gestionarAsistentes.getEscoger().getSelectedItem().toString();
                
                Presentacion presentacionBuscada = congresoInternacional.encontrarPresentacion(
                    gestionarAsistentes.getDia().getText(),
                    gestionarAsistentes.getTitulo().getText()
                );
                
                int duracion = presentacionBuscada.calcularDuracionMinutos();
                
                switch (selectedOption) {
                    
                    case "Añadir":
                        
                        Persona temp = new Asistente(
                            gestionarAsistentes.getNombre().getText(),
                            gestionarAsistentes.getRut().getText(),
                            1,
                            duracion    
                         );
                        
                        //System.out.println(temp.verificarDatos("21595598-2"));

                        if (presentacionBuscada.buscarParticipantePorEspecificacion(temp.getRut()) != null) {
                            JOptionPane.showMessageDialog(gestionarAsistentes, "Error: El asistente ya se encuentra en la presentación.", "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        
                        if (congresoInternacional.searchAsistente(temp.getRut()) != null) {
                            congresoInternacional.addingAsistenteToPresentacion(temp.getRut(), duracion);
                            presentacionBuscada.agregarPersona((Asistente) temp);
                        } else {
                            congresoInternacional.addingAsistenteToPresentacion(temp.getNombre(), temp.getRut(), duracion);
                            presentacionBuscada.agregarPersona((Asistente) temp);
                        }
                        
                        break;
                    
                    case "Modificar":
                        
                       Persona asistente = presentacionBuscada.buscarParticipantePorEspecificacion(gestionarAsistentes.getRut().getText());
 
                       if (presentacionBuscada.buscarParticipantePorEspecificacion(gestionarAsistentes.getNuevoNombre().getText()) != null) {
                            JOptionPane.showMessageDialog(gestionarAsistentes, "Error: El asistente ya se encuentra en la presentación.", "Error", JOptionPane.ERROR_MESSAGE);
                            
                            System.out.println(gestionarAsistentes.getNuevoRut());
                            System.out.println(gestionarAsistentes.getRut().getText());
                            break;
                        }
                       
                       Asistente a = congresoInternacional.searchAsistente(gestionarAsistentes.getRut().getText());
                       Asistente b = congresoInternacional.searchAsistente(asistente.getRut());
                       
                       if (!a.compararCon(gestionarAsistentes.getNuevoRut().getText(), gestionarAsistentes.getNuevoRut().getText()) && a != null && b != null) {

                            congresoInternacional.removingAsistenteFromPresentacion(a.getRut(), duracion);
                            congresoInternacional.addingAsistenteToPresentacion(b.getRut(), duracion);
                            
                            asistente.setNombre(gestionarAsistentes.getNuevoNombre().getText());
                            asistente.setRut(gestionarAsistentes.getNuevoRut().getText());

                        } else if (!a.compararCon(gestionarAsistentes.getNuevoRut().getText(), gestionarAsistentes.getNuevoRut().getText()) && a != null && b == null) {
                            
                            asistente.setNombre(gestionarAsistentes.getNuevoNombre().getText());
                            asistente.setRut(gestionarAsistentes.getNuevoRut().getText());
                            
                            congresoInternacional.removingAsistenteFromPresentacion(a.getRut(), duracion);
                            congresoInternacional.addingAsistenteToPresentacion(asistente.getRut(), asistente.getNombre(), duracion);
     
                        }
                       
                       break;
                       
                    case "Eliminar":
                        
                        congresoInternacional.removingAsistenteFromPresentacion(gestionarAsistentes.getRut().getText(), duracion);
                        presentacionBuscada.eliminarParticipante(gestionarAsistentes.getRut().getText());
                        
                        break;
                                             
                }
            } catch (presentacionNoEncontradaException e) {
                JOptionPane.showMessageDialog(gestionarAsistentes, "Error: Presentación no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (diaInvalidoException e) {
                JOptionPane.showMessageDialog(gestionarAsistentes, "Error: Día inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (rutInvalidoException e) {
                JOptionPane.showMessageDialog(gestionarAsistentes, "Error: Rut inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (asistenteException e) {
                JOptionPane.showMessageDialog(gestionarAsistentes, "Error: El asistente no ha sido encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(gestionarAsistentes, "Error: Error indeterminado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            gestionarAsistentes.dispose();
            return;
        }
    }
}
    

