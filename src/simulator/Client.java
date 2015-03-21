package simulator;

public class Client {
  private int arriveTime;
  private int serviceTime;
  private int waitTime = 0;
  private String label;

  public int exitTime;
  public int nextEntry;
  public boolean isBeingServed = false;
  
  public Client(int arriveTime, int nextEntry, int serviceTime, String label) {
    this.arriveTime = arriveTime;
    this.nextEntry = arriveTime + nextEntry;
    this.serviceTime = serviceTime;
    this.exitTime = arriveTime + serviceTime;
    this.label = label;
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
