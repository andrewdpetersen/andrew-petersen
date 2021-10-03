package menus;

import java.util.Scanner;

/**
 * The OuterMenu class is a class with a single method. The method calls for user
 * input and calls a different method or closes the app based on the input.
 */
public class OuterMenu implements LoginMenu, NewUserMenu{

    /**
     * The OuterMenu method takes a boolean parameter, "outer", and runs a while loop
     * while "outer" is true. It asks for input from the console, and runs a switch based
     * on the input.
     */
    public void OuterMenu(boolean outer){

        //a control flow while loop that runs as long as the boolean "outer" is true.
        while (outer) {
            Scanner menuScan = new Scanner(System.in);

            System.out.print("########## OUTER PORTAL ##########\n" +
                    "(1) Returning User- Login\n" +
                    "(2) New User- Create Username\n" +
                    "(3) Quit- Exit\n" +
                    "##################################\n" +
                    "Please make a selection and press 'Enter':");
            String outerInput = menuScan.nextLine();

            //a control flow switch based on the choice read by the scanner
            switch (outerInput) {
                case "1":
                    //runs the loginPortal method.
                    loginPortal();
                    break;
                case "2":
                    //runs the newUserMenu method.
                    newUserMenu();
                    break;
                case "3":
                case "Q":
                case "q":
                    //exits the app
                    outer = false;
                    break;
                default:
                    System.out.println("You entered an invalid selection! Please try again.");
                    break;
            }
        }
    }
}
