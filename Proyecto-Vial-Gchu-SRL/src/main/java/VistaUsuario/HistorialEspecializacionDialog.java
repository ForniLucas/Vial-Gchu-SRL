package VistaUsuario;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
	 * Create the dialog.
	 */
	public HistorialEspecializacionDialog(ModificarEmpleadoDialog dialog, String dniID) {
		super(dialog, "HistorialEspecializacionDialog",true);
		this.dni = dniID;
		
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
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton modificarBtn = new JButton("Asociar Nueva");
				modificarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						EspecializacionDialog especializacionDialog = new EspecializacionDialog(HistorialEspecializacionDialog.this, dni);
						especializacionDialog.setVisible(true);
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
						EmpleadoDialog empleados = new EmpleadoDialog();
						empleados.setVisible(true);
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
		modeloEspecializacion.setRowCount(0);
		int id = Integer.parseInt(dni);
		Set<Especializacion> filasTablaRopa = controladorEmpleado.listarEspecializacion(id);
		Iterator<Especializacion> iterador = filasTablaRopa.iterator();
		while (iterador.hasNext()) {
			Especializacion esp = (Especializacion) iterador.next();
			String fila[] = {String.valueOf(esp.getId()),String.valueOf(esp.getTipo()),String.valueOf(convertirFecha(esp.getFechaActualizacion())),
					String.valueOf(esp.getRol()),String.valueOf(convertirFecha(esp.getFechaFin()))};
			modeloEspecializacion.addRow(fila);
		}
	}
	
	public String convertirFecha(LocalDate fecha) {
	    // Crear el formateador para el formato de salida
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	    // Formatear la fecha en el formato deseado "dd/MM/yyyy"
	    String fechaFormateada = fecha.format(formatter);

	    // Devolver la fecha formateada
	    return fechaFormateada;
	}
}
