package menus.validuser;

import DAOs.AccountsDAO;
import models.Accounts;
import models.Users;
import utils.ConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountCreation {
    public void accountCreation(Users user){

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

        Accounts newAccount = new Accounts();
        newAccount.setAccount_id(-1);
        newAccount.setAccount_type(accountType);
        newAccount.setBalance(startingBalance);
        newAccount.setUser_id(user.getUser_id());


        try{
            Connection conn = ConnectionManager.getConnection();
            AccountsDAO addAccount = new AccountsDAO(conn);

            addAccount.updateAccounts(newAccount);
        }
        catch(SQLException | IOException e){
            e.printStackTrace();
        }

        new BankMenu().bankMenu(user);
    }
}
