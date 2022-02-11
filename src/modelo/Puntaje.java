package modelo;

/** Puntaje obtenido y nombre que lo obtuvo
 * @author Agus Salvadores Fran Gonzalez
*/

public class Puntaje {
	private int x;
	private String nombre;
	
	/** Constructor de la clase puntaje 
	*/
	
	public Puntaje(){
		this.setX(0);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
