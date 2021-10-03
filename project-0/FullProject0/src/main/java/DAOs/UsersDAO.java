package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Users;
import utils.MyArrayList;

public class UsersDAO {

    //the UsersDAO class has a private field with a connection object.
    private Connection conn;

    //A constructor that takes one parameter, a Connection object
    public UsersDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * The updateUser method takes a Users object as a parameter. It prepares a SQL
     * statement and then queries the database using the Connection object, "conn".
     * It then examines the ResultSet and if the user's data matches that from the row of
     * the result set, it updates the user's data in the database with another prepared
     * statement. If the user's data does not match, it uses another PreparedStatement to
     * insert the user's data as a new row in the database.
     */
    public void updateUser(Users user) {

        try {

            //creates a string
            String checkUser = "SELECT * FROM users WHERE user_id = ?";

            //takes the String checkUser and changes it to a PreparedStatement
            PreparedStatement sqlCheckUser = conn.prepareStatement(checkUser);

            /* Gets the User_id field from the Users object "user" and sets it as the parameter
            *  in the PreparedStatement "sqlCheckUser"
            */
            sqlCheckUser.setInt(1, user.getUser_id());

            //Executes the PreparedStatement and assigns it to the ResultSet "checkIfUser".
            ResultSet checkIfUser = sqlCheckUser.executeQuery();

            /*  Now we use a control flow if/else to check if the data from "user" exists
                in the ResultSet. If not, we insert the user in the database. If it exists,
                we update the row in the database that corresponds with the user.
            */
            if (!checkIfUser.next()) {

                /*
                This block of code creates a PreparedStatement using the fields in the
                "user" instance and inserts the data into the database.
                 */

                String sqlCreate = "INSERT INTO users (username,password,email,first_name,last_name)\n\n" +
                        "VALUES (?,?,?,?,?)";
                PreparedStatement sqlPstmt = conn.prepareStatement(sqlCreate);
                sqlPstmt.setString(1, user.getUsername());
                sqlPstmt.setString(2, user.getPassword());
                sqlPstmt.setString(3, user.getEmail());
                sqlPstmt.setString(4, user.getFirst_name());
                sqlPstmt.setString(5, user.getLast_name());
                sqlPstmt.executeUpdate();
            } else {

                /*
                This block of code creates a prepared statement using the fields in the
                "user" instance and updates the corresponding data in the database.
                 */
                String sqlUsername = "UPDATE users SET username = ?;";
                String sqlPassword = "UPDATE users SET password = ?;";
                String sqlEmail = "UPDATE users SET email = ?;";
                String sqlFirst_Name = "UPDATE users SET first_name = ?;";
                String sqlLast_Name = "UPDATE users SET last_name = ?;";

                String sqlUpdateAll = sqlUsername + sqlPassword + sqlEmail + sqlFirst_Name + sqlLast_Name;
                PreparedStatement sqlUpdateStmt = conn.prepareStatement(sqlUpdateAll);
                sqlUpdateStmt.setString(1, user.getUsername());
                sqlUpdateStmt.setString(2, user.getPassword());
                sqlUpdateStmt.setString(3, user.getEmail());
                sqlUpdateStmt.setString(4, user.getFirst_name());
                sqlUpdateStmt.setString(5, user.getLast_name());
                sqlUpdateStmt.executeUpdate();
            }

            //the catch statement below catches and handles any SQLException objects.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Below, the getUserById method is never used in my application, and
     * so it is commented out.
     */
/*
    public Users getUserById(int user_id) {

        try {
            String sqlQuery = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement sqlPstmt = conn.prepareStatement(sqlQuery);
            sqlPstmt.setInt(1, user_id);
            ResultSet results = sqlPstmt.executeQuery();

            Users user = new Users();
            user.setUser_id(user_id);
            user.setUsername(results.getString(2));
            user.setPassword(results.getString(3));
            user.setEmail(results.getString(4));
            user.setFirst_name(results.getString(5));
            user.setLast_name(results.getString(6));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            Users user = new Users();
            return user;
        }
    }
*/

    /**
     * The getUserList method below returns a MyArrayList collection object.
     * This collection contains Users objects, and it sets data to the fields
     * of each object based on the data contained in the database.
     */
    public MyArrayList<Users> getUserList() {

        try {
            //The next 3 lines create and execute a PreparedStatement, assigning the output
            //to a ResultSet
            String sqlQuery = "SELECT * FROM users";
            PreparedStatement sqlPstmt = conn.prepareStatement(sqlQuery);
            ResultSet results = sqlPstmt.executeQuery();

            //instantiates a MyArrayList collection object
            MyArrayList<Users> fullUserList = new MyArrayList<>();

            //The control flow while loop runs as long as there is another row in the
            //ResultSet.
            while (results.next()) {

                //The next 8 lines instantiate a new Users object, "nextUser", sets the fields
                //of nextUser with data from the ResultSet, and adds it to the collection.
                Users nextUser = new Users();
                nextUser.setUser_id(results.getInt(1));
                nextUser.setUsername(results.getString(2));
                nextUser.setPassword(results.getString(3));
                nextUser.setEmail(results.getString(4));
                nextUser.setFirst_name(results.getString(5));
                nextUser.setLast_name(results.getString(6));
                fullUserList.add(nextUser);
            }
            //we return the MyArrayList object, "fullUserList"
            return fullUserList;

        } catch (SQLException e) {
            e.printStackTrace();
            MyArrayList<Users> userList = new MyArrayList<>();
            return userList;
        }
    }

    /**
     * Below, the deleteUser method is never used in my application, and
     * so it is commented out.
     */

    /*
    public void deleteUser(int user_id) {
        try {
            String sqlDelete = "DELETE * FROM users WHERE user_id = ?";
            PreparedStatement sqlPstmt = conn.prepareStatement(sqlDelete);
            sqlPstmt.setInt(1, user_id);
            sqlPstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
