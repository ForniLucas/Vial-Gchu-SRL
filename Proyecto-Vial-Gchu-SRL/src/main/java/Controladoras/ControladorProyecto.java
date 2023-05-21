package Controladoras;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Domain.ElementoDeSeguridad;
import Domain.Empleado;
import Domain.Especializacion;
import Domain.Maquinaria;
import Domain.Proyecto;
import Domain.RopaDeTrabajo;
import Domain.TipoProyecto;
import Domain.Trabajo;
import Domain.Utiliza;
import Enumeraciones.EstadoProyecto;

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
	      EstadoProyecto cancelado= EstadoProyecto.CANCELADO;
	      //Actualiza el proyecto
	      unProyecto.setEstado(cancelado);
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
	
public void finalizarProyecto(Proyecto unProyecto, LocalDate unaFechaFin) {
		
		// Iniciar sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Transaction transaction = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      transaction = session.beginTransaction();
	      unProyecto.setFechaFin(unaFechaFin);
	      unProyecto.setEstado(EstadoProyecto.FINALIZADO);
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

public void asignarTipoProyecto(Proyecto unProyecto, TipoProyecto unTipo) {
	
	// Iniciar sesión de Hibernate
    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    SessionFactory factory = null;
    Session session = null;
    Transaction transaction = null;
    try {
      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
      session = factory.openSession();
      transaction = session.beginTransaction();
      unProyecto.asignarTipoProyecto(unTipo);
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
	
public LinkedList<Proyecto> buscarNombre (String nombreProyecto) {
		
		// Iniciar la sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    LinkedList<Proyecto> resultados = new LinkedList<Proyecto>();
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
	      criteria.select(root).where(builder.like(root.get("nombre").as(String.class), "%" + nombreProyecto + "%"));
	      TypedQuery<Proyecto> query = session.createQuery(criteria);
	      
	      // Obtener el resultado de la consulta
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
	    	  builder.lessThanOrEqualTo(root.<LocalDate>get("fechaEstFin"), builder.literal(otraFecha))
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

	public void asignarTrabajo(Empleado unEmpleado, Proyecto unProyecto, int horasDeTrabajo, LocalDate fechaInicio, LocalDate fechaEstFin) {
		// Iniciar sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Transaction transaction = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      transaction = session.beginTransaction();
	      
	      Trabajo trabajo = new Trabajo(horasDeTrabajo,fechaInicio,fechaEstFin);
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
	
	
	public void desasociarTrabajo(Trabajo unTrabajo) {
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
	      session.delete(unTrabajo);
	      transaction.commit();
	      
	      
	      
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
	
	public Trabajo buscarTrabajo(Long pId) {
	    // Iniciar la sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Trabajo resultado = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      
	      // Crear un objeto CriteriaBuilder para construir la consulta
	      CriteriaBuilder builder = session.getCriteriaBuilder();
	      // Crear una consulta para buscar el empleado con el DNI especificado
	      CriteriaQuery<Trabajo> criteria = builder.createQuery(Trabajo.class);
	      // Definir la tabla (clase) a partir de la cual se hará la consulta
	      Root<Trabajo> root = criteria.from(Trabajo.class);
	      // Seleccionar el empleado con el DNI especificado
	      criteria.select(root).where(builder.equal(root.get("id"), pId));
	      // Crear un objeto TypedQuery a partir de la consulta construida
	      TypedQuery<Trabajo> query = session.createQuery(criteria);
	      
	      // Obtener el resultado de la consulta
	      List<Trabajo> resultados = query.getResultList();
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
	
	public void asignarUtiliza(Maquinaria unaMaquinaria, Proyecto unProyecto, LocalDate fechaInicio, LocalDate fechaEstFin) {
		// Iniciar sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Transaction transaction = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      transaction = session.beginTransaction();
	      
	      Utiliza unaUtiliza = new Utiliza( fechaInicio, fechaEstFin, null); 
	      										
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
	
	public void desasociarUtiliza(Utiliza unaUtiliza) {
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
	      session.delete(unaUtiliza);
	      transaction.commit();
	      
	      
	      
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
	
	public Utiliza buscarUtiliza(Long pId) {
	    // Iniciar la sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Utiliza resultado = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      
	      // Crear un objeto CriteriaBuilder para construir la consulta
	      CriteriaBuilder builder = session.getCriteriaBuilder();
	      // Crear una consulta para buscar el empleado con el DNI especificado
	      CriteriaQuery<Utiliza> criteria = builder.createQuery(Utiliza.class);
	      // Definir la tabla (clase) a partir de la cual se hará la consulta
	      Root<Utiliza> root = criteria.from(Utiliza.class);
	      // Seleccionar el empleado con el DNI especificado
	      criteria.select(root).where(builder.equal(root.get("id"), pId));
	      // Crear un objeto TypedQuery a partir de la consulta construida
	      TypedQuery<Utiliza> query = session.createQuery(criteria);
	      
	      // Obtener el resultado de la consulta
	      List<Utiliza> resultados = query.getResultList();
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
	      criteria.select(root).where(builder.equal(root.get("id"), unId));
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
	      criteria.select(root).where(builder.equal(root.get("id"), unId));
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
	
	public void crearPlantillaDeTrabajo(Trabajo unTrabajo){
    	try {
    		String rutaImagen = "src/main/java/Vista/img/3.2 400x400.png";
    		Document documento;
    	    FileOutputStream archivo;
    	    documento = new Document();
    	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    	    archivo = new FileOutputStream(unTrabajo.getEmpleado().getApellido()+String.valueOf(unTrabajo.getEmpleado().getDni())+"Proyecto"+unTrabajo.getProyecto().getNombre() + ".pdf");
            PdfWriter.getInstance(documento, archivo);
            documento.open();
            Font font = new Font(FontFamily.TIMES_ROMAN, 18, Font.NORMAL);
            Font fonTable = new Font(FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
          
            

            Image image = null;
            try {
                image =  Image.getInstance(rutaImagen);//carga imagen
                image.scaleAbsolute(100, 100);//cambia tamaño
                image.setAbsolutePosition(475, 735);//coloca imagen en la posicion
                
            } catch (Exception e) {
            }
            
            documento.add(image);//agrega la imagen al documento
            Paragraph texto  = new Paragraph("PLANILLA DE TRABAJO DE UN",font);      
            texto.setAlignment(0);
            documento.add(texto);
            texto  = new Paragraph("EMPLEADO PARA UN PROYECTO",font);      
            texto.setAlignment(0);
            documento.add(texto);
            
            


            documento.add(Chunk.NEWLINE);
            
            

            
            //EMPLEADO
            
            PdfPTable tabla = new PdfPTable(1);
            tabla.setWidthPercentage(100);
            PdfPCell f1 = new PdfPCell(new Phrase("EMPLEADO",fonTable));
            f1.setBackgroundColor(BaseColor.ORANGE);
            tabla.addCell(f1);
            documento.add(tabla);
            
            PdfPTable tabla2= new PdfPTable(2);
            tabla2.setWidthPercentage(100);
            PdfPCell f21 = new PdfPCell(new Phrase("APELLIDO",fonTable));
            PdfPCell f22 = new PdfPCell(new Phrase(unTrabajo.getEmpleado().getApellido(),fonTable));
            tabla2.addCell(f21);
            tabla2.addCell(f22);
            
            documento.add(tabla2);
            
            PdfPTable tabla3= new PdfPTable(2);
            tabla3.setWidthPercentage(100);
            PdfPCell f31 = new PdfPCell(new Phrase("NOMBRE",fonTable));
            PdfPCell f32 = new PdfPCell(new Phrase(unTrabajo.getEmpleado().getNombre(),fonTable));
            tabla3.addCell(f31);
            tabla3.addCell(f32);
            
            documento.add(tabla3);
            
            PdfPTable tabla4= new PdfPTable(2);
            tabla4.setWidthPercentage(100);
            PdfPCell f41 = new PdfPCell(new Phrase("DNI",fonTable));
            PdfPCell f42 = new PdfPCell(new Phrase(String.valueOf(unTrabajo.getEmpleado().getDni()) ,fonTable));
            tabla4.addCell(f41);
            tabla4.addCell(f42);
            
            documento.add(tabla4);
            
            PdfPTable tabla5= new PdfPTable(2);
            tabla5.setWidthPercentage(100);
            PdfPCell f51 = new PdfPCell(new Phrase("TELEFONO",fonTable));
            PdfPCell f52 = new PdfPCell(new Phrase(String.valueOf(unTrabajo.getEmpleado().getTelefono()),fonTable));
            tabla5.addCell(f51);
            tabla5.addCell(f52);
            
            documento.add(tabla5);
            
            PdfPTable tabla6= new PdfPTable(2);
            tabla6.setWidthPercentage(100);
            PdfPCell f61 = new PdfPCell(new Phrase("DIRECCION",fonTable));
            PdfPCell f62 = new PdfPCell(new Phrase(unTrabajo.getEmpleado().getDireccion(),fonTable));
            tabla6.addCell(f61);
            tabla6.addCell(f62);
            
            documento.add(tabla6);
            
            PdfPTable tabla7= new PdfPTable(2);
            tabla7.setWidthPercentage(100);
            PdfPCell f71 = new PdfPCell(new Phrase("FECHA DE NACIMIENTO",fonTable));
            PdfPCell f72 = new PdfPCell(new Phrase(unTrabajo.getEmpleado().getFechaNac().format(formatter),fonTable));
            tabla6.addCell(f71);
            tabla6.addCell(f72);
            
            documento.add(tabla7);
            
            //PROYECTO
            
            tabla = new PdfPTable(1);
            tabla.setWidthPercentage(100);
            f1 = new PdfPCell(new Phrase("PROYECTO",fonTable));
            f1.setBackgroundColor(BaseColor.ORANGE);
            tabla.addCell(f1);
            documento.add(tabla);
            
            tabla2= new PdfPTable(2);
            tabla2.setWidthPercentage(100);
            f21 = new PdfPCell(new Phrase("LEGAJO",fonTable));
            f22 = new PdfPCell(new Phrase(String.valueOf(unTrabajo.getProyecto().getId()),fonTable));
            
            tabla2.addCell(f21);
            tabla2.addCell(f22);
            
            documento.add(tabla2);
            
            tabla3= new PdfPTable(2);
            tabla3.setWidthPercentage(100);
            f31 = new PdfPCell(new Phrase("NOMBRE",fonTable));
            f32 = new PdfPCell(new Phrase(unTrabajo.getProyecto().getNombre(),fonTable));
            tabla3.addCell(f31);
            tabla3.addCell(f32);
            
            documento.add(tabla3);
            
            tabla4= new PdfPTable(2);
            tabla4.setWidthPercentage(100);
            f41 = new PdfPCell(new Phrase("TIPO",fonTable));
            f42 = new PdfPCell(new Phrase(unTrabajo.getProyecto().getTipoProyecto().getTipo().toString(),fonTable));
            f42.setBottom(5);
            tabla4.addCell(f41);
            tabla4.addCell(f42);
            
            documento.add(tabla4);
            
            tabla5= new PdfPTable(2);
            tabla5.setWidthPercentage(100);
            f51 = new PdfPCell(new Phrase("FECHA DE INICIO",fonTable));
            f52 = new PdfPCell(new Phrase(unTrabajo.getProyecto().getFechaInicio().format(formatter),fonTable));
            f52.setBottom(5);
            tabla5.addCell(f51);
            tabla5.addCell(f52);
            
            documento.add(tabla5);
            
            //TRABAJO
            documento.add(Chunk.NEWLINE);

            tabla = new PdfPTable(1);
            tabla.setWidthPercentage(100);
            f1 = new PdfPCell(new Phrase("ACTIVIDADES",fonTable));
            f1.setBackgroundColor(BaseColor.ORANGE);
            tabla.addCell(f1);
            documento.add(tabla);
            
            tabla2= new PdfPTable(2);
            tabla2.setWidthPercentage(100);
            f21 = new PdfPCell(new Phrase("FECHA DE INICIO",fonTable));
            f22 = new PdfPCell(new Phrase(unTrabajo.getFechaInicio().format(formatter),fonTable));
            
            tabla2.addCell(f21);
            tabla2.addCell(f22);
            
            documento.add(tabla2);
            
            tabla3= new PdfPTable(2);
            tabla3.setWidthPercentage(100);
            f31 = new PdfPCell(new Phrase("FECHA ESTIMADA DE FIN",fonTable));
            f32 = new PdfPCell(new Phrase(unTrabajo.getFechaEstFin().format(formatter),fonTable));
            tabla3.addCell(f31);
            tabla3.addCell(f32);
            
            documento.add(tabla3);
            
	            
            tabla4= new PdfPTable(2);
            tabla4.setWidthPercentage(100);
            f41 = new PdfPCell(new Phrase("HORAS TRABAJADAS",fonTable));
            f42 = new PdfPCell(new Phrase(String.valueOf(unTrabajo.getHorasDeTrabajo()),fonTable));
            f42.setBottom(5);
            tabla4.addCell(f41);
            tabla4.addCell(f42);
            
            documento.add(tabla4);
            
            documento.add(Chunk.NEWLINE);
            fonTable.setStyle(Font.BOLD);
            fonTable.setSize(12);
            texto  = new Paragraph("FIRMA  EMPLEADOR:_____________________________________________________ ",fonTable);
            texto.setAlignment(0);
            texto.setIndentationLeft(0);
            documento.add(texto);
            documento.add(Chunk.NEWLINE);
            texto  = new Paragraph("FIRMA EMPLEADO:_______________________________________________________",fonTable);
            texto.setAlignment(0);
            texto.setIndentationLeft(0);
            documento.add(texto);
            
            
            
            documento.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch(DocumentException e){
            System.err.println(e.getMessage());
        }
    }
}
