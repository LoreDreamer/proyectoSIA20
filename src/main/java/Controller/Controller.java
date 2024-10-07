package Controller;

import java.awt.event.*;
import javax.swing.*;
import Classes.*;
import Windows.*;

public class Controller implements ActionListener {
    
    private mainMenu menuPrincipal;
    private VentanaAnadir agregarPresentacion;
    private VentanaMostrar mostrarPresentaciones;
    private VentanaRecalendarizar recalendarizarPresentacion;
    private VentanaAsistentes gestionarAsistentes;
    private VentanaFiltro filtrar;
    private Congreso congresoInternacional;
    
    public void iniciar() {
        
        congresoInternacional = new Congreso();
        congresoInternacional.inicializarPresentaciones();
        congresoInternacional.inicializarDatosPresentaciones();

        menuPrincipal = new mainMenu();
        agregarPresentacion = new VentanaAnadir();
        mostrarPresentaciones = new VentanaMostrar(congresoInternacional);
        recalendarizarPresentacion = new VentanaRecalendarizar();
        gestionarAsistentes = new VentanaAsistentes();
        filtrar = new VentanaFiltro(congresoInternacional);
                
        menuPrincipal.getAgregar().addActionListener(this);
        menuPrincipal.getMostrar().addActionListener(this);
        menuPrincipal.getRecalendarizar().addActionListener(this);
        menuPrincipal.getAsistentes().addActionListener(this);
        menuPrincipal.getFiltrar().addActionListener(this);
        
        agregarPresentacion.getAgregar().addActionListener(this);
        agregarPresentacion.getVolver().addActionListener(this);
        
        mostrarPresentaciones.getVolver().addActionListener(this);
        mostrarPresentaciones.getSearch().addActionListener(this);
        
        recalendarizarPresentacion.getVolver().addActionListener(this);
        recalendarizarPresentacion.getRecalendarizar().addActionListener(this);
        
        gestionarAsistentes.getVolver().addActionListener(this);
        gestionarAsistentes.getGestionar().addActionListener(this);
        
        filtrar.getVolver().addActionListener(this);
        filtrar.getSearch().addActionListener(this);

        menuPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuPrincipal.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource() == menuPrincipal.getAgregar()) {
            agregarPresentacion.setAlwaysOnTop(true);
            agregarPresentacion.setVisible(true);
            return;
        }
        
        if (ae.getSource() == menuPrincipal.getMostrar()) {
            mostrarPresentaciones.setAlwaysOnTop(true);
            mostrarPresentaciones.setVisible(true);
            return;
        }
                
        if (ae.getSource() == menuPrincipal.getRecalendarizar()) {
            recalendarizarPresentacion.setAlwaysOnTop(true);
            recalendarizarPresentacion.setVisible(true);
            return;
        }
        
        if (ae.getSource() == menuPrincipal.getAsistentes()) {
            gestionarAsistentes.setVisible(true);
            return;
        }
        
        if (ae.getSource() == menuPrincipal.getFiltrar()) {
            filtrar.setVisible(true);
            return;
        }
        
