package com.HabilitacionProfesional.VialGchu;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.*;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    	try {
    		SessionFactory factory = new MetadataSources(registry)
    		.buildMetadata().buildSessionFactory();
    		Session session = factory.openSession();
    		Transaction transaction = session.beginTransaction();
    		
    		Empleado nuevoEmpleado = new Empleado();
            nuevoEmpleado.setNombre("martin");
            nuevoEmpleado.setApellido("lopez");
            nuevoEmpleado.setDni(10);
            nuevoEmpleado.setDireccion("con la toxi");
            
            TipoDeProyecto nuevoTipoDeProyecto= new TipoDeProyecto();
            nuevoTipoDeProyecto.setDescripcion("funciona");
            nuevoEmpleado.setProyecto(nuevoTipoDeProyecto);
            nuevoTipoDeProyecto.setEmpleado(nuevoEmpleado);
            
            
            Especializacion nuevaEspecializacion = new Especializacion();
            nuevaEspecializacion.setEmpleado(nuevoEmpleado);
            nuevaEspecializacion.setProfesion("panadero");
              
            nuevoEmpleado.setEsp(nuevaEspecializacion);
    		
            Proyecto unProyecto= new Proyecto();
            unProyecto.setNombre("andando");
            
            Trabajo unTrabajo= new Trabajo();
            unTrabajo.asignar(unProyecto, nuevoEmpleado);
            unTrabajo.setHorasDeTrabajo(10);
            
    		session.save(nuevoEmpleado);
    		session.save(nuevaEspecializacion);
    	session.save(nuevoTipoDeProyecto);
    	session.save(unProyecto);
    	session.save(unTrabajo);
    		transaction.commit();
    		
    		session.close();
    		factory.close();
    
    		} catch (Exception ex) {
    		System.out.println(ex.getMessage());
    		ex.printStackTrace();
    		StandardServiceRegistryBuilder.destroy(registry);
}
    	 /*
    	Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setNombre("martin");
        nuevoEmpleado.setApellido("lopez");
        nuevoEmpleado.setDni(10);
        nuevoEmpleado.setDireccion("con la toxi");
        
        Especializacion nuevaEspecializacion = new Especializacion();
      nuevaEspecializacion.setEmpleado(nuevoEmpleado);
        nuevaEspecializacion.setProfesion("panadero");
        
        nuevoEmpleado.setEsp(nuevaEspecializacion);
        
        
       
    	Configuration conf =new Configuration().configure().addAnnotatedClass(Empleado.class);
         SessionFactory sf = conf.buildSessionFactory();
        Session sesion = sf.openSession();
        Transaction tx = sesion.beginTransaction();
  
         tx.commit();

        
          * 
         
          

        Configuration conf =new Configuration().configure().addAnnotatedClass(Especializacion.class);
        SessionFactory sf = conf.buildSessionFactory();
       Session sesion = sf.openSession();
       Transaction tx = sesion.beginTransaction();
       sesion.save(nuevaEspecializacion);
        tx.commit();
     */
    }
}
