package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import FileOp.UserOp;
import FileOp.UserOp.Role;

import Main.Main;

public class MainTests {

	UserOp userOp = new UserOp();
	String testUserName = "TestUserName",testName = "testName",testSName = "testSName";
	long testPassword = 123;
	
    @BeforeEach
    void setUp() {
    	userOp.add(testName, testSName, testUserName, testPassword, "Matematik", Role.Teacher);
    }

    @AfterEach
    void tearDown() {
    	  userOp.remove(testUserName, testPassword);
    }

    @Test
    @DisplayName("Kulanıcı ismi ve şifre ile kullanıcı kontrolü")
    void testIsExist1() {
        
        assertTrue(Main.isExist(testUserName, testPassword));
    }
    
    @Test
    @DisplayName("İsim ve soy isim ile kullanıcı kontrolü")
    void testIsExist2() {
        
        assertTrue(Main.isExist(testName, testSName));
    }
    
    @Test
    @DisplayName("Sadece kullanıcı ismi ile kullanıcı kontrolü")
    void testIsExist3() {
        
        assertTrue(Main.isExist(testUserName));
    }

  
}