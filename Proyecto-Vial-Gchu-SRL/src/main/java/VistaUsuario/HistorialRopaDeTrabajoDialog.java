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

import Controladoras.ControladorEmpleado;
import Domain.Empleado;
import Domain.RopaDeTrabajo;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class HistorialRopaDeTrabajoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
	String dni = new String();
	//Tabla Principal
	String ids[] = {"Legajo","Tipo", "Talle","Fecha de Entrega"}; 
	DefaultTableModel mt = new DefaultTableModel();
	JTable table = new JTable(mt);
	JScrollPane scrollPane = new JScrollPane(); 

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			HistorialRopaDeTrabajoDialog dialog = new HistorialRopaDeTrabajoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HistorialRopaDeTrabajoDialog(ModificarEmpleadoDialog dialog, String dni) {
		super(dialog, "HistorialRopaDeTrabajoDialog",true);
		this.dni = dni;

		setBounds(50, 50, 600, 600);
		setTitle("HISTORIAL DE ROPA DE TRABAJO");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		//Formato de tabla
		mt.setColumnIdentifiers(ids);
		contentPanel.setLayout(null);
		table.setBounds(89, 39, 500, 500);
		scrollPane.setBounds(42, 55, 500, 455);
		scrollPane.setViewportView(table);
		contentPanel.add(scrollPane);
		
		cargarRopa();
		
		JLabel dniLbl = new JLabel("DNI: " + dni);
		dniLbl.setBounds(55, 26, 108, 13);
		contentPanel.add(dniLbl);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton asginarBtn = new JButton("Asignar Nueva Ropa De Trabajo");
				asginarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						RopaDeTrabajoDialog ropaDeTrabajoDialog = new RopaDeTrabajoDialog(HistorialRopaDeTrabajoDialog.this, HistorialRopaDeTrabajoDialog.this.dni);
						ropaDeTrabajoDialog.setVisible(true);					
						}
				});
				asginarBtn.setActionCommand("OK");
				buttonPane.add(asginarBtn);
				getRootPane().setDefaultButton(asginarBtn);
			}
			{
				JButton cancelarBtn = new JButton("Cancelar");
				cancelarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EmpleadoDialog empleados = new EmpleadoDialog();
						empleados.setVisible(true);
						setVisible(false);
					}
				});
				cancelarBtn.setHorizontalAlignment(SwingConstants.RIGHT);
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}
	//"Legajo","Tipo", "Talle","Fecha de Entrega"
	public void cargarRopa() {
		DefaultTableModel modeloTablaRopa = (DefaultTableModel) table.getModel();
		int id = Integer.parseInt(dni);
		Set<RopaDeTrabajo> filasTablaRopa = controladorEmpleado.listarRopaDeTrabajo(id);
		Iterator<RopaDeTrabajo> iterador = filasTablaRopa.iterator();
		while (iterador.hasNext()) {
			RopaDeTrabajo ropa = (RopaDeTrabajo) iterador.next();
			String fila[] = {String.valueOf(ropa.getId()),String.valueOf(ropa.getTipo()),String.valueOf(ropa.getTalle()),
					String.valueOf(ropa.getFechaEntrega())};
			modeloTablaRopa.addRow(fila);
		}
	}
}
