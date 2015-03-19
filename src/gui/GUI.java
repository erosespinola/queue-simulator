package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class GUI extends JFrame{
	private JTextField textFieldSeed;
	private JTextField textFieldLambda;
	private JTextField textFieldMiu;
	private JTextField textField;
	
	public GUI() {
		setResizable(false);
		setTitle("Queue Simulator");
		getContentPane().setLayout(null);
		
		JLabel lblSeed = new JLabel("S");
		lblSeed.setBounds(10, 14, 6, 14);
		getContentPane().add(lblSeed);
		
		textFieldSeed = new JTextField();
		textFieldSeed.setBounds(20, 11, 118, 20);
		getContentPane().add(textFieldSeed);
		textFieldSeed.setColumns(10);
		
		JLabel lblLambda = new JLabel("\u03BB");
		lblLambda.setBounds(10, 39, 6, 14);
		getContentPane().add(lblLambda);
		
		textFieldLambda = new JTextField();
		textFieldLambda.setBounds(20, 36, 118, 20);
		getContentPane().add(textFieldLambda);
		textFieldLambda.setColumns(10);
		
		JLabel lblMiu = new JLabel("\u03BC");
		lblMiu.setBounds(10, 64, 6, 14);
		getContentPane().add(lblMiu);
		
		textFieldMiu = new JTextField();
		textFieldMiu.setBounds(20, 61, 118, 20);
		getContentPane().add(textFieldMiu);
		textFieldMiu.setColumns(10);
		
		JButton btnSimulate = new JButton("Simulate");
		btnSimulate.setBounds(10, 120, 128, 23);
		getContentPane().add(btnSimulate);
		
		textField = new JTextField();
		textField.setBounds(20, 89, 118, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblT = new JLabel("T");
		lblT.setBounds(10, 92, 6, 14);
		getContentPane().add(lblT);
	}
}
