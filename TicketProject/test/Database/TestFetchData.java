package Database;

import centralsystem.CSystem;
import creator.CSystemFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ticket.Ticket;

/**
 *
 * @author Manuele
 */
public class TestFetchData {
    private CSystem cs;
    
    public TestFetchData() {
        cs = new CSystemFactory().buildNewCentralSystem();
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
    public void testFetchSingleTicket() {
        assertTrue(cs.getTicketById("0") != null);
        assertTrue(cs.getTicketById("1") == null);
    }
    
    @Test
    public void testFetchUser() {
        assertTrue(cs.checkUser("ManuManu"));
        assertFalse(cs.checkUser("Otto"));
    }
    
    @Test
    public void testFetchCollector() {
        assertTrue(cs.collectorLogin("areds", "ioboh"));
        assertFalse(cs.collectorLogin("un", "due"));
    }
}
