package project2;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class AwesomeCarPredictor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Awesome Car Predictor!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(620,520));
		//frame.setBounds(0,0,800,500);
		
		//Setting the icon
		ImageIcon icon = new ImageIcon(AwesomeCarPredictor.class.getResource("/project2/icon.jpg"));
		frame.setIconImage(icon.getImage());

		frame.getContentPane().add(new UsernamePanel(frame));
		
		frame.pack();
		frame.setVisible(true);
	}

}
