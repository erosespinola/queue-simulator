package simulator;

public class Client {
  private int arriveTime;
  private int serviceTime;
  private int waitTime = 0;
  private String label;

  private int exitTime;
  private int nextEntry;
  private boolean isBeingServed = false;
  
  public int getArriveTime() {
	return arriveTime;
}

public int getServiceTime() {
	return serviceTime;
}

public int getWaitTime() {
	return waitTime;
}

public String getLabel() {
	return label;
}

public int getExitTime() {
	return exitTime;
}

public int getNextEntry() {
	return nextEntry;
}

public boolean isBeingServed() {
	return isBeingServed;
}

public Client(int arriveTime, int nextEntry, int serviceTime, String label) {
    this.arriveTime = arriveTime;
    this.nextEntry = arriveTime + nextEntry;
    this.serviceTime = serviceTime;
    this.exitTime = arriveTime + serviceTime;
    this.label = label;
  }
  
  public int getSystemTime(){
	  return this.waitTime + this.serviceTime;
  }

  public void IncrementWaitingTime() { 
    if(!isBeingServed) {
      waitTime++;
      exitTime++;
    }
  }

  public void enter() {
    System.out.println("    " + label + " ha entrado en el tiempo: " + arriveTime);
  }

  public void exit() {
    System.out.println("    " + label + " ha salido en el tiempo: " + exitTime);
  }

  public void serve() {
    isBeingServed = true;
    System.out.println("    " + label + " ha entrado en servicio en el tiempo: " + (waitTime + arriveTime) + " con tiempo de salida: " + exitTime);
  }
  
}
