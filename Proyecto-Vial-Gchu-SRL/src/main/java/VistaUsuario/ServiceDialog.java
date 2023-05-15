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

import Controladoras.ControladorMaquinaria;
import Domain.Maquinaria;
import Domain.Service;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ServiceDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
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
		table.setBounds(26, 71, 532, 112);
		scrollPane.setBounds(26, 71, 532, 112);
		scrollPane.setViewportView(table);
		contentPanel.add(scrollPane);
		{
			JLabel lblNewLabel = new JLabel("Ingrese el Legajo de la Maquinaria");
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
					maquinaria = controladorMaquinaria.buscar(codigo);
					cargarService();
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
						//control TRY CATCH, si hay algo en los TxtField debería pasar lo de abajo, si no hay nada, error y no se añade nada
						String inicio = fechaInicioTxt.getText();
						String fin = fechaFinTxt.getText();
						String obs = observacionesTxt.getText();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						LocalDate fechaIni = LocalDate.parse(inicio, formatter);
						LocalDate fechaFin = LocalDate.parse(fin, formatter);
						
						
						
						Service service = new Service(fechaIni,fechaFin, obs);
						
						controladorMaquinaria.asignarService(maquinaria, service);
						actualizarTabla();
					}
				});
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
		Maquinaria maquinaria = new Maquinaria();
		maquinaria = controladorMaquinaria.buscar(codigo);
		Iterator <Service>iterador = maquinaria.getServices().iterator();
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
		    cargarService();
	}
}


