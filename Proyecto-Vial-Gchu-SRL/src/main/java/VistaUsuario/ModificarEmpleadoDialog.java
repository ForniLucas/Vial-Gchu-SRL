package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ModificarEmpleadoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
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
		setBounds(100, 100, 500, 700);
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
			textField = new JTextField();
			textField.setBounds(237, 37, 96, 19);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(237, 101, 96, 19);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBounds(237, 165, 96, 19);
			contentPanel.add(textField_2);
			textField_2.setColumns(10);
		}
		{
			textField_3 = new JTextField();
			textField_3.setBounds(237, 229, 96, 19);
			contentPanel.add(textField_3);
			textField_3.setColumns(10);
		}
		{
			textField_4 = new JTextField();
			textField_4.setBounds(237, 293, 96, 19);
			contentPanel.add(textField_4);
			textField_4.setColumns(10);
		}
		{
			textField_5 = new JTextField();
			textField_5.setBounds(237, 360, 96, 19);
			contentPanel.add(textField_5);
			textField_5.setColumns(10);
		}
		{
			textField_6 = new JTextField();
			textField_6.setBounds(237, 421, 96, 19);
			contentPanel.add(textField_6);
			textField_6.setColumns(10);
		}
		{
			textField_7 = new JTextField();
			textField_7.setBounds(237, 485, 96, 19);
			contentPanel.add(textField_7);
			textField_7.setColumns(10);
		}
		{
			textField_8 = new JTextField();
			textField_8.setBounds(237, 549, 96, 19);
			contentPanel.add(textField_8);
			textField_8.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnNewButton_1 = new JButton("Asignar Elemento de Seguridad");
				buttonPane.add(btnNewButton_1);
			}
			{
				JButton btnNewButton = new JButton("Asignar Ropa de Trabajo");
				buttonPane.add(btnNewButton);
			}
			{
				JButton guardarModEmpleadoBtn = new JButton("Guardar");
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
