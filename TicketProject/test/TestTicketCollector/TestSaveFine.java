package TestTicketCollector;

import centralsystem.CSystem;
import creator.CSystemFactory;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ticketCollector.TicketCollector;

/**
 *
 * @author Manuele
 */
public class TestSaveFine {
    
    public TestSaveFine() {
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
    public void testSaveFineWithConnection() {
        CSystem cSystem = new CSystemFactory().buildNewCentralSystem();
        
        TicketCollector tCollector = new TicketCollector(0, "localhost");
        assertTrue(tCollector.loginCollector("areds", "ioboh"));
        assertTrue(tCollector.getOfflineFinesNumber() == 0);
        
        tCollector.createFine("cf", 5);
        assertTrue(tCollector.getOfflineFinesNumber() == 0);
    }
    
    @Test
    public void testSaveFineWithoutConnection() {
        TicketCollector tCollector = new TicketCollector(0, "localhost");
        assertTrue(tCollector.loginCollector("areds", "ioboh"));
        assertTrue(tCollector.getOfflineFinesNumber() == 0);
        
        tCollector.createFine("cf", 5);
        assertTrue(tCollector.getOfflineFinesNumber() == 1);
    }
    
    @Test
    public void testSaveFineWithLaterConnection() {
        TicketCollector tCollector = new TicketCollector(0, "localhost");
        assertTrue(tCollector.loginCollector("areds", "ioboh"));
        assertTrue(tCollector.getOfflineFinesNumber() == 0);
        
        tCollector.createFine("cf", 5);
        assertTrue(tCollector.getOfflineFinesNumber() == 1);
        
        CSystem cSystem = new CSystemFactory().buildNewCentralSystem();
        tCollector.createFine("cf2", 10);
        assertTrue(tCollector.getOfflineFinesNumber() == 0);
    }
}
