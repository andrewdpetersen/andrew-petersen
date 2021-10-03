package menus.validuser;

import menus.OuterMenu;

import models.Users;

import java.util.Scanner;

public interface BankMenu extends AccountView, AccountCreation{
    default void bankMenu(Users user){
        boolean validUser =true;
        //MENU OPTIONS
        while(validUser){
            Scanner bankScanner = new Scanner(System.in);
            System.out.println("Welcome, " + user.getUsername() +"!\n" +
                    "(1) Display all accounts\n" +
                    "(2) Create a new account\n" +
                    "(3) View one of my accounts\n" +
                    "(4) Logout");
            String bankSelection = bankScanner.nextLine();

            switch(bankSelection) {
                //display accounts list and total balance
                case "1":
                    new AccountOverview().accountOverview(user);
                    break;
                //create new bank account
                case "2":
                    accountCreation(user);
                    break;
                //view one specific account
                case "3":
                    accountView(user);
                    break;
                //logout
                case "L":
                case "l":
                case "4":
                    validUser = false;
                    new OuterMenu().OuterMenu(false);
                    break;
            }
        }
    }
}
