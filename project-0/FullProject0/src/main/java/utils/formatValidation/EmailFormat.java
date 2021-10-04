package utils.formatValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The interface EmailFormat contains one method, "emailChecker", which takes a
 * String as a parameter and returns a boolean. This is used for validating that email
 * address Strings are in proper email format.
 */
public interface EmailFormat {
    /**
     * The method emailChecker takes a String as a parameter and returns a boolean.
     * This is used for validating that email address Strings are in proper email format.
     * It will return 'true' if the String is in a proper email format, otherwise, it
     * will return 'false'.
     */
    default boolean emailChecker(String email){
        //something@something.***

        // This line assigns a Pattern instance to the reference, "emailPattern".
        // ^ Starts the regex
        // [a-zA-Z] This means that the email pattern must start with a letter
        // [-a-zA-Z0-9_.]* This means, any letter, number, '-','_', or '.' can occur multiple times
        // [@] This means the '@' character must occur exactly once
        // [.] This means the '.' character must occur exactly once
        // [a-zA-Z]{3} This means that the pattern must end with exactly 3 letters
        Pattern emailPattern = Pattern.compile("^([a-zA-Z][-a-zA-Z0-9_.]*[@][a-zA-Z][-a-zA-Z0-9_]*[.][a-zA-Z]{3})$");

        // This line calls the ".matcher()" method on the "email" String parameter and
        // assigns a Matcher instance to the reference, "checkEmail".
        Matcher checkEmail = emailPattern.matcher(email);

        // This line calls the ".matches()" method on "checkEmail" and assigns a boolean
        // to the reference "validEmail".
        boolean validEmail=checkEmail.matches();

        //This line returns the boolean from the previous line.
        return validEmail;
    }
}
