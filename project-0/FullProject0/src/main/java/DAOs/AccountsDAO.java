package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Accounts;
import utils.MyArrayList;

public class AccountsDAO {
    private Connection conn;

    public AccountsDAO(Connection conn){this.conn = conn;}

    public void updateAccounts(Accounts account) throws SQLException {
        //create and update a row in accounts table
        String checkAccount = "SELECT * FROM accounts WHERE account_id = ?";
        PreparedStatement prepCheck = conn.prepareStatement(checkAccount);
        prepCheck.setInt(1,account.getAccount_id());
        ResultSet checkIfAccount = prepCheck.executeQuery();

        if(!checkIfAccount.next()){
            String sqlNewAccount = "INSERT INTO accounts (account_type,balance,user_id)\n\n" +
                    "VALUES (?,?,?)";
            PreparedStatement prepNewAccount = conn.prepareStatement(sqlNewAccount);
            prepNewAccount.setString(1,account.getAccount_type());
            prepNewAccount.setFloat(2,account.getBalance());
            prepNewAccount.setInt(3,account.getUser_id());
            prepNewAccount.executeUpdate();
        }
        else{
            String sqlUpdate = "UPDATE accounts SET account_type=?, " +
                    "balance=?, user_id=? WHERE account_id = ?;";

            PreparedStatement prepUpdateAll = conn.prepareStatement(sqlUpdate);
            prepUpdateAll.setString(1,account.getAccount_type());
            prepUpdateAll.setFloat(2,account.getBalance());
            prepUpdateAll.setInt(3,account.getUser_id());
            prepUpdateAll.setInt(4,account.getAccount_id());
            prepUpdateAll.executeUpdate();
        }
    }

    public Accounts getAccountById(int account_id) throws SQLException {
        String sqlGet = "SELECT * FROM accounts WHERE account_id = ?";
        PreparedStatement prepGet = conn.prepareStatement(sqlGet);
        prepGet.setInt(1, account_id);
        ResultSet results = prepGet.executeQuery();
        Accounts getAccount = new Accounts();
        if (results.next()) {
            getAccount.setAccount_id(account_id);
            getAccount.setAccount_type(results.getString(2));
            getAccount.setBalance(results.getFloat(3));
            getAccount.setUser_id(results.getInt(4));
        }
        return getAccount;
    }

    public MyArrayList<Accounts> getAllAccounts() throws SQLException {
        //returns a list of all the accounts table
        String sqlGetAll = "SELECT * FROM accounts";
        PreparedStatement prepGetAll = conn.prepareStatement(sqlGetAll);
        ResultSet getAll = prepGetAll.executeQuery();

        MyArrayList<Accounts> fullAccountsList = new MyArrayList<>();
        while(getAll.next()){
            Accounts nextAccount = new Accounts();
            nextAccount.setAccount_id(getAll.getInt(1));
            nextAccount.setAccount_type(getAll.getString(2));
            nextAccount.setBalance(getAll.getFloat(3));
            nextAccount.setUser_id(getAll.getInt(4));
            fullAccountsList.add(nextAccount);
        }
        return fullAccountsList;
    }

    public MyArrayList<Accounts> getAccountsByUser(int user_id) throws SQLException {
        //returns all the accounts from a given user
        String sqlGet = "SELECT * FROM accounts WHERE user_id = ?";
        PreparedStatement prepGet = conn.prepareStatement(sqlGet);
        prepGet.setInt(1,user_id);
        ResultSet getAccountByUser = prepGet.executeQuery();

        MyArrayList<Accounts> userAccountsList = new MyArrayList<>();

        while(getAccountByUser.next()){
            Accounts nextUserAccount = new Accounts();
            nextUserAccount.setAccount_id(getAccountByUser.getInt(1));
            nextUserAccount.setAccount_type(getAccountByUser.getString(2));
            nextUserAccount.setBalance(getAccountByUser.getFloat(3));
            nextUserAccount.setUser_id(getAccountByUser.getInt(4));
            userAccountsList.add(nextUserAccount);
        }
        return userAccountsList;
    }

    public void deleteAccount(int account_id) throws SQLException {
        //deletes the account with the given account_id
        String sqlDelete = "DELETE * FROM accounts WHERE account_id = ?";
        PreparedStatement prepDelete = conn.prepareStatement(sqlDelete);
        prepDelete.setInt(1,account_id);
        prepDelete.executeUpdate();
    }
}
