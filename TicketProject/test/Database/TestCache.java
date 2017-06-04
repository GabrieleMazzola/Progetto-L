package Database;

import databaseadapter.mapper.UserMapper;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Manuele
 */
public class TestCache {
    
    public TestCache() {
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
    public void testCache() {
        UserMapper mapper = new UserMapper("users");
        assertTrue(mapper.getCacheSize() == 0);
        mapper.get("ManuManu");
        assertTrue(mapper.getCacheSize() == 1);
    }
}
