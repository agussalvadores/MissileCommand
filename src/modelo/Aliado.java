package modelo;
import taller2.grafico.Dibujable;

/** Representa a una ciudad o un silo
 * @author Agus Salvadores Fran Gonzalez
*/
public abstract class Aliado{
	private Coordenada pos;
	private String nombre;
	/** Metodo constructor de la clase Aliado
	 * @param posicion
	 * @param nombre 
	*/
	Aliado(Coordenada posicion,String nombre){
		this.setPos(posicion);
		this.setNombre(nombre);
	}
	/** Se verifica que se trata de un Silo
	*/
	
	public boolean esSilo() {
		if(this.getNombre().equals("SILO")) {
		return true;
		}
		else {
			return false;
		}
	}
	/** Se verifica que se trata de una ciudad
	*/
	
	public boolean esCiudad() {
		if(this.getNombre().equals("CIUDAD")) {
		return true;
		}
		else {
			return false;
		}
	}
	public Coordenada getPos() {
		return pos;
	}
	public void setPos(Coordenada pos) {
		this.pos = pos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
