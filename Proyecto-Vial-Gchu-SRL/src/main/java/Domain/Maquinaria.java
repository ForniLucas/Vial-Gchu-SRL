/**
 * 
 */
package Domain;

import java.time.LocalDate;
import java.util.HashSet;
//import java.util.LinkedList;
//import java.util.List;
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
import javax.persistence.Table;

import Enumeraciones.Elemento;

/**
 * @author lucas
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Maquinaria",
        schema = "public")

public class Maquinaria implements java.io.Serializable{
	@Column(name="maquinariaid", unique=true, nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
    
    @Column(name = "Codigo",nullable=false)
    private String codigo;
    
    @Column(name = "Descrpición",nullable=false)
    private String descripcion;
    
    @Column(name = "Fabricante",nullable=false)
    private String fabricante;
    
    @Column(name = "Ubicación",nullable=false)
    private String ubicacionAlmacenamiento;
    
    @Column(name = "estado",nullable=false)
    private boolean estado;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
    	    orphanRemoval = true
    , mappedBy = "Maquinaria")
    private Set<Utiliza> Trabajos = new HashSet<Utiliza>(0);
    

    @OneToMany(fetch=FetchType.LAZY, mappedBy="Maquinaria",cascade = CascadeType.ALL,orphanRemoval = true)
	private Set<Service> Services = new HashSet<Service>(0);
    

    public Maquinaria(String codigo, String descripcion, String fabricante, String ubicacionAlmacenamiento) {
        this.id = null;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fabricante = fabricante;
        this.ubicacionAlmacenamiento = ubicacionAlmacenamiento;
        this.estado = true;

        //services = new LinkedList<>();
    }

    public Maquinaria() {}
    
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFabricante() {
        return fabricante;
    }

    public long getId() {
        return id;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getUbicacionAlmacenamiento() {
        return ubicacionAlmacenamiento;
    }

    public void setUbicacionAlmacenamiento(String ubicacionAlmacenamiento) {
        this.ubicacionAlmacenamiento = ubicacionAlmacenamiento;
    }

    public boolean getEstado() {
        return this.estado;
    }

    public void setEstadoBaja() {
        //if (getEstado()) {
            this.estado = false; 
        //}
    }

    public void setEstadoAlta() {
        //if (!getEstado()) {
            this.estado = true;
        //}
    }

    public void modificarMaquinaria(Maquinaria unaMaquinaria) {
        this.codigo = unaMaquinaria.codigo;
        this.descripcion = unaMaquinaria.descripcion;
        this.fabricante = unaMaquinaria.fabricante;
        this.ubicacionAlmacenamiento = unaMaquinaria.ubicacionAlmacenamiento;
        this.estado = unaMaquinaria.estado;
    }
    
    public void addTrabajo(Utiliza comment) {
	     Trabajos.add(comment);
   }
    

    public void asignarService(Service unservice) {
        Services.add(unservice);
    }

    public Set<Service> getServices() {
        return this.Services;
    } 
}
