package VistaUsuario;

import java.awt.BorderLayout;
import Enumeraciones.Elemento;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class ElementoDeSeguridadDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ElementoDeSeguridadDialog dialog = new ElementoDeSeguridadDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ElementoDeSeguridadDialog() {
		setBounds(50, 50, 450, 450);
		this.setResizable(false);
		this.setTitle("ELEMENTO DE SEGURIDAD");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Tipo");
			lblNewLabel.setBounds(122, 34, 45, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Fecha de Entrega");
			lblNewLabel_1.setBounds(73, 90, 94, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JComboBox tipoBox = new JComboBox(Elemento.values());
			tipoBox.setBounds(208, 30, 113, 21);
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
				JButton cancelBtn = new JButton("Cancelar");
				cancelBtn.setActionCommand("Cancel");
				buttonPane.add(cancelBtn);
			}
		}
	}

}
