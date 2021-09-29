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

        MyArrayList<Integer> idList = new MyArrayList<>();

        System.out.print("Which account would you like to view, please type the number inside the ():");
        int i =0;
        while(i<accountList.size()-1){
            Accounts tempAccount = accountList.get(i);
            idList.add(tempAccount.getAccount_id());
            System.out.println("(" + tempAccount.getAccount_id() + ")" +
                    "; Type: " + tempAccount.getAccount_type() + "; " +
                    "Balance: $" + String.valueOf(tempAccount.getBalance()));
            i++;
        }

        String chooseAccount = accountScanner.nextLine();
        int s =0;
        while(s<idList.size()-1){
            if(chooseAccount.equals(s)){
                //go to AccountMenu
            }
            //else- invalid selection
        }
    }
}
