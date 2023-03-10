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
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class AltaProyectoDialog extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
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
			lblNewLabel.setBounds(75, 36, 95, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Fecha Estimada de Fin");
			lblNewLabel_1.setBounds(36, 91, 134, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Estado");
			lblNewLabel_2.setBounds(106, 146, 64, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Nombre de Proyecto");
			lblNewLabel_3.setBounds(43, 201, 124, 13);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Tipo de Proyecto");
			lblNewLabel_4.setBounds(56, 256, 111, 13);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Descripci??n");
			lblNewLabel_5.setBounds(83, 313, 87, 13);
			contentPanel.add(lblNewLabel_5);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Actividades");
			lblNewLabel_6.setBounds(90, 366, 77, 13);
			contentPanel.add(lblNewLabel_6);
		}
		{
			JLabel lblNewLabel_7 = new JLabel("Insumos");
			lblNewLabel_7.setBounds(106, 421, 64, 13);
			contentPanel.add(lblNewLabel_7);
		}
		{
			
			
			fechaInicioTxt.setBounds(190, 30, 96, 19);
			contentPanel.add(fechaInicioTxt);
			fechaInicioTxt.setColumns(10);
		}
		{
			
			fechaFinTxt.setText("dd-mm-aaaa");
			fechaFinTxt.setBounds(190, 85, 96, 19);
			contentPanel.add(fechaFinTxt);
			fechaFinTxt.setColumns(10);
		}
		{
			
			estadoBox.setBounds(190, 140, 96, 19);
			contentPanel.add(estadoBox);
		}
		{
			
			nombreTxt.setBounds(190, 195, 196, 19);
			contentPanel.add(nombreTxt);
			nombreTxt.setColumns(255);
		}
		{
			
			descripcionTxt.setBounds(190, 307, 223, 19);
			contentPanel.add(descripcionTxt);
			descripcionTxt.setColumns(255);
		}
		{
			
			actividadesTxt.setBounds(190, 360, 223, 19);
			contentPanel.add(actividadesTxt);
			actividadesTxt.setColumns(255);
		}
		{
			
			insumosTxt.setBounds(190, 415, 223, 19);
			contentPanel.add(insumosTxt);
			insumosTxt.setColumns(255);
		}
		{
			
			comboBox.setBounds(190, 249, 130, 21);
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
