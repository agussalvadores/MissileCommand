package modelo;

/** Representa un silo haciendo uso de la clase extendida Aliado
 * @author Agus Salvadores Fran Gonzalez
*/

public class Silo extends Aliado{
	private int cantanti;
	private EstadoSilo estado;
	private int orden;
	/** Constructor de Silo
	 * @param posicion
	 * @param orden
	*/
	
	public Silo(Coordenada posicion, int orden) {
		super(posicion,"SILO");
		this.setOrden(orden);
		this.setCantanti(10);
		this.setEstado(EstadoSilo.OK);
		
	}
	
	/** Efectua los disparos hacia un destino
	 * @param admin
	 * @param destino
	*/
	public void disparar(Administrador admin,Coordenada destino) {
		if(this.getCantanti()>0) {
			Coordenada aux= new Coordenada(this.getPos().getX(),this.getPos().getY());
			Antimisil anti= new Antimisil(destino,aux);
			admin.getListaAntimisiles().add(anti);
			this.setCantanti(this.getCantanti()-1);
			if(this.getCantanti()<4) {
				this.setEstado(EstadoSilo.LOW);
			}
			if(this.getCantanti()==0) {
				this.setEstado(EstadoSilo.OUT);
			}
		}
	}
	public EstadoSilo getEstado() {
		return estado;
	}
	public void setEstado(EstadoSilo estado) {
		this.estado = estado;
	}
	public int getCantanti() {
		return cantanti;
	}
	public void setCantanti(int cantanti) {
		this.cantanti = cantanti;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
}
