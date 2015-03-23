package random;

/**
 * Clase de generador de n�meros aleatorios
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
	 * @param seed Semilla para los n�meros aleatorios
	 * @param min N�mero m�nimo aceptable
	 * @param max N�mero m�ximo aceptable
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
	 * Calcula el siguiente n�mero aleatorio en la secuencia usando
	 * el generador Learnmonth-Lewis
	 * 
	 * @param value Valor que es usado para normalizar
	 * 
	 * @return Regresa el siguiente n�mero de la secuencia
	 */
	public int next(double value) {
		this.seed = (this.a * this.seed) % this.m;
		this.r = this.seed / this.m;
		return this.normalize(value);
	}
	

	/**
	 * Normaliza el n�mero dado un par�metro
	 * 
	 * @param value Valor usado para normalizar el n�mero
	 * 
	 * @return Regresa un n�mero normalizado dado el par�metro
	 */
	private int normalize(double value) {
		return (int) (this.max + (this.min - this.max) * (-(Math.log(1 - this.r)) / value));
	}

}
