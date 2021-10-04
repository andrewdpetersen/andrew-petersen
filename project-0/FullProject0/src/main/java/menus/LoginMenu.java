package menus;

import DAOs.UsersDAO;
import menus.validuser.BankMenu;
import models.Users;
import utils.CallAndResponse;
import utils.ConnectionManager;
import utils.MyArrayList;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * The LoginMenu interface contains one method, "loginPortal". It is used
 * for checking console input against user data in the database, and either
 * exiting to the loop in the OuterMenu or continuing to the BankMenu.
 */
public interface LoginMenu extends BankMenu, CallAndResponse {
    /**
     * The loginPortal method asks for input from the console, and then uses
     * that input to compare to data in the database. If the data matches the input,
     * it continues to the BankMenu via the BankMenu method. Otherwise, it prints a
     * message into the console.
     */
    default void loginPortal(){
        Scanner checker = new Scanner(System.in);

        //The next 2 lines ask for input from the console and assign the input to 2 references,
        // "userNameCheck" and "passwordCheck".
        String usernameCheck = caller("Please enter your username:");
        String passwordCheck = caller("Please enter your password:");

        try{
            Connection conn = ConnectionManager.getConnection();

            // This block instantiates a UsersDAO, and uses the getUserList method
            // to return a MyArrayList filled with Users objects.
            UsersDAO dao = new UsersDAO(conn);
            MyArrayList<Users> userList = dao.getUserList();

            // This instantiates a MyArrayList of Strings, called "usernameList"
            MyArrayList<String> usernameList = new MyArrayList<>();

            // The while loop adds all usernames as Strings to the "usernameList".
            int i=0;
            while(i<userList.size()-1){
                Users findTheUser = userList.get(i);
                if(findTheUser!=null) {
                    String addUserName = findTheUser.getUsername();
                    usernameList.add(addUserName);
                }
                i++;
            }

            //True if the username is in the database.
            boolean usernameExists = usernameList.contains(usernameCheck);

            //runs if the username is in the database
            if(usernameExists){

                //finds the user with the corresponding username
                Users checkingUser = userList.get(usernameList.find(usernameCheck));
                String nameCheck = checkingUser.getUsername();
                String passCheck = checkingUser.getPassword();

                //If the username and password match the data in the database,
                //runs the bankMenu method with the matching Users instance as an argument.
                if(nameCheck.equals(usernameCheck) && passCheck.equals(passwordCheck)){
                    bankMenu(checkingUser);
                }
                else{
                    //message if the incorrect password is entered
                    System.out.println("Incorrect password");
                }
            }
            else{
                //message if the username is not in the database
                System.out.println("There is no user with that username!");
            }
        }
        catch (SQLException | IOException e){
            e.printStackTrace();
        }
    }
}
