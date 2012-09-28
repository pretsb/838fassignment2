import javax.swing.*;
import java.awt.*;
	
public class distanceandlightSensor {
	public static void main(String args[]){
	JFrame frame = new JFrame("Menu");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(200,200);	
	JPanel panel = new JPanel(); // the panel is not visible in output
	JTextArea ta = new JTextArea();
	panel.add(ta);
	
	frame.getContentPane().add(BorderLayout.SOUTH,panel);
	frame.getContentPane().add(BorderLayout.CENTER,ta);
	frame.setVisible(true);
	
	for(int size= 16;size<32;size++){
	try {
		Thread.sleep(25);
		ta.setFont(new Font("Serif", Font.ITALIC, size));
		ta.setForeground(Color.blue);
		ta.setText("Hello");
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
	}
}
