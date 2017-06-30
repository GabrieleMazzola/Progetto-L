package Database;

import centralsystem.CSystem;
import creator.CSystemFactory;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ticket.Ticket;
import ticketCollector.Fine;

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
        Ticket t = cs.getTicketById("100");
        assertTrue(t != null);
        assertTrue(t.getCode().equals("100"));
        assertTrue(t.getType().equals("Single"));
        assertTrue(t.getOwner().equals("ManuManu"));
        assertTrue(t.getExpiryDate() == null);
    }
    
    @Test
    public void testFetchSingleTicketByUsername() {
        assertTrue(cs.getSalesByUsername("ManuManu").size() == 2);
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
    
    @Test
    public void testFetchFine() {
        assertTrue(cs.getFineById(0) != null);
        assertTrue(cs.getFineById(10) == null);
    }
    
    @Test
    public void testFetchFineByCF() {
        Set<Fine> fines = cs.getFinesOf("cf");
        assertTrue(fines.size() == 3);
    }
}
