package utils;

import models.Accounts;
import models.Transactions;
import models.Users;
import utils.formatValidation.CurrencyFormat;
import java.text.ParseException;

/**
 * The PrintOut interface is used to print data to the console in a more
 * well-designed format. It is a beautification interface and has a printOut method.
 * The printOut method can take Users, Accounts, Transactions, or float data types. It
 * will format the output differently for each type.
 */
public interface PrintOut extends CurrencyFormat {

    /**
     * This prints out a formatted Users instance in the console
     */
    default void printOut(Users user){
        System.out.println("User ID: "+ user.getUser_id()+"// Username: "+user.getUsername()+"\n" +
                "Name: "+user.getFirst_name()+" "+user.getLast_name());
    }

    /**
     * This prints out a formatted Accounts instance in the console
     */
    default void printOut(Accounts account) throws ParseException {
        System.out.println( "("+account.getAccount_id()+") " +
                account.getAccount_type()+" account\n" +
                "Current Balance: $"+betterConverter(account.getBalance()));
    }

    /**
     * This prints out a formatted Transactions instance in the console
     */
    default void printOut(Transactions transaction) throws ParseException {
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
