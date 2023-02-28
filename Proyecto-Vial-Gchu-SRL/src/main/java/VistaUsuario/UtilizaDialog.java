package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladoras.ControladorMaquinaria;
import Controladoras.ControladorProyecto;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UtilizaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String idproyecto = new String();
	String codigo = new String();
	JTextField fechaInicioTxt = new JTextField();;
	JTextField fechaEstimadaTxt = new JTextField();;
	JTextField fechaFinTxt = new JTextField();;
	MaquinariaDialog dialog2 = new MaquinariaDialog();
	ControladorMaquinaria controladorM = new ControladorMaquinaria();
	ControladorProyecto controladorP = new ControladorProyecto();
	JTextField codigoTxt = new JTextField();
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			UtilizaDialog dialog = new UtilizaDialog();
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
	public UtilizaDialog(ProyectoDialog dialog1, String idproyecto, String codigo) {
		super(dialog1, "UtilizaDialog",true);
		this.idproyecto = idproyecto;
		this.codigo = codigo;
		this.dialog2 = dialog2;
		
		
		
		setBounds(50,50, 550, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel proyectoidLbl = new JLabel("Proyecto: ");
		proyectoidLbl.setBounds(54, 42, 221, 13);
		contentPanel.add(proyectoidLbl);
		
		JLabel codigoidLbl = new JLabel("Ingrese una patente: ");
		codigoidLbl.setBounds(54, 97, 109, 13);
		contentPanel.add(codigoidLbl);
		
		JLabel fechaInicioLbl = new JLabel("Fecha de Inicio:");
		fechaInicioLbl.setBounds(131, 262, 125, 13);
		contentPanel.add(fechaInicioLbl);
		
		JLabel lblNewLabel = new JLabel("Fecha Estimada de Fin");
		lblNewLabel.setBounds(131, 317, 125, 13);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha de Fin:");
		lblNewLabel_1.setBounds(131, 372, 125, 13);
		contentPanel.add(lblNewLabel_1);
		
		
		fechaInicioTxt.setText("dd-mm-aaaa");
		fechaInicioTxt.setBounds(286, 259, 109, 19);
		contentPanel.add(fechaInicioTxt);
		fechaInicioTxt.setColumns(10);
		
		fechaEstimadaTxt.setText("dd-mm-aaaa");
		fechaEstimadaTxt.setBounds(286, 314, 109, 19);
		contentPanel.add(fechaEstimadaTxt);
		fechaEstimadaTxt.setColumns(10);
		
		fechaFinTxt.setText("dd-mm-aaaa");
		fechaFinTxt.setBounds(286, 369, 109, 19);
		contentPanel.add(fechaFinTxt);
		fechaFinTxt.setColumns(10);
		
		codigoTxt = new JTextField();
		codigoTxt.setBounds(219, 94, 116, 19);
		contentPanel.add(codigoTxt);
		codigoTxt.setColumns(10);
		
		JButton buscarBtn = new JButton("Buscar");
		buscarBtn.setBounds(391, 93, 85, 21);
		contentPanel.add(buscarBtn);
		
		JLabel descripcionLbl = new JLabel("Descripción:");
		descripcionLbl.setBounds(54, 152, 221, 13);
		contentPanel.add(descripcionLbl);
		
		JLabel fabricanteLbl = new JLabel("Fabricante: ");
		fabricanteLbl.setBounds(54, 207, 139, 13);
		contentPanel.add(fabricanteLbl);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton asignarBtn = new JButton("Asignar");
				asignarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				asignarBtn.setActionCommand("OK");
				buttonPane.add(asignarBtn);
				getRootPane().setDefaultButton(asignarBtn);
			}
			{
				JButton cancelarBtn = new JButton("Cancelar");
				cancelarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}
}
