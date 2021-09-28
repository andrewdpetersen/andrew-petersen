package menus;

import DAOs.UsersDAO;
import models.Users;
import utils.ConnectionManager;
import utils.MyArrayList;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginMenu {
    public void loginMenu(){
        Scanner checker = new Scanner(System.in);

        System.out.print("Please enter your username:");
        String usernameCheck = checker.nextLine();

        System.out.print("Please enter your password:");
        String passwordCheck = checker.nextLine();

        try{
            Connection conn = ConnectionManager.getConnection();

            UsersDAO dao = new UsersDAO(conn);
            MyArrayList<Users> userList = dao.getUserList();

            MyArrayList<String> usernameList = new MyArrayList<>();
            int i=0;
            while(i<userList.size()-1){
                usernameList.add(userList.get(i).getUsername());
                i++;
            }

            boolean usernameExists = usernameList.contains(usernameCheck);
            if(usernameExists){
                Users checkingUser = userList.get(usernameList.find(usernameCheck));
                String nameCheck = checkingUser.getUsername();
                String passCheck = checkingUser.getPassword();
                if(nameCheck.equals(usernameCheck) && passCheck.equals(passwordCheck)){
                    System.out.print("This takes you to the Bank Portal");
                    //Bank portal
                    //Bank portal
                    //Bank portal
                }
                else{
                    System.out.print("Incorrect password");
                }
            }
            else{
                System.out.print("There is no user with that username!");
            }
        }
        catch (SQLException | IOException e){
            e.printStackTrace();
        }
    }
}
