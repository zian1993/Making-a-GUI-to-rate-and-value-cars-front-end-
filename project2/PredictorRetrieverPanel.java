package project2;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSlider;
import project1.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class PredictorRetrieverPanel extends JPanel {

	//Initializing all variables.

	private JFrame main;
	private Car car;

	private JComboBox maintcomboBox;
	private String[] options = {"vhigh","high","med","low"};

	private JComboBox buycomboBox;

	private JRadioButton smallboot;
	private JRadioButton medboot;
	private JRadioButton bigboot;

	private JSpinner doorspinner;
	private SpinnerNumberModel doormodel;
	private JSpinner peoplespinner;
	private SpinnerNumberModel peoplemodel;

	private JProgressBar completionBar;
	private JButton Done;
	private JSlider safetyslider;

	private JLabel lblWelcomeToThe;
	private JLabel lblPleaseEnterThe;
	private JLabel lblMaintenanceCosts;
	private JLabel lblBootCapacity;
	private JLabel lblNumberOfDoors;
	private JLabel lblNumberOfPeople;
	private JLabel lblBuyingCost;
	private JLabel lblCompletion;
	private JLabel lblSafety;
	private JLabel lblSafetyLevel;
	private JLabel lblpressEnterWhen;

	private int tasks = 0;
	private ButtonGroup radiomen;
	private JLabel lblOriginalPurchasePrice;
	private JTextField buytext;

	private int buyprice = 0;
	private String errormsg = new String();

	public PredictorRetrieverPanel(JFrame f) {
		setForeground(UIManager.getColor("text"));

		main = f;
		car = new Car();

		setBackground(Color.GRAY);
		setLayout(null);

		repaint();

		completionBar = new JProgressBar();
		completionBar.setBounds(31, 350, 243, 14);
		completionBar.setIndeterminate(true);
		add(completionBar);

		lblWelcomeToThe = new JLabel("Welcome to the Predictor Panel!");
		lblWelcomeToThe.setForeground(UIManager.getColor("text"));
		lblWelcomeToThe.setBounds(127, 23, 208, 14);
		add(lblWelcomeToThe);

		lblPleaseEnterThe = new JLabel("Please enter the customer car attributes!");
		lblPleaseEnterThe.setForeground(UIManager.getColor("text"));
		lblPleaseEnterThe.setBounds(106, 40, 301, 14);
		add(lblPleaseEnterThe);

		maintcomboBox = new JComboBox(options);
		maintcomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				car.setMaint((String)maintcomboBox.getSelectedItem());
				update();
			}
		});
		maintcomboBox.setBounds(39, 82, 106, 25);
		add(maintcomboBox);

		lblMaintenanceCosts = new JLabel("Maintenance Costs:");
		lblMaintenanceCosts.setForeground(UIManager.getColor("text"));
		lblMaintenanceCosts.setBounds(39, 65, 157, 14);
		add(lblMaintenanceCosts);

		radiomen = new ButtonGroup();

		smallboot = new JRadioButton("small");
		smallboot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.setTrunk(smallboot.getText());
				update();
			}
		});
		smallboot.setBounds(36, 145, 109, 23);
		add(smallboot);

		medboot = new JRadioButton("med");
		medboot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.setTrunk(medboot.getText());
				update();
			}
		});
		medboot.setBounds(36, 171, 109, 23);
		add(medboot);

		bigboot = new JRadioButton("big");
		bigboot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.setTrunk(bigboot.getText());
				update();
			}
		});
		bigboot.setBounds(36, 197, 109, 23);
		add(bigboot);

		radiomen.add(smallboot);
		radiomen.add(medboot);
		radiomen.add(bigboot);

		lblBootCapacity = new JLabel("Boot Capacity:");
		lblBootCapacity.setForeground(UIManager.getColor("text"));
		lblBootCapacity.setBounds(38, 124, 158, 14);
		add(lblBootCapacity);

		lblNumberOfDoors = new JLabel("Number of doors:");
		lblNumberOfDoors.setForeground(UIManager.getColor("text"));
		lblNumberOfDoors.setBounds(234, 65, 142, 14);
		add(lblNumberOfDoors);

		doormodel = new SpinnerNumberModel(2,2,5,1);
		doorspinner = new JSpinner(doormodel);
		doorspinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				car.setDoors((Integer)doorspinner.getValue());
				update();
			}
		});
		doorspinner.setBounds(267, 82, 36, 34);
		add(doorspinner);

		lblNumberOfPeople = new JLabel("Number of people:");
		lblNumberOfPeople.setForeground(UIManager.getColor("text"));
		lblNumberOfPeople.setBounds(238, 175, 149, 14);
		add(lblNumberOfPeople);

		peoplemodel = new SpinnerNumberModel(2,2,5,1);
		peoplespinner = new JSpinner(peoplemodel);
		peoplespinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (((Integer)peoplespinner.getValue()) == 3)
					car.setPersons(4);
				else
					car.setPersons((Integer)peoplespinner.getValue());
				update();
			}
		});
		peoplespinner.setBounds(267, 191, 36, 34);
		add(peoplespinner);

		lblBuyingCost = new JLabel("Buying cost:");
		lblBuyingCost.setForeground(UIManager.getColor("text"));
		lblBuyingCost.setBounds(418, 65, 124, 14);
		add(lblBuyingCost);

		lblCompletion = new JLabel("Completion:");
		lblCompletion.setForeground(UIManager.getColor("text"));
		lblCompletion.setBounds(31, 325, 96, 14);
		add(lblCompletion);

		Done = new JButton("Done!");
		Done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Forcing the user to enter all the data before going to the next
				//panel, so that the car is set up right
				if ((buyprice == 0) || (car.getBoot().equals(""))||(car.getBuy().equals(""))
						||(car.getDoors()==0)||(car.getMaint().equals(""))||(car.getPersons()== 0)
						||(car.getSafety().equals("")))
				{
					if (buyprice == 0)
						errormsg = "Original Purchase Price (press ENTER)";
					if (car.getBoot().equals(""))
						errormsg += "\nBoot Size";
					if (car.getBuy().equals(""))
						errormsg += "\nBuying Price";
					if (car.getDoors()==0)
						errormsg += "\nNumber of Doors";
					if (car.getMaint().equals(""))
						errormsg += "\nMaintenance";
					if (car.getPersons()== 0)
						errormsg += "\nNumber of People";
					if (car.getSafety().equals(""))
						errormsg += "\nSafety Rating";

					JOptionPane.showMessageDialog(null,
							"Error: Please complete the following field(s): \n"+errormsg, "Error my Friend!",
							JOptionPane.ERROR_MESSAGE);
					errormsg = "";
					return;
				}

				//If all is well, going onto the next panel
				setVisible(false);
				main.getContentPane().add(new PredictionResults(car, buyprice, main));
			}
		});
		Done.setBounds(443, 341, 89, 23);
		add(Done);

		lblSafety = new JLabel("Safety:");
		lblSafety.setForeground(UIManager.getColor("text"));
		lblSafety.setBounds(418, 175, 124, 14);
		add(lblSafety);

		safetyslider = new JSlider();
		safetyslider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (safetyslider.getValue()<=33)
				{
					car.setSafety("low");
					lblSafetyLevel.setText("low");
				}
				else if (safetyslider.getValue()<=66)
				{
					car.setSafety("med");
					lblSafetyLevel.setText("medium");
				}
				else
				{
					car.setSafety("high");
					lblSafetyLevel.setText("awesome!");
				}
				update();
			}
		});
		safetyslider.setBounds(372, 197, 160, 23);
		add(safetyslider);

		buycomboBox = new JComboBox(options);
		buycomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				car.setBuying((String)buycomboBox.getSelectedItem());
				update();
			}
		});
		buycomboBox.setBounds(418, 88, 96, 23);
		add(buycomboBox);

		lblSafetyLevel = new JLabel("Safety level");
		lblSafetyLevel.setForeground(UIManager.getColor("text"));
		lblSafetyLevel.setBounds(436, 231, 124, 14);
		add(lblSafetyLevel);

		lblOriginalPurchasePrice = new JLabel("Original purchase price (ints only): ");
		lblOriginalPurchasePrice.setForeground(UIManager.getColor("text"));
		lblOriginalPurchasePrice.setBounds(168, 252, 233, 14);
		add(lblOriginalPurchasePrice);

		buytext = new JTextField();
		buytext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Getting the purchase price of the car
				//Setting up if-else statements in case user enters
				//something other than an integer value.
				if (buytext.getText().matches("\\d+"))
					buyprice = Integer.parseInt(buytext.getText());
				else
				{
					JOptionPane.showMessageDialog(null,
							"Error: Only integers my friend!", "Error my Friend!",
							JOptionPane.ERROR_MESSAGE);
					buytext.setText("");
				}
				update();
			}
		});
		buytext.setBounds(200, 284, 118, 20);
		add(buytext);
		buytext.setColumns(10);
		
		lblpressEnterWhen = new JLabel("(press ENTER when done)");
		lblpressEnterWhen.setForeground(Color.WHITE);
		lblpressEnterWhen.setBounds(168, 265, 167, 14);
		add(lblpressEnterWhen);
	}

	//Created this to update the progress bar in a different thread.
	private void update() {

		if (tasks<7)
			tasks++;

		completionBar.setIndeterminate(false);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				//This will be called on the EDT
				completionBar.setValue((tasks/7)*100);
			}
		});
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/project2/predictorpanel.jpg"));
		g.drawImage(icon.getImage(), 0, 0, null);
	}
}
