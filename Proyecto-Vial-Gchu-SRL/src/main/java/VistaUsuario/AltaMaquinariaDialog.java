package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladoras.ControladorMaquinaria;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaMaquinariaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField codigoTxt;
	private JTextField descripcionTxt;
	private JTextField fabricanteTxt;
	private JTextField ubicacionTxt;

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
			lblNewLabel.setBounds(200, 74, 86, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Descripción");
			lblNewLabel_1.setBounds(180, 178, 106, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Fabricante");
			lblNewLabel_2.setBounds(185, 285, 81, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Ubicación de Almacenamiento");
			lblNewLabel_3.setBounds(101, 392, 240, 13);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Estado");
			lblNewLabel_4.setBounds(203, 499, 83, 13);
			contentPanel.add(lblNewLabel_4);
		}
		{
			codigoTxt = new JTextField();
			codigoTxt.setBounds(276, 71, 96, 19);
			contentPanel.add(codigoTxt);
			codigoTxt.setColumns(255);
		}
		{
			descripcionTxt = new JTextField();
			descripcionTxt.setBounds(276, 175, 137, 19);
			contentPanel.add(descripcionTxt);
			descripcionTxt.setColumns(255);
		}
		{
			fabricanteTxt = new JTextField();
			fabricanteTxt.setBounds(276, 282, 96, 19);
			contentPanel.add(fabricanteTxt);
			fabricanteTxt.setColumns(255);
		}
		{
			ubicacionTxt = new JTextField();
			ubicacionTxt.setBounds(276, 389, 137, 19);
			contentPanel.add(ubicacionTxt);
			ubicacionTxt.setColumns(255);
		}
		{
			JComboBox estadoBox = new JComboBox();
			estadoBox.setBounds(276, 495, 111, 21);
			contentPanel.add(estadoBox);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton guardarBtn = new JButton("Guardar");
				guardarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						ControladorMaquinaria controlador = new ControladorMaquinaria();
						
						String codigo = codigoTxt.getText();
						String desc = descripcionTxt.getText();
						String fabr = fabricanteTxt.getText();
						String ubic = ubicacionTxt.getText();
						
						controlador.alta(codigo, desc, fabr, ubic);
						
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

}
