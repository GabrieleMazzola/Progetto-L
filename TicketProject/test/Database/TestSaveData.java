package Database;

import centralsystem.CSystem;
import creator.CSystemFactory;
import databaseadapter.people.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ticket.SingleType;
import ticket.Ticket;
import ticketCollector.Fine;
import ticketCollector.TicketCollector;

/**
 *
 * @author Manuele
 */
public class TestSaveData {
    private CSystem cs;
    
    public TestSaveData() {
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

    //I test di salvataggio vengono commentati dal momento che non è possibile inserire 2 volte 
    //lo stesso valore
    @Test
    public void testSaveSingleTicket() {
        Ticket t = new Ticket("100", new SingleType());
        t.setOwner("ManuManu");
        assertTrue(cs.addTicket(t));
        //assertTrue(true);
    }
    
    @Test
    public void testSaveUser() {
        assertTrue(cs.addUser("ManuManu", "Manuele", "Longhi", "cf", "scoiattolo"));
        //assertTrue(true);
    }
    
    @Test
    public void testSaveCollector() {
        assertTrue(cs.addCollector("areds", "Andrea", "Rossi", "RSSNDR95A13G388U", "ioboh"));
        //assertTrue(true);
    }
    
    @Test
    public void testSaveFine() {
        TicketCollector tc = new TicketCollector(0, "localhost");
        assertTrue(tc.loginCollector("areds", "ioboh"));
        assertTrue(tc.createFine("cf", 20));
        assertTrue(tc.createFine("cf", 30));
        assertTrue(tc.createFine("cf", 10));
    }
}
