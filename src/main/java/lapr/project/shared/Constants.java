package lapr.project.shared;

/**
 * @author Luís Araújo
 */
public class Constants {
    // Roles
    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    public static final String ROLE_TRAFFIC_MANAGER = "TRAFFIC MANAGER";
    public static final String ROLE_FLEET_MANAGER = "FLEET MANAGER";
    public static final String ROLE_WAREHOUSE_MANAGER = "WAREHOUSE MANAGER";
    public static final String ROLE_WAREHOUSE_STAFF = "WAREHOUSE STAFF";
    public static final String ROLE_PORT_STAFF = "PORT STAFF";
    public static final String ROLE_PORT_MANAGER = "PORT MANAGER";
    public static final String ROLE_CLIENT = "CLIENT";
    public static final String ROLE_SHIP_CAPTAIN = "SHIP CAPTAIN";
    public static final String ROLE_SHIP_CHIEF_ELETRICAL_ENGINEER = "SHIP CHIEF ELETRICAL ENGINEER";
    public static final String ROLE_TRUCK_DRIVER = "TRUCK DRIVER";
    public static final String PARAMS_COMPANY_DESIGNATION = "Company.Designation";
    public static final String PARAMS_FILENAME = "src/main/resources/application.properties";

    private final String className;

    public Constants(){
        className="Constants";
    }

    public String getClassName(){
        return className;
    }
}
