package lapr.project.controller;

import lapr.project.auth.AuthFacade;
import lapr.project.auth.UserSession;
import lapr.project.model.Company;
import lapr.project.shared.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Luís Araújo
 */
public class App {
    /**
     * Instance of the class
     */
    private static App singleton = null;
    /**
     * Instance of Company
     */
    private final Company company;
    /**
     * Instance of AuthFacade
     */
    private final AuthFacade authFacade;

    /**
     * Constructor that creates the App
     */
    public App() {
        Properties props = getProperties();
        this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION));
        this.authFacade = this.company.getAuthFacade();
        bootstrap();
    }

    /**
     * Method that returns the instance of App
     * @return instance of App
     */
    public static App getInstance() {
        if (singleton == null) {
            synchronized (App.class) {
                singleton = new App();
            }
        }
        return singleton;
    }

    /**
     * Method that gets the company
     * @return the company
     */
    public Company getCompany() {
        return this.company;
    }

    /**
     * Method that gets the current user session
     * @return current user session
     */
    public UserSession getCurrentUserSession() {
        return this.authFacade.getCurrentUserSession();
    }

    /**
     * Method that verifies if the login is done
     * @param email
     * @param pwd
     * @return true if the login is done
     */
    public boolean doLogin(String email, String pwd) {
        return this.authFacade.doLogin(email, pwd).isLoggedIn();
    }

    /**
     * Method that does the logout
     */
    public void doLogout() {
        this.authFacade.doLogout();
    }

    /**
     * Method that returns the properties
     * @return the properties
     */
    private Properties getProperties() {
        Properties props = new Properties();

        // Read configured values
        try {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return props;
    }

    /**
     * Method that has the roles and the emails and passwords of users of a certain role
     */
    private void bootstrap() {
        //ADMIN
        this.authFacade.addUserRole(Constants.ROLE_ADMIN, Constants.ROLE_ADMIN);
        this.authFacade.addUserWithRole("Main Administrator", "admin@lei.lapr3.pt", "admin123456", Constants.ROLE_ADMIN);
        //TRAFFIC MANAGER
        this.authFacade.addUserRole(Constants.ROLE_TRAFFIC_MANAGER,Constants.ROLE_TRAFFIC_MANAGER);
        this.authFacade.addUserWithRole("Traffic Manager", "trafficmanager@lei.lapr3.pt", "tm123456", Constants.ROLE_TRAFFIC_MANAGER);
        //FLEET MANAGER
        this.authFacade.addUserRole(Constants.ROLE_FLEET_MANAGER,Constants.ROLE_FLEET_MANAGER);
        this.authFacade.addUserWithRole("Fleet Manager Manager", "FleetM1@lei.pt", "fm123456", Constants.ROLE_FLEET_MANAGER);
        //PORT MANAGER
        this.authFacade.addUserRole(Constants.ROLE_PORT_MANAGER,Constants.ROLE_PORT_MANAGER);
        this.authFacade.addUserWithRole("Port Manager", "PortM1@lei.lapr3.pt", "pm123456", Constants.ROLE_PORT_MANAGER);
        //SHIP CAPTAIN
        this.authFacade.addUserRole(Constants.ROLE_SHIP_CAPTAIN,Constants.ROLE_SHIP_CAPTAIN);
        this.authFacade.addUserWithRole("Ship Captain", "shipcap@lapr3.pt", "sc123456", Constants.ROLE_SHIP_CAPTAIN);
        //CLIENT
        this.authFacade.addUserRole(Constants.ROLE_CLIENT,Constants.ROLE_CLIENT);
        this.authFacade.addUserWithRole("Client", "client@lei.lapr3.pt", "client123456", Constants.ROLE_CLIENT);
    }
}
