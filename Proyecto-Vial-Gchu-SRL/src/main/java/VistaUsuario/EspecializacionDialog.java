package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladoras.ControladorEmpleado;
import Domain.Empleado;
import Enumeraciones.Profesion;
import Enumeraciones.RolEmpleado;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EspecializacionDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField dniTxt;
	private String dni;
	private JComboBox espComboBox = new JComboBox(Profesion.values());
	private JComboBox rolComboBox = new JComboBox(RolEmpleado.values());
	private ControladorEmpleado controlador = new ControladorEmpleado();
	private Empleado empleado = new Empleado();
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			EspecializacionDialog dialog = new EspecializacionDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
|*/
	/**
	 * Create the dialog.
	 */
	public EspecializacionDialog(HistorialEspecializacionDialog dialog, String dni) {
		super(dialog, "EspecializacionDialog",true);
		
		this.dni = dni;
		
		dniTxt.setText(dni);
		
		setBounds(50, 50, 450, 360);
		this.setResizable(false);
		this.setTitle("ROPA DE TRABAJO");
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DNI");
		lblNewLabel.setBounds(139, 48, 29, 16);
		contentPanel.add(lblNewLabel);
		{
			dniTxt = new JTextField();
			dniTxt.setBounds(189, 47, 96, 19);
			contentPanel.add(dniTxt);
			dniTxt.setColumns(250);
			dniTxt.setEnabled(false);
		}
		{
			JLabel tipoLbl = new JLabel("Especializacion:");
			tipoLbl.setBounds(96, 109, 72, 13);
			contentPanel.add(tipoLbl);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Rol:");
			lblNewLabel_1.setBounds(150, 164, 18, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			
			espComboBox.setBounds(189, 105, 96, 21);
			contentPanel.add(espComboBox);
		}
		{
			
			rolComboBox.setBounds(189, 160, 96, 21);
			contentPanel.add(rolComboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton guardarBtn = new JButton("Guardar");
				guardarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						empleado = controlador.buscarDNI(Integer.parseInt(dniTxt.getText()));
						//controlador.asignarEspecializacion(empleado, null);
					}
				});
				guardarBtn.setActionCommand("OK");
				buttonPane.add(guardarBtn);
				getRootPane().setDefaultButton(guardarBtn);
			}
			{
				JButton cancelarBtn = new JButton("Cancelar");
				cancelarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}
}