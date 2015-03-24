package simulator;

import java.util.*;
import random.Random;

/**
 * Simulador de cola M/M/1
 */
public class Simulator {
	private ArrayList < Client > clients;
	private double idleTime = 0;
	private double nextEntryEvent = 0;
	private double nextExitEvent = -1;
	private int arrivals = 0;
	private Random random;
	private double time = 0;
	private double lambda;
	private double miu;
	private int id = 0;
	private int listHead = 0;
	private double simulationTime;

	public Simulator(double lambda, double miu, double seed, double simulationTime) {
		this.clients = new ArrayList < Client > ();
		this.lambda = lambda;
		this.miu = miu;
		this.random = new Random(seed, 0, 5);
		this.simulationTime = simulationTime;
	}

	/**
	 * Avanza la simulación hacia el siguiente evento
	 *
	 * @return boolean bandera que determina si la simulación avanza o no
	 *
	 */
	public boolean Advance() {

		// set next time
		if (nextExitEvent == -1) {
			time = nextEntryEvent;
		} else {
			time = nextExitEvent > nextEntryEvent ? nextEntryEvent : nextExitEvent;
		}

		if (time > simulationTime) {
			return false;
		}

		System.out.println("-- Tiempo: " + time + " --");

		// if on next entry time, push a new client to the line
		if (time == nextEntryEvent) {

			Client newClient = new Client(time, this.random.next(this.lambda), this.random.next(this.miu), "C" + (++id));
			newClient.enter();

			// if first in line, serve that client
			if (listHead == clients.size()) {
				newClient.serve();
				nextExitEvent = newClient.getExitTime();
			}

			clients.add(newClient);
			nextEntryEvent = newClient.getNextEntry();

			// calculate waiting time of line entering client
			if (!newClient.isBeingServed()) {
				this.calculateWaitTime(clients.size() - 1);
			}

			this.arrivals++;
		}

		// if on next exit time, drop the client from the line
		if (nextExitEvent >= 0 && time == nextExitEvent) {
			Client tmpClient = clients.get(listHead++);
			tmpClient.exit();

			// serve next client in line
			if (listHead != clients.size()) {
				Client nextClient = clients.get(listHead);
				nextClient.serve();
				nextExitEvent = nextClient.getExitTime();
			} else {
				idleTime += nextEntryEvent - nextExitEvent;
				nextExitEvent = -1;
			}
		}

		System.out.println("");
		return true;
	}

	/**
	 * Determina el tiempo de espera para un cliente
	 *
	 * @param index lugar de la lista del cliente
	 *
	 */
	private void calculateWaitTime(int index) {
		Client client = clients.get(index);
		if (index == 0) {
			client.setWaitingTime(0);
		} else {
			Client tmp = clients.get(index - 1);
			client.setWaitingTime(tmp.getExitTime() - client.getArriveTime());
		}
	}

	/**
	 *
	 * Incrementa el tiempo de espera para todos los clientes en la cola
	 *
	 */
	private void incrementWaitingTime() {
		for (Client client: clients) {
			// client.IncrementWaitingTime();
		}
	}

	/**
	 * Calcula el numero promeido de unidades en el sistema
	 *
	 * @return double promedio de unidades
	 *
	 */
	public double L() {
		double waitingTime = 0;
		for (Client client: clients) {
			waitingTime += client.getSystemTime();
		}
		return waitingTime / this.time;
	}

	/**
	 * Calcula el numero promedio de unidades en la cola
	 *
	 * @return double promedio de unidades
	 *
	 */
	public double Lq() {
		double waitingTime = 0;
		for (Client client: clients) {
			waitingTime += client.getWaitTime();
		}
		return waitingTime / this.time;
	}

	/**
	 * Calcula el tiempo promedio de espera en el sistema
	 *
	 * @return double tiempo promedio de espera
	 *
	 */
	public double W() {
		double waitingTime = 0;
		for (Client client: clients) {
			waitingTime += client.getSystemTime();
		}
		return waitingTime / this.arrivals;
	}

	/**
	 * Calcula el tiempo promedio de espera en la cola al tiempo t
	 *
	 * @return double tiempo promedio de espera
	 *
	 */
	public double Wq() {
		double waitingTime = 0;
		for (Client client: clients) {
			waitingTime += client.getWaitTime();
		}
		return waitingTime / this.arrivals;
	}

	/**
	 * Calcula el tiempo ocioso del sistema
	 *
	 * @return double longitud del tiempo ocioso
	 *
	 */
	public double O() {
		return this.idleTime;
	}

	/**
	 * Regresa la lista de clientes que han pasado por el sistema
	 * incluyendo los que han salido y los que estan actualmente en el
	 *
	 * @return ArrayList<Client> clientes servidos
	 *
	 */
	public ArrayList < Client > getClients() {
		return clients;
	}

	/**
	 * Regresa el tiempo actual de la simulación
	 *
	 * @return double tiempo
	 *
	 */
	public double getTime() {
		return this.time;
	}

}
