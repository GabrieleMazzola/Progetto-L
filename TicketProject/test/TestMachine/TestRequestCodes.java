package TestMachine;

import centralsystem.CSystem;
import creator.CSystemFactory;
import creator.TicketMachineFactory;
import machines.TicketMachine;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Manuele
 */
public class TestRequestCodes {
    
    public TestRequestCodes() {
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
        CSystem cSystem = new CSystemFactory().buildNewCentralSystem();
        
        TicketMachineFactory tmFactory = new TicketMachineFactory();
        TicketMachine tMachine = tmFactory.createTicketMachine(5000, "localhost");
        try {
            Thread.sleep(4000);
        }
        catch(InterruptedException exc) {
            System.out.println(exc);
        }
        assertTrue(tMachine.getSerialsAmount() > 10);
    }
}
