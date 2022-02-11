package modelo;

/** Representacion de la clase Inteligente  
 * @author Agus Salvadores Fran Gonzalez
*/

public class Inteligente extends Crucero {
	
	/** Constructor de la clase inteligente 
	 * @param des
	 * @param posc
	 * @param vel
	 * @param ali
	*/
	public Inteligente(Coordenada des, Coordenada posc, int vel, Aliado ali) {
		super(des,posc,vel,ali);
		this.setNombre("INTELIGENTE");
		System.out.println("SE CREO EL INTELIGENTE");
	}
	
	/** Se efectua el desvio por el corresponiente misil
	 * @param admin
	 * @param explo 
	*/
	public void desvio(Administrador admin,Explosion explo) {
		if((admin.distanciaEntreDos(this.getPosc(), explo.getPos())<30) && (this.getPosc().getY()>explo.getPos().getY())) {
			double x= this.getPosc().getX()+10;
			double y= this.getPosc().getY();
			Coordenada aux = new Coordenada(x,y);
			this.setPosc(aux);
		}
	}
	public void bifurcacion(Oleada nivel, Administrador admin){
		
	}
}
