package modelo;
import java.util.*;
/** Representa los antimisiles
 * @author Agus Salvadores Fran Gonzalez 
*/

public class Antimisil{
	private boolean estado;
	private Coordenada posc;
	private LinkedList<Coordenada> estela;
	private Coordenada destino;
	private int vel;
	
	/** Constructor de la clase antimisil
	 * @param des
	 * @param ini
	 * 
	*/
	public Antimisil(Coordenada des, Coordenada ini) {
		this.setVel(8);
		this.setEstado(true);
		this.setPosc(ini);
		this.setDestino(des);
		this.estela= new LinkedList<Coordenada>();
		System.out.println("SE CREO EL ANTIMISIL");
	}
	/** Se almacena en un vector de tipo double, el movimiento a realizar en X  e Y
	*/
	
public double[] obtenerMovimientoXy() {
	double[] xy = new double[2];
	double alfa;
	alfa=Math.atan2(this.getDestino().getY()-this.getPosc().getY(),this.getDestino().getX()-this.getPosc().getX());
	xy[0]=Math.cos(alfa)*this.vel;
	xy[1]=Math.sin(alfa)*this.vel;
	return (xy);
	}

/** Utiliza lo calculado en obtenerMovimientoXy() y efectua el movimiento
*/
	public void mover() {
		double[] movxy;
		movxy=this.obtenerMovimientoXy();
		Coordenada c = new Coordenada(this.getPosc().getX(),this.getPosc().getY());
		this.getEstela().add(c);
		this.getPosc().setX((int)(this.getPosc().getX()+movxy[0]));
		this.getPosc().setY((int)(this.getPosc().getY()+movxy[1]));
		System.out.println("MOVER ANTIMISIL"+movxy[0]+"  ; " +movxy[1]);
	}
	/** Se lleva a cabo la explosion
	 * @param admin
	*/
	
	public void explotar(Administrador admin) {
		Coordenada aux= new Coordenada(this.getPosc().getX(),this.getPosc().getY());
		Explosion exp= new Explosion(aux);
		admin.getListaExplosiones().add(exp);
	}
	/** Obtiene un retorno true o false, en caso de llegar o no a destino
	 * @param admin
	*/
	
	public boolean llegoDestino(Administrador admin) {
		if ((admin.distanciaEntreDos(posc, destino))<5) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Coordenada getDestino() {
		return destino;
	}
	public void setDestino(Coordenada destino) {
		this.destino = destino;
	}
	public LinkedList<Coordenada> getEstela() {
		return estela;
	}
	public void setEstela(LinkedList<Coordenada> estela) {
		this.estela = estela;
	}
	public Coordenada getPosc() {
		return posc;
	}
	public void setPosc(Coordenada posc) {
		this.posc = posc;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public int getVel() {
		return vel;
	}
	public void setVel(int vel) {
		this.vel = vel;
	}
}
