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


import Enumeraciones.Profesion;
import Enumeraciones.RolEmpleado;


/**
 * @author lucas
 *
 */
@Entity
@Table(name = "Especialización",
        schema = "public")
public class Especializacion implements java.io.Serializable {

	@Column(name="especializacionid", unique=true, nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	
	@Column(name="profesion",nullable=false)
    private Profesion profesion;

    @Column(name = "Actualización",nullable=false)
    @UpdateTimestamp
    private LocalDate fechaActualizacion;

    @Column(name = "Rol",nullable=false)
    private RolEmpleado rol;

    @Column(name = "Fin",nullable=true)
    private LocalDate fechaFin;
    
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="empleado_id", nullable=false)
	private Empleado Empleado;
    
    
    
    /**
     * public Especializacion(int id, Profesion tipo, LocalDate fechaActualizacion, RolEmpleado unRol) {
        this.id = id;
        this.profesion = tipo;
        this.fechaActualizacion = fechaActualizacion;
        this.rol = unRol;
    }
     *
     */
    

    public Profesion getTipo() {
        return profesion;
    }

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public Long getId() {
        return this.id;
    }

    public RolEmpleado getRol() {
        return rol;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setTipo(Profesion tipo) {
        this.profesion = tipo;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public void setRol(RolEmpleado unRol) {
        this.rol = unRol;
    }

}

