package modelo;

/** Representa una posicion en X e Y
 * @author Agus Salvadores Fran Gonzalez
*/


public class Coordenada {
	private double x;
	private double y;
	
	/** Constructor de la clase Coordenada
	 * @param a
	 * @param b 
	*/
	
	
	public Coordenada(double a, double b) {
		this.setX(a);
		this.setY(b);
		System.out.println("SE CREO LA COORDENADA");
	}
	public void setX(double a){
		this.x=a;
	}
	public void setY(double a){
		this.y=a;
	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	
	/** Se toma la igualdad de una coordenada
	 * @param cor
	*/
	
	public boolean equals(Coordenada cor) {
		boolean x=false;
		if((this.getX()==cor.getX())&&(this.getY()==cor.getY())) {
			x=true;
		}
		return x;
	}
}
