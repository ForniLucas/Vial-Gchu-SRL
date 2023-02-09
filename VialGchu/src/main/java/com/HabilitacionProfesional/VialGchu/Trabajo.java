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

@Entity
@Table(name = "Trabajo",
        schema = "public")
public class Trabajo implements java.io.Serializable{
	@Column(name="trabajoid", unique=true, nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

	@Column(name="horasTrabajo",nullable=false)
	private int horasDeTrabajo;
	
	
	
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name="empleado_id")
	 private Empleado Empleado;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name="proyecto_id")
	 private Proyecto Proyecto;
	 
	 
	 
	

/*
    public Trabajo(int idP, int idE, int horasDeTrabajo, LocalDate fechaInicio, LocalDate fechaEstFin, LocalDate fechaFin) {
        this.idProyecto = idP;
        this.idEmpleado = idE;
        this.horasDeTrabajo = horasDeTrabajo;
        this.fechaInicio = fechaInicio;
        this.fechaEstFin = fechaEstFin;
        this.fechaFin = fechaFin;
    }
 */
    public int getHorasDeTrabajo() {
        return horasDeTrabajo;
    }

 

    public void asignar(Proyecto unProyecto, Empleado unEmpleado) {
        this.Empleado=unEmpleado;
        this.Proyecto=unProyecto;
    	
    	unProyecto.addEmpleado(unEmpleado);
        unEmpleado.addProyecto(unProyecto);
        
    }
    
    /*
    public int getIdEmpleado(){
        return idEmpleado;
    }
    
    public int getIdProjecto(){
        return idProyecto;
    }
	 */
    
    

    public void setHorasDeTrabajo(int horasDeTrabajo) {
        this.horasDeTrabajo = horasDeTrabajo;
    }

 
  
    public void setEmpleado(Empleado horasDeTrabajo) {
        this.Empleado = horasDeTrabajo;
    }
    
    public void setProyecto(Proyecto horasDeTrabajo) {
        this.Proyecto = horasDeTrabajo;
    }
    
}


