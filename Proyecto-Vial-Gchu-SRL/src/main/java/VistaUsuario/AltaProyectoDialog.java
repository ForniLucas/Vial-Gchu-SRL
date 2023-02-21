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
			lblNewLabel.setBounds(93, 32, 76, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Fecha Estimada de Fin");
			lblNewLabel_1.setBounds(54, 87, 115, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Estado");
			lblNewLabel_2.setBounds(124, 142, 45, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Nombre de Proyecto");
			lblNewLabel_3.setBounds(61, 197, 108, 13);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Tipo de Proyecto");
			lblNewLabel_4.setBounds(74, 252, 95, 13);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Descripción");
			lblNewLabel_5.setBounds(101, 309, 68, 13);
			contentPanel.add(lblNewLabel_5);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Actividades");
			lblNewLabel_6.setBounds(108, 362, 61, 13);
			contentPanel.add(lblNewLabel_6);
		}
		{
			JLabel lblNewLabel_7 = new JLabel("Insumos");
			lblNewLabel_7.setBounds(124, 417, 45, 13);
			contentPanel.add(lblNewLabel_7);
		}
		{
			fechaInicioTxt = new JTextField();
			fechaInicioTxt.setBounds(208, 29, 96, 19);
			contentPanel.add(fechaInicioTxt);
			fechaInicioTxt.setColumns(10);
		}
		{
			fechaFinTxt = new JTextField();
			fechaFinTxt.setBounds(208, 84, 96, 19);
			contentPanel.add(fechaFinTxt);
			fechaFinTxt.setColumns(10);
		}
		{
			estadoTxt = new JTextField();
			estadoTxt.setBounds(208, 139, 96, 19);
			contentPanel.add(estadoTxt);
			estadoTxt.setColumns(10);
		}
		{
			nombreTxt = new JTextField();
			nombreTxt.setBounds(208, 194, 96, 19);
			contentPanel.add(nombreTxt);
			nombreTxt.setColumns(10);
		}
		{
			descripcionTxt = new JTextField();
			descripcionTxt.setBounds(208, 306, 96, 19);
			contentPanel.add(descripcionTxt);
			descripcionTxt.setColumns(10);
		}
		{
			actividadesTxt = new JTextField();
			actividadesTxt.setBounds(208, 359, 96, 19);
			contentPanel.add(actividadesTxt);
			actividadesTxt.setColumns(10);
		}
		{
			insumosTxt = new JTextField();
			insumosTxt.setBounds(208, 414, 96, 19);
			contentPanel.add(insumosTxt);
			insumosTxt.setColumns(10);
		}
		{
			JComboBox comboBox = new JComboBox(TipoDeProyecto.values());
			comboBox.setBounds(208, 248, 96, 21);
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
