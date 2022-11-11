package lapr.project.auth.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Luís Araújo
 */
class UserTest {
    User user;

    @BeforeEach
    void setUp() {
        Email email = new Email("1111111@isep.ipp.pt");
        Password password = new Password("123456");
        user = new User(email, password, "João Pedro");
    }

    /**
     * Test that verifies if the method that returns the id is correct
     */
    @Test
    void getId() {
        //Arrange
        Email expectedResult = new Email("1111111@isep.ipp.pt");

        //Act
        Email result = user.getId();

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test that verifies if the method that returns the name is correct
     */
    @Test
    void getName() {
        //Arrange
        String expectedResult = "João Pedro";

        //Act
        String result = user.getName();

        //Assert
        assertEquals(expectedResult, result);
    }

    /**
     * Test that verifies if a certain id exists
     */
    @Test
    void hasId1() {
        //Arrange
        Email email2 = new Email("1111111@isep.ipp.pt");

        //Act
        boolean expectedResult = user.hasId(email2);

        //Assert
        assertTrue(expectedResult);
    }

    /**
     * Test that verifies if a certain id exists
     */
    @Test
    void hasId2() {
        //Arrange
        Email email2 = new Email("11111111@isep.ipp.pt");

        //Act
        boolean expectedResult = user.hasId(email2);

        //Assert
        assertFalse(expectedResult);
    }

    /**
     * Test that verifies if a certain password exists
     */
    @Test
    void hasPassword1() {
        //Act
        boolean expectedResult = user.hasPassword("123456");

        //Assert
        assertTrue(expectedResult);
    }

    /**
     * Test that verifies if a certain password exists
     */
    @Test
    void hasPassword2() {
        //Act
        boolean expectedResult = user.hasPassword("1234567");

        //Assert
        assertFalse(expectedResult);
    }

    /**
     * Test that verifies if a certain role was added
     */
    @Test
    void testIfRoleWasAdded1() {
        //Arrange
        UserRole userRole = new UserRole("1", "Description");

        //Act
        boolean expectedResult = user.addRole(userRole);

        //Assert
        assertTrue(expectedResult);
    }

    /**
     * Test that verifies if a certain role was added
     */
    @Test
    void testIfRoleWasAdded2() {
        //Arrange
        UserRole userRole = null;

        //Act
        boolean expectedResult = user.addRole(userRole);

        //Assert
        assertFalse(expectedResult);
    }

    /**
     * Test that verifies if a certain role was removed
     */
    @Test
    void testIfRoleWasRemoved1() {
        //Arrange
        UserRole userRole = new UserRole("1", "Description");

        //Act
        user.addRole(userRole);
        boolean expectedResult = user.removeRole(userRole);

        //Assert
        assertTrue(expectedResult);
    }

    /**
     * Test that verifies if a certain role was removed
     */
    @Test
    void testIfRoleWasRemoved2() {
        //Arrange
        UserRole userRole = null;

        //Act
        boolean expectedResult = user.removeRole(userRole);

        //Assert
        assertFalse(expectedResult);
    }

    /**
     * Test that verifies if a certain role exists
     */
    @Test
    void hasRole1() {
        //Arrange
        UserRole userRole = new UserRole("1", "Description");

        //Act
        user.addRole(userRole);
        boolean expectedResult = user.hasRole(userRole);

        //Assert
        assertTrue(expectedResult);
    }

    /**
     * Test that verifies if a certain role exists
     */
    @Test
    void hasRole2() {
        //Arrange
        UserRole userRole = new UserRole("2", "Description");

        //Act
        boolean expectedResult = user.hasRole(userRole);

        //Assert
        assertFalse(expectedResult);
    }

    /**
     * Test that verifies if a certain role exists
     */
    @Test
    void testHasRole1() {
        //Arrange
        UserRole userRole = new UserRole("1", "Description");

        //Act
        user.addRole(userRole);
        boolean expectedResult = user.hasRole(userRole.getId());

        //Assert
        assertTrue(expectedResult);
    }

    /**
     * Test that verifies if a certain role exists
     */
    @Test
    void testHasRole2() {
        //Arrange
        UserRole userRole = new UserRole("2", "Description");

        //Act
        boolean expectedResult = user.hasRole(userRole.getId());

        //Assert
        assertFalse(expectedResult);
    }

    /**
     * Test that verifies if a list of roles exists
     */
    @Test
    void getRoles() {
        //Arrange
        List<UserRole> list = new ArrayList<>();
        UserRole userRole = new UserRole("1", "Description");

        //Act
        list.add(userRole);

        //Assert
        assertNotNull(list);
    }

    /**
     * Test that tests the equals method
     */
    @Test
    void testEquals() {
        //Arrange
        Email email2 = new Email("1111112@isep.ipp.pt");
        Password password2 = new Password("1234567");
        User user2 = new User(email2, password2, "João Francisco");
        User user3 = null;

        //Assert
        assertTrue(user.equals(user));
        assertTrue(user2.equals(user2));
        assertFalse(user.equals(user2));
        assertFalse(user.equals(user3));
        assertFalse(user.equals("null"));
    }

    /**
     * Test that tests the constructor
     */
    @Test
    void testConstructor() {
        //Act
        Exception exception1 = assertThrows(IllegalArgumentException.class,()-> new User(null, null,""));
        Exception exception2 = assertThrows(IllegalArgumentException.class,()-> new User(new Email("luis@gmail.com"), null,"Luis"));
        Exception exception3 = assertThrows(IllegalArgumentException.class,()-> new User(new Email("luis@gmail.com"), new Password("123456"),""));
        String expectedMessage="User cannot have an id, password or name as null/blank.";
        String message1=exception1.getMessage();
        String message2=exception2.getMessage();
        String message3=exception3.getMessage();

        //Assert
        assertEquals(expectedMessage,message1);
        assertEquals(expectedMessage,message2);
        assertEquals(expectedMessage,message3);
    }

    /**
     * Test that tests the toString method
     */
    @Test
    void testToString() {
        //Arrange
        Email email2 = new Email("1111112@isep.ipp.pt");
        Password password2 = new Password("1234567");
        User user2 = new User(email2, password2, "João Francisco");

        //Act
        String expected= String.format("%s - %s", user2.getId().toString(),user2.getName());
        String result = user2.toString();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test that tests the hashcode method
     */
    @Test
    void testHashCode() {
        //Arrange
        Email email2 = new Email("1111112@isep.ipp.pt");
        Password password2 = new Password("1234567");
        User user2 = new User(email2, password2, "João Francisco");

        //Act
        int expected = 23*7+user2.getId().hashCode();
        int result = user2.hashCode();

        //Assert
        assertEquals(expected,result);
    }
}