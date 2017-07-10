package databasetests;

import centralsystem.CSystem;
import centralsystem.factory.CSystemFactory;
import database.factories.DBMapperFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;



public class FetchDataTests {
    private CSystem cs;
    
    public FetchDataTests() {
        cs = CSystemFactory.getInstance().buildLightCSystem(DBMapperFactory.class.getCanonicalName());
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

//    @Test
//    public void testFetchSingleTicket() {
//        
//        Date local  = new Date();
//        cs.addSale(new Sale(local, (long)100, "ManuManu", new SimpleTicket("Biglietto Breve", "T1", 1.30, 90), "1227.0.0.1"));
//        
//        Set<Sale> t = cs.getSalesByUsername("ManuManu");
//        Sale sale = t.iterator().next();
//        
//        assertTrue(sale != null);
//        assertTrue(sale.getSaleDate().equals(local));
//        assertTrue(sale.getSerialCode()==100);
//        assertTrue(sale.getUsername().equals("ManuManu"));
//        assertTrue(sale.getProduct().getDescription().equals("Biglietto Breve"));
//        assertTrue(sale.getType().equals("T1"));
//        assertTrue(sale.getProduct().getCost()==1.30);
//        assertTrue(sale.getProduct().getDuration()==90);
//        assertTrue(sale.getSellerMachineIp().equals("1227.0.0.1"));
//
//    }
//    
//    @Test
//    public void testFetchSingleTicketByUsername() {
//        assertTrue(cs.getSalesByUsername("ManuManu").size() == 0);
//    }
//    
//    @Test
//    public void testFetchUser() {
//        
//        cs.createUser( "ManuManu", "ManuManu", "ManuManu", "ManuManu", "ManuManu");
//        
//        assertTrue(cs.checkUser("ManuManu"));
//        assertFalse(cs.checkUser("Otto"));
//        
//    }
//    
//    @Test
//    public void testFetchCollector() {
//        
//        cs.createCollector("areds", "areds", "areds", "areds", "ioboh");
//        
//        assertTrue(cs.collectorLogin("areds", "ioboh"));
//        assertFalse(cs.collectorLogin("un", "due"));
//    }
//    
//    @Test
//    public void testFetchFine() {
//        cs.addFine(new Fine(0, "Patate", 90));
//        
//        assertTrue(cs.getFineById(0) != null);
//        assertTrue(cs.getFineById(10) == null);
//    }
//    
//    @Test
//    public void testFetchFineByCF() {
//        
//        cs.addFine(new Fine(0, "cf", 90));
//        cs.addFine(new Fine(0, "cf", 70));
//        cs.addFine(new Fine(0, "cf", 50));
//        
//        Set<Fine> fines = cs.getFinesOf("cf");
//        assertTrue(fines.size() == 3);
//    }
    
    @Test
    public void testFetchProductCounter() {
        assertTrue(cs.getProductsCounter() == 0);
    }
    
}