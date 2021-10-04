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

/**
 * The AccountOverview class contains one method, "accountOverview", that takes a Users
 * object as a parameter. It uses this argument ("user") to print a list of all accounts
 * that are related to the user in the database, along with a total balance from all
 * the accounts.
 */
public class AccountOverview implements CurrencyFormat, BankMenu, AccountView{
    /**
     * The accountOverview method takes a Users object, "user" as a parameter.
     * It uses this argument ("user") to print a list of all accounts that
     * are related to the user in the database, along with a total balance from all
     * the accounts.
     */
    public void accountOverview(Users user){

        try {
            int validID = user.getUser_id();
            Connection conn = ConnectionManager.getConnection();
            AccountsDAO accountListDAO = new AccountsDAO(conn);

            //Assigns a MyArrayList containing Accounts objects to the
            //reference "accountList".
            MyArrayList<Accounts> accountList = accountListDAO.getAccountsByUser(validID);

            //This section prints out each Accounts instance from the database. It also
            //adds each balance to the "totalBalance" float.
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

            //prints out the total balance in currency format
            System.out.println("The total balance of all your accounts is: $" +
                    betterConverter(totalBalance));
        }
        catch (SQLException | IOException | ParseException e){
            e.printStackTrace();
        }

        //This section asks for console input
        String overviewMenu = caller("What would you like to do? +\n" +
                    "(1) View details for one of my accounts\n" +
                    "(2) Go back to the Bank Menu\n" +
                    "(3) Logout");

        //This control flow switch uses the console input from the previous section
        switch(overviewMenu){
            case "1":
                //runs the accountView method
                accountView(user);
                break;
            case "2":
                //runs the bankMenu method
                bankMenu(user);
                break;
            case "3":
                //runs the OuterMenu method
                new OuterMenu().OuterMenu(false);
                break;
        }
    }
}
