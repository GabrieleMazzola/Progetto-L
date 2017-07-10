package ticketcollectortests;

import centralsystem.factory.CSystemFactory;
import database.factories.SimMapperFactory;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ticketcollector.TicketCollector;



public class SaveFineTests {
    
    public SaveFineTests() {
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
    public void testSaveFineWithConnection() throws IOException {
        
        CSystemFactory.getInstance().buildLightCSystem(SimMapperFactory.class.getName());
        
        TicketCollector tCollector;

        tCollector = new TicketCollector("127.0.0.1");
        tCollector.initConnection();
        assertTrue(tCollector.loginCollector("COLLECTOR", "COLLECTOR"));
        assertTrue(tCollector.getOfflineFinesNumber() == 0);
        
        tCollector.addFine("cf", 5);
        assertTrue(tCollector.getOfflineFinesNumber() == 0);
    }
    
//    @Test
//    public void testSaveFineWithoutConnection() throws IOException {
//        TicketCollector tCollector = new TicketCollector(0, "127.0.0.1");
//        tCollector.initConnection();
//        assertFalse(tCollector.loginCollector("COLLECTOR", "COLLECTOR"));
//        assertTrue(tCollector.getOfflineFinesNumber() == 0);
//        
//        tCollector.addFine("cf", 5);
//        assertTrue(tCollector.getOfflineFinesNumber() == 1);
//    }
//    
//    @Test
//    public void testSaveFineWithLaterConnection() {
//        TicketCollector tCollector = new TicketCollector(0, "127.0.0.1");
//        System.out.println(tCollector.loginCollector("COLLECTOR", "COLLECTOR"));
//        assertFalse(tCollector.loginCollector("COLLECTOR", "COLLECTOR"));
//        assertTrue(tCollector.getOfflineFinesNumber() == 0);
//        
//        tCollector.addFine("cf", 5);
//        assertTrue(tCollector.getOfflineFinesNumber() == 1);
//        
//        CSystemFactory.getInstance().buildLightCSystem(SimMapperFactory.class.getName());
//        tCollector.addFine("cf2", 10);
//        assertTrue(tCollector.getOfflineFinesNumber() == 0);
//    }
}