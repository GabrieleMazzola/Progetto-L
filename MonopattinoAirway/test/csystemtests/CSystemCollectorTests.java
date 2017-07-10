package csystemtests;

import centralsystem.CSystem;
import database.factories.SimMapperFactory;
import items.Fine;
import org.junit.*;
import static org.junit.Assert.*;


public class CSystemCollectorTests {
    
    CSystem cs;
    
    public CSystemCollectorTests() {
        cs = new CSystem(SimMapperFactory.class.getCanonicalName());
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
    public void addCollector(){
        assertTrue(cs.createCollector("COLL", "COLL", "COLL", "COLL", "COLL"));
    }
    
    @Test
    public void collectorLogin(){
        assertTrue(cs.createCollector("COLL", "COLL", "COLL", "COLL", "COLL"));
        assertTrue(cs.collectorLogin("COLL", "COLL"));
    }
    
    @Test
    public void collectorLoginFail(){
        assertFalse(cs.collectorLogin("COLL", "COLL"));
    }
    
    @Test
    public void doFine(){
        Fine fine = new Fine("444","AJEJE BRAZORF",60,"Simone");
        assertTrue(cs.addFine(fine));
    }
    
    @Test
    public void checkIfTicketExists(){
        assertFalse(cs.existsTicket(22)); /*Scoprire come ottenere un seriale*/
    }

}