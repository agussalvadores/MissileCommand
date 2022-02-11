package control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;

public class Guardar extends JDialog  implements ActionListener {
	private JTextArea areaTexto;
	private JTextArea areaTexto2;
	private JButton guardar;
	private String texto="";
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Guardar() {
		this.setTitle("NUEVO PUNTAJE ALTO");
		this.setModal(true);
		this.setResizable(false);
		this.setSize(350,200);
		this.setLayout(new BorderLayout());
		this.guardar = new JButton("Guardar");
		this.guardar.setPreferredSize(new Dimension(50,25));
		this.guardar.addActionListener(this);
		this.add(guardar,BorderLayout.SOUTH);
		this.areaTexto=new JTextArea(texto);
		areaTexto.setFont(new Font("Verdana",Font.ITALIC,12));
		this.add(areaTexto,BorderLayout.CENTER);
		this.areaTexto2=new JTextArea("      NUEVO PUNTAJE ALTO!!! \n      INGRESE SU NOMBRE:      \n      ");
		this.areaTexto2.setEditable(false);
		areaTexto2.setFont(new Font("Verdana",Font.ITALIC,12));
		this.add(areaTexto2,BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.texto=this.areaTexto.getText();
		this.dispose();
	}
}
