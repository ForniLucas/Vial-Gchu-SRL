package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladoras.ControladorMaquinaria;
import Domain.Maquinaria;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ModificarMaquinariaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JOptionPane optionPane = new JOptionPane();
	JTextField legajoTxt = new JTextField();
	JTextField codigoTxt = new JTextField();
	JTextField descripcionTxt = new JTextField();
	JTextField fabricanteTxt = new JTextField();
	JTextField ubicacionTxt = new JTextField();
	private JComboBox<String> estadoBox = new JComboBox<String>();
	private ControladorMaquinaria controlador = new ControladorMaquinaria();
	private Maquinaria maquina = new Maquinaria();
	String codigo = new String();
	
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			ModificarMaquinariaDialog dialog = new ModificarMaquinariaDialog();
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
	public ModificarMaquinariaDialog(MaquinariaDialog dialog, String codigoid) {
		super(dialog, "BajaEmpleadoDialog",true);
		this.codigo = codigoid;
		
		
		
		setBounds(50, 50, 600, 700);
		this.setResizable(false);
		this.setTitle("MODIFICAR MAQUINARIA");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Ingresar el código de la Maquinaria");
			lblNewLabel.setBounds(40, 54, 212, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Código");
			lblNewLabel_1.setBounds(186, 135, 96, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Descripción");
			lblNewLabel_2.setBounds(163, 264, 119, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Fabricante");
			lblNewLabel_3.setBounds(163, 393, 119, 13);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Ubicación de Almacenamiento");
			lblNewLabel_4.setBounds(84, 519, 198, 13);
			contentPanel.add(lblNewLabel_4);
		}
		/*{
			JLabel lblNewLabel_5 = new JLabel("Estado");
			lblNewLabel_5.setBounds(220, 514, 75, 13);
			contentPanel.add(lblNewLabel_5);
		}*/
		{
			legajoTxt = new JTextField();
			legajoTxt.setBounds(250, 51, 96, 19);
			contentPanel.add(legajoTxt);
			legajoTxt.setColumns(255);
			legajoTxt.setText(codigo);
		}
		{
			
			codigoTxt.setBounds(299, 132, 96, 19);
			contentPanel.add(codigoTxt);
			codigoTxt.setColumns(255);
		}
		{
			
			descripcionTxt.setBounds(299, 261, 152, 19);
			contentPanel.add(descripcionTxt);
			descripcionTxt.setColumns(255);
		}
		{
			
			fabricanteTxt.setBounds(299, 390, 96, 19);
			contentPanel.add(fabricanteTxt);
			fabricanteTxt.setColumns(255);
		}
		{
			
			ubicacionTxt.setBounds(299, 516, 152, 19);
			contentPanel.add(ubicacionTxt);
			ubicacionTxt.setColumns(255);
		}
		/*{
			
			estadoBox.setBounds(305, 510, 119, 21);
			estadoBox.addItem("En servicio");
		    estadoBox.addItem("Fuera de Servicio");
			contentPanel.add(estadoBox);
		}*/
		{
			JButton buscarBtn = new JButton("Buscar");
			buscarBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String codigo = legajoTxt.getText(); 
						maquina = controlador.buscar(codigo);
						String estadoString = maquina.getEstado() ? "En servicio" : "Fuera de Servicio";
						estadoBox.setSelectedItem(estadoString);
						codigoTxt.setText(codigo);
						descripcionTxt.setText(maquina.getDescripcion());
						fabricanteTxt.setText(maquina.getFabricante());
						ubicacionTxt.setText(maquina.getUbicacionAlmacenamiento());
					} catch (Exception e1) {
						optionPane.showMessageDialog(null, "Error al buscar: Valor Ingresado no valido o inexistente");
					}
				}
			});
			buscarBtn.setBounds(416, 50, 85, 21);
			contentPanel.add(buscarBtn);
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
							String codigo = codigoTxt.getText();
							String descripcion = descripcionTxt.getText();
							String fabricante = fabricanteTxt.getText();
							String ubicacion = ubicacionTxt.getText();
							
							if (codigo.trim().isEmpty() || descripcion.trim().isEmpty() || fabricante.trim().isEmpty() || ubicacion.trim().isEmpty()) {
						        JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
						        return;
						    }
							
							boolean control = validarDatos( codigo,  descripcion,  fabricante,  ubicacion);
						    
							if (control) { 
						    	maquina.setCodigo(codigo);
								maquina.setDescripcion(descripcion);
								maquina.setFabricante(fabricante);
								maquina.setUbicacionAlmacenamiento(ubicacion);
								controlador.modificar(maquina);
								optionPane.showMessageDialog(null,"Datos modificados con éxito");
								setVisible(false);
								MaquinariaDialog maquinariaDialog = new MaquinariaDialog();
								maquinariaDialog.setVisible(true);
						    }
							
							
						} catch (Exception e1) {
						    JOptionPane.showMessageDialog(null, "Ocurrió un error al procesar los datos: " + e1.getMessage());
						    return;
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
						MaquinariaDialog maquinariaDialog = new MaquinariaDialog();
						maquinariaDialog.setVisible(true);
					}
				});
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}
	

public boolean validarDatos(String codigo, String desc, String fabr, String ubic) {
		
		boolean resultado = true;
		
		// Validar código
		if (!codigo.matches("^[A-Z0-9]{1,10}$")) {
		    // Mensaje de error si el código contiene caracteres no permitidos o excede los 10 caracteres
		    JOptionPane.showMessageDialog(null, "Ingrese un código válido (solo letras mayúsculas y números, hasta 10 caracteres).");
		    resultado = false;
		}

		// Validar descripción
		if (!desc.matches("[a-zA-Z0-9 ]{1,50}")) {
		    // Mensaje de error si la descripción contiene caracteres no permitidos o excede los 50 caracteres
		    JOptionPane.showMessageDialog(null, "Ingrese una descripción válida (solo letras, números y espacios, hasta 50 caracteres).");
		    resultado = false;
		}
		
		//Validar la no existencia de un Codigo similar

		Maquinaria maquina = controlador.buscar(codigo);
		if (!(maquina.getCodigo() == null)) {
			JOptionPane.showMessageDialog(null, "Ya existe una maquina con el mismo codigo.");
			resultado = false;
		}
		
		// Validar fabricante
		if (!fabr.matches("[a-zA-Z]{1,30}")) {
		    // Mensaje de error si el fabricante no contiene solo letras o excede los 30 caracteres
		    JOptionPane.showMessageDialog(null, "Ingrese un fabricante válido (solo letras y hasta 30 caracteres).");
		    resultado = false;
		}
		
		// Validar ubicación
		if (!ubic.matches("[a-zA-Z0-9 ]{1,30}")) {
		    // Mensaje de error si la ubicación contiene caracteres no permitidos o excede los 30 caracteres
		    JOptionPane.showMessageDialog(null, "Ingrese una ubicación válida (solo letras, números y espacios, hasta 30 caracteres).");
		    resultado = false;
		}

		return resultado;

	}

}
