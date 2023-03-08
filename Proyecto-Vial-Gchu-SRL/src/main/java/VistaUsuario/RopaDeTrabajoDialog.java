package VistaUsuario;

import java.awt.BorderLayout;

import Enumeraciones.Ropa;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladoras.ControladorEmpleado;
import Domain.Empleado;
import Domain.RopaDeTrabajo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class RopaDeTrabajoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField fechaTxt;
	private JTextField textField;
	JOptionPane optionPane = new JOptionPane();
	private ControladorEmpleado controlador = new ControladorEmpleado();
	private Empleado empleado = new Empleado();
	private JLabel apellidoLbl = new JLabel("");
	private JLabel nombreLbl = new JLabel("");
	
	private String dni = new String();
	
	private String[] talles = {"1","2","3","4","5"};
	private JComboBox comboBox = new JComboBox(talles);
	private JComboBox<Ropa> comboBoxRopa = new JComboBox<Ropa>(Ropa.values()){
		@Override
	    public String toString() {
	        String selectedItem = (String) getSelectedItem().toString();
	        return selectedItem.replace('_', ' ');
	    }
	};

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			RopaDeTrabajoDialog dialog = new RopaDeTrabajoDialog();
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
	public RopaDeTrabajoDialog(HistorialRopaDeTrabajoDialog dialog, String dni) {
		super(dialog, "RopaDeTrabajoDialog",true);
		this.dni = dni;
		
		textField.setText(dni);
		
		setBounds(50, 50, 450, 450);
		this.setResizable(false);
		this.setTitle("ROPA DE TRABAJO");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Tipo");
			lblNewLabel.setBounds(143, 196, 30, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Talle");
			lblNewLabel_1.setBounds(143, 257, 30, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Fecha de Entrega");
			lblNewLabel_2.setBounds(73, 311, 100, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			
			comboBoxRopa.setBounds(228, 192, 100, 21);
			contentPanel.add(comboBoxRopa);
		}
		
		
		comboBox.setBounds(228, 253, 48, 21);
		contentPanel.add(comboBox);
		{
			fechaTxt = new JTextField();
			fechaTxt.setText("dd/mm/aaaa");
			fechaTxt.setBounds(232, 308, 96, 19);
			contentPanel.add(fechaTxt);
			fechaTxt.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Ingrese el DNI del Empleado");
			lblNewLabel_3.setBounds(10, 22, 149, 13);
			contentPanel.add(lblNewLabel_3);
		}
		{
			textField = new JTextField();
			textField.setBounds(169, 19, 107, 19);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JButton buscarBtn = new JButton("Buscar");
			buscarBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String dniString = textField.getText(); 
						int dni = Integer.parseInt(dniString); 
						empleado = controlador.buscarDNI(dni);
						apellidoLbl.setText("Apellido: " + empleado.getApellido());
						nombreLbl.setText("Nombre: " + empleado.getNombre());
					} catch (Exception e1) {
						optionPane.showMessageDialog(null, "Error al buscar: Valor Ingresado no valido o inexistente");
					}
					
				}
			});
			buscarBtn.setBounds(311, 18, 85, 21);
			contentPanel.add(buscarBtn);
		}
		{
			//LABEL PARA CARGAR APELLIDO RESULTADO DE LA BUSQUEDA
			
			apellidoLbl.setBounds(57, 102, 137, 13);
			contentPanel.add(apellidoLbl);
		}
		{
			//LABEL PARA CARGAR EL NOMBRE RESIULTADO DE LA BUSQUEDA
			
			nombreLbl.setBounds(240, 102, 137, 13);
			contentPanel.add(nombreLbl);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton guardarBtn = new JButton("Guardar");
				guardarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if (empleado.getApellido() != null) {
								String fechaEtnregaString = fechaTxt.getText(); 
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
								LocalDate fechaEtnrega = LocalDate.parse(fechaEtnregaString, formatter); 
								
								String talle = (String) comboBox.getSelectedItem();
								
								Ropa ropa = (Ropa) comboBoxRopa.getSelectedItem();
								
								RopaDeTrabajo ropaTrabajo = new RopaDeTrabajo();
								ropaTrabajo.setEmpleado(empleado);
								ropaTrabajo.setTipo(ropa);
								ropaTrabajo.setTalle(talle);
								ropaTrabajo.setFechaEntrega(fechaEtnrega);
								
								controlador.asignarRopaDeTrabajo(empleado, ropaTrabajo);
							}else {
								optionPane.showMessageDialog(null, "Debe buscar un Empleado primero.");
							}
						} catch (Exception e1) {
							optionPane.showMessageDialog(null, "Error al Guardar: " + e1.getMessage());
						}
						
						
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
