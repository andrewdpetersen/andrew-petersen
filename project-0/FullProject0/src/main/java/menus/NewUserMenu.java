package menus;

import DAOs.UsersDAO;
import models.Users;
import utils.CallAndResponse;
import utils.ConnectionManager;
import utils.formatValidation.EmailFormat;
import utils.formatValidation.NameFormat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class NewUserMenu extends CallAndResponse implements EmailFormat, NameFormat {
    public void newUserMenu(){


        //This section asks the user for input and sets 5 string references to be used later

        String username1 = caller("Please enter a username and then press 'Enter':");
        String password1 = caller("Please enter a password and then press 'Enter':");
        String email1 = caller("Please enter your email and then press 'Enter':");
        String first_name1 = caller("Please enter your first (given) name and then press 'Enter':");
        String last_name1 = caller("Please enter your last (family) name and then press 'Enter':");


        //Name and email validation section

        boolean goodEmail = emailChecker(email1);

        //Making sure the first letter of first_name is automatically capitalized

        String firstFirst = first_name1.substring(0,1);
        String restFirst = first_name1.substring(1);
        firstFirst.toUpperCase();
        String First_name1 = firstFirst.concat(restFirst);

        boolean goodFirstName = firstNameChecker(First_name1);
        boolean goodLastName = lastNameChecker(last_name1);


        //Email validation using regex in the EmailFormat interface

        if(!goodEmail){
            System.out.println("The email you entered is not in a valid email format");
        }

        //Name validation using regex in the NameFormat interface

        if(!goodFirstName|!goodLastName){
            System.out.println("Please make sure you are typing your name without any numbers" +
                    "or special characters.");
        }

        //If names and email are validated, add the user to our DB.

        else {
            Users addingUser = new Users();
            addingUser.setUsername(username1);
            addingUser.setPassword(password1);
            addingUser.setEmail(email1);
            addingUser.setFirst_name(First_name1);
            addingUser.setLast_name(last_name1);

            try {
                Connection conn = ConnectionManager.getConnection();

                UsersDAO dao = new UsersDAO(conn);
                dao.updateUser(addingUser);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
