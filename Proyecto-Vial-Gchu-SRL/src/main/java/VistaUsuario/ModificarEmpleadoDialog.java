package VistaUsuario;

import java.awt.BorderLayout;
import Enumeraciones.Profesion;
import Enumeraciones.RolEmpleado;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladoras.ControladorEmpleado;
import Domain.Empleado;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ModificarEmpleadoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String dniid = new String();
	String legajo = new String();
	String apellido = new String();
	String nombre = new String();
	String dni = new String();
	String telefono = new String();
	String direccion = new String();
	String fechaDeNacimiento = new String();
	String estado = new String();
	JOptionPane optionPane = new JOptionPane();
	JTextField dniidTxt = new JTextField();
	JTextField apellidoTxt = new JTextField();
	JTextField nombreTxt = new JTextField();
	JTextField dniTxt = new JTextField();
	JTextField telefonoTxt = new JTextField();
	JTextField direccionTxt = new JTextField();
	JTextField fechaDeNacimientoTxt = new JTextField();
	ControladorEmpleado controlador = new ControladorEmpleado();
	Empleado empleado = new Empleado();

	/**
	 * Create the dialog.
	 */
	public ModificarEmpleadoDialog(EmpleadoDialog dialog, String dniid) {
		super(dialog, "ModificarEmpleadoDialog",true);
		this.dniid = dniid;
		
		dniidTxt.setText(dniid);
		
		setBounds(50, 50, 750, 700);
		this.setTitle("MODIFICAR EMPLEADOS");
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Ingrese el DNI:");
			lblNewLabel.setBounds(161, 82, 85, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Apellido");
			lblNewLabel_1.setBounds(226, 181, 68, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Nombre");
			lblNewLabel_2.setBounds(226, 245, 96, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("DNI");
			lblNewLabel_3.setBounds(237, 309, 68, 13);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Teléfono");
			lblNewLabel_4.setBounds(218, 373, 76, 13);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Dirección");
			lblNewLabel_5.setBounds(203, 437, 68, 13);
			contentPanel.add(lblNewLabel_5);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Fecha de Nacimiento");
			lblNewLabel_6.setBounds(161, 501, 133, 13);
			contentPanel.add(lblNewLabel_6);
		}
		{
			dniidTxt = new JTextField(dniid);
			dniidTxt.setBounds(279, 79, 96, 19);
			contentPanel.add(dniidTxt);
			dniidTxt.setColumns(255);
		}
		{
			
			
			apellidoTxt.setBounds(304, 178, 167, 19);
			contentPanel.add(apellidoTxt);
			apellidoTxt.setColumns(255);
		}
		{
			
			
			nombreTxt.setBounds(304, 242, 167, 19);
			contentPanel.add(nombreTxt);
			nombreTxt.setColumns(255);
		}
		{
			
			
			dniTxt.setBounds(304, 306, 96, 19);
			contentPanel.add(dniTxt);
			dniTxt.setColumns(255);
		}
		{
			
			
			telefonoTxt.setBounds(304, 370, 133, 19);
			contentPanel.add(telefonoTxt);
			telefonoTxt.setColumns(255);
		}
		{
			
			
			direccionTxt.setBounds(304, 437, 167, 19);
			contentPanel.add(direccionTxt);
			direccionTxt.setColumns(255);
		}
		{
			
			
			fechaDeNacimientoTxt.setBounds(304, 498, 96, 19);
			contentPanel.add(fechaDeNacimientoTxt);
			fechaDeNacimientoTxt.setColumns(10);
		}
		{
			JButton buscarBtn = new JButton("Buscar");
			buscarBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try {
						int dni_buscar = Integer.parseInt(dniidTxt.getText()); 
						empleado = controlador.buscarDNI(dni_buscar);
					
						apellidoTxt.setText(empleado.getApellido());
						nombreTxt.setText(empleado.getNombre());
						String dni = Integer.toString(dni_buscar);
						dniTxt.setText(dni);
						String telefon = Integer.toString(empleado.getTelefono());
						telefonoTxt.setText(telefon);
						direccionTxt.setText(empleado.getDireccion());
						
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String fechaNac = empleado.getFechaNac() != null ? empleado.getFechaNac().format(formatter) : null;
						if (fechaNac != null && !fechaNac.isEmpty()) {
						    fechaDeNacimientoTxt.setText(fechaNac);
						} else {
						    fechaDeNacimientoTxt.setText("");
						}
						fechaDeNacimientoTxt.setText(fechaNac);
					} catch (Exception e1) {
						optionPane.showMessageDialog(null, "Error al buscar: Valor Ingresado no valido o inexistente");
					}
					
					
				}
			});
			buscarBtn.setBounds(434, 78, 85, 21);
			contentPanel.add(buscarBtn);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton guardarModEmpleadoBtn = new JButton("Guardar");
				guardarModEmpleadoBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					try {
						if (empleado.getApellido() != null) {
							String nombre = nombreTxt.getText();
							String apellido = apellidoTxt.getText();
							String dniString = dniTxt.getText(); // Get the value of the JTextField as a String
							int dni = Integer.parseInt(dniString); // Convert the String to an int
							String telefonoString = telefonoTxt.getText(); 
							int telefono = Integer.parseInt(telefonoString); 
							String direccion = direccionTxt.getText(); 
							String fechaDeNacimientoString = fechaDeNacimientoTxt.getText(); // Get the value of the JTextField as a String
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Specify the input format
							LocalDate fechaDeNacimiento = LocalDate.parse(fechaDeNacimientoString, formatter);
							
							if (nombre.trim().isEmpty() || apellido.trim().isEmpty() || dniString.trim().isEmpty() ||
						            telefonoString.trim().isEmpty() || direccion.trim().isEmpty() || fechaDeNacimientoString.trim().isEmpty()) {
						        optionPane.showMessageDialog(null,"Por favor, complete todos los campos.");
						        return;
						    }
							
							try {
								
								boolean control = validarDatos(nombre, apellido, dniString, telefonoString, direccion, fechaDeNacimiento);
								
								if (control) {
									empleado.setNombre(nombre);
									empleado.setApellido(apellido);
									empleado.setDni(dni);
									empleado.setTelefono(telefono);
									empleado.setDireccion(direccion);
									empleado.setFechaNac(fechaDeNacimiento);
									controlador.modificar(empleado);
									optionPane.showMessageDialog(null, "Datos modificados con éxito");
									setVisible(false);
									EmpleadoDialog empleados = new EmpleadoDialog();
									empleados.setVisible(true);
								}
								
							}catch (NumberFormatException e1) {
						        optionPane.showMessageDialog(null, "El valor del DNI o teléfono debe ser un número entero.");
						        return;
						    } catch (DateTimeParseException e2) {
						        optionPane.showMessageDialog(null, "La fecha de nacimiento debe tener formato dd/MM/yyyy.");
						        return;
						        
						    }catch (RuntimeException ex ){
						    	optionPane.showMessageDialog(null, ex.getMessage());
						        return;
							} catch (Exception e4) {
								optionPane.showMessageDialog(null,"Ocurrió un error al procesar los datos: " + e4.getMessage());
								return;
							}
							
						}
						else {
							optionPane.showMessageDialog(null, "Debe buscar un empleado primero.");
						}
					}catch (Exception e1) {
						optionPane.showMessageDialog(null, "Error al modificar el empleado: " + e1.getMessage());
					}
						
					}
				});
				{
					JButton gestionarEspBtn = new JButton("Gestionar Especialización");
					gestionarEspBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							setVisible(false);
							HistorialEspecializacionDialog historialEspecializacion = new HistorialEspecializacionDialog(ModificarEmpleadoDialog.this,
									ModificarEmpleadoDialog.this.dniid);
							historialEspecializacion.setVisible(true);
						}
					});
					buttonPane.add(gestionarEspBtn);
				}
				{
					JButton gestionarElemBtn = new JButton("Gestionar Elementos de Seguridad");
					gestionarElemBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							setVisible(false);
							HistorialElementoDeSeguridadDialog historialElemento = new HistorialElementoDeSeguridadDialog(ModificarEmpleadoDialog.this,
									ModificarEmpleadoDialog.this.dniid);
							historialElemento.setVisible(true);
						}
					});
					buttonPane.add(gestionarElemBtn);
				}
				{
					JButton gestionarRopaBtn = new JButton("Gestionar Ropa");
					gestionarRopaBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							setVisible(false);
							HistorialRopaDeTrabajoDialog historialRopa = new HistorialRopaDeTrabajoDialog(ModificarEmpleadoDialog.this,
									ModificarEmpleadoDialog.this.dniid);
							historialRopa.setVisible(true);
						}
					});
					buttonPane.add(gestionarRopaBtn);
				}
				guardarModEmpleadoBtn.setActionCommand("OK");
				buttonPane.add(guardarModEmpleadoBtn);
				getRootPane().setDefaultButton(guardarModEmpleadoBtn);
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
		if (!(empleado.getNombre() == null)) {
			JOptionPane.showMessageDialog(null, "Ya existe un empleado con el mismo dni.");
			resultado = false;
		}


		// Validar número de teléfono
		try {
		    
		    if (telefonoString.length() >= 10) {
		        // Mensaje de error si el número de teléfono no tiene 10 dígitos
		        JOptionPane.showMessageDialog(null, "Ingrese un número de teléfono válido (Hatsa 10 dígitos).");
		        resultado = false;
		    }
		} catch (NumberFormatException e) {
		    // Mensaje de error si el número de teléfono no es un número válido
		    JOptionPane.showMessageDialog(null, "Ingrese un número de teléfono válido (solo números).");
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
