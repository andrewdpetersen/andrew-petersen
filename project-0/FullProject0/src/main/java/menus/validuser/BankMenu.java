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
        Scanner bankScanner = new Scanner(System.in);
        System.out.println("Welcome, " + user.getUsername() +"!\n" +
                "(1) Display all accounts\n" +
                "(2) Create a new account\n" +
                "(3) View one of my accounts\n" +
                "(4) Logout");
        String bankSelection = bankScanner.nextLine();

        switch(bankSelection){
            //display accounts list and total balance
            case "1":
                new AccountOverview().accountOverview(user);
                break;
            case "2":
                new AccountCreation().accountCreation(user);
                break;
            //create new bank account

            //view account

            //logout-quit -> OuterMenu
        }
    }
}
