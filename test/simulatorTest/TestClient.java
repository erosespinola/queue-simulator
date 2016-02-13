package simulatorTest;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import simulator.QueueClient;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Diego
 */
public class TestClient {
    private QueueClient client;
    
    @Before
    public void setUp() {
        this.client = new QueueClient(0, 50, "label");
    }
    
    @Test
    public void testClient() {
        Assert.assertEquals(client.getArriveTime(), 0, 0.01);
        Assert.assertEquals(client.getLabel(), "label");
        Assert.assertEquals(client.getServiceTime(), 50, 0.01);
    }
}
