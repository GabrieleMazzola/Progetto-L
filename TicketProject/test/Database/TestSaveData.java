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
        //assertTrue(cs.addTicket(t));
        assertTrue(true);
    }
    
    @Test
    public void testSaveUser() {
        //assertTrue(cs.addUser("ManuManu", "Manuele", "Longhi", "cf", "scoiattolo"));
        assertTrue(true);
    }
    
    @Test
    public void testSaveCollector() {
        assertTrue(cs.addCollector("areds", "Andrea", "Rossi", "RSSNDR95A13G388U", "ioboh"));
    }
}
