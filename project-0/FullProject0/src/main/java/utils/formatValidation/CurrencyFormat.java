package utils.formatValidation;

import utils.MyArrayList;

import java.text.DecimalFormat;
import java.text.ParseException;

public class CurrencyFormat {
    //$1,234.56

    public String converter(float amount) {

        float amount100 = amount * 100;
        int amount100round = Math.round(amount100);
        String number = String.valueOf(amount100round);

        MyArrayList<String> numberChars = new MyArrayList<>();
        int cursor = 0;
        while (cursor < number.length()) {
            Character tempChar = number.charAt(cursor);
            String tempString = tempChar.toString();
            numberChars.add(tempString);
            cursor++;
        }

        int numSize = number.length();

        if(numSize==12){
            numberChars.add(",",1);
            numberChars.add(",",5);
            numberChars.add(",",9);
            numberChars.add(".",13);
        }else if(numSize==11){
            numberChars.add(",",3);
            numberChars.add(",",7);
            numberChars.add(".",11);
        }else if(numSize==10){
            numberChars.add(",",2);
            numberChars.add(",",6);
            numberChars.add(".",10);
        }else if(numSize==9){
            numberChars.add(",",1);
            numberChars.add(",",5);
            numberChars.add(".",9);
        }else if(numSize==8){
            numberChars.add(",",3);
            numberChars.add(".",7);
        }else if(numSize==7){
            numberChars.add(",",2);
            numberChars.add(".",6);
        }else if(numSize==6){
            numberChars.add(",",1);
            numberChars.add(".",5);
        }else if(numSize==2){
            numberChars.add("0.",0);
        }else if(numSize==1){
            numberChars.add("0.0",0);
        } else{
            numberChars.add(".",numSize-2);
        }
        numberChars.add("$",0);
        String money ="";

        int recombining = 0;
        while(recombining<numberChars.size()){
            if(numberChars.get(recombining)!=null){
                String tempMoney = money + numberChars.get(recombining);
                money = tempMoney;
                recombining++;
            }
        }
        return money;
    }

    public String betterConverter(float amount) throws ParseException {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);

        Object money = decimalFormat.parseObject(String.valueOf(amount));
        String currency = money.toString();

        return currency;
    }
}
