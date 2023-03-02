package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Domain.Empleado;
import Controladoras.ControladorEmpleado;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmpleadoDialog extends JDialog {
	private final JPanel contentPanel = new JPanel();
	//private JTable table;
	ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
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
			EmpleadoDialog dialog = new EmpleadoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Create the dialog.
	 */
	public EmpleadoDialog() {
		setBounds(50, 50, 1390, 750);
		setResizable(false);
		this.setTitle("GESTIÓN DE EMPLEADOS");
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
		        apellido = mt.getValueAt(filaSeleccionada, 1).toString();
		        nombre = mt.getValueAt(filaSeleccionada, 2).toString();
		        dni = mt.getValueAt(filaSeleccionada, 3).toString();
		        telefono = mt.getValueAt(filaSeleccionada, 4).toString();
		        direccion = mt.getValueAt(filaSeleccionada, 5).toString();
		        fechaDeNacimiento = mt.getValueAt(filaSeleccionada, 6).toString();
		        estado = mt.getValueAt(filaSeleccionada, 7).toString();       
			}
		});
		table.setBounds(89, 39, 1195, 600);
		
		scrollPane.setBounds(89, 39, 1195, 600);
		scrollPane.setViewportView(table);
		contentPanel.add(scrollPane);
		//CARGAR EMPLEADOS ESTA ABAJO
		cargarEmpleados();
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton altaEmpleadoBtn = new JButton("Alta");
				altaEmpleadoBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						AltaEmpleadoDialog altaEmpleadoDialog = new AltaEmpleadoDialog();
					    altaEmpleadoDialog.setVisible(true);}
				});

					{
						JButton hRTBtn = new JButton("Gestionar ropa de Trabajo");
						hRTBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								setVisible(false);
								HistorialRopaDeTrabajoDialog historialRopa = new HistorialRopaDeTrabajoDialog(EmpleadoDialog.this, dni, apellido, nombre);
								historialRopa.setVisible(true);
							}
						});
						{
							JButton hEspecializacionBtn = new JButton("Historial Especializacion de Empleado");
							hEspecializacionBtn.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									setVisible(false);
									HistorialEspecializacionDialog historialEspecializacion = new HistorialEspecializacionDialog(EmpleadoDialog.this, dni, apellido, nombre);
									historialEspecializacion.setVisible(true);									
								}
							});
							buttonPane.add(hEspecializacionBtn);
						}
						buttonPane.add(hRTBtn);
					}
				//	buttonPane.add(ropaDTButton);
				}
				{
					JButton elementoDSButton = new JButton("Asignar Elemento de Seguridad");
					elementoDSButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ElementoDeSeguridadDialog elementoDeSeguridadDialog = new ElementoDeSeguridadDialog();
							elementoDeSeguridadDialog.setVisible(true);
						}
					});
					{
						JButton hEDSBtn = new JButton("Historial Elemento de Seguridad");
						hEDSBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								setVisible(false);
								HistorialElementoDeSeguridadDialog historialElementoDeSeguridad = new HistorialElementoDeSeguridadDialog(EmpleadoDialog.this, dni, apellido, nombre);
								historialElementoDeSeguridad.setVisible(true);
							}
						});
						buttonPane.add(hEDSBtn);
					}
					buttonPane.add(elementoDSButton);
				}
				
			//	buttonPane.add(altaEmpleadoBtn);
			{
				JButton bajaEmpleadoBtn = new JButton("Baja");
				bajaEmpleadoBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						BajaEmpleadoDialog bajaEmpleadoDialog = new BajaEmpleadoDialog(EmpleadoDialog.this, dni);
				        bajaEmpleadoDialog.setVisible(true);
					}
				});
				buttonPane.add(bajaEmpleadoBtn);
			}
			{
				JButton modificarEmpleadoBtn = new JButton("Modificar");
				modificarEmpleadoBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						ModificarEmpleadoDialog modificarEmpleadoDialog = new ModificarEmpleadoDialog(EmpleadoDialog.this, dni);
				        modificarEmpleadoDialog.setVisible(true);
					}
				});
				
				buttonPane.add(modificarEmpleadoBtn);
			}
			{
				JButton cancelarBtn = new JButton("Cancelar");
				cancelarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
			

	
	
	public void cargarEmpleados(){
		DefaultTableModel modeloTablaEmpleado = (DefaultTableModel) table.getModel();
		List<Empleado> filasTablaEmpleado = controladorEmpleado.listarEmpleados();
		Iterator<Empleado> iterador = filasTablaEmpleado.iterator();
		while (iterador.hasNext()) {
			Empleado empleado = (Empleado) iterador.next();
			String estado = empleado.getEstado()? "Activo" : "Inactivo";
			String fila[] = {String.valueOf(empleado.getId()),String.valueOf(empleado.getApellido()),String.valueOf(empleado.getNombre()),
					String.valueOf(empleado.getDni()),String.valueOf(empleado.getTelefono()),String.valueOf(empleado.getDireccion()),
					String.valueOf(empleado.getFechaNac()),String.valueOf(estado)};
			modeloTablaEmpleado.addRow(fila);
		}
		
	}
	
}
