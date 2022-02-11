package modelo;

/** Define numero de oleada
 * @author Agus Salvadores Fran Gonzalez
*/

public class Oleada {
	private int num;
	
	/** Constructor de la clase Oleada
	 * @param num 
	*/
	
	public Oleada(int num) {
		this.num=num;
		System.out.println("CONSTRUCTOR OLEADA");
	}
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	
}
