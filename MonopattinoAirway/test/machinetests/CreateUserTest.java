package machinetests;

import centralsystem.factory.CSystemFactory;
import communication.SocketHandler;
import database.factories.SimMapperFactory;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ticketmachine.TicketMachine;


public class CreateUserTest {
    
    public static TicketMachine tMachine;
    
    public CreateUserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
            
        CSystemFactory.getInstance().buildLightCSystem(SimMapperFactory.class.getName());
        tMachine = new TicketMachine(1, 5000, "localhost");
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
    public void TestCreateUser(){
        
        assertTrue(tMachine.createUser("Simone", "Colosi", "CLS", "Colosi", "classico0000"));
        
        assertTrue(tMachine.getLoggedUsername()=="-");
        
        assertFalse(tMachine.login("Pippo", "classico0000"));
        assertFalse(tMachine.login("Colosi", "patate"));
        assertTrue(tMachine.login("Colosi", "classico0000"));
        assertTrue(tMachine.getLoggedUsername().equals("Colosi"));
        
        assertTrue(tMachine.logout());
        assertTrue(tMachine.getLoggedUsername()=="-");

    }
}
