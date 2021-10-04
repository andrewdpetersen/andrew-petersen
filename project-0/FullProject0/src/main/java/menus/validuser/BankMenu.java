package menus.validuser;

import menus.OuterMenu;
import models.Users;
import java.util.Scanner;

/**
 * The interface BankMenu contains one method, "bankMenu", displays a list of options
 * and asks for console input. Based on the input, it runs a variety of different methods.
 */
public interface BankMenu extends AccountView, AccountCreation{
    /**
     * The bankMenu method displays a list of options and asks for console input. Based
     * on the input, it runs a variety of different methods.
     */
    default void bankMenu(Users user){

        //Asks for console input and assigns it to "bankSelection"
        boolean validUser =true;
        while(validUser){
            String bankSelection = caller("Welcome, " + user.getUsername() +"!\n" +
                    "(1) Display all accounts\n" +
                    "(2) Create a new account\n" +
                    "(3) View one of my accounts\n" +
                    "(4) Logout");

            //The control flow switch takes "bankSelection" as an argument
            switch(bankSelection) {
                case "1":
                    //calls the accountOverview method
                    validUser = false;
                    new AccountOverview().accountOverview(user);
                    break;

                case "2":
                    //calls the accountCreation method
                    validUser = false;
                    accountCreation(user);
                    break;

                case "3":
                    //calls the accountView method
                    validUser = false;
                    accountView(user);
                    break;

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
