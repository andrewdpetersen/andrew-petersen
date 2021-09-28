import DAOs.AccountsDAO;
import DAOs.UsersDAO;
import menus.OuterMenu;
import models.Accounts;
import models.Users;
import utils.ConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Driver {
    public static void main(String[] args){
        new OuterMenu().OuterMenu();

//        try{
//            Connection conn = ConnectionManager.getConnection();
//
//            Accounts testAccount = new Accounts();
//            testAccount.setAccount_type("Personal Checking");
//            testAccount.setBalance(0.00f);
//            testAccount.setUser_id(1);
//
//            AccountsDAO dao = new AccountsDAO(conn);
//            dao.updateAccounts(testAccount);
//        }
//        catch (SQLException | IOException e){
//            e.printStackTrace();
//        }



//        try{
//            Connection conn = ConnectionManager.getConnection();
//
//            UsersDAO dao = new UsersDAO(conn);
//            dao.updateUser(testUser);
//        }
//        catch (SQLException | IOException e){
//            e.printStackTrace();
//        }
    }
}
