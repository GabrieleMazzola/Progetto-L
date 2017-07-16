package databasetests;

import database.interfaces.mapperinterfaces.UserMapper;
import database.mapper.simmapper.SimUserMapper;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class CacheTests {
    
    public CacheTests() {
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
        UserMapper mapper = new SimUserMapper();  
        assertTrue(mapper.getCacheSize() == 1);
        mapper.get("ManuManu");
        assertTrue(mapper.getCacheSize() == 1);
    }
}