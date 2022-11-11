package lapr.project.controller;

import lapr.project.auth.AuthFacade;
import lapr.project.model.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Luís Araújo
 */
class AppTest {
    App app;
    Company company;
    AuthFacade authFacade;

    @BeforeEach
    void setUp() {
        company = new Company("Company");
        app = new App();
        authFacade = new AuthFacade();
    }

    @Test
    void doLogin1() {
        //Act
        boolean result=app.doLogin("1231231@isep.ipp.pt", "123456");

        //Assert
        assertFalse(result);
    }

    @Test
    void doLogin2() {
        //Act
        boolean result=app.doLogin("admin@lei.lapr3.pt", "admin123456");

        //Assert
        assertTrue(result);
    }

}