package Enumeraciones;

/**
*
* @author Tincho
*/
public enum TipoDeProyecto {
	   BASES_Y_COLUMNAS("Bases y Columnas"),
	   TENDIDO_DE_REDES("Tendido de Redes"),
	   EXCAVACION_SUBTERRANEA("Excavación Subterránea"),
	   REPARACIONES("Reparaciones");

	   private String displayName;

	   private TipoDeProyecto(String displayName) {
	       this.displayName = displayName;
	   }

	   @Override
	   public String toString() {
	       return displayName;
	   }
	}



