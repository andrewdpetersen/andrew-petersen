package menus.validuser;

import DAOs.AccountsDAO;
import models.Accounts;
import models.Users;
import utils.CallAndResponse;
import utils.ConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * The AccountCreation interface contains one method, "accountCreation", that
 * takes a Users object as a parameter. It is used to take input from the console
 * and use that input to create a new Accounts object in the database.
 */
public interface AccountCreation extends AccountView, CallAndResponse {

    /**
     * The accountCreation method takes a Users object, "user", as an argument.
     * It also takes input from the console, and uses the combination of the input,
     * and the data in the fields of the instance "user" to create an instance of
     * the Accounts class. Then, after validation of the inputs, the method passes
     * the Accounts instance to the database using an AccountsDAO. Finally, it calls
     * the AccountView method.
     */
    default void accountCreation(Users user){

        //introduction
        System.out.println("Welcome to the account creation process!");

        //this section asks for console input and assigns it to the reference "accountType".
        String accountType = caller("What type of account would you like to create?\n" +
                "(1) Savings\n" +
                "(2) Checking\n" +
                "(3) Business");

        //this section asks for console input and assigns it to the reference "startingBalance".
        float startingBalance = Float.parseFloat(caller("How much money would you like to deposit into your account to begin?" +
                "(Please enter whole numbers followed by a decimal point and two more numbers" +
                "Example: ####.##)"));

        //The next 3 lines instantiate an Accounts object and set the Account_id
        //(also, they make an int and assign it to "bad").
        int bad =0;
        Accounts newAccount = new Accounts();
        newAccount.setAccount_id(-1);

        // This section checks the "accountType" variable and uses a Setter on the
        // account_type field of the Accounts object. If the input is not an expected
        // input, it also reassigns the bad variable to equal 1.
        if(accountType.equals("1")){
            String account_Type = "Savings";
            newAccount.setAccount_type(account_Type);
        }else if(accountType.equals("2")){
            String account_Type = "Checking";
            newAccount.setAccount_type(account_Type);
        }else if(accountType.equals("3")){
            String account_Type = "Business";
            newAccount.setAccount_type(account_Type);
        }else{
            System.out.println("Invalid account type selected");
            bad =1;
        }

        // This section validates that the startingBalance is not negative, and
        // sets the balance field of the Accounts object. If the input is not an expected
        // input, it also reassigns the bad variable to equal 1.
        if(startingBalance>=0){
            float starting_Balance = startingBalance;
            newAccount.setBalance(starting_Balance);
        }else{
            System.out.println("Please enter a number that is greater than 0.");
            bad=1;
        }

        //This sets the user_id field in the Accounts object, based on the user argument.
        newAccount.setUser_id(user.getUser_id());

        //this section checks if bad has changed, and executes if it has not changed (bad=0).
        if(bad<1) {
            try {
                Connection conn = ConnectionManager.getConnection();
                AccountsDAO addAccount = new AccountsDAO(conn);

                addAccount.updateAccounts(newAccount);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        //Finally, we run the accountView method with the "user" instance as an argument.
        accountView(user);
    }
}
