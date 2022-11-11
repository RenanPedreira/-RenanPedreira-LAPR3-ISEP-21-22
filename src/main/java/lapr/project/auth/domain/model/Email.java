package lapr.project.auth.domain.model;

import org.apache.commons.lang3.StringUtils;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 *
 * @author Luís Araújo
 */
public class Email implements Serializable {
    /**
     * String attribute that is going to be used as the email of the user
     */
    private final String email2;

    /**
     * Constructor to create an Email
     * @param email
     */
    public Email(String email)
    {
        if (!validate(email))
            throw new IllegalArgumentException("Invalid Email Address.");
        this.email2 = email;
    }

    /**
     * Method that validates the email
     * @param email
     * @return false if the email is blank or true if the email follows a certain format
     */
    public boolean validate(String email) {
        if (StringUtils.isBlank(email))
            return false;
        // Check for other invalid criteria here

        //
        return checkFormat(email);
    }

    /**
     * Method that validates the format of an email
     * @param email
     * @return true if the email is in the right format and false otherwise
     */
    // Extracted from https://www.geeksforgeeks.org/check-email-address-valid-not-java/
    private boolean checkFormat(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    /**
     * Method that returns the email that is being used
     * @return the email
     */
    public String getEmail2(){
        return email2;
    }

    /**
     * Method that returns the hashcode of the Email
     * @return hashcode of the Email
     */
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 17 * hash + this.email2.hashCode();
        return hash;
    }

    /**
     * Compares two emails in order to see if they are the same
     * @param o the other email that is being compared
     * @return true if the emails are the same
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
        Email obj = (Email) o;
        return Objects.equals(this.email2, obj.email2);
    }

    /**
     * Prints the information about the email
     * @return the information about the email
     */
    @Override
    public String toString()
    {
        return String.format("%s", this.email2);
    }
}
