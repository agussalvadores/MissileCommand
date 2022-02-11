package modelo;
import java.util.*;

/** Realiza las tareas esenciales del juego
 * @author Agus Salvadores Fran Gonzalez
*/

public class Administrador {
	private LinkedList<Misil> listaMisiles;
	private LinkedList<Antimisil> listaAntimisiles;
	private LinkedList<Explosion> listaExplosiones;
	private LinkedList<Bombardero> listaBombarderos;
	private LinkedList<Aliado> listaAliados;
	private boolean[][] mapa;
	
	/** Constructor de la clase Administador
	*/
	
	public Administrador() {
		System.out.println("CONSTRUCTOR ADMINISTRADOR");
		this.mapa= new boolean[525][480];
		for(int i=0;i<525;i++) {
			for(int j=0;j<480;j++) {
				mapa[i][j]=false;
			}
		}
		this.listaExplosiones= new LinkedList<Explosion>();
		this.listaAntimisiles= new LinkedList<Antimisil>();
		this.listaBombarderos= new LinkedList<Bombardero>();
		this.listaMisiles=new LinkedList<Misil>();
		this.listaAliados=new LinkedList<Aliado>();
	}
	
	/** Inicializa los tres silos
	*/
	
	public void inicializarSilos() {
		for (int i=0; i<3;i++) {
			if(i==0) {
				Coordenada corde= new Coordenada(55+(i*233),1);
				Silo sil=new Silo(corde,i);
				this.getListaAliados().add(sil);
			}
			else {
				if(i==2) {
				Coordenada corde= new Coordenada(40+(i*233),1);
				Silo sil=new Silo(corde,i);
				this.getListaAliados().add(sil);
				}
				else {
					Coordenada corde= new Coordenada(45+(i*233),1);
					Silo sil=new Silo(corde,i);
					this.getListaAliados().add(sil);
				}
			}
			
		}
		System.out.println(this.getListaAliados().size());
		System.out.println("INICIALIZAR SILOS");
	}
	
	/** Inicializa las seis ciudades
	*/
	
	public void inicializarCiudades() {
		for(int h=1;h<8;h++) {
			if(h<4) {
				Coordenada corde= new Coordenada(30+58*h,1);
				Ciudad ciu =new Ciudad(corde);
				this.getListaAliados().add(ciu);
			}
			else{
				if(h>4){
					Coordenada corde= new Coordenada(30+58*h,1);
					Ciudad ciu =new Ciudad(corde);
					this.getListaAliados().add(ciu);
				}
			}
		}
		System.out.println(this.getListaAliados().size());
		System.out.println("INICIALIZAR CIUDADES");
	}
	
	/** Inicializa los cuatro grupos de misiles
	 * @param ole
	 * @param grupos
	*/
	
	public void grupoMisiles(Oleada ole, Contador grupos) {
		if(grupos.getX()>0) {
			grupos.setX(grupos.getX()-1);
			System.out.println("SE DECREMENTO EL GRUPO"+grupos.getX());
			int rango = (4 - 3) + 1;     
			int max= (int)(Math.random() * rango) + 3;
			System.out.println(this.getListaAliados().size());
			for (int i=0;i<max;i++) {
				int ran1 = ((this.getListaAliados().size() -1) - 0) + 1;  
				int destino= (int)(Math.random() * ran1) + 0;
				int ran2 = (525 - 0) + 1;     
				int corx1= (int)(Math.random() * ran2) + 0;
				System.out.println(corx1);
				System.out.println(destino);
				Coordenada origen= new Coordenada(corx1,480);
				MBITR mbi =new MBITR(this.getListaAliados().get(destino).getPos(),origen,1+ole.getNum(),false,this.getListaAliados().get(destino));
				this.getListaMisiles().add(mbi);
			}
			System.out.println(this.getListaMisiles().size());
			System.out.println("GRUPO ENVIADO");
		}
		System.out.println("GRUPOS MISILES");
	}
	
	/** Se crea un misil crucero
	 * @param ole
	 * @param regresivoCrucero
	 * 
	*/
	
