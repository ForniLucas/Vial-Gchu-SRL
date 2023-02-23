package VistaUsuario;

import java.awt.BorderLayout;
import Enumeraciones.Elemento;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ElementoDeSeguridadDialog extends JDialog {
	

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ElementoDeSeguridadDialog dialog = new ElementoDeSeguridadDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ElementoDeSeguridadDialog() {
		setBounds(50, 50, 450, 450);
		this.setResizable(false);
		this.setTitle("ELEMENTO DE SEGURIDAD");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Tipo");
			lblNewLabel.setBounds(132, 180, 45, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Fecha de Entrega");
			lblNewLabel_1.setBounds(83, 236, 94, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JComboBox tipoBox = new JComboBox(Elemento.values());
			tipoBox.setBounds(218, 176, 113, 21);
			contentPanel.add(tipoBox);
		}
		
		textField = new JTextField();
		textField.setBounds(218, 233, 113, 19);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Ingrese el DNI del Empleado");
		lblNewLabel_2.setBounds(23, 38, 142, 13);
		contentPanel.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(175, 35, 96, 19);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton buscarBtn = new JButton("Buscar");
		buscarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buscarBtn.setBounds(299, 34, 85, 21);
		contentPanel.add(buscarBtn);
		//LABEL PARA CARGAR EL NOMBRE DEL RESULTADO DE LA BUSQUEDA
		JLabel nombreLabel = new JLabel("");
		nombreLabel.setBounds(237, 101, 135, 13);
		contentPanel.add(nombreLabel);
		//LABEL PARA CARGAR EL APELLIDO DEL RESULTADO DE LA BUSQUEDA
		JLabel apellidoLabel = new JLabel("");
		apellidoLabel.setBounds(45, 101, 127, 13);
		contentPanel.add(apellidoLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
				JButton cancelBtn = new JButton("Cancelar");
				cancelBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						EmpleadoDialog empleados = new EmpleadoDialog();
						empleados.setVisible(true);
					}
				});
				cancelBtn.setActionCommand("Cancel");
				buttonPane.add(cancelBtn);
			}
		}
	}
}
