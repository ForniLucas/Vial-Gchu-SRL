package Controladoras;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
import Domain.RopaDeTrabajo;
import Domain.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.PrePersist;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

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
	
	public void desasociarElementoDeSeguridad(ElementoDeSeguridad unElemento) {
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
	      session.delete(unElemento);
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
	
	public void desasociarRopaDeTrabajo(RopaDeTrabajo unaRopa) {
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
	      session.delete(unaRopa);
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

	

	public void asignarEspecializacion(Empleado unEmpleado,Especializacion unaEspecializacion) {
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
	      unEmpleado.addEspecializacion(unaEspecializacion);;
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

	public LinkedList<Empleado> buscarApellidoyNombre(String pNombre, String pApellido) {
	    // Inicializar las variables para la sesión y la fábrica de sesiones
	    SessionFactory factory = null;
	    Session session = null;
	    LinkedList<Empleado> resultados = new LinkedList<Empleado>();
	    

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
	              builder.like(root.get("nombre").as(String.class), "%" + pNombre + "%"),
	              builder.like(root.get("apellido").as(String.class), "%" + pApellido + "%")
	          )
	      );
	      // Crear un objeto TypedQuery a partir de la consulta construida
	      TypedQuery<Empleado> typedQuery = session.createQuery(criteria);
	      // Obtener el resultado de la consulta
	      resultados = new LinkedList<Empleado>(typedQuery.getResultList());

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

	    return resultados;
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
	
	 public void crearPlantillaDeEntregaDeElementoDeSeguridad(Empleado unEmpleado, ElementoDeSeguridad unElemento){
	    	try {
	    		String rutaImagen = "src/main/java/Vista/img/3.2 400x400.png";
	    		Document documento;
	    	    FileOutputStream archivo;
	    	    documento = new Document();
	    	    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	    	    archivo = new FileOutputStream(unEmpleado.getApellido()+String.valueOf(unEmpleado.getDni())+unElemento.getTipo().toString() + ".pdf");
	            PdfWriter.getInstance(documento, archivo);
	            documento.open();
	            Font font = new Font(FontFamily.TIMES_ROMAN, 18, Font.NORMAL);
	            Font fonTable = new Font(FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
	            Font fonText = new Font(FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
	            

	            Image image = null;
	            try {
	                image =  Image.getInstance(rutaImagen);//carga imagen
	                image.scaleAbsolute(100, 100);//cambia tamaño
	                image.setAbsolutePosition(475, 735);//coloca imagen en la posicion
	                
	            } catch (Exception e) {
	            }
	            
	            documento.add(image);//agrega la imagen al documento
	            Paragraph texto  = new Paragraph("CONSTANCIA DE ENTREGA DE",font);      
	            texto.setAlignment(0);
	            documento.add(texto);
	            texto  = new Paragraph("PROTECCION PERSONAL",font);      
	            texto.setAlignment(0);
	            documento.add(texto);
	            
	            


	            documento.add(Chunk.NEWLINE);
	            
	            texto  = new Paragraph("De acuerdo a lo estipulado en la Ley 16.744, Art. 68 inciso tres:",fonText);
	            texto.setAlignment(0);
	            texto.setIndentationLeft(0);
	            documento.add(texto);
	            
	            
	            texto  = new Paragraph("“Las empresas deberán proporcionar a sus trabajadores, los equipos e implementos de ",fonText);
	            texto.setAlignment(0);
	            texto.setIndentationLeft(0);
	            documento.add(texto);
	            
	            
	            texto  = new Paragraph("protección necesarios, no pudiendo en caso alguno cobrarles su valor”. ",fonText);
	            texto.setAlignment(0);
	            texto.setIndentationLeft(0);
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
	            PdfPCell f22 = new PdfPCell(new Phrase(unEmpleado.getApellido(),fonTable));
	            tabla2.addCell(f21);
	            tabla2.addCell(f22);
	            
	            documento.add(tabla2);
	            
	            PdfPTable tabla3= new PdfPTable(2);
	            tabla3.setWidthPercentage(100);
	            PdfPCell f31 = new PdfPCell(new Phrase("NOMBRE",fonTable));
	            PdfPCell f32 = new PdfPCell(new Phrase(unEmpleado.getNombre(),fonTable));
	            tabla3.addCell(f31);
	            tabla3.addCell(f32);
	            
	            documento.add(tabla3);
	            
	            PdfPTable tabla4= new PdfPTable(2);
	            tabla4.setWidthPercentage(100);
	            PdfPCell f41 = new PdfPCell(new Phrase("DNI",fonTable));
	            PdfPCell f42 = new PdfPCell(new Phrase(String.valueOf(unEmpleado.getDni()) ,fonTable));
	            tabla4.addCell(f41);
	            tabla4.addCell(f42);
	            
	            documento.add(tabla4);
	            
	            PdfPTable tabla5= new PdfPTable(2);
	            tabla5.setWidthPercentage(100);
	            PdfPCell f51 = new PdfPCell(new Phrase("TELEFONO",fonTable));
	            PdfPCell f52 = new PdfPCell(new Phrase(String.valueOf(unEmpleado.getTelefono()),fonTable));
	            tabla5.addCell(f51);
	            tabla5.addCell(f52);
	            
	            documento.add(tabla5);
	            
	            PdfPTable tabla6= new PdfPTable(2);
	            tabla6.setWidthPercentage(100);
	            PdfPCell f61 = new PdfPCell(new Phrase("DIRECCION",fonTable));
	            PdfPCell f62 = new PdfPCell(new Phrase(unEmpleado.getDireccion(),fonTable));
	            tabla6.addCell(f61);
	            tabla6.addCell(f62);
	            
	            documento.add(tabla6);
	            
	            //ESPECIALIZACION
	            
	            tabla = new PdfPTable(1);
	            tabla.setWidthPercentage(100);
	            f1 = new PdfPCell(new Phrase("DESCRIPCIÓN DEL PUESTO DE TRABAJO DEL EMPLEADO",fonTable));
	            f1.setBackgroundColor(BaseColor.ORANGE);
	            tabla.addCell(f1);
	            documento.add(tabla);
	            
	            tabla2= new PdfPTable(2);
	            tabla2.setWidthPercentage(100);
	            f21 = new PdfPCell(new Phrase("PROFESIÓN",fonTable));
	            f22 = new PdfPCell(new Phrase(unEmpleado.buscarEspecializacionActual().getTipo().toString(),fonTable));
	            
	            tabla2.addCell(f21);
	            tabla2.addCell(f22);
	            
	            documento.add(tabla2);
	            
	            tabla3= new PdfPTable(2);
	            tabla3.setWidthPercentage(100);
	            f31 = new PdfPCell(new Phrase("ROL",fonTable));
	            f32 = new PdfPCell(new Phrase(unEmpleado.buscarEspecializacionActual().getRol().toString(),fonTable));
	            tabla3.addCell(f31);
	            tabla3.addCell(f32);
	            
	            documento.add(tabla3);
	            
	            //ELEMENTO DE SEGURIDAD
	            documento.add(Chunk.NEWLINE);

	            tabla = new PdfPTable(1);
	            tabla.setWidthPercentage(100);
	            f1 = new PdfPCell(new Phrase("ELEMENTO DE SEGURIDAD",fonTable));
	            f1.setBackgroundColor(BaseColor.ORANGE);
	            tabla.addCell(f1);
	            documento.add(tabla);
	            
	            tabla2= new PdfPTable(2);
	            tabla2.setWidthPercentage(100);
	            f21 = new PdfPCell(new Phrase("ELEMENTO",fonTable));
	            f22 = new PdfPCell(new Phrase(unElemento.getTipo().toString(),fonTable));
	            
	            tabla2.addCell(f21);
	            tabla2.addCell(f22);
	            
	            documento.add(tabla2);
	            
	            tabla3= new PdfPTable(2);
	            tabla3.setWidthPercentage(100);
	            f31 = new PdfPCell(new Phrase("FECHA DE RECEPCIÓN",fonTable));
	            f32 = new PdfPCell(new Phrase(unElemento.getFechaEntrega().toString()));//format(formatter),fonTable
	            tabla3.addCell(f31);
	            tabla3.addCell(f32);
	            
	            documento.add(tabla3);
	            
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
	            fonText.setStyle(Font.BOLD);
	            fonText.setSize(9);
	            texto  = new Paragraph("El trabajador se compromete a mantener los elementos de protección personal en buen estado, y además de solicitar el cambio de este cuando se encuentre en mal estado. ",fonText);
	            texto.setAlignment(0);
	            texto.setIndentationLeft(0);
	            documento.add(texto);
	            
	            
	            documento.close();
	            JOptionPane.showMessageDialog(null, "El archivo PDF se a creado correctamente!");
	        } catch (FileNotFoundException e) {
	            System.err.println(e.getMessage());
	        } catch(DocumentException e){
	            System.err.println(e.getMessage());
	        }
	    }
	
	 public void crearPlantillaDeEntregaDeRopaDeTrabajo(Empleado unEmpleado, RopaDeTrabajo unaRopa){
	    	try {
	    		String rutaImagen = "src/main/java/Vista/img/3.2 400x400.png";
	    		Document documento;
	    	    FileOutputStream archivo;
	    	    documento = new Document();
	    	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	    	    archivo = new FileOutputStream(unEmpleado.getApellido()+String.valueOf(unEmpleado.getDni())+unaRopa.getTipo().toString() + ".pdf");
	            PdfWriter.getInstance(documento, archivo);
	            documento.open();
	            Font font = new Font(FontFamily.TIMES_ROMAN, 18, Font.NORMAL);
	            Font fonTable = new Font(FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
	            Font fonText = new Font(FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
	            

	            Image image = null;
	            try {
	                image =  Image.getInstance(rutaImagen);//carga imagen
	                image.scaleAbsolute(100, 100);//cambia tamaño
	                image.setAbsolutePosition(475, 735);//coloca imagen en la posicion
	                
	            } catch (Exception e) {
	            }
	            
	            documento.add(image);//agrega la imagen al documento
	            Paragraph texto  = new Paragraph("CONSTANCIA DE ENTREGA DE",font);      
	            texto.setAlignment(0);
	            documento.add(texto);
	            texto  = new Paragraph("ROPA DE TRABAJO",font);      
	            texto.setAlignment(0);
	            documento.add(texto);
	            
	            


	            documento.add(Chunk.NEWLINE);
	            
	            texto  = new Paragraph("De acuerdo a lo estipulado en la Ley 16.744, Art. 68 inciso tres:",fonText);
	            texto.setAlignment(0);
	            texto.setIndentationLeft(0);
	            documento.add(texto);
	            
	            
	            texto  = new Paragraph("“Las empresas deberán proporcionar a sus trabajadores, los equipos e implementos de ",fonText);
	            texto.setAlignment(0);
	            texto.setIndentationLeft(0);
	            documento.add(texto);
	            
	            
	            texto  = new Paragraph("protección necesarios, no pudiendo en caso alguno cobrarles su valor”. ",fonText);
	            texto.setAlignment(0);
	            texto.setIndentationLeft(0);
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
	            PdfPCell f22 = new PdfPCell(new Phrase(unEmpleado.getApellido(),fonTable));
	            tabla2.addCell(f21);
	            tabla2.addCell(f22);
	            
	            documento.add(tabla2);
	            
	            PdfPTable tabla3= new PdfPTable(2);
	            tabla3.setWidthPercentage(100);
	            PdfPCell f31 = new PdfPCell(new Phrase("NOMBRE",fonTable));
	            PdfPCell f32 = new PdfPCell(new Phrase(unEmpleado.getNombre(),fonTable));
	            tabla3.addCell(f31);
	            tabla3.addCell(f32);
	            
	            documento.add(tabla3);
	            
	            PdfPTable tabla4= new PdfPTable(2);
	            tabla4.setWidthPercentage(100);
	            PdfPCell f41 = new PdfPCell(new Phrase("DNI",fonTable));
	            PdfPCell f42 = new PdfPCell(new Phrase(String.valueOf(unEmpleado.getDni()) ,fonTable));
	            tabla4.addCell(f41);
	            tabla4.addCell(f42);
	            
	            documento.add(tabla4);
	            
	            PdfPTable tabla5= new PdfPTable(2);
	            tabla5.setWidthPercentage(100);
	            PdfPCell f51 = new PdfPCell(new Phrase("TELEFONO",fonTable));
	            PdfPCell f52 = new PdfPCell(new Phrase(String.valueOf(unEmpleado.getTelefono()),fonTable));
	            tabla5.addCell(f51);
	            tabla5.addCell(f52);
	            
	            documento.add(tabla5);
	            
	            PdfPTable tabla6= new PdfPTable(2);
	            tabla6.setWidthPercentage(100);
	            PdfPCell f61 = new PdfPCell(new Phrase("DIRECCION",fonTable));
	            PdfPCell f62 = new PdfPCell(new Phrase(unEmpleado.getDireccion(),fonTable));
	            tabla6.addCell(f61);
	            tabla6.addCell(f62);
	            
	            documento.add(tabla6);
	            
	            //ESPECIALIZACION
	            
	            tabla = new PdfPTable(1);
	            tabla.setWidthPercentage(100);
	            f1 = new PdfPCell(new Phrase("DESCRIPCIÓN DEL PUESTO DE TRABAJO DEL EMPLEADO",fonTable));
	            f1.setBackgroundColor(BaseColor.ORANGE);
	            tabla.addCell(f1);
	            documento.add(tabla);
	            
	            tabla2= new PdfPTable(2);
	            tabla2.setWidthPercentage(100);
	            f21 = new PdfPCell(new Phrase("PROFESIÓN",fonTable));
	            f22 = new PdfPCell(new Phrase(unEmpleado.buscarEspecializacionActual().getTipo().toString(),fonTable));
	            
	            tabla2.addCell(f21);
	            tabla2.addCell(f22);
	            
	            documento.add(tabla2);
	            
	            tabla3= new PdfPTable(2);
	            tabla3.setWidthPercentage(100);
	            f31 = new PdfPCell(new Phrase("ROL",fonTable));
	            f32 = new PdfPCell(new Phrase(unEmpleado.buscarEspecializacionActual().getRol().toString(),fonTable));
	            tabla3.addCell(f31);
	            tabla3.addCell(f32);
	            
	            documento.add(tabla3);
	            
	            //ROPA DE TRABAJO
	            documento.add(Chunk.NEWLINE);

	            tabla = new PdfPTable(1);
	            tabla.setWidthPercentage(100);
	            f1 = new PdfPCell(new Phrase("ROPA DE TRABAJO",fonTable));
	            f1.setBackgroundColor(BaseColor.ORANGE);
	            tabla.addCell(f1);
	            documento.add(tabla);
	            
	            tabla2= new PdfPTable(2);
	            tabla2.setWidthPercentage(100);
	            f21 = new PdfPCell(new Phrase("ROPA",fonTable));
	            f22 = new PdfPCell(new Phrase(unaRopa.getTipo().toString(),fonTable));
	            
	            tabla2.addCell(f21);
	            tabla2.addCell(f22);
	            
	            documento.add(tabla2);
	            
	            tabla3= new PdfPTable(2);
	            tabla3.setWidthPercentage(100);
	            f31 = new PdfPCell(new Phrase("TALLE",fonTable));
	            f32 = new PdfPCell(new Phrase(unaRopa.getTalle(),fonTable));
	            tabla3.addCell(f31);
	            tabla3.addCell(f32);
	            
	            documento.add(tabla3);
	            
		            
	            tabla4= new PdfPTable(2);
	            tabla4.setWidthPercentage(100);
	            f41 = new PdfPCell(new Phrase("FECHA DE RECEPCIÓN",fonTable));
	            f42 = new PdfPCell(new Phrase(unaRopa.getFechaEntrega().format(formatter),fonTable));
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
	            fonText.setStyle(Font.BOLD);
	            fonText.setSize(9);
	            texto  = new Paragraph("El trabajador se compromete a mantener los elementos de protección personal en buen estado, y además de solicitar el cambio de este cuando se encuentre en mal estado. ",fonText);
	            texto.setAlignment(0);
	            texto.setIndentationLeft(0);
	            documento.add(texto);
	            
	            
	            documento.close();
	            JOptionPane.showMessageDialog(null, "El archivo PDF se a creado correctamente!");
	        } catch (FileNotFoundException e) {
	            System.err.println(e.getMessage());
	        } catch(DocumentException e){
	            System.err.println(e.getMessage());
	        }
	    }
}