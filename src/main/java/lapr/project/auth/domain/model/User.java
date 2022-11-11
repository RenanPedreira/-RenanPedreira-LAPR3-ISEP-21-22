package lapr.project.auth.domain.model;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import java.io.Serializable;
import java.util.*;

/**
 * @author Luís Araújo
 */
public class User implements Serializable {
    /**
     * User´s email
     */
    private final Email id;
    /**
     * User´s password
     */
    private final Password password;
    /**
     * User´s name
     */
    private final String name;
    /**
     * Set of user roles
     */
    private final Set<UserRole> roles = new HashSet<>();

    /**
     * Constructor to create an User with his email, password and name
     *
     * @param id
     * @param pwd
     * @param name
     */
    public User(Email id, Password pwd, String name) {
        if ((!ObjectUtils.allNotNull(id, pwd)) || StringUtils.isBlank(name))
            throw new IllegalArgumentException("User cannot have an id, password or name as null/blank.");

        this.id = id;
        this.password = pwd;
        this.name = name.trim();
    }

    /**
     * Method that returns the User´s email
     *
     * @return User´s email
     */
    public Email getId() {
        return id;
    }

    /**
     * Method that returns the User´s name
     *
     * @return User´s name
     */
    public String getName() {
        return name;
    }

    /**
     * Method that verifies if the user´s id already exists
     *
     * @param id
     * @return true if exists
     */
    public boolean hasId(Email id) {
        return this.id.equals(id);
    }

    /**
     * Method that verifies if the user´s password is valid
     *
     * @param pwd
     * @return true if is valid
     */
    public boolean hasPassword(String pwd) {
        return this.password.checkPassword(pwd);
    }

    /**
     * Method that verifies if a role was added
     *
     * @param role
     * @return true if the role was added and false otherwise
     */
    public boolean addRole(UserRole role) {
        if (role != null)
            return this.roles.add(role);
        return false;
    }

    /**
     * Method that verifies if a role was removed
     *
     * @param role
     * @return true if the role was removed and false otherwise
     */
    public boolean removeRole(UserRole role) {
        if (role != null)
            return this.roles.remove(role);
        return false;
    }

    /**
     * Method that verifies if the role exists
     *
     * @param role
     * @return true if the role exists and false otherwise
     */
    public boolean hasRole(UserRole role) {
        return this.roles.contains(role);
    }

    /**
     * Method that verifies if the role exists by using its id
     *
     * @param roleId
     * @return true if the role exists and false otherwise
     */
    public boolean hasRole(String roleId) {
        for (UserRole role : this.roles) {
            if (role.hasId(roleId))
                return true;
        }
        return false;
    }

    /**
     * Method that returns a list of roles
     *
     * @return list of roles
     */
    public List<UserRole> getRoles() {
        List<UserRole> list = new ArrayList<>();
        for (UserRole role : this.roles)
            list.add(role);
        return Collections.unmodifiableList(list);
    }

    /**
     * Method that returns the hashcode of the User
     *
     * @return hashcode of the User
     */
    @Override
    public int hashCode() {
        int hash2 = 7;
        hash2 = 23 * hash2 + this.id.hashCode();
        return hash2;
    }

    /**
     * Compares two users in order to see if they are the same
     *
     * @param o2 the other user that is being compared
     * @return true if the users are the same
     */
    @Override
    public boolean equals(Object o2) {
        // Inspired in https://www.sitepoint.com/implement-javas-equals-method-correctly/

        // self check
        if (this == o2)
            return true;
        // null check
        if (o2 == null)
            return false;
        // type check and cast
        if (getClass() != o2.getClass())
            return false;
        // field comparison
        User obj2 = (User) o2;
        return Objects.equals(this.id, obj2.id);
    }

    /**
     * Prints the information about the user
     *
     * @return the information about the user
     */
    @Override
    public String toString() {
        return String.format("%s - %s", this.id.toString(), this.name);
    }
}
