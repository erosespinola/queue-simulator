package simulator;

import java.util.ArrayList;

public abstract class QueueSimulator {

	// protected members
	protected int arrivals = 0;
	protected ArrayList<Client> clients;
	protected double idleTime = 0;
	protected double currentTime = 0;

	// abstract methods
	protected abstract void advance();

	/**
	 * Advance simulation
	 * @param double simulation time
	 */
	public void runSimulation(double simulationTime) {
		this.idleTime = 0;
		this.currentTime = 0;
		this.clients.clear();
		while(this.currentTime <= simulationTime) {
			this.advance();
		}
	}

	/**
	 * Calculate queue mean units
	 */
	public double getMeanUnits() {
		double waitingTime = 0;
		for (Client client: clients) {
			waitingTime += client.getSystemTime();
		}
		return waitingTime / this.currentTime;
	}

	/**
	 * Calculate queue mean units in line
	 */
	public double getMeanLineUnits() {
		double waitingTime = 0;
		for (Client client: clients) {
			waitingTime += client.getWaitTime();
		}
		return waitingTime / this.currentTime;
	}

	/**
	 * Calculate queue mean waiting time
	 */
	public double geteMeanWaitingTime() {
		double waitingTime = 0;
		for (Client client: clients) {
			waitingTime += client.getSystemTime();
		}
		return waitingTime / this.arrivals;
	}

	/**
	 * Calculate queue mean waiting time in line
	 * @return double
	 */
	public double geteMeanLineWaitingTime() {
		double waitingTime = 0;
		for(Client client: clients) {
			waitingTime += client.getWaitTime();
		}
		return waitingTime / this.arrivals;
	}

	/**
	 * Get queue idle time
	 */
	public double getIdleTime() {
		return this.idleTime;
	}

	/**
	 * Get queue current time
	 */
	public double getCurrentTime() {
		return this.currentTime;
	}

	/**
	 * Returns the client list involved in the system
	 * @return clients that have passed and are in queue
	 */
	public ArrayList<Client> getClients() {
		return clients;
	}

}
