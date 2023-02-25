package Domain;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * @author lucas
 *
 */

@Entity
@Table(name = "Empleado",
        schema = "public")

public class Empleado implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="empleadoid", unique=true, nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	
	 @Column(name="nombre",nullable=false)
	 private String nombre;
	 
	 @Column(name="apellido",nullable=false)
	 private String apellido;
	 
	 @Column(name="dni",nullable=false)
	 private int dni;
	 
	 @Column(name="telefono",nullable=true)
	 private int telefono;
	 
	 @Column(name="direccion",nullable=false)
	 private String direccion;
	 
	 @Column(name="fechaNac",nullable=false)
	 private LocalDate fechaNac;
	 
	 @Column(name="estado",nullable=true)
     private boolean estado;
	 
	 @OneToMany(fetch=FetchType.LAZY, mappedBy="Empleado",cascade = CascadeType.ALL,orphanRemoval = true)
	 private Set<Especializacion> Especializaciones = new HashSet<Especializacion>(0);
	 
	 @OneToMany(fetch=FetchType.LAZY, mappedBy="Empleado",cascade = CascadeType.ALL,orphanRemoval = true)
	 private Set<RopaDeTrabajo> RopasDeTrabajo = new HashSet<RopaDeTrabajo>(0);
	 
	 @OneToMany(fetch=FetchType.LAZY, mappedBy="Empleado",cascade = CascadeType.ALL,orphanRemoval = true)
	 private Set<ElementoDeSeguridad> ElementosDeSeguridad = new HashSet<ElementoDeSeguridad>(0);
	 
	 
	 
@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
	    	    orphanRemoval = true
        , mappedBy = "Empleado")
    private Set<Trabajo> Trabajos = new HashSet<Trabajo>(0);

  
    public Empleado (String nombre, String apellido, int dni, int telefono, String direccion, LocalDate fechaNac) {
        this.id = null;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaNac = fechaNac;
        this.estado = true;
    }
    
    public Empleado() {
    }

      
    public long getId() {
        return this.id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String Apellido) {
        this.apellido = Apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int Telefono) {
        this.telefono = Telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public int getEdad() {
        return Period.between(fechaNac, LocalDate.now()).getYears();
        // Utilizamos la libreria Period de JAVA, que maneja tipos Date y obtubimos 
        // el tiempo entre la fechad de nacimiento y la actual para luego calcular la cantida de años.
    }

    public void setFechaNac(LocalDate FechaNac) {
        this.fechaNac = FechaNac;
    }

    public void setEstadoAlta() {
        this.estado = true;
    }

    public void setEstadoBaja() {
        this.estado = false;
    }

    public boolean getEstado() {
        return this.estado;
    }

    public boolean verificarEmpleadoDNI(int unDNI) {
        return (unDNI == this.dni);
    }

    public boolean verificarEmpleadoNombreyApellido(String unNombre, String unApellido) {
        return ((unNombre.equals(this.nombre)) && (unApellido.equals(this.apellido)));

    }


    
    public void addEspecializacion(Especializacion unaEspecializacion) {
   	
    	if (Especializaciones == null) {
    		this.Especializaciones.add(unaEspecializacion);
    	}
    	else {
    		this.finalizarEspecializacion(LocalDate.now());
    		this.Especializaciones.add(unaEspecializacion);
    	}

    }

    public void finalizarEspecializacion(LocalDate unDia) {
        
    	this.buscarEspecializacionActual().setFechaFin(unDia);
    }
 
     
    public Especializacion buscarEspecializacionActual() {
    	
    	Especializacion esp = new Especializacion();
    	
    	for(Especializacion espe : Especializaciones) {
    		if (espe.getFechaFin() == null) {
    			esp = espe;
    		}
    	}
    	
    	return esp;
    }
    
    
    
    
    public void addTrabajo(Trabajo comment) {
    	     Trabajos.add(comment);
        }
    
    public void addRopa(RopaDeTrabajo unaRopa) {
	     RopasDeTrabajo.add(unaRopa);
   }
    
    public void addElemento(ElementoDeSeguridad unElemento) {
	     ElementosDeSeguridad.add(unElemento);
  }
    
    
    public void modificarEmpleado(Empleado unEmpleado) {
        this.apellido = unEmpleado.apellido;
        this.nombre = unEmpleado.nombre;
        this.dni = unEmpleado.dni;
        this.direccion = unEmpleado.direccion;
        this.fechaNac = unEmpleado.fechaNac;
        this.telefono = unEmpleado.telefono;

    }
}

