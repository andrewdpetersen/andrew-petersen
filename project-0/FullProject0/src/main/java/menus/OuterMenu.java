package menus;

import java.util.Scanner;

/**
 * The OuterMenu class is a class with a single method. The method calls for user
 * input and calls a different method or closes the app based on the input.
 */
public class OuterMenu implements LoginMenu, NewUserMenu{
    public void OuterMenu(boolean outer){
        boolean running = outer;

        while (running) {
            Scanner menuScan = new Scanner(System.in);

            System.out.print("########## OUTER PORTAL ##########\n" +
                    "(1) Returning User- Login\n" +
                    "(2) New User- Create Username\n" +
                    "(3) Quit- Exit\n" +
                    "##################################\n" +
                    "Please make a selection and press 'Enter':");
            String outerInput = menuScan.nextLine();

            switch (outerInput) {
                case "1":
                    loginPortal();
                    break;
                case "2":
                    newUserMenu();
                    break;
                case "3":
                case "Q":
                case "q":
                    outer = false;
                    break;
                default:
                    System.out.println("You entered an invalid selection! Please try again.");
                    break;
            }
        }
    }
}
