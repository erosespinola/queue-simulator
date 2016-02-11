package random;

/**
 * Random number generator
 */
public abstract class Random {

	/**
	 * Return the next pseudorandom, double
	 */
	abstract public double nextDouble();

	/**
	 * Returns the next pseudorandom, uniformly distributed int
	 * @param min inclusive lower bound
	 * @param max inclusive upper bound
	 */
	public int nextInt(int min, int max) {
		double next = this.nextDouble();
		return (int)(min + (max - min) * next);
	}

}
