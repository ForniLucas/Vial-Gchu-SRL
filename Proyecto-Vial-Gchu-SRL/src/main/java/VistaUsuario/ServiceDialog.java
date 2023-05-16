package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.util.Set;

import Controladoras.ControladorMaquinaria;
import Domain.Empleado;
import Domain.Maquinaria;
import Domain.Service;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Console;


public class ServiceDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JOptionPane optionPane = new JOptionPane();
	private JTextField legajoTxt;
	private JTextField fechaInicioTxt;
	private JTextField fechaFinTxt;
	private JTextField observacionesTxt;
	String ids[] = {"Legajo","Fecha de Inicio","Fecha de Fin","Observaciones"}; 
	DefaultTableModel mt = new DefaultTableModel();
	JTable table = new JTable(mt);
	JScrollPane scrollPane = new JScrollPane(); 
	ControladorMaquinaria controladorMaquinaria = new ControladorMaquinaria();
	private String codigo = new String();
	private Maquinaria maquinaria = new Maquinaria(); 
	private String id = new String();
	private String fechaInicio = new String();
	private String fechaFin = new String();
	private String observaciones = new String();
	private Service service = new Service();
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			ServiceDialog dialog = new ServiceDialog();
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
	public ServiceDialog(MaquinariaDialog dialog, String codigoid) {
		super(dialog, "ServiceDialog",true);
		this.codigo = codigoid;
		
		setBounds(50, 50, 600, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//Formato de tabla
		mt.setColumnIdentifiers(ids);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = table.getSelectedRow();
		        DefaultTableModel mt = (DefaultTableModel)table.getModel();
		        id = mt.getValueAt(filaSeleccionada, 0).toString();
		        fechaInicio = mt.getValueAt(filaSeleccionada, 1).toString();
		        fechaFin = mt.getValueAt(filaSeleccionada, 2).toString();
		        observaciones = mt.getValueAt(filaSeleccionada, 3).toString();
			}
		});
		table.setBounds(26, 71, 532, 112);
		scrollPane.setBounds(26, 71, 532, 112);
		scrollPane.setViewportView(table);
		contentPanel.add(scrollPane);
		{
			JLabel lblNewLabel = new JLabel("Ingrese el Código de la Maquinaria");
			lblNewLabel.setBounds(26, 22, 173, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			legajoTxt = new JTextField();
			legajoTxt.setBounds(232, 19, 119, 19);
			contentPanel.add(legajoTxt);
			legajoTxt.setColumns(255);
			legajoTxt.setText(codigo);
			
		}
		{
			JButton buscarBtn = new JButton("Buscar");
			buscarBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						String codigo = legajoTxt.getText();
						// Verificar si el texto contiene caracteres no válidos
					    if (!codigo.matches("^[a-zA-Z0-9]*$")) {
					        optionPane.showMessageDialog(null, "El codigo ingresado contiene caracteres no válidos. Solo se permiten letras y números.");
					        return; // Salir del método sin continuar
					    }
						
						if (!codigo.isEmpty()) {
							cargarService();
						}
						
					} catch (Exception ex) {
					    optionPane.showMessageDialog(null, ex.getMessage());
					}		
				}
			});
			buscarBtn.setBounds(416, 18, 85, 21);
			contentPanel.add(buscarBtn);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Fecha de Inicio");
			lblNewLabel_1.setBounds(164, 228, 132, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Fecha de Fin");
			lblNewLabel_2.setBounds(176, 299, 120, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Observaciones");
			lblNewLabel_3.setBounds(164, 377, 132, 13);
			contentPanel.add(lblNewLabel_3);
		}
		{
			fechaInicioTxt = new JTextField();
			fechaInicioTxt.setText("dd/mm/aaaa");
			fechaInicioTxt.setBounds(306, 225, 96, 19);
			contentPanel.add(fechaInicioTxt);
			fechaInicioTxt.setColumns(10);
		}
		{
			fechaFinTxt = new JTextField();
			fechaFinTxt.setText("dd/mm/aaaa");
			fechaFinTxt.setBounds(306, 296, 96, 19);
			contentPanel.add(fechaFinTxt);
			fechaFinTxt.setColumns(10);
		}
		{
			observacionesTxt = new JTextField();
			observacionesTxt.setBounds(306, 374, 152, 19);
			contentPanel.add(observacionesTxt);
			observacionesTxt.setColumns(255);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton serviceBtn = new JButton("Añadir Service");
				serviceBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
						    maquinaria = controladorMaquinaria.buscar(codigo); 
						    String inicio = fechaInicioTxt.getText();
						    String fin = fechaFinTxt.getText();
						    String obs = observacionesTxt.getText();    
						    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						    LocalDate fechaI;
						    LocalDate fechaF;    
						    try {
						        fechaI = LocalDate.parse(inicio, formatter);
						        fechaF = LocalDate.parse(fin, formatter);
						        Service service = new Service(fechaI, fechaF, obs);
						        controladorMaquinaria.asignarService(maquinaria, service);
							    actualizarTabla();
							    cargarService();
							    System.out.println("Codigo de maquinaria heredado: "+ codigo+ "Maquinaria: "+ maquinaria.getCodigo());
						    } catch (DateTimeParseException e2) {
						        optionPane.showMessageDialog(null, "La fecha debe tener el formato dd/MM/yyyy.");
						        return;
						    }
						} catch (Exception ex) {
						    optionPane.showMessageDialog(null, "Ocurrió un error al procesar los datos: " + ex.getMessage());
						    return;
						}

						
					}
				});
				
				JButton imprimirButton = new JButton("Imprimir Planilla de Mantenimiento");
				imprimirButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						maquinaria = controladorMaquinaria.buscar(codigo);
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						LocalDate fechaI = LocalDate.parse(fechaInicio, formatter);
						LocalDate fechaF = LocalDate.parse(fechaFin, formatter);
						Service service = new Service(fechaI, fechaF, observaciones);
						controladorMaquinaria.crearPlantillaDeMantenimiento(maquinaria, service);
					}
				});
				buttonPane.add(imprimirButton);
				serviceBtn.setActionCommand("OK");
				buttonPane.add(serviceBtn);
				getRootPane().setDefaultButton(serviceBtn);
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
	
	public void cargarService() {
		DefaultTableModel modeloTablaService = (DefaultTableModel) table.getModel();
		
		Set<Service> serviciosSet = controladorMaquinaria.listarServices(codigo);
		List<Service> filasTablaService = new ArrayList<Service>();

		for (Service service : serviciosSet) {
		    filasTablaService.add(service);
		}

		Iterator <Service>iterador = filasTablaService.iterator();
			while (iterador.hasNext()) {
				Service service = (Service) iterador.next();
				String fila [] = {String.valueOf(service.getId()), String.valueOf(service.getFechaInicio()),String.valueOf(service.getFechaFin()), 
						String.valueOf(service.getObservaciones())};
				modeloTablaService.addRow(fila);
			}
	}

	public void actualizarTabla() {
		 DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		    modelo.setRowCount(0); // Limpia la tabla
	}
}


