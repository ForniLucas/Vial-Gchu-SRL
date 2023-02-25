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
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HistorialRopaDeTrabajoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
	Empleado empleado = new Empleado();
	String id = new String();
	String apellido = new String();
	String nombre = new String();
	String dni = new String();
	String telefono = new String();
	String direccion = new String();
	String fechaDeNacimiento = new String();
	String estado = new String();
	//Tabla Principal
	String ids[] = {"Legajo","Apellido", "Nombre","DNI", "Teléfono","Dirección", "Fecha De Nacimiento","Estado"}; 
	DefaultTableModel mt = new DefaultTableModel();
	JTable table = new JTable(mt);
	JScrollPane scrollPane = new JScrollPane(); 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	public HistorialRopaDeTrabajoDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton asginarBtn = new JButton("Asignar");
				asginarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
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
						setVisible(false);
					}
				});
				cancelarBtn.setHorizontalAlignment(SwingConstants.RIGHT);
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}

}
