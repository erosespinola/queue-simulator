package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import simulator.Client;

/**
 * Clase que implementa los métodos necesarios para crear un diagrama de la simulación
 *
 */
@SuppressWarnings("serial")
public class Diagram extends JFrame {
	
	private int time;
	private ArrayList<Client> clients;
	private int lineSizeX;
	private int xStart, yStart, lineDistance;
	
	/**
	 * @param time Tiempo total de la simulación
	 * @param clients ArrayList de clientes para ser dibujados
	 */
	public Diagram(int time, ArrayList<Client> clients) {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setName("Simulador de Cola M/M/1 Diagrama");
		this.getContentPane().setBackground(Color.WHITE);
		setSize(new Dimension(1200, 500));
		setVisible(true);
		this.setResizable(true);
		this.time = time;
		this.lineSizeX = this.getWidth() - 200;
		this.xStart = 100;
		this.yStart = 100;
		this.clients = clients;
		this.lineDistance = 200;
	}
	
	/**
	 * @return Regresa el tiempo total de la simulación
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @param time
	 */
	public void setTime(int time) {
		this.time = time;
	}
	
	/**
	 * @return Regresa un ArrayList con los clientes a dibujar
	 */
	public ArrayList<Client> getClients() {
		return clients;
	}

	/**
	 * @param clients
	 */
	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}


	/**
	 * 
	 * Método que dibuja los valores de entrada a la simulación de un cliente
	 * 
	 * @param g Instancia de Graphics para dibujar
	 * @param client Representa el cliente de donde obtener su información
	 */
	public void drawClientEntered(Graphics g,Client client){
		int x = (client.getArriveTime()) * (this.lineSizeX / this.time) + this.xStart;
		g.drawString(client.getLabel(), x, this.yStart - 40 );
		g.drawLine(x,this.yStart - 30, x, this.yStart);
		int[] xTriangle = {x, x - 5, x + 5};
		int[] yTriangle = {this.yStart, this.yStart - 5, this.yStart - 5};
		g.drawPolygon(xTriangle, yTriangle, 3);
	}
	
	/**
	 * 
	 * Método que dibuja los valores de entrada a el servicio de un cliente
	 * 
	 * @param g Instancia de Graphics para dibujar
	 * @param client Representa el cliente de donde obtener su información
	 */
	public void drawClientEnteredService(Graphics g,Client client){
		int x = (client.getWaitTime() + client.getArriveTime()) * (this.lineSizeX / this.time)  + this.xStart;
		int xIni = (client.getArriveTime()) * (this.lineSizeX / this.time)  + this.xStart;
		g.drawLine(xIni,this.yStart, x, this.yStart + this.lineDistance);
		int y = this.yStart + this.lineDistance;
		int[] xTriangle = {x, x - 5, x + 5};
		int[] yTriangle = {y, y - 5, y - 5};
		g.drawPolygon(xTriangle, yTriangle, 3);
	}
	
	/**
	 * 
	 * Método que dibuja los valores de salida del servicio de un cliente
	 * 
	 * @param g Instancia de Graphics para dibujar
	 * @param client Representa el cliente de donde obtener su información
	 */
	public void drawClientServed(Graphics g,Client client){
		int x = (client.getExitTime()) * (this.lineSizeX / this.time) + this.xStart;
		g.drawString(client.getLabel(), x, this.yStart + this.lineDistance + 20 );
		g.drawLine(x,this.yStart + this.lineDistance, x, this.yStart + this.lineDistance + 50);
		int y = this.yStart + this.lineDistance + 50;
		int[] xTriangle = {x, x - 5, x + 5};
		int[] yTriangle = {y, y - 5, y - 5};
		g.drawPolygon(xTriangle, yTriangle, 3);
	}
	
	/**
	 * 
	 * Método que dibuja la escala de tiempo para la simulación
	 * 
	 * @param g Instancia de Graphics para dibujar
	 */
	public void drawTimeScale(Graphics g){
		if (this.time != 0) {
			 g.drawLine(this.xStart, this.yStart, this.lineSizeX + this.xStart, this.yStart);
			 g.drawLine(this.xStart, this.yStart + this.lineDistance, this.lineSizeX + this.xStart,  this.yStart + this.lineDistance);
			 int scale = this.lineSizeX / this.getTime();
			 for (int i = 0; i <= this.getTime(); i++) {
				 int x =this.xStart +(i * scale);
				g.drawLine(x, this.yStart - 10, x,  this.yStart + 10);
				if (i % 2 == 0) {
					g.drawString(""+i, x, this.yStart + 20);
				}
				
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Window#paint(java.awt.Graphics)
	 */
	public void paint (Graphics g) {
		if (this.time != 0) {
			this.drawTimeScale(g);
			
			for (Client client : this.clients) {
				this.drawClientEntered(g, client);
				this.drawClientEnteredService(g, client);
				this.drawClientServed(g, client);
			}
		}
	}
}
