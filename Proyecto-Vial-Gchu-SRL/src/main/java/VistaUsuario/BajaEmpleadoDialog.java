package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladoras.ControladorEmpleado;
import Domain.Empleado;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BajaEmpleadoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BajaEmpleadoDialog dialog = new BajaEmpleadoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BajaEmpleadoDialog() {
		setBounds(50, 50, 450, 300);
		this.setTitle("BAJA DE EMPLEADOS");
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Ingrese Legajo del Empleado");
			lblNewLabel.setBounds(24, 26, 133, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			textField = new JTextField();
			textField.setBounds(167, 23, 132, 19);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setBounds(74, 69, 45, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("New label");
			lblNewLabel_2.setBounds(167, 69, 45, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("New label");
			lblNewLabel_3.setBounds(74, 116, 45, 13);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JButton buscarBtn = new JButton("Buscar");
			buscarBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String dniString = textField.getText(); // Get the value of the JTextField as a String
					int dni = Integer.parseInt(dniString); // Convert the String to an int
					
					ControladorEmpleado controlador = new ControladorEmpleado();
					Empleado empleado = controlador.buscarDNI(dni);
					
					//Cambio la informacion de los Lables
					{
						JLabel lblNewLabel_1 = new JLabel(empleado.getNombre());
						lblNewLabel_1.setBounds(74, 69, 45, 13);
						contentPanel.add(lblNewLabel_1);
					}
					{
						JLabel lblNewLabel_2 = new JLabel(empleado.getApellido());
						lblNewLabel_2.setBounds(167, 69, 45, 13);
						contentPanel.add(lblNewLabel_2);
					}
					{
						JLabel lblNewLabel_3 = new JLabel(Boolean.toString(empleado.getDadoDeBaja()));
						lblNewLabel_3.setBounds(74, 116, 45, 13);
						contentPanel.add(lblNewLabel_3);
					}
					
					//Logro encontrar al empleado!
					
				}
			});
			buscarBtn.setBounds(309, 22, 85, 21);
			contentPanel.add(buscarBtn);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton bajaEmpleadoBtn = new JButton("Dar de Baja");
				bajaEmpleadoBtn.setActionCommand("OK");
				buttonPane.add(bajaEmpleadoBtn);
				getRootPane().setDefaultButton(bajaEmpleadoBtn);
			}
			{
				JButton cancelarBtn = new JButton("Cancelar");
				cancelarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						EmpleadoDialog empleados = new EmpleadoDialog();
						empleados.setVisible(true);
					}
				});
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}

}
