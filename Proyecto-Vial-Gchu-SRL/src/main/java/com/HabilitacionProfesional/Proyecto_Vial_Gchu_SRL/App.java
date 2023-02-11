package com.HabilitacionProfesional.Proyecto_Vial_Gchu_SRL;

import java.time.LocalDate;

import Controladoras.ControladorEmpleado;
import Domain.Empleado;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Long id = (long) 1;
    	Empleado modificado = new Empleado(id, "modificado", "modificado", 123, 123, "modificado", LocalDate.now());
        ControladorEmpleado controladorEmp = new ControladorEmpleado();
        //controladorEmp.alta(3, "Lucas3", "3", 3, 423, "3", LocalDate.now());
        //controladorEmp.alta(4, "Lucas4", "4", 4, 423, "4", LocalDate.now());
        //controladorEmp.alta(5, "Lucas5", "5", 5, 423, "5", LocalDate.now());
        //controladorEmp.alta(6, "Lucas5", "6", 6, 423, "6", LocalDate.now());
        //controladorEmp.modificar(modificado);
        //controladorEmp.bajaLogica(modificado);
        //controladorEmp.baja(modificado);
        String encontroDNI = controladorEmp.buscarDNI(6).getNombre();
        Empleado Forni = controladorEmp.buscarApellidoyNombre("Lucas","Forni");
        System.out.println(encontroDNI);
        System.out.println(Forni.getNombre());
        System.out.println(Forni.getApellido());
    }
}
