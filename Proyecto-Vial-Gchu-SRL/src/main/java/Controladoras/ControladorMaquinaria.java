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

import Domain.Empleado;
import Domain.Especializacion;
import Domain.Maquinaria;
import Domain.Proyecto;
import Domain.RopaDeTrabajo;
import Domain.Service;

public class ControladorMaquinaria {
	private LinkedList<Maquinaria> maquinarias = new LinkedList<Maquinaria>();
	
	public void alta(String codigo, String descripcion, String fabricante, String ubicacionAlmacenamiento ) {
		
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
			Maquinaria maquinaria = new Maquinaria(codigo, descripcion,fabricante,ubicacionAlmacenamiento);
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
	
	public void asignarService (Maquinaria unaMaquina,Service unService) {
		// Iniciar sesión de Hibernate
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    Transaction transaction = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      transaction = session.beginTransaction();
	      
	     
	      unaMaquina.asignarService(unService);;
	      session.update(unaMaquina);
	      
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
	
	
	public LinkedList<Maquinaria> listarMaquinaria() {
		LinkedList<Maquinaria> resultados = new LinkedList<Maquinaria>();
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory factory = null;
		Session session = null;

		try {
			factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			session = factory.openSession();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Maquinaria> criteria = builder.createQuery(Maquinaria.class);
			Root<Maquinaria> root = criteria.from(Maquinaria.class);

			criteria.select(root);
			TypedQuery<Maquinaria> query = session.createQuery(criteria);
			resultados = new LinkedList<Maquinaria>(query.getResultList());
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
	
	public Set<Service> listarServices(String unCodigo) {
	    // Iniciar la sesión de Hibernate
		Set<Service> resultados = new HashSet<Service>(0);
	    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	    SessionFactory factory = null;
	    Session session = null;
	    try {
	      factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	      session = factory.openSession();
	      
	      // Crear un objeto CriteriaBuilder para construir la consulta
	      CriteriaBuilder builder = session.getCriteriaBuilder();

	      CriteriaQuery<Maquinaria> criteria = builder.createQuery(Maquinaria.class);
	      // Definir la tabla (clase) a partir de la cual se hará la consulta
	      Root<Maquinaria> root = criteria.from(Maquinaria.class);
	      criteria.select(root).where(builder.equal(root.get("codigo"), unCodigo));
	      // Crear un objeto TypedQuery a partir de la consulta construida
	      TypedQuery<Maquinaria> query = session.createQuery(criteria);
	      
	      // Obtener el resultado de la consulta
	      List<Maquinaria> resultado = query.getResultList();
	      if (!resultado.isEmpty()) {
	      resultados = resultado.get(0).getServices();
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
	
	 public void crearPlantillaDeMantenimiento(Maquinaria unaMaquina, Service unService){
	    	try {
	    		String rutaImagen = "src/main/java/Vista/img/3.2 400x400.png";
	    		Document documento;
	    	    FileOutputStream archivo;
	    	    documento = new Document();
	    	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	    	    archivo = new FileOutputStream("Service"+unaMaquina.getCodigo() + ".pdf");
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
	            Paragraph texto  = new Paragraph("PLANILLA DE MANTENIMIENTO DE MAQUINARIA",font);      
	            texto.setAlignment(0);
	            documento.add(texto);
	            
	            



	            documento.add(Chunk.NEWLINE);
	            documento.add(Chunk.NEWLINE);
	            documento.add(Chunk.NEWLINE);

	            
	            //MAQUINA
	            
	            PdfPTable tabla = new PdfPTable(1);
	            tabla.setWidthPercentage(100);
	            PdfPCell f1 = new PdfPCell(new Phrase("FICHA TÉCNICA DE LA MÁQUINA/EQUIPO",fonTable));
	            f1.setBackgroundColor(BaseColor.ORANGE);
	            tabla.addCell(f1);
	            documento.add(tabla);
	            
	            PdfPTable tabla2= new PdfPTable(2);
	            tabla2.setWidthPercentage(100);
	            PdfPCell f21 = new PdfPCell(new Phrase("CODIGO",fonTable));
	            PdfPCell f22 = new PdfPCell(new Phrase(unaMaquina.getCodigo(),fonTable));
	            tabla2.addCell(f21);
	            tabla2.addCell(f22);
	            
	            documento.add(tabla2);
	            
	            PdfPTable tabla3= new PdfPTable(2);
	            tabla3.setWidthPercentage(100);
	            PdfPCell f31 = new PdfPCell(new Phrase("DESCRIPCION",fonTable));
	            PdfPCell f32 = new PdfPCell(new Phrase(unaMaquina.getDescripcion(),fonTable));
	            tabla3.addCell(f31);
	            tabla3.addCell(f32);
	            
	            documento.add(tabla3);
	            
	            PdfPTable tabla4= new PdfPTable(2);
	            tabla4.setWidthPercentage(100);
	            PdfPCell f41 = new PdfPCell(new Phrase("FABRICANTE",fonTable));
	            PdfPCell f42 = new PdfPCell(new Phrase(unaMaquina.getFabricante(),fonTable));
	            tabla4.addCell(f41);
	            tabla4.addCell(f42);
	            
	            documento.add(tabla4);
	            
	            //SERVICE
	            
	            tabla = new PdfPTable(1);
	            tabla.setWidthPercentage(100);
	            f1 = new PdfPCell(new Phrase("SERVICE",fonTable));
	            f1.setBackgroundColor(BaseColor.ORANGE);
	            tabla.addCell(f1);
	            documento.add(tabla);
	            
	            tabla2= new PdfPTable(2);
	            tabla2.setWidthPercentage(100);
	            f21 = new PdfPCell(new Phrase("FECHA DE ENTRADA",fonTable));
	            f22 = new PdfPCell(new Phrase(unService.getFechaInicio().format(formatter),fonTable));
	            
	            tabla2.addCell(f21);
	            tabla2.addCell(f22);
	            
	            documento.add(tabla2);
	            
	            tabla3= new PdfPTable(2);
	            tabla3.setWidthPercentage(100);
	            f31 = new PdfPCell(new Phrase("FECHA DE SALIDA",fonTable));
	            f32 = new PdfPCell(new Phrase(unService.getFechaFin().format(formatter),fonTable));
	            tabla3.addCell(f31);
	            tabla3.addCell(f32);
	            
	            documento.add(tabla3);
	            
	            tabla4= new PdfPTable(2);
	            tabla4.setWidthPercentage(100);
	            f41 = new PdfPCell(new Phrase("TAREAS/OBSERVACIONES",fonTable));
	            f42 = new PdfPCell(new Phrase(unService.getObservaciones(),fonTable));
	            f42.setBottom(5);
	            tabla4.addCell(f41);
	            tabla4.addCell(f42);
	            
	            documento.add(tabla4);
	            
	            
	            documento.close();
	            JOptionPane.showMessageDialog(null, "El archivo PDF se a creado correctamente!");
	        } catch (FileNotFoundException e) {
	            System.err.println(e.getMessage());
	        } catch(DocumentException e){
	            System.err.println(e.getMessage());
	        }
	    }

}
