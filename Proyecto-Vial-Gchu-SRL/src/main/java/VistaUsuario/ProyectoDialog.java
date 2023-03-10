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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ProyectoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	//Tabla Principal2
	String ids[] = {"Legajo","Nombre","Fecha de Inicio","Fecha Estimada de Fin", "Estado","Fecha de Fin"}; 
	DefaultTableModel mt = new DefaultTableModel();
	JTable table = new JTable(mt);
	JScrollPane scrollPane = new JScrollPane(); 
	ControladorProyecto controladorProyecto = new ControladorProyecto();
	String id = new String();
	String nombre = new String();
	String fechaDeInicio = new String();
	String fechaEstimada = new String();
	String estado = new String();
	String fechaFin = new String();
	private JTextField buscarNombreTxt;
	private JTextField buscarIdTxt;
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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = table.getSelectedRow();
		        DefaultTableModel mt = (DefaultTableModel)table.getModel();
		        id = mt.getValueAt(filaSeleccionada, 0).toString();
		        nombre = mt.getValueAt(filaSeleccionada, 1).toString();
		        fechaDeInicio = mt.getValueAt(filaSeleccionada, 2).toString();
		        fechaEstimada = mt.getValueAt(filaSeleccionada, 3).toString();
		        estado = mt.getValueAt(filaSeleccionada, 4).toString();
		        fechaFin = mt.getValueAt(filaSeleccionada, 5).toString();
			}
		});
		scrollPane.setBounds(102, 72, 1000, 600);
		scrollPane.setViewportView(table);
		contentPanel.add(scrollPane);
		{
			JLabel nombreLbl = new JLabel("Ingrese un nombre:");
			nombreLbl.setBounds(102, 28, 127, 13);
			contentPanel.add(nombreLbl);
		}
		{
			buscarNombreTxt = new JTextField();
			buscarNombreTxt.setColumns(10);
			buscarNombreTxt.setBounds(239, 25, 205, 19);
			contentPanel.add(buscarNombreTxt);
		}
		{
			JLabel idLbl = new JLabel("O ingrese un ID:");
			idLbl.setBounds(476, 28, 127, 13);
			contentPanel.add(idLbl);
		}
		{
			buscarIdTxt = new JTextField();
			buscarIdTxt.setColumns(10);
			buscarIdTxt.setBounds(600, 25, 205, 19);
			contentPanel.add(buscarIdTxt);
		}
		{
			JButton buscarBtn = new JButton("Buscar");
			buscarBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			buscarBtn.setBounds(851, 25, 85, 19);
			contentPanel.add(buscarBtn);
		}
		
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
						BajaProyectoDialog bajaProyectoDialog = new BajaProyectoDialog(ProyectoDialog.this, id);
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
						ModificarProyectoDialog modificarProyectoDialog = new ModificarProyectoDialog(ProyectoDialog.this, id);
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
							setVisible(false);
							EliminarProyectoDialog eliminarProyectoDialog = new EliminarProyectoDialog(ProyectoDialog.this, id);
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
			String estado = proyecto.getEstado().toString();
			String fila[] = {String.valueOf(proyecto.getId()),String.valueOf(proyecto.getNombre()),String.valueOf(proyecto.getFechaInicio()),
					String.valueOf(proyecto.getFechaEstFin()),String.valueOf(estado),String.valueOf(proyecto.getFechaFin())};
			modeloTablaProyecto.addRow(fila);
		}
		
	}
	public void refrescar() {
		table.repaint();;
	}

}
