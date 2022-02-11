package grafico;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanelConfiguracion extends JPanel implements ActionListener{
	private JList<String> lis1;
	private JList<String> lis2;
	private JButton volver;
	private JButton guardar;
	private JButton ranking;
	private JTextArea text1;
	private JTextArea text2;
	private int numole=1;
	private int max=6;
	private Image fondo;
	
	
	
	public PanelConfiguracion() {
		setSize(525, 480);
		try {
			fondo = ImageIO.read(getClass().getResourceAsStream("/grafica/imagenes/fondobase.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		GridLayout g=new GridLayout();
		g.setColumns(3);
		g.setHgap(5);
		g.setVgap(50);
		g.setRows(4);
		this.setLayout(g);
		String[] oles = { "1", "2", "3", "4","5","6","7","8","9","10","11","12","13","14","15" };
		String[] maxs = { "1", "2", "3", "4","5","6"};
		lis1 = new JList(oles);
		lis2 = new JList(maxs);
		lis2.setVisibleRowCount(4);
		lis1.setVisibleRowCount(4);
		guardar = new JButton("Confirmar");
		guardar.addActionListener(this);
		volver = new JButton("Volver");
		volver.addActionListener(this);
		ranking = new JButton("Confirmar");
		ranking.addActionListener(this);
		
		for(int i=0;i<3;i++) {
			JPanel j = new JPanel();
			j.setOpaque(false);
			this.add(j);
		}
		this.text1 = new JTextArea("ELIJA OLEADA");
		text1.setOpaque(false);
		text1.setForeground(Color.WHITE);
		JPanel p1= new JPanel();
		p1.setOpaque(false);
		p1.add(text1);
		this.add(p1);
		
		JPanel p2= new JPanel();
		p2.setOpaque(false);
		JScrollPane scroll=new JScrollPane(lis1);
		p2.add(scroll);
		this.add(p2);
		
		JPanel p3= new JPanel();
		p3.setOpaque(false);
		p3.add(guardar);
		this.add(p3);
		
		this.text2 = new JTextArea("MODIFICAR RANKING");
		text2.setOpaque(false);
		text2.setForeground(Color.WHITE);
		JPanel p4= new JPanel();
		p4.setOpaque(false);
		p4.add(text2);
		this.add(p4);
		
		JPanel p5 = new JPanel();
		p5.setOpaque(false);
		p5.add(new JScrollPane(lis2));
		this.add(p5);
		
		JPanel p6 = new JPanel();
		p6.setOpaque(false);
		p6.add(ranking);
		this.add(p6);
		
		JPanel p7 = new JPanel();
		p7.setOpaque(false);
		this.add(p7);
		
		JPanel p8 = new JPanel();
		p8.setOpaque(false);
		p8.add(volver);
		this.add(p8);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(guardar)) {
			if(lis1.getSelectedValue()!=null) {
				numole=Integer.parseInt((String) lis1.getSelectedValue()) ;
			}
		}
		if(arg0.getSource().equals(ranking)) {
			if(lis2.getSelectedValue()!=null) {
			max=Integer.parseInt((String) lis2.getSelectedValue());
			}
			
		}
		Frame.INSTANCE.setContentPane(new PanelPrincipal(max,numole));
	}
	public void paint(Graphics g) {
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(),this);
        setOpaque(false);
        super.paint(g);
	}

}
