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
import Domain.ElementoDeSeguridad;
import Domain.Empleado;

public class HistorialElementoDeSeguridadDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
	Empleado empleado = new Empleado();
	String id = new String();
	String nombre = new String();
	String dni = new String();
	//Tabla Principal
	String ids[] = {"Legajo","Tipo", "Fecha de Entrega"}; 
	DefaultTableModel mt = new DefaultTableModel();
	JTable table = new JTable(mt);
	JScrollPane scrollPane = new JScrollPane(); 

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			HistorialElementoDeSeguridadDialog dialog = new HistorialElementoDeSeguridadDialog();
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
	public HistorialElementoDeSeguridadDialog(ModificarEmpleadoDialog dialog, String dniid) {
		this.dni = dniid;
		
		setBounds(50, 50, 600, 600);
		setTitle("HISTORIAL DE ELEMENTO DE SEGURIDAD");
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
		
		cargarElementos();
		
		JLabel dniLbl = new JLabel("DNI: " + dni);
		dniLbl.setBounds(55, 26, 108, 13);
		contentPanel.add(dniLbl);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton modificarBtn = new JButton("Asignar Nuevo Elemento de Seguridad");
				modificarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						ElementoDeSeguridadDialog elementoDialog = new ElementoDeSeguridadDialog(HistorialElementoDeSeguridadDialog.this, dni);
						elementoDialog.setVisible(true);
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
	
	public void cargarElementos() {
		DefaultTableModel modeloTablaElemento = (DefaultTableModel) table.getModel();
		int id = Integer.parseInt(dni);
		Set<ElementoDeSeguridad> filasTablaElemento = controladorEmpleado.listarElementosDeSeguridad(id);
		Iterator<ElementoDeSeguridad> iterador = filasTablaElemento.iterator();
		while (iterador.hasNext()) {
			ElementoDeSeguridad elemento = (ElementoDeSeguridad) iterador.next();
			String fila[] = {String.valueOf(elemento.getiD()),String.valueOf(elemento.getTipo()),String.valueOf(elemento.getFechaEntrega())};
			modeloTablaElemento.addRow(fila);
		}
	}

}
