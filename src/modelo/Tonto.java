package modelo;

/** Representacion de un misil Tonto
 * @author Agus Salvadores Fran Gonzalez
*/

public class Tonto extends Crucero{
	/** Constructor de la clase Tonto
	 * @param des
	 * @param posc
	 * @param vel
	 * @param ali
	*/
	public Tonto(Coordenada des, Coordenada posc, int vel, Aliado ali) {
		super(des,posc,vel,ali);
		this.setNombre("TONTO");
		System.out.println("SE CREO EL tonto");
	}
	public void bifurcacion(Oleada nivel, Administrador admin) {
		
	}
	public void desvio(Administrador admin, Explosion exp) {
		
	}
}
