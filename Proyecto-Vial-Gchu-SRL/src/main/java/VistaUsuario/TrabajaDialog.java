package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controladoras.ControladorEmpleado;
import Controladoras.ControladorMaquinaria;
import Controladoras.ControladorProyecto;
import Domain.Empleado;
import Domain.Maquinaria;
import Domain.Proyecto;

public class TrabajaDialog extends JDialog {
	JTextField fechaInicioTxt = new JTextField();;
	JTextField fechaEstimadaTxt = new JTextField();
	JTextField horasTrabajadasTxt = new JTextField();
	MaquinariaDialog dialog2 = new MaquinariaDialog();
	ControladorEmpleado controladorE = new ControladorEmpleado();
	ControladorProyecto controladorP = new ControladorProyecto();
	JTextField dniTxt = new JTextField();
	Empleado empleado = new Empleado();
	JLabel apellidoLbl = new JLabel("Apellido: ");
	JLabel nombreLbl = new JLabel("Nombre: ");
	Proyecto proyecto = new Proyecto();
	JOptionPane optionPane = new JOptionPane();
	private final JPanel contentPanel = new JPanel();
	String idproyecto = new String();
	String dni = new String();
	String apellido = new String();
	String nombre = new String();
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			TrabajaDialog dialog = new TrabajaDialog();
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
	public TrabajaDialog(HistorialTrabaja dialog1, String idproyectoc) {
		super(dialog1, "UtilizaDialog",true);
		this.idproyecto = idproyectoc;
		
		this.setResizable(false);
		this.setTitle("ASOCIAR EMPLEADOS AL PROYECTO");
		setBounds(50,50, 550, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel proyectoidLbl = new JLabel("Proyecto: "+ idproyecto);
		proyectoidLbl.setBounds(54, 42, 221, 13);
		contentPanel.add(proyectoidLbl);
		
		JLabel codigoidLbl = new JLabel("Ingrese un DNI: ");
		codigoidLbl.setBounds(54, 97, 109, 13);
		contentPanel.add(codigoidLbl);
		
		JLabel fechaInicioLbl = new JLabel("Fecha de Inicio:");
		fechaInicioLbl.setBounds(127, 261, 139, 13);
		contentPanel.add(fechaInicioLbl);
		
		JLabel lblNewLabel = new JLabel("Fecha Estimada de Fin");
		lblNewLabel.setBounds(127, 303, 139, 13);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Horas Trabajadas:");
		lblNewLabel_2.setBounds(127, 344, 109, 16);
		contentPanel.add(lblNewLabel_2);
		fechaInicioTxt.setBounds(276, 258, 109, 19);
		
		fechaInicioTxt.setText("dd/mm/aaaa");
		contentPanel.add(fechaInicioTxt);
		fechaInicioTxt.setColumns(255);
		fechaEstimadaTxt.setBounds(276, 300, 109, 19);
		
		fechaEstimadaTxt.setText("dd/mm/aaaa");
		contentPanel.add(fechaEstimadaTxt);
		fechaEstimadaTxt.setColumns(255);
		horasTrabajadasTxt.setBounds(276, 343, 109, 20);
		
		horasTrabajadasTxt.setText("");
		contentPanel.add(horasTrabajadasTxt);
		horasTrabajadasTxt.setColumns(255);
		
		dniTxt = new JTextField();
		dniTxt.setBounds(219, 94, 116, 19);
		contentPanel.add(dniTxt);
		dniTxt.setColumns(255);

		JButton buscarBtn = new JButton("Buscar");
		buscarBtn.setBounds(391, 93, 85, 21);
		buscarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					empleado = controladorE.buscarDNI(Integer.parseInt(dniTxt.getText()));
					apellido = empleado.getApellido();
					nombre = empleado.getNombre();
					apellidoLbl.setText("Apellido: "+ apellido);
					nombreLbl.setText("Nombre: "+ nombre);
				} catch (Exception e1) {
					optionPane.showMessageDialog(null, "Error al buscar: Valor Ingresado no valido o inexistente");
				}
				
			}
		});
		contentPanel.add(buscarBtn);
		apellidoLbl.setBounds(54, 152, 221, 13);
		contentPanel.add(apellidoLbl);
		nombreLbl.setBounds(54, 207, 139, 13);
		contentPanel.add(nombreLbl);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Guardar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if (empleado.getApellido() != null) {
								proyecto = controladorP.buscarID(Integer.parseInt(idproyecto));
								String fechaInicioString = fechaInicioTxt.getText(); // Get the value of the JTextField as a String
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Specify the input format
								LocalDate fechaInicio = LocalDate.parse(fechaInicioString, formatter); // Convert the String to a LocalDate object using the formatter
								String fechaEstimadaString = fechaEstimadaTxt.getText();
								LocalDate fechaEstimadaFin = LocalDate.parse(fechaEstimadaString, formatter);
								String horasTrabajadasString = horasTrabajadasTxt.getText();
								
								
								boolean control = validarDatos(fechaInicioString, fechaEstimadaString, horasTrabajadasString);
								
								if (control) {
									int horasTrabajadas = Integer.parseInt(horasTrabajadasString);
									controladorP.asignarTrabajo(empleado, proyecto,horasTrabajadas,fechaInicio,fechaEstimadaFin); //
									optionPane.showMessageDialog(null, "Empleado asignado exitosamente.");
									ProyectoDialog proyectoD = new ProyectoDialog();
									proyectoD.setVisible(true);
									setVisible(false);	
								}
							}
							else {optionPane.showMessageDialog(null, "Debe buscar un empleado primero.");}
							
						} catch (Exception e3) {
						    optionPane.showMessageDialog(null, "Ocurrió un error al procesar los datos: " + e3.getMessage());
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public boolean validarDatos (String fechaInicioString, String fechaEstFinString, String horasTrabajadasString) {
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
	    
	 // Validar horas trabajadas
	    try {
	        int horasTrabajadas = Integer.parseInt(horasTrabajadasString);
	        if (horasTrabajadas <= 0) {
	            JOptionPane.showMessageDialog(null, "Ingrese un número válido de horas trabajadas.");
	            resultado = false;
	            return resultado;
	        }
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Ingrese un número válido de horas trabajadas.");
	        resultado = false;
	        return resultado;
	    }
	    
	    return resultado;
		
	}

}
