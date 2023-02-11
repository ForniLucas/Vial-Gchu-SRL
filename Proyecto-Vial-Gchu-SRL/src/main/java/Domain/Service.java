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
    
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="maquinaria_id", nullable=false)
	private Maquinaria Maquinaria;

 /*   
    public Service(int id, LocalDate fechaInicio, LocalDate fechaFin, String observaciones) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.observaciones = observaciones;
    }
*/
    
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
    
    
    
}
