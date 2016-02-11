package simulator;

import java.util.ArrayList;
import random.*;

/**
 * Simulador de cola M/M/1
 */
public class MM1QueueSimulator extends QueueSimulator {

	// private members
	private final Random arrivalRandom;
	private final Random servingRandom;
	private double nextEntryEvent = 0;
	private double nextExitEvent = -1;
	private final double arrivalMean;
	private double servingMean;
	private int id = 0;
	private int listHead = 0;

	/**
	 * M/M/1 queue simulator
	 */
	public MM1QueueSimulator(double arrivalMean, double servingMean, double seed) {
		this.arrivalMean = arrivalMean;
		this.servingMean = servingMean;
		this.clients = new ArrayList<Client>();
		this.arrivalRandom = new ExponentialRandom(seed, arrivalMean);
		this.servingRandom = new ExponentialRandom(seed, servingMean);
	}

	/**
	 * Avanza la simulación hacia el siguiente evento
	 *
	 * @return boolean bandera que determina si la simulación avanza o no
	 *
	 */
	protected void advance() {

		// set next time
		if (nextExitEvent == -1) {
			this.currentTime = nextEntryEvent;
		} else {
			this.currentTime = nextExitEvent > nextEntryEvent ? nextEntryEvent : nextExitEvent;
		}

		System.out.println("-- Tiempo: " + this.currentTime + " --");

		// if on next entry time, push a new client to the line
		if (this.currentTime == nextEntryEvent) {

			Client newClient = new Client(this.currentTime, this.arrivalRandom.nextDouble(), this.servingRandom.nextDouble(), "C" + (++id));
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
		if (nextExitEvent >= 0 && this.currentTime == nextExitEvent) {
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

}
