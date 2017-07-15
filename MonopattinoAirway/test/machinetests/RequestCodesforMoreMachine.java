
package machinetests;

import centralsystem.factory.CSystemFactory;
import database.factories.SimMapperFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ticketmachine.TicketMachine;


public class RequestCodesforMoreMachine {
    
    public RequestCodesforMoreMachine() {
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
        TicketMachine tMachine2 = new TicketMachine(2, 5000,"localhost");
        
        try {
            Thread.sleep(4000);
        }
        catch(InterruptedException exc) {
            System.out.println(exc);
        }
        assertTrue(tMachine.getSerialsAmount() == 30);
        assertTrue(tMachine2.getSerialsAmount() == 30);
                
    }
}
