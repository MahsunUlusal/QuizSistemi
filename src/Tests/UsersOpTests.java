package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import FileOp.UserOp;
import FileOp.UserOp.Role;

public class UsersOpTests {
	
	UserOp userOp = new UserOp();
	String testUserName = "testUserName",testName = "testName",testSName = "testSName";
	long testPassword = 123;
	
	@DisplayName("Kullanıcı ekleme")
	@BeforeEach
    void setUp() {
		
		userOp.add(testName, testSName, testUserName, testPassword, "Matematik", Role.Teacher);
    }
	
	@DisplayName("Kullanıcı silme")
	@AfterEach
    void tearDown() {
        userOp.remove(testUserName, testPassword);
	}
	
	@DisplayName("Kullanıcı bilgilerini gösterme")
	@Test
    void testShowUser() { 
        String name = userOp.show(testUserName, testPassword, "name");
        String sName = userOp.show(testUserName, testPassword, "sName");
        String role = userOp.show(testUserName, testPassword, "role");
        String subject = userOp.show(testUserName, testPassword, "subject");
        
        assertEquals(testName, name);
        assertEquals(testSName, sName);
        assertEquals("Teacher", role);
        assertEquals("Matematik", subject);
    }
	
	@DisplayName("Kullanıcı bilgilerini değiştirme")
	@Test
    void testChangeUser() { 
        userOp.change(testUserName, testPassword, "name", "newTestName");
        userOp.change(testUserName, testPassword, "sName", "newTestSName");
        userOp.change(testUserName, testPassword, "userName", "newTestUserName");
        userOp.change("newTestUserName", testPassword, "password", "456");
        userOp.change("newTestUserName", 456, "subject", "Kimya");
        
        assertEquals("newTestName", userOp.show("newTestUserName", 456, "name"));
        assertEquals("newTestSName", userOp.show("newTestUserName", 456, "sName"));
        assertEquals("Kimya", userOp.show("newTestUserName", 456, "subject"));
        
        userOp.change("newTestUserName", 456, "userName", "testUserName");
        userOp.change(testUserName, 456, "password", "123");

    }
    
}
