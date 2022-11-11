package lapr.project.auth.domain.store;

import lapr.project.auth.domain.model.Email;
import lapr.project.auth.domain.model.Password;
import lapr.project.auth.domain.model.User;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Luís Araújo
 */
public class UserStore implements Serializable {
    /**
     * Set of a User
     */
    private final Set<User> store = new HashSet<>();

    /**
     * Method that creates a User by using its name, email and password
     * @param name
     * @param email
     * @param password
     * @return User
     */
    public User create(String name, String email, String password)
    {
        return new User(new Email(email), new Password(password), name);
    }

    /**
     * Method that verifies that a user isn´t null to add it next
     * @param user
     * @return true if the user is added and false otherwise
     */
    public boolean add(User user)
    {
        if (user != null) {
            if (!exists(user))
                return this.store.add(user);
        }
        return false;
    }

    /**
     * Method that verifies that a user isn´t null to remove it next
     * @param user
     * @return false if a user is removed and false otherwise
     */
    public boolean remove(User user)
    {
        if (user != null)
            return this.store.remove(user);
        return false;
    }

    /**
     * Method that gets a user by using its email
     * @param email
     * @return a user
     */
    public Optional<User> getById(String email)
    {
        return this.getById(new Email(email));
    }

    /**
     * Method that gets a user by using its email
     * @param email
     * @return a user
     */
    public Optional<User> getById(Email email)
    {
        for(User user: this.store)
        {
            if(user.hasId(email))
                return Optional.of(user);
        }
        return Optional.empty();
    }

    /**
     * Method that verifies if a user exists by using its email
     * @param email
     * @return true if a user exists and false otherwise
     */
    public boolean exists(String email)
    {
        Optional<User> result = getById(email);
        return result.isPresent();
    }

    /**
     * Method that verifies if a user exists by using its email
     * @param email
     * @return true if a user exists and false otherwise
     */
    public boolean exists(Email email)
    {
        Optional<User> result = getById(email);
        return result.isPresent();
    }

    /**
     * Method that verifies if a user exists
     * @param user
     * @return true if a user exists and false otherwise
     */
    public boolean exists(User user)
    {
        return this.store.contains(user);
    }
}
