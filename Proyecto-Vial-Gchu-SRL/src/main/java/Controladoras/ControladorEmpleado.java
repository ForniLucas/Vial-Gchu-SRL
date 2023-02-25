package Controladoras;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Domain.ElementoDeSeguridad;
import Domain.Empleado;
import Domain.Especializacion;
import Domain.RopaDeTrabajo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.*;
import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;


public class ControladorEmpleado 
{
	private LinkedList<Empleado> empleados = new LinkedList<Empleado>(); 
	
	
	public void alta(String nombre, String apellido, int dni, int telefono, String direccion, LocalDate fechaNac) {
	    // Iniciar la sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Transaction transaction = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      transaction = session.beginTransaction();
	      
	      // Agregar el empleado a la lista y guardar en la base de datos
	      Empleado empleado = new Empleado(nombre, apellido, dni, telefono, direccion, fechaNac);
	      empleados.add(empleado);
	      session.save(empleado);
	      transaction.commit();
	      
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

	public void asignarElementoDeSeguridad(Empleado unEmpleado, ElementoDeSeguridad unElemento) {
		// Iniciar sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Transaction transaction = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      transaction = session.beginTransaction();
	      
	      // Actualizar empleado en la base de datos
	      unEmpleado.addElemento(unElemento);
	      session.update(unEmpleado);
	      transaction.commit();
	      
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
	
	public void asignarRopaDeTrabajo(Empleado unEmpleado,RopaDeTrabajo unaRopa) {
		// Iniciar sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Transaction transaction = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      transaction = session.beginTransaction();
	      
	      // Actualizar empleado en la base de datos
	      unEmpleado.addRopa(unaRopa);
	      session.update(unEmpleado);
	      
	      transaction.commit();
	      
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

	public void modificar(Empleado unEmpleado) {
	    // Iniciar sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Transaction transaction = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      transaction = session.beginTransaction();
	      
	      // Actualizar empleado en la base de datos
	      session.update(unEmpleado);
	      transaction.commit();
	      
	      // Actualizar el empleado en la lista 
	      actualizarlistado(unEmpleado);
	      
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

	public void bajaLogica(Empleado unEmpleado) {
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
	      unEmpleado.setEstadoBaja();;
	      session.update(unEmpleado);
	      transaction.commit();
	      
	      // Actualizar el empleado en la lista 
	      actualizarlistado(unEmpleado);
	      
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

	public void baja(Empleado unEmpleado) {
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
	      session.delete(unEmpleado);
	      transaction.commit();
	      
	      // Eliminar el empleado de la lista
	      empleados.remove(unEmpleado);
	      
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

	public Empleado buscarDNI(int pDNI) {
	    // Iniciar la sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Empleado resultado = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      
	      // Crear un objeto CriteriaBuilder para construir la consulta
	      CriteriaBuilder builder = session.getCriteriaBuilder();
	      // Crear una consulta para buscar el empleado con el DNI especificado
	      CriteriaQuery<Empleado> criteria = builder.createQuery(Empleado.class);
	      // Definir la tabla (clase) a partir de la cual se hará la consulta
	      Root<Empleado> root = criteria.from(Empleado.class);
	      // Seleccionar el empleado con el DNI especificado
	      criteria.select(root).where(builder.equal(root.get("dni"), pDNI));
	      // Crear un objeto TypedQuery a partir de la consulta construida
	      TypedQuery<Empleado> query = session.createQuery(criteria);
	      
	      // Obtener el resultado de la consulta
	      List<Empleado> resultados = query.getResultList();
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

	public Empleado buscarApellidoyNombre(String pNombre, String pApellido) {
	    // Inicializar las variables para la sesión y la fábrica de sesiones
	    SessionFactory factory = null;
	    Session session = null;
	    Empleado empleado = null;

	    try {
	      // Crear una fábrica de sesiones
	      factory = new Configuration().configure().buildSessionFactory();
	      // Abrir una sesión
	      session = factory.openSession();
	      // Iniciar una transacción
	      Transaction transaction = session.beginTransaction();

	      // Crear un objeto CriteriaBuilder para construir la consulta
	      CriteriaBuilder builder = session.getCriteriaBuilder();
	      // Crear una consulta para buscar el empleado con el nombre y apellido especificado
	      CriteriaQuery<Empleado> criteria = builder.createQuery(Empleado.class);
	      // Definir la tabla (clase) a partir de la cual se hará la consulta
	      Root<Empleado> root = criteria.from(Empleado.class);
	      // Seleccionar el empleado con el nombre y apellido especificados
	      criteria.select(root).where(
	          builder.and(
	              builder.equal(root.get("nombre"), pNombre),
	              builder.equal(root.get("apellido"), pApellido)
	          )
	      );
	      // Crear un objeto TypedQuery a partir de la consulta construida
	      TypedQuery<Empleado> typedQuery = session.createQuery(criteria);
	      // Obtener el resultado de la consulta
	      empleado = typedQuery.getSingleResult();

	      // Realizar un commit de la transacción
	      transaction.commit();
	    } catch (Exception ex) {
	      if (session != null) {
	        // Realizar un rollback en caso de una excepción
	        session.getTransaction().rollback();
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
	    }

	    return empleado;
	  }
	
	
	public void actualizarlistado(Empleado unEmpleado) {
	    int index = empleados.indexOf(unEmpleado);
	    if (index != -1) {
	      empleados.set(index, unEmpleado);
	    }
	}
	
	public LinkedList<Empleado> listarEmpleados() {
	    // Iniciar la sesión de Hibernate
		LinkedList<Empleado> resultados = new LinkedList<Empleado>();
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      
	      // Crear un objeto CriteriaBuilder para construir la consulta
	      CriteriaBuilder builder = session.getCriteriaBuilder();

	      CriteriaQuery<Empleado> criteria = builder.createQuery(Empleado.class);
	      // Definir la tabla (clase) a partir de la cual se hará la consulta
	      Root<Empleado> root = criteria.from(Empleado.class);
	      // Seleccionar todos los empleados
	      criteria.select(root);
	      // Crear un objeto TypedQuery a partir de la consulta construida
	      TypedQuery<Empleado> query = session.createQuery(criteria);
	      
	      // Obtener el resultado de la consulta
	      resultados = new LinkedList<Empleado>(query.getResultList()); //Aca ya obtenemos el listado de los empleados obtenidos de la consulta
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
	    return resultados;
	  }
	
	public Set<Especializacion> listarEspecializacion(int unDni) {
	    // Iniciar la sesión de Hibernate
		Set<Especializacion> resultados = new HashSet<Especializacion>(0);
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      
	      // Crear un objeto CriteriaBuilder para construir la consulta
	      CriteriaBuilder builder = session.getCriteriaBuilder();

	      CriteriaQuery<Empleado> criteria = builder.createQuery(Empleado.class);
	      // Definir la tabla (clase) a partir de la cual se hará la consulta
	      Root<Empleado> root = criteria.from(Empleado.class);
	      criteria.select(root).where(builder.equal(root.get("dni"), unDni));
	      // Crear un objeto TypedQuery a partir de la consulta construida
	      TypedQuery<Empleado> query = session.createQuery(criteria);
	      
	      // Obtener el resultado de la consulta
	      List<Empleado> resultado = query.getResultList();
	      if (!resultado.isEmpty()) {
	      resultados = resultado.get(0).getEspecializaciones();
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
	    return resultados;
	  }
	
	public Set<RopaDeTrabajo> listarRopaDeTrabajo(int unDni) {
	    // Iniciar la sesión de Hibernate
		Set<RopaDeTrabajo> resultados = new HashSet<RopaDeTrabajo>(0);
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      
	      // Crear un objeto CriteriaBuilder para construir la consulta
	      CriteriaBuilder builder = session.getCriteriaBuilder();

	      CriteriaQuery<Empleado> criteria = builder.createQuery(Empleado.class);
	      // Definir la tabla (clase) a partir de la cual se hará la consulta
	      Root<Empleado> root = criteria.from(Empleado.class);
	      criteria.select(root).where(builder.equal(root.get("dni"), unDni));
	      // Crear un objeto TypedQuery a partir de la consulta construida
	      TypedQuery<Empleado> query = session.createQuery(criteria);
	      
	      // Obtener el resultado de la consulta
	      List<Empleado> resultado = query.getResultList();
	      if (!resultado.isEmpty()) {
	      resultados = resultado.get(0).getRopas();
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
	    return resultados;
	  }
	
	public Set<ElementoDeSeguridad> listarElementosDeSeguridad(int unDni) {
	    // Iniciar la sesión de Hibernate
		Set<ElementoDeSeguridad> resultados = new HashSet<ElementoDeSeguridad>(0);
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      
	      // Crear un objeto CriteriaBuilder para construir la consulta
	      CriteriaBuilder builder = session.getCriteriaBuilder();

	      CriteriaQuery<Empleado> criteria = builder.createQuery(Empleado.class);
	      // Definir la tabla (clase) a partir de la cual se hará la consulta
	      Root<Empleado> root = criteria.from(Empleado.class);
	      criteria.select(root).where(builder.equal(root.get("dni"), unDni));
	      // Crear un objeto TypedQuery a partir de la consulta construida
	      TypedQuery<Empleado> query = session.createQuery(criteria);
	      
	      // Obtener el resultado de la consulta
	      List<Empleado> resultado = query.getResultList();
	      if (!resultado.isEmpty()) {
	      resultados = resultado.get(0).getElementos();
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
	    return resultados;
	  }

}