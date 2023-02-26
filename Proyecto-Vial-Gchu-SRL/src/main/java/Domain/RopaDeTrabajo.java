/**
 * 
 */
package Domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import Enumeraciones.Ropa;

/**
 * @author lucas
 *
 */
@Entity
@Table(name = "RopaDeTrabajo",
        schema = "public")
public class RopaDeTrabajo implements java.io.Serializable{

	@Column(name="ropaTrabajoid", unique=true, nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="empleado_id", nullable=false)
	private Empleado Empleado;
    
    @Column(name = "ropa",nullable=false)
    private Ropa tipo;
    
    @Column(name = "talle",nullable=false)
    private String talle;
    
    @Column(name = "entrega",nullable=false)
    @UpdateTimestamp
    private LocalDate fechaEntrega;
    

    public RopaDeTrabajo( Ropa tipo, String talle, LocalDate fechaEntrega) {
        this.id = null;
        this.tipo = tipo;
        this.talle = talle;
        this.fechaEntrega = fechaEntrega;
    }
    
    public RopaDeTrabajo() {
    	
    }
    
    
    public Empleado getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(Empleado Empleado) {
        this.Empleado = Empleado;
    }

    public Ropa getTipo() {
        return tipo;
    }

    public long getId() {
        return id;
    }

    public void setTipo(Ropa tipo) {
        this.tipo = tipo;
    }

    public String getTalle() {
        return talle;
    }

    public void setTalle(String talle) {
        this.talle = talle;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

}
