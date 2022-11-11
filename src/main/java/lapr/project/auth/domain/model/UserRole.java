package lapr.project.auth.domain.model;

import org.apache.commons.lang3.StringUtils;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Luís Araújo
 */
public class UserRole implements Serializable {
    /**
     * UserRole´s id
     */
    private final String id;
    /**
     * UserRole´s description
     */
    private final String description;

    /**
     * Constructor to create an UserRole with its id and description
     * @param id
     * @param description
     */
    public UserRole(String id, String description)
    {
        if (StringUtils.isBlank(id) || StringUtils.isBlank(description))
            throw new IllegalArgumentException("UserRole id and/or description cannot be blank.");

        this.id = extractId(id);
        this.description = description;
    }

    /**
     * Method that puts the id in upper case
     * @param id
     * @return id in upper case
     */
    private String extractId(String id)
    {
        return id.trim().toUpperCase();
    }

    /**
     * Method that returns the id of the UserRole
     * @return id of the UserRole
     */
    public String getId() {
        return id;
    }

    /**
     * Method that returns the description of the UserRole
     * @return description of the UserRole
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method that verifies if the id is in the right format: not blank
     * @param id
     * @return true if the id is valid
     */
    public boolean hasId(String id)
    {
        if (StringUtils.isBlank(id))
            return false;
        return this.id.equals(extractId(id));
    }

    /**
     * Method that returns the hashcode of the UserRole
     *
     * @return hashcode of the UserRole
     */
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + this.id.hashCode();
        return hash;
    }

    /**
     * Compares two user roles in order to see if they are the same
     *
     * @param o the other user role that is being compared
     * @return true if the user roles are the same
     */
    @Override
    public boolean equals(Object o) {
        // Inspired in https://www.sitepoint.com/implement-javas-equals-method-correctly/

        // null check
        if (o == null)
            return false;
        // self check
        if (this == o)
            return true;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        // field comparison
        UserRole obj = (UserRole) o;
        return Objects.equals(this.id, obj.id);
    }

    /**
     * Prints the information about the user role
     *
     * @return the information about the user role
     */
    @Override
    public String toString()
    {
        return String.format("%s - %s", this.id, this.description);
    }
}
