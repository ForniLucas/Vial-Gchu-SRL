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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controladoras.ControladorMaquinaria;
import Controladoras.ControladorProyecto;
import Domain.Utiliza;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HistorialUtiliza extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String id = new String();
	String idUtiliza = new String();
	JOptionPane optionPane = new JOptionPane();
	private ControladorProyecto controladorP = new ControladorProyecto();
	//Tabla Principal
	private String ids[] = {"Legajo","Fecha de Inicio", "Fecha de Fin", "Código"}; 
	private DefaultTableModel mt = new DefaultTableModel();
	private JTable table = new JTable(mt);
	private JScrollPane scrollPane = new JScrollPane();
	private final JButton asignarMaquinariaBtn = new JButton("Asociar Nueva");
	private String codigo = new String();
	private String fechaInicio = new String();
	private String fechaEstimada = new String();
	private String fechaFin = new String();
	private final JButton desasociarBtn = new JButton("Desasociar");
	private ControladorMaquinaria controaldorMaquinaria = new ControladorMaquinaria();
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			HistorialUtiliza dialog = new HistorialUtiliza();
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
	public HistorialUtiliza(ModificarProyectoDialog dialog, String idc) {
		super(dialog, "HistorialUtiliza",true);
		this.id = idc;
		
		setBounds(50, 50, 600, 600);
		this.setResizable(false);
		this.setTitle("HISTORIAL DE MAQUINARIA UTILIZADA POR PROYECTO");
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
		        idUtiliza = mt.getValueAt(filaSeleccionada, 0).toString();
		        fechaInicio = mt.getValueAt(filaSeleccionada, 1).toString();
		        fechaEstimada = mt.getValueAt(filaSeleccionada, 2).toString();
		        fechaFin = mt.getValueAt(filaSeleccionada, 3).toString();
		        codigo = mt.getValueAt(filaSeleccionada, 4).toString();
			}
		});
		scrollPane.setBounds(42, 55, 500, 455);
		scrollPane.setViewportView(table);
		contentPanel.add(scrollPane);
		cargarUtiliza();
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
						ProyectoDialog proyectoD = new ProyectoDialog();
						proyectoD.setVisible(true);
						setVisible(false);	
					}
				});
				asignarMaquinariaBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						UtilizaDialog utiliza = new UtilizaDialog(HistorialUtiliza.this, id);
						setVisible(false);
						utiliza.setVisible(true);
					}
				});
				
				buttonPane.add(asignarMaquinariaBtn);
				desasociarBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						try {
						Utiliza utiliza =controladorP.buscarUtiliza(Long.parseLong(idUtiliza));
						controladorP.desasociarUtiliza(utiliza);
						optionPane.showMessageDialog(null, "Operación exitosa");
						cargarUtiliza();
						}	
						catch (Exception ex) {
							optionPane.showMessageDialog(null, "Debe seleccionar una maquinaria");
					}
					}
				});
				
				buttonPane.add(desasociarBtn);
				cancelarBtn.setHorizontalAlignment(SwingConstants.RIGHT);
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}
	//"Legajo","Fecha de Inicio", "Fecha Estimada de Fin","Fecha de Fin" "Maquinaria"
	
	public void cargarUtiliza() {
		DefaultTableModel modeloTablaUtiliza = (DefaultTableModel) table.getModel();
		modeloTablaUtiliza.setRowCount(0);
		Long idP = Long.parseLong(id);
		Set<Utiliza> filasUtiliza = controladorP.listarUtiliza(idP);
		Iterator<Utiliza> iterador = filasUtiliza.iterator();
		while (iterador.hasNext()) {
			Utiliza utiliza = (Utiliza) iterador.next();
			String fila[] = {String.valueOf(utiliza.getId()),String.valueOf(utiliza.getFechaInicio()),String.valueOf(utiliza.getFechaEstFin()),
					String.valueOf(utiliza.getMaquinaria().getCodigo())};
			modeloTablaUtiliza.addRow(fila);
		}
	}

}
