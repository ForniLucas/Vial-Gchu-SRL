package com.HabilitacionProfesional.VialGchu;



import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.tuple.GenerationTiming;



@Entity
@Table(name="Empleado"
    ,schema="public"
)


public class Empleado  implements java.io.Serializable {
@Column(name="empid", unique=true, nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;
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
    
    @Column(name = "updated_on")
	@UpdateTimestamp
	private LocalDate updatedOn;
    
    @OneToOne( cascade = CascadeType.ALL,
    	fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_id")
    private TipoDeProyecto tipoproyect;
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="Empleado")
    private Set<Especializacion> Especializacion = new HashSet<Especializacion>(0);
    
    
    @OneToMany(
    	    cascade = CascadeType.ALL,
    	    orphanRemoval = true
    	)
    	@JoinTable(
    	    name = "Trabajo",
    	    joinColumns = @JoinColumn(name = "empleado_id"),
    	    inverseJoinColumns = @JoinColumn(name = "proyecto_id")
    	)
    	private Set<Proyecto> Proyectos = new HashSet<Proyecto>(0);
	// private List<Especializacion> Especializacion= ;
    /*
     * 
   
	 @OneToMany(mappedBy="empleado") 
	 @Column(name="Especializacion",nullable=true)
	  private List<Especializacion> Especializacion=new ArrayList<Especializacion>();
	   */
    //@Column(name="fechaNac",nullable=false)
   // private Date fechaNac;
    //@Column(name="Especializacion",nullable=false)
    //private Especializacion Especializacion;
    @Column(name="estado",nullable=true)
    private boolean estado;
   



//public Empleado(long idEmpleado, String nombre, String apellido, int dni, int telefono, String direccion) {
   //     this.id= idEmpleado;
   //     this.nombre = nombre;
   //     this.apellido = apellido;
   //     this.dni = dni;
   //     this.telefono = telefono;
   //     this.direccion = direccion;
   //     //this.fechaNac = fechaNac;
   //     this.estado=true;
   // }


    public long getId(){
        return this.id;
    }
    
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }
    
    public void setProyecto(TipoDeProyecto Nombre) {
        this.tipoproyect = Nombre;
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
    
    public void setEsp(Especializacion esp) {
        this.Especializacion.add(esp);
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

   // public Date getFechaNac() {
    //    return fechaNac;
   // }
    
    public void addProyecto(Proyecto comment) {
        Proyectos.add(comment);
    }
    
    
    
    
   // public int getEdad() {
    //    return Period.between(fechaNac.toLocalDate(), LocalDate.now()).getYears(); 
    // Utilizamos la libreria Period de JAVA, que maneja tipos Date y obtubimos 
    // el tiempo entre la fechad de nacimiento y la actual para luego calcular la cantida de años.
    //}

    //public void setFechaNac(Date FechaNac) {
    //    this.fechaNac = FechaNac;
   // }
    
     public void setEstadoAlta() {
        this.estado = true;
    }
    
    public void setEstadoBaja() {
        this.estado = false;
    }

    public boolean getDadoDeBaja() {
        return this.estado;
    }

    
    public boolean verificarEmpleadoDNI(int unDNI) {
        return (unDNI == this.dni);
    }
            
    public boolean verificarEmpleadoNombreyApellido(String unNombre, String unApellido){
        return ((unNombre.equals(this.nombre)) && (unApellido.equals(this.apellido)));
       
    }
    
   // public void setEspecializacion(Especializacion unaEspecializacion) {this.Especializacion.add(unaEspecializacion);}
    
    
    public void modificarEmpleado(Empleado unEmpleado){
        this.apellido= unEmpleado.apellido;
        this.nombre= unEmpleado.nombre;
        this.dni= unEmpleado.dni;
        this.direccion= unEmpleado.direccion;
      //  this.fechaNac=unEmpleado.fechaNac;
        this.telefono= unEmpleado.telefono;
        
        }
}