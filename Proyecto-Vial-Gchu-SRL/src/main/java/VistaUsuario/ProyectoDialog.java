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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ProyectoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	//Tabla Principal2
	String ids[] = {"Legajo","Nombre","Fecha de Inicio","Fecha Estimada de Fin", "Estado","Fecha de Fin"}; 
	DefaultTableModel mt = new DefaultTableModel();
	JTable table = new JTable(mt);
	JScrollPane scrollPane = new JScrollPane(); 
	JOptionPane optionPane = new JOptionPane();
	ControladorProyecto controladorProyecto = new ControladorProyecto();
	String id = new String();
	String nombre = new String();
	String fechaDeInicio = new String();
	String fechaEstimada = new String();
	String estado = new String();
	String fechaFin = new String();
	private JTextField buscarFechaDesde;
	private JTextField buscarIdTxt;
	private JTextField buscarFechaHasta;
	private JTextField buscarNombre;
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
			JLabel fechaDesdeLbl = new JLabel("Ingrese fecha desde:");
			fechaDesdeLbl.setBounds(26, 28, 161, 13);
			contentPanel.add(fechaDesdeLbl);
		}
		{
			buscarFechaDesde = new JTextField();
			buscarFechaDesde.setColumns(10);
			buscarFechaDesde.setBounds(140, 25, 121, 19);
			contentPanel.add(buscarFechaDesde);
		}
		{
			JLabel idLbl = new JLabel("O ingrese un ID:");
			idLbl.setBounds(488, 28, 127, 13);
			contentPanel.add(idLbl);
		}
		{
			buscarIdTxt = new JTextField();
			buscarIdTxt.setColumns(10);
			buscarIdTxt.setBounds(587, 25, 85, 19);
			contentPanel.add(buscarIdTxt);
		}
		{
			JButton buscarBtn = new JButton("Buscar");
			buscarBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
					    String fechaDesdeString = buscarFechaDesde.getText();
					    String fechaHastaString = buscarFechaHasta.getText();
					    String idString = buscarIdTxt.getText();
					    String nombre = buscarNombre.getText();

					    LocalDate fechaDesde = null;
					    LocalDate fechaHasta = null;
					    int id = 0;

					    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

					    if (fechaDesdeString.isEmpty() && fechaHastaString.isEmpty() && idString.isEmpty() && nombre.isEmpty() ) {
					    	actualizarTabla();
					    }
					    else {
					    	boolean control = validarDatos(fechaDesdeString, fechaHastaString, idString, nombre);
					    	
					    	if (control) {
					    		if (!idString.isEmpty()) {
					    			id = Integer.parseInt(idString);
					    			cargarProyecto(id);
					    		} 
					    		else if (!nombre.isEmpty()){
					    			cargarProyectos(nombre);
					    		}
					    		else {
					    			fechaDesde = LocalDate.parse(fechaDesdeString, formatter);
						    		fechaHasta = LocalDate.parse(fechaHastaString, formatter);
						    		cargarProyectos(fechaDesde,fechaHasta);
					    		}
					    	}
					    }
					}catch (Exception ex) {
					    optionPane.showMessageDialog(null, ex.getMessage());
					}
				}
			});
			buscarBtn.setBounds(1017, 25, 85, 19);
			contentPanel.add(buscarBtn);
		}
		
		JLabel lblHasta = new JLabel("Hasta:");
		lblHasta.setBounds(275, 28, 102, 13);
		contentPanel.add(lblHasta);
		
		buscarFechaHasta = new JTextField();
		buscarFechaHasta.setColumns(10);
		buscarFechaHasta.setBounds(324, 25, 121, 19);
		contentPanel.add(buscarFechaHasta);
		
		JLabel nombreLbl = new JLabel("O ingrese un nombre:");
		nombreLbl.setBounds(711, 28, 127, 13);
		contentPanel.add(nombreLbl);
		
		buscarNombre = new JTextField();
		buscarNombre.setColumns(10);
		buscarNombre.setBounds(835, 25, 121, 19);
		contentPanel.add(buscarNombre);
		
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
			String fila[] = {String.valueOf(proyecto.getId()),String.valueOf(proyecto.getNombre()),String.valueOf(convertirFecha(proyecto.getFechaInicio())),
					String.valueOf(convertirFecha(proyecto.getFechaEstFin())),String.valueOf(estado),String.valueOf(convertirFecha(proyecto.getFechaFin()))};
			modeloTablaProyecto.addRow(fila);
		}
		
	}
	
	public void cargarProyectos(LocalDate desde, LocalDate hasta) {
		DefaultTableModel modeloTablaProyecto = (DefaultTableModel) table.getModel();
		modeloTablaProyecto.setRowCount(0);
		List<Proyecto> filasTablaProyecto = controladorProyecto.buscarEntreFechas(desde, hasta);
		Iterator<Proyecto> iterador = filasTablaProyecto.iterator();
		while (iterador.hasNext()) {
			Proyecto proyecto = (Proyecto) iterador.next();
			String estado = proyecto.getEstado().toString();
			String fila[] = {String.valueOf(proyecto.getId()),String.valueOf(proyecto.getNombre()),String.valueOf(convertirFecha(proyecto.getFechaInicio())),
					String.valueOf(convertirFecha(proyecto.getFechaEstFin())),String.valueOf(estado),String.valueOf(convertirFecha(proyecto.getFechaFin()))};
			modeloTablaProyecto.addRow(fila);
		}
	}
	
	public void cargarProyectos(String pNombre) {
		DefaultTableModel modeloTablaProyecto = (DefaultTableModel) table.getModel();
		modeloTablaProyecto.setRowCount(0);
		List<Proyecto> filasTablaProyecto = controladorProyecto.buscarNombre(pNombre);
		Iterator<Proyecto> iterador = filasTablaProyecto.iterator();
		while (iterador.hasNext()) {
			Proyecto proyecto = (Proyecto) iterador.next();
			String estado = proyecto.getEstado().toString();
			String fila[] = {String.valueOf(proyecto.getId()),String.valueOf(proyecto.getNombre()),String.valueOf(convertirFecha(proyecto.getFechaInicio())),
					String.valueOf(convertirFecha(proyecto.getFechaEstFin())),String.valueOf(estado),String.valueOf(convertirFecha(proyecto.getFechaFin()))};
			modeloTablaProyecto.addRow(fila);
		}
	}
	
	public void cargarProyecto(int id) {
		DefaultTableModel modeloTablaProyecto = (DefaultTableModel) table.getModel();
		modeloTablaProyecto.setRowCount(0);
		List<Proyecto> proyectoEncontrados = new LinkedList<Proyecto>();
		Proyecto proyectoEncontrado = controladorProyecto.buscarID(id);
		if (proyectoEncontrado != null) proyectoEncontrados.add(proyectoEncontrado);
		List<Proyecto> filasTablaProyecto = proyectoEncontrados;
		Iterator<Proyecto> iterador = filasTablaProyecto.iterator();
		while (iterador.hasNext()) {
			Proyecto proyecto = (Proyecto) iterador.next();
			String estado = proyecto.getEstado().toString();
			String fila[] = {String.valueOf(proyecto.getId()),String.valueOf(proyecto.getNombre()),String.valueOf(convertirFecha(proyecto.getFechaInicio())),
					String.valueOf(convertirFecha(proyecto.getFechaEstFin())),String.valueOf(estado),String.valueOf(convertirFecha(proyecto.getFechaFin()))};
			modeloTablaProyecto.addRow(fila);
		}
	}
	
	public boolean validarDatos(String fechaDesde, String fechaHasta, String id, String nombre) {
	    boolean resultado = true;

	    if ((!fechaDesde.isEmpty() && !id.isEmpty()) || (!fechaHasta.isEmpty() && !id.isEmpty())) {
	    	JOptionPane.showMessageDialog(null, "Debe elejir solo un criterio de busqueda");
            resultado = false;
            return resultado;
	    }
	    
	    if ((!fechaDesde.isEmpty() && !nombre.isEmpty()) || (!fechaHasta.isEmpty() && !nombre.isEmpty())) {
	    	JOptionPane.showMessageDialog(null, "Debe elejir solo un criterio de busqueda");
            resultado = false;
            return resultado;
	    }
	    
	    if ((!nombre.isEmpty() && !id.isEmpty()) ) {
	    	JOptionPane.showMessageDialog(null, "Debe elejir solo un criterio de busqueda");
            resultado = false;
            return resultado;
	    }
	    
	    
	    // Validar que el ID sea un número válido y mayor a 0
	    if (!id.isEmpty()) {
	        try {
	            int idNum = Integer.parseInt(id);
	            if (idNum <= 0) {
	                JOptionPane.showMessageDialog(null, "El ID debe ser mayor a 0.");
	                resultado = false;
	                return resultado;
	            }
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(null, "Ingrese un valor numérico válido para el ID.");
	            resultado = false;
	            return resultado;
	        }
	    }

	    // Validar las fechas
	    if(!fechaDesde.isEmpty() && fechaHasta.isEmpty()) {
	    	JOptionPane.showMessageDialog(null, "Debe completar fecha Hasta");
            resultado = false;
            return resultado;
	    }
	    
	    if(fechaDesde.isEmpty() && !fechaHasta.isEmpty()) {
	    	JOptionPane.showMessageDialog(null, "Debe completar fecha Desde");
            resultado = false;
            return resultado;
	    }
	    
	    if (!fechaDesde.isEmpty() && !fechaHasta.isEmpty()) {
	        try {
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	            LocalDate desde = LocalDate.parse(fechaDesde, formatter);
	            LocalDate hasta = LocalDate.parse(fechaHasta, formatter);
	            if (desde.isAfter(hasta)) {
	                JOptionPane.showMessageDialog(null, "La fecha desde no puede ser posterior a la fecha hasta.");
	                resultado = false;
	                return resultado;
	            }
	        } catch (DateTimeParseException ex) {
	            JOptionPane.showMessageDialog(null, "Ingrese fechas válidas con el formato dd/MM/yyyy.");
	            resultado = false;
	            return resultado;
	        }
	    }

	    return resultado;
	}


	
	public void actualizarTabla() {
		 DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		    modelo.setRowCount(0); // Limpia la tabla
		    cargarProyectos(); 
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
