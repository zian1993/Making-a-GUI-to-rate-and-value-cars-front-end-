package project2;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.UIManager;

public class UsernamePanel extends JPanel implements ActionListener{
	private JTextField usernametext;
	private JLabel lblUsername;
	private JLabel lblOopsLooksLike;
	private JPasswordField passwordtext;
	private JFrame main;
	
	private JLabel lblPassword;
	private JLabel lblAwesomeCars;
	private JLabel lblrippingPeopleOff;
	private JLabel lblEnterUsernameAnd;

	
	public UsernamePanel(JFrame f) {
		main = f;
		
		setBackground(Color.GRAY);
		setForeground(UIManager.getColor("text"));
		setLayout(null);
		
		repaint();
		
		lblUsername = new JLabel("Username:");
		lblUsername.setForeground(UIManager.getColor("text"));
		lblUsername.setBounds(132, 158, 99, 21);
		add(lblUsername);
		
		usernametext = new JTextField();
		usernametext.setBounds(314, 158, 86, 20);
		add(usernametext);
		usernametext.setColumns(10);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setForeground(UIManager.getColor("text"));
		lblPassword.setBounds(132, 209, 99, 14);
		add(lblPassword);
		
		lblOopsLooksLike = new JLabel("Welcome my friend!");
		lblOopsLooksLike.setForeground(UIManager.getColor("text"));
		lblOopsLooksLike.setBounds(215, 261, 199, 14);
		add(lblOopsLooksLike);
		
		passwordtext = new JPasswordField();
		passwordtext.setBounds(314, 206, 86, 20);
		passwordtext.addActionListener(this);
		add(passwordtext);
		
		lblAwesomeCars = new JLabel("Awesome Cars!");
		lblAwesomeCars.setForeground(UIManager.getColor("text"));
		lblAwesomeCars.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAwesomeCars.setBounds(197, 30, 193, 21);
		add(lblAwesomeCars);
		
		lblrippingPeopleOff = new JLabel("-Ripping people off, only sometimes!");
		lblrippingPeopleOff.setForeground(UIManager.getColor("text"));
		lblrippingPeopleOff.setBounds(263, 62, 234, 14);
		add(lblrippingPeopleOff);
		
		lblEnterUsernameAnd = new JLabel("Enter Username and Password, then press Enter!");
		lblEnterUsernameAnd.setForeground(UIManager.getColor("text"));
		lblEnterUsernameAnd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEnterUsernameAnd.setBounds(109, 117, 375, 14);
		add(lblEnterUsernameAnd);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ((usernametext.getText().equals("prof"))&&((new String(passwordtext.getPassword())).equals("pass")))
		{
			this.setVisible(false);
			main.getContentPane().add(new PredictorRetrieverPanel(main));
		}
		else
		{
			lblOopsLooksLike.setText("Wrong info! Try again!");
			usernametext.setText("");
			passwordtext.setText("");
		}
	}
	
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    ImageIcon icon = new ImageIcon(this.getClass().getResource("/project2/icon.jpg"));
	    g.drawImage(icon.getImage(), 0, 0, null);
	}
}
