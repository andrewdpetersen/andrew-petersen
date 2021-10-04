package utils.formatValidation;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * The interface CurrencyFormat contains one method, "betterConverter". It takes
 * a float object as a parameter and returns a String object converted to proper
 * currency format.
 */
public interface CurrencyFormat {
    //$1,234.56

    /**
     * The betterConverter method takes a float object as a parameter and returns
     * a String object converted to proper currency format.
     */
    default String betterConverter(float amount) throws ParseException {

        //This line instantiates a DecimalFormat object, "decimalFormat".
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");

        //This line updates our instance, we will have commas in the format.
        decimalFormat.setGroupingUsed(true);

        //This line sets the commas to occur at every 3 numbers.
        decimalFormat.setGroupingSize(3);

        //This line formats the float parameter and assigns it to a String
        String money = decimalFormat.format(amount);

        //Return the String from the previous line
        return money;
    }
}
