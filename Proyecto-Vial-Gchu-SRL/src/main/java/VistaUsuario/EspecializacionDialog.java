package VistaUsuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controladoras.ControladorEmpleado;
import Domain.Empleado;
import Domain.Especializacion;
import Enumeraciones.EstadoProyecto;
import Enumeraciones.Profesion;
import Enumeraciones.RolEmpleado;
import Enumeraciones.TipoDeProyecto;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import Enumeraciones.Profesion;

public class EspecializacionDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField dniTxt;
	private String dni;
	private JComboBox<Profesion> espComboBox= new JComboBox<Profesion>(Profesion.values());
	private JComboBox<RolEmpleado> rolComboBox = new JComboBox<RolEmpleado>(RolEmpleado.values());
	private ControladorEmpleado controlador = new ControladorEmpleado();
	private Empleado empleado = new Empleado();

	/**
	 * Launch the application.
	 */
	/**
	 * Create the dialog.
	 */
	public EspecializacionDialog(HistorialEspecializacionDialog dialog, String dniid) {
		super(dialog, "EspecializacionDialog",true);
		this.dni = dniid;
		setBounds(50, 50, 450, 360);
		this.setResizable(false);
		this.setTitle("ROPA DE TRABAJO");
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DNI");
		lblNewLabel.setBounds(139, 48, 29, 16);
		contentPanel.add(lblNewLabel);
		{
			dniTxt = new JTextField();
			dniTxt.setBounds(189, 47, 96, 19);
			contentPanel.add(dniTxt);
			dniTxt.setColumns(250);
			dniTxt.setEnabled(false);
			dniTxt.setText(dni);
		}
		{
			JLabel tipoLbl = new JLabel("Especializacion:");
			tipoLbl.setBounds(96, 109, 126, 13);
			contentPanel.add(tipoLbl);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Rol:");
			lblNewLabel_1.setBounds(150, 164, 72, 13);
			contentPanel.add(lblNewLabel_1);
		}
		{
			
			espComboBox.setBounds(189, 105, 96, 21);
			contentPanel.add(espComboBox);
		}
		{
			
			rolComboBox.setBounds(189, 160, 96, 21);
			contentPanel.add(rolComboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton guardarBtn = new JButton("Guardar");
				guardarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						empleado = controlador.buscarDNI(Integer.parseInt(dni));
						LocalDate currentDate = LocalDate.now();
				        Profesion prof = (Profesion)espComboBox.getSelectedItem();
				        RolEmpleado rol =(RolEmpleado)rolComboBox.getSelectedItem();
				        Especializacion especializacion = new Especializacion(empleado,prof, currentDate, rol);
				       
				        controlador.asignarEspecializacion(empleado, especializacion);
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
					}
				});
				cancelarBtn.setActionCommand("Cancel");
				buttonPane.add(cancelarBtn);
			}
		}
	}
}
