package machinetests;

import centralsystem.factory.CSystemFactory;
import controller.TicketMachineSession;
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
    public void testBuyPhysicalTicketCash() {
        double prevMoney = tMachine.getTotalMoney();
        double prevInkLvl = tMachine.getInk();
        
        TicketMachineSession session = new TicketMachineSession(tMachine);
        
        session.startSale("P1");
        session.insertingMoney(0.5);
        session.insertingMoney(1);
        
        double afterMoney = tMachine.getTotalMoney();
        double afterInkLvl = tMachine.getInk();
        
        assertTrue(afterMoney == prevMoney + 1.30);
        assertTrue(prevInkLvl > afterInkLvl);
    }

    @Test
    public void testBuyPhysicalTicketCCard() {
        double prevMoney = tMachine.getTotalMoney();
        double prevInkLvl = tMachine.getInk();
        
        TicketMachineSession session = new TicketMachineSession(tMachine);
        session.startSale("P1");
        session.insertingCardNumber("2222222222222222");
        
        double afterMoney = tMachine.getTotalMoney();
        double afterInkLvl = tMachine.getInk();
        
        assertTrue(afterMoney == prevMoney);
        assertTrue(prevInkLvl > afterInkLvl);
    }
    
    @Test 
    public void testBuyOnlineTicket() {
        double prevMoney=tMachine.getTotalMoney();
        double prevInkLvl = tMachine.getInk();

        TicketMachineSession session = new TicketMachineSession(tMachine);
        assertTrue(session.login("ADMIN", "ADMIN"));
        
        session.startSale("T1");
        session.insertingCardNumber("0000000000000000");

        double afterMoney = tMachine.getTotalMoney();
        double afterInkLvl = tMachine.getInk();

        assertTrue(afterMoney == prevMoney);
        assertTrue(prevInkLvl == afterInkLvl);
    }
    
    @Test
    public void testBuyOnlineSeason() {
        double prevMoney=tMachine.getTotalMoney();
        double prevInkLvl = tMachine.getInk();

        TicketMachineSession session = new TicketMachineSession(tMachine);
        assertFalse(session.login("ADMIN1", "ADMIN"));
        assertTrue(session.login("ADMIN", "ADMIN"));
        
        session.startSale("S1");
        session.insertingCardNumber("0000000000000000");

        double afterMoney = tMachine.getTotalMoney();
        double afterInkLvl = tMachine.getInk();

        assertTrue(afterMoney == prevMoney);
        assertTrue(prevInkLvl == afterInkLvl);
    
    }
    
    @Test
    public void testBuyPhysicalSeason() {
        double prevMoney=tMachine.getTotalMoney();
        double prevInkLvl = tMachine.getInk();
        
        TicketMachineSession session = new TicketMachineSession(tMachine);
        session.startSale("S1");
        session.insertingCardNumber("0000000000000000");

        double afterMoney = tMachine.getTotalMoney();
        double afterInkLvl = tMachine.getInk();

        assertTrue(afterMoney == prevMoney);
        assertTrue(prevInkLvl > afterInkLvl);
    }      
}