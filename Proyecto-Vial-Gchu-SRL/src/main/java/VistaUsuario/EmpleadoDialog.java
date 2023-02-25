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
		table.setBounds(89, 39, 1195, 600);
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = table.getSelectedRow();
		        DefaultTableModel modeloTablaEmpleado = (DefaultTableModel)table.getModel();
		        id = modeloTablaEmpleado.getValueAt(filaSeleccionada, 0).toString();
		        apellido = modeloTablaEmpleado.getValueAt(filaSeleccionada, 1).toString();
		        nombre = modeloTablaEmpleado.getValueAt(filaSeleccionada, 2).toString();
		        dni = modeloTablaEmpleado.getValueAt(filaSeleccionada, 3).toString();
		        telefono = modeloTablaEmpleado.getValueAt(filaSeleccionada, 4).toString();
		        direccion = modeloTablaEmpleado.getValueAt(filaSeleccionada, 5).toString();
		        fechaDeNacimiento = modeloTablaEmpleado.getValueAt(filaSeleccionada, 6).toString();
		        estado = modeloTablaEmpleado.getValueAt(filaSeleccionada, 7).toString();       
			}
		});
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
					JButton ropaDTButton = new JButton("Asignar Ropa de Trabajo");
					ropaDTButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							RopaDeTrabajoDialog ropaDeTrabajoDialog = new RopaDeTrabajoDialog();
							ropaDeTrabajoDialog.setVisible(true);
						}
					});
					{
						JButton hRTBtnn = new JButton("Historial Ropa de Trabajo");
						hRTBtnn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							}
						});
						{
							JButton hEspecializacionBtn = new JButton("Historial Especializacion de Empleado");
							hEspecializacionBtn.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									HistorialEspecializacionDialog historialEspecializacion = new HistorialEspecializacionDialog();
									setVisible(false);
									historialEspecializacion.setVisible(true);
									historialEspecializacion.id = id;
									historialEspecializacion.apellido = apellido;
									historialEspecializacion.nombre = nombre;
									
								}
							});
							buttonPane.add(hEspecializacionBtn);
						}
						buttonPane.add(hRTBtnn);
					}
					buttonPane.add(ropaDTButton);
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
							}
						});
						buttonPane.add(hEDSBtn);
					}
					buttonPane.add(elementoDSButton);
				}
				
				buttonPane.add(altaEmpleadoBtn);
			{
				JButton bajaEmpleadoBtn = new JButton("Baja");
				bajaEmpleadoBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						BajaEmpleadoDialog bajaEmpleadoDialog = new BajaEmpleadoDialog();
				        bajaEmpleadoDialog.setVisible(true);
				        bajaEmpleadoDialog.dni = dni;
					}
				});
				buttonPane.add(bajaEmpleadoBtn);
			}
			{
				JButton modificarEmpleadoBtn = new JButton("Modificar");
				modificarEmpleadoBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						ModificarEmpleadoDialog modificarEmpleadoDialog = new ModificarEmpleadoDialog();
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
