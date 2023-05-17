package Enumeraciones;

/**
*
* @author Tincho
*/

public enum Elemento {
	   CASCODESEGURIDAD("Casco de Seguridad"),
	   GORROLEGIONARIO("Gorro Legionario"),
	   BARBIQUEJO("Barbiquejo"),
	   LENTESDEPROTECCION("Lentes de Proteccion"),
	   CHALECOREFLECTANTE("Chaleco Reflectante"),
	   ARNESDESEGURIDAD("Arnes de Seguridad"),
	   PROTECCIONAUDITIVA("Protección Auditiva"),
	   PROTECCIONRESPIRATORIA("Proteccion Respiratoria"),
	   BLOQUEADORSOLAR("Bloqueador Solar"),
	   TRAJEINIFUGO("Traje Ignifugo"),
	   TRAJEPROPILENO("Traje de Propileno");

	   private String displayName;

	   private Elemento(String displayName) {
	       this.displayName = displayName;
	   }

	   @Override
	   public String toString() {
	       return displayName;
	   }
	}
