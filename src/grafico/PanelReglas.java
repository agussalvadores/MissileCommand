package grafico;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanelReglas extends JPanel implements ActionListener{
	private JButton volver = new JButton("Atras");
	private Image fondo;
	private String texto = "";
	
	public PanelReglas() {
		this.setSize(507, 470);
		agregarComponentes();
		this.volver.addActionListener(this);
		try {
			fondo = ImageIO.read(getClass().getResourceAsStream("/grafica/imagenes/fondo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void agregarComponentes() {
		try {
			String cadena;
			FileReader reader;
			reader = new FileReader("reglas.txt");
			BufferedReader b = new BufferedReader(reader);
			while((cadena = b.readLine())!= null) {
				this.texto= this.texto + cadena + "\n";
			}
			b.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setLayout(new FlowLayout());
		Dimension dime= new Dimension();
		dime.setSize(500, 360);
		JTextArea textarea= new JTextArea("El objetivo del juego es defender tus ciudades y bases de \n"+
				"misiles, El enemigo dispara misiles bal�sticos interplanetarios \n"+
				"(MBI) y misiles crucero, ambos tienen como objetivo destruir tus \n"+
				"ciudades y bases militares. Hay 2 tipos de misil crucero: misiles \n"+
				"cruceros tontos (que caen en l�nea recta) y misiles crucero \n"+
				"inteligentes, los cuales tratar�n de evadir tus misilesantibal�sticos (MABs). \n \n"+

				"El enemigo ataca en una serie de oleadas que puede variar en \n"+
				"el n�mero de misiles bal�sticos interplanetarios que atacan. \n"+
				"Cada oleada de misiles se mueve m�s r�pido que la oleada \n"+
				"anterior. Mientras m�s r�pida es la oleada, m�s dif�cil es \n"+
				"defender las ciudades. Por este motivo, mientras m�s r�pida es la oleada, \n"+ 
				"m�s alto ser� el puntaje ganado. \n"+
				"Con cada oleada, ten�s 30 MABs para defenderte, 10 en cada uno de los silos, \n"+ 
				"los cuales est�n ubicados en la parte inferior de la pantalla, a la izquierda, \n"+ 
				"al centro y a la derecha. \n"+
				"Una vez que has disparado los 30 MABs, estar�s indefenso hasta \n"+ 
				"que comience una nueva oleada. \n"+
				"Cada vez que un MAB llega a su destino o alcanza alg�n misil \n"+ 
				"enemigo ocurre una explosi�n. \n"+
				"La ondaexpansiva de esta explosi�n puede ocasionar que otros misiles \n"+ 
				"bal�sticos interplanetarios o crucero que se encuentran en la zona de impacto \n"+
				"tambi�n sean destruidos. Si la explosi�n del MAB no alcanza lacabeza del misil, \n"+
				"�ste seguir� con su trayectoria, s�lo se ver� afectada \n"+
				"la estela que deja a su paso.. \n \n"+

				"Debes tener en cuenta que hay un umbral (l�nea horizontal imaginaria) en el \n"+
				"campo de juego, debajo dela cual no podr�s disparar misiles. \n"+
				"De esta manera se protege a los silos y las ciudades de laautodestrucci�n.\n"+
				"Si logras sobrevivir a la oleada, pasar�s al siguiente nivel, \n \n"+
				"si no logras sobrevivir a la oleada, tendr�s s�lootra oportunidad \n"+
				"para volver a defenderte. \n \n"+
				"El juego finaliza cuando todas las ciudades son destruidas",10,40);
		JScrollPane scroll = new JScrollPane(textarea);
		JPanel cuadro = new JPanel();
		scroll.setPreferredSize(dime);
		cuadro.setSize(dime);
		scroll.setOpaque(false);
		scroll.setBorder(null);
		cuadro.setOpaque(false);
		textarea.setOpaque(false);
		cuadro.add(scroll);
		this.add(cuadro);
		this.add(volver);
	}

	public void paint(Graphics g) {
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(),this);
        setOpaque(false);
        super.paint(g);
 }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(volver)){
			Frame.INSTANCE.setContentPane(new PanelPrincipal(6,1));
		}
	}
}