        if (ae.getSource() == agregarPresentacion.getAgregar()) {
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
                JOptionPane.showMessageDialog(agregarPresentacion, "Error: Tiempo inválido", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (datoInvalidosException e) {
                JOptionPane.showMessageDialog(agregarPresentacion, "Error: Rut inválido", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (diaInvalidoException e) {
                JOptionPane.showMessageDialog(agregarPresentacion, "Error: Día inválido", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(agregarPresentacion, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            agregarPresentacion.dispose();
            return;
        }

        if (ae.getSource() == mostrarPresentaciones.getSearch()) {
            try {
                mostrarPresentaciones.loadPresentacionesData(mostrarPresentaciones.getInput().getText());
            } catch (diaInvalidoException e) {
                JOptionPane.showMessageDialog(mostrarPresentaciones, "Error: Día inválido", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(mostrarPresentaciones, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }

        if (ae.getSource() == recalendarizarPresentacion.getRecalendarizar()) {
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
                JOptionPane.showMessageDialog(recalendarizarPresentacion, "Error: Error indeterminado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        
            recalendarizarPresentacion.dispose();
            return;
        }

        if (ae.getSource() == gestionarAsistentes.getGestionar()) {
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

                        if (presentacionBuscada.buscarParticipantePorEspecificacion(temp.getRut()) != null) {
                            JOptionPane.showMessageDialog(gestionarAsistentes, "Error: El asistente ya se encuentra en la presentación.", "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                        }

                        if (congresoInternacional.searchAsistente(temp.getRut()) != null) {
                            congresoInternacional.addingAsistenteToPresentacion(temp.getRut(), duracion);
                            presentacionBuscada.agregarPersona((Asistente) temp);
                        } else {
                            congresoInternacional.addingAsistenteToPresentacion(temp.getRut(), temp.getNombre(), duracion);
                            presentacionBuscada.agregarPersona((Asistente) temp);
                        }
                        break;

                    case "Modificar":
                        Persona asistente = presentacionBuscada.buscarParticipantePorEspecificacion(gestionarAsistentes.getRut().getText());
                        System.out.println("Check");

                        if (presentacionBuscada.buscarParticipantePorEspecificacion(gestionarAsistentes.getNuevoRut().getText()) != null) {
                            JOptionPane.showMessageDialog(gestionarAsistentes, "Error: El asistente ya se encuentra en la presentación.", "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        
                        Asistente a = congresoInternacional.searchAsistente(gestionarAsistentes.getNuevoRut().getText());
                        Asistente b = congresoInternacional.searchAsistente(asistente.getRut());
                        
                        if (!b.compararCon(gestionarAsistentes.getNuevoRut().getText(), gestionarAsistentes.getNuevoNombre().getText()) && a != null && b != null) {
                            congresoInternacional.removingAsistenteFromPresentacion(b.getRut(), duracion);
                            congresoInternacional.addingAsistenteToPresentacion(a.getRut(), duracion);

                            presentacionBuscada.modificarParticipante((Asistente) asistente, gestionarAsistentes.getNuevoNombre().getText(), gestionarAsistentes.getNuevoRut().getText());
                        } else if (!b.compararCon(gestionarAsistentes.getNuevoRut().getText()) && a == null){
                            asistente.setNombre(gestionarAsistentes.getNuevoNombre().getText());
                            asistente.setRut(gestionarAsistentes.getNuevoRut().getText());

                            congresoInternacional.removingAsistenteFromPresentacion(b.getRut(), duracion);
                            congresoInternacional.addingAsistenteToPresentacion(asistente.getRut(), asistente.getNombre(), duracion);
                        }
                        break;

                    case "Eliminar":
                        congresoInternacional.removingAsistenteFromPresentacion(gestionarAsistentes.getRut().getText(), duracion);
                        presentacionBuscada.eliminarParticipante(gestionarAsistentes.getRut().getText());
                        break;
                }
                
                congresoInternacional.actualizarAsistentes(presentacionBuscada);

            } catch (presentacionNoEncontradaException e) {
                JOptionPane.showMessageDialog(gestionarAsistentes, "Error: Presentación no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (diaInvalidoException e) {
                JOptionPane.showMessageDialog(gestionarAsistentes, "Error: Día inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (datoInvalidosException e) {
                JOptionPane.showMessageDialog(gestionarAsistentes, "Error: Rut inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (asistenteException e) {
                JOptionPane.showMessageDialog(gestionarAsistentes, "Error: El asistente no ha sido encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(gestionarAsistentes, "Error: Error indeterminado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            gestionarAsistentes.dispose();
            return;
        }

        if (ae.getSource() == filtrar.getSearch()) {
            try {
                filtrar.loadFilteredData(Integer.parseInt(filtrar.getInput().getText()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(filtrar, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }

        if (ae.getSource() == gestionarAsistentes.getVolver()) {
            gestionarAsistentes.dispose();
            return;
        }

        if (ae.getSource() == filtrar.getVolver()) {
            filtrar.dispose();
            return;
        }
        
        if (ae.getSource() == agregarPresentacion.getVolver()) {
            agregarPresentacion.dispose();
            return;
        }

        if (ae.getSource() == mostrarPresentaciones.getVolver()) {
            mostrarPresentaciones.dispose();
            return;
        }

        if (ae.getSource() == recalendarizarPresentacion.getVolver()) {
            recalendarizarPresentacion.dispose();
            return;
        }

        if (ae.getSource() == gestionarAsistentes.getVolver()) {
            gestionarAsistentes.dispose();
            return;
        }
    }
}




