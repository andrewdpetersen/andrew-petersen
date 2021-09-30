package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class CallAndResponse extends CurrencyFormat{

    public String callAndResponse(String call){
        Scanner getResponse = new Scanner(System.in);
        System.out.println(call);
        String response = getResponse.nextLine();
        getResponse.close();
        return response;
    }

    public String moneyResponse(String call){
        Scanner getAmount = new Scanner(System.in);
        System.out.println(call);
        String money = getAmount.nextLine();
        float amount = Float.parseFloat(money);

        return converter(amount);
    }
}
