/**
 * 
 */
package Domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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



import Enumeraciones.Elemento;

/**
 * @author lucas
 *
 */


@Entity
@Table(name = "Proyecto",
        schema = "public")

public class Proyecto implements java.io.Serializable{
	
	@Column(name="proyectoid", unique=true, nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	
	@Column(name = "nombre",nullable=false)
    private String nombre;
	
    @Column(name = "inicio",nullable=false)
    private LocalDate fechaInicio;
    
    @Column(name = "FinEstimado",nullable=false)
    private LocalDate fechaEstmiadaFin;
    
    @Column(name = "finEfectivo",nullable=true)
    private LocalDate fechaFin;
    
    @Column(name = "estado",nullable=true)
    private boolean estado;
    

    
    @OneToOne(cascade = CascadeType.ALL,
    		  fetch = FetchType.LAZY)
    @JoinColumn(name = "tipoproyecto_id")
        private TipoProyecto TipoProyecto;
    
    
    @OneToMany(
    	    cascade = CascadeType.ALL,
    	    orphanRemoval = true
    	)
    @JoinTable(
    	    name = "Trabajo",
    	    joinColumns = @JoinColumn(name = "proyecto_id"),
    	    inverseJoinColumns = @JoinColumn(name = "empleado_id")
    	)
    private Set<Empleado> Empleados = new HashSet<Empleado>(0);
    
    @OneToMany(
    	    cascade = CascadeType.ALL,
    	    orphanRemoval = true
    	)
    @JoinTable(
    	    name = "Utiliza",
    	    joinColumns = @JoinColumn(name = "proyecto_id"),
    	    inverseJoinColumns = @JoinColumn(name = "maquinaria_id")
    	)
    private Set<Maquinaria> Maquinarias = new HashSet<Maquinaria>(0);

   /** public Proyecto(int id, LocalDate fechaInicio, LocalDate fechaEstmiadaFin, LocalDate fechaFin, boolean estado, String nombre) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaEstmiadaFin = fechaEstmiadaFin;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.nombre = nombre;

        empleados = new LinkedList<>();
        maquinas = new LinkedList<>();
    }
	*
	*/
    public long getId() {
        return id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaEstmiadaFin() {
        return fechaEstmiadaFin;
    }

    public void setFechaEstmiadaFin(LocalDate fechaEstmiadaFin) {
        this.fechaEstmiadaFin = fechaEstmiadaFin;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
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
/*
 * 

    public void asignarEmpleado(Empleado unEmpleado) {
        empleados.add(unEmpleado);
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void asignar(Maquinaria unaMaquinaria) {
        maquinas.add(unaMaquinaria);
    }

    public List<Maquinaria> getMaquinas() {
        return maquinas;
    }
    */ 
    public void addEmpleado(Empleado comment) {
        Empleados.add(comment);
    }

}

