package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
						
						
						String nombre = nombreTxt.getText();
						String apellido = apellidoTxt.getText();

						String dniString = dniTxt.getText(); // Get the value of the JTextField as a String
						int dni = Integer.parseInt(dniString); // Convert the String to an int
						String telefonoString = telefonoTxt.getText(); 
						int telefono = Integer.parseInt(telefonoString); 
						String direccion = direccionTxt.getText(); 
						
						String fechaDeNacimientoString = fechaDeNacimientoTxt.getText(); // Get the value of the JTextField as a String
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Specify the input format
						LocalDate fechaDeNacimiento = LocalDate.parse(fechaDeNacimientoString, formatter); // Convert the String to a LocalDate object using the formatter

						
						
						
						controlador.alta(nombre, apellido, dni, telefono, direccion, fechaDeNacimiento);
						optionPane.showMessageDialog(null,"Datos almacenados con éxito");
						//controlador.asignarElementoDeSeguridad(controlador.buscarDNI(dni), especializacion);
						
						
						
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
}
