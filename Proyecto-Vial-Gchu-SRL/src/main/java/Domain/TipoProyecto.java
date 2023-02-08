/**
 * 
 */
package Domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;



import Enumeraciones.TipoDeProyecto;
/**
 *
 * @author Tincho
 */
@Entity
@Table(name = "TipoProyecto",
        schema = "public")
public class TipoProyecto implements java.io.Serializable{

	@Column(name="tipoProyectoid", unique=true, nullable=false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
    
    @Column(name = "tipo",nullable=false)
    private TipoDeProyecto tipo;
    
    @Column(name = "descripción",nullable=false)
    private String descripcion;
    
    @Column(name = "actividades",nullable=false)
    private String actividades;
    
    @Column(name = "insumos",nullable=false)
    private String insumos;
    
    @OneToOne(mappedBy = "TipoProyecto")
    private Proyecto Proyecto;

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

    /* 
     * Aca no deberia haber set del id creeria
    public void setId(int id) {
        this.id = id;
    }
    */

    public TipoDeProyecto getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeProyecto tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

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
    
}
