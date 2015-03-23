package simulator;

/**
 * Clase de Cliente
 */
public class Client {
	private String label; 		// Identificador del cliente
	private int arriveTime;		// Tiempo de arribo al sistema
	private int serviceTime;	// Tiempo de servicio
	private int waitTime = 0;	// Tiempo de espera en cola
	private int exitTime;		// Tiempo de salida del sistema
	private int nextEntry;		// Siguiente entrada al sistema
	private boolean isBeingServed = false;		// Cliente en servicio
	
	/**
	 * Constructor de la clase
	 * 
	 * @param arriveTime Tiempo en el que el cliente arriba al sistema
	 * @param nextEntry Siguiente entrada al sistema
	 * @param serviceTime Tiempo que lleva el servicio del cliente
	 * @param label Etiqueta identificadora del cliente
	 * 			
	 */
	public Client(int arriveTime, int nextEntry, int serviceTime, String label) {
	    this.arriveTime = arriveTime;
	    this.nextEntry = arriveTime + nextEntry;
	    this.serviceTime = serviceTime;
	    this.exitTime = arriveTime + serviceTime;
	    this.label = label;
	}
	
	/**
	 * Getter de arriveTime
	 * 
	 * @return Regresa el tiempo de arribo al sistema del cliente
	 * 			
	 */
	public int getArriveTime() {
		return arriveTime;
	}
	
	/**
	 * Getter de serviceTime
	 * 
	 * @return Regresa el tiempo de servicio en el servidor del cliente
	 * 			
	 */
	public int getServiceTime() {
		return serviceTime;
	}
	
	/**
	 * Getter de waitTime
	 * 
	 * @return Regresa el tiempo de espera en cola del cliente
	 * 			
	 */
	public int getWaitTime() {
		return waitTime;
	}
	
	/**
	 * Getter de label
	 * 
	 * @return Regresa el identificador del cliente
	 * 			
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Getter de exitTime
	 * 
	 * @return Regresa el tiempo de salida del sistema del cliente
	 * 			
	 */
	public int getExitTime() {
		return exitTime;
	}
	
	/**
	 * Getter de nextEntry
	 * 
	 * @return Regresa el tiempo de la siguiente entrada al sistema
	 * 			
	 */
	public int getNextEntry() {
		return nextEntry;
	}
	
	/**
	 * Getter de isBeingServed
	 * 
	 * @return Regresa si el cliente esta en servicio o no
	 * 			
	 */
	public boolean isBeingServed() {
		return isBeingServed;
	}
	
	/**
	 * Getter de systemTime
	 * 
	 * @return Regresa el tiempo que total del cliente en el sistema
	 * 			
	 */
	public int getSystemTime(){
		return this.waitTime + this.serviceTime;
	}
	
	/**
	 * Incrementa el tiempo que el cliente ha esperado si no esta siendo servido,
	 * además incrementa su tiempo de salida
	 * 		
	 */
	public void IncrementWaitingTime() { 
		if(!isBeingServed) {
			waitTime++;
			exitTime++;
		}
	}
	
	/**
	 * Imprime el tiempo de entrada al sistema del cliente
	 * 			
	 */
	public void enter() {
		System.out.println("    " + label + " ha entrado en el tiempo: " + arriveTime);
	}
	
	/**
	 * Imprime el tiempo de salida del sistema del cliente
	 * 			
	 */
	public void exit() {
		System.out.println("    " + label + " ha salido en el tiempo: " + exitTime);
	}

	/**
	 * Imprime el tiempo de entrada al servidor del cliente
	 * 			
	 */
	public void serve() {
		isBeingServed = true;
		System.out.println("    " + label + " ha entrado en servicio en el tiempo: " + (waitTime + arriveTime) + " con tiempo de salida: " + exitTime);
	}
  
}
