package utils.formatValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface NameFormat {

    default boolean firstNameChecker(String first){
        Pattern firstNamePattern = Pattern.compile("^([A-Z][-a-zA-Z'.\\s]+[a-zA-Z.])$");
        Matcher checkFirstName = firstNamePattern.matcher(first);

        boolean validFirstName = checkFirstName.matches();

        return validFirstName;
    }

    default boolean lastNameChecker(String last){
        Pattern lastNamePattern = Pattern.compile("^([a-zA-Z][-a-zA-Z'.\\s]+[a-zA-Z.])$");
        Matcher checkLastName = lastNamePattern.matcher(last);

        boolean validLastName = checkLastName.matches();

        return validLastName;
    }
}
