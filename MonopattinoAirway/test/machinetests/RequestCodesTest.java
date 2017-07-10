package machinetests;

import centralsystem.CSystem;
import centralsystem.factory.CSystemFactory;
import communication.SocketHandler;
import database.factories.SimMapperFactory;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ticketmachine.TicketMachine;


public class RequestCodesTest {
    
    public RequestCodesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testRquestCodes() {

        CSystemFactory.getInstance().buildLightCSystem(SimMapperFactory.class.getName());
        TicketMachine tMachine = new TicketMachine(1, 5000, "localhost");
        
        try {
            Thread.sleep(4000);
        }
        catch(InterruptedException exc) {
            System.out.println(exc);
        }
        assertTrue(tMachine.getSerialsAmount() > 10);
    }
}
