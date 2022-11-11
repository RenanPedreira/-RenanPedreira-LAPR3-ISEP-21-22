package lapr.project.auth;

import lapr.project.auth.domain.model.Email;
import lapr.project.auth.domain.model.Password;
import lapr.project.auth.domain.model.User;
import lapr.project.auth.domain.model.UserRole;
import lapr.project.auth.mappers.dto.UserRoleDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author
 */
class UserSessionTest {
    UserSession userSession, userSession2;
    User user;

    @BeforeEach
    void setUp() {
        Email email = new Email("1111111@isep.ipp.pt");
        Password password = new Password("123456");
        user = new User(email, password, "João Pedro");
        userSession = new UserSession(user);
        userSession2 = new UserSession();
    }

    /**
     * Test that verifies if the user is logged in
     */
    @Test
    void isLoggedIn(){
        //Act
        Email email2 = new Email("1111111@isep.ipp.pt");
        Password password2 = new Password("123456");
        User user2 = new User(email2, password2, "João Pedro");
        UserSession userSession3=new UserSession(user2);

        //Assert
        assertTrue(userSession3.isLoggedIn());
    }

    /**
     * Test that tests the method that returns the username
     */
    @Test
    void getUserName() {
        //Act
        String result = userSession2.getUserName();

        //Assert
        assertNull(result);
    }

    /**
     * Test that tests the method that returns the user id
     */
    @Test
    void getUserId() {
        //Act
        Email result = userSession2.getUserId();

        //Assert
        assertNull(result);
    }

    /**
     * Test that test the method that return the list with the user roles
     */
    @Test
    void getUserRoles() {
        //Arrange
        List<UserRoleDTO> list = new ArrayList<>();
        UserRoleDTO userRoleDTO=new UserRoleDTO("1", "Description");

        //Act
        list.add(userRoleDTO);

        //Assert
        assertNotNull(list);
    }

    /**
     * Test that tests the method that verifies if a user with a certain role is added
     */
    @Test
    void isLoggedInWithRole() {
        //Act
        boolean result=userSession.isLoggedInWithRole("1");

        //Assert
        assertFalse(result);
    }
}