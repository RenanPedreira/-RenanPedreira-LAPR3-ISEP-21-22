package lapr.project.auth.domain.model;

import java.io.Serializable;

/**
 * @author Luís Araújo
 */
public class EmployeeRole implements Serializable {
    /**
     * EmployeeRole´s id
     */
    private final String id;

    /**
     * Constructor to create an EmployeeRole
     * @param id
     */
    public EmployeeRole(String id){
        this.id=id;
    }

    /**
     * Method that returns the id of the EmployeeRole
     * @return the id
     */
    public String getId() {
        return id;
    }
}
