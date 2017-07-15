package databasetests;

import centralsystem.CSystem;
import centralsystem.factory.CSystemFactory;
import database.factories.DBMapperFactory;
import database.factories.SimMapperFactory;
import database.people.User;
import items.Fine;
import items.Sale;
import items.SimpleTicket;
import java.util.Date;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;



public class FetchDataTests {
    private CSystem cs;
    
    public FetchDataTests() {
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
    public void testFetchSingleTicket() {
        
        Date local  = new Date();
        cs.addSale(new Sale(local, (long)100, "ManuManu", new SimpleTicket("Biglietto Breve", "T1", 1.9, 90), "1227.0.0.1"));
       
        Set<Sale> t = cs.getSalesByUsername("ManuManu");
        Sale sale = t.iterator().next();
        
        assertTrue(sale != null);
        assertTrue(sale.getSaleDate().equals(local));
        assertTrue(sale.getSerialCode()==100);
        assertTrue(sale.getUsername().equals("ManuManu"));
        assertTrue(sale.getProduct().getDescription().equals("Biglietto Breve"));
        assertTrue(sale.getType().equals("T1"));
        assertTrue(sale.getProduct().getCost()==1.90);
        assertTrue(sale.getProduct().getDuration()==90);
        assertTrue(sale.getSellerMachineIp().equals("1227.0.0.1"));

    }
    
    @Test
    public void testFetchSingleTicketByUsername() {
        assertTrue(cs.getSalesByUsername("ManuManu").size() == 0);
    }
    
    @Test
    public void testFetchUser() {
        
        cs.createUser( "ManuManu", "ManuManu", "ManuManu", "ManuManu", "ManuManu","mail");
        
        assertTrue(cs.checkUser("ManuManu"));
        User user=cs.getUser("ManuManu");
        
        assertTrue(user.getName().equals("ManuManu"));
        assertTrue(user.getSurname().equals("ManuManu"));
        assertTrue(user.getUsername().equals("ManuManu"));
        assertTrue(user.getCf().equals("ManuManu"));
        assertTrue(user.getPsw().equals("ManuManu"));
        assertTrue(user.getEmail()=="mail");
       
        assertFalse(cs.checkUser("Otto"));
        assertTrue(cs.getUser("ottovolante")==null);
        
    }
    
    @Test
    public void testFetchCollector() {
        cs.createCollector("areds", "areds", "areds", "areds", "ioboh");
        
        assertTrue(cs.collectorLogin("areds", "ioboh"));
        assertFalse(cs.collectorLogin("un", "due"));
    }
    
    @Test
    public void testFetchFine() {
        cs.addFine(new Fine("0", "Patate", 90, "areds"));
        assertTrue(cs.getFineById(0) != null);
        assertTrue(cs.getFineById(10) == null);
    }
    
    @Test
    public void testFetchFineByCF() {
        
        cs.addFine(new Fine("0", "cf", 90, "areds"));
        cs.addFine(new Fine("0", "cf", 70, "areds"));
        cs.addFine(new Fine("0", "cf", 50, "areds"));
        
        Set<Fine> fines = cs.getFinesOf("cf");
        assertTrue(fines.size() == 3);
    }
    
    @Test
    public void testFetchProductCounter() {
        assertTrue(cs.getProductsCounter() == 0);
    }
    
}