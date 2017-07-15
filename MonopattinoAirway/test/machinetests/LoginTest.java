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


public class LoginTest {
    
    public LoginTest() {
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
       
        CSystemFactory.getInstance().buildLightCSystem(SimMapperFactory.class.getName());
        TicketMachine tMachine = new TicketMachine(1, 5000, "localhost");
        
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
