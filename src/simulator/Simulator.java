package simulator;

import java.util.*;
import random.Random;

public class Simulator {
	private ArrayList<Client> clients;
	private int nextEntryEvent = 0;
	private int nextExitEvent = -1;
	private Random random;
	private int time = 0;
	private double lambda;
	private double miu;
	private int id = 0;
	
	public Simulator(double lambda, double miu, double seed) {
		this.clients = new ArrayList<Client>();
		this.lambda = lambda;
		this.miu = miu;
		this.random = new Random(seed, 0, 10);
	}

	/**
	 * Advances the simulation
	 */
	public void Advance() {

		incrementWaitingTime();

		// TODO: change printing from standard output to screen
		// perhaps implement an event system
		System.out.print("-- Tiempo: " + time + " --");
		System.out.println(" Siguiente Salida: " + nextExitEvent);

		// if on next entry time, push a new client to the line
		if (time == nextEntryEvent) {

			Client newClient = new Client(time, this.random.next(this.lambda), this.random.next(this.miu), "Client " + (++id));
			newClient.enter();

			// if first in line, serve that client
			if (clients.isEmpty()) {
				newClient.serve();
				nextExitEvent = newClient.exitTime;
			}

			clients.add(newClient);

		}

		// if on next exit time, drop the client from the line
		if (nextExitEvent >= 0 && time == nextExitEvent) {
			Client tmpClient = clients.remove(0);
			tmpClient.exit();

			// serve next client in line
			if (!clients.isEmpty()) {
				Client nextClient = clients.get(0);
				nextClient.serve();
				nextExitEvent = nextClient.exitTime;
			} else {
				nextExitEvent = -1;
			}

		}

		// determine next entry time
		if (!clients.isEmpty()) {
			Client topClient = clients.get(clients.size() - 1);
			nextEntryEvent = topClient.nextEntry;
		}

		time++;

		System.out.println("");

	}

	/**
	 * Increment waiting time for all items in line
	 */
	private void incrementWaitingTime() {
		for (Client client : clients) {
			client.IncrementWaitingTime();
		}
	}

}
