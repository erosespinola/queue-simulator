
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import simulator.Simulator;

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

    private Simulator simulator;
    
    @Before
    public void setUp() {
        this.simulator = new Simulator(15.2, 0.01, 0.01, 100);
    }
    
    @Test
    public void testSimulator() {
        simulator.advance();
        simulator.advance();
        Assert.assertEquals(2.3086773106858353E10, simulator.L(), 0.01);
        Assert.assertEquals(2.556345447636032E7, simulator.Lq(), 0.01);
        Assert.assertEquals(0.0, simulator.O(), 0.01);
        Assert.assertEquals(0.06581218494038268, simulator.Wq(), 0.01);
        Assert.assertEquals(59.43605872165143, simulator.W(), 0.01);
        Assert.assertEquals(5.148927348707287E-9, simulator.getTime(), 0.01);
    }
    
    @Test
    public void testGetClients() {
        simulator.advance();
        simulator.advance();
        simulator.advance();
        simulator.advance();
        simulator.advance();
        simulator.advance();
        Assert.assertEquals(5, simulator.getClients().size());
        
    }
    
    
    
    
    
}
