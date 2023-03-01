package Controladoras;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
import Domain.Especializacion;
import Domain.Maquinaria;
import Domain.Proyecto;
import Domain.TipoProyecto;
import Domain.Trabajo;
import Domain.Utiliza;

public class ControladorProyecto {

	private LinkedList<Proyecto> proyectos = new LinkedList<Proyecto>(); 
	
	public void alta(Proyecto poyecto) {
		
		// Iniciar la sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Transaction transaction = null;
	    
	    try {
	    	factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		    session = factory.openSession();
		    transaction = session.beginTransaction();
		    
		    // Agregar el proytecto a la lista y guardar en la base de datos    
		    //Proyecto poyecto = new Proyecto(fechaInicio, fechaEstimadaFin, fechaFin, estado, nombre);  // a "estado" podriamos directamente poner "iniciado" o algo asi.
		    proyectos.add(poyecto);
		    session.save(poyecto);
		    transaction.commit();
		   
	    }catch(Exception ex) {
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
	
	public void bajaLogica(Proyecto unProyecto) {
		
		// Iniciar la sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Transaction transaction = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      transaction = session.beginTransaction();
	      
	      //Actualiza el proyecto
	      unProyecto.setEstado("Cancelado");
	      unProyecto.setFechaFin(LocalDate.now());
	      session.update(unProyecto);
	      transaction.commit();

	      //Actualizar el proyecto en la lista
	      actualizarlistado(unProyecto);
	      
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
	
	public void baja(Proyecto unProyecto) {
	
		// Iniciar la sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Transaction transaction = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      transaction = session.beginTransaction();
	      
	      //Elimina el proyecto de la base de datos
	      session.delete(unProyecto);
	      transaction.commit();
	      
	      //Eliminar el proyecto de la lista
	      proyectos.remove(unProyecto);
	      
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
		
	public void modificar(Proyecto unProyecto) {
		
		// Iniciar sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Transaction transaction = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      transaction = session.beginTransaction();
	      
	      //Actualiza el proyecto
	      session.update(unProyecto);
	      transaction.commit();
	      
	      //Actualizar el proyecto en la lista
	      actualizarlistado(unProyecto);
	      
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
	
public void finalizarProyecto(Proyecto unProyecto) {
		
		// Iniciar sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Transaction transaction = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      transaction = session.beginTransaction();
	      
	      //Actualiza el proyecto
	      session.update(unProyecto);
	      transaction.commit();
	      
	      //Actualizar el proyecto en la lista
	      actualizarlistado(unProyecto);
	      
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
	
	
	public Proyecto buscarID (int idProyecto) {
		
		// Iniciar la sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Proyecto resultado = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
		
	      // Crear un objeto CriteriaBuilder para construir la consulta
	      CriteriaBuilder builder = session.getCriteriaBuilder();
	      // Crear una consulta para buscar el empleado con el DNI especificado
	      CriteriaQuery<Proyecto> criteria = builder.createQuery(Proyecto.class);
	      // Definir la tabla (clase) a partir de la cual se hará la consulta
	      Root<Proyecto> root = criteria.from(Proyecto.class);
	      // Seleccionar el empleado con el DNI especificado
	      criteria.select(root).where(builder.equal(root.get("id"), idProyecto));
	      TypedQuery<Proyecto> query = session.createQuery(criteria);
	      
	      // Obtener el resultado de la consulta
	      List<Proyecto> resultados = query.getResultList();
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
	
	public LinkedList<Proyecto> buscarEntreFechas(LocalDate unaFecha, LocalDate otraFecha){
		LinkedList<Proyecto> resultados = new LinkedList<Proyecto>();
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;

	    try {
	    	// Creación de la fabrica de sesión a partir del registro de servicios estándar
	    	factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

	    	// Apertura de una sesión con la base de datos
	    	session = factory.openSession();

	    	// Creación de un objeto CriteriaBuilder para crear la consulta
	    	CriteriaBuilder builder = session.getCriteriaBuilder();

	    	// Creación de una consulta de tipo Proyecto
	    	CriteriaQuery<Proyecto> criteria = builder.createQuery(Proyecto.class);

	    	// Definición del origen de los datos para la consulta (Proyecto)
	    	Root<Proyecto> root = criteria.from(Proyecto.class);

	    	// Seleccionar los proyectos que cumplan con las condiciones de fecha
	    	criteria.select(root).where(
	    	  // "inicio" debe ser mayor o igual a "unaFecha"
	    	  builder.greaterThanOrEqualTo(root.<LocalDate>get("fechaInicio"), builder.literal(unaFecha)),
	    	  // "finEfectivo" debe ser menor o igual a "otraFecha"
	    	  builder.lessThanOrEqualTo(root.<LocalDate>get("fechaFin"), builder.literal(otraFecha))
	    	);

	    	// Creación de un objeto TypedQuery a partir de la consulta
	    	TypedQuery<Proyecto> query = session.createQuery(criteria);

	    	// Ejecución de la consulta y asignación de los resultados a la lista "resultados"
	    	resultados = new LinkedList<Proyecto>(query.getResultList());

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
		
	public LinkedList<Proyecto> listarProyectos() {
		LinkedList<Proyecto> resultados = new LinkedList<Proyecto>();
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory factory = null;
		Session session = null;

		try {
			factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			session = factory.openSession();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Proyecto> criteria = builder.createQuery(Proyecto.class);
			Root<Proyecto> root = criteria.from(Proyecto.class);

			criteria.select(root);
			TypedQuery<Proyecto> query = session.createQuery(criteria);
			resultados = new LinkedList<Proyecto>(query.getResultList());
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

	public void actualizarlistado(Proyecto unProyecto) {
	    int index = proyectos.indexOf(unProyecto);
	    if (index != -1) {
	    	proyectos.set(index, unProyecto);
	    }
	}

	public void asignarTrabajo(Empleado unEmpleado, Proyecto unProyecto) {
		// Iniciar sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Transaction transaction = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      transaction = session.beginTransaction();
	      
	      Trabajo trabajo = new Trabajo();
	      trabajo.asignar(unProyecto, unEmpleado);
	      unEmpleado.addTrabajo(trabajo);
	      unProyecto.addTrabajo(trabajo);
	      
	      
	      //Actualiza el proyecto
	      session.save(trabajo);
	      session.update(unEmpleado);
	      session.update(unProyecto);
	      transaction.commit();
	      
	      //Actualizar el proyecto en la lista
	      actualizarlistado(unProyecto);
	      
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
	
	public void asignarUtiliza(Maquinaria unaMaquinaria, Proyecto unProyecto) {
		// Iniciar sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Transaction transaction = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      transaction = session.beginTransaction();
	      
	      Utiliza unaUtiliza = new Utiliza(); //Deberiamos pasarle los argumentos correspondientes?
	      										//Debemos pasarle los parametros
	      unaUtiliza.asignar(unProyecto, unaMaquinaria);
	      unaMaquinaria.addTrabajo(unaUtiliza);
	      unProyecto.addMaquina(unaUtiliza);
	      
	      
	      //Actualiza el proyecto
	      session.save(unaUtiliza);
	      session.update(unaMaquinaria);
	      session.update(unProyecto);
	      transaction.commit();
	      
	      //Actualizar el proyecto en la lista
	      actualizarlistado(unProyecto);
	      
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
	
	public Set<Utiliza> listarUtiliza(Long unId) {
	    // Iniciar la sesión de Hibernate
		Set<Utiliza> resultados = new HashSet<Utiliza>(0);
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      
	      // Crear un objeto CriteriaBuilder para construir la consulta
	      CriteriaBuilder builder = session.getCriteriaBuilder();

	      CriteriaQuery<Proyecto> criteria = builder.createQuery(Proyecto.class);
	      // Definir la tabla (clase) a partir de la cual se hará la consulta
	      Root<Proyecto> root = criteria.from(Proyecto.class);
	      criteria.select(root).where(builder.equal(root.get("proyectoid"), unId));
	      // Crear un objeto TypedQuery a partir de la consulta construida
	      TypedQuery<Proyecto> query = session.createQuery(criteria);
	      
	      // Obtener el resultado de la consulta
	      List<Proyecto> resultado = query.getResultList();
	      if (!resultado.isEmpty()) {
	      resultados = resultado.get(0).getMaquinasUtilizas();
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
	
	public Set<Trabajo> listarTrabaja(Long unId) {
	    // Iniciar la sesión de Hibernate
		Set<Trabajo> resultados = new HashSet<Trabajo>(0);
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      
	      // Crear un objeto CriteriaBuilder para construir la consulta
	      CriteriaBuilder builder = session.getCriteriaBuilder();

	      CriteriaQuery<Proyecto> criteria = builder.createQuery(Proyecto.class);
	      // Definir la tabla (clase) a partir de la cual se hará la consulta
	      Root<Proyecto> root = criteria.from(Proyecto.class);
	      criteria.select(root).where(builder.equal(root.get("proyectoid"), unId));
	      // Crear un objeto TypedQuery a partir de la consulta construida
	      TypedQuery<Proyecto> query = session.createQuery(criteria);
	      
	      // Obtener el resultado de la consulta
	      List<Proyecto> resultado = query.getResultList();
	      if (!resultado.isEmpty()) {
	      resultados = resultado.get(0).getTrabajadores();
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
