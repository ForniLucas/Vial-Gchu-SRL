package VistaUsuario;

import java.awt.BorderLayout;
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

public class ModificarMaquinariaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField legajoTxt;
	private JTextField codigoTxt;
	private JTextField descripcionTxt;
	private JTextField fabricanteTxt;
	private JTextField ubicacionTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModificarMaquinariaDialog dialog = new ModificarMaquinariaDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModificarMaquinariaDialog() {
		setBounds(50, 50, 600, 700);
		this.setResizable(false);
		this.setTitle("MODIFICAR MAQUINARIA");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Ingresar el Legajo de la Maquinaria");
			lblNewLabel.setBounds(28, 54, 177, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Patente");
			lblNewLabel_1.setBounds(220, 146, 45, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Descripción");
			lblNewLabel_2.setBounds(197, 238, 68, 13);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Fabricante");
			lblNewLabel_3.setBounds(201, 330, 64, 13);
			contentPanel.add(lblNewLabel_3);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Ubicación de Almacenamiento");
			lblNewLabel_4.setBounds(113, 422, 152, 13);
			contentPanel.add(lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Estado");
			lblNewLabel_5.setBounds(220, 514, 45, 13);
			contentPanel.add(lblNewLabel_5);
		}
		{
			legajoTxt = new JTextField();
			legajoTxt.setBounds(250, 51, 96, 19);
			contentPanel.add(legajoTxt);
			legajoTxt.setColumns(10);
		}
		{
			codigoTxt = new JTextField();
			codigoTxt.setBounds(305, 143, 96, 19);
			contentPanel.add(codigoTxt);
			codigoTxt.setColumns(10);
		}
		{
			descripcionTxt = new JTextField();
			descripcionTxt.setBounds(305, 235, 152, 19);
			contentPanel.add(descripcionTxt);
			descripcionTxt.setColumns(10);
		}
		{
			fabricanteTxt = new JTextField();
			fabricanteTxt.setBounds(305, 327, 96, 19);
			contentPanel.add(fabricanteTxt);
			fabricanteTxt.setColumns(10);
		}
		{
			ubicacionTxt = new JTextField();
			ubicacionTxt.setBounds(305, 419, 152, 19);
			contentPanel.add(ubicacionTxt);
			ubicacionTxt.setColumns(10);
		}
		{
			JComboBox estadoBox = new JComboBox();
			estadoBox.setBounds(305, 510, 96, 21);
			contentPanel.add(estadoBox);
		}
		{
			JButton buscarBtn = new JButton("Buscar");
			buscarBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			buscarBtn.setBounds(426, 50, 85, 21);
			contentPanel.add(buscarBtn);
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
				guardarBtn.setActionCommand("OK");
				buttonPane.add(guardarBtn);
				getRootPane().setDefaultButton(guardarBtn);
			}
			{
				JButton cancelarBtn = new JButton("Cancelar");
				cancelarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						MaquinariaDialog maquinariaDialog = new MaquinariaDialog();
						maquinariaDialog.setVisible(true);
					}
				});
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}

}
