package utils.formatValidation;

import utils.MyArrayList;

import java.text.DecimalFormat;
import java.text.ParseException;

public interface CurrencyFormat {
    //$1,234.56

    default String betterConverter(float amount) throws ParseException {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);

        String money = decimalFormat.format(amount);

        return money;
    }
}
