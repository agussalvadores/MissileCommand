package grafico;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import modelo.Juego;

public class FinJuego extends JPanel{
	private Image fondo;
	
	public FinJuego(Juego jueg) {
		this.setSize(507, 470);
		GridLayout g=new GridLayout();
		g.setColumns(1);
		g.setVgap(0);
		g.setRows(18);
		this.setLayout(g);
		for(int i = 0; i<18;i++) {
			JPanel pan= new JPanel();
			
			if((i>10)&&(i<16)) {
				Font f=new Font("Arial", Font.ITALIC, 15);
				JTextArea text= new JTextArea("                              "+jueg.getPuntajet()[i-11].getNombre()+" : "+jueg.getPuntajet()[i-11].getX(),10,40);
				text.setFont(f);
				text.setForeground(Color.WHITE);
				text.setOpaque(false);
				pan.add(text);
			}
			pan.setOpaque(false);
			this.add(pan);
		}
		try {
			fondo = ImageIO.read(getClass().getResourceAsStream("/grafica/imagenes/gameover.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void paint(Graphics g) {
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(),this);
        setOpaque(false);
        super.paint(g);
	}
}
