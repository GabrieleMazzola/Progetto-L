/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import centralsystem.CSystem;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import machines.StubMachine;
import machines.TicketMachine;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *
 * @author Zubeer
 */
@RunWith(JUnit4.class)
public class NewEmptyJUnitTest {
    String host = "10.87.232.53";
    CSystem CS;
   // TicketMachine tk = new TicketMachine(5000, host);
   // StubMachine stb = new StubMachine(host, 5000, tk);

    
    public NewEmptyJUnitTest() {
          
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
       // CS = new CSystem();
        
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void method() {
        assertTrue(true);
    }
    
    
    @Test
    public void testServerOnline() {
        try {
            Socket socketTest = new Socket(host, 5000);
            assertFalse(socketTest.isConnected());
        } catch (IOException ex) {
            assertTrue(true);
            System.err.println("ex");
        }
        assertTrue(true);
    }
}
