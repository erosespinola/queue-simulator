package random;

/**
 * Clase de generador de números aleatorios
 */
public class Random {
	final private double a;
	final private double m;
	final private int min;
	final private int max;
	private double r;
	private double seed;
	
	/**
	 * Constructor de la clase
	 * 
	 * @param seed Semilla para los números aleatorios
	 * @param min Número mínimo aceptable
	 * @param max Número máximo aceptable
	 */
	public Random(double seed, int min, int max) {
		this.seed = seed;
		this.min = min;
		this.max = max;
		this.a = 16807;
		this.m = 2147483647;
		this.r = this.seed / this.m;
	}

	/**
	 * Calcula el siguiente número aleatorio en la secuencia usando
	 * el generador Learnmonth-Lewis
	 * 
	 * @param value Valor que es usado para normalizar
	 * 
	 * @return Regresa el siguiente número de la secuencia
	 */
	public int next(double value) {
		this.seed = (this.a * this.seed) % this.m;
		this.r = this.seed / this.m;
		return this.normalize(value);
	}
	

	/**
	 * Normaliza el número dado un parámetro
	 * 
	 * @param value Valor usado para normalizar el número
	 * 
	 * @return Regresa un número normalizado dado el parámetro
	 */
	private int normalize(double value) {
		return (int) (this.max + (this.min - this.max) * (-(Math.log(1 - this.r)) / value));
	}

}
