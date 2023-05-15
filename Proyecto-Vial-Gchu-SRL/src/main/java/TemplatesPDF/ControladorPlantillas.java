package TemplatesPDF;

import com.itextpdf.kernel.geom.Path;
import com.itextpdf.kernel.pdf.canvas.parser.clipper.Paths;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import VistaUsuario.Principal;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.BevelBorder;
import javax.swing.JOptionPane;

/**
 * @author lucas
 *
 */
public class ControladorPlantillas {
	String rutaImagen = "src/main/java/Vista/img/3.2 400x400.png";


	Font fuente = FontFactory.getFont("Times New Roman");

	    
	    Document documento;
	    FileOutputStream archivo;
	    Paragraph titulo;
	    
	    public ControladorPlantillas()
	    {  
	        documento = new Document();
	        titulo  = new Paragraph("Plantilla Personalizada");        
	    }
	    
	    public void crearPlantilla(){
	        try {
	            archivo = new FileOutputStream("pruebaPaTincholo" + ".pdf");
	            PdfWriter.getInstance(documento, archivo);
	            documento.open();
	            Font font = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

	            titulo.setAlignment(1);

	            Image image = null;
	            try {
	                image =  Image.getInstance(rutaImagen);//carga imagen
	                image.scaleAbsolute(50, 50);//cambia tamaño
	                //image.setAbsolutePosition(415, 750);//coloca imagen en la posicion
	                
	            } catch (Exception e) {
	            }
	            
	            //documento.add(image);//agrega la imagen al documento
	            
	            documento.add(titulo);
	            
	            PdfPTable tabla = new PdfPTable(2);
	            tabla.setWidthPercentage(100);
	            PdfPCell name = new PdfPCell(new Phrase("Planilla de ",fuente));
	            PdfPCell logo = new PdfPCell(image);
	            
	            documento.add(new Paragraph("Director: " + "lucas",fuente));
	            
	            documento.add(Chunk.NEWLINE);
	            
	            Paragraph texto = new Paragraph("La factibilidad operativa se refiere a la capacidad de un proyecto o plan de ser implementado con éxito en un entorno operativo real. "
                    	+ "En otras palabras, se trata de evaluar si un proyecto o plan es viable desde el punto de vista de su ejecución y mantenimiento a largo plazo.\n\n"
                    	+ "La factibilidad operativa es importante porque ayuda a determinar si un proyecto puede implementarse de manera efectiva en términos de recursos humanos, técnicos y financieros. "
                    	+ "Para ello, es necesario considerar factores como la disponibilidad de personal capacitado, la infraestructura tecnológica necesaria y el presupuesto disponible.\n\n"
                    	+ "Sin embargo, la factibilidad operativa puede ser difícil de implementar debido a varios factores. Uno de ellos es la complejidad del entorno operativo, "
                    	+ "que puede incluir factores internos y externos que afectan la capacidad de implementación del proyecto. Además, pueden surgir problemas imprevistos durante la implementación del proyecto que dificulten su ejecución efectiva.",font);
	        
	            texto.setAlignment(Element.ALIGN_JUSTIFIED);
	            documento.add(texto);
	            
	            documento.add(Chunk.NEWLINE);
	            /**
	             * @author lucas
	             *
	            
	            PdfPTable tabla = new PdfPTable(4);
	            tabla.setWidthPercentage(100);
	            PdfPCell name = new PdfPCell(new Phrase("Nombre"));
	            name.setBackgroundColor(BaseColor.ORANGE);
	            PdfPCell age = new PdfPCell(new Phrase("Edad"));
	            age.setBackgroundColor(BaseColor.ORANGE);
	            PdfPCell tel = new PdfPCell(new Phrase("Telefono"));
	            tel.setBackgroundColor(BaseColor.ORANGE);
	            PdfPCell address = new PdfPCell(new Phrase("Dirección"));
	            address.setBackgroundColor(BaseColor.ORANGE);
	            
	            tabla.addCell(name);
	            tabla.addCell(age);
	            tabla.addCell(tel);
	            tabla.addCell(address);            
	             */ 
	            
	           // for(Persona persona: this.personas){
	                tabla.addCell("Lucas");//persona.getNombre());                
	                tabla.addCell("23");//persona.getEdad()+"");
	                tabla.addCell("3446347569");//persona.getTelefono());
	                tabla.addCell("Peron 491");//persona.getDireccion());                
	           // }
	            
	            documento.add(tabla);          
	            documento.add(Chunk.NEWLINE);
	            documento.add(new Paragraph("Fecha: " + "hoy"));
	            
	            documento.close();
	            JOptionPane.showMessageDialog(null, "El archivo PDF se a creado correctamente!");
	        } catch (FileNotFoundException e) {
	            System.err.println(e.getMessage());
	        } catch(DocumentException e){
	            System.err.println(e.getMessage());
	        }
	        
	    }
	    
	    public void crear(){
	    	try {
	    	 archivo = new FileOutputStream("inicial" + ".pdf");
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
	            PdfPCell f22 = new PdfPCell(new Phrase("Edad",fonTable));
	            tabla2.addCell(f21);
	            tabla2.addCell(f22);
	            
	            documento.add(tabla2);
	            
	            PdfPTable tabla3= new PdfPTable(2);
	            tabla3.setWidthPercentage(100);
	            PdfPCell f31 = new PdfPCell(new Phrase("DESCRIPCION",fonTable));
	            PdfPCell f32 = new PdfPCell(new Phrase("Edad",fonTable));
	            tabla3.addCell(f31);
	            tabla3.addCell(f32);
	            
	            documento.add(tabla3);
	            
	            PdfPTable tabla4= new PdfPTable(2);
	            tabla4.setWidthPercentage(100);
	            PdfPCell f41 = new PdfPCell(new Phrase("FABRICANTE",fonTable));
	            PdfPCell f42 = new PdfPCell(new Phrase("Edad",fonTable));
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
	            f22 = new PdfPCell(new Phrase("Edad",fonTable));
	            tabla2.addCell(f21);
	            tabla2.addCell(f22);
	            
	            documento.add(tabla2);
	            
	            tabla3= new PdfPTable(2);
	            tabla3.setWidthPercentage(100);
	            f31 = new PdfPCell(new Phrase("FECHA DE SALIDA",fonTable));
	            f32 = new PdfPCell(new Phrase("Edad",fonTable));
	            tabla3.addCell(f31);
	            tabla3.addCell(f32);
	            
	            documento.add(tabla3);
	            
	            tabla4= new PdfPTable(2);
	            tabla4.setWidthPercentage(100);
	            f41 = new PdfPCell(new Phrase("TAREAS/OBSERVACIONES",fonTable));
	            f42 = new PdfPCell(new Phrase("Edad",fonTable));
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
	    
	