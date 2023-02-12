package com.HabilitacionProfesional.Proyecto_Vial_Gchu_SRL;

import java.time.LocalDate;

import Controladoras.ControladorEmpleado;
import Controladoras.ControladorMaquinaria;
import Domain.Empleado;
import Domain.Maquinaria;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//Long id = (long) 1;
    	ControladorMaquinaria controlador = new ControladorMaquinaria();
    	//Maquinaria unaMaquina = new Maquinaria(id, "modificado", "modificado", "modificado", "modificado");
    	
    	for (int i = 1; i < 7; i++) {
    		Long idtemp = (long) i;
    		controlador.alta(idtemp, "Maquinaria "+idtemp, "Maquinaria "+idtemp, "Maquinaria "+idtemp, "Maquinaria "+idtemp);
    		}
    	Maquinaria maquina = controlador.buscar("Maquinaria 3");
    	
    	System.out.println(maquina.getCodigo());
    	System.out.println(maquina.getDescripcion());
    	//controlador.modificar(unaMaquina);
    	//controlador.bajaLogica(unaMaquina);
        //controlador.baja(unaMaquina);
    	
    }
}
