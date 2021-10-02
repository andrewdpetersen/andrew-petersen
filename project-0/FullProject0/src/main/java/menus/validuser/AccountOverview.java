package menus.validuser;

import DAOs.AccountsDAO;
import menus.OuterMenu;
import models.Accounts;
import models.Users;
import utils.ConnectionManager;
import utils.MyArrayList;
import utils.formatValidation.CurrencyFormat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class AccountOverview implements CurrencyFormat, BankMenu, AccountView{
    public void accountOverview(Users user){

        try {
            int validID = user.getUser_id();


            Connection conn = ConnectionManager.getConnection();
            AccountsDAO accountListDAO = new AccountsDAO(conn);

            MyArrayList<Accounts> accountList = accountListDAO.getAccountsByUser(validID);

            int i = 0;
            float totalBalance = 0.0f;
            while (i < accountList.size()) {
                Accounts tempAccount = accountList.get(i);
                if(tempAccount!=null) {
                    System.out.println("Account ID: " + tempAccount.getAccount_id() + "\n" +
                            "Account Type: " + tempAccount.getAccount_type() + "\n" +
                            "Balance: $" + betterConverter(tempAccount.getBalance()));
                    totalBalance += tempAccount.getBalance();
                }
                i++;
            }
            System.out.println("The total balance of all your accounts is: $" +
                    betterConverter(totalBalance));
        }
        catch (SQLException | IOException | ParseException e){
            e.printStackTrace();
        }

        Scanner overviewScanner = new Scanner(System.in);
        System.out.println("What would you like to do?\n" +
                "(1) View details for one of my accounts\n" +
                "(2) Go back to the Bank Menu\n" +
                "(3) Logout");

        String overviewMenu = overviewScanner.nextLine();
        switch(overviewMenu){
            case "1":
                accountView(user);
                break;
            case "2":
                bankMenu(user);
                break;
            case "3":
                new OuterMenu().OuterMenu();
                break;
        }
    }
}
