package menus.validuser;

import DAOs.AccountsDAO;
import models.Accounts;
import models.Users;
import utils.ConnectionManager;
import utils.MyArrayList;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AccountOverview {
    public void accountOverview(Users user) throws SQLException, IOException {

        int validID = user.getUser_id();
        Connection conn = ConnectionManager.getConnection();
        AccountsDAO accountListDAO = new AccountsDAO(conn);

        MyArrayList<Accounts> accountList = accountListDAO.getAccountsByUser(validID);

        int i=0;
        float totalBalance = 0.0f;
        while(i<accountList.size()-1){
            Accounts tempAccount = accountList.get(i);
            System.out.println("Account ID: " + tempAccount.getAccount_id() +"\n" +
                    "Account Type: " + tempAccount.getAccount_type() +"\n" +
                    "Balance: " + String.valueOf(tempAccount.getBalance()));
            totalBalance+=tempAccount.getBalance();
            i++;
        }
        System.out.println("The total balance of all your accounts is: $"+
                String.valueOf(totalBalance));

        //code here to go back to BankMenu
        //code here to go into AccountView
        //code here to logout
    }
}
