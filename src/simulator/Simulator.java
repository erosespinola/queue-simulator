package simulator;

import java.util.TimerTask;

public class Simulator extends TimerTask {
	private int n;
	
	public Simulator(int n) {
		this.n = n;
	}
	
	@Override
	public void run() {
		System.out.println(n);
	}
	
}
