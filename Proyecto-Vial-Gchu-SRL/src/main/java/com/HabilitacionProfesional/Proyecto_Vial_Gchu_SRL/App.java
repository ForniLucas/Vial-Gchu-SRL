package com.HabilitacionProfesional.Proyecto_Vial_Gchu_SRL;

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

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Long id = (long) 1;
    	//LocalDate date1 = LocalDate.of(2021, Month.JANUARY, 1);
    	//LocalDate date2 = LocalDate.of(2022, Month.DECEMBER, 31);

    	ControladorEmpleado controlador = new ControladorEmpleado();
    	ControladorProyecto controladorP = new ControladorProyecto();

    	//controlador.alta(id, "migel", "akd", 1239, 1239, "asdjakl", LocalDate.now());
    	//sscontroladorP.alta(1, LocalDate.now(), LocalDate.now(), "algo", "al;kd", LocalDate.now());
    	Proyecto proyecto = controladorP.buscarID(1);
    	Empleado empleado = controlador.buscarDNI(1239);
    	
    	controladorP.asignarTrabajo(empleado, proyecto);
    	
    	//LinkedList<Proyecto> listado = controlador.buscarEntreFechas(date1, date2);
    	//Proyecto proyecto = controladorP.buscarID(5);
    	
        //Empleado Tom = controlador.buscarDNI(56789012);
    	
    	//controlador.modificar(Tom);
    	
    	//controladorP.modificar(proyecto);
    	
    	//controladorP.asignarTrabajo(Tom, proyecto);


    }
}
