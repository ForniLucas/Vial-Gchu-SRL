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
import TemplatesPDF.ControladorPlantillas;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//Long id = (long) 1;
    	ControladorPlantillas plant = new ControladorPlantillas();
    	plant.crearPlantilla();

    	

    }
}
