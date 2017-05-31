
import exceptions.TicketTypeNotFoundException;
import machines.TicketMachine;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import paymentMethods.PaymentMethod;
import ticket.TicketType;

/**
 *
 * @author Manuele
 */
public class TestApp {
    
    public TestApp() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testLoginCustomer() {
        TicketMachine tMachine = new TicketMachine(0, 5000, "10.87.156.248");
        assertTrue(tMachine.login("ADMIN", "ADMIN"));
    }
    
    @Test
    public void testBuySingleTicketCCard() throws TicketTypeNotFoundException {
        TicketMachine tMachine = new TicketMachine(0, 5000, "10.87.156.248");
        tMachine.setTicketToSell("Single");
        tMachine.setPaymentMethod(PaymentMethod.CREDITCARD);
        assertTrue(tMachine.buyTicket());
    }
    
    @Test
    public void testBuySingleTicketCash() {
        TicketMachine tMachine = new TicketMachine(0, 5000, "10.87.156.248");
        assertTrue(tMachine.getSelectedTicketCost() == 0);
        
        double inkLvl = tMachine.getInk();
        double paperLvl = tMachine.getPaper();
        float initMoney = tMachine.getMoneyInTank();
        
        tMachine.setTicketToSell("Single");
        tMachine.setPaymentMethod(PaymentMethod.CASH);
        assertTrue(tMachine.getSelectedTicketCost() == 1.5);
        
        tMachine.insertMoney(2);
        
        assertTrue(tMachine.getInk() < inkLvl);
        assertTrue(tMachine.getPaper() < paperLvl);
        assertTrue(initMoney < tMachine.getMoneyInTank());
    }
}
