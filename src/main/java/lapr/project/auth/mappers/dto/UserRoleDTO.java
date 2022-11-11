package lapr.project.auth.mappers.dto;

/**
 * @author Luís Araújo
 */
public class UserRoleDTO {
    /**
     * User role´s id
     */
    private final String id;
    /**
     * User role´s description
     */
    private final String description;

    /**
     * Constructor to create an UserRoleDto by using its id and description
     * @param id
     * @param description
     */
    public UserRoleDTO(String id, String description)
    {
        this.id = id;
        this.description = description;
    }

    /**
     * Method that returns the id
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Method that returns the description
     * @return description
     */
    public String getDescription() {
        return description;
    }
}
