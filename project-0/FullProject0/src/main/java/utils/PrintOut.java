package utils;

import models.Accounts;
import models.Transactions;
import models.Users;
import utils.formatValidation.CurrencyFormat;

import java.math.RoundingMode;
import java.math.BigDecimal;
import java.text.ParseException;

public class PrintOut extends CurrencyFormat {

    //This prints out a Users object
    public void printOut(Users user){
        System.out.println("User ID: "+ user.getUser_id()+"// Username: "+user.getUsername()+"\n" +
                "Name: "+user.getFirst_name()+" "+user.getLast_name());
    }

    //This prints out an Accounts object
    public void printOut(Accounts account){
        System.out.println("Account ID: "+account.getAccount_id()+"// " +
                account.getAccount_type()+" account\n" +
                "Current Balance: $"+account.getBalance());
    }

    //This prints out a properly formatted balance
    public void printOut(float balance){
        //print out in money form
        BigDecimal bal = new BigDecimal(balance).setScale(2,RoundingMode.HALF_UP);
        System.out.print("$"+bal);
    }


    public void printOut(Transactions transaction) throws ParseException {
        String type;
        if(transaction.isDeposit()){
            type = "Deposit";
        }else if(transaction.isWithdrawal()){
            type = "Withdrawal";
        }else{
            type = "Transfer to Account #"+transaction.getTransfer_to_account_id();
        }

        System.out.println("Transaction ID:"+transaction.getTransaction_id()+"// " +
                "Type of transaction:"+type+"" +"\n" +
                "Amount: $"+betterConverter(transaction.getTransaction_amount()));
    }
}
