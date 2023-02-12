package com.HabilitacionProfesional.VialGchu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 *
 * @author Tincho
 */
@Entity
@Table(name = "TipoDeProyecto",
        schema = "public")
public class TipoDeProyecto implements java.io.Serializable{

	@Column(name="tipoProyectoid", unique=true, nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@OneToOne(mappedBy = "tipoproyect")
	//@JoinColumn(name = "empleado_id")
    private Empleado Empleado;
    
	/*
	 * 
	@Id
	@GeneratedValue(generator = "foreigngen")
	@OneToOne(mappedBy = "TipoProyecto")
	@GenericGenerator(strategy = "foreign", name="foreigngen")
	@Column(name = "Empleado_ID")
	private Empleado Empleado;
	
	
	 */
	
    
    @Column(name = "descripción",nullable=false)
    private String descripcion;
    
	

    /*
    public TipoProyecto(long id, TipoDeProyecto tipo, String descripcion, String actividades, String insumos) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.actividades = actividades;
        this.insumos = insumos;
        
    }
    
*/
    public long getId() {
        return id;
    }
    
    public Empleado getEmpleado() {
        return Empleado;
    }
    public void setEmpleado(Empleado unEmpleado) {
    	this.Empleado=unEmpleado;
    }
    /* 
     * Aca no deberia haber set del id creeria
    public void setId(int id) {
        this.id = id;
    }
   

    public TipoDeProyecto getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeProyecto tipo) {
        this.tipo = tipo;
    }
 */
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
/*
 * 

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public String getInsumos() {
        return insumos;
    }

    public void setInsumos(String insumos) {
        this.insumos = insumos;
    }
     */
}

