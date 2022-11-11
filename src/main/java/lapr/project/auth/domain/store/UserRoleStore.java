package lapr.project.auth.domain.store;

import lapr.project.auth.domain.model.UserRole;
import lapr.project.utils.Utils;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Luís Araújo
 */
public class UserRoleStore implements Serializable {
    /**
     * Set of User Roles
     */
    private final Set<UserRole> store = new HashSet<>();

    /**
     * Method that creates a user role with his id and description
     * @param id
     * @param description
     * @return a user role
     */
    public UserRole create(String id, String description)
    {
        return new UserRole(id,description);
    }

    /**
     * Method that returns the set of User Roles
     * @return set of user roles
     */
    public Set<UserRole> getStore() {
        return new HashSet<>(store);
    }

    /**
     * Method that verifies if a user role exists and it´s not null. Then add it
     * @param role
     * @return true if the user role exists and false otherwise
     */
    public boolean add(UserRole role)
    {
        if (role != null&&!exists(role)) {
                return this.store.add(role);
        }
        return false;
    }

    /**
     * Method that verifies if a user role isn´t null to remove it
     * @param role
     * @return true if the user role is removed and false otherwise
     */
    public boolean remove(UserRole role)
    {
        if (role != null)
            return this.store.remove(role);
        return false;
    }

    /**
     * Method that returns a User Role by using its id
     * @param id
     * @return user role by using its id
     */
    public Optional<UserRole> getById(String id)
    {
        for(UserRole role: this.store)
        {
            if(role.hasId(id))
                return Optional.of(role);
        }
        return Optional.empty();
    }

    /**
     * Method that verifies if a user role exists by using its id
     * @param id
     * @return true if the user role exists
     */
    public boolean exists(String id)
    {
        Optional<UserRole> result = getById(id);
        return result.isPresent();
    }

    /**
     * Method that verifies if a user role exists
     * @param role
     * @return true if the user role exists
     */
    public boolean exists(UserRole role)
    {
        return this.store.contains(role);
    }

    /**
     * shows the roles available
     */
    public void showRoleslist(){
        for(UserRole u : store){
            Utils.print(u.getId());
        }
    }
}
