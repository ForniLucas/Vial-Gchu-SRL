package com.HabilitacionProfesional.VialGchu;


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
 * @author lucas
 *
 */
@Entity
@Table(name = "Especializacion",
        schema = "public")
public class Especializacion implements java.io.Serializable {

	@Column(name="especializacionid", unique=true, nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="empleado_id")
	     private Empleado Empleado;

	

	@Column(name="profesion",nullable=false)
    private String profesion;

    @Column(name = "Actualización",nullable=false)
    @UpdateTimestamp
    private LocalDate fechaActualizacion;

    
    /**
     * public Especializacion(int id, Profesion tipo, LocalDate fechaActualizacion, RolEmpleado unRol) {
        this.id = id;
        this.profesion = tipo;
        this.fechaActualizacion = fechaActualizacion;
        this.rol = unRol;
    }
     *
     */
    
    public Especializacion() {}

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public Long getId() {
        return this.id;
    }

    
    public void setEmpleado(Empleado unEmpleado) {
        this.Empleado = unEmpleado;
    }


    public void setProfesion(String tipo) {
        this.profesion = tipo;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }


}


