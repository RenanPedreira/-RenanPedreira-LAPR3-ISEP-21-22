package lapr.project.auth;

import lapr.project.auth.domain.model.User;
import lapr.project.auth.domain.model.*;
import lapr.project.auth.domain.store.*;
import java.io.Serializable;
import java.util.Optional;

/**
 * @author Luís Araújo
 */
public class AuthFacade implements Serializable {
    /**
     * Instance o User session
     */
    private UserSession userSession;
    /**
     * Instance of UserRoleStore
     */
    private final UserRoleStore roles ;
    /**
     * Instance of UserStore
     */
    private final UserStore users;

    /**
     * Constructor that creates an AuthFacade
     */
    public AuthFacade()
    {
        this.userSession = new UserSession();
        this.roles = new UserRoleStore();
        this.users = new UserStore();
    }

    /**
     * Method that verifies if a role exists by using its id
     * @param id
     * @return true if the role exists
     */
    public boolean rolesExists(String id){
        return this.roles.exists(id);

    }

    /**
     * Method that creates an user role by using an id and description
     * @param id
     * @param description
     * @return true if the user role was added and false otherwise
     */
    public boolean addUserRole(String id, String description)
    {
        UserRole role = this.roles.create(id, description);
        return this.roles.add(role);
    }

    /**
     * Method that creates an user by using an name, email and password
     * @param name
     * @param email
     * @param pwd
     * @return true if the user was added and false otherwise
     */
    public boolean addUser(String name, String email, String pwd)
    {
        User user = this.users.create(name, email, pwd);
        return this.users.add(user);
    }

    /**
     * Method that shows the roles
     */
    public void showRoleslist(){
        this.roles.showRoleslist();
    }

    /**
     * Method that adds an user with a certain role
     * @param name
     * @param email
     * @param pwd
     * @param roleId
     * @return true if the user was added
     */
    public boolean addUserWithRole(String name, String email, String pwd, String roleId)
    {
        Optional<UserRole> roleResult = this.roles.getById(roleId);
        if (!roleResult.isPresent())
            return false;

        User user = this.users.create(name,email,pwd);
        user.addRole(roleResult.get());
        return this.users.add(user);
    }

    /**
     * Method that adds an user with various roles
     * @param name
     * @param email
     * @param pwd
     * @param rolesId
     * @return true if the user was added
     */
    public boolean addUserWithRoles(String name, String email, String pwd, String[] rolesId)
    {
        User user = this.users.create(name, email, pwd);
        for (String roleId: rolesId)
        {
            Optional<UserRole> roleResult = this.roles.getById(roleId);
            if (roleResult.isPresent())
                user.addRole(roleResult.get());
        }

        return this.users.add(user);
    }

    /**
     * Method that verifies if an user exists by using its email
     * @param email
     * @return true if the user exists
     */
    public boolean existsUser(String email)
    {
        return this.users.exists(email);
    }

    /**
     * Method that does the login with an email and password
     * @param email
     * @param password
     * @return the user session
     */
    public UserSession doLogin(String email, String password)
    {
        Optional<User> result = this.users.getById(email);
        if (result.isPresent())
        {
            User user = result.get();
            if (user.hasPassword(password)){
                this.userSession = new UserSession(user);
            }
        }
        return this.userSession;
    }

    /**
     * Method that does the logout
     */
    public void doLogout()
    {
        this.userSession.doLogout();
    }

    /**
     * Method that returns the current user session
     * @return current user session
     */
    public UserSession getCurrentUserSession()
    {
        return this.userSession;
    }

}
