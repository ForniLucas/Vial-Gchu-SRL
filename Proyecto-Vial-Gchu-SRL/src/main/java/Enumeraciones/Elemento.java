package Enumeraciones;

/**
*
* @author Tincho
*/

public enum Elemento {
	   CASCO_SEGURIDAD("Casco de Seguridad"),
	   GORRO_LEGIONARIO("Gorro Legionario"),
	   BARBIQUEJO("Barbiquejo"),
	   LENTES("Lentes de Protección"),
	   CHALECO_REFLECTANTE("Chaleco Reflectante"),
	   ARNES("Arnes de Seguridad"),
	   PROTECCION_AUDITIVA("Protección Auditiva"),
	   PROTECCION_RESPIRATORIA("Protección Respiratoria"),
	   BLOQUEADOR_SOLAR("Bloqueador Solar"),
	   TRAJE_INIFUGO("Traje Ignífugo"),
	   TRAJE_PROPANO("Traje de Propileno");

	   private String displayName;

	   private Elemento(String displayName) {
	       this.displayName = displayName;
	   }

	   @Override
	   public String toString() {
	       return displayName;
	   }
	}
