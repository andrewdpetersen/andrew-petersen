package utils;

import utils.formatValidation.CurrencyFormat;

import java.text.ParseException;
import java.util.Scanner;

/**
 * The interface CallAndResponse contains a method "caller" which takes a String
 * parameter, "call", prints "call" to the console, and returns the console input as
 * a String, "response".
 */
public interface CallAndResponse extends CurrencyFormat {

    /**
     * The caller method takes a String parameter, "call", prints "call" to the console,
     * and returns the console input as a String, "response".
     */
    default String caller(String call){
        //instantiates a Scanner and prints "call" to the console
        Scanner getResponse = new Scanner(System.in);
        System.out.println(call);

        //scans the console input and returns "response"
        String response = getResponse.nextLine();
        return response;
    }

    /**
     * The caller method takes a String parameter, "call", prints "call" to the console,
     * and returns the console input as a formatted float, "amount".
     */
    default String moneyResponse(String call) throws ParseException {
        //instantiates a Scanner and prints "call" to the console
        Scanner getAmount = new Scanner(System.in);
        System.out.println(call);

        //scans the console input and converts it to a float "amount"
        String money = getAmount.nextLine();
        float amount = Float.parseFloat(money);

        //returns a re-formatted "amount"
        return betterConverter(amount);
    }
}
