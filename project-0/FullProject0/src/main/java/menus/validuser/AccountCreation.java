package menus.validuser;

import DAOs.AccountsDAO;
import models.Accounts;
import models.Users;
import utils.ConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public interface AccountCreation extends AccountView{
    default void accountCreation(Users user){

        //code here to create an account
        System.out.println("Welcome to the account creation process!");

        Scanner accountCreate = new Scanner(System.in);

        System.out.println("What type of account would you like to create?\n" +
                "(1) Savings\n" +
                "(2) Checking\n" +
                "(3) Business");
        String accountType = accountCreate.nextLine();

        System.out.println("How much money would you like to deposit into your account to begin?" +
                "(Please enter whole numbers followed by a decimal point and two more numbers" +
                "Example: ####.##");
        float startingBalance = Float.parseFloat(accountCreate.nextLine());

        int bad =0;
        Accounts newAccount = new Accounts();
        newAccount.setAccount_id(-1);

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

        if(startingBalance>=0){
            float starting_Balance = startingBalance;
            newAccount.setBalance(starting_Balance);
        }else{
            System.out.println("Please enter a number that is greater than 0.");
            bad=1;
        }
        newAccount.setUser_id(user.getUser_id());


        if(bad<1) {
            try {
                Connection conn = ConnectionManager.getConnection();
                AccountsDAO addAccount = new AccountsDAO(conn);

                addAccount.updateAccounts(newAccount);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        accountView(user);
    }
}
