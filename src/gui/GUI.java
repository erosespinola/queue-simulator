package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import simulator.Simulator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.Vector;

import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {
	private JTextField textFieldSeed;
	private JTextField textFieldLambda;
	private JTextField textFieldMiu;
	private JTextField textFieldTime;
	
	public GUI() {
		setItems();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		getContentPane().setLayout(null);
		setResizable(false);
		setTitle("Simulador de Cola M/M/1");
		setSize(new Dimension(600, 400));
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getActionCommand().equals("Simulate")) {
			int time = 0;
			double lambda = 0, seed = 0, miu = 0;
			if(!this.textFieldSeed.getText().isEmpty() && isNumeric(this.textFieldSeed.getText())
					&& !this.textFieldSeed.getText().isEmpty() && isNumeric(this.textFieldSeed.getText())
					&& !this.textFieldSeed.getText().isEmpty() && isNumeric(this.textFieldSeed.getText())
					&& !this.textFieldSeed.getText().isEmpty() && isNumeric(this.textFieldSeed.getText())) {
				
				try {
					seed = Double.parseDouble(this.textFieldSeed.getText());
					lambda = Double.parseDouble(this.textFieldLambda.getText());
					miu = Double.parseDouble(this.textFieldMiu.getText());
					time = Integer.parseInt(this.textFieldTime.getText());
				} 
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Números incorrectos");
					time = -1;
					e.printStackTrace();
				}
				

        Simulator simulator = new Simulator(lambda, miu, seed);
        // advance simulator for given time units
        while(time-- >= 0) {
          simulator.Advance();
        }
				return;
			}
			JOptionPane.showMessageDialog(null, "Fill out all the fields with numeric values");
		}
	}
	
	private void setItems() {
		JLabel lblSeed = new JLabel("S");
		lblSeed.setBounds(10, 14, 128, 14);
		getContentPane().add(lblSeed);
		
		textFieldSeed = new JTextField();
		textFieldSeed.setText("35");
		textFieldSeed.setToolTipText("Set seed\r\n");
		textFieldSeed.setBounds(25, 11, 118, 20);
		getContentPane().add(textFieldSeed);
		textFieldSeed.setColumns(10);
		
		JLabel lblLambda = new JLabel("\u03BB");
		lblLambda.setBounds(10, 39, 128, 14);
		getContentPane().add(lblLambda);
		
		textFieldLambda = new JTextField();
		textFieldLambda.setText("10");
		textFieldLambda.setToolTipText("Set lambda");
		textFieldLambda.setBounds(25, 36, 118, 20);
		getContentPane().add(textFieldLambda);
		textFieldLambda.setColumns(10);
		
		JLabel lblMiu = new JLabel("\u03BC");
		lblMiu.setBounds(10, 64, 128, 14);
		getContentPane().add(lblMiu);
		
		textFieldMiu = new JTextField();
		textFieldMiu.setText("15");
		textFieldMiu.setToolTipText("Set miu");
		textFieldMiu.setBounds(25, 61, 118, 20);
		getContentPane().add(textFieldMiu);
		textFieldMiu.setColumns(10);
		
		JLabel lblTime = new JLabel("T");
		lblTime.setBounds(10, 92, 128, 14);
		getContentPane().add(lblTime);
		
		textFieldTime = new JTextField();
		textFieldTime.setText("50");
		textFieldTime.setToolTipText("Set time");
		textFieldTime.setBounds(25, 89, 118, 20);
		getContentPane().add(textFieldTime);
		textFieldTime.setColumns(10);
		
		JButton btnSimulate = new JButton("Simulate");
		btnSimulate.setToolTipText("");
		btnSimulate.setBounds(10, 120, 128, 23);
		getContentPane().add(btnSimulate);
		
		JPanel panelResults = new JPanel();
		panelResults.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelResults.setBackground(Color.WHITE);
		panelResults.setBounds(148, 11, 436, 349);
		getContentPane().add(panelResults);
		
		btnSimulate.addActionListener(this);
	}
	
	private boolean isNumeric(String str) {  
		try {
			Double.parseDouble(str);
		}
		catch(NumberFormatException e) {  
			return false;  
		}  
		return true;  
	}

	public static void main(String[] args) {
		GUI g = new GUI();
	}
}
