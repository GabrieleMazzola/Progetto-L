package banktests;

import bank.CheckValidityAlgorithm;
import bank.LuhnAlgorithm;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class AlgorithmTest {
    
    public AlgorithmTest() {
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
    public void testCorrectCardNumber() {
        String code ="4388576018410707";
        
        CheckValidityAlgorithm algorithm = new LuhnAlgorithm();
        assertTrue(algorithm.check(code));
    }
    
    @Test
    public void testIncorrectCardNumber() {
        String code ="5488576018410707";
        
        CheckValidityAlgorithm algorithm = new LuhnAlgorithm();
        assertFalse(algorithm.check(code));
    }
}