package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladoras.ControladorMaquinaria;
import Domain.Maquinaria;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaMaquinariaDialog extends JDialog {

	ControladorMaquinaria controlador = new ControladorMaquinaria();
	private final JPanel contentPanel = new JPanel();
	private JTextField codigoTxt;
	private JTextField descripcionTxt;
	private JTextField fabricanteTxt;
	private JTextField ubicacionTxt;
	JOptionPane optionPane = new JOptionPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AltaMaquinariaDialog dialog = new AltaMaquinariaDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AltaMaquinariaDialog() {
		setBounds(50, 50, 600, 700);
		this.setResizable(false);
		this.setTitle("ALTA DE MAQUINARIA");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Código");
			lblNewLabel.setBounds(202, 76, 86, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Descripción");
			lblNewLabel_1.setBounds(182, 180, 106, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Fabricante");
			lblNewLabel_2.setBounds(187, 287, 81, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Ubicación de Almacenamiento");
			lblNewLabel_3.setBounds(103, 394, 240, 13);
			contentPanel.add(lblNewLabel_3);
		}

		{
			codigoTxt = new JTextField();
			codigoTxt.setBounds(298, 73, 96, 19);
			contentPanel.add(codigoTxt);
			codigoTxt.setColumns(255);
		}
		{
			descripcionTxt = new JTextField();
			descripcionTxt.setBounds(298, 177, 137, 19);
			contentPanel.add(descripcionTxt);
			descripcionTxt.setColumns(255);
		}
		{
			fabricanteTxt = new JTextField();
			fabricanteTxt.setBounds(298, 284, 96, 19);
			contentPanel.add(fabricanteTxt);
			fabricanteTxt.setColumns(255);
		}
		{
			ubicacionTxt = new JTextField();
			ubicacionTxt.setBounds(298, 391, 137, 19);
			contentPanel.add(ubicacionTxt);
			ubicacionTxt.setColumns(255);
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
						    String desc = descripcionTxt.getText();
						    String fabr = fabricanteTxt.getText();
						    String ubic = ubicacionTxt.getText();
						    
						    if (codigo.trim().isEmpty() || desc.trim().isEmpty() || fabr.trim().isEmpty() || ubic.trim().isEmpty()) {
						        JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
						        return;
						    }
						    boolean control = validarDatos( codigo,  desc,  fabr,  ubic);
						    if (control) {
						    	controlador.alta(codigo, desc, fabr, ubic);
							    JOptionPane.showMessageDialog(null, "Datos almacenados con éxito");
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
		if (!(maquina == null)) {
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
