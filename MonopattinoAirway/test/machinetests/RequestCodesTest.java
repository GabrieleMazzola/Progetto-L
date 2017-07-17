package machinetests;

import centralsystem.factory.CSystemFactory;
import controller.TicketMachineSession;
import database.factories.SimMapperFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ticketmachine.TicketMachine;


public class RequestCodesTest {
    
    public RequestCodesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        CSystemFactory.getInstance().buildLightCSystem(SimMapperFactory.class.getName());
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
    public void testRquestCodes() {

        TicketMachine tMachine = new TicketMachine(1, 5000, "localhost");
        
        try {
            Thread.sleep(4000);
        }
        catch(InterruptedException exc) {
            System.out.println(exc);
        }
        assertTrue(tMachine.getSerialsAmount() == 30);
    }
    
    @Test
    public void testRequestCodes() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        
        
        TicketMachine tMachine = new TicketMachine(2,5000,"localhost");
        TicketMachineSession session = new TicketMachineSession(tMachine);
        
        try {
            Thread.sleep(4000);
        }
        catch(InterruptedException exc) {
            System.out.println(exc);
        }
        
        for(int i=0; i<11; i++){
            session.startSale("P1");
            session.insertingMoney(20);
        }
        
        try {
            Thread.sleep(4000);
        }
        catch(InterruptedException exc) {
            System.out.println(exc);
        }
        
        assertTrue(tMachine.getSerialsAmount()==49);
        
    }
}
