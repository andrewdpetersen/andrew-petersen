package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Users;
import utils.MyArrayList;

public class UsersDAO {
    private Connection conn;

    public UsersDAO(Connection conn){this.conn = conn;}

    public void updateUser(Users user) throws SQLException {
        String checkUser = "SELECT * FROM users WHERE user_id = ?";
        PreparedStatement sqlCheckUser = conn.prepareStatement(checkUser);
        sqlCheckUser.setInt(1, user.getUser_id());
        ResultSet checkIfUser = sqlCheckUser.executeQuery();

        if (!checkIfUser.next()) {
            String sqlCreate = "INSERT INTO users (username,password,email,first_name,last_name)\n\n" +
                    "VALUES (?,?,?,?,?)";
            PreparedStatement sqlPstmt = conn.prepareStatement(sqlCreate);
            sqlPstmt.setString(1, user.getUsername());
            sqlPstmt.setString(2, user.getPassword());
            sqlPstmt.setString(3, user.getEmail());
            sqlPstmt.setString(4, user.getFirst_name());
            sqlPstmt.setString(5, user.getLast_name());
            sqlPstmt.executeUpdate();
        }
        else {
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
    }

    public Users getUserById(int user_id) throws SQLException{
        String sqlQuery = "SELECT * FROM users WHERE user_id = ?";
        PreparedStatement sqlPstmt = conn.prepareStatement(sqlQuery);
        sqlPstmt.setInt(1,user_id);
        ResultSet results = sqlPstmt.executeQuery();
        Users user = new Users();
        user.setUser_id(user_id);
        user.setUsername(results.getString(2));
        user.setPassword(results.getString(3));
        user.setEmail(results.getString(4));
        user.setFirst_name(results.getString(5));
        user.setLast_name(results.getString(6));
        return user;
    }

    public MyArrayList<Users> getUserList() throws SQLException{
        String sqlQuery = "SELECT * FROM users";
        PreparedStatement sqlPstmt = conn.prepareStatement(sqlQuery);
        ResultSet results = sqlPstmt.executeQuery();
        MyArrayList<Users> fullUserList = new MyArrayList<>();
        while(results.next()){
            Users nextUser = new Users();
            nextUser.setUser_id(results.getInt(1));
            nextUser.setUsername(results.getString(2));
            nextUser.setPassword(results.getString(3));
            nextUser.setEmail(results.getString(4));
            nextUser.setFirst_name(results.getString(5));
            nextUser.setLast_name(results.getString(6));
            fullUserList.add(nextUser);
        }
        return fullUserList;
    }

    public void deleteUser(int user_id) throws SQLException{
        String sqlDelete = "DELETE * FROM users WHERE user_id = ?";
        PreparedStatement sqlPstmt = conn.prepareStatement(sqlDelete);
        sqlPstmt.setInt(1,user_id);
        sqlPstmt.executeUpdate();
    }
}
