package lapr.project.controller;

import lapr.project.auth.AuthFacade;
import lapr.project.auth.UserSession;
import lapr.project.auth.domain.model.Email;
import lapr.project.auth.domain.model.Password;
import lapr.project.auth.domain.model.User;
import lapr.project.auth.mappers.dto.UserRoleDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Luís Araújo
 */
class AuthControllerTest {
    AuthController authController;
    App app;
    AuthFacade authFacade;

    @BeforeEach
    void setUp() {
        authController = new AuthController();
        app = new App();
        authFacade = new AuthFacade();
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
     * Test that does the login with the right data
     */
    @Test
    void doLogin1() {
        //Assert
        assertTrue(authController.doLogin("admin@lei.lapr3.pt", "admin123456"));
    }

    /**
     * Test that does the login with the wrong data
     */
    @Test
    void doLogin2() {
        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User user=new User(null, null, null);
            UserSession userSession=new UserSession(user);
            authController.doLogin(userSession.getUserId().getEmail2(), "123456");
        });
    }

    /**
     * Test that does the login with the wrong data
     */
    @Test
    void doLogin3() {
        //Assert
        assertFalse(authController.doLogin("", "admin123456"));
    }

    /**
     * Test that tests the method that returns the user roles
     */
    @Test
    void getUserRoles() {
        //Arrange
        authController.doLogin("admin@lei.lapr3.pt", "admin123456");
        UserRoleDTO userRoleDTO = new UserRoleDTO("1", "Description");
        List<UserRoleDTO> rolesDTO = new ArrayList<>();

        //Act
        rolesDTO = authController.getUserRoles();

        //Assert
        assertNotNull(rolesDTO);
    }

    /**
     * Test that tests the method that returns the user roles
     */
    @Test
    void getUserRoles2() {
        //Arrange
        authController.doLogin("admin@lei.lapr3.pt", "admin123456");
        authController.doLogout();
        UserRoleDTO userRoleDTO = new UserRoleDTO("1", "Description");
        List<UserRoleDTO> rolesDTO = new ArrayList<>();

        //Act
        rolesDTO = authController.getUserRoles();

        //Assert
        assertNull(rolesDTO);
    }

    /**
     * Test that tests the method that does the logout
     */
    @Test
    void doLogout() {
        UserSession userSession = new UserSession();
        AuthFacade authFacade = new AuthFacade();
        userSession.doLogout();
        authFacade.doLogout();
        app.doLogout();
    }
}