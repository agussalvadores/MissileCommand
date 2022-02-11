package modelo;

/** Se utiliza para llevar un contador de valores y poder ser usado a traves de parametros sin perder su valor
 * @author Agus Salvadores Fran Gonzalez
*/


public class Contador {
	private int x;
	
	/** Constructor de la clase contador 
	 * @param x 
	*/
	public Contador(int x) {
		this.setX(x);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
}
