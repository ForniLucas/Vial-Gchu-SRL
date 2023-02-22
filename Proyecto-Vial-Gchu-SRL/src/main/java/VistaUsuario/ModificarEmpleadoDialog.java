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
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ModificarEmpleadoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField legajoTxt;
	private JTextField apellidoTxt;
	private JTextField nombreTxt;
	private JTextField dniTxt;
	private JTextField telefonoTxt;
	private JTextField direccionTxt;
	private JTextField fechaDeNacimientoTxt;
	ControladorEmpleado controlador = new ControladorEmpleado();
	Empleado empleado = new Empleado();
	//Empleado empleadoGlob = new Empleado();

	/*JLabel lblNewLabel_1 = new JLabel("");
	JLabel lblNewLabel_2 = new JLabel("");
	JLabel lblNewLabel_3 = new JLabel("");
	JLabel lblNewLabel_4 = new JLabel("");
	JLabel lblNewLabel_5 = new JLabel("");
	JLabel lblNewLabel_6 = new JLabel("");
	JLabel lblNewLabel_7 = new JLabel("");
	JLabel lblNewLabel_8 = new JLabel("");*/
	
	
	public static void main(String[] args) {
		try {
			ModificarEmpleadoDialog dialog = new ModificarEmpleadoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModificarEmpleadoDialog() {
		
		apellidoTxt = new JTextField();
		nombreTxt = new JTextField();
		dniTxt = new JTextField();
		direccionTxt = new JTextField();
		telefonoTxt = new JTextField();
		fechaDeNacimientoTxt = new JTextField();
		/*lblNewLabel_1.setVisible(false);
		lblNewLabel_2.setVisible(false);
		lblNewLabel_3.setVisible(false);
		lblNewLabel_4.setVisible(false);
		lblNewLabel_5.setVisible(false);
		lblNewLabel_6.setVisible(false);
		lblNewLabel_7.setVisible(false);
		lblNewLabel_8.setVisible(false);*/
		
		
		setBounds(50, 50, 600, 700);
		this.setTitle("MODIFICAR EMPLEADOS");
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Ingrese Legajo del Empleado");
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
			lblNewLabel_5.setBounds(137, 360, 53, 13);
			contentPanel.add(lblNewLabel_5);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Fecha de Nacimiento");
			lblNewLabel_6.setBounds(83, 424, 107, 13);
			contentPanel.add(lblNewLabel_6);
		}
		{
			JLabel lblNewLabel_7 = new JLabel("Especialización");
			lblNewLabel_7.setBounds(114, 488, 76, 13);
			contentPanel.add(lblNewLabel_7);
		}
		{
			JLabel lblNewLabel_8 = new JLabel("Rol");
			lblNewLabel_8.setBounds(145, 552, 45, 13);
			contentPanel.add(lblNewLabel_8);
		}
		{
			legajoTxt = new JTextField();
			legajoTxt.setBounds(237, 37, 96, 19);
			contentPanel.add(legajoTxt);
			legajoTxt.setColumns(255);
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
			JComboBox especializacionBox = new JComboBox(Profesion.values());
			especializacionBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			especializacionBox.setBounds(237, 484, 96, 21);
			contentPanel.add(especializacionBox);
		}
		{
			JComboBox rolBox = new JComboBox(RolEmpleado.values());
			rolBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			rolBox.setBounds(237, 548, 96, 21);
			contentPanel.add(rolBox);
		}
		{
			JButton buscarBtn = new JButton("Buscar");
			buscarBtn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					String dniString = legajoTxt.getText(); // Get the value of the JTextField as a String
					int dni_buscar = Integer.parseInt(dniString); // Convert the String to an int
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
				JButton elementoSeguridadBtn = new JButton("Asignar Elemento de Seguridad");
				elementoSeguridadBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ElementoDeSeguridadDialog elementoDeSeguridadDialog = new ElementoDeSeguridadDialog();
						elementoDeSeguridadDialog.setVisible(true);
					}
				});
				buttonPane.add(elementoSeguridadBtn);
			}
			{
				JButton ropaTrabajoBtn = new JButton("Asignar Ropa de Trabajo");
				ropaTrabajoBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RopaDeTrabajoDialog ropaDeTrabajoDialog = new RopaDeTrabajoDialog();
						ropaDeTrabajoDialog.setVisible(true);
					}
				});
				buttonPane.add(ropaTrabajoBtn);
			}
			{
				JButton guardarModEmpleadoBtn = new JButton("Guardar");
				guardarModEmpleadoBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
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
						
						empleado.setNombre(nombre);
						empleado.setApellido(apellido);
						empleado.setDni(dni);
						empleado.setTelefono(telefono);
						empleado.setDireccion(direccion);
						empleado.setFechaNac(fechaDeNacimiento);
						
						controlador.modificar(empleado);
						
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
