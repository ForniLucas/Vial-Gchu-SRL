/**
 * 
 */
package Domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@SuppressWarnings("serial")
@Entity
@Table(name = "Utiliza",
        schema = "public")
public class Utiliza implements java.io.Serializable{
	
	@Column(name="utilizaid", unique=true, nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="maquinaria_id")
	private Maquinaria Maquinaria;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="proyecto_id")
	private Proyecto Proyecto;
	 
	
	@Column(name="inicio",nullable=false)
    private LocalDate fechaInicio; 
	
	@Column(name="finEstimado",nullable=false)
    private LocalDate fechaEstFin;
	
	@Column(name="fin",nullable=false)
    private LocalDate fechaFin;

	/*
	 * 
	
    public Utiliza(int idP, int idM, LocalDate fechaInicio, LocalDate fechaEstFin, LocalDate fechaFin) {
        this.idProjecto = idP;
        this.idMaquinaria = idM;
        this.fechaInicio = fechaInicio;
        this.fechaEstFin = fechaEstFin;
        this.fechaFin = fechaFin;
    }
	 */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaEstFin() {
        return fechaEstFin;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }
/*
 * 

    public int getIdProjecto(){
        return idProjecto; 
    }
    
    public int getIdMaquinaria(){
        return idMaquinaria;
    }
  */   
    
    
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaEstFin(LocalDate fechaEstFin) {
        this.fechaEstFin = fechaEstFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    
    
}

