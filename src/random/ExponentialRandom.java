package random;

/**
 * Exponential distribution random generator
 */
public class ExponentialRandom extends Random {

	private final double mean;
	private final Random generator;

	/**
	 * LinearRandom
	 * @param seed the initial seed
	 */
	public ExponentialRandom(double seed, double mean) {
		this.generator = new LinearRandom(seed);
		this.mean = mean;
	}

	/**
	 * Return the next pseudorandom, double from an exponential distribution
	 * @param mean the mean of the distrubtion to use
	 */
        @Override
	public double nextDouble() {
		return -(Math.log(1 - this.generator.nextDouble()) / this.mean);
	}

}
