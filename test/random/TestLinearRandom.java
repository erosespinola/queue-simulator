package random;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import random.LinearRandom;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Diego
 */
public class TestLinearRandom {

    private LinearRandom random;

	private final double SEED = 3;

	private final ArrayList<Double> KNOWN_CASES = new ArrayList<Double>() {{
		add(0.000023479107778276833);
		add(0.39461336442949874);
		add(0.2668159665850997);
		add(0.37595039577034783);
	}};

    @Before
    public void setUp() {
        this.random = new LinearRandom(SEED);
    }
    
    @Test
    public void testNextDouble() {
		int casesLength = KNOWN_CASES.size();
		ArrayList<Double> results = new ArrayList<Double>();
		while(casesLength-- > 0) {
			results.add(random.nextDouble());
		}

		assertEquals(true, KNOWN_CASES.equals(results));
    }

}
