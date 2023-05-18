package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.persistence.PersistenceException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladoras.ControladorEmpleado;
import Domain.Empleado;
import Domain.Especializacion;
import Enumeraciones.Profesion;
import Enumeraciones.RolEmpleado;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
//import com.toedter.calendar.JDateChooser;

public class AltaEmpleadoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField apellidoTxt;
	private JTextField nombreTxt;
	private JTextField dniTxt;
	private JTextField telefonoTxt;
	private JTextField direccionTxt;
	private JTextField fechaDeNacimientoTxt;
	private JComboBox especializacionBox = new JComboBox(Profesion.values());
	private JComboBox rolBox = new JComboBox(RolEmpleado.values());
	ControladorEmpleado controlador = new ControladorEmpleado();
	Especializacion especializacion = new Especializacion();
	JOptionPane optionPane = new JOptionPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AltaEmpleadoDialog dialog = new AltaEmpleadoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AltaEmpleadoDialog() {
		setBounds(50, 50, 600, 700);
		this.setTitle("ALTA EMPLEADOS");
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			apellidoTxt = new JTextField();
			apellidoTxt.setBounds(271, 61, 204, 19);
			contentPanel.add(apellidoTxt);
			apellidoTxt.setColumns(255);
		}
		
		nombreTxt = new JTextField();
		nombreTxt.setBounds(271, 156, 204, 19);
		contentPanel.add(nombreTxt);
		nombreTxt.setColumns(255);
		
		dniTxt = new JTextField();
		dniTxt.setBounds(271, 245, 96, 19);
		contentPanel.add(dniTxt);
		dniTxt.setColumns(255);
		
		telefonoTxt = new JTextField();
		telefonoTxt.setBounds(271, 337, 204, 19);
		contentPanel.add(telefonoTxt);
		telefonoTxt.setColumns(255);
		
		direccionTxt = new JTextField();
		direccionTxt.setBounds(271, 429, 204, 19);
		contentPanel.add(direccionTxt);
		direccionTxt.setColumns(255);
		
		fechaDeNacimientoTxt = new JTextField();
		fechaDeNacimientoTxt.setText("dd/mm/aaaa");
		fechaDeNacimientoTxt.setBounds(271, 521, 96, 19);
		contentPanel.add(fechaDeNacimientoTxt);
		fechaDeNacimientoTxt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Apellido");
		lblNewLabel.setBounds(165, 64, 156, 13);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(160, 159, 161, 13);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DNI");
		lblNewLabel_2.setBounds(179, 248, 108, 13);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Teléfono");
		lblNewLabel_3.setBounds(160, 340, 127, 13);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Dirección");
		lblNewLabel_4.setBounds(154, 432, 133, 13);
		contentPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Fecha de Nacimiento");
		lblNewLabel_5.setBounds(105, 524, 195, 13);
		contentPanel.add(lblNewLabel_5);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton guardarBtn = new JButton("Guardar");
				guardarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
						    String nombre = nombreTxt.getText();
						    String apellido = apellidoTxt.getText();
						    String dniString = dniTxt.getText();
						    String telefonoString = telefonoTxt.getText();
						    String direccion = direccionTxt.getText();
						    String fechaDeNacimientoString = fechaDeNacimientoTxt.getText();
						    
						    if (nombre.trim().isEmpty() || apellido.trim().isEmpty() || dniString.trim().isEmpty() ||
						            telefonoString.trim().isEmpty() || direccion.trim().isEmpty() || fechaDeNacimientoString.trim().isEmpty()) {
						        optionPane.showMessageDialog(null,"Por favor, complete todos los campos.");
						        return;
						    }

						    int dni = 0;
						    int telefono = 0;
						    LocalDate fechaDeNacimiento = null;

						    try {
						        dni = Integer.parseInt(dniString);
						        
						        telefono = Integer.parseInt(telefonoString);
						        
						        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						        fechaDeNacimiento = LocalDate.parse(fechaDeNacimientoString, formatter);
						        boolean control = validarDatos(nombre, apellido, dniString, telefonoString, direccion, fechaDeNacimiento);
						        	if (control) {	
						        		controlador.alta(nombre, apellido, dni, telefono, direccion, fechaDeNacimiento);
						        		optionPane.showMessageDialog(null,"Datos almacenados con éxito");
						        		setVisible(false);
						        		EmpleadoDialog empleados = new EmpleadoDialog();
										empleados.setVisible(true);
						        	}
						    } catch (NumberFormatException e1) {
						        optionPane.showMessageDialog(null, "El valor del DNI o teléfono debe ser un número entero.");
						        return;
						    } catch (DateTimeParseException e2) {
						        optionPane.showMessageDialog(null, "La fecha de nacimiento debe tener formato dd/MM/yyyy.");
						        return;
						        
						    }catch (RuntimeException ex ){
						    	optionPane.showMessageDialog(null, ex.getMessage());
						        return;
						    }
							} catch (Exception e4) {
								optionPane.showMessageDialog(null,"Ocurrió un error al procesar los datos: " + e4.getMessage());
								return;
							}

  
					}

				});
				guardarBtn.setActionCommand("OK");
				buttonPane.add(guardarBtn);
				getRootPane().setDefaultButton(guardarBtn);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						EmpleadoDialog empleados = new EmpleadoDialog();
						empleados.setVisible(true);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public boolean validarDatos(String nombre, String apellido, String dniString, String telefonoString, String direccion, LocalDate fechaDeNacimiento) {
		
		boolean resultado = true;
		
		// Validar nombre y apellido
		if (!nombre.matches("[a-zA-Z]{1,30}")) {
		    // Mensaje de error si el nombre no contiene solo letras o excede los 30 caracteres
		    JOptionPane.showMessageDialog(null, "Ingrese un nombre válido (solo letras y hasta 30 caracteres).");
		    resultado = false;
		}
		if (!apellido.matches("[a-zA-Z]{1,30}")) {
		    // Mensaje de error si el apellido no contiene solo letras o excede los 30 caracteres
		    JOptionPane.showMessageDialog(null, "Ingrese un apellido válido (solo letras y hasta 30 caracteres).");
		    resultado = false;
		}

		// Validar DNI
		try {
		    if (dniString.length() != 8) {
		        // Mensaje de error si el DNI no tiene 8 dígitos
		        JOptionPane.showMessageDialog(null, "Ingrese un DNI válido (8 dígitos).");
		        resultado = false;
		    }
		} catch (NumberFormatException e) {
		    // Mensaje de error si el DNI no es un número válido
		    JOptionPane.showMessageDialog(null, "Ingrese un DNI válido (solo números).");
		    resultado = false;
		}
		
		// Validar la no existencia del usuario
		int dni = 0;
		dni = Integer.parseInt(dniString);
		Empleado empleado = controlador.buscarDNI(dni);
		if (!(empleado== null)) {
			JOptionPane.showMessageDialog(null, "Ya existe un empleado con el mismo dni.");
			resultado = false;
		}


		// Validar número de teléfono
		
		    
			if (!telefonoString.matches("[0-9 ]{1,8}")) {
		        // Mensaje de error si el número de teléfono no tiene 8 dígitos
		        JOptionPane.showMessageDialog(null, "Ingrese un número de teléfono válido (Hatsa 8 dígitos).");
		        resultado = false;
		    }
		
		
	


		// Validar dirección
		if (!direccion.matches("[a-zA-Z0-9 ]{1,50}")) {
		    // Mensaje de error si la dirección contiene caracteres no permitidos o excede los 50 caracteres
		    JOptionPane.showMessageDialog(null, "Ingrese una dirección válida (solo letras, números y espacios, hasta 50 caracteres).");
		    resultado = false;
		}

		// Validar fecha de nacimiento
		try {
		    if (fechaDeNacimiento.isAfter(LocalDate.now())) {
		        // Mensaje de error si la fecha de nacimiento es posterior a la fecha actual
		        JOptionPane.showMessageDialog(null, "Ingrese una fecha de nacimiento válida (dd/MM/yyyy).");
		        resultado = false;
		    }
		} catch (DateTimeParseException e) {
		    // Mensaje de error si la fecha de nacimiento no es una cadena válida en el formato especificado
		    JOptionPane.showMessageDialog(null, "Ingrese una fecha de nacimiento válida (dd/MM/yyyy).");
		    resultado = false;
		}

		return resultado;

	   
	    
	}

}

