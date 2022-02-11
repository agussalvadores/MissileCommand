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
				"misiles, El enemigo dispara misiles balísticos interplanetarios \n"+
				"(MBI) y misiles crucero, ambos tienen como objetivo destruir tus \n"+
				"ciudades y bases militares. Hay 2 tipos de misil crucero: misiles \n"+
				"cruceros tontos (que caen en línea recta) y misiles crucero \n"+
				"inteligentes, los cuales tratarán de evadir tus misilesantibalísticos (MABs). \n \n"+

				"El enemigo ataca en una serie de oleadas que puede variar en \n"+
				"el número de misiles balísticos interplanetarios que atacan. \n"+
				"Cada oleada de misiles se mueve más rápido que la oleada \n"+
				"anterior. Mientras más rápida es la oleada, más difícil es \n"+
				"defender las ciudades. Por este motivo, mientras más rápida es la oleada, \n"+ 
				"más alto será el puntaje ganado. \n"+
				"Con cada oleada, tenés 30 MABs para defenderte, 10 en cada uno de los silos, \n"+ 
				"los cuales están ubicados en la parte inferior de la pantalla, a la izquierda, \n"+ 
				"al centro y a la derecha. \n"+
				"Una vez que has disparado los 30 MABs, estarás indefenso hasta \n"+ 
				"que comience una nueva oleada. \n"+
				"Cada vez que un MAB llega a su destino o alcanza algún misil \n"+ 
				"enemigo ocurre una explosión. \n"+
				"La ondaexpansiva de esta explosión puede ocasionar que otros misiles \n"+ 
				"balísticos interplanetarios o crucero que se encuentran en la zona de impacto \n"+
				"también sean destruidos. Si la explosión del MAB no alcanza lacabeza del misil, \n"+
				"éste seguirá con su trayectoria, sólo se verá afectada \n"+
				"la estela que deja a su paso.. \n \n"+

				"Debes tener en cuenta que hay un umbral (línea horizontal imaginaria) en el \n"+
				"campo de juego, debajo dela cual no podrás disparar misiles. \n"+
				"De esta manera se protege a los silos y las ciudades de laautodestrucción.\n"+
				"Si logras sobrevivir a la oleada, pasarás al siguiente nivel, \n \n"+
				"si no logras sobrevivir a la oleada, tendrás sólootra oportunidad \n"+
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
