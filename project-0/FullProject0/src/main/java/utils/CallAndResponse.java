package utils;

import utils.formatValidation.CurrencyFormat;

import java.text.ParseException;
import java.util.Scanner;

public class CallAndResponse extends CurrencyFormat {

    public String caller(String call){
        Scanner getResponse = new Scanner(System.in);
        System.out.println(call);
        String response = getResponse.nextLine();
        return response;
    }

    public String moneyResponse(String call) throws ParseException {
        Scanner getAmount = new Scanner(System.in);
        System.out.println(call);
        String money = getAmount.nextLine();
        float amount = Float.parseFloat(money);

        return betterConverter(amount);
    }
}
