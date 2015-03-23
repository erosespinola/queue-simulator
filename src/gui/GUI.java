package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import simulator.Simulator;

/**
 * Clase de GUI
 */
@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {
	private JTextField textFieldSeed;
	private JTextField textFieldLambda;
	private JTextField textFieldMiu;
	private JTextField textFieldTime;
	
	private JLabel lblW, lblWq, lblL, lblLq, lblO;
	
	/**
	 * Constructor de la clase
	 * Inicializa la ventana y elementos en ella
	 * 			
	 */
	public GUI() {
		setItems();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(true);
		setTitle("Simulador de Cola M/M/1");
		setSize(new Dimension(600, 400));
		setVisible(true);
	}
	
	/**
	 * Función que identifica acciones en la ventana
	 * 		
	 * @param arg0 Evento ocurrido en la ventana
	 * 	
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Simulate")) {
			int time = 0;
			int totalTime = 0;
			double lambda = 0, seed = 0, miu = 0;
			
			// Validación de los campos
			if (!this.textFieldSeed.getText().isEmpty()
					&& isNumeric(this.textFieldSeed.getText())
					&& !this.textFieldSeed.getText().isEmpty()
					&& isNumeric(this.textFieldSeed.getText())
					&& !this.textFieldSeed.getText().isEmpty()
					&& isNumeric(this.textFieldSeed.getText())
					&& !this.textFieldSeed.getText().isEmpty()
					&& isNumeric(this.textFieldSeed.getText())) {

				seed = Double.parseDouble(this.textFieldSeed.getText());
				lambda = Double.parseDouble(this.textFieldLambda.getText());
				miu = Double.parseDouble(this.textFieldMiu.getText());
				time = Integer.parseInt(this.textFieldTime.getText());
				totalTime = time;
				
				if (miu < lambda) {
					JOptionPane.showMessageDialog(null, "Números inválidos");
					return;
				}

				Simulator simulator = new Simulator(lambda, miu, seed);
				
				// Avanza el simuladar en unidades de tiempo dadas
				while (time-- >= 0) {
					simulator.Advance();
				}

				//Impresión de resultados en panel
				
				this.lblL.setText(this.lblL.getText() + simulator.L());
				this.lblLq.setText(this.lblLq.getText() + simulator.Lq());
				this.lblW.setText(this.lblW.getText() + simulator.W());
				this.lblWq.setText(this.lblWq.getText() + simulator.Wq());
				this.lblO.setText(this.lblO.getText() + simulator.O());

		        // Creación del diagrama de simulación
				if (!simulator.getClients().isEmpty()  && totalTime > 0) {
					Diagram di = new Diagram(totalTime, simulator.getClients());
					di.repaint();
				}
				
				return;
			}
			
			JOptionPane.showMessageDialog(null, "Fill out all the fields with numeric values");
		}
	}
	
	/**
	 * Función que coloca e inicializa los elementos en la ventana
	 * 		
	 */
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
		panelResults.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		panelResults.setBackground(Color.WHITE);
		panelResults.setBounds(148, 11, 436, 349);
		panelResults.repaint();
		getContentPane().add(panelResults);
		panelResults.setLayout(null);
		
		this.lblW = new JLabel("W = ");
		lblW.setBounds(10, 11, 197, 14);
		panelResults.add(lblW);
		
		this.lblWq = new JLabel("Wq = ");
		lblWq.setBounds(10, 36, 197, 14);
		panelResults.add(lblWq);
		
		this.lblL = new JLabel("L = ");
		lblL.setBounds(10, 61, 197, 14);
		panelResults.add(lblL);
		
		this.lblLq = new JLabel("Lq = ");
		lblLq.setBounds(10, 86, 197, 14);
		panelResults.add(lblLq);
		
		this.lblO = new JLabel("O = ");
		lblO.setBounds(10, 111, 197, 14);
		panelResults.add(lblO);

		btnSimulate.addActionListener(this);
		this.repaint();
		
	}
	
	/**
	 * Función que identifica si un String es numérico o no
	 * 		
	 * @param str String a probar
	 * 	
	 * @return True si es numérico, false de otro modo
	 */
	private boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		GUI g = new GUI();
	}
}
