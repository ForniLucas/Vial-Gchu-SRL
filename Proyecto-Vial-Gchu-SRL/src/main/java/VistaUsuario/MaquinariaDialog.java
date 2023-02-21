package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MaquinariaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

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
		setBounds(100, 100, 1200, 750);
		this.setResizable(false);
		this.setTitle("GESTIÓN DE MAQUINARIA");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
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
					BajaMaquinariaDialog bajaMaquinariaDialog = new BajaMaquinariaDialog();
					bajaMaquinariaDialog.setVisible(true);
				}
			});
			buttonPane.add(bajaBtn);
			
			JButton modificarBtn = new JButton("Modificación");
			modificarBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					ModificarMaquinariaDialog modificarMaquinariaDialog = new ModificarMaquinariaDialog();
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
						ServiceDialog serviceDialog = new ServiceDialog();
						serviceDialog.setVisible(true);
					}
				});
				buttonPane.add(serviceBtn);
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}
}
