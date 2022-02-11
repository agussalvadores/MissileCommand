package modelo;

/** Representacion de un satelite
*/

public class Satelite extends Bombardero{
	
	/** Constructor de un satelite
	 * @param origen
	 * @param destino
	 * @param direccion
	*/
	public Satelite(Coordenada origen,Coordenada destino, String direccion) {
		super(origen,destino,direccion);
		this.setNombre("SATELITE");
	}
}
