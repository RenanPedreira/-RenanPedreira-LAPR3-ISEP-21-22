package lapr.project.auth.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Luís Araújo
 */
class PasswordTest {
    Password password;
    @BeforeEach
    void setUp() {
        password=new Password("123456");
    }

    /**
     * Test that verifies if the password is valid
     */
    @Test
    public void validatePassword() {
        //Arrange
        boolean result=false;

        //Act
        boolean expectedResult=password.validate("");

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test that verifies if the password is not blank or null
     */
    @Test
    public void checkPassword() {
        //Arrange
        boolean result=false;

        //Act
        boolean expectedResult=password.checkPassword("");

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test that checks the equals method
     */
    @Test
    public void testEquals() {
        //Arrange
        Password password2 = new Password("1234567");
        Password password3 = null;

        //Assert
        assertTrue(password.equals(password));
        assertTrue(password2.equals(password2));
        assertFalse(password.equals(password2));
        assertFalse(password.equals(password3));
        assertFalse(password.equals("null"));
    }

    /**
     * Test that tests the hash code method
     */
    @Test
    void testHashCode() {
        //Arrange
        Password password2 = new Password("1234567");

        //Act
        int expected=7*7+password2.toString().hashCode();
        int result = password2.hashCode();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test that tests the constructor
     */
    @Test
    void testConstructor() {
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class,()-> new Password(""));
        String expectedMessage="Invalid Email Address.";
        String message=exception.getMessage();

        //Assert
        assertEquals(expectedMessage,message);
    }
}