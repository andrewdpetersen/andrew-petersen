package menus.validuser;

import DAOs.AccountsDAO;
import menus.OuterMenu;
import models.Accounts;
import models.Users;
import utils.ConnectionManager;
import utils.formatValidation.CurrencyFormat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class AccountMenu extends CurrencyFormat {
    public void accountMenu(Users user, int account_id){



        try {
            Connection conn = ConnectionManager.getConnection();
            AccountsDAO accountListDAO = new AccountsDAO(conn);

            Accounts featureAccount = accountListDAO.getAccountById(account_id);

            System.out.println("Account ID: "+ account_id);
            System.out.println("Account Type: " + featureAccount.getAccount_type());
            System.out.println("Balance: $"+ betterConverter(featureAccount.getBalance()));
            System.out.println("User ID: "+featureAccount.getUser_id());
        }
        catch(SQLException | IOException | ParseException e){
            e.printStackTrace();
        }

        Scanner choiceScanner = new Scanner(System.in);
        System.out.println("(1) Deposit funds into this account\n" +
                "(2) Withdraw funds from this account\n" +
                "(3) Transfer funds from this account to a different account\n" +
                "(4) Return to Bank Menu\n" +
                "(5) Logout\n" +
                "Please make a selection to continue:");
        String choice = choiceScanner.nextLine();

        switch(choice){
            case "1":
                //run deposit script
                Scanner depositScanner = new Scanner(System.in);
                System.out.println("How much would you like to deposit?");
                float depositAmount = Float.parseFloat(depositScanner.nextLine());
                if(depositAmount>=0){
                    try{
                        Connection conn = ConnectionManager.getConnection();
                        AccountsDAO accountListDAO = new AccountsDAO(conn);

                        Accounts featureAccount = accountListDAO.getAccountById(account_id);

                        float oldBalance = featureAccount.getBalance();
                        float newBalance = oldBalance+depositAmount;
                        featureAccount.setBalance(newBalance);

                        accountListDAO.updateAccounts(featureAccount);

                        new AccountMenu().accountMenu(user,account_id);
                    }
                    catch (SQLException|IOException e){
                        e.printStackTrace();
                    }
                }
                else{
                    System.out.println("You cannot make a negative deposit");
                }
                break;

            case "2":

                //run withdrawal script
                Scanner withdrawalScanner = new Scanner(System.in);
                System.out.println("How much would you like to withdraw?");
                float withdrawalAmount = Float.parseFloat(withdrawalScanner.nextLine());

                if(withdrawalAmount>=0) {


                    try {
                        Connection conn = ConnectionManager.getConnection();
                        AccountsDAO accountListDAO = new AccountsDAO(conn);

                        Accounts featureAccount = accountListDAO.getAccountById(account_id);

                        float oldBalance = featureAccount.getBalance();
                        float newBalance = oldBalance - withdrawalAmount;
                        featureAccount.setBalance(newBalance);

                        accountListDAO.updateAccounts(featureAccount);

                        new AccountMenu().accountMenu(user, account_id);
                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("You cannot make a negative withdrawal");
                }
                break;

            case "3":

                //run transfer script

                break;
            case "4":
                new BankMenu().bankMenu(user);
                break;
            case "5":
                new OuterMenu().OuterMenu();
                break;
        }
    }
}
