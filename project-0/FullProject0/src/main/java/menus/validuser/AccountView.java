package menus.validuser;

import DAOs.AccountsDAO;
import models.Accounts;
import models.Users;
import utils.ConnectionManager;
import utils.MyArrayList;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountView {
    public void accountView(Users user) throws SQLException, IOException {

        //code here to select and view one account
        Scanner accountScanner = new Scanner(System.in);
        int validID = user.getUser_id();
        Connection conn = ConnectionManager.getConnection();
        AccountsDAO accountListDAO = new AccountsDAO(conn);
        MyArrayList<Accounts> accountList = accountListDAO.getAccountsByUser(validID);

        System.out.print("Which account would you like to view?");

    }
}
