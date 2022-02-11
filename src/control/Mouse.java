package control;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import modelo.Coordenada;
import modelo.Juego;

/**  
*/

public class Mouse extends MouseAdapter{
	private boolean click = false;
	private Juego jueg;
	
	public Mouse(Juego jueg) {
		this.jueg=jueg;
	}
	public void mousePressed(MouseEvent e) {
		if (!click) {
			click = true;
		}
		
		jueg.procesarDisparos(jueg.getAdmin(), new Coordenada(507-e.getX(),392-e.getY()));
		
	}

	public boolean esClick() {
		return click;
	}

	public void reiniciarclick() {
		if (click)
			this.click = false;
	}
}
