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
		
		setBounds(50, 50, 600, 700);
		this.setTitle("MODIFICAR EMPLEADOS");
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Ingrese el DNI del Empleado");
			lblNewLabel.setBounds(41, 40, 149, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Apellido");
			lblNewLabel_1.setBounds(145, 104, 45, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Nombre");
			lblNewLabel_2.setBounds(145, 168, 45, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("DNI");
			lblNewLabel_3.setBounds(156, 232, 34, 13);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Teléfono");
			lblNewLabel_4.setBounds(137, 296, 53, 13);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Dirección");
			lblNewLabel_5.setBounds(137, 360, 90, 13);
			contentPanel.add(lblNewLabel_5);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Fecha de Nacimiento");
			lblNewLabel_6.setBounds(83, 424, 144, 13);
			contentPanel.add(lblNewLabel_6);
		}
		{
			dniidTxt = new JTextField();
			dniidTxt.setBounds(237, 37, 96, 19);
			contentPanel.add(dniidTxt);
			dniidTxt.setColumns(255);
		}
		{
			
			
			apellidoTxt.setBounds(237, 101, 133, 19);
			contentPanel.add(apellidoTxt);
			apellidoTxt.setColumns(255);
		}
		{
			
			
			nombreTxt.setBounds(237, 165, 133, 19);
			contentPanel.add(nombreTxt);
			nombreTxt.setColumns(255);
		}
		{
			
			
			dniTxt.setBounds(237, 229, 96, 19);
			contentPanel.add(dniTxt);
			dniTxt.setColumns(255);
		}
		{
			
			
			telefonoTxt.setBounds(237, 293, 133, 19);
			contentPanel.add(telefonoTxt);
			telefonoTxt.setColumns(255);
		}
		{
			
			
			direccionTxt.setBounds(237, 360, 133, 19);
			contentPanel.add(direccionTxt);
			direccionTxt.setColumns(255);
		}
		{
			
			
			fechaDeNacimientoTxt.setBounds(237, 421, 96, 19);
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
			buscarBtn.setBounds(392, 36, 85, 21);
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
							//RolEmpleado rol = (RolEmpleado) rolBox.getSelectedItem();
							//Profesion especializacion = (Profesion) especializacionBox.getSelectedItem();
							
							empleado.setNombre(nombre);
							empleado.setApellido(apellido);
							empleado.setDni(dni);
							empleado.setTelefono(telefono);
							empleado.setDireccion(direccion);
							empleado.setFechaNac(fechaDeNacimiento);
							
							controlador.modificar(empleado);
							optionPane.showMessageDialog(null, "Empleado modificado exitosamente.");
						}
						else {
							optionPane.showMessageDialog(null, "Debe buscar un empleado primero.");
						}
					}catch (Exception e1) {
						optionPane.showMessageDialog(null, "Error al modificar el empleado: " + e1.getMessage());
					}
						
					}
				});
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

}
