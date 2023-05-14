package VistaUsuario;

import java.awt.BorderLayout;

import Enumeraciones.EstadoProyecto;
import Enumeraciones.TipoDeProyecto;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladoras.ControladorProyecto;
import Domain.Proyecto;
import Domain.TipoProyecto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class AltaProyectoDialog extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	JOptionPane optionPane = new JOptionPane();
	private JTextField fechaInicioTxt;
	private JTextField fechaFinTxt;
	private JComboBox<EstadoProyecto> estadoBox= new JComboBox<EstadoProyecto>(EstadoProyecto.values());
	private JTextField nombreTxt;
	private JTextField descripcionTxt;
	private JTextField actividadesTxt;
	private JTextField insumosTxt;
	private ControladorProyecto controlador = new ControladorProyecto();
	private JComboBox<TipoDeProyecto> comboBox = new JComboBox<TipoDeProyecto>(TipoDeProyecto.values()){
	
		@Override
	    public String toString() {
	        String selectedItem = (String) getSelectedItem().toString();
	        return selectedItem.replace('_', ' ');
	    }
	};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AltaProyectoDialog dialog = new AltaProyectoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AltaProyectoDialog() {
		
		fechaInicioTxt = new JTextField();
		fechaInicioTxt.setText("dd/mm/aaaa");
		fechaFinTxt = new JTextField();
		nombreTxt = new JTextField();
		descripcionTxt = new JTextField();
		actividadesTxt = new JTextField();
		insumosTxt = new JTextField();
		
		setBounds(50, 50, 500, 550);
		this.setResizable(false);
		this.setTitle("ALTA DE PROYECTOS");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Fecha de Inicio");
			lblNewLabel.setBounds(121, 42, 120, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Fecha Estimada de Fin");
			lblNewLabel_1.setBounds(82, 97, 174, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Estado");
			lblNewLabel_2.setBounds(152, 152, 120, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Nombre de Proyecto");
			lblNewLabel_3.setBounds(89, 207, 124, 13);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Tipo de Proyecto");
			lblNewLabel_4.setBounds(105, 262, 120, 13);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Descripción");
			lblNewLabel_5.setBounds(129, 427, 120, 13);
			contentPanel.add(lblNewLabel_5);
		}

		{
				
			fechaInicioTxt.setBounds(226, 43, 96, 19);
			contentPanel.add(fechaInicioTxt);
			fechaInicioTxt.setColumns(10);
		}
		{
		//82, 97, 134, 13
			fechaFinTxt.setText("dd/mm/aaaa");
			fechaFinTxt.setBounds(226, 94, 96, 19);
			contentPanel.add(fechaFinTxt);
			fechaFinTxt.setColumns(10);
		}
		{
		//152, 152, 64, 13
			estadoBox.setBounds(226, 149, 96, 19);
			contentPanel.add(estadoBox);
		}
		{
			
			nombreTxt.setBounds(226, 204, 196, 19);
			contentPanel.add(nombreTxt);
			nombreTxt.setColumns(255);
		}
		{
			
			descripcionTxt.setBounds(226, 413, 223, 42);
			contentPanel.add(descripcionTxt);
			descripcionTxt.setColumns(255);
		}
		{
			actividadesTxt.setBounds(226, 314, 196, 19);
			contentPanel.add(actividadesTxt);
			actividadesTxt.setColumns(225);
		}
		{
			insumosTxt.setBounds(226, 369, 196, 19);
			contentPanel.add(insumosTxt);
			insumosTxt.setColumns(225);
		}
		
		JLabel actLbl = new JLabel("Actividades");
		actLbl.setBounds(129, 317, 120, 13);
		contentPanel.add(actLbl);
		{
			JLabel insLb = new JLabel("Insumos");
			insLb.setBounds(143, 372, 120, 13);
			contentPanel.add(insLb);
		}

		{
			//105, 262, 111, 13
			comboBox.setBounds(226, 259, 150, 21);
			contentPanel.add(comboBox);
		
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton guardarBtn = new JButton("Guardar");
				guardarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String fechaInicioString = fechaInicioTxt.getText(); 
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						LocalDate fechaInicio = LocalDate.parse(fechaInicioString, formatter);
						
						String fechaFinString = fechaFinTxt.getText(); 
						DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
						LocalDate fechaFin = LocalDate.parse(fechaFinString, formatter2);
						
						String nombre = nombreTxt.getText();
						String desc = descripcionTxt.getText();
						String actividades = actividadesTxt.getText(); 
						String insumos = insumosTxt.getText();
						EstadoProyecto estado = (EstadoProyecto) estadoBox.getSelectedItem();
						
						TipoDeProyecto tipo = (TipoDeProyecto) comboBox.getSelectedItem(); 
						TipoProyecto tipoProyecto = new TipoProyecto(tipo, desc, actividades, insumos);
								
						Proyecto proyecto = new Proyecto();
						
						proyecto.setFechaInicio(fechaInicio);
						proyecto.setFechaEstFin(fechaFin);
						proyecto.setFechaFin(fechaFin);
						proyecto.setNombre(nombre);
						proyecto.setEstado(estado);
						proyecto.asignarTipoProyecto(tipoProyecto);
						
						controlador.alta(proyecto);
						optionPane.showMessageDialog(null,"Datos modificados con éxito");
						
						
					}
				});
				guardarBtn.setActionCommand("Guardar");
				buttonPane.add(guardarBtn);
				getRootPane().setDefaultButton(guardarBtn);
			}
			{
				JButton cancelarBtn = new JButton("Cancelar");
				cancelarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						ProyectoDialog proyectoDialog = new ProyectoDialog();
						proyectoDialog.setVisible(true);
					}
				});
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}
}
