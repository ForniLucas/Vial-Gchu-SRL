package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladoras.ControladorMaquinaria;
import Controladoras.ControladorProyecto;
import Domain.Maquinaria;
import Domain.Proyecto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class UtilizaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String idproyecto = new String();
	String codigo = new String();
	String fabricante = new String();
	String descripcion = new String();
	JTextField fechaInicioTxt = new JTextField();;
	JTextField fechaEstimadaTxt = new JTextField();;
	MaquinariaDialog dialog2 = new MaquinariaDialog();
	ControladorMaquinaria controladorM = new ControladorMaquinaria();
	ControladorProyecto controladorP = new ControladorProyecto();
	JTextField codigoTxt = new JTextField();
	Maquinaria maquinaria = new Maquinaria();
	JLabel descripcionLbl = new JLabel("Descripción: ");
	JLabel fabricanteLbl = new JLabel("Fabricante: ");
	Proyecto proyecto = new Proyecto();
	JOptionPane optionPane = new JOptionPane();
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
	public UtilizaDialog(HistorialUtiliza dialog1, String idproyecto) {
		super(dialog1, "UtilizaDialog",true);
		this.idproyecto = idproyecto;
		
		
		codigoTxt.setText(codigo);
		proyecto = controladorP.buscarID(Integer.parseInt(idproyecto));
		
		this.setResizable(false);
		this.setTitle("ASOCIAR MAQUINARIA AL PROYECTO");
		setBounds(50,50, 550, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel proyectoidLbl = new JLabel("Proyecto: "+ idproyecto);
		proyectoidLbl.setBounds(54, 42, 221, 13);
		contentPanel.add(proyectoidLbl);
		
		JLabel codigoidLbl = new JLabel("Ingrese un código: ");
		codigoidLbl.setBounds(54, 97, 109, 13);
		contentPanel.add(codigoidLbl);
		
		JLabel fechaInicioLbl = new JLabel("Fecha de Inicio:");
		fechaInicioLbl.setBounds(131, 262, 125, 13);
		contentPanel.add(fechaInicioLbl);
		
		JLabel lblNewLabel = new JLabel("Fecha Estimada de Fin");
		lblNewLabel.setBounds(131, 317, 185, 13);
		contentPanel.add(lblNewLabel);
		
		
		fechaInicioTxt.setText("dd-mm-aaaa");
		fechaInicioTxt.setBounds(286, 259, 109, 19);
		fechaInicioTxt.setText("dd/mm/aaaa");
		fechaInicioTxt.setBounds(286, 259, 109, 19);
		contentPanel.add(fechaInicioTxt);
		fechaInicioTxt.setColumns(10);
		
		fechaEstimadaTxt.setText("dd/mm/aaaa");
		fechaEstimadaTxt.setBounds(286, 314, 109, 19);
		contentPanel.add(fechaEstimadaTxt);
		fechaEstimadaTxt.setColumns(10);
		
		
		codigoTxt = new JTextField();
		codigoTxt.setBounds(219, 94, 116, 19);
		contentPanel.add(codigoTxt);
		codigoTxt.setColumns(10);
		
		JButton buscarBtn = new JButton("Buscar");
		buscarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maquinaria = controladorM.buscar(codigoTxt.getText());
				descripcion = maquinaria.getDescripcion();
				fabricante = maquinaria.getFabricante();
				
				descripcionLbl.setText("Descripcion: "+ descripcion);
				fabricanteLbl.setText("Fabricante: "+ fabricante);
			}
		});
		buscarBtn.setBounds(391, 93, 85, 21);
		contentPanel.add(buscarBtn);
		
		
		descripcionLbl.setBounds(54, 152, 221, 13);
		contentPanel.add(descripcionLbl);
		
		
		fabricanteLbl.setBounds(54, 207, 139, 13);
		contentPanel.add(fabricanteLbl);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton asignarBtn = new JButton("Guardar");
				asignarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String fechaInicioString = fechaInicioTxt.getText(); 
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
						LocalDate fechaInicio = LocalDate.parse(fechaInicioString, formatter);
						
						String fechaEstimadaString = fechaEstimadaTxt.getText(); 
						LocalDate fechaEstimada = LocalDate.parse(fechaEstimadaString, formatter);
						
						if (maquinaria.getCodigo() !=  null)
						{
							
							controladorP.asignarUtiliza(maquinaria, proyecto, fechaInicio, fechaEstimada);
						
						//donde se le pasan los datos que cargamos?
						  optionPane.showMessageDialog(null, "Maquinaria asignada exitosamente.");
						} else {
							 optionPane.showMessageDialog(null, "Debe buscar una maquinaria primero.");
						}
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
						setVisible(false);
					}
				});
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}
}
