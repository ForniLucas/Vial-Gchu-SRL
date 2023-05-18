package VistaUsuario;

import java.awt.BorderLayout;
import Enumeraciones.Elemento;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladoras.ControladorEmpleado;
import Domain.ElementoDeSeguridad;
import Domain.Empleado;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ElementoDeSeguridadDialog extends JDialog {
	

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private String dni = new String();
	JOptionPane optionPane = new JOptionPane();
	private ControladorEmpleado controlador = new ControladorEmpleado();
	private Empleado empleado = new Empleado();
	private JLabel nombreLabel = new JLabel("");
	private JLabel apellidoLabel = new JLabel("");
	private JComboBox<Elemento> tipoBox = new JComboBox<Elemento>(Elemento.values()){
		@Override
	    public String toString() {
	        String selectedItem = (String) getSelectedItem().toString();
	        return selectedItem.replace('_', ' ');
	    }
	};
	/**
	 * Create the dialog.
	 */
	public ElementoDeSeguridadDialog(HistorialElementoDeSeguridadDialog dialog, String dniid) {
		super(dialog, "ElementoDeSeguridadDialog",true);
		this.dni = dniid;
		
		setBounds(50, 50, 500, 500);
		this.setResizable(false);
		this.setTitle("ELEMENTO DE SEGURIDAD");
		this.toFront();
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Tipo");
			lblNewLabel.setBounds(132, 180, 111, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Fecha de Entrega");
			lblNewLabel_1.setBounds(83, 236, 160, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			
			tipoBox.setBounds(218, 176, 153, 21);
			contentPanel.add(tipoBox);
	
		}
		
		textField = new JTextField("dd/mm/aaaa");
		textField.setBounds(218, 233, 113, 19);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewDNI = new JLabel("DNI: "+ dni);
		lblNewDNI.setBounds(142, 64, 189, 13);
		contentPanel.add(lblNewDNI);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton guardarBtn = new JButton("Guardar");
				guardarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							empleado = controlador.buscarDNI(Integer.parseInt(dni));
							String fechaEtnregaString = textField.getText(); 
							
							boolean control = validarDatos(fechaEtnregaString);
							if (control) {
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
								LocalDate fechaEtnrega = LocalDate.parse(fechaEtnregaString, formatter);
							
								Elemento elemento = (Elemento) tipoBox.getSelectedItem();
							
								ElementoDeSeguridad elementoSeguridad = new ElementoDeSeguridad();
							
								elementoSeguridad.setEmpleado(empleado);
								elementoSeguridad.setTipo(elemento);
								elementoSeguridad.setFechaEntrega(fechaEtnrega);

								controlador.asignarElementoDeSeguridad(empleado, elementoSeguridad);
								optionPane.showMessageDialog(null,"Operación exitosa");
								setVisible(false);
								EmpleadoDialog empleados = new EmpleadoDialog();
								empleados.setVisible(true);
							}
							
						}catch (Exception e4) {
								optionPane.showMessageDialog(null,"Ocurrió un error al procesar los datos: " + e4.getMessage());
								return;
						}
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
	
	
	public boolean validarDatos(String fechaEntre) {
	    boolean resultado = true;
	    
	    // Validar formato de fecha de entrega
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    try {
	        LocalDate fechaEntrega = LocalDate.parse(fechaEntre, formatter);
	        
	        // Validar fecha de entrega en relación a la fecha actual
	        if (fechaEntrega.isBefore(LocalDate.now())) {
	            JOptionPane.showMessageDialog(null, "La fecha de entrega debe ser posterior a la fecha actual.");
	            resultado = false;
	        }
	    } catch (DateTimeParseException e) {
	        JOptionPane.showMessageDialog(null, "Ingrese una fecha de entrega válida en formato dd/MM/yyyy.");
	        resultado = false;
	    }
	    
	    return resultado;
	}
}
