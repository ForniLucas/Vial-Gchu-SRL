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

import Domain.Empleado;
import Domain.Maquinaria;
import Controladoras.ControladorMaquinaria;

import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MaquinariaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	//Tabla Principal
	String ids[] = {"Legajo","codigo","Descripción","Fabricante","Ubicación de Almacenamiento","Estado"}; 
	DefaultTableModel mt = new DefaultTableModel();
	JOptionPane optionPane = new JOptionPane();
	JTable table = new JTable(mt);
	JScrollPane scrollPane = new JScrollPane(); 
	ControladorMaquinaria controladorMaquinaria = new ControladorMaquinaria();
	String id = new String();
	String codigo = new String();
	String descripcion = new String();
	String fabricante = new String();
	String ubicacion = new String();
	String estado = new String();
	private JTextField buscarcodigoTxt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MaquinariaDialog dialog = new MaquinariaDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MaquinariaDialog() {
		setBounds(50, 50, 1200, 750);
		this.setResizable(false);
		this.setTitle("GESTIÓN DE MAQUINARIA");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		//Formato de tabla
		mt.setColumnIdentifiers(ids);
		table.setBounds(100, 40, 1000, 600);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = table.getSelectedRow();
		        DefaultTableModel mt = (DefaultTableModel)table.getModel();
		         id = mt.getValueAt(filaSeleccionada, 0).toString();
		         codigo = mt.getValueAt(filaSeleccionada, 1).toString();
		         descripcion = mt.getValueAt(filaSeleccionada, 2).toString();
		         fabricante = mt.getValueAt(filaSeleccionada, 3).toString();
		         ubicacion = mt.getValueAt(filaSeleccionada, 4).toString();
		         estado = mt.getValueAt(filaSeleccionada, 5).toString();
			}
		});
		scrollPane.setBounds(91, 72, 1000, 600);
		scrollPane.setViewportView(table);
		contentPanel.add(scrollPane);
		contentPanel.setLayout(null);
		
		JLabel codigoLbl = new JLabel("Ingrese el código:");
		codigoLbl.setBounds(91, 32, 127, 13);
		contentPanel.add(codigoLbl);
		
		buscarcodigoTxt = new JTextField();
		buscarcodigoTxt.setColumns(10);
		buscarcodigoTxt.setBounds(228, 29, 205, 19);
		contentPanel.add(buscarcodigoTxt);
		
		JButton buscarBtn = new JButton("Buscar");
		buscarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String buscar = "";
				    String inputText = buscarcodigoTxt.getText();
				    
				    // Verificar si el texto contiene caracteres no válidos
				    if (!inputText.matches("^[a-zA-Z0-9]*$")) {
				        optionPane.showMessageDialog(null, "El texto ingresado contiene caracteres no válidos. Solo se permiten letras y números.");
				        return; // Salir del método sin continuar
				    }
				    
				    buscar = inputText.toUpperCase();
					
					if (buscar.isEmpty()) {
						actualizarTabla();
					}
					else {
						cargarMaquinaria(buscar);
					}
					
					
				} catch (Exception ex) {
				    optionPane.showMessageDialog(null, ex.getMessage());
				}
				
			}
		});
		buscarBtn.setBounds(460, 29, 85, 19);
		contentPanel.add(buscarBtn);
		cargarMaquinaria();
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton altaBtn = new JButton("Alta");
			altaBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					AltaMaquinariaDialog altaMaquinariaDialog = new AltaMaquinariaDialog();
					altaMaquinariaDialog.setVisible(true);
				}
			});
			buttonPane.add(altaBtn);
			
			JButton bajaBtn = new JButton("Baja");
			bajaBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					BajaMaquinariaDialog bajaMaquinariaDialog = new BajaMaquinariaDialog(MaquinariaDialog.this, codigo);
					bajaMaquinariaDialog.setVisible(true);
				}
			});
			buttonPane.add(bajaBtn);
			
			JButton modificarBtn = new JButton("Modificar");
			modificarBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					ModificarMaquinariaDialog modificarMaquinariaDialog = new ModificarMaquinariaDialog(MaquinariaDialog.this, codigo);
					modificarMaquinariaDialog.setVisible(true);
				}
			});
			buttonPane.add(modificarBtn);
			{
				JButton cancelarBtn = new JButton("Cancelar");
				cancelarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				
				JButton serviceBtn = new JButton("Gestionar Service");
				serviceBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						ServiceDialog serviceDialog = new ServiceDialog(MaquinariaDialog.this, codigo);
						serviceDialog.setVisible(true);
					}
				});
				buttonPane.add(serviceBtn);
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}
	public void cargarMaquinaria(){
		DefaultTableModel modeloTablaMaquinaria = (DefaultTableModel) table.getModel();
		modeloTablaMaquinaria.setRowCount(0);
		List<Maquinaria> filasTablaEmpleado = controladorMaquinaria.listarMaquinaria();
		Iterator<Maquinaria> iterador = filasTablaEmpleado.iterator();
		while (iterador.hasNext()) {
			Maquinaria maquinaria = (Maquinaria) iterador.next();
			String estado = maquinaria.getEstado() ? "En Servicio" : "Fuera De Servicio";
			String fila[] = {String.valueOf(maquinaria.getId()),String.valueOf(maquinaria.getCodigo()),String.valueOf(maquinaria.getDescripcion()),
					String.valueOf(maquinaria.getFabricante()),String.valueOf(maquinaria.getUbicacionAlmacenamiento()),String.valueOf(estado),
		};
			modeloTablaMaquinaria.addRow(fila);
		}
		
	}
	
	public void cargarMaquinaria(String codigo){
		DefaultTableModel modeloTablaMaquinaria = (DefaultTableModel) table.getModel();
		modeloTablaMaquinaria.setRowCount(0);
		List<Maquinaria> maquinariaEncontrados = new LinkedList<Maquinaria>();
		Maquinaria maquinariaEncontrado = controladorMaquinaria.buscar(codigo);
		if (maquinariaEncontrado != null) maquinariaEncontrados.add(maquinariaEncontrado);
		List<Maquinaria> filasTablaEmpleado = maquinariaEncontrados;
		Iterator<Maquinaria> iterador = filasTablaEmpleado.iterator();
		while (iterador.hasNext()) {
			Maquinaria maquinaria = (Maquinaria) iterador.next();
			String estado = maquinaria.getEstado() ? "En Servicio" : "Fuera De Servicio";
			String fila[] = {String.valueOf(maquinaria.getId()),String.valueOf(maquinaria.getCodigo()),String.valueOf(maquinaria.getDescripcion()),
					String.valueOf(maquinaria.getFabricante()),String.valueOf(maquinaria.getUbicacionAlmacenamiento()),String.valueOf(estado),
		};
			modeloTablaMaquinaria.addRow(fila);
		}
		
	}
	
	public void actualizarTabla() {
		 DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		    modelo.setRowCount(0); // Limpia la tabla
		    cargarMaquinaria();
	}
}
