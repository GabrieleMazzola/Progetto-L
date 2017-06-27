/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSystemTest;

import centralsystem.CSystem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ticketCollector.Fine;

/**
 *
 * @author Francesco
 */
public class CSystemCollectorTests {
    
    CSystem cs;
    
    public CSystemCollectorTests() {
        cs = new CSystem();
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
        assertTrue(cs.addCollector("COLL", "COLL", "COLL", "COLL", "COLL"));
    }
    
    @Test
    public void collectorLogin(){
        cs.addCollector("COLL", "COLL", "COLL", "COLL", "COLL");
        assertTrue(cs.collectorLogin("COLL", "COLL"));
    }
    
    @Test
    public void collectorLoginFail(){
        assertFalse(cs.collectorLogin("COLL", "COLL"));
    }
    
    @Test
    public void doFine(){
        Fine fine = new Fine(444,"AJEJE BRAZORF",60);
        assertTrue(cs.makeFine(fine));
    }
    
    @Test
    public void checkIfTicketExists(){
        assertFalse(cs.existsTicket(22)); /*Scoprire come ottenere un seriale*/
    }
    
    @Test
    (expected = UnsupportedOperationException.class)
    public void checkIfTicketisValid(){
        assertFalse(cs.isValid(22)); /*Come sopra*/
    }
    
}
