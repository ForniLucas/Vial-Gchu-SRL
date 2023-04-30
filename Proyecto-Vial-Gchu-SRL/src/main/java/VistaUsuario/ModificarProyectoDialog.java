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

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ModificarProyectoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JOptionPane optionPane = new JOptionPane();
	private JTextField legajoTxt = new JTextField();
	private JTextField fechaDeInicioTxt = new JTextField();
	private JTextField fechaFinTxt = new JTextField();
	private JComboBox<EstadoProyecto> estadoBox= new JComboBox<EstadoProyecto>(EstadoProyecto.values());
	private JTextField nombreTxt = new JTextField();
	private JTextField descripcionTxt = new JTextField();
	private JTextField actividadesTxt = new JTextField();
	private JTextField insumosTxt = new JTextField();
	
	private String id = new String();
	
	private JComboBox<TipoDeProyecto> tipoBox = new JComboBox<TipoDeProyecto>(TipoDeProyecto.values()){
	    @Override
	    public String toString() {
	        String selectedItem = (String) getSelectedItem().toString();
	        return selectedItem.replace('_', ' ');
	    }
	};
	Proyecto proyecto = new Proyecto();
	ControladorProyecto controladorProyecto = new ControladorProyecto();

	/**
	 * Launch the application.
	 */
	
	/*
	public static void main(String[] args) {
		try {
			ModificarProyectoDialog dialog = new ModificarProyectoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} */

	/**
	 * Create the dialog.
	 */
	public ModificarProyectoDialog(ProyectoDialog dialog, String id) {
		super(dialog, "BajaProyectoDialog",true);
		this.id = id;
		
		legajoTxt.setText(id);
		
		setBounds(50, 50, 550, 550);
		this.setResizable(false);
		this.setTitle("MODIFICAR PROYECTOS");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Ingrese el Legajo:");
			lblNewLabel.setBounds(38, 43, 109, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Fecha de Inicio");
			lblNewLabel_1.setBounds(94, 112, 84, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Fecha Estimada de Fin");
			lblNewLabel_2.setBounds(64, 166, 129, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Estado");
			lblNewLabel_3.setBounds(133, 220, 45, 13);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Nombre de Proyecto");
			lblNewLabel_4.setBounds(75, 274, 129, 13);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Tipo De Proyecto");
			lblNewLabel_5.setBounds(91, 324, 102, 13);
			contentPanel.add(lblNewLabel_5);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Descripción");
			lblNewLabel_6.setBounds(107, 382, 71, 13);
			contentPanel.add(lblNewLabel_6);
		}
		
		{
			
			legajoTxt.setBounds(193, 40, 151, 19);
			contentPanel.add(legajoTxt);
			legajoTxt.setColumns(255);
		}
		{
			
			fechaDeInicioTxt.setText("dd/mm/aaaa");
			fechaDeInicioTxt.setBounds(214, 109, 96, 19);
			contentPanel.add(fechaDeInicioTxt);
			fechaDeInicioTxt.setColumns(255);
		}
		{
		//(64, 166, 129, 13)
			fechaFinTxt.setText("dd/mm/aaaa");
			fechaFinTxt.setBounds(214, 163, 96, 19);
			contentPanel.add(fechaFinTxt);
			fechaFinTxt.setColumns(255);
		}
		{
			//(133, 220, 45, 13)
			estadoBox.setBounds(214, 217, 96, 19);
		    contentPanel.add(estadoBox);
		}
		{
			
			nombreTxt.setBounds(214, 271, 247, 19);
			contentPanel.add(nombreTxt);
			nombreTxt.setColumns(255);
		}
		{
			
			descripcionTxt.setBounds(214, 379, 247, 45);
			contentPanel.add(descripcionTxt);
			descripcionTxt.setColumns(255);
		}
		//(78, 328, 100, 13)
		{
			
			tipoBox.setBounds(214, 325, 130, 21);
			contentPanel.add(tipoBox);
		}
		{
			JButton buscarBtn = new JButton("Buscar");
			buscarBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try { 
						String codigo = legajoTxt.getText(); 
						int id = Integer.parseInt(codigo);
						proyecto = controladorProyecto.buscarID(id);
						nombreTxt.setText(proyecto.getNombre());
						fechaDeInicioTxt.setText(proyecto.getFechaInicio().toString());
						fechaFinTxt.setText(proyecto.getFechaEstFin().toString());
						estadoBox.setSelectedItem(proyecto.getEstado());
						TipoProyecto tipo = (TipoProyecto)(proyecto.getTipoProyecto());
						tipoBox.setSelectedItem(tipo.getTipo());
						actividadesTxt.setText(tipo.getActividades());
						insumosTxt.setText(tipo.getInsumos());
						descripcionTxt.setText(tipo.getDescripcion());
					} catch (Exception e1) {
						optionPane.showMessageDialog(null, "Error al buscar: Valor Ingresado no valido o inexistente");
					}
					
				}
			});
			buscarBtn.setBounds(391, 39, 85, 21);
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
							String fechaInicioString = fechaDeInicioTxt.getText(); 
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
							
							TipoDeProyecto tipo = (TipoDeProyecto) tipoBox.getSelectedItem(); 
							TipoProyecto tipoProyecto = new TipoProyecto(tipo, desc, actividades, insumos);
							
							proyecto.setFechaInicio(fechaInicio);
							proyecto.setFechaEstFin(fechaFin);
							proyecto.setNombre(nombre);
							proyecto.setEstado(estado);
							proyecto.asignarTipoProyecto(tipoProyecto);
							
							controladorProyecto.modificar(proyecto);
						} catch (Exception e1) {
							optionPane.showMessageDialog(null, "Error al Realizar la operación: " + e1.getMessage());
						}
						
					}
				});
				
				JButton gestionMaqBtn = new JButton("Gestionar Maquinaria");
				gestionMaqBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						HistorialUtiliza historialUtiliza = new HistorialUtiliza(ModificarProyectoDialog.this, ModificarProyectoDialog.this.id);
						historialUtiliza.setVisible(true);
					}
				});
				buttonPane.add(gestionMaqBtn);
				
				JButton gestionEmplBtn = new JButton("Gestionar Empleados");
				gestionEmplBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						HistorialTrabaja historialTrabaja = new HistorialTrabaja(ModificarProyectoDialog.this, ModificarProyectoDialog.this.id);
						historialTrabaja.setVisible(true);
					}
				});
				buttonPane.add(gestionEmplBtn);
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
