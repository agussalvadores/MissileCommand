package grafico;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import modelo.Juego;
import modelo.Oleada;

public class PanelPrincipal extends JPanel implements ActionListener{
	private JButton reglas=new JButton("Reglas del Juego");
	private JButton ranking=new JButton("Ranking");
	private JButton jugar=new JButton("¡A Jugar!");
	private JButton conf= new JButton("Configuracion");
	private Image fondo;
	private PanelJuego panel;
	private int max;
	private int numole;
	
	public PanelPrincipal(int max, int ole){
		this.setSize(510, 433);
		this.max=max;
		this.numole=ole;
		agregarComponentes();
		try {
			fondo = ImageIO.read(getClass().getResourceAsStream("/grafica/imagenes/missile.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void agregarComponentes() {
		this.reglas.addActionListener(this);
		this.ranking.addActionListener(this);
		this.jugar.addActionListener(this);
		this.conf.addActionListener(this);
		GridLayout g=new GridLayout();
		g.setColumns(3);
		g.setHgap(50);
		g.setRows(7);
		this.setLayout(g);
		for(int i = 0; i <18;i++) {
			JPanel pan= new JPanel();
			if(i==2) {
				pan.add(conf);
			}
			pan.setOpaque(false);
			this.add(pan);
		}
		this.add(reglas);
		this.add(jugar);
		this.add(ranking);
		
	}
	
	public void paint(Graphics g) {
	        g.drawImage(fondo, 0, 0, getWidth(), getHeight(),this);
	        setOpaque(false);
	        super.paint(g);
	 }
	public void init () {
		Directorios.init();
	}
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource().equals(reglas)){
			Frame.INSTANCE.setContentPane(new PanelReglas());
		}
		if(arg0.getSource().equals(jugar)){
			final Juego jue= new Juego();
			jue.inicializar(numole);
			jue.inicializarOleada();
			panel = new PanelJuego(jue,numole);
			Frame.INSTANCE.setContentPane(panel);
			init();
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					if (!jue.terminoElJuego(jue.getAdmin().getListaAliados())) {
						jue.procesar();
						panel.repaint();
					}
					else {
						timer.cancel();
						System.out.println("TIMER CANCEL");
					}
				}
			};
			timer.schedule(task, 16, 16);
		}
		if(arg0.getSource().equals(ranking)){
			Frame.INSTANCE.setContentPane(new PanelRanking(0,false,max,numole,0));
		}
		if(arg0.getSource().equals(conf)){
			Frame.INSTANCE.setContentPane(new PanelConfiguracion());
		}
	}
}
