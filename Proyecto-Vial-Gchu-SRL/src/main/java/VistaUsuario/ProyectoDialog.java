package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProyectoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

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
						BajaProyectoDialog bajaProyectoDialog = new BajaProyectoDialog();
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
						ModificarProyectoDialog modificarProyectoDialog = new ModificarProyectoDialog();
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
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}

}
