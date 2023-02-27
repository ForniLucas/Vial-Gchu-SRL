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

import Controladoras.ControladorEmpleado;
import Domain.Empleado;
import Domain.Especializacion;

public class HistorialEspecializacionDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
	String apellido = new String();
	String nombre = new String();
	String dni = new String();
	//Tabla Principal
	String ids[] = {"Legajo","Tipo", "Fecha de Actualizacion","Rol", "Fecha de Fin"}; 
	DefaultTableModel mt = new DefaultTableModel();
	JTable table = new JTable(mt);
	JScrollPane scrollPane = new JScrollPane(); 

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			HistorialEspecializacionDialog dialog = new HistorialEspecializacionDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public HistorialEspecializacionDialog(EmpleadoDialog dialog, String dni, String apellido, String nombre) {
		super(dialog, "HistorialEspecializacionDialog",true);
		this.dni = dni;
		this.apellido = apellido;
		this.nombre = nombre;
		
		setBounds(50, 50, 600, 600);
		setTitle("HISTORIAL DE ESPECIALIZACIONES");
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
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		cargarEspecializacion();
		JLabel dniLbl = new JLabel("DNI: " + dni);
		dniLbl.setBounds(55, 26, 108, 13);
		contentPanel.add(dniLbl);
		
		JLabel apellidoLbl = new JLabel("Apellido: "+apellido);
		apellidoLbl.setBounds(173, 26, 147, 13);
		contentPanel.add(apellidoLbl);
		
		JLabel nombreLbl = new JLabel("Nombre: "+nombre);
		nombreLbl.setBounds(330, 26, 212, 13);
		contentPanel.add(nombreLbl);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton modificarBtn = new JButton("Modificar Empleado");
				modificarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						EmpleadoDialog empleadoDialog = new EmpleadoDialog();
						empleadoDialog.setVisible(true);
						}
				});
				modificarBtn.setActionCommand("OK");
				buttonPane.add(modificarBtn);
				getRootPane().setDefaultButton(modificarBtn);
			}
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
	//"Legajo","Tipo", "Fecha de Actualizacion","Rol", "Fecha de Fin"
	public void cargarEspecializacion() {
		DefaultTableModel modeloEspecializacion= (DefaultTableModel) table.getModel();
		int id = Integer.parseInt(dni);
		Set<Especializacion> filasTablaRopa = controladorEmpleado.listarEspecializacion(id);
		Iterator<Especializacion> iterador = filasTablaRopa.iterator();
		while (iterador.hasNext()) {
			Especializacion esp = (Especializacion) iterador.next();
			String fila[] = {String.valueOf(esp.getId()),String.valueOf(esp.getTipo()),String.valueOf(esp.getFechaActualizacion()),
					String.valueOf(esp.getRol()),String.valueOf(esp.getFechaFin())};
			modeloEspecializacion.addRow(fila);
		}
	}
}
