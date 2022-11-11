package lapr.project.auth.domain.model;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.apache.commons.lang3.StringUtils;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Luís Araújo
 */
public class Password implements Serializable {
    /**
     * String attribute that is going to be used as the password of the user
     */
    private final String password2;

    /**
     * Constructor to create a password
     * @param password2
     */
    public Password(String password2)
    {
        if (!validate(password2))
            throw new IllegalArgumentException("Invalid Email Address.");
        this.password2 = createHash(password2);
    }

    /**
     * Method that validates the password
     * @param password
     * @return true if the password is not blank
     */
    public boolean validate(String password) {
        return !(StringUtils.isBlank(password));
    }

    /**
     * Method that hashes the password
     * @param password
     * @return password hashed
     */
    private String createHash(String password)
    {
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST,password.toCharArray());
    }

    /**
     * Method that verifies if a password is valid: not blank and within the characters´ limit
     * @param pwd
     * @return
     */
    public boolean checkPassword(String pwd)
    {
        if (StringUtils.isBlank(pwd))
            return false;
        BCrypt.Result result = BCrypt.verifyer().verify(pwd.toCharArray(),this.password2.toCharArray());
        return result.verified;
    }

    /**
     * Method that returns the hashcode of the Password
     * @return hashcode of the Password
     */
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 7 * hash + this.password2.hashCode();
        return hash;
    }

    /**
     * Compares two passwords in order to see if they are the same
     * @param o the other password that is being compared
     * @return true if the passwords are the same
     */
    @Override
    public boolean equals(Object o) {
        // Inspired in https://www.sitepoint.com/implement-javas-equals-method-correctly/

        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        // field comparison
        Password obj = (Password) o;
        return Objects.equals(this.password2, obj.password2);
    }

    /**
     * Returns the information about the password
     * @return the information about the password
     */
    public String toString(){
        return password2;
    }
}
