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
 * @author dcamargo
 */
public class RandomTest {
    private Random random;
    
    @Before
    public void setUp() {
        this.random = new Random(3, 0, 5);
    }
    
    @Test
    public void test() {
        Assert.assertEquals(0.0023479383416789242, random.next(0.01), 0.0001);
        Assert.assertEquals(50.18879580090104, random.next(0.01), 0.0001);
    }
    
}
