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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BajaMaquinariaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	ControladorMaquinaria controlador = new ControladorMaquinaria();
	Maquinaria maquina = new Maquinaria();
	JLabel lblNewLabel_1 = new JLabel("");
	JLabel lblNewLabel_2 = new JLabel("");
	JLabel lblNewLabel_3 = new JLabel("");
	JLabel lblNewLabel_4 = new JLabel("");
	JLabel lblNewLabel_5 = new JLabel("");
	JOptionPane optionPane = new JOptionPane();
	String codigo = new String();
	JTextField legajoTxt = new JTextField();
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			BajaMaquinariaDialog dialog = new BajaMaquinariaDialog();
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
	public BajaMaquinariaDialog(MaquinariaDialog dialog, String codigo) {
		super(dialog, "BajaEmpleadoDialog",true);
		this.codigo = codigo;
		
		
		legajoTxt.setText(codigo);
		
		lblNewLabel_1.setVisible(false);
		lblNewLabel_2.setVisible(false);
		lblNewLabel_3.setVisible(false);
		lblNewLabel_4.setVisible(false);
		
		
		setBounds(50, 50, 500, 300);
		this.setResizable(false);
		this.setTitle("BAJA DE MAQUINARIA");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Ingrese Codigo de la Maquinaria");
			lblNewLabel.setBounds(20, 31, 246, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			
			legajoTxt.setBounds(213, 28, 116, 19);
			contentPanel.add(legajoTxt);
			legajoTxt.setColumns(255);
		}
		{
			
			lblNewLabel_1.setBounds(263, 92, 174, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			
			lblNewLabel_2.setBounds(49, 161, 163, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			
			lblNewLabel_3.setBounds(263, 161, 174, 13);
			contentPanel.add(lblNewLabel_3);
		}
		{
			
			lblNewLabel_4.setBounds(49, 92, 163, 13); //VER
			contentPanel.add(lblNewLabel_4);
		}
		{
			JButton buscarBtn = new JButton("Buscar");
			buscarBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String codigo = legajoTxt.getText();
						
						maquina = controlador.buscar(codigo);
						lblNewLabel_1.setVisible(true);
						lblNewLabel_2.setVisible(true);
						lblNewLabel_3.setVisible(true);
						lblNewLabel_4.setVisible(true);
						
						String estadoString = maquina.getEstado() ? "En servicio" : "Fuera de Servicio";
						lblNewLabel_1.setText("Código: " + maquina.getCodigo());
						lblNewLabel_2.setText("Descripción: " + maquina.getDescripcion());
						lblNewLabel_3.setText("Fabricante: " + maquina.getFabricante());
						lblNewLabel_4.setText("Estado: " + estadoString);
					} catch (Exception e1) {
						optionPane.showMessageDialog(null, "Error al buscar: Valor Ingresado no valido o inexistente");
					}
					
					
				}
			});
			buscarBtn.setBounds(373, 27, 85, 21);
			contentPanel.add(buscarBtn);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton bajaBtn = new JButton("Dar de Baja");
				bajaBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if (maquina.getCodigo() != null) {
								if (controlador.buscar(maquina.getCodigo()).getEstado()) {
									controlador.bajaLogica(maquina);
									optionPane.showMessageDialog(null, "Baja Exitosa");
								}else {
									optionPane.showMessageDialog(null, "La Maquina ya esta dado de baja");
								}
							}else {
								optionPane.showMessageDialog(null, "Debe buscar una Maquina primero.");
							}
							
						} catch(Exception e1) {
							optionPane.showMessageDialog(null, "Error al dar de baja la Maquinaria: " + e1.getMessage());
						}
						
					}
				});
				bajaBtn.setActionCommand("OK");
				buttonPane.add(bajaBtn);
				getRootPane().setDefaultButton(bajaBtn);
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

}
