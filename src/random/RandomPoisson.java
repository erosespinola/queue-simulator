package random;

import java.util.Vector;

public class RandomPoisson {
	private Vector<Integer> num;
	private double[] rand;
	private double exp;
		
	public RandomPoisson(double lambda, int size) {
		super();
		this.random(35, 16807, 0, 2147483647, size);
		this.num = new Vector<Integer>();
		this.exp = Math.pow(Math.E, -lambda);
		this.poisson();		
	}

	private void random(double seed, double a, double c, double m, int size){
		if (size <= 0) return;
		
		this.rand = new double[size];
		double x = (a * seed + c) % m;
		
		for(int i = 0; i < size; i ++) {
			this.rand[i] = x / m;
			x = (a * x + c) % m;
		}
	}
	
	public void poisson() {
		if (this.rand.length <= 0) return;
		
		int variable = 0;
		double p;
		double mult = 1;

		for (int i = 0; i < this.rand.length; i++) {
			p = this.rand[i];
			
			if (p * mult < this.exp) {
				this.num.add(variable);
				mult = 1;
				variable = 0;
			} else {
				mult = this.rand[i];
				variable++;
			}
		}	
	}
	
	public Vector<Integer> getNumbers() {
		return num;
	}

}
