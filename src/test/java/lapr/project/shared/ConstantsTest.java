package lapr.project.shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author
 */
class ConstantsTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void checkConstants(){
        String expected_ADMIN = "ADMINISTRATOR";
        String expected_TRAFFIC_MANAGER = "TRAFFIC MANAGER";
        String expected_FLEET_MANAGER = "FLEET MANAGER";
        String expected_WAREHOUSE_MANAGER = "WAREHOUSE MANAGER";
        String expected_WAREHOUSE_STAFF = "WAREHOUSE STAFF";
        String expected_PORT_STAFF = "PORT STAFF";
        String expected_PORT_MANAGER = "PORT MANAGER";
        String expected_CLIENT = "CLIENT";
        String expected_SHIP_CAPTAIN = "SHIP CAPTAIN";
        String expected_SHIP_CHIEF_ELETRICAL_ENGINEER = "SHIP CHIEF ELETRICAL ENGINEER";
        String expected_TRUCK_DRIVER = "TRUCK DRIVER";
        String expected_COMPANY_DESIGNATION = "Company.Designation";
        String expected_FILENAME = "src/main/resources/application.properties";

        assertEquals(expected_ADMIN,Constants.ROLE_ADMIN);
        assertEquals(expected_TRAFFIC_MANAGER,Constants.ROLE_TRAFFIC_MANAGER);
        assertEquals(expected_FLEET_MANAGER,Constants.ROLE_FLEET_MANAGER);
        assertEquals(expected_WAREHOUSE_MANAGER,Constants.ROLE_WAREHOUSE_MANAGER);
        assertEquals(expected_WAREHOUSE_STAFF,Constants.ROLE_WAREHOUSE_STAFF);
        assertEquals(expected_PORT_STAFF,Constants.ROLE_PORT_STAFF);
        assertEquals(expected_PORT_MANAGER,Constants.ROLE_PORT_MANAGER);
        assertEquals(expected_CLIENT,Constants.ROLE_CLIENT);
        assertEquals(expected_SHIP_CAPTAIN,Constants.ROLE_SHIP_CAPTAIN);
        assertEquals(expected_SHIP_CHIEF_ELETRICAL_ENGINEER,Constants.ROLE_SHIP_CHIEF_ELETRICAL_ENGINEER);
        assertEquals(expected_TRUCK_DRIVER,Constants.ROLE_TRUCK_DRIVER);
        assertEquals(expected_COMPANY_DESIGNATION,Constants.PARAMS_COMPANY_DESIGNATION);
        assertEquals(expected_FILENAME,Constants.PARAMS_FILENAME);

        Constants con = new Constants();
        String expected_Admin="Constants";
        assertEquals(expected_Admin,con.getClassName());
    }
}