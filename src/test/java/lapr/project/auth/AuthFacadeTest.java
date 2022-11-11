package lapr.project.auth;

import lapr.project.auth.domain.model.Email;
import lapr.project.auth.domain.model.Password;
import lapr.project.auth.domain.model.User;
import lapr.project.auth.domain.model.UserRole;
import lapr.project.auth.domain.store.UserRoleStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author
 */
class AuthFacadeTest {
    AuthFacade authFacade;
    UserRole userRole;
    UserRoleStore userRoleStore;

    @BeforeEach
    void setUp() {
        authFacade = new AuthFacade();
        userRoleStore = new UserRoleStore();
        userRole = userRoleStore.create("1", "Description");
        userRoleStore.add(userRole);
        authFacade.addUserRole("1", "description");
        authFacade.addUser("João Pedro", "1111111@isep.ipp.pt", "123456");
        authFacade.addUserRole("2", "description2");
    }

    /**
     * Test that tests the method that verifies if a certain role exists
     */
    @Test
    void testIfRolesExist() {
        //Act
        boolean result = authFacade.rolesExists("1");

        //Assert
        assertTrue(result);
    }

    /**
     * Test that tests the method that verifies if a certain user is added
     */
    @Test
    void testIfUserIsAdd() {
        //Act
        boolean result = authFacade.addUser("João Francisco", "1111112@isep.ipp.pt", "1234567");

        //Assert
        assertTrue(result);
    }

    /**
     * Test that tests the method that verifies if a certain user with a certain role is added
     */
    @Test
    void testIfUserWithRoleIsAdd() {
        //Act
        boolean result = authFacade.addUserWithRole("João Maria", "1111113@isep.ipp.pt", "12345678", "1");

        //Assert
        assertTrue(result);
    }

    /**
     * Test that tests the method that verifies if a certain user with certain roles is added
     */
    @Test
    void testIfUserWithRolesIsAdd() {
        //Arrange
        String[] rolesId = new String[10];
        rolesId[0] = "1";
        rolesId[1] = "2";

        //Act
        boolean result = authFacade.addUserWithRoles("João Maria", "1111113@isep.ipp.pt", "12345678", rolesId);

        //Assert
        assertTrue(result);
    }

    /**
     * Test that tests the method that verifies if a certain user exists
     */
    @Test
    void testIfUserExists() {
        //Act
        boolean result = authFacade.existsUser("1111111@isep.ipp.pt");

        //Assert
        assertTrue(result);

    }

    /**
     * Test that tests the method that does the login
     */
    @Test
    void doLogin() {
        //Arrange
        Email email = new Email("1111111@isep.ipp.pt");
        Password password = new Password("123456");
        User user = new User(email, password, "João Pedro");
        UserSession expectedResult = new UserSession(user);

        //Act
        UserSession result = authFacade.doLogin("1111111@isep.ipp.pt", "123456");

        //Assert
        assertNotEquals(expectedResult, result);
    }

    /**
     * Test that tests the method that returns the current user session
     */
    @Test
    void getCurrentUserSession() {
        //Arrange
        Email email = new Email("1111111@isep.ipp.pt");
        Password password = new Password("123456");
        User user = new User(email, password, "João Pedro");
        UserSession expectedResult = new UserSession(user);

        //Act
        UserSession result = authFacade.getCurrentUserSession();

        //Assert
        assertNotEquals(expectedResult, result);
    }
}