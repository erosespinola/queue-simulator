package random;

/**
 * Random Number Generator Class
 */
public class Random {
	final private double a;
	final private double m;
	final private int min;
	final private int max;
	private double r;
	private double seed;

	public Random(double seed, int min, int max) {
		this.seed = seed;
		this.min = min;
		this.max = max;
		this.a = 16807;
		this.m = 2147483647;
		this.r = this.seed / this.m;
	}

	/**
	 * Calculates the next random normalized number in the sequence, using Learnmonth-Lewis
	 * Random Number Generator
	 * 
	 * @param value
	 * 			value that is being used to be normalized
	 * @return returns the next random number in the sequence
	 */
	public int next(double value) {
		this.seed = (this.a * this.seed) % this.m;
		this.r = this.seed / this.m;
		return this.normalize(value);
	}
	

	/**
	 * Normalizes the current random number with a given value
	 * 
	 * @param value
	 * 			the value that is used to normalized the value
	 * @return normalized number with the value determined by the user
	 */
	private int normalize(double value) {
		return (int) (this.max + (this.min - this.max) * (-(Math.log(1 - this.r)) / value));
	}

}
