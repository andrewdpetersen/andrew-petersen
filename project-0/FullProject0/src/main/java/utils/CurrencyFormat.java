package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyFormat {
    //$1,234.56
    // OR
    //$1234.56

    public String converter(float amount){
        BigDecimal balance = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);

        String money = "$"+balance;
        return money;
    }
}
