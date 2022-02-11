package grafico;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import control.Mouse;
import modelo.Aliado;
import modelo.Antimisil;
import modelo.Bombardero;
import modelo.Coordenada;
import modelo.Explosion;
import modelo.Juego;
import modelo.Misil;
import modelo.Silo;


public class PanelJuego extends JPanel{
	private Image fondo;
	private Juego jueg;
	private int numole;
	private boolean finOleadaCreada = false;
	
	public void paintComponent(Graphics g) {
		System.out.println("GRAFICAR TODO");
		Graphics2D graf = (Graphics2D) g;
		super.paintComponent(g);
		LinkedList<Misil> listaMisiles=jueg.getAdmin().getListaMisiles();
		LinkedList<Antimisil> listaAntimisiles=jueg.getAdmin().getListaAntimisiles();
		LinkedList<Explosion> listaExplosiones= jueg.getAdmin().getListaExplosiones();
		LinkedList<Bombardero> listaBombarderos= jueg.getAdmin().getListaBombarderos();
		LinkedList<Aliado> listaAliados =jueg.getAdmin().getListaAliados();
		
		graf.setColor(Color.WHITE);
		double x = this.getWidth() / 2;
		double y = this.getHeight() / 2 - 20;
		graf.rotate(Math.toRadians(180.0), x, y);
		LinkedList<Bombardero> copiabomb = (LinkedList<Bombardero>) listaBombarderos.clone();
		for(Bombardero b : copiabomb) {
			if (b.esAvion()) {
				AffineTransform at = AffineTransform.getTranslateInstance(b.getPos().getX(),b.getPos().getY());
				if(b.getDire().equals("izq")) {
					at.rotate(Math.toRadians(180.0), 0, 0);
					graf.drawImage(Directorios.avion, at, null);
				}
				else {
					at.rotate(Math.toRadians(180.0), 0, 0);
					graf.drawImage(Directorios.avion2, at, null);
				}
			} else {
				if (b.esSatelite()) {
					AffineTransform at = AffineTransform.getTranslateInstance(b.getPos().getX(),b.getPos().getY());
					at.rotate(Math.toRadians(180.0), 0, 0);
					graf.drawImage(Directorios.satelite, at, null);
				}
			}
		}
		LinkedList<Aliado> copiaaliado = (LinkedList<Aliado>) listaAliados.clone();
		for(Aliado a : copiaaliado) {
			if (a.esSilo()) {
				AffineTransform at = AffineTransform.getTranslateInstance(a.getPos().getX(),a.getPos().getY()+15);
				at.rotate(Math.toRadians(180.0), 0, 0);
				graf.drawImage(Directorios.silo, at, null);
				graf.setColor(Color.WHITE);
				graf.rotate(Math.toRadians(180.0), 0, 0);
				if(((Silo)a).getOrden()==0) {
				graf.drawString(""+ ((Silo)a).getCantanti(), (int)  a.getPos().getX()-105,(int) a.getPos().getY()+38);
				graf.drawString(""+ ((Silo)a).getEstado(), (int) a.getPos().getX()-85,(int) a.getPos().getY()+38);
				}
				else {
					if(((Silo)a).getOrden()==2) {
						graf.drawString(""+ ((Silo)a).getCantanti(), (int)  a.getPos().getX()-1010,(int) a.getPos().getY()+38);
						graf.drawString(""+ ((Silo)a).getEstado(), (int) a.getPos().getX()-990,(int) a.getPos().getY()+38);
					}
					else {
						graf.drawString(""+ ((Silo)a).getCantanti(), (int)  a.getPos().getX()-548,(int) a.getPos().getY()+38);
						graf.drawString(""+ ((Silo)a).getEstado(), (int) a.getPos().getX()-530,(int) a.getPos().getY()+38);
					}
				}
				graf.rotate(Math.toRadians(180.0), 0, 0);
			} else {
				if (a.esCiudad()) {
					AffineTransform at = AffineTransform.getTranslateInstance(a.getPos().getX()+15,a.getPos().getY()+17);
					at.rotate(Math.toRadians(180.0), 0, 0);
					graf.drawImage(Directorios.ciudad, at, null);
				}
			}
		}
		LinkedList<Misil> copiamisiles = (LinkedList<Misil>) listaMisiles.clone();
		for(Misil m : copiamisiles) {
			LinkedList<Coordenada> copiaestela = (LinkedList<Coordenada>) m.getEstela().clone();
			for(Coordenada c: copiaestela) {
				graf.setColor(Color.WHITE);
				graf.fillOval((int) c.getX(), (int) c.getY(), 2, 2);
				graf.setColor(Color.WHITE);
				graf.drawOval((int) c.getX(), (int) c.getY(), 2, 2);
			}
			if(m.esMBITR()) {
				double alfa = Math.atan2(m.getDestino().getY()-m.getPosc().getY(), m.getDestino().getX()-m.getPosc().getX());
				AffineTransform  at = AffineTransform.getTranslateInstance(m.getPosc().getX()-20,m.getPosc().getY()-20);
				at.rotate(Math.toRadians(alfa), 20, 20);
				graf.drawImage(Directorios.MBITR, at, null);
			}
			else {
				double alfa = Math.atan2(m.getDestino().getY()-m.getPosc().getY(), m.getDestino().getX()-m.getPosc().getX());
				AffineTransform  at = AffineTransform.getTranslateInstance(m.getPosc().getX()-20,m.getPosc().getY()-20);
				at.rotate(Math.toRadians(alfa), 0, 0);
				graf.drawImage(Directorios.crucero, at, null);
			}
		}
		LinkedList<Explosion> copiaexplociones = (LinkedList<Explosion>) listaExplosiones.clone();
		for( Explosion e : copiaexplociones) {
			AffineTransform  at = AffineTransform.getTranslateInstance(e.getPos().getX()+20,e.getPos().getY()+20);
			at.rotate(Math.toRadians(180.0));
			graf.drawImage(Directorios.explosion, at, null);
		}
		LinkedList<Antimisil> copiaantimisiles = (LinkedList<Antimisil>) listaAntimisiles.clone();
		for( Antimisil a : copiaantimisiles) {
			for(Coordenada co: a.getEstela()) {
				graf.setColor(Color.WHITE);
				graf.fillOval((int) co.getX(), (int) co.getY(), 2, 2);
				graf.setColor(Color.CYAN);
				graf.drawOval((int) co.getX(), (int) co.getY(), 2, 2);
			}
			double alfa = Math.atan2(a.getDestino().getY()-a.getPosc().getY(), a.getDestino().getX()-a.getPosc().getX());
			AffineTransform  at = AffineTransform.getTranslateInstance(a.getPosc().getX()+20,a.getPosc().getY()+20);
			at.rotate(Math.toRadians(180+alfa), 0, 0);
			graf.drawImage(Directorios.antimisil, at, null);
		}
		if(jueg.getImprimo()>0) {
			AffineTransform  at = AffineTransform.getTranslateInstance(500,480);
			at.rotate(Math.toRadians(180.0));
			graf.drawImage(Directorios.ciuextra, at, null);
			jueg.setImprimo(jueg.getImprimo()-1);
		}
		if ((jueg.terminoOleada()) && (!jueg.terminoElJuego(jueg.getAdmin().getListaAliados())) && !finOleadaCreada) {
			finOleadaCreada = true;
			System.out.println("DIBUJA FIN OLEADA");
			Frame.INSTANCE.setContentPane(new FinOleada(jueg));
			final Timer time = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					if(!jueg.getPausa()) {
						Frame.INSTANCE.setContentPane(PanelJuego.this);
						time.cancel();
						finOleadaCreada=false;
					}
				}
			};
			time.schedule(task,0,200);
		}
		
		if ((!jueg.getPausa())&&(jueg.terminoElJuego(jueg.getAdmin().getListaAliados()))){
			Frame.INSTANCE.setContentPane(new FinJuego(jueg));
			final Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					Frame.INSTANCE.setContentPane(new PanelRanking(jueg.getPuntajet()[4].getX(), true,6,numole,jueg.getTiempofin()));
					timer.cancel();
				}
			};
			timer.schedule(task, 6000, 5000);
		}
	}
	public PanelJuego(Juego jueg, int numole) {
		this.setSize(507, 433);
		this.jueg=jueg;
		this.numole=numole;
		addMouseListener(new Mouse(jueg));
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		try {
			fondo = ImageIO.read(getClass().getResourceAsStream("/grafica/imagenes/fondo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);
		super.paint(g);
	}
}
