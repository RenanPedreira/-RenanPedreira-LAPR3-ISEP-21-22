package lapr.project.auth;

import lapr.project.auth.domain.model.*;
import lapr.project.auth.domain.model.User;
import lapr.project.auth.mappers.UserRoleMapper;
import lapr.project.auth.mappers.dto.UserRoleDTO;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author Luís Araújo
 */
public class UserSession implements Serializable {
    /**
     * Instance of User
     */
    private User user = null;

    /**
     * Constructor that creates an User Session
     */
    public UserSession()
    {
        this.user = null;
    }

    /**
     * Constructor that creates an User Session using an user
     * @param user
     */
    public UserSession(User user)
    {
        if (user == null)
            throw new IllegalArgumentException("Argument cannot be null.");
        this.user = user;
    }

    /**
     * Method that does the logout
     */
    public void doLogout()
    {
        this.user = null;
    }

    /**
     * Method that verifies if an user is logged in
     * @return true if the user is logged in and false otherwise
     */
    public boolean isLoggedIn()
    {
        return this.user != null;
    }

    /**
     * Method that verifies if an user is logged in with a certain role
     * @param roleId
     * @return true if an user is logged in with a certain role and false otherwise
     */
    public boolean isLoggedInWithRole(String roleId)
    {
        if (isLoggedIn())
        {
            return this.user.hasRole(roleId);
        }
        return false;
    }

    /**
     * Method that returns the user´s name
     * @return user´s name
     */
    public String getUserName()
    {
        if (isLoggedIn())
            this.user.getName();
        return null;
    }

    /**
     * Method that returns the user´s id
     * @return user´s id
     */
    public Email getUserId()
    {
        if (isLoggedIn())
            return this.user.getId();
        return null;
    }

    /**
     * Method that returns a list of user roles dto
     * @return list of user roles dto
     */
    public List<UserRoleDTO> getUserRoles()
    {
        if (isLoggedIn()) {
            UserRoleMapper mapper = new UserRoleMapper();
            return mapper.toDTO(this.user.getRoles());
        }
        return Collections.emptyList();
    }
}
