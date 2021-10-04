package utils.formatValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The interface NameFormat contains two methods, "firstNameChecker" and lastNameChecker".
 * Both methods take a String parameter and return a boolean if the String matches
 * the specific pattern defined within the respective method.
 */
public interface NameFormat {
    /**
     * The method firstNameChecker takes a String parameter and returns a boolean.
     * Returns 'true' if the String matches the format defined in the Pattern.
     */
    default boolean firstNameChecker(String first){

        // This line assigns a Pattern instance to the reference, firstNamePattern.
        Pattern firstNamePattern = Pattern.compile("^([A-Z][-a-zA-Z'.\\s]+[a-zA-Z.])$");

        // This line calls the ".matcher()" method on the "first" String parameter and
        // assigns a Matcher instance to the reference, "checkFirstName".
        Matcher checkFirstName = firstNamePattern.matcher(first);

        // This line calls the ".matches()" method on "checkFirstName" and assigns a boolean
        // to the reference "validFirstName".
        boolean validFirstName = checkFirstName.matches();

        //This line returns the boolean from the previous line.
        return validFirstName;
    }

    /**
     * The method lastNameChecker takes a String parameter and returns a boolean.
     * Returns 'true' if the String matches the format defined in the Pattern.
     */
    default boolean lastNameChecker(String last){

        // This line assigns a Pattern instance to the reference, lastNamePattern.
        Pattern lastNamePattern = Pattern.compile("^([a-zA-Z][-a-zA-Z'.\\s]+[a-zA-Z.])$");

        // This line calls the ".matcher()" method on the "last" String parameter and
        // assigns a Matcher instance to the reference, "checkLastName".
        Matcher checkLastName = lastNamePattern.matcher(last);

        // This line calls the ".matches()" method on "checkLastName" and assigns a boolean
        // to the reference "validLastName".
        boolean validLastName = checkLastName.matches();

        //This line returns the boolean from the previous line.
        return validLastName;
    }
}
