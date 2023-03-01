/**
 * 
 */
package Domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
//import java.util.LinkedList;
//import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import Enumeraciones.EstadoProyecto;


/**
 * @author lucas
 *
 */

@Entity
@Table(name = "Proyecto",
        schema = "public")

public class Proyecto implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	

	
	@Column(name="proyectoid", unique=true, nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	
	@Column(name = "nombre",nullable=false)
    private String nombre;
	
	@Column(name = "fechaInicio",nullable=false)
    private LocalDate fechaInicio;
    
    @Column(name = "fechaEstFin",nullable=false)
    private LocalDate fechaEstFin;
    
    @Column(name = "fechaFin",nullable=true)
    private LocalDate fechaFin;
    
    @Column(name = "estado",nullable=true)
    private EstadoProyecto estado; //Se modifico de Boolean a String
    
    @OneToOne(cascade = CascadeType.ALL,
    		  fetch = FetchType.EAGER)
    @JoinColumn(name = "tipoproyecto_id")
        private TipoProyecto TipoProyecto;
       
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
    	    orphanRemoval = true
        , mappedBy = "Proyecto")
    private Set<Trabajo> Trabajadores = new HashSet<Trabajo>(0);
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
    	    orphanRemoval = true
        , mappedBy = "Proyecto")
    private Set<Utiliza> Maquinas = new HashSet<Utiliza>(0);
    
   

    public Proyecto(LocalDate fechaInicio, LocalDate fechaEstFin, LocalDate fechaFin, EstadoProyecto estado, String nombre) {
        this.id = null;
        this.fechaInicio = fechaInicio;
        this.fechaEstFin = fechaEstFin;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.nombre = nombre;
    }
    
    public Proyecto() {}
    
    public int getId() {
        return this.id != null ? this.id.intValue() : -1;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaEstFin() {
        return fechaEstFin;
    }

    public void setFechaEstFin(LocalDate fechaEstFin) {
        this.fechaEstFin = fechaEstFin;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public EstadoProyecto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProyecto estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoProyecto getTipoProyecto() {
        return this.TipoProyecto;
    }

    public void asignarTipoProyecto(TipoProyecto unTipoProyecto) {

        this.TipoProyecto = unTipoProyecto;
    }
    
    public Set<Trabajo> getTrabajadores(){
    	return Trabajadores;
    }
    
    public Set<Utiliza> getMaquinasUtilizas(){
    	return Maquinas;
    }

    public LinkedList<Empleado> getEmpleados() {
    	LinkedList<Empleado> empleados = new LinkedList<Empleado>();
    	Set<Trabajo> trabajadores = getTrabajadores();
    	
    	for (Trabajo trabajo : trabajadores) {
    	    empleados.add(trabajo.getEmpleado());
    	}
    	
    	return empleados;
    }


    public LinkedList<Maquinaria> getMaquinas() {
    	LinkedList<Maquinaria> maquinas = new LinkedList<Maquinaria>();
    	Set<Utiliza> Maquinas = getMaquinasUtilizas();
    	
    	for (Utiliza maquina : Maquinas) {
    		maquinas.add(maquina.getMaquinaria());
    	}
    	
    	return maquinas;
    }
    

    public void addTrabajo(Trabajo comment) {
        Trabajadores.add(comment);
    	 }
    
    public void addMaquina(Utiliza comment) {
        Maquinas.add(comment);
    	 }
}

