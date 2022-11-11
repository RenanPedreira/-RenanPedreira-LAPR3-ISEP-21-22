package lapr.project.auth.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Luís Araújo
 */
class EmailTest {
    Email email;

    @BeforeEach
    public void setUp() throws Exception {
        email = new Email("1111111@isep.ipp.pt");
    }

    /**
     * Test that gets the email
     */
    @Test
    public void getIdentification() {
        //Arrange
        String expectedResult = "1111111@isep.ipp.pt";

        //Act
        String result = email.getEmail2();

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test that verifies if the email is valid
     */
    @Test
    public void validateEmail() {
        //Arrange
        boolean result=false;

        //Act
        boolean expectedResult=email.validate("");

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test that checks the equals method
     */
    @Test
    public void testEquals() {
        //Arrange
        Email email2 = new Email("1234567@isep.ipp.pt");
        Email email3 = null;

        //Assert
        assertTrue(email.equals(email));
        assertTrue(email2.equals(email2));
        assertFalse(email.equals(email2));
        assertFalse(email.equals(email3));
        assertFalse(email.equals("null"));
    }

    /**
     * Test the toString() method
     */
    @Test
    public void testToString() {
        //Arrange
        Email email2 = new Email("carlosluis@isep.ipp.pt");

        //Act
        String expected = email2.getEmail2();
        String result = email2.toString();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test the toString() method
     */
    @Test
    public void testHashCode() {
        //Arrange
        Email email2 = new Email("carlosluis@isep.ipp.pt");

        //Act
        int expected = 7*17+email2.getEmail2().hashCode();
        int result = email2.hashCode();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test to the constructor
     */
    @Test
    public void testConstructor(){
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class,()-> new Email("carlosluis"));
        String expectMessage = "Invalid Email Address.";
        String message = exception.getMessage();

        //Arrange
        assertEquals(expectMessage,message);
    }
}