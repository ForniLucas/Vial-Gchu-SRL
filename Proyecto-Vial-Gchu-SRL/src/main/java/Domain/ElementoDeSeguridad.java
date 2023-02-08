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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import Enumeraciones.Elemento;

/**
 * @author lucas
 *
 */
@Entity
@Table(name = "Elemento de Seguridad",
        schema = "public")
public class ElementoDeSeguridad implements java.io.Serializable {

	@Column(name="seguridadid", unique=true, nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	
	 @Column(name="elemento",nullable=false)
	 private Elemento tipo;
	 
    @Column(name = "entrega",nullable=false)
    @UpdateTimestamp
    private LocalDate fechaEntrega;
    
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="empleado_id", nullable=false)
	private Empleado Empleado;
    
/**
    public ElementoDeSeguridad(int iD, Elemento tipo, LocalDate fechaEntrega, Empleado Empleado) {
        this.id = iD;
        this.tipo = tipo;
        this.fechaEntrega = fechaEntrega;
        this.Empleado = Empleado;
    }
*/
    public Empleado getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(Empleado Empleado) {
        this.Empleado = Empleado;
    }

    public long getiD() {
        return id;
    }

    public Elemento getTipo() {
        return tipo;
    }

    public void setTipo(Elemento tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

}
