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




//import Enumeraciones.Elemento;



@Entity
@Table(name = "Proyecto",
        schema = "public")

public class Proyecto implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//LinkedList<Empleado> empleados = new LinkedList<Empleado>();
   // LinkedList<Maquinaria> maquinas = new LinkedList<Maquinaria>();
	
	@Column(name="proyectoid", unique=true, nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	
	@Column(name = "nombre",nullable=false)
    private String nombre;
	
	@Column(name = "fechaInicio",nullable=false)
    private LocalDate fechaInicio;
    
    @Column(name = "FinEstimado",nullable=false)
    private LocalDate fechaEstmiadaFin;
    
    @Column(name = "fechaFin",nullable=true)
    private LocalDate fechaFin;
    
    @Column(name = "estado",nullable=true)
    private String estado; //Se modifico de Boolean a String
    

    
    @OneToOne(cascade = CascadeType.ALL,
    		  fetch = FetchType.LAZY)
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
    
   

    public Proyecto(Long id, LocalDate fechaInicio, LocalDate fechaEstmiadaFin, LocalDate fechaFin, String estado, String nombre) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaEstmiadaFin = fechaEstmiadaFin;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.nombre = nombre;
    }
    
    public Proyecto() {}
    
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
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

    public void asignarEmpleado(Empleado unEmpleado) {
    	empleados.add(unEmpleado);
    }

    public LinkedList<Empleado> getEmpleados() {
        return empleados;
    }

    public void asignar(Maquinaria unaMaquinaria) {
        maquinas.add(unaMaquinaria);
    }

    public LinkedList<Maquinaria> getMaquinas() {
        return maquinas;
    }

    public void addTrabajo(Trabajo comment) {
        Trabajadores.add(comment);
    	 }
    
    public void addMaquina(Utiliza comment) {
        Maquinas.add(comment);
    	 }
}

