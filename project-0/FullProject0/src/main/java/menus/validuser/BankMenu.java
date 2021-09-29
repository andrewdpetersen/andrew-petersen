package menus.validuser;

import DAOs.AccountsDAO;
import DAOs.UsersDAO;
import menus.LoginMenu;
import models.Accounts;
import models.Transactions;
import models.Users;
import utils.ConnectionManager;
import utils.MyArrayList;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class BankMenu extends LoginMenu {
    public void bankMenu(Users user) throws SQLException, IOException {

        //MENU OPTIONS

        //display accounts list
        Connection conn = ConnectionManager.getConnection();
        AccountsDAO dao = new AccountsDAO(conn);

        MyArrayList<Accounts> accountList = dao.getAccountsByUser(user.getUser_id());

        // ^^^Figure out a way to print the accountList^^^

        //display total balance
        float totalBalance = 0.0f;
        int i =0;
        while(i<accountList.size()-1){
            totalBalance+=accountList.get(i).getBalance();
            i++;
        }
        System.out.println("The total balance of all your accounts is: $" + totalBalance);

        //create new bank account
        Connection conn = ConnectionManager.getConnection();
        AccountsDAO dao = new AccountsDAO(conn);

        Accounts account = new Accounts();
        System.out.println("Please select the type of account you would like to create:\n" +
                "(1) Savings\n" +
                "(2) Checking\n" +
                "(3) Business");
        Scanner scan = new Scanner(System.in);
        String accountTypeSelection = scan.nextLine();

        if(accountTypeSelection.equals("1")){
            account.setAccount_type("Savings");
        }else if(accountTypeSelection.equals("2")){
            account.setAccount_type("Checking");
        }else if(accountTypeSelection.equals("3")){
            account.setAccount_type("Business");
        }else {
            //no account type selected
        }

        account.setBalance(0.0f);
        account.setUser_id(user.getUser_id());

        dao.updateAccounts(account);

        //view account

        //logout-quit -> OuterMenu

    }
}
