package com.HabilitacionProfesional.Proyecto_Vial_Gchu_SRL;

import java.awt.EventQueue;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;

import Controladoras.ControladorEmpleado;
import Controladoras.ControladorMaquinaria;
import Controladoras.ControladorProyecto;
import Domain.Empleado;
import Domain.Maquinaria;
import Domain.Proyecto;
import Domain.Trabajo;
//import TemplatesPDF.ControladorPlantillas;
import VistaUsuario.Principal;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
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
}
