import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import random.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Diego
 */
public class TestRandom {
    private Random random;
    
    @Before
    public void setUp() {
        this.random = new Random(3, 0, 5);
    }
    
    @Test
    public void testNextNumber() {
        Assert.assertEquals(0.0023479383416789242, random.next(0.01), 0.0001);
        Assert.assertEquals(50.18879580090104, random.next(0.01), 0.0001);
        Assert.assertEquals(31.035853982912265, random.next(0.01), 0.0001);
        Assert.assertEquals(47.15254198144107, random.next(0.01), 0.0001);
        Assert.assertEquals(91.20540001017321, random.next(0.01), 0.0001);
        Assert.assertEquals(106.9667924623809, random.next(0.01), 0.0001);
        Assert.assertEquals(15.21421882815047, random.next(0.01), 0.0001);
        Assert.assertEquals(3.728051318825291, random.next(0.01), 0.0001);
    }
}
