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
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BajaEmpleadoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String dni = new String();
	JTextField dniTxt = new JTextField();
	ControladorEmpleado controlador = new ControladorEmpleado();
	Empleado empleado = new Empleado();
	JLabel lblNewLabel_1 = new JLabel("");
	JLabel lblNewLabel_2 = new JLabel("");
	JLabel lblNewLabel_3 = new JLabel("");
	JLabel lblNewLabel_4 = new JLabel("");
	JOptionPane optionPane = new JOptionPane();
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
		
		lblNewLabel_1.setVisible(false);
		lblNewLabel_2.setVisible(false);
		lblNewLabel_3.setVisible(false);
		lblNewLabel_4.setVisible(false);
		
		dniTxt.setText(dni);
		
		setBounds(50, 50, 450, 300);
		this.setTitle("BAJA DE EMPLEADOS");
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		{
			JLabel lblNewLabel = new JLabel("Ingrese DNI del Empleado");
			lblNewLabel.setBounds(24, 26, 133, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			//dniTxt = new JTextField();
			dniTxt.setBounds(164, 23, 124, 19);
			contentPanel.add(dniTxt);
			dniTxt.setColumns(255);
		}
		{
			
			lblNewLabel_1.setBounds(238, 83, 174, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			
			lblNewLabel_2.setBounds(24, 152, 163, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			
			lblNewLabel_3.setBounds(238, 152, 174, 13);
			contentPanel.add(lblNewLabel_3);
		}
		{
			
			lblNewLabel_4.setBounds(24, 83, 163, 13); //VER
			contentPanel.add(lblNewLabel_4);
		}
		{
			JButton buscarBtn = new JButton("Buscar");
			buscarBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String dniString = dniTxt.getText(); // Get the value of the JTextField as a String
					int dni = Integer.parseInt(dniString); // Convert the String to an int
					
					empleado = controlador.buscarDNI(dni);
					lblNewLabel_1.setVisible(true);
					lblNewLabel_2.setVisible(true);
					lblNewLabel_3.setVisible(true);
					lblNewLabel_4.setVisible(true);
					
					lblNewLabel_1.setText("Nombre: " + empleado.getNombre());
					lblNewLabel_2.setText("Apellido: " + empleado.getApellido());
					lblNewLabel_3.setText("Dni: " + empleado.getDni());
					lblNewLabel_4.setText("Estado: " + Boolean.toString(empleado.getEstado()));
					
					
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
				bajaEmpleadoBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controlador.bajaLogica(empleado);
						optionPane.showMessageDialog(null, "Baja de Empleado Exitosa");
					}
				});
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
