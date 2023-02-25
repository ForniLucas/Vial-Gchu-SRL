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

import Domain.Proyecto;
import Controladoras.ControladorProyecto;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;

public class ProyectoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	//Tabla Principal2
	String ids[] = {"Legajo","Nombre","Fecha de Inicio","Fecha Estimada de Fin", "Estado","Fecha de Fin"}; 
	DefaultTableModel mt = new DefaultTableModel();
	JTable table = new JTable(mt);
	JScrollPane scrollPane = new JScrollPane(); 
	ControladorProyecto controladorProyecto = new ControladorProyecto();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ProyectoDialog dialog = new ProyectoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ProyectoDialog() {
		setBounds(50, 50, 1200, 750);
		this.setResizable(false);
		this.setTitle("GESTIÓN DE PROYECTOS");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//Formato de tabla
		mt.setColumnIdentifiers(ids);
		table.setBounds(100, 40, 1000, 600);
		scrollPane.setBounds(100, 40, 1000, 600);
		scrollPane.setViewportView(table);
		contentPanel.add(scrollPane);
		
		cargarProyectos();
	
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton altaBtn = new JButton("Alta");
				altaBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						AltaProyectoDialog altaProyectoDialog = new AltaProyectoDialog();
						altaProyectoDialog.setVisible(true);
					}
				});
				buttonPane.add(altaBtn);
			}
			{
				JButton bajaBtn = new JButton("Baja");
				bajaBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						BajaProyectoDialog bajaProyectoDialog = new BajaProyectoDialog();
						bajaProyectoDialog.setVisible(true);
					}
				});
				buttonPane.add(bajaBtn);
			}
			{
				JButton modificarBtn = new JButton("Modificar");
				modificarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						ModificarProyectoDialog modificarProyectoDialog = new ModificarProyectoDialog();
						modificarProyectoDialog.setVisible(true);
					}
				});
				buttonPane.add(modificarBtn);
			}
			{
				JButton cancelarBtn = new JButton("Cancelar");
				cancelarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				{
					JButton eliminarBtn = new JButton("Eliminar");
					eliminarBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							EliminarProyectoDialog eliminarProyectoDialog = new EliminarProyectoDialog();
							eliminarProyectoDialog.setVisible(true);
						}
					});
					buttonPane.add(eliminarBtn);
				}
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}
	//"Legajo","Nombre","Fecha de Inicio","Fecha Estimada de Fin", "Estado","Fecha de Fin"
	public void cargarProyectos(){
		DefaultTableModel modeloTablaProyecto = (DefaultTableModel) table.getModel();
		List<Proyecto> filasTablaProyecto = controladorProyecto.listarProyectos();
		Iterator<Proyecto> iterador = filasTablaProyecto.iterator();
		while (iterador.hasNext()) {
			Proyecto proyecto = (Proyecto) iterador.next();
			String fila[] = {String.valueOf(proyecto.getId()),String.valueOf(proyecto.getNombre()),String.valueOf(proyecto.getFechaInicio()),
					String.valueOf(proyecto.getFechaEstmiadaFin()),String.valueOf(proyecto.getEstado()),String.valueOf(proyecto.getFechaFin())};
			modeloTablaProyecto.addRow(fila);
		}
		
	}

}
