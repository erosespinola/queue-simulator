package random;

import java.util.Vector;

public class Random {
  private double exp;
    
  public Random(double seed, double lambda, double miu) {
    this.exp = Math.pow(Math.E, -lambda);
  }

  /**
   * Generate next random number
   */
  public int next() {
    return 0;
  }

}
