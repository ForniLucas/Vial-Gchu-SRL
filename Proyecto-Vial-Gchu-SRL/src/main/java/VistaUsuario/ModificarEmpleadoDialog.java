package VistaUsuario;

import java.awt.BorderLayout;
import Enumeraciones.Profesion;
import Enumeraciones.RolEmpleado;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
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
			legajoTxt.setColumns(10);
		}
		{
			apellidoTxt = new JTextField();
			apellidoTxt.setBounds(237, 101, 133, 19);
			contentPanel.add(apellidoTxt);
			apellidoTxt.setColumns(10);
		}
		{
			nombreTxt = new JTextField();
			nombreTxt.setBounds(237, 165, 133, 19);
			contentPanel.add(nombreTxt);
			nombreTxt.setColumns(10);
		}
		{
			dniTxt = new JTextField();
			dniTxt.setBounds(237, 229, 96, 19);
			contentPanel.add(dniTxt);
			dniTxt.setColumns(10);
		}
		{
			telefonoTxt = new JTextField();
			telefonoTxt.setBounds(237, 293, 133, 19);
			contentPanel.add(telefonoTxt);
			telefonoTxt.setColumns(10);
		}
		{
			direccionTxt = new JTextField();
			direccionTxt.setBounds(237, 360, 133, 19);
			contentPanel.add(direccionTxt);
			direccionTxt.setColumns(10);
		}
		{
			fechaDeNacimientoTxt = new JTextField();
			fechaDeNacimientoTxt.setText("dd-mm-aaaa");
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
