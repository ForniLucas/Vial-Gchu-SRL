package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladoras.ControladorProyecto;
import Domain.Proyecto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BajaProyectoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JTextField legajoTxt = new JTextField();
	ControladorProyecto controladorProyecto = new ControladorProyecto();
	String id = new String();
	JLabel lblNewLabel_1 = new JLabel("");
	JLabel lblNewLabel_2 = new JLabel("");
	JLabel lblNewLabel_3 = new JLabel("");
	JLabel lblNewLabel_4 = new JLabel("");
	JLabel lblNewLabel_5 = new JLabel("");
	Proyecto proyecto = new Proyecto();
	JOptionPane optionPane = new JOptionPane();
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			BajaProyectoDialog dialog = new BajaProyectoDialog();
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
	public BajaProyectoDialog(ProyectoDialog dialog, String id) {
		super(dialog, "BajaProyectoDialog",true);
		this.id = id;
		
		legajoTxt.setText(id);
		
		setBounds(50, 50, 500, 300);
		this.setResizable(false);
		this.setTitle("BAJA DE PROYECTOS");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Ingresar Legajo:");
			lblNewLabel.setBounds(48, 29, 147, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			legajoTxt.setBounds(167, 26, 132, 19);
			contentPanel.add(legajoTxt);
			legajoTxt.setColumns(255);
		}
		{
			
			lblNewLabel_1.setBounds(263, 92, 174, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			
			lblNewLabel_2.setBounds(49, 161, 163, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			
			lblNewLabel_3.setBounds(263, 161, 174, 13);
			contentPanel.add(lblNewLabel_3);
		}
		{
			
			lblNewLabel_4.setBounds(49, 92, 163, 13); //VER
			contentPanel.add(lblNewLabel_4);
		}
		{
			//AGREGAR UNO MAS PARA MOSTRAR LA UBICACIÓN
			//lblNewLabel_5.setBounds(); //VER
			//contentPanel.add(lblNewLabel_5);
		}
		{
			JButton buscarBtn = new JButton("Buscar");
			buscarBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String codigo = legajoTxt.getText();
						int id = Integer.parseInt(codigo);
						proyecto = controladorProyecto.buscarID(id);
						lblNewLabel_1.setText("Legajo: " + proyecto.getId());
						lblNewLabel_2.setText("Nombre: " + proyecto.getNombre());
						lblNewLabel_3.setText("Tipo: " + proyecto.getTipoProyecto().getTipo().toString());
						lblNewLabel_4.setText("Estado: " + (proyecto.getEstado()));
					} catch (Exception e1) {
						optionPane.showMessageDialog(null, "Error al buscar: Valor Ingresado no valido o inexistente");
					}
					
				}
			});
			buscarBtn.setBounds(324, 25, 85, 21);
			contentPanel.add(buscarBtn);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton bajaBtn = new JButton("Dar de Baja");
				bajaBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							if (proyecto.getNombre() != null) {
					           
					            if (!"Cancelado".equals(controladorProyecto.buscarID(Integer.parseInt(legajoTxt.getText())).getEstado().toString())) {
					            	controladorProyecto.bajaLogica(proyecto);
					                optionPane.showMessageDialog(null, "Datos dados de baja con éxito");
					                setVisible(false);
									ProyectoDialog proyectoDialog = new ProyectoDialog();
									proyectoDialog.setVisible(true);
					            } else {
					                optionPane.showMessageDialog(null, "El proyecto no se pudo dar de Baja.");
					            }
					        } else {
					            optionPane.showMessageDialog(null, "Debe buscar un proyecto primero.");
					        }
						} catch (Exception e1) {
							optionPane.showMessageDialog(null, "Error al intentar dar de baja el proyecto: " + e1.getMessage());
						}
						
					}
				});
				bajaBtn.setActionCommand("OK");
				buttonPane.add(bajaBtn);
				getRootPane().setDefaultButton(bajaBtn);
			}
			{
				JButton cancelarBtn = new JButton("Cancelar");
				cancelarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						ProyectoDialog proyectoDialog = new ProyectoDialog();
						proyectoDialog.setVisible(true);
					}
				});
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}

}
