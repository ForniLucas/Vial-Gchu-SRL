package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controladoras.ControladorEmpleado;
import Controladoras.ControladorMaquinaria;
import Controladoras.ControladorProyecto;
import Domain.Empleado;
import Domain.Maquinaria;
import Domain.Proyecto;

public class TrabajaDialog extends JDialog {
	JTextField fechaInicioTxt = new JTextField();;
	JTextField fechaEstimadaTxt = new JTextField();;
	JTextField fechaFinTxt = new JTextField();
	JTextField horasTrabajadas = new JTextField();
	MaquinariaDialog dialog2 = new MaquinariaDialog();
	ControladorEmpleado controladorE = new ControladorEmpleado();
	ControladorProyecto controladorP = new ControladorProyecto();
	JTextField dniTxt = new JTextField();
	Empleado empleado = new Empleado();
	JLabel apellidoLbl = new JLabel("Apellido: ");
	JLabel nombreLbl = new JLabel("Nombre: ");
	Proyecto proyecto = new Proyecto();
	JOptionPane optionPane = new JOptionPane();
	private final JPanel contentPanel = new JPanel();
	String idproyecto = new String();
	String dni = new String();
	String apellido = new String();
	String nombre = new String();
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			TrabajaDialog dialog = new TrabajaDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	/**
	 * Create the dialog.
	 */
	public TrabajaDialog(HistorialTrabaja dialog1, String idproyecto, String dni) {
		super(dialog1, "UtilizaDialog",true);
		this.idproyecto = idproyecto;
		this.dni = dni;
		
		this.setResizable(false);
		this.setTitle("ASIGNAR EMPLEADOS AL PROYECTO");
		setBounds(50,50, 550, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel proyectoidLbl = new JLabel("Proyecto: "+ idproyecto);
		proyectoidLbl.setBounds(54, 42, 221, 13);
		contentPanel.add(proyectoidLbl);
		
		JLabel codigoidLbl = new JLabel("Ingrese un DNI: ");
		codigoidLbl.setBounds(54, 97, 109, 13);
		contentPanel.add(codigoidLbl);
		
		JLabel fechaInicioLbl = new JLabel("Fecha de Inicio:");
		fechaInicioLbl.setBounds(127, 261, 139, 13);
		contentPanel.add(fechaInicioLbl);
		
		JLabel lblNewLabel = new JLabel("Fecha Estimada de Fin");
		lblNewLabel.setBounds(127, 303, 139, 13);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha de Fin:");
		lblNewLabel_1.setBounds(127, 346, 139, 13);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Horas Trabajadas:");
		lblNewLabel_2.setBounds(127, 393, 109, 16);
		contentPanel.add(lblNewLabel_2);
		fechaInicioTxt.setBounds(276, 258, 109, 19);
		
		fechaInicioTxt.setText("dd-mm-aaaa");
		contentPanel.add(fechaInicioTxt);
		fechaInicioTxt.setColumns(255);
		fechaEstimadaTxt.setBounds(276, 300, 109, 19);
		
		fechaEstimadaTxt.setText("dd-mm-aaaa");
		contentPanel.add(fechaEstimadaTxt);
		fechaEstimadaTxt.setColumns(255);
		fechaFinTxt.setBounds(276, 343, 109, 19);
		
		fechaFinTxt.setText("dd-mm-aaaa");
		contentPanel.add(fechaFinTxt);
		fechaFinTxt.setColumns(255);
		horasTrabajadas.setBounds(276, 392, 109, 20);
		
		horasTrabajadas.setText("");
		contentPanel.add(horasTrabajadas);
		horasTrabajadas.setColumns(255);
		
		dniTxt = new JTextField();
		dniTxt.setBounds(219, 94, 116, 19);
		contentPanel.add(dniTxt);
		dniTxt.setColumns(255);

		JButton buscarBtn = new JButton("Buscar");
		buscarBtn.setBounds(391, 93, 85, 21);
		buscarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				empleado = controladorE.buscarDNI(Integer.parseInt(dniTxt.getText()));
				apellido = empleado.getApellido();
				nombre = empleado.getNombre();
				apellidoLbl.setText("Apellido: "+ apellido);
				nombreLbl.setText("Nombre: "+ nombre);
			}
		});
		contentPanel.add(buscarBtn);
		apellidoLbl.setBounds(54, 152, 221, 13);
		contentPanel.add(apellidoLbl);
		nombreLbl.setBounds(54, 207, 139, 13);
		contentPanel.add(nombreLbl);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Asignar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (empleado.getApellido()!= null) {
							controladorP.asignarTrabajo(empleado, proyecto); //
							optionPane.showMessageDialog(null, "Empleado asignado exitosamente.");
						}
						else {optionPane.showMessageDialog(null, "Debe buscar un empleado primero.");}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
