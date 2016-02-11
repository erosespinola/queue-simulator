package random;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ExponentialRandomTest {

	private ExponentialRandom random;

	private final double SEED = 3;
	private final double MEAN = 0.01;

	private final ArrayList<Double> KNOWN_CASES = new ArrayList<Double>() {{
		add(0.0023479383416789242);
        add(50.18879580090104);
        add(31.035853982912265);
        add(47.15254198144107);
        add(91.20540001017321);
        add(106.9667924623809);
        add(15.21421882815047);
        add(3.728051318825291);
	}};

	@Before
	public void setUp()  {
		this.random = new ExponentialRandom(SEED, MEAN);
	}

	@Test
	public void testNextDouble()  {
		int casesLength = KNOWN_CASES.size();
		ArrayList<Double> results = new ArrayList<Double>();
		while(casesLength-- > 0) {
			results.add(random.nextDouble());
		}
		assertEquals(true, KNOWN_CASES.equals(results));

	}
}
