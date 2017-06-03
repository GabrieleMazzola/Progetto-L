package Database;

import centralsystem.CSystem;
import creator.CSystemFactory;
import databaseadapter.User;
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

    @Test
    public void testSaveSingleTicket() {
//        Ticket t = new Ticket("100", new SingleType());
//        assertTrue(cs.addTicket(t));
    }
    
    @Test
    public void testSaveUser() {
        //Ordine: username, name, surname, cf, psw
        assertTrue(cs.addUser("ManuManu", "Manuele", "Longhi", "cf", "scoiattolo"));
    }
}
