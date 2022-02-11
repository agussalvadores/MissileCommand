package modelo;
import java.util.LinkedList;


/** Representacion de un Misil 
 * @author Agus Salvadores Fran Gonzalez
*/

public abstract class Misil{     
	private Coordenada posc;
	private int velocidad;
	private boolean estado;
	private String nombre;
	private LinkedList<Coordenada> estela;
	private Coordenada destino;
	private Aliado aliDestino;
	
	/** Constructor de la clase Misil
	 * @param des
	 * @param posc
	 * @param vel
	 * @param alides
	*/
	public Misil(Coordenada des, Coordenada posc, int vel, Aliado alides){
		this.setEstado(true);
		this.setDestino(des);
		this.setVelocidad(vel);
		this.setEstela(new LinkedList<Coordenada>());
		this.setPosc(posc);
		this.setAliDestino(alides);
	}
	
	/** Se almacena en un vector de tipo double, el movimiento a realizar en X  e Y
	*/
	public double[] obtenerMovimientoXy() {
		double[] xy = new double[2];
		double alfa;
		alfa=Math.atan2(this.getDestino().getY()-this.getPosc().getY(),this.getDestino().getX()-this.getPosc().getX());
		xy[0]=Math.cos(alfa)*this.velocidad;
		xy[1]=Math.sin(alfa)*this.velocidad;
		if(xy[1]==0) {
			xy[1]=-3;
		}
		return (xy);
	}
	
	/** Utiliza lo calculado en obtenerMovimientoXy() y efectua el movimiento
	 * @throws RangoMisilExcepcion 
	*/
	
	public void mover() throws RangoMisilExcepcion {
		double[] movxy;
		movxy=this.obtenerMovimientoXy();
		Coordenada c = new Coordenada(this.getPosc().getX(),this.getPosc().getY());
		this.getEstela().add(c);
		this.getPosc().setX((int)(this.getPosc().getX()+movxy[0]));
		this.getPosc().setY((int)(this.getPosc().getY()+movxy[1]));
//		if(this.getPosc().getX()>525 || this.getPosc().getY()>480) {
//			throw new RangoMisilExcepcion();
//		}
	}
	public abstract void bifurcacion(Oleada nivel, Administrador admin);
	public abstract void desvio(Administrador admin, Explosion exp);
	
	/** Obtiene un retorno true o false, en caso de llegar o no a destino
	 * @param admin
	*/
	
	public void explotar(Administrador admin) {
		Coordenada aux= new Coordenada(this.getPosc().getX(),this.getPosc().getY());
		Explosion exp= new Explosion(aux);
		admin.getListaExplosiones().add(exp);
	}
	public boolean llegoDestino(Administrador admin) {
		if ((admin.distanciaEntreDos(posc, destino)<3)) {
			return true;
		}
		else {
		return false;
		}
	}
	
	/** Determina si es un MBITR
	*/
	
	public boolean esMBITR() {
		if(this.getNombre().equals("MBITR")) {
		return true;
		}
		else {
			return false;
		}
	}
	
	/** Determina si es un Tonto 
	*/
	public boolean esTonto() {
		if(this.getNombre().equals("TONTO")) {
		return true;
		}
		else {
			return false;
		}
	}
	
	/** Determina si es un inteligente
	*/
	public boolean esInteligente() {
		if(this.getNombre().equals("INTELIGENTE")) {
		return true;
		}
		else {
			return false;
		}
	}
	public Coordenada getPosc() {
		return posc;
	}
	public void setPosc(Coordenada posc) {
		this.posc = posc;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public void setEstela(LinkedList<Coordenada> cor) {
		this.estela=cor;
	}
	public LinkedList<Coordenada> getEstela(){
		return this.estela;
	}
	public Coordenada getDestino() {
		return destino;
	}
	public void setDestino(Coordenada destino) {
		this.destino = destino;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Aliado getAliDestino() {
		return aliDestino;
	}
	public void setAliDestino(Aliado aliDestino) {
		this.aliDestino = aliDestino;
	}
}
