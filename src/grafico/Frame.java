package grafico;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Frame extends JFrame{
	
	public static final Frame INSTANCE = new Frame();
	
	public Frame() {
		this.setContentPane(new PanelPrincipal(6,1));
		this.setSize(525, 480);
		this.setTitle("Missile Command");
		this.setVisible(true);
		
	}
	public static void main(String[] args){
		Frame frame = INSTANCE;
	}
}