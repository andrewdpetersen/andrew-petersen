package menus.validuser;

import DAOs.AccountsDAO;
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
 * The interface AccountView contains one method, "accountView", that prints a series
 * of Accounts instances, asks for a console input to select one of the instances and then
 * continues by using the input as an argument in the accountMenu method.
 */
public interface AccountView extends CurrencyFormat {
    /**
     * The accountView method prints a series of Accounts instances, asks for a
     * console input to select one of the instances and then continues by using
     * the input as an argument in the accountMenu method.
     */
    default void accountView(Users user){


        try {
            Scanner accountScanner = new Scanner(System.in);
            int validID = user.getUser_id();
            Connection conn = ConnectionManager.getConnection();
            AccountsDAO accountListDAO = new AccountsDAO(conn);

            //This line uses a DAO to assign a reference that contains a list of
            //all accounts for the given "user" instance.
            MyArrayList<Accounts> accountList = accountListDAO.getAccountsByUser(validID);

            //instantiates a MyArrayList object that contains Integer objects
            MyArrayList<Integer> idList = new MyArrayList<>();

            //This section prints each Accounts instance with its data
            int i = 0;
            while (i < accountList.size()) {
                Accounts tempAccount = accountList.get(i);
                if (tempAccount != null) {
                    idList.add(tempAccount.getAccount_id());
                    System.out.println("(" + tempAccount.getAccount_id() + ")" +
                            "; Type: " + tempAccount.getAccount_type() + "; " +
                            "Balance: $"+ betterConverter(tempAccount.getBalance()));
                }
                i++;
            }

            //Asks for console input
            System.out.println("Which account would you like to view, please type the number inside the ():");
            int chooseAccount = Integer.valueOf(accountScanner.nextLine());

            //Checks if the input was one of the available accounts, and calls the accountMenu
            //method if the input was valid.
            if(idList.contains(chooseAccount)){
                new AccountMenu().accountMenu(user, chooseAccount);
            }

            //Prints a message if the input was not an available account
            else{
                System.out.println("That selection does not correspond to an account you" +
                        "have access to.");
            }
        }
        catch (SQLException | IOException | ParseException e){
            e.printStackTrace();
        }
    }
}
