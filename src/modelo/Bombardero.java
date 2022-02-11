package modelo;

/** Representacion de Bombarderos en el juego
 * @author Agus Salvadores Fran Gonzalez
*/

public abstract class Bombardero{
	private Coordenada pos;
	private Coordenada des;
	private int cantmisiles;
	private String dire;
	private String nombre;
	
	/** Constructor de la clase bombardero
	 * @param origen
	 * @param destino
	 * @param direccion 
	*/
	Bombardero(Coordenada origen, Coordenada destino, String direccion){
		this.setDire(direccion);
		this.setDes(destino);
		this.setPos(origen);
		int rango = (4 - 2) + 1;     
		this.setCantmisiles((int)(Math.random() * rango) + 2);
		
	}
	public Coordenada getPos() {
		return pos;
	}
	public void setPos(Coordenada pos) {
		this.pos = pos;
	}
	public int getCantmisiles() {
		return cantmisiles;
	}
	public void setCantmisiles(int cantmisiles) {
		this.cantmisiles = cantmisiles;
	}
	public void avanzar(Oleada ole) {
		Coordenada aux=this.getPos();
		if(this.getDire().equals("izq")) {
			aux.setX(aux.getX()-(ole.getNum()));
		}
		else {
			aux.setX(aux.getX()+(ole.getNum()));
		}
		this.setPos(aux);
	}
	public String getDire() {
		return dire;
	}
	public void setDire(String dire) {
		this.dire = dire;
	}
	public Coordenada getDes() {
		return des;
	}
	public void setDes(Coordenada des) {
		this.des = des;
	}
	
	/** Corrobora si se llego a destino
	*/
	public boolean llegoDestino() {
		if (this.getPos().equals(this.getDes())) {
			return true;
		}
		else {
		return false;
		}
	}
	
	/** Corrobora si se trata de un avion 
	*/
	public boolean esAvion() {
		if(this.getNombre().equals("AVION")) {
		return true;
		}
		else {
			return false;
		}
	}
	
	/** Corrobora si se trata de un satelite 
	*/
	public boolean esSatelite() {
		if(this.getNombre().equals("SATELITE")) {
		return true;
		}
		else {
			return false;
		}
	}
	
	/** Se procesan los disparos 
	 * @param ole 
	 * @param admin
	 * @param regresivobombardero 
	*/
	
	public void disparar(Oleada ole,Administrador admin, Contador regresivobombardero) {
		if((regresivobombardero.getX()==0) &&(admin.getListaAliados().size()>0)) {
			int ran = ((admin.getListaAliados().size()-1) - 0) + 1;     
			int destino= (int)(Math.random() * ran) + 0;
			Coordenada aux= new Coordenada(this.getPos().getX(),this.getPos().getY());
			MBITR mbi =new MBITR(admin.getListaAliados().get(destino).getPos(),aux,ole.getNum(),true,admin.getListaAliados().get(destino));
			admin.getListaMisiles().add(mbi);
			regresivobombardero.setX(60*3);
		}
		regresivobombardero.setX(regresivobombardero.getX()-1);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
