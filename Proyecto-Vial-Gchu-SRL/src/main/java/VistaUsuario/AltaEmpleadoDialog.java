package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Domain.Empleado;
import Enumeraciones.Profesion;
import Enumeraciones.RolEmpleado;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class AltaEmpleadoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField apellidoTxt;
	private JTextField nombreTxt;
	private JTextField dniTxt;
	private JTextField telefonoTxt;
	private JTextField direccionTxt;
	private JTextField fechaDeNacimientoTxt;

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
			apellidoTxt.setBounds(248, 45, 204, 19);
			contentPanel.add(apellidoTxt);
			apellidoTxt.setColumns(10);
		}
		
		nombreTxt = new JTextField();
		nombreTxt.setBounds(248, 116, 204, 19);
		contentPanel.add(nombreTxt);
		nombreTxt.setColumns(10);
		
		dniTxt = new JTextField();
		dniTxt.setBounds(248, 187, 96, 19);
		contentPanel.add(dniTxt);
		dniTxt.setColumns(10);
		
		telefonoTxt = new JTextField();
		telefonoTxt.setBounds(248, 258, 204, 19);
		contentPanel.add(telefonoTxt);
		telefonoTxt.setColumns(10);
		
		direccionTxt = new JTextField();
		direccionTxt.setBounds(248, 329, 204, 19);
		contentPanel.add(direccionTxt);
		direccionTxt.setColumns(10);
		
		fechaDeNacimientoTxt = new JTextField();
		fechaDeNacimientoTxt.setText("dd-mm-aaaa");
		fechaDeNacimientoTxt.setBounds(248, 400, 96, 19);
		contentPanel.add(fechaDeNacimientoTxt);
		fechaDeNacimientoTxt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Apellido");
		lblNewLabel.setBounds(152, 48, 56, 13);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(152, 119, 56, 13);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DNI");
		lblNewLabel_2.setBounds(176, 190, 32, 13);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Teléfono");
		lblNewLabel_3.setBounds(152, 261, 56, 13);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Dirección");
		lblNewLabel_4.setBounds(152, 332, 56, 13);
		contentPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Fecha de Nacimiento");
		lblNewLabel_5.setBounds(98, 403, 110, 13);
		contentPanel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Especialización");
		lblNewLabel_6.setBounds(119, 473, 89, 13);
		contentPanel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Rol");
		lblNewLabel_7.setBounds(176, 545, 32, 13);
		contentPanel.add(lblNewLabel_7);
		
		JComboBox especializacionBox = new JComboBox(Profesion.values());
		especializacionBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		especializacionBox.setBounds(248, 469, 96, 21);
		contentPanel.add(especializacionBox);
		
		JComboBox rolBox = new JComboBox(RolEmpleado.values());
		rolBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		rolBox.setBounds(248, 541, 96, 21);
		contentPanel.add(rolBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton asignarESBtn = new JButton("Asiganr Elemento de Seguridad");
				asignarESBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ElementoDeSeguridadDialog elementoDeSeguridadDialog = new ElementoDeSeguridadDialog();
						elementoDeSeguridadDialog.setVisible(true);
					}
				});
				buttonPane.add(asignarESBtn);
			}
			{
				JButton asignarRTBtn = new JButton("Asignar Ropa de Trabajo");
				asignarRTBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RopaDeTrabajoDialog ropaDeTrabajoDialog = new RopaDeTrabajoDialog();
						ropaDeTrabajoDialog.setVisible(true);
					}
				});
				buttonPane.add(asignarRTBtn);
			}
			{
				JButton guardarBtn = new JButton("Guardar");
				guardarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
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
