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

@Entity
@Table(name = "Utiliza",
        schema = "public")
public class Utiliza implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	@Column(name="utilizaid", unique=true, nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {
	           CascadeType.PERSIST, 
	           CascadeType.MERGE
	       })
	@JoinColumn(name="maquinariaid")
	private Maquinaria Maquinaria;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {
	           CascadeType.PERSIST, 
	           CascadeType.MERGE
	        })
	@JoinColumn(name="proyectoid")
	private Proyecto Proyecto;
	
	@Column(name="inicio",nullable=false)
    private LocalDate fechaInicio; 
	
	@Column(name="finEstimado",nullable=false)
    private LocalDate fechaEstFin;
	
	@Column(name="fin",nullable=true)
    private LocalDate fechaFin;


	
    public Utiliza( LocalDate fechaInicio, LocalDate fechaEstFin, LocalDate fechaFin) {
        this.id=null;
        this.fechaInicio = fechaInicio;
        this.fechaEstFin = fechaEstFin;
        this.fechaFin = fechaFin;
    }

    public Utiliza() {
    	
    }
    

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaEstFin() {
        return fechaEstFin;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }


    public Proyecto getProyecto(){
        return Proyecto; 
    }
    
    public Maquinaria getMaquinaria(){
        return Maquinaria;
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
    
    public void asignar(Proyecto unProyecto, Maquinaria unaMaquina) {
        this.Maquinaria=unaMaquina;
        this.Proyecto=unProyecto;
    }
    
    
}

