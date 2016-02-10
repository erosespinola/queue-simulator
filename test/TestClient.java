
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import simulator.Client;

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
    private Client client;
    
    @Before
    public void setUp() {
        this.client = new Client(0, 50, 100, "label");
    }
    
    @Test
    public void testClient() {
        Assert.assertEquals(client.getArriveTime(), 0, 0.01);
        Assert.assertEquals(client.getNextEntry(), 50, 0.01);
        Assert.assertEquals(client.getLabel(), "label");
        Assert.assertEquals(client.getServiceTime(), 100, 0.01);
    }
}
