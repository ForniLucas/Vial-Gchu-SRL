package Enumeraciones;

public enum EstadoProyecto {
	INICIADO("Iniciado"),
	EN_CURSO("En curso"),
	SUSPENDIDO("Suspendido"),
	FINALIZADO("Finalizado"),
	CANCELADO("Cancelado");

	   private String displayName;

	   private EstadoProyecto(String displayName) {
	       this.displayName = displayName;
	   }

	   @Override
	   public String toString() {
	       return displayName;
	   }

}
