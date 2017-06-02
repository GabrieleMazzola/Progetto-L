package TestMachine;

import centralsystem.CSystem;
import creator.CSystemFactory;
import creator.TicketMachineFactory;
import machines.TicketMachine;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ticket.SingleType;

/**
 *
 * @author Manuele
 */
public class TestBuyTicket {
    
    public TestBuyTicket() {
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
    public void testBuyTicketCash() {
        CSystem cSystem = new CSystemFactory().buildNewCentralSystem();
        
        TicketMachineFactory tmFactory = new TicketMachineFactory();
        TicketMachine tMachine = tmFactory.createTicketMachine(5000, "localhost");
        
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException exc) {
            System.out.println(exc);
        }
        
        float prevMoney = tMachine.getTotalMoney();
        double prevInkLvl = tMachine.getInk();
        
        tMachine.setTicketToSell(new SingleType());
        tMachine.insertMoney(0.5);
        tMachine.insertMoney(1);
        
        float afterMoney = tMachine.getTotalMoney();
        double afterInkLvl = tMachine.getInk();
        
        assertTrue(afterMoney == prevMoney + new SingleType().getCost());
        assertTrue(prevInkLvl > afterInkLvl);
    }
    
    @Test
    public void testBuyTicketCCard() {
        CSystem cSystem = new CSystemFactory().buildNewCentralSystem();
        
        TicketMachineFactory tmFactory = new TicketMachineFactory();
        TicketMachine tMachine = tmFactory.createTicketMachine(5000, "localhost");
        
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException exc) {
            System.out.println(exc);
        }
        
        float prevMoney = tMachine.getTotalMoney();
        double prevInkLvl = tMachine.getInk();
        
        tMachine.setTicketToSell(new SingleType());
        tMachine.buyTicketCreditCard("2222222222222222");
        
        float afterMoney = tMachine.getTotalMoney();
        double afterInkLvl = tMachine.getInk();
        
        assertTrue(afterMoney == prevMoney);
        assertTrue(prevInkLvl > afterInkLvl);
    }
}
