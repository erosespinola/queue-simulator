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
		this.clients = new ArrayList<QueueClient>();
		this.arrivalRandom = new ExponentialRandom(seed, arrivalMean);
		this.servingRandom = new ExponentialRandom(seed, servingMean);
	}

	/**
	 * Advance simulator
	 */
	protected void advance() {

		// set next time
		if(nextExitEvent == -1) {
			this.currentTime = nextEntryEvent;
		} else {
			this.currentTime = nextExitEvent > nextEntryEvent ? nextEntryEvent : nextExitEvent;
		}

		System.out.println("-- Tiempo: " + this.currentTime + " --");

		// if on next entry time, push a new client to the line
		if(this.currentTime == nextEntryEvent) {
			this.handleEntryEvent();
		}
		// if on next exit time, drop the client from the line
		if(nextExitEvent >= 0 && this.currentTime == nextExitEvent) {
			this.handleExitEvent();
		}

		System.out.println("");
	}

	/**
	 * Handle queue entry event
	 */
	private void handleEntryEvent() {
		QueueClient newClient = new QueueClient(this.currentTime, this.servingRandom.nextDouble(), "C" + (++id));
		System.out.println(" " + newClient.getLabel() + " ha entrado en el tiempo: " + this.currentTime);

		// if first in line, serve that client
		if (listHead == clients.size()) {
			newClient.setServeStatus(true);
			System.out.println(" " + newClient.getLabel() + " ha entrado en servicio en el tiempo: " + this.currentTime + " con tiempo de salida: " + newClient.getExitTime());
			this.nextExitEvent = newClient.getExitTime();
		}

		clients.add(newClient);
		nextEntryEvent = this.currentTime + this.arrivalRandom.nextDouble();

		// calculate waiting time of line entering client
		if (!newClient.isBeingServed()) {
			this.calculateWaitTime(clients.size() - 1);
		}

		this.arrivals++;
	}

	/**
	 * Handle queue exit event
	 */
	private void handleExitEvent() {

		QueueClient tmpClient = clients.get(listHead++);
		tmpClient.setServeStatus(false);
		System.out.println(" " + tmpClient.getLabel() + " ha salido en el tiempo: " + tmpClient.getExitTime());

		// serve next client in line
		if (listHead != clients.size()) {
			QueueClient nextClient = clients.get(listHead);
			nextClient.setServeStatus(true);
			this.nextExitEvent = nextClient.getExitTime();
		} else {
			this.idleTime += nextEntryEvent - nextExitEvent;
			this.nextExitEvent = -1;
		}
	}

	/**
	 * Set client waiting time
	 * @param index client list index
	 */
	private void calculateWaitTime(int index) {
		QueueClient client = clients.get(index);
		if (index == 0) {
			client.setWaitingTime(0);
		} else {
			QueueClient tmp = clients.get(index - 1);
			client.setWaitingTime(tmp.getExitTime() - client.getArriveTime());
		}
	}

}
