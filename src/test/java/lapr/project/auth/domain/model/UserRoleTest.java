package lapr.project.auth.domain.model;

import lapr.project.shared.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Luís Araújo
 */
class UserRoleTest {
    UserRole userRole;
    @BeforeEach
    void setUp() {
        userRole=new UserRole("1", "Description");
    }

    /**
     * Test that verifies if the method that returns the id is correct
     */
    @Test
    void getId() {
        //Arrange
        String expectedResult = "1";

        //Act
        String result = userRole.getId();

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test that verifies if the method that returns the description is correct
     */
    @Test
    void getDescription() {
        //Arrange
        String expectedResult = "Description";

        //Act
        String result = userRole.getDescription();

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test that verifies if a certain id exists
     */
    @Test
    void hasId() {
        //Act
        boolean expectedResult = userRole.hasId("1");
        boolean expectedResul2 = userRole.hasId("");

        //Assert
        assertTrue(expectedResult);
        assertFalse(expectedResul2);
    }

    /**
     * Test that verifies if a certain id exists
     */
    @Test
    void hasId2() {
        //Act
        boolean expectedResult = userRole.hasId("12");

        //Assert
        assertFalse(expectedResult);
    }

    /**
     * Test that checks the equals method
     */
    @Test
    public void testEquals() {
        //Arrange
        UserRole userRole2=new UserRole("2", "Description2");
        UserRole userRole3=null;

        //Assert
        assertTrue(userRole.equals(userRole));
        assertTrue(userRole2.equals(userRole2));
        assertFalse(userRole.equals(userRole2));
        assertFalse(userRole.equals(userRole3));
        assertFalse(userRole.equals("null"));
    }

    /**
     * Test that tests the constructor
     */
    @Test
    void testConstructor() {
        //Act
        Exception exception1 = assertThrows(IllegalArgumentException.class,()-> new UserRole(Constants.ROLE_WAREHOUSE_STAFF,""));
        Exception exception2 = assertThrows(IllegalArgumentException.class,()-> new UserRole("",Constants.ROLE_WAREHOUSE_STAFF));
        Exception exception3 = assertThrows(IllegalArgumentException.class,()-> new UserRole("",""));
        String expectedMessage="UserRole id and/or description cannot be blank.";
        String message1=exception1.getMessage();
        String message2=exception2.getMessage();
        String message3=exception3.getMessage();

        //Assert
        assertEquals(expectedMessage,message1);
        assertEquals(expectedMessage,message2);
        assertEquals(expectedMessage,message3);
    }

    /**
     * Test that tests the equals method
     */
    @Test
    void testEquals1() {
        //Arrange
        UserRole userRole2 = new UserRole("ID", "Description");
        UserRole userRole3 = new UserRole("ID2", "Description2");

        //Assert
        assertTrue(userRole2.equals(userRole2));
        assertTrue(userRole3.equals(userRole3));
        assertFalse(userRole2.equals(userRole3));
        assertFalse(userRole3.equals(userRole2));
        assertFalse(userRole2.equals("null"));
    }

    /**
     * Test that tests the toString method
     */
    @Test
    void testToString() {
        //Arrange
        String expected=String.format("%s - %s",userRole.getId(),userRole.getDescription());

        //Act
        String result=userRole.toString();

        //Assert
        assertEquals(expected,result);
    }
}