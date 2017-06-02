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
public class TestLogin {
    
    public TestLogin() {
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
    public void testLoginCustomer() {
        CSystem cSystem = new CSystemFactory().buildNewCentralSystem();
        
        TicketMachineFactory tmFactory = new TicketMachineFactory();
        TicketMachine tMachine = tmFactory.createTicketMachine(5000, "localhost");
        
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException exc) {
            System.out.println(exc);
        }
        assertTrue(tMachine.login("ADMIN", "ADMIN"));
        assertFalse(tMachine.login("admin", "admin"));
    }
}