	public void crucero(Oleada ole,Contador regresivoCrucero) {
		if(regresivoCrucero.getX()>0) {
			regresivoCrucero.setX(regresivoCrucero.getX()-1);
		boolean[] tipom = new boolean [16];
		for(int j=1;j<17;j+=4) {
			tipom[j]=true;
			tipom[j]=true;
		}
		if(tipom[ole.getNum()]) {
			int ran3 = ((this.listaAliados.size()-1) - 0) + 1;     
			int destino1= (int)(Math.random() * ran3) + 0;
			int ran4 = (525 - 0) + 1;     
			int corx2= (int)(Math.random() * ran4) + 0;
			Coordenada origen1= new Coordenada(corx2,480);
			Tonto ton =new Tonto(this.getListaAliados().get(destino1).getPos(),origen1,1+ole.getNum(),this.getListaAliados().get(destino1));
			listaMisiles.add(ton);
		}
		else {
			int ran3 = ((this.listaAliados.size()-1) - 0) + 1;     
			int destino1= (int)(Math.random() * ran3) + 0;
			int ran4 = (525 - 0) + 1;     
			int corx2= (int)(Math.random() * ran4) + 0;
			Coordenada origen1= new Coordenada(corx2,480);
			Inteligente inte=new Inteligente (this.listaAliados.get(destino1).getPos(),origen1,1+ole.getNum(),this.getListaAliados().get(destino1));
			listaMisiles.add(inte);
		}
		System.out.println("CRUCERO");
		}
	}
	
	/** Elige con random si mandar avion o satelite
	 * @param ole
	*/
	
	public void mandarBombardero(Oleada ole) {
		if(ole.getNum()>1) {
			int ran = (2 - 1) + 1;     
			int i= (int)(Math.random() * ran) + 1;
			if (i==1){
				mandarAvion(ole);
			}
			else {
				mandarSatelite(ole);
			}
		}
		System.out.println("MANDAR BOMBARDERO");
	}
	
	/** Manda un avion
	 * @param ole
	*/
	
	public void mandarAvion(Oleada ole) {
		int orix;
		int desx;
		String dire;
		int ran = (2 - 1) + 1;     
		int i= (int)(Math.random() * ran) + 1;
		if (i==1){
			orix=0;
			desx=525;
			dire="der";
		}
		else {
			orix=525;
			desx=0;
			dire="izq";
		}
		Coordenada coro=new Coordenada(orix,430-(30*ole.getNum()));
		Coordenada cord=new Coordenada(desx,430-(30*ole.getNum()));
		Avion avio=new Avion(coro,cord,dire);
		this.listaBombarderos.add(avio);
	}
	
	/** Calcula la distancia entre dos puntos
	 * @param cor1
	 * @param cor2
	*/
	
	
	public int distanciaEntreDos(Coordenada cor1, Coordenada cor2) {
		int x= (int) Math.sqrt(Math.pow(cor2.getX()-cor1.getX(), 2) + Math.pow(cor2.getY()-cor1.getY(), 2));
		if(x>0) {
			return x;
		}
		else {
			return 0-x;
		}
	}
	
	/** Manda un satelite 
	 * @param ole
	*/
	
	
	public void mandarSatelite(Oleada ole) {
		int orix;
		int desx;
		String dire;
		int ran = (2 - 1) + 1;     
		int i= (int)(Math.random() * ran) + 1;
		if (i==1){
			orix=0;
			desx=525;
			dire="der";
		}
		else {
			orix=525;
			desx=0;
			dire="izq";
		}
		Coordenada coro=new Coordenada(orix,430-(30*ole.getNum()));
		Coordenada cord=new Coordenada(desx,430-(30*ole.getNum()));
		Satelite sate=new Satelite(coro,cord,dire);
		this.listaBombarderos.add(sate);
	}
	public LinkedList<Aliado> getListaAliados() {
		return listaAliados;
	}
	public void setListaAliados(LinkedList<Aliado> listaAliados) {
		this.listaAliados = listaAliados;
	}
	public LinkedList<Bombardero> getListaBombarderos() {
		return listaBombarderos;
	}
	public void setListaBombarderos(LinkedList<Bombardero> listaBombarderos) {
		this.listaBombarderos = listaBombarderos;
	}
	public LinkedList<Misil> getListaMisiles() {
		return listaMisiles;
	}
	public void setListaMisiles(LinkedList<Misil> listaMisiles) {
		this.listaMisiles = listaMisiles;
	}
	public LinkedList<Antimisil> getListaAntimisiles() {
		return listaAntimisiles;
	}
	public void setListaAntimisiles(LinkedList<Antimisil> listaAntimisiles) {
		this.listaAntimisiles = listaAntimisiles;
	}
	public LinkedList<Explosion> getListaExplosiones() {
		return listaExplosiones;
	}
	public void setListaExplociones(LinkedList<Explosion> listaExplociones) {
		this.listaExplosiones = listaExplociones;
	}
}
