package modelo;

/** Representa a la clase Avion
*/

public class Avion extends Bombardero {
	
	/** Constructor de la clase avion
	 * @param origen
	 * @param destino
	 * @param direccion
	*/
	public Avion(Coordenada origen,Coordenada destino, String direccion) {
		super(origen,destino,direccion);
		this.setNombre("AVION");
	}
}
