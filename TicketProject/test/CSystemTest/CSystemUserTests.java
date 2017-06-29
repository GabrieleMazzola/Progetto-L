/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSystemTest;

import centralsystem.CSystem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Francesco
 */
public class CSystemUserTests {
    
    CSystem cs;
    
    public CSystemUserTests() {
        cs = new CSystem();
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
    public void searchUser(){
        assertTrue(cs.checkUser("ADMIN"));
    }
    
    @Test
    public void CreateUserNonExisting(){
        assertTrue(cs.createUser("NONADMIN", "NONADMIN", "NNDMN", "NONADMIN", "NONADMIN"));
    }
    
    @Test
    public void CreateUserExisting(){
        assertFalse(cs.createUser("ADMIN", "ADMIN", "ADMIN", "ADMIN", "ADMIN"));
    }
    
    @Test
    public void UserLoginRight(){
        assertTrue(cs.userLogin("ADMIN", "ADMIN"));
    }
    
    @Test
    public void UserLoginWrong(){
        assertFalse(cs.userLogin("ADMIN", "NONADMIN"));
    }
}
