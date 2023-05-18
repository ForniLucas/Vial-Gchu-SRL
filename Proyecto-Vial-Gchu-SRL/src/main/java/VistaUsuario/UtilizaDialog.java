package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladoras.ControladorMaquinaria;
import Controladoras.ControladorProyecto;
import Domain.Maquinaria;
import Domain.Proyecto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;

public class UtilizaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String idproyecto = new String();
	String codigo = new String();
	String fabricante = new String();
	String descripcion = new String();
	JTextField fechaInicioTxt = new JTextField();;
	JTextField fechaEstimadaTxt = new JTextField();;
	MaquinariaDialog dialog2 = new MaquinariaDialog();
	ControladorMaquinaria controladorM = new ControladorMaquinaria();
	ControladorProyecto controladorP = new ControladorProyecto();
	JTextField codigoTxt = new JTextField();
	Maquinaria maquinaria = new Maquinaria();
	JLabel descripcionLbl = new JLabel("Descripción: ");
	JLabel fabricanteLbl = new JLabel("Fabricante: ");
	Proyecto proyecto = new Proyecto();
	JOptionPane optionPane = new JOptionPane();
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			UtilizaDialog dialog = new UtilizaDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	/**
	 * Create the dialog.
	 */
	public UtilizaDialog(HistorialUtiliza dialog1, String idproyectoi) {
		super(dialog1, "UtilizaDialog",true);
		this.idproyecto = idproyectoi;
		
		
		codigoTxt.setText(codigo);
		proyecto = controladorP.buscarID(Integer.parseInt(idproyecto));
		
		this.setResizable(false);
		this.setTitle("ASOCIAR MAQUINARIA AL PROYECTO");
		setBounds(50,50, 550, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel proyectoidLbl = new JLabel("Proyecto: "+ idproyecto);
		proyectoidLbl.setBounds(54, 42, 221, 13);
		contentPanel.add(proyectoidLbl);
		
		JLabel codigoidLbl = new JLabel("Ingrese un código: ");
		codigoidLbl.setBounds(54, 97, 109, 13);
		contentPanel.add(codigoidLbl);
		
		JLabel fechaInicioLbl = new JLabel("Fecha de Inicio:");
		fechaInicioLbl.setBounds(131, 262, 125, 13);
		contentPanel.add(fechaInicioLbl);
		
		JLabel lblNewLabel = new JLabel("Fecha Estimada de Fin");
		lblNewLabel.setBounds(131, 317, 185, 13);
		contentPanel.add(lblNewLabel);
		
		
		fechaInicioTxt.setText("dd/mm/aaaa");
		fechaInicioTxt.setBounds(286, 259, 109, 19);
		fechaInicioTxt.setText("dd/mm/aaaa");
		fechaInicioTxt.setBounds(286, 259, 109, 19);
		contentPanel.add(fechaInicioTxt);
		fechaInicioTxt.setColumns(10);
		
		
		fechaEstimadaTxt.setText("dd/mm/aaaa");
		fechaEstimadaTxt.setBounds(286, 314, 109, 19);
		fechaEstimadaTxt.setText("dd/mm/aaaa");
		fechaEstimadaTxt.setBounds(286, 314, 109, 19);
		contentPanel.add(fechaEstimadaTxt);
		fechaEstimadaTxt.setColumns(10);
		
		
		codigoTxt = new JTextField();
		codigoTxt.setBounds(219, 94, 116, 19);
		contentPanel.add(codigoTxt);
		codigoTxt.setColumns(10);
		
		JButton buscarBtn = new JButton("Buscar");
		buscarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					maquinaria = controladorM.buscar(codigoTxt.getText());
					descripcion = maquinaria.getDescripcion();
					fabricante = maquinaria.getFabricante();
					descripcionLbl.setText("Descripcion: "+ descripcion);
					fabricanteLbl.setText("Fabricante: "+ fabricante);
				} catch (Exception e1) {
					optionPane.showMessageDialog(null, "Error al buscar: Valor Ingresado no valido o inexistente");
				}
				
			}
		});
		buscarBtn.setBounds(391, 93, 85, 21);
		contentPanel.add(buscarBtn);
		
		
		descripcionLbl.setBounds(54, 152, 300, 13);
		contentPanel.add(descripcionLbl);
		
		
		fabricanteLbl.setBounds(54, 207, 281, 13);
		contentPanel.add(fabricanteLbl);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton asignarBtn = new JButton("Guardar");
				asignarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
								if (maquinaria.getCodigo()!=  null)
								{
									proyecto = controladorP.buscarID(Integer.parseInt(idproyecto));
									maquinaria = controladorM.buscar(codigoTxt.getText());
									String fechaInicioString = fechaInicioTxt.getText(); 
									DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
									LocalDate fechaInicio = LocalDate.parse(fechaInicioString, formatter);
									String fechaEstimadaString = fechaEstimadaTxt.getText(); 
									LocalDate fechaEstimada = LocalDate.parse(fechaEstimadaString, formatter);
									
									boolean control = validarDatos(fechaInicioString,fechaEstimadaString);
									if (control) {
									controladorP.asignarUtiliza(maquinaria, proyecto, fechaInicio, fechaEstimada);
									  optionPane.showMessageDialog(null, "Maquinaria asignada exitosamente.");
									  ProyectoDialog proyectoD = new ProyectoDialog();
									  proyectoD.setVisible(true);
									  setVisible(false);	
									}
								
								
								} else {
									 optionPane.showMessageDialog(null, "Debe buscar una maquinaria primero.");
								}
							} catch (Exception e3) {
						    optionPane.showMessageDialog(null, "Ocurrió un error al procesar los datos: " + e3.getMessage());
						}
						
						
					}
				});
				asignarBtn.setActionCommand("OK");
				buttonPane.add(asignarBtn);
				getRootPane().setDefaultButton(asignarBtn);
			}
			{
				JButton cancelarBtn = new JButton("Cancelar");
				cancelarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ProyectoDialog proyectoD = new ProyectoDialog();
						proyectoD.setVisible(true);
						setVisible(false);	
					}
				});
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}
	
	public boolean validarDatos (String fechaInicioString, String fechaEstFinString) {
		boolean resultado = true;
		
		// Validar fecha de inicio
	    try {
	        LocalDate fechaInicio = LocalDate.parse(fechaInicioString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	        LocalDate fechaEstFin = LocalDate.parse(fechaEstFinString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

	        if (!fechaEstFinString.trim().isEmpty()) {
	            try {
		            if (fechaEstFin.isBefore(fechaInicio)) {
		                JOptionPane.showMessageDialog(null, "La fecha de fin estimada no puede ser anterior a la fecha de inicio.");
		                resultado = false;
		                return resultado;
		            }
	            } catch (DateTimeParseException e) {
	                JOptionPane.showMessageDialog(null, "Ingrese una fecha de fin válida (formato: dd/MM/yyyy 1).");
	                resultado = false;
	                return resultado;
	            }
	        }
	    } catch (DateTimeParseException e) {
	            JOptionPane.showMessageDialog(null, e.getMessage());
	            resultado = false;
	        return resultado;
	    }
	    
	    return resultado;
		
	}
}
