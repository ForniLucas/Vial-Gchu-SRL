package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Principal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 800, 560);
		frame.getContentPane().setLayout(new BorderLayout());
		
		
		//paneles de la pantalla principal
		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(255, 255, 255));
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		panel4.setBackground(new Color(255, 255, 255));
		JPanel panel5 = new JPanel();
		panel5.setBackground(new Color(255, 255, 255));
		panel1.setPreferredSize(new Dimension(800,50));
		panel4.setPreferredSize(new Dimension(800,40));
		panel5.setPreferredSize(new Dimension(10,10));
		
		frame.getContentPane().add(panel1, BorderLayout.NORTH);
		panel1.setLayout(new BorderLayout(0, 0));
		
		JLabel lbTitulo = new JLabel("VIAL GUALEGUAYCHÚ S.R.L.");
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setFont(new Font("Verdana", Font.BOLD, 36));
		panel1.add(lbTitulo);
		
		//frame.getContentPane().add(panel2, BorderLayout.WEST);
		
		//frame.getContentPane().add(panel3, BorderLayout.EAST);
		
		frame.getContentPane().add(panel4, BorderLayout.SOUTH);
		panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
		//JButton cancelar = new JButton("Cancelar");
		//cancelar.setBackground(new Color(192, 192, 192));
		JButton empleados = new JButton("Gestionar Empleados");
		empleados.setBackground(new Color(192, 192, 192));
		JButton proyecto = new JButton("Gestionar Proyectos");
		proyecto.setBackground(new Color(192, 192, 192));
		JButton maquinaria = new JButton("Gestionar Maquinaria");
		maquinaria.setBackground(new Color(192, 192, 192));
		panel4.add(empleados);
		panel4.add(proyecto);
		panel4.add(maquinaria);
		//panel4.add(cancelar);
		panel4.setVisible(true);
		
		frame.getContentPane().add(panel5, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("");
		panel5.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/Vista/img/3.2 400x400.png")));
		
	}

}