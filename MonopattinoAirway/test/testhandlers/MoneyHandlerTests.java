package testhandlers;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ticketmachine.handlers.MoneyHandler;


public class MoneyHandlerTests {
    
    private static final double[] money = {0.01,0.02,0.05,0.10,0.20,0.50,1,2,5,10,20,50,100,200};
    private static MoneyHandler mHandler;
    
    public MoneyHandlerTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mHandler = new MoneyHandler();
        for(int i=0;i<14;i++){
            mHandler.setQuantityOf(money[i], 10);
        }
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void insertedMoney(){
        
        for(int i = 0; i<14;i++){
            for(int j=0;j<=i;j++)
                mHandler.addMoney(money[i]);
        }
            
        for(int i = 0; i<14;i++){
            assertTrue(mHandler.getQuantityOf(money[i])==(i+10+1));
        }    
    }
    
    @Test
    public void setQuantityTest(){   
        
        for(int i=0;i<14;i++){
            mHandler.setQuantityOf(money[i], i);
        }
       
        for(int i=0;i<14;i++)
            assertTrue(mHandler.getQuantityOf(money[i])==i);   
    }
    
    @Test
    public void giveChangeTest(){
        
        assertEquals(1.00, mHandler.giveChange(2, 3), 0.1);
        assertTrue(mHandler.getQuantityOf(1.00)==9);
        

    }
}
