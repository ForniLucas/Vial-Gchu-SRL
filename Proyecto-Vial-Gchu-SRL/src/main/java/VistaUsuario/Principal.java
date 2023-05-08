package VistaUsuario;

import java.awt.BorderLayout;

import java.awt.event.ActionListener;
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
import java.awt.event.ActionEvent;

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
		frame =  new JFrame();
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
		
		//FRAME TITULO
		frame.getContentPane().add(panel1, BorderLayout.NORTH);
		panel1.setLayout(new BorderLayout(0, 0));
		
		JLabel lbTitulo = new JLabel("VIAL GUALEGUAYCHÚ S.R.L.");
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setFont(new Font("Verdana", Font.BOLD, 36));
		panel1.add(lbTitulo);
		
		//FRAME BOTONES 
		frame.getContentPane().add(panel4, BorderLayout.SOUTH);
		panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
		
		//BOTON EMPLEADO
		JButton empleados = new JButton("Gestionar Empleados");
		empleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmpleadoDialog empleadoDialog = new EmpleadoDialog();
		        empleadoDialog.setVisible(true);
			}
		});
		empleados.setBackground(new Color(192, 192, 192));
		
		//BOTON PROYECTO
		JButton proyecto = new JButton("Gestionar Proyectos");
		proyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProyectoDialog proyectoDialog = new ProyectoDialog();
				proyectoDialog.setVisible(true);
			}
		});
		proyecto.setBackground(new Color(192, 192, 192));
		
		//BOTON MAQUINARIA
		JButton maquinaria = new JButton("Gestionar Maquinaria");
		maquinaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MaquinariaDialog maquinariaDialog = new MaquinariaDialog();
				maquinariaDialog.setVisible(true);
			}
		});
		maquinaria.setBackground(new Color(192, 192, 192));
		panel4.add(empleados);
		panel4.add(proyecto);
		panel4.add(maquinaria);
		panel4.setVisible(true);
		
		
		//FRAME CENTRAL
		frame.getContentPane().add(panel5, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("");
		panel5.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/Vista/img/3.2 400x400.png")));
	}

}