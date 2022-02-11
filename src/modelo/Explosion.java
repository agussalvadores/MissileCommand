package modelo;

/** Efectua una explosion en el caso que se produzca una colision 
 * @author Agus Salvadores Fran Gonzalez
*/


public class Explosion{
	private int tiempo;
	private Coordenada pos;
	
	/** Constructor de la clase Explosion
	 * @param cor
	*/
	
	
	public Explosion(Coordenada cor) {
		this.setTiempo(70);
		this.setPos(cor);
	}
	public Coordenada getPos() {
		return pos;
	}
	public void setPos(Coordenada pos) {
		this.pos = pos;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}	
}
	
