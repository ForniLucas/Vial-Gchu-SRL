package VistaUsuario;

import java.awt.BorderLayout;

import Enumeraciones.EstadoProyecto;
import Enumeraciones.TipoDeProyecto;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladoras.ControladorProyecto;
import Domain.Proyecto;
import Domain.TipoProyecto;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ModificarProyectoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JOptionPane optionPane = new JOptionPane();
	private JTextField legajoTxt = new JTextField();
	private JTextField fechaDeInicioTxt = new JTextField();
	private JTextField fechaFinTxt = new JTextField();
	private JComboBox<EstadoProyecto> estadoBox= new JComboBox<EstadoProyecto>(EstadoProyecto.values());
	private JTextField nombreTxt = new JTextField();
	private JTextField descripcionTxt = new JTextField();
	private JTextField actividadesTxt = new JTextField();
	private JTextField insumosTxt = new JTextField();
	
	private String id = new String();
	private String proyid = new String();
	private JComboBox<TipoDeProyecto> tipoBox = new JComboBox<TipoDeProyecto>(TipoDeProyecto.values()){
	    @Override
	    public String toString() {
	        String selectedItem = (String) getSelectedItem().toString();
	        return selectedItem.replace('_', ' ');
	    }
	};
	Proyecto proyecto = new Proyecto();
	ControladorProyecto controladorProyecto = new ControladorProyecto();

	/**
	 * Launch the application.
	 */
	
	/*
	public static void main(String[] args) {
		try {
			ModificarProyectoDialog dialog = new ModificarProyectoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} */

	/**
	 * Create the dialog.
	 */
	public ModificarProyectoDialog(ProyectoDialog dialog, String id) {
		super(dialog, "BajaProyectoDialog",true);
		this.id = id;
		
		legajoTxt.setText(id);
		
		setBounds(50, 50, 550, 550);
		this.setResizable(false);
		this.setTitle("MODIFICAR PROYECTOS");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Ingrese el Legajo:");
			lblNewLabel.setBounds(69, 18, 120, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Fecha de Inicio");
			lblNewLabel_1.setBounds(140, 54, 120, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Fecha Estimada de Fin");
			lblNewLabel_2.setBounds(105, 100, 170, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Estado");
			lblNewLabel_3.setBounds(169, 155, 120, 13);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Nombre de Proyecto");
			lblNewLabel_4.setBounds(118, 210, 120, 13);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Tipo De Proyecto");
			lblNewLabel_5.setBounds(129, 265, 120, 13);
			contentPanel.add(lblNewLabel_5);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Descripción");
			lblNewLabel_6.setBounds(157, 430, 120, 13);
			contentPanel.add(lblNewLabel_6);
		}
		
		{
			
			legajoTxt.setBounds(199, 15, 151, 19);
			contentPanel.add(legajoTxt);
			legajoTxt.setColumns(255);
		}
		{
			
			fechaDeInicioTxt.setText("dd/mm/aaaa");
			fechaDeInicioTxt.setBounds(254, 51, 96, 19);
			contentPanel.add(fechaDeInicioTxt);
			fechaDeInicioTxt.setColumns(255);
		}
		{
		//105, 100, 109, 13
			fechaFinTxt.setText("dd/mm/aaaa");
			fechaFinTxt.setBounds(254, 97, 96, 19);
			contentPanel.add(fechaFinTxt);
			fechaFinTxt.setColumns(255);
		}
		{
			//169, 155, 45, 13
			estadoBox.setBounds(254, 152, 96, 19);
		    contentPanel.add(estadoBox);
		}
		{
			
			nombreTxt.setBounds(254, 207, 247, 19);
			contentPanel.add(nombreTxt);
			nombreTxt.setColumns(255);
		}
		{
			
			descripcionTxt.setBounds(254, 414, 247, 45);
			contentPanel.add(descripcionTxt);
			descripcionTxt.setColumns(255);
		}
		//129, 265, 85, 13
		{
			
			tipoBox.setBounds(254, 262, 130, 21);
			contentPanel.add(tipoBox);
			
		}
		{
			actividadesTxt.setBounds(254, 321, 196, 19);
			contentPanel.add(actividadesTxt);
			actividadesTxt.setColumns(225);
		}
		{
			insumosTxt.setBounds(254, 372, 196, 19);
			contentPanel.add(insumosTxt);
			insumosTxt.setColumns(225);
		}
		{
			JButton buscarBtn = new JButton("Buscar");
			buscarBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try { 
						String codigo = legajoTxt.getText(); 
						int id = Integer.parseInt(codigo);
						proyecto = controladorProyecto.buscarID(id);
						nombreTxt.setText(proyecto.getNombre());
						fechaDeInicioTxt.setText(convertirFecha(proyecto.getFechaInicio()).toString());
						fechaFinTxt.setText(convertirFecha(proyecto.getFechaEstFin()).toString());
						estadoBox.setSelectedItem(proyecto.getEstado());
						TipoProyecto tipo = (TipoProyecto)(proyecto.getTipoProyecto());
						tipoBox.setSelectedItem(tipo.getTipo());
						actividadesTxt.setText(tipo.getActividades());
						insumosTxt.setText(tipo.getInsumos());
						descripcionTxt.setText(tipo.getDescripcion());
					} catch (Exception e1) {
						optionPane.showMessageDialog(null, "Error al buscar: Valor Ingresado no valido o inexistente");
					}
					
				}
			});
			buscarBtn.setBounds(376, 14, 85, 21);
			contentPanel.add(buscarBtn);
		}
		{
			JLabel actLbl = new JLabel("Actividades");
			actLbl.setBounds(157, 320, 120, 13);
			contentPanel.add(actLbl);
		}
		{
			JLabel insumosLbl = new JLabel("Insumos");
			insumosLbl.setBounds(169, 375, 120, 13);
			contentPanel.add(insumosLbl);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton guardarBtn = new JButton("Guardar");
				guardarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
						    String fechaInicioString = fechaDeInicioTxt.getText();
						    String fechaFinString = fechaFinTxt.getText();
						    String nombre = nombreTxt.getText();
						    String desc = descripcionTxt.getText();
						    String actividades = actividadesTxt.getText();
						    String insumos = insumosTxt.getText();
						    EstadoProyecto estado = (EstadoProyecto) estadoBox.getSelectedItem();
						    TipoDeProyecto tipo = (TipoDeProyecto) tipoBox.getSelectedItem();

						    if (fechaInicioString.trim().isEmpty() || fechaFinString.trim().isEmpty() || nombre.trim().isEmpty() ||
						            desc.trim().isEmpty() || actividades.trim().isEmpty() || insumos.trim().isEmpty()) {
						        optionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
						        return;
						    }

						    LocalDate fechaInicio = null;
						    LocalDate fechaFin = null;

						    try {
						        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						        fechaInicio = LocalDate.parse(fechaInicioString, formatter);
						        fechaFin = LocalDate.parse(fechaFinString, formatter);

						        TipoProyecto tipoProyecto = new TipoProyecto(tipo, desc, actividades, insumos);

						        proyecto.setFechaInicio(fechaInicio);
						        proyecto.setFechaEstFin(fechaFin);
						        proyecto.setNombre(nombre);
						        proyecto.setEstado(estado);
						        proyecto.asignarTipoProyecto(tipoProyecto);

						        controladorProyecto.modificar(proyecto);
						        optionPane.showMessageDialog(null, "Datos modificados con éxito");
						        setVisible(false);
								ProyectoDialog proyectoDialog = new ProyectoDialog();
								proyectoDialog.setVisible(true);
						    } catch (DateTimeParseException e1) {
						        optionPane.showMessageDialog(null, "La fecha debe tener formato dd/MM/yyyy.");
						    } catch (Exception e2) {
						        optionPane.showMessageDialog(null, "Error al Realizar la operación: " + e2.getMessage());
						    }
						} catch (Exception e3) {
						    optionPane.showMessageDialog(null, "Ocurrió un error al procesar los datos: " + e3.getMessage());
						}

						
					}
				});
				
				JButton gestionMaqBtn = new JButton("Gestionar Maquinaria");
				gestionMaqBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if (proyecto.getNombre() == null) {
								optionPane.showMessageDialog(null, "Debe buscar un proyecto primero");
							}
							else {
								proyid = legajoTxt.getText();
								HistorialUtiliza historialUtiliza = new HistorialUtiliza(ModificarProyectoDialog.this, proyid);
								historialUtiliza.setVisible(true);
							}
							
						} catch (Exception ex) {
						    optionPane.showMessageDialog(null, "Error:" + ex.getMessage());
						}
						
					}
				});
				buttonPane.add(gestionMaqBtn);
				
				JButton gestionEmplBtn = new JButton("Gestionar Empleados");
				gestionEmplBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if (proyecto.getNombre() == null) {
								optionPane.showMessageDialog(null, "Debe buscar un proyecto primero");
							}
							else {
								proyid = legajoTxt.getText();
								HistorialTrabaja historialTrabaja = new HistorialTrabaja(ModificarProyectoDialog.this, proyid);
								historialTrabaja.setVisible(true);
								}
					
						} catch (Exception ex) {
								optionPane.showMessageDialog(null, "Error:" + ex.getMessage());
						}
					}
				});
				buttonPane.add(gestionEmplBtn);
				guardarBtn.setActionCommand("Guardar");
				buttonPane.add(guardarBtn);
				getRootPane().setDefaultButton(guardarBtn);
			}
			{
				JButton cancelarBtn = new JButton("Cancelar");
				cancelarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						ProyectoDialog proyectoDialog = new ProyectoDialog();
						proyectoDialog.setVisible(true);
					}
				});
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}
	
	public boolean validarDatos(String fechaInicioString, String fechaFinString, String nombre, String desc, String actividades, String insumos) {
	    boolean resultado = true;
	    
	    // Validar fecha de inicio
	    try {
	        LocalDate.parse(fechaInicioString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    } catch (DateTimeParseException e) {
	        JOptionPane.showMessageDialog(null, "Ingrese una fecha de inicio válida (formato: dd/MM/yyyy).");
	        resultado = false;
	    }
	    
	    // Validar fecha de fin
	    try {
	        LocalDate.parse(fechaFinString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    } catch (DateTimeParseException e) {
	        JOptionPane.showMessageDialog(null, "Ingrese una fecha de fin válida (formato: dd/MM/yyyy).");
	        resultado = false;
	    }
	    
	    // Validar nombre
	    if (nombre.trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Ingrese un nombre para el proyecto.");
	        resultado = false;
	    }
	    
	    // Validar descripción
	    if (!desc.matches("[a-zA-Z0-9 ]{1,50}")) {
	        JOptionPane.showMessageDialog(null, "Ingrese una descripción válida (solo letras, números y espacios, hasta 50 caracteres).");
	        resultado = false;
	    }
	    
	    // Validar actividades
	    if (!actividades.matches("[a-zA-Z0-9 ]{1,50}")) {
	        JOptionPane.showMessageDialog(null, "Ingrese actividades válidas (solo letras, números y espacios, hasta 50 caracteres).");
	        resultado = false;
	    }
	    
	    // Validar insumos
	    if (!insumos.matches("[a-zA-Z0-9 ]{1,50}")) {
	        JOptionPane.showMessageDialog(null, "Ingrese insumos válidos (solo letras, números y espacios, hasta 50 caracteres).");
	        resultado = false;
	    }
	    
	    return resultado;
	}

	public String convertirFecha(LocalDate fecha) {
	    // Crear el formateador para el formato de salida
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	    // Formatear la fecha en el formato deseado "dd/MM/yyyy"
	    String fechaFormateada = fecha.format(formatter);

	    // Devolver la fecha formateada
	    return fechaFormateada;
	}
}
