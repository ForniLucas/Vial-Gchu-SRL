package com.HabilitacionProfesional.VialGchu;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;



@Entity
@Table(name = "Proyecto",
        schema = "public")

public class Proyecto implements java.io.Serializable{
	
	@Column(name="proyectoid", unique=true, nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	
	@Column(name = "nombre",nullable=false)
    private String nombre;
	
    @Column(name = "inicio",nullable=false)
    @UpdateTimestamp
    private LocalDate fechaInicio;
    
    
    @Column(name = "estado",nullable=true)
    private boolean estado;
    
    
    @OneToMany(
    	    cascade = CascadeType.ALL,
    	    orphanRemoval = true
    	)
    	@JoinTable(
    	    name = "Trabajo",
    	    joinColumns = @JoinColumn(name = "proyecto_id"),
    	    inverseJoinColumns = @JoinColumn(name = "empleado_id")
    	)
    	private Set<Empleado> Empleados = new HashSet<Empleado>(0);

    


   /** public Proyecto(int id, LocalDate fechaInicio, LocalDate fechaEstmiadaFin, LocalDate fechaFin, boolean estado, String nombre) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaEstmiadaFin = fechaEstmiadaFin;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.nombre = nombre;

        empleados = new LinkedList<>();
        maquinas = new LinkedList<>();
    }
	*
	*/
    public long getId() {
        return id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

   

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addEmpleado(Empleado comment) {
        Empleados.add(comment);
    }



}

