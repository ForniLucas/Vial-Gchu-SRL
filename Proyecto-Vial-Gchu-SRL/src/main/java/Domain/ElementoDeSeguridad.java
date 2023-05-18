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
//import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import Enumeraciones.Elemento;



@Entity
@Table(name = "ElementoDeSeguridad",
        schema = "public")
public class ElementoDeSeguridad implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name="seguridadid", unique=true, nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	
	 @Column(name="elemento",nullable=false)
	 private Elemento tipo;
	 
    @Column(name = "entrega",nullable=false)
    @UpdateTimestamp
    private LocalDate fechaEntrega;
    
    @ManyToOne(fetch=FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="empleado_id", nullable=false)
	private Empleado Empleado;
    

    public ElementoDeSeguridad(Elemento tipo, LocalDate fechaEntrega, Empleado Empleado) {
        this.id = null;
        this.tipo = tipo;
        this.fechaEntrega = fechaEntrega;
        this.Empleado = Empleado;
    }
   public ElementoDeSeguridad() {}     
        
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
