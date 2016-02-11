package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
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
public class Diagram extends JPanel {

    private double time;
    private ArrayList<Client> clients;
    private int lineSizeX;
    private int xStart, yStart, lineDistance;
    JFrame frmDiagramaDeCola;

    /**
     * Función que inicializa los elementos del diagrama
     *
     * @param time Tiempo total de la simulación
     * @param clients ArrayList de clientes para ser dibujados
     */
    public Diagram(double time, ArrayList<Client> clients) {
        super();
        this.time = time;
        this.clients = clients;
        this.xStart = 50;
        this.yStart = 80;
        this.lineSizeX = (int) ((this.getFormatedTime(time)) + this.xStart);
        this.lineDistance = 200;
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension((int) (this.lineSizeX + 100), 400));

        //Confiuración del Frame que contiene el Panel donde se pinta el gráfico
        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 0, 797, 373);

        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension((int) this.lineSizeX, 400));
        contentPane.add(scrollPane);

        frmDiagramaDeCola = new JFrame();
        frmDiagramaDeCola.setTitle("Diagrama de cola");
        frmDiagramaDeCola.setContentPane(contentPane);
        frmDiagramaDeCola.pack();
        frmDiagramaDeCola.setSize(800, 400);
        frmDiagramaDeCola.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmDiagramaDeCola.setVisible(true);
        frmDiagramaDeCola.setResizable(false);

    }

    /**
     * Getter del tiempo
     *
     * @return Regresa el tiempo total de la simulación
     */
    public double getTime() {
        return time;
    }

    /**
     * Setter del tiempo
     *
     * @param time Tiempo de la línea de tiempo
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
     * @param clients Clientes a simular
     */
    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    /**
     * Formatea el tiempo para la escala necesaria de acuerdo al total de tiempo
     * y de clientes
     *
     * @param time Tiempo a formatear
     * @return Tiempo formateado
     */
    public double getFormatedTime(double time) {
        if (time < 1) {
            return time = time * this.getClients().size() * 1000;
        } else if (time > 0 && time < 10) {
            return time = time * this.getClients().size() * 100;
        } else if (time > 10 && time < 100) {
            return time = time * this.getClients().size();
        } else {
            return time = time * this.getClients().size() / 2;
        }
    }

    /**
     * Método que dibuja los valores de entrada a la simulación de un cliente
     *
     * @param g Instancia de Graphics para dibujar
     * @param client Representa el cliente de donde obtener su informaciÃ³n
     */
    public void drawClientEntered(Graphics g, Client client) {
        Graphics2D g2 = (Graphics2D) g;
        double x = (client.getArriveTime()) * (this.lineSizeX / this.time) + this.xStart;
        g2.drawString(client.getLabel(), (int) x, (int) this.yStart - 40);
        g2.draw(new Line2D.Double(x, this.yStart - 30, x, this.yStart));
        int[] xTriangle = {(int) x, (int) x - 5, (int) x + 5};
        int[] yTriangle = {(int) this.yStart, (int) this.yStart - 5, (int) this.yStart - 5};
        g.drawPolygon(xTriangle, yTriangle, 3);
    }

    /**
     * Método que dibuja los valores de entrada a el servicio de un cliente
     *
     * @param g Instancia de Graphics para dibujar
     * @param client Representa el cliente de donde obtener su informaciÃ³n
     */
    public void drawClientEnteredService(Graphics g, Client client) {
        Graphics2D g2 = (Graphics2D) g;
        double x = (client.getWaitTime() + client.getArriveTime()) * (this.lineSizeX / this.time) + this.xStart;
        double xIni = (client.getArriveTime()) * (this.lineSizeX / this.time) + this.xStart;
        g2.draw(new Line2D.Double(xIni, this.yStart, x, this.yStart + this.lineDistance));
        double y = this.yStart + this.lineDistance;
        int[] xTriangle = {(int) x, (int) x - 5, (int) x + 5};
        int[] yTriangle = {(int) y, (int) y - 5, (int) y - 5};
        g.drawPolygon(xTriangle, yTriangle, 3);
    }

    /**
     * Método que dibuja los valores de salida del servicio de un cliente
     *
     * @param g Instancia de Graphics para dibujar
     * @param client Representa el cliente de donde obtener su informaciÃ³n
     */
    public void drawClientServed(Graphics g, Client client) {
        Graphics2D g2 = (Graphics2D) g;
        double x = (client.getExitTime()) * (this.lineSizeX / this.time) + this.xStart;
        g2.drawString(client.getLabel(), (int) x, (int) (this.yStart + this.lineDistance + 20));
        g2.draw(new Line2D.Double(x, this.yStart + this.lineDistance, x, this.yStart + this.lineDistance + 50));
        double y = this.yStart + this.lineDistance + 50;
        int[] xTriangle = {(int) x, (int) x - 5, (int) x + 5};
        int[] yTriangle = {(int) y, (int) y - 5, (int) y - 5};
        g.drawPolygon(xTriangle, yTriangle, 3);
    }

    /**
     * Método que dibuja la escala de tiempo para la simulación
     *
     * @param g Instancia de Graphics para dibujar
     */
    public void drawTimeScale(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (this.time != 0) {

            g2.draw(new Line2D.Double(this.xStart, this.yStart, this.lineSizeX + this.xStart, this.yStart));
            g2.draw(new Line2D.Double(this.xStart, this.yStart + this.lineDistance, this.lineSizeX + this.xStart, this.yStart + this.lineDistance));
            double scale = this.lineSizeX / this.getTime();

            for (int i = 0; i <= this.getTime(); i++) {
                double x = this.xStart + (i * scale);
                g2.draw(new Line2D.Double(x, this.yStart - 10, x, this.yStart + 10));
                if (i % 1 == 0) {
                    g2.drawString("T" + i, (int) x, (int) this.yStart + 20);
                }

            }

        }
    }

    /**
     * Método que llama a pintar elementos en la gráfica
     *
     * @param g Instancia de Graphics para dibujar
     */
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (this.time != 0) {
            this.drawTimeScale(g);

            for (int i = 0; i < this.clients.size() - 1; i++) {
                this.drawClientEntered(g2, this.clients.get(i));
                this.drawClientEnteredService(g2, this.clients.get(i));
                this.drawClientServed(g2, this.clients.get(i));
            }

        }
    }

    /**
     * Método que cierra la ventana
     */
    public void close() {
        this.frmDiagramaDeCola.setVisible(false);
        this.frmDiagramaDeCola.dispose();
    }

}
