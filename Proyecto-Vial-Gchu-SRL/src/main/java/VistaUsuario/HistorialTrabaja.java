package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controladoras.ControladorProyecto;
import Domain.Trabajo;
import Domain.Utiliza;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HistorialTrabaja extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String id = new String();
	ControladorProyecto controladorP = new ControladorProyecto();
	//Tabla Principal
	String ids[] = {"DNI","Horas Trabajadas","Fecha de Inicio","Fecha Estimada de Fin","Fecha de Fin"}; 
	DefaultTableModel mt = new DefaultTableModel();
	JTable table = new JTable(mt);
	JScrollPane scrollPane = new JScrollPane();
	String dni = new String();
	String horasTrabajadas = new String();
	String fechaInicio = new String();
	String fechaEstimadaFin = new String();
	String fechaFin = new String();
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			HistorialTrabaja dialog = new HistorialTrabaja();
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
	public HistorialTrabaja(ModificarProyectoDialog dialog, String idc) {
		super(dialog, "HistorialUtiliza",true);
		this.id = idc;
		
		setBounds(50, 50, 600, 600);
		this.setResizable(false);
		this.setTitle("HISTORIAL DE EMPELADOS POR PROYECTO");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		//Formato de tabla
		mt.setColumnIdentifiers(ids);
		contentPanel.setLayout(null);
		contentPanel.setLayout(null);
		table.setBounds(89, 39, 500, 500);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = table.getSelectedRow();
		        DefaultTableModel mt = (DefaultTableModel)table.getModel();
		        dni = mt.getValueAt(filaSeleccionada, 0).toString();
		        horasTrabajadas = mt.getValueAt(filaSeleccionada, 1).toString();
		        fechaInicio = mt.getValueAt(filaSeleccionada, 2).toString();
		        fechaEstimadaFin = mt.getValueAt(filaSeleccionada, 3).toString();
		        fechaFin = mt.getValueAt(filaSeleccionada, 4).toString();
			}
		});
		scrollPane.setBounds(42, 55, 500, 455);
		scrollPane.setViewportView(table);
		contentPanel.add(scrollPane);
		cargarTrabajo();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel dniLbl = new JLabel("ID de proyecto" + id);
		dniLbl.setBounds(55, 26, 200, 13);
		contentPanel.add(dniLbl);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelarBtn = new JButton("Cancelar");
				cancelarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				
				JButton asignarBtn = new JButton("Asociar Nuevo");
				asignarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TrabajaDialog trabaja = new TrabajaDialog(HistorialTrabaja.this, id);
						setVisible(false);
						trabaja.setVisible(true);
					}
				});
				
				JButton btnImprimir = new JButton("Imprimir Plantilla");
				btnImprimir.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
						LocalDate fechaI = LocalDate.parse(fechaInicio, formatter);
						LocalDate fechaE = LocalDate.parse(fechaEstimadaFin, formatter);
						Trabajo trabajo = new Trabajo(Integer.parseInt(horasTrabajadas), fechaI, fechaE);
						controladorP.crearPlantillaDeTrabajo(trabajo);
					}
				});
				buttonPane.add(btnImprimir);
				buttonPane.add(asignarBtn);
				
				JButton btnDesasociar = new JButton("Desasociar");
				btnDesasociar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
						LocalDate fechaI = LocalDate.parse(fechaInicio, formatter);
						LocalDate fechaE = LocalDate.parse(fechaEstimadaFin, formatter);
						Trabajo trabajo = new Trabajo(Integer.parseInt(horasTrabajadas), fechaI, fechaE);
						controladorP.desasociarTrabajo(trabajo);
					}
				});
				buttonPane.add(btnDesasociar);
				cancelarBtn.setHorizontalAlignment(SwingConstants.RIGHT);
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}
	//"DNI","Horas Trabajadas""Fecha de Inicio", "Fecha Estimada de Fin","Fecha de Fin" 
	
	public void cargarTrabajo() {
		DefaultTableModel modeloTablaTrabaja = (DefaultTableModel) table.getModel();
		Long idP = Long.parseLong(id);
		Set<Trabajo> filasTrabaja = controladorP.listarTrabaja(idP);
		Iterator<Trabajo> iterador = filasTrabaja.iterator();
		while (iterador.hasNext()) {
			Trabajo trabajo = (Trabajo) iterador.next();
			String fila[] = {String.valueOf(trabajo.getEmpleado().getDni()),String.valueOf(trabajo.getHorasDeTrabajo()),String.valueOf(trabajo.getFechaInicio()),
					String.valueOf(trabajo.getFechaEstFin()),String.valueOf(trabajo.getFechaFin())};
			modeloTablaTrabaja.addRow(fila);
		}
	}
}
