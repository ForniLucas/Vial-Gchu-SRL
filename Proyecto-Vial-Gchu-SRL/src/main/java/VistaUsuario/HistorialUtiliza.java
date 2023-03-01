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
import Domain.Utiliza;

public class HistorialUtiliza extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String id = new String();
	ControladorProyecto controladorP = new ControladorProyecto();
	//Tabla Principal
	String ids[] = {"Fecha de Inicio", "Fecha Estimada de Fin","Fecha de Fin", "Patente","Descripcion"}; 
	DefaultTableModel mt = new DefaultTableModel();
	JTable table = new JTable(mt);
	JScrollPane scrollPane = new JScrollPane();
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
	public HistorialUtiliza(ProyectoDialog dialog, String id) {
		super(dialog, "HistorialUtiliza",true);
		this.id = id;
		
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
						setVisible(false);
					}
				});
				cancelarBtn.setHorizontalAlignment(SwingConstants.RIGHT);
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}
	//"Legajo","Fecha de Inicio", "Fecha Estimada de Fin","Fecha de Fin" "Maquinaria"
	
	public void cargarUtiliza() {
		DefaultTableModel modeloTablaUtiliza = (DefaultTableModel) table.getModel();
		Long idP = Long.parseLong(id);
		Set<Utiliza> filasUtiliza = controladorP.listarUtiliza(idP);
		Iterator<Utiliza> iterador = filasUtiliza.iterator();
		while (iterador.hasNext()) {
			Utiliza utiliza = (Utiliza) iterador.next();
			String fila[] = {String.valueOf(utiliza.getFechaInicio()),String.valueOf(utiliza.getFechaEstFin()),
					String.valueOf(utiliza.getFechaFin()),String.valueOf(utiliza.getMaquinaria().getCodigo()),String.valueOf(utiliza.getMaquinaria().getDescripcion())};
			modeloTablaUtiliza.addRow(fila);
		}
	}

}
