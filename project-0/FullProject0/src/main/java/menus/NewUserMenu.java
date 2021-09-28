package menus;

import DAOs.UsersDAO;
import models.Users;
import utils.ConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class NewUserMenu {
    public void newUserMenu(){

        Scanner createScan = new Scanner(System.in);

        System.out.print("Please enter a username and then press 'Enter':");
        String username1 = createScan.nextLine();

        System.out.print("Please enter a password and then press 'Enter':");
        String password1 = createScan.nextLine();

        System.out.print("Please enter your email and then press 'Enter':");
        String email1 = createScan.nextLine();

        System.out.print("Please enter your first (given) name and then press 'Enter':");
        String first_name1 = createScan.nextLine();

        System.out.print("Please enter your last (family) name and then press 'Enter':");
        String last_name1 = createScan.nextLine();

        Users addingUser = new Users();
        addingUser.setUsername(username1);
        addingUser.setPassword(password1);
        addingUser.setEmail(email1);
        addingUser.setFirst_name(first_name1);
        addingUser.setLast_name(last_name1);

        try{
            Connection conn = ConnectionManager.getConnection();

            UsersDAO dao = new UsersDAO(conn);
            dao.updateUser(addingUser);
        }
        catch (SQLException | IOException e){
            e.printStackTrace();
        }
    }
}
