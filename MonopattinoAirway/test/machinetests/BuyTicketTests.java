package machinetests;

import centralsystem.factory.CSystemFactory;
import database.factories.SimMapperFactory;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ticketmachine.TicketMachine;


public class BuyTicketTests {
    
    private static TicketMachine tMachine;
    
    public BuyTicketTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {

            
        CSystemFactory.getInstance().buildLightCSystem(SimMapperFactory.class.getName());
        tMachine = new TicketMachine(0,5000, "localhost");
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
    public void testBuyTicketCash() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        
        
        
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException exc) {
            System.out.println(exc);
        }
        
        double prevMoney = tMachine.getTotalMoney();

        double prevInkLvl = tMachine.getInk();
        
        tMachine.setTicketToSell("T1");
        tMachine.insertMoney(0.5);
        tMachine.insertMoney(1);
        
        double afterMoney = tMachine.getTotalMoney();
        double afterInkLvl = tMachine.getInk();
        
        assertTrue(afterMoney == prevMoney + 1.30);
        assertTrue(prevInkLvl > afterInkLvl);
    }

    @Test
    public void testBuyTicketCCard() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException exc) {
            System.out.println(exc);
        }
        
        double prevMoney = tMachine.getTotalMoney();
        double prevInkLvl = tMachine.getInk();
        
        tMachine.setTicketToSell("T1");
        tMachine.buyTicketCreditCard("2222222222222222");
        
        double afterMoney = tMachine.getTotalMoney();
        double afterInkLvl = tMachine.getInk();
        
        assertTrue(afterMoney == prevMoney);
        assertTrue(prevInkLvl > afterInkLvl);
    }
}