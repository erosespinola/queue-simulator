package simulatorTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import simulator.MM1QueueSimulator;
import simulator.QueueSimulator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Diego
 */
public class TestSimulator {

    private MM1QueueSimulator simulator;
    
    @Before
    public void setUp() {
        this.simulator = new MM1QueueSimulator(15.2, 0.01, 0.01);
    }
    
    @Test
    public void testSimulator() {
        simulator.runSimulation(1);
        Assert.assertEquals(15507.648350879712, simulator.getMeanUnits(), 0.01);
        Assert.assertEquals(13814.641032021349, simulator.getMeanLineUnits(), 0.01);
        Assert.assertEquals(851.0893424332411, simulator.getMeanWaitingTime(), 0.01);
        Assert.assertEquals(758.1738691687154, simulator.getMeanLineWaitingTime(), 0.01);
        Assert.assertEquals(0.0, simulator.getIdleTime(), 0.01);
        Assert.assertEquals(1, simulator.getCurrentTime(), 0.1);
    }
    
    @Test
    public void testGetClients() {
        simulator.runSimulation(1);
        Assert.assertEquals(19, simulator.getClients().size());
        
    }
}
