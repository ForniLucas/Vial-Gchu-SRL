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

import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Tincho
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "Service",
        schema = "public")
public class Service implements java.io.Serializable{
	
	@Column(name="serviceid", unique=true, nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "incio", nullable=false)
    @UpdateTimestamp
    private LocalDate fechaInicio;
    
    @Column(name = "fin", nullable=true)
    private LocalDate fechaFin;
    
    @Column(name = "observaciones", nullable=true)
    private String observaciones;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="maquinaria_id", nullable=false)
	private Maquinaria Maquinaria;


    public Service(LocalDate fechaInicio, LocalDate fechaFin, String observaciones) {
        this.id = null;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.observaciones = observaciones;
    }
    
    public Service() {
    }
    
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }
    
    public long getId(){
        return id;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public Maquinaria getMaquinaria() {
        return Maquinaria;
    }

    public void setMaquinaria(Maquinaria unaMaquinaria) {
        this.Maquinaria = unaMaquinaria;
    }
    
    
}
