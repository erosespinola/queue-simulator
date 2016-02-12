package simulator;

/**
 * Queue Client
 */
public class QueueClient {

	// private members
	private String label;
	private double arriveTime;
	private double serviceTime;
	private double waitTime = 0;
	private double exitTime;
	private boolean isBeingServed = false;

	/**
	 * QueueClient
	 * @param arriveTime client arrival time at queue
	 * @param serviceTime client service time
	 * @param label client identifier
	 */
	public QueueClient(double arriveTime, double serviceTime, String label) {
		this.arriveTime = arriveTime;
		this.serviceTime = serviceTime;
		this.exitTime = arriveTime + serviceTime;
		this.label = label;
	}

	/**
	 * Returns client arrival time
	 */
	public double getArriveTime() {
		return arriveTime;
	}

	/**
	 * Returns client service time
	 */
	public double getServiceTime() {
		return serviceTime;
	}

	/**
	 * Returns client waiting time
	 */
	public double getWaitTime() {
		return waitTime;
	}

	/**
	 * Returns client identifier
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Returns client exit time
	 */
	public double getExitTime() {
		return exitTime;
	}

	/**
	 * Return client serving status
	 */
	public boolean isBeingServed() {
		return isBeingServed;
	}

	/**
	 * Returns the client total time in the system
	 */
	public double getSystemTime(){
		return this.waitTime + this.serviceTime;
	}

	/**
   	 * Returns the client waiting time
     */
	public void setWaitingTime(double time) {
		this.waitTime = time;
		this.exitTime = this.arriveTime + this.waitTime + this.serviceTime;
	}

	/**
	 * Set client serving status
	 */
	public void setServeStatus(boolean status) {
		this.isBeingServed = status;
	}

}
