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
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class RopaDeTrabajoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField fechaTxt;
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
	 * Create the dialog.
	 */
	public RopaDeTrabajoDialog(HistorialRopaDeTrabajoDialog dialog, String dniid) {
		super(dialog, "RopaDeTrabajoDialog",true);
		this.dni = dniid;
		setBounds(50, 50, 450, 450);
		this.setResizable(false);
		this.setTitle("ROPA DE TRABAJO");
		this.toFront();
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Tipo");
			lblNewLabel.setBounds(146, 85, 80, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Talle");
			lblNewLabel_1.setBounds(146, 183, 70, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Fecha de Entrega");
			lblNewLabel_2.setBounds(76, 281, 140, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			
			comboBoxRopa.setBounds(226, 82, 100, 21);
			contentPanel.add(comboBoxRopa);
		}
		
		
		comboBox.setBounds(226, 179, 48, 21);
		contentPanel.add(comboBox);
		{
			fechaTxt = new JTextField();
			fechaTxt.setText("dd/mm/aaaa");
			fechaTxt.setBounds(226, 278, 96, 19);
			contentPanel.add(fechaTxt);
			fechaTxt.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("DNI: "+ dni );
			lblNewLabel_3.setBounds(153, 25, 149, 13);
			contentPanel.add(lblNewLabel_3);
		}
	
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton guardarBtn = new JButton("Guardar");
				guardarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						empleado = controlador.buscarDNI(Integer.parseInt(dni));
						try {
							if (empleado != null) {
								String fechaEtnregaString = fechaTxt.getText(); 
								
								boolean control = validarDatos(fechaEtnregaString);
								
								if (control) {
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
									optionPane.showMessageDialog(null,"Operación exitosa");
									setVisible(false);
									EmpleadoDialog empleados = new EmpleadoDialog();
									empleados.setVisible(true);
								}
								
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
	
	public boolean validarDatos(String fechaString) {
		boolean resultado = true; 
		try {
	        LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    } catch (DateTimeParseException e) {
	        JOptionPane.showMessageDialog(null, "Ingrese una fecha válida (formato: dd/MM/yyyy).");
	        resultado = false;
	    }
		
		return resultado;
	}
}
