/**
 * 
 */
package Domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
    
    @Column(name = "codigo",nullable=false)
    private String codigo;
    
    @Column(name = "descrpición",nullable=false)
    private String descripcion;
    
    @Column(name = "fabricante",nullable=false)
    private String fabricante;
    
    @Column(name = "ubicación",nullable=false)
    private String ubicacionAlmacenamiento;
    
    @Column(name = "estado",nullable=true)
    private boolean estado;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
    	    orphanRemoval = true
    , mappedBy = "Maquinaria")
    private Set<Utiliza> Trabajos = new HashSet<Utiliza>(0);
    

    @OneToMany(fetch=FetchType.EAGER, mappedBy="Maquinaria",cascade = CascadeType.ALL,orphanRemoval = true)
	private Set<Service> Services = new HashSet<Service>(0);
    
    @PrePersist
    public void onPrePersist() {
    	StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        Session session = null;
        Transaction transaction = null;
        try {
          factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
          session = factory.openSession();
          transaction = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Maquinaria> criteria = builder.createQuery(Maquinaria.class);
        Root<Maquinaria> root = criteria.from(Maquinaria.class);
        criteria.select(root).where(builder.equal(root.get("codigo"), this.getCodigo()));
        List<Maquinaria> maquinarias = session.createQuery(criteria).getResultList();
        if (!maquinarias.isEmpty()) {
            throw new RuntimeException("Ya existe una maquinaria con el mismo codigo.");
        } 
        }
        finally {
            if (session != null) {
                session.close();
            }
            if (factory != null) {
                factory.close();
            }
        }
    }
    

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
