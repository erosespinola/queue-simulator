package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import simulator.Client;

/**
 * Clase de Diagrama
 *
 */
@SuppressWarnings("serial")
public class Diagram extends JFrame {
	private int time;
	private ArrayList<Client> clients;
	private int lineSizeX;
	private int xStart, yStart, lineDistance;

	/**
	 * Función que inicializa los elementos del diagrama
	 * 
	 * @param time Tiempo total de la simulación
	 * @param clients ArrayList de clientes para ser dibujados
	 */
	public Diagram(int time, ArrayList<Client> clients) {
		super();
		this.time = time;
		this.xStart = 100;
		this.yStart = 100;
		this.lineSizeX = (time * 20) + this.xStart;
		this.clients = clients;
		this.lineDistance = 200;
		this.setBackground(Color.WHITE);
		
		
        JFrame frame = new JFrame();
        this.setPreferredSize(new Dimension(this.lineSizeX * 2, 500));
        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0,0,this.lineSizeX,500);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(this.lineSizeX, 500));
        contentPane.add(scrollPane);
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
		
		
	}
	
	/**
	 * Getter del tiempo
	 * 
	 * @return Regresa el tiempo total de la simulación
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Setter del tiempo
	 * 
	 * @param time
	 */
	public void setTime(int time) {
		this.time = time;
	}
	
	/**
	 * Getter de los clientes
	 * 
	 * @return Regresa un ArrayList con los clientes a dibujar
	 */
	public ArrayList<Client> getClients() {
		return clients;
	}

	/**
	 * Setter de los clientes
	 * 
	 * @param clients
	 */
	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}


	/**
	 * Método que dibuja los valores de entrada a la simulación de un cliente
	 * 
	 * @param g Instancia de Graphics para dibujar
	 * @param client Representa el cliente de donde obtener su informaciÃ³n
	 */
	public void drawClientEntered(Graphics g,Client client){
		// int x = (client.getArriveTime()) * (this.lineSizeX / this.time) + this.xStart;
		// g.drawString(client.getLabel(), x, this.yStart - 40 );
		// g.drawLine(x,this.yStart - 30, x, this.yStart);
		// int[] xTriangle = {x, x - 5, x + 5};
		// int[] yTriangle = {this.yStart, this.yStart - 5, this.yStart - 5};
		// g.drawPolygon(xTriangle, yTriangle, 3);
	}
	
	/**
	 * Método que dibuja los valores de entrada a el servicio de un cliente
	 * 
	 * @param g Instancia de Graphics para dibujar
	 * @param client Representa el cliente de donde obtener su informaciÃ³n
	 */
	public void drawClientEnteredService(Graphics g,Client client){
		// int x = (client.getWaitTime() + client.getArriveTime()) * (this.lineSizeX / this.time)  + this.xStart;
		// int xIni = (client.getArriveTime()) * (this.lineSizeX / this.time)  + this.xStart;
		// g.drawLine(xIni,this.yStart, x, this.yStart + this.lineDistance);
		// int y = this.yStart + this.lineDistance;
		// int[] xTriangle = {x, x - 5, x + 5};
		// int[] yTriangle = {y, y - 5, y - 5};
		// g.drawPolygon(xTriangle, yTriangle, 3);
	}
	
	/**
	 * Método que dibuja los valores de salida del servicio de un cliente
	 * 
	 * @param g Instancia de Graphics para dibujar
	 * @param client Representa el cliente de donde obtener su informaciÃ³n
	 */
	public void drawClientServed(Graphics g,Client client){
		// int x = (client.getExitTime()) * (this.lineSizeX / this.time) + this.xStart;
		// g.drawString(client.getLabel(), x, this.yStart + this.lineDistance + 20 );
		// g.drawLine(x,this.yStart + this.lineDistance, x, this.yStart + this.lineDistance + 50);
		// int y = this.yStart + this.lineDistance + 50;
		// int[] xTriangle = {x, x - 5, x + 5};
		// int[] yTriangle = {y, y - 5, y - 5};
		// g.drawPolygon(xTriangle, yTriangle, 3);
	}
	
	/**
	 * Método que dibuja la escala de tiempo para la simulación
	 * 
	 * @param g Instancia de Graphics para dibujar
	 */
	public void drawTimeScale(Graphics g){
		// if (this.time != 0) {
		// 	 g.drawLine(this.xStart, this.yStart, this.lineSizeX + this.xStart, this.yStart);
		// 	 g.drawLine(this.xStart, this.yStart + this.lineDistance, this.lineSizeX + this.xStart,  this.yStart + this.lineDistance);
		// 	 int scale = this.lineSizeX / this.getTime();
		// 	 for (int i = 0; i <= this.getTime(); i++) {
		// 		 int x =this.xStart +(i * scale);
		// 		g.drawLine(x, this.yStart - 10, x,  this.yStart + 10);
		// 		if (i % 2 == 0) {
		// 			g.drawString(""+i, x, this.yStart + 20);
		// 		}
				
		// 	}
		// }
	}
	
	/**
	 * Método que llama a pintar elementos en la gráfica
	 * 
	 * @param g Instancia de Graphics para dibujar
	 */
	public void paint (Graphics g) {
		if (this.time != 0) {
			this.drawTimeScale(g);
			
			for (int i = 0; i < this.clients.size() - 1; i++) {
				this.drawClientEntered(g, this.clients.get(i));
				this.drawClientEnteredService(g, this.clients.get(i));
				this.drawClientServed(g, this.clients.get(i));
			}

		}
	}
}
