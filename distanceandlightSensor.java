import javax.swing.*;
import java.awt.*;
	
public class distanceandlightSensor {
	public static void main(String args[]){
	JFrame frame = new JFrame("Menu");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(800,800);	
	JPanel panel = new JPanel(); // the panel is not visible in output
	JTextArea ta = new JTextArea();
	panel.add(ta);
	ta.setText("Hello");
	frame.getContentPane().add(BorderLayout.SOUTH,panel);
	frame.getContentPane().add(BorderLayout.CENTER,ta);
	frame.setVisible(true);
	}
}
