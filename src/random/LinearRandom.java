package random;

/**
 * Learnmonth-Lewis linear congruential generator
 */
public class LinearRandom extends Random {

	// class constants
	private static final double MULTIPLIER = 16807.0;
	private static final double MODULO = 2147483647.0;

	// private members
	private double seed;

	/**
	 * LinearRandom
	 * @param seed the initial seed
	 */
	public LinearRandom(double seed) {
		this.seed = seed;
	}

	/**
	 * Return the next pseudorandom, int
	 */
        @Override
	public double nextDouble() {
		this.seed = (MULTIPLIER * this.seed) % MODULO;
		return this.seed / MODULO;
	}

}
