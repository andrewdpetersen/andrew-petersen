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

public class BankMenu extends LoginMenu {
    public void bankMenu(Users user) throws SQLException, IOException {

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

        //MENU OPTIONS
        //create new bank account
        //view account
        //logout-quit -> OuterMenu
    }
}
