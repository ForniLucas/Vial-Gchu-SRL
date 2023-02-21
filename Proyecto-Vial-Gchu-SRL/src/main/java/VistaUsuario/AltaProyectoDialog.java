package VistaUsuario;

import java.awt.BorderLayout;
import Enumeraciones.TipoDeProyecto;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaProyectoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField fechaInicioTxt;
	private JTextField fechaFinTxt;
	private JTextField estadoTxt;
	private JTextField nombreTxt;
	private JTextField descripcionTxt;
	private JTextField actividadesTxt;
	private JTextField insumosTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AltaProyectoDialog dialog = new AltaProyectoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AltaProyectoDialog() {
		setBounds(50, 50, 450, 550);
		this.setResizable(false);
		this.setTitle("ALTA DE PROYECTOS");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Fecha de Inicio");
			lblNewLabel.setBounds(104, 33, 76, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Fecha Estimada de Fin");
			lblNewLabel_1.setBounds(65, 88, 115, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Estado");
			lblNewLabel_2.setBounds(135, 143, 45, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Nombre de Proyecto");
			lblNewLabel_3.setBounds(72, 198, 108, 13);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Tipo de Proyecto");
			lblNewLabel_4.setBounds(85, 253, 95, 13);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Descripción");
			lblNewLabel_5.setBounds(112, 310, 68, 13);
			contentPanel.add(lblNewLabel_5);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Actividades");
			lblNewLabel_6.setBounds(119, 363, 61, 13);
			contentPanel.add(lblNewLabel_6);
		}
		{
			JLabel lblNewLabel_7 = new JLabel("Insumos");
			lblNewLabel_7.setBounds(135, 418, 45, 13);
			contentPanel.add(lblNewLabel_7);
		}
		{
			fechaInicioTxt = new JTextField();
			fechaInicioTxt.setText("dd-mm-aaaa");
			fechaInicioTxt.setBounds(190, 30, 96, 19);
			contentPanel.add(fechaInicioTxt);
			fechaInicioTxt.setColumns(10);
		}
		{
			fechaFinTxt = new JTextField();
			fechaFinTxt.setText("dd-mm-aaaa");
			fechaFinTxt.setBounds(190, 85, 96, 19);
			contentPanel.add(fechaFinTxt);
			fechaFinTxt.setColumns(10);
		}
		{
			estadoTxt = new JTextField();
			estadoTxt.setBounds(190, 140, 96, 19);
			contentPanel.add(estadoTxt);
			estadoTxt.setColumns(10);
		}
		{
			nombreTxt = new JTextField();
			nombreTxt.setBounds(190, 195, 196, 19);
			contentPanel.add(nombreTxt);
			nombreTxt.setColumns(10);
		}
		{
			descripcionTxt = new JTextField();
			descripcionTxt.setBounds(190, 307, 196, 19);
			contentPanel.add(descripcionTxt);
			descripcionTxt.setColumns(10);
		}
		{
			actividadesTxt = new JTextField();
			actividadesTxt.setBounds(190, 360, 196, 19);
			contentPanel.add(actividadesTxt);
			actividadesTxt.setColumns(10);
		}
		{
			insumosTxt = new JTextField();
			insumosTxt.setBounds(190, 415, 196, 19);
			contentPanel.add(insumosTxt);
			insumosTxt.setColumns(10);
		}
		{
			JComboBox comboBox = new JComboBox(TipoDeProyecto.values());
			comboBox.setBounds(190, 249, 123, 21);
			contentPanel.add(comboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton guardarBtn = new JButton("Guardar");
				guardarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				guardarBtn.setActionCommand("Guardar");
				buttonPane.add(guardarBtn);
				getRootPane().setDefaultButton(guardarBtn);
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
