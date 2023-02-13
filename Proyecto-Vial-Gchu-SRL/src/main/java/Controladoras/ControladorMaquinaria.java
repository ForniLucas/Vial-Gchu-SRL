package Controladoras;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import Domain.Empleado;
import Domain.Maquinaria;

public class ControladorMaquinaria {
	private LinkedList<Maquinaria> maquinarias = new LinkedList<Maquinaria>();
	
	public void alta(Long idMaquinaria, String codigo, String descripcion, String fabricante, String ubicacionAlmacenamiento ) {
		
		// Iniciar la sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Transaction transaction = null;
	    
		try {
			factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		     session = factory.openSession();
		     transaction = session.beginTransaction();
		      
		    // Agregar la maquinaria a la lista y guardar en la base de datos
			Maquinaria maquinaria = new Maquinaria(idMaquinaria, codigo, descripcion,fabricante,ubicacionAlmacenamiento);
			maquinarias.add(maquinaria);
			session.save(maquinaria);
		    transaction.commit();
		
		}
		catch (Exception ex) {
	    	  // Realizar un rollback en caso de una excepción
	    	  if (transaction != null) {
	    	    transaction.rollback();
	    	  }
	    	  System.out.println(ex.getMessage());
	    	  ex.printStackTrace();
	    	} finally {
	    	  // Cerrar la sesión y destruir el registro del servicio
	    	  if (session != null) {
	    	    session.close();
	    	  }
	    	  if (factory != null) {
	    	    factory.close();
	    	  }
	    	  StandardServiceRegistryBuilder.destroy(registry);
	    	}
		}

	public void modificar(Maquinaria unaMaquinaria) {
	    // Iniciar sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Transaction transaction = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      transaction = session.beginTransaction();
	      
	      // Actualizar maquinaria en la base de datos
	      session.update(unaMaquinaria);
	      transaction.commit();

	      //Actualizar la maquinaria en la lista
	      actualizarlistado(unaMaquinaria);
	      
	    } catch (Exception ex) {
	      // Realizar un rollback en caso de una excepción
	      if (transaction != null) {
	        transaction.rollback();
	      }
	      System.out.println(ex.getMessage());
	      ex.printStackTrace();
	    } finally {
	      // Cerrar la sesión y destruir el registro del servicio
	      if (session != null) {
	        session.close();
	      }
	      if (factory != null) {
	        factory.close();
	      }
	      StandardServiceRegistryBuilder.destroy(registry);
	    }
	  }

	public void bajaLogica(Maquinaria unaMaquinaria) {
	    // Iniciar la sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Transaction transaction = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      transaction = session.beginTransaction();
	      
	      // Cambiar el estado de baja a "true" para el empleado especificado
	      unaMaquinaria.setEstadoBaja();;
	      session.update(unaMaquinaria);
	      transaction.commit();
	      
	      //Actualizar la maquinaria en la lista
	      actualizarlistado(unaMaquinaria);
	      
	    } catch (Exception ex) {
	      if (transaction != null) {
	        // Realizar un rollback en caso de una excepción
	        transaction.rollback();
	      }
	      System.out.println(ex.getMessage());
	      ex.printStackTrace();
	    } finally {
	      if (session != null) {
	        session.close();
	      }
	      if (factory != null) {
	        factory.close();
	      }
	      StandardServiceRegistryBuilder.destroy(registry);
	    }
	  }

	public void baja(Maquinaria unaMaquinaria) {
	    // Start Hibernate session
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Transaction transaction = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      transaction = session.beginTransaction();
	      
	      // Eliminar el empleado de la base de datos
	      session.delete(unaMaquinaria);
	      transaction.commit();
	      
	      //Eliminar la maquinaria de la lista
	      maquinarias.remove(unaMaquinaria);
	      
	    } catch (Exception ex) {
	      if (transaction != null) {
	        // Realizar un rollback en caso de una excepción
	        transaction.rollback();
	      }
	      System.out.println(ex.getMessage());
	      ex.printStackTrace();
	    } finally {
	      if (session != null) {
	        session.close();
	      }
	      if (factory != null) {
	        factory.close();
	      }
	      StandardServiceRegistryBuilder.destroy(registry);
	    }
	  }	

	public Maquinaria buscar(String pCodigo) {
	    // Iniciar la sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Maquinaria resultado = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      
	      // Crear un objeto CriteriaBuilder para construir la consulta
	      CriteriaBuilder builder = session.getCriteriaBuilder();
	      // Crear una consulta para buscar el empleado con el DNI especificado
	      CriteriaQuery<Maquinaria> criteria = builder.createQuery(Maquinaria.class);
	      // Definir la tabla (clase) a partir de la cual se hará la consulta
	      Root<Maquinaria> root = criteria.from(Maquinaria.class);
	      // Seleccionar el empleado con el DNI especificado
	      criteria.select(root).where(builder.equal(root.get("codigo"), pCodigo));
	      // Crear un objeto TypedQuery a partir de la consulta construida
	      TypedQuery<Maquinaria> query = session.createQuery(criteria);
	      
	      // Obtener el resultado de la consulta
	      List<Maquinaria> resultados = query.getResultList();
	      if (!resultados.isEmpty()) {
	        resultado = resultados.get(0);
	      }
	    } catch (Exception ex) {
	      System.out.println(ex.getMessage());
	      ex.printStackTrace();
	    } finally {
	      if (session != null) {
	        session.close();
	      }
	      if (factory != null) {
	        factory.close();
	      }
	      StandardServiceRegistryBuilder.destroy(registry);
	    }
	    return resultado;
	  }

	public void actualizarlistado(Maquinaria unaMaquina) {
	    int index = maquinarias.indexOf(unaMaquina);
	    if (index != -1) {
	    	maquinarias.set(index, unaMaquina);
	    }
	}

}
