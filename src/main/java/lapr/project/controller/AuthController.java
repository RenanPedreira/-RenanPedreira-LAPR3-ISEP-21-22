package lapr.project.controller;

import lapr.project.auth.mappers.dto.UserRoleDTO;

import java.util.List;

/**
 * @author Luís Araújo
 */
public class AuthController {
    /**
     * Instance of App
     */
    private final App app;

    /**
     * Constructor that creates a controller with the app
     */
    public AuthController() {
        this.app = App.getInstance();
    }

    /**
     * Method that verifies if it´s possible to do the login using the email and password
     * @param email
     * @param pwd
     * @return true if it´s possible and false otherwise
     */
    public boolean doLogin(String email, String pwd) {
        try {
            return this.app.doLogin(email, pwd);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * Method that returns a list of user roles
     * @return list of user roles
     */
    public List<UserRoleDTO> getUserRoles() {
        if (this.app.getCurrentUserSession().isLoggedIn()) {
            return this.app.getCurrentUserSession().getUserRoles();
        }
        return null;
    }

    /**
     * Method that does the logout
     */
    public void doLogout() {
        this.app.doLogout();
    }
}

