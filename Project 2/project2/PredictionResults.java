package project2;

import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import project1.*;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import project1.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class PredictionResults extends JPanel {
	
	//Initializing variables:
	private Car car;
	private Predictor dude;
	private JFrame main;
	private int buyprice;
	
	private ArrayList<Car> resultreturn;
	private ArrayList<String> carstoshow;
	
	private JLabel lblPredictionResults;
	private JLabel lblYourCarsOverall;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblTradeinValueOffered;
	private JLabel lblselectOne;
	
	private JLabel ratinglabel;
	private JLabel matchlabel;
	private JLabel tradevallabel;
	
	private JButton Backbtn;
	private JButton Exitbtn;
	private JButton btnLogOut;
	
	private JComboBox carresultscombo;
	
	private int chosenval = 0;
	private String finalrating;
	private int tradeinval = 0;
	
	
	public PredictionResults(Car a, int b, JFrame c) {
		
		car = new Car();
		dude = new Predictor("/project2/carTrain.DATA");
		car = a;
		main = c;
		buyprice = b;
		resultreturn = new ArrayList<Car>();
		carstoshow = new ArrayList<String>();
		
		setBackground(Color.GRAY);
		setLayout(null);
		
		repaint();
		
		lblPredictionResults = new JLabel("Prediction Results!");
		lblPredictionResults.setForeground(UIManager.getColor("text"));
		lblPredictionResults.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPredictionResults.setBounds(182, 11, 230, 38);
		add(lblPredictionResults);
		
		lblYourCarsOverall = new JLabel("Your cars overall rating:");
		lblYourCarsOverall.setForeground(UIManager.getColor("text"));
		lblYourCarsOverall.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblYourCarsOverall.setBounds(48, 75, 244, 27);
		add(lblYourCarsOverall);
		
		ratinglabel = new JLabel(dude.getPrediction(car));
		ratinglabel.setForeground(UIManager.getColor("text"));
		ratinglabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		ratinglabel.setBounds(322, 82, 164, 14);
		add(ratinglabel);
		
		label = new JLabel("Exact match found? ");
		label.setForeground(UIManager.getColor("text"));
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(48, 113, 244, 27);
		add(label);
		
		matchlabel = new JLabel(String.valueOf(dude.equalcheck(car)));
		matchlabel.setForeground(UIManager.getColor("text"));
		matchlabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		matchlabel.setBounds(322, 120, 164, 14);
		add(matchlabel);
		
		label_1 = new JLabel("Car(s) Returned from search: ");
		label_1.setForeground(UIManager.getColor("text"));
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setBounds(48, 151, 196, 27);
		add(label_1);
		
		if (dude.equalcheck(car))
			carstoshow.add(dude.returnequal(car).toString());
		else
		{
			resultreturn = dude.threesimilarreturn(car);
			for (int z = 0; z<resultreturn.size(); z++)
			{
				carstoshow.add(resultreturn.get(z).toString());
			}
		}
		carresultscombo = new JComboBox(carstoshow.toArray());
		carresultscombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If exact match is found, and only one option is possible
				if (carstoshow.size()==1)
					finalrating = dude.returnequal(car).getRating();
				else
				{
				chosenval = carresultscombo.getSelectedIndex();
				finalrating = resultreturn.get(chosenval).getRating();
				}
				
				lblselectOne.setText("SELECTED!");
				//Setting up the trade in value
				if (finalrating.equals("unacc"))
					tradeinval = (int) (buyprice*(0.25));
				else if (finalrating.equals("acc"))
					tradeinval = (int) (buyprice*(0.50));
				else if (finalrating.equals("good"))
					tradeinval = (int) (buyprice*(0.70));
				else if (finalrating.equals("vgood"))
					tradeinval = (int) (buyprice*(0.90));
				
				tradevallabel.setText("$"+tradeinval+"!!!");
			}
		});
		carresultscombo.setBounds(254, 165, 340, 20);
		add(carresultscombo);
		
		lblTradeinValueOffered = new JLabel("Trade-in value offered based on selected car above:");
		lblTradeinValueOffered.setForeground(UIManager.getColor("text"));
		lblTradeinValueOffered.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTradeinValueOffered.setBounds(48, 225, 476, 14);
		add(lblTradeinValueOffered);
		
		tradevallabel = new JLabel("");
		tradevallabel.setForeground(UIManager.getColor("text"));
		tradevallabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		tradevallabel.setBounds(216, 259, 196, 14);
		add(tradevallabel);
		
		Backbtn = new JButton("Back");
		Backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				main.getContentPane().add(new PredictorRetrieverPanel(main));
			}
		});
		Backbtn.setBounds(24, 322, 89, 23);
		add(Backbtn);
		
		Exitbtn = new JButton("Customer happy. Exit!");
		Exitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		Exitbtn.setBounds(409, 322, 174, 23);
		add(Exitbtn);
		
		lblselectOne = new JLabel("(none selected!)");
		lblselectOne.setForeground(UIManager.getColor("text"));
		lblselectOne.setBounds(48, 181, 117, 14);
		add(lblselectOne);
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				main.getContentPane().add(new UsernamePanel(main));
			}
		});
		btnLogOut.setBounds(236, 322, 89, 23);
		add(btnLogOut);
	}
	
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    ImageIcon icon = new ImageIcon(this.getClass().getResource("/project2/final.jpg"));
	    g.drawImage(icon.getImage(), 0, 0, null);
	}
}
