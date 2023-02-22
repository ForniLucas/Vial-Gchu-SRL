package VistaUsuario;

import java.awt.BorderLayout;
import Enumeraciones.TipoDeProyecto;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ModificarProyectoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField legajoTxt;
	private JTextField fechaDeInicioTxt;
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
			ModificarProyectoDialog dialog = new ModificarProyectoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModificarProyectoDialog() {
		setBounds(50, 50, 450, 600);
		this.setResizable(false);
		this.setTitle("BAJA DE PROYECTOS");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Ingrese Legajo del Proyecto");
			lblNewLabel.setBounds(58, 33, 140, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Fecha de Inicio");
			lblNewLabel_1.setBounds(114, 87, 84, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Fecha Estimada de Fin");
			lblNewLabel_2.setBounds(84, 141, 114, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Estado");
			lblNewLabel_3.setBounds(153, 195, 45, 13);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Nombre de Proyecto");
			lblNewLabel_4.setBounds(95, 249, 103, 13);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Tipo De Proyecto");
			lblNewLabel_5.setBounds(98, 303, 100, 13);
			contentPanel.add(lblNewLabel_5);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Descripción");
			lblNewLabel_6.setBounds(127, 357, 71, 13);
			contentPanel.add(lblNewLabel_6);
		}
		{
			JLabel lblNewLabel_7 = new JLabel("Actividades");
			lblNewLabel_7.setBounds(127, 411, 71, 13);
			contentPanel.add(lblNewLabel_7);
		}
		{
			JLabel lblNewLabel_8 = new JLabel("Insumos");
			lblNewLabel_8.setBounds(140, 465, 58, 13);
			contentPanel.add(lblNewLabel_8);
		}
		{
			legajoTxt = new JTextField();
			legajoTxt.setBounds(208, 33, 96, 19);
			contentPanel.add(legajoTxt);
			legajoTxt.setColumns(255);
		}
		{
			fechaDeInicioTxt = new JTextField();
			fechaDeInicioTxt.setText("dd-mm-aaaa");
			fechaDeInicioTxt.setBounds(208, 87, 96, 19);
			contentPanel.add(fechaDeInicioTxt);
			fechaDeInicioTxt.setColumns(10);
		}
		{
			fechaFinTxt = new JTextField();
			fechaFinTxt.setText("dd-mm-aaaa");
			fechaFinTxt.setBounds(208, 141, 96, 19);
			contentPanel.add(fechaFinTxt);
			fechaFinTxt.setColumns(10);
		}
		{
			estadoTxt = new JTextField();
			estadoTxt.setBounds(208, 195, 96, 19);
			contentPanel.add(estadoTxt);
			estadoTxt.setColumns(255);
		}
		{
			nombreTxt = new JTextField();
			nombreTxt.setBounds(208, 249, 189, 19);
			contentPanel.add(nombreTxt);
			nombreTxt.setColumns(255);
		}
		{
			descripcionTxt = new JTextField();
			descripcionTxt.setBounds(208, 357, 189, 19);
			contentPanel.add(descripcionTxt);
			descripcionTxt.setColumns(255);
		}
		{
			actividadesTxt = new JTextField();
			actividadesTxt.setBounds(208, 411, 189, 19);
			contentPanel.add(actividadesTxt);
			actividadesTxt.setColumns(255);
		}
		{
			insumosTxt = new JTextField();
			insumosTxt.setBounds(208, 465, 189, 19);
			contentPanel.add(insumosTxt);
			insumosTxt.setColumns(255);
		}
		{
			JComboBox tipoBox = new JComboBox(TipoDeProyecto.values());
			tipoBox.setBounds(208, 302, 114, 21);
			contentPanel.add(tipoBox);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton guardarBtn = new JButton("Guardar");
				guardarBtn.setActionCommand("OK");
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
