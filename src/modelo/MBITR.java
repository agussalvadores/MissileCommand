package modelo;

/** Representacion de un misil MBITR
 * @author Agus Salvadores Fran Gonzalez
*/

public class MBITR extends Misil {
	private boolean bifu;
	
	/**Constructor de la clase MBITR
	 * @param des
	 * @param posc
	 * @param vel
	 * @param bif
	 * @param alides 
	*/
	public MBITR(Coordenada des, Coordenada posc, int vel, boolean bif,Aliado alides) {
		super(des,posc,vel,alides);
		this.bifu=bif;
		this.setNombre("MBITR");
		System.out.println("SE CREO EL MBTIR");
	}
	
	/** Bifurcacion de los misiles en el juego
	 * @param nivel
	 * @param admin
	*/
	
	public void bifurcacion(Oleada nivel, Administrador admin) {
		if(this.bifu==false) {
			int ran2 = (3 - 2) + 1;   
			int cant= (int)(Math.random() * ran2) + 2;
			int altura = 380; 
			for (int i=0;i<cant;i++) {
				int ran1 = (admin.getListaAliados().size() - 0) + 1;
				int destino= (int)(Math.random() * ran1) + 0;
				if((nivel.getNum()>3)&&(this.getPosc().getY()<altura)){
					this.bifu=true;
					Coordenada aux= new Coordenada(this.getPosc().getX(),this.getPosc().getY());
					MBITR mbi= new MBITR(admin.getListaAliados().get(destino-1).getPos(),aux, this.getVelocidad(),true,admin.getListaAliados().get(destino-1));
					admin.getListaMisiles().add(mbi);
				}
			}
		}
	}
	public void desvio(Administrador admin, Explosion exp) {
		
	}
}
