/**
 * 
 */
package Domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




/**
 * @author lucas
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Trabajo",
        schema = "public")
public class Trabajo implements java.io.Serializable{
	@Column(name="trabajoid", unique=true, nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	

	
	 @ManyToOne(fetch = FetchType.LAZY, cascade = {
	            CascadeType.PERSIST, 
	            CascadeType.MERGE
	        })
	 @JoinColumn(name="empleado_id")
	 private Empleado Empleado;
	 
	 @ManyToOne(fetch = FetchType.LAZY, cascade = {
	            CascadeType.PERSIST, 
	            CascadeType.MERGE
	        })
	 @JoinColumn(name="proyecto_id")
	 private Proyecto Proyecto;
	
	@Column(name="horasTrabajo",nullable=false)
	private int horasDeTrabajo;
	
	@Column(name="inicio",nullable=false)
    private LocalDate fechaInicio; 
	
	@Column(name="finEstimado",nullable=false)
    private LocalDate fechaEstFin;
	
	@Column(name="fin",nullable=false)
    private LocalDate fechaFin;
    
/*
    public Trabajo(int idP, int idE, int horasDeTrabajo, LocalDate fechaInicio, LocalDate fechaEstFin, LocalDate fechaFin) {
        this.idProyecto = idP;
        this.idEmpleado = idE;
        this.horasDeTrabajo = horasDeTrabajo;
        this.fechaInicio = fechaInicio;
        this.fechaEstFin = fechaEstFin;
        this.fechaFin = fechaFin;
    }
 */
    public int getHorasDeTrabajo() {
        return horasDeTrabajo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaEstFin() {
        return fechaEstFin;
    }
   
    /*
    public int getIdEmpleado(){
        return idEmpleado;
    }
    
    public int getIdProjecto(){
        return idProyecto;
    }
	 */
    
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setHorasDeTrabajo(int horasDeTrabajo) {
        this.horasDeTrabajo = horasDeTrabajo;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaEstFin(LocalDate fechaEstFin) {
        this.fechaEstFin = fechaEstFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    public void asignar(Proyecto unProyecto, Empleado unEmpleado) {
        this.Empleado=unEmpleado;
        this.Proyecto=unProyecto;
    	
    	unProyecto.addTrabajo(this);
        unEmpleado.addTrabajo(this);
        
    }
    
}

