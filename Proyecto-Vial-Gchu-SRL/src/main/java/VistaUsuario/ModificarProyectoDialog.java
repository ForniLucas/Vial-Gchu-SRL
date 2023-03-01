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
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ModificarProyectoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
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
		
		setBounds(50, 50, 600, 700);
		this.setResizable(false);
		this.setTitle("MODIFICAR PROYECTOS");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Ingrese el Legajo del Proyecto");
			lblNewLabel.setBounds(37, 51, 140, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Fecha de Inicio");
			lblNewLabel_1.setBounds(170, 122, 84, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Fecha Estimada de Fin");
			lblNewLabel_2.setBounds(140, 176, 114, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Estado");
			lblNewLabel_3.setBounds(209, 230, 45, 13);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Nombre de Proyecto");
			lblNewLabel_4.setBounds(151, 284, 103, 13);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Tipo De Proyecto");
			lblNewLabel_5.setBounds(154, 338, 100, 13);
			contentPanel.add(lblNewLabel_5);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Descripción");
			lblNewLabel_6.setBounds(183, 392, 71, 13);
			contentPanel.add(lblNewLabel_6);
		}
		{
			JLabel lblNewLabel_7 = new JLabel("Actividades");
			lblNewLabel_7.setBounds(183, 446, 71, 13);
			contentPanel.add(lblNewLabel_7);
		}
		{
			JLabel lblNewLabel_8 = new JLabel("Insumos");
			lblNewLabel_8.setBounds(196, 500, 58, 13);
			contentPanel.add(lblNewLabel_8);
		}
		{
			
			legajoTxt.setBounds(220, 48, 151, 19);
			contentPanel.add(legajoTxt);
			legajoTxt.setColumns(255);
		}
		{
			
			fechaDeInicioTxt.setText("dd/mm/aaaa");
			fechaDeInicioTxt.setBounds(264, 122, 96, 19);
			contentPanel.add(fechaDeInicioTxt);
			fechaDeInicioTxt.setColumns(255);
		}
		{
		
			fechaFinTxt.setText("dd/mm/aaaa");
			fechaFinTxt.setBounds(264, 176, 96, 19);
			contentPanel.add(fechaFinTxt);
			fechaFinTxt.setColumns(255);
		}
		{
			
			estadoBox.setBounds(264, 230, 96, 19);
		    contentPanel.add(estadoBox);
		}
		{
			
			nombreTxt.setBounds(264, 284, 189, 19);
			contentPanel.add(nombreTxt);
			nombreTxt.setColumns(255);
		}
		{
			
			descripcionTxt.setBounds(264, 392, 189, 19);
			contentPanel.add(descripcionTxt);
			descripcionTxt.setColumns(255);
		}
		{
			
			actividadesTxt.setBounds(264, 446, 189, 19);
			contentPanel.add(actividadesTxt);
			actividadesTxt.setColumns(255);
		}
		{
			
			insumosTxt.setBounds(264, 500, 189, 19);
			contentPanel.add(insumosTxt);
			insumosTxt.setColumns(255);
		}
		{
			
			tipoBox.setBounds(264, 337, 114, 21);
			contentPanel.add(tipoBox);
		}
		{
			JButton buscarBtn = new JButton("Buscar");
			buscarBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

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
				}
			});
			buscarBtn.setBounds(418, 47, 85, 21);
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
