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
import javax.swing.JTextField;

public class BajaProyectoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField legajoTxt;
	ControladorProyecto controladorProyecto = new ControladorProyecto();
	JLabel lblNewLabel_1 = new JLabel("");
	JLabel lblNewLabel_2 = new JLabel("");
	JLabel lblNewLabel_3 = new JLabel("");
	JLabel lblNewLabel_4 = new JLabel("");
	JLabel lblNewLabel_5 = new JLabel("");
	Proyecto proyecto = new Proyecto();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BajaProyectoDialog dialog = new BajaProyectoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BajaProyectoDialog() {
		setBounds(50, 50, 500, 300);
		this.setResizable(false);
		this.setTitle("BAJA DE PROYECTOS");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Ingresar Legajo de Proyecto");
			lblNewLabel.setBounds(10, 29, 147, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			legajoTxt = new JTextField();
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
					String codigo = legajoTxt.getText();
					int id = Integer.parseInt(codigo);
					proyecto = controladorProyecto.buscarID(id);
					lblNewLabel_1.setText("Legajo: " + proyecto.getId());
					lblNewLabel_2.setText("Nombre: " + proyecto.getNombre());
					lblNewLabel_3.setText("Tipo: " + proyecto.getTipoProyecto());
					lblNewLabel_4.setText("Estado: " + (proyecto.getEstado()));
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
