package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Domain.Empleado;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class AltaEmpleadoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtA;
	private JTextField txtNombre;
	private JTextField txtDni;
	private JTextField txtTelfono;
	private JTextField txtDireccin;
	private JTextField txtFechaDeNacimiento;
	private JTextField txtEspecializacin;
	private JTextField txtRol;

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
			txtA = new JTextField();
			txtA.setBounds(248, 45, 238, 19);
			contentPanel.add(txtA);
			txtA.setColumns(10);
		}
		
		txtNombre = new JTextField();
		txtNombre.setText("Nombre");
		txtNombre.setBounds(248, 116, 238, 19);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setText("DNI");
		txtDni.setBounds(248, 187, 96, 19);
		contentPanel.add(txtDni);
		txtDni.setColumns(10);
		
		txtTelfono = new JTextField();
		txtTelfono.setText("Teléfono");
		txtTelfono.setBounds(248, 258, 135, 19);
		contentPanel.add(txtTelfono);
		txtTelfono.setColumns(10);
		
		txtDireccin = new JTextField();
		txtDireccin.setText("Dirección");
		txtDireccin.setBounds(248, 329, 238, 19);
		contentPanel.add(txtDireccin);
		txtDireccin.setColumns(10);
		
		txtFechaDeNacimiento = new JTextField();
		txtFechaDeNacimiento.setText("dd/mm/aaaa");
		txtFechaDeNacimiento.setBounds(248, 400, 96, 19);
		contentPanel.add(txtFechaDeNacimiento);
		txtFechaDeNacimiento.setColumns(10);
		
		txtEspecializacin = new JTextField();
		txtEspecializacin.setText("Especialización");
		txtEspecializacin.setBounds(248, 459, 96, 19);
		contentPanel.add(txtEspecializacin);
		txtEspecializacin.setColumns(10);
		
		txtRol = new JTextField();
		txtRol.setText("Rol");
		txtRol.setBounds(248, 542, 96, 19);
		contentPanel.add(txtRol);
		txtRol.setColumns(10);
		
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
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton asignarESBtn = new JButton("Asiganr Elemento de Seguridad");
				asignarESBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
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
						setVisible(false);
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
