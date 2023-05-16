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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;

public class AltaProyectoDialog extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	JOptionPane optionPane = new JOptionPane();
	private JTextField fechaInicioTxt;
	private JTextField fechaEstimadaFinTxt;
	private JComboBox<EstadoProyecto> estadoBox= new JComboBox<EstadoProyecto>(EstadoProyecto.values());
	private JTextField nombreTxt;
	private JTextField descripcionTxt;
	private JTextField actividadesTxt;
	private JTextField insumosTxt;
	private ControladorProyecto controlador = new ControladorProyecto();
	private JTextField fechaEstimadaFin;
	private JTextField fechaFinTxt;
	private JComboBox<TipoDeProyecto> comboBox = new JComboBox<TipoDeProyecto>(TipoDeProyecto.values()){
	
		@Override
	    public String toString() {
	        String selectedItem = (String) getSelectedItem().toString();
	        return selectedItem.replace('_', ' ');
	    }
	};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AltaProyectoDialog dialog = new AltaProyectoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AltaProyectoDialog() {
		
		fechaInicioTxt = new JTextField();
		fechaInicioTxt.setText("dd/mm/aaaa");
		fechaFinTxt = new JTextField();
		nombreTxt = new JTextField();
		descripcionTxt = new JTextField();
		actividadesTxt = new JTextField();
		insumosTxt = new JTextField();
		
		setBounds(50, 50, 500, 550);
		this.setResizable(false);
		this.setTitle("ALTA DE PROYECTOS");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Fecha de Inicio");
			lblNewLabel.setBounds(81, 34, 145, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Fecha Estimada de Fin");
			lblNewLabel_1.setBounds(52, 83, 174, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Estado");
			lblNewLabel_2.setBounds(117, 181, 95, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Nombre de Proyecto");
			lblNewLabel_3.setBounds(52, 230, 160, 13);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Tipo de Proyecto");
			lblNewLabel_4.setBounds(70, 279, 156, 13);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Descripción");
			lblNewLabel_5.setBounds(99, 426, 95, 13);
			contentPanel.add(lblNewLabel_5);
		}

		{
			//	81, 34, 145, 13
			fechaInicioTxt.setBounds(236, 31, 96, 19);
			contentPanel.add(fechaInicioTxt);
			fechaInicioTxt.setColumns(10);
		}
		{
		//52, 83, 174, 13
			fechaEstimadaFin = new JTextField();
			fechaEstimadaFin.setText("dd/mm/aaaa");
			fechaEstimadaFin.setBounds(236, 83, 96, 19);
			contentPanel.add(fechaEstimadaFin);
			fechaEstimadaFin.setColumns(10);
		}
		{
		//117, 181, 95, 13
			estadoBox.setBounds(236, 181, 96, 19);
			contentPanel.add(estadoBox);
		}
		{
			
			nombreTxt.setBounds(236, 227, 196, 19);
			contentPanel.add(nombreTxt);
			nombreTxt.setColumns(255);
		}
		{
			
			descripcionTxt.setBounds(236, 412, 223, 42);
			contentPanel.add(descripcionTxt);
			descripcionTxt.setColumns(255);
		}
		{
			actividadesTxt.setBounds(236, 325, 196, 19);
			contentPanel.add(actividadesTxt);
			actividadesTxt.setColumns(225);
		}
		{
			insumosTxt.setBounds(236, 374, 196, 19);
			contentPanel.add(insumosTxt);
			insumosTxt.setColumns(225);
		}
		
		JLabel actLbl = new JLabel("Actividades");
		actLbl.setBounds(99, 328, 127, 13);
		contentPanel.add(actLbl);
		{
			JLabel insLb = new JLabel("Insumos");
			insLb.setBounds(112, 377, 100, 13);
			contentPanel.add(insLb);
		}
		
		JLabel fechaFinLbl = new JLabel("Fecha de Fin");
		fechaFinLbl.setBounds(93, 132, 143, 13);
		contentPanel.add(fechaFinLbl);
		
		
		{
			fechaFinTxt = new JTextField();
			fechaFinTxt.setText("dd/mm/aaaa");
			fechaFinTxt.setBounds(236, 129, 96, 19);
			contentPanel.add(fechaFinTxt);
			fechaFinTxt.setColumns(10);
		}

		{
			//70, 279, 156, 13
			comboBox.setBounds(236, 279, 150, 21);
			contentPanel.add(comboBox);
		
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
						    String fechaInicioString = fechaInicioTxt.getText();
						    String fechaEstimadaFinString = fechaEstimadaFin.getText();
						    String fechaFinString = fechaEstimadaFinTxt.getText();
						    String nombre = nombreTxt.getText();
						    String desc = descripcionTxt.getText();
						    String actividades = actividadesTxt.getText();
						    String insumos = insumosTxt.getText();
						    EstadoProyecto estado = (EstadoProyecto) estadoBox.getSelectedItem();
						    TipoDeProyecto tipo = (TipoDeProyecto) comboBox.getSelectedItem();

						    if (fechaInicioString.trim().isEmpty() || fechaEstimadaFinString.trim().isEmpty() || nombre.trim().isEmpty() ||
						            desc.trim().isEmpty() || actividades.trim().isEmpty() || insumos.trim().isEmpty()) {
						        optionPane.showMessageDialog(null,"Por favor, complete todos los campos.");
						        return;
						    }

						    LocalDate fechaInicio = null;
						    LocalDate fechaEFin = null;
						    LocalDate fechaFin = null;
						    try {
						        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						        fechaInicio = LocalDate.parse(fechaInicioString, formatter);
						        fechaEFin = LocalDate.parse(fechaEstimadaFinString, formatter);
						        fechaEFin = LocalDate.parse(fechaFinString, formatter);
						        boolean control = validarDatos(fechaInicioString, fechaEstimadaFinString, nombre, desc, actividades, insumos);
						        if (control) {
						            TipoProyecto tipoProyecto = new TipoProyecto(tipo, desc, actividades, insumos);
						            Proyecto proyecto = new Proyecto();
						            proyecto.setFechaInicio(fechaInicio);
						            proyecto.setFechaEstFin(fechaEFin);
						            proyecto.setFechaFin(fechaFin);
						            proyecto.setNombre(nombre);
						            proyecto.setEstado(estado);
						            proyecto.asignarTipoProyecto(tipoProyecto);
						            controlador.alta(proyecto);
						            optionPane.showMessageDialog(null,"Datos guardados con éxito");
						            setVisible(false);
									ProyectoDialog proyectoDialog = new ProyectoDialog();
									proyectoDialog.setVisible(true);
						        }
						    } catch (DateTimeParseException e1) {
						        optionPane.showMessageDialog(null, "La fecha debe tener formato dd/MM/yyyy.");
						        return;
						    } catch (RuntimeException ex) {
						        optionPane.showMessageDialog(null, ex.getMessage());
						        return;
						    }
						} catch (Exception e4) {
						    optionPane.showMessageDialog(null,"Ocurrió un error al procesar los datos: " + e4.getMessage());
						    return;
						}

					}
				});
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
}
