package grafico;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import control.Guardar;
import modelo.Ranking;

public class PanelRanking extends JPanel implements ActionListener{
	private JButton volver;
	private int max;
	private int ole;
	private Image fondo;
	private JTable grilla;
	private Object[] titulos= {"POSICION","NOMBRE","PUNTAJE","TIEMPO (s)"};
	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public PanelRanking(int puntaje, boolean anotar,int max,int ole,int tiempo) {
		System.out.println("RANKING");
		this.ole=ole;
		this.max=max;
		setSize(525, 480);
		try {
			fondo = ImageIO.read(getClass().getResourceAsStream("/grafica/imagenes/fondobase.png"));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		/*EL CODIGO ESTA COMENTADO PORQUE ESA SERIA LA FORMA DE 
		 * CARGAR EL ARCHIVO "ranking.dat" PERO NOS TIRA UN ERROR QUE 
		 * INTENTAMOS SOLUCIONAR PORBANDO UN MONTON DE FORMAS Y NO PUDIMOS
		 * POR ESO HICIMOS LA ALTERNATIVA DE CREAR LOS PUNTAJES CADA VEZ QUE SE EJECUTA EL JUEGO
		 */
		
		ObjectOutputStream salida = null;
		ObjectInputStream entrada = null;
//		Ranking[] punt = new Ranking[6];
//		punt[0] = new Ranking(3000, "Homero", 1,40);
//		punt[1] = new Ranking(2500, "Marge", 2,38);
//		punt[2] = new Ranking(2000, "Bart", 3,36);
//		punt[3] = new Ranking(1500, "Lisa", 4,34);
//		punt[4] = new Ranking(1000, "Maggie", 5,32);
//		punt[5] = new Ranking(500, "Moe", 6,30);
		try {
//			salida = new ObjectOutputStream(new FileOutputStream("C:/Users/Agus Salvadores/Desktop/ranking.dat"));
//			salida.writeObject(punt);
//			salida.close();
			entrada = new ObjectInputStream(new FileInputStream("C:/Users/Agus Salvadores/Desktop/ranking.dat"));
			Ranking[] historico = (Ranking[]) entrada.readObject();
			entrada.close();
			if (anotar) {
				Ranking[] aux = new Ranking[max];
				aux[0] = new Ranking(700, "Homero", 1,300);
				aux[1] = new Ranking(600, "Marge", 2,300);
				aux[2] = new Ranking(500, "Bart", 3,300);
				aux[3] = new Ranking(400, "Lisa", 4,300);
				aux[4] = new Ranking(200, "Maggie", 5,300);
				aux[5] = new Ranking(100, "Moe", 6,300);
				anotar=false;
				String nombre = "";
				for (int i = 0; i < max; i++) {
					if ((puntaje>historico[i].getPuntos())||((puntaje==historico[i].getPuntos())&&(tiempo<historico[i].getTiempo()))) {
								Guardar c = new Guardar();
								c.setVisible(true);
								nombre = c.getTexto();
								if (nombre.length() > 20) {
									String cortar;
									cortar = nombre.substring(0, 20);
									nombre = cortar;
								}
								aux[i].setNombre(nombre);
								aux[i].setPuntos(puntaje);
								aux[i].setPos(i+1);
								aux[i].setTiempo(tiempo);
								for(int h=i+1;h<max;h++) {
									aux[h]=historico[h-1];
								}
								i=max;
						}
					else {
						aux[i]=historico[i];
					}
				}
				historico=aux;
			}
			salida = new ObjectOutputStream(new FileOutputStream("C:/Users/Agus Salvadores/Desktop/ranking.dat"));
			salida.writeObject(historico);
			salida.close();
			agregarComponentes(historico);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void agregarComponentes(Ranking[] punt) {
		GridLayout g=new GridLayout();
		g.setColumns(1);
		g.setVgap(0);
		g.setRows(3);
		this.setLayout(g);
		Object[][] datos= new Object[max][4];
		for(int i=0;i<max;i++) {
			datos[i][0]=punt[i].getPos();
			datos[i][1]=punt[i].getNombre();
			datos[i][2]=punt[i].getPuntos();
			datos[i][3]=punt[i].getTiempo();
		}
		grilla=new JTable();
		DefaultTableModel modelo = new DefaultTableModel(titulos,0);
		modelo.addRow(titulos);
		for(int i=0;i<max;i++) {
			modelo.addRow(datos[i]);
		}
		grilla.setModel(modelo);
		JPanel p= new JPanel();
		p.setOpaque(false);
		this.add(p);
		JPanel p1 = new JPanel();
		p1.setOpaque(false);
		p1.add(grilla);
		this.add(p1);
		this.volver = new JButton("Volver");
		this.volver.addActionListener(this);
		JPanel p2= new JPanel();
		p2.setOpaque(false);
		p2.add(this.volver);
		this.add(p2);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(volver)) {
			Frame.INSTANCE.setContentPane(new PanelPrincipal(max,ole));
		}
	}
	public void paint(Graphics g) {
       g.drawImage(fondo, 0, 0, getWidth(), getHeight(),this);
        setOpaque(false);
        super.paint(g);
	}
}
