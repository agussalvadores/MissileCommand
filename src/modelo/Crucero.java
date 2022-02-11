package modelo;

/** Representacion de la clase Crucero en el juego 
*/

public abstract class Crucero extends Misil{
	
	/** Constructor de la clase crucero
	 * @param des
	 * @param posc
	 * @param vel
	 * @param ali 
	*/
	public Crucero(Coordenada des, Coordenada posc, int vel, Aliado ali) {
		super(des,posc,vel,ali);
	}
}
