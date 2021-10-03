package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Accounts;
import utils.MyArrayList;

/**
 * The AccountsDAO class is used to access Accounts object data in the database.
 * The AccountsDAO class uses a Connection object, "conn". It contains a constructor method,
 * an updateAccounts method, a getAccountById method, and a getAccountsByUser method.
 */
public class AccountsDAO {
    private Connection conn;

    public AccountsDAO(Connection conn){this.conn = conn;}

    /**
     * The updateAccounts method takes an Accounts object as a parameter. It prepares
     * a PreparedStatement instance and uses the Connection to query the database.
     *
     * The results of this query determine the next step, either inserting a new row of data
     * into the database, or updating a current row in the database.
     */
    public void updateAccounts(Accounts account) throws SQLException {

        //The next 4 lines create and execute a PreparedStatement, and
        //then assign the result of the query to a ResultSet.
        String checkAccount = "SELECT * FROM accounts WHERE account_id = ?";
        PreparedStatement prepCheck = conn.prepareStatement(checkAccount);
        prepCheck.setInt(1,account.getAccount_id());
        ResultSet checkIfAccount = prepCheck.executeQuery();

        //If the result set is empty, the next if/then block inserts a new row into the database.
        if(!checkIfAccount.next()){
            String sqlNewAccount = "INSERT INTO accounts (account_type,balance,user_id)\n\n" +
                    "VALUES (?,?,?)";
            PreparedStatement prepNewAccount = conn.prepareStatement(sqlNewAccount);
            prepNewAccount.setString(1,account.getAccount_type());
            prepNewAccount.setFloat(2,account.getBalance());
            prepNewAccount.setInt(3,account.getUser_id());
            prepNewAccount.executeUpdate();
        }

        //If the ResultSet is not empty, the else block updates the corresponding
        //row in the database.
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

    /**
     * The getAccountById method takes an int as a parameter (the account_id)
     * and it returns an Accounts object. If the account in question does not have
     * corresponding data in the database, it returns an instance with default values
     * for each field.
     */
    public Accounts getAccountById(int account_id) throws SQLException {

        //The next 4 lines create and execute a PreparedStatement, and
        //then assign the result of the query to a ResultSet.
        String sqlGet = "SELECT * FROM accounts WHERE account_id = ?";
        PreparedStatement prepGet = conn.prepareStatement(sqlGet);
        prepGet.setInt(1, account_id);
        ResultSet results = prepGet.executeQuery();

        //instantiates a new Accounts object and assigns it to "getAccount".
        Accounts getAccount = new Accounts();

        /*
            The control flow statement checks if there is a row in the ResultSet
            and if it exists, sets the fields in the "getAccount" instance
            based on the data in the database.
        */
        if (results.next()) {
            getAccount.setAccount_id(account_id);
            getAccount.setAccount_type(results.getString(2));
            getAccount.setBalance(results.getFloat(3));
            getAccount.setUser_id(results.getInt(4));
        }
        return getAccount;
    }

    /**
     * The getAllAccounts method is not used and so is commented out.
     */
/*
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
*/

    /**
     * The getAccountsByUser method takes an int as a parameter (the user_id)
     * and returns a MyArrayList of all the accounts that have the given user_id
     * as their user_id field.
     */
    public MyArrayList<Accounts> getAccountsByUser(int user_id) throws SQLException {

        //The next 4 lines create and execute a PreparedStatement, and
        //then assign the result of the query to a ResultSet.
        String sqlGet = "SELECT * FROM accounts WHERE user_id = ?";
        PreparedStatement prepGet = conn.prepareStatement(sqlGet);
        prepGet.setInt(1,user_id);
        ResultSet getAccountByUser = prepGet.executeQuery();

        //instantiates a MyArrayList object and assigns it to "userAccountsList".
        MyArrayList<Accounts> userAccountsList = new MyArrayList<>();

        /*
        The while control flow statement runs while there is another row in the
        ResultSet. It instantiates an Accounts object, sets the fields in the object
        with data from the database, and then adds it to the "userAccountsList".
         */
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

    /**
     * The deleteAccount method is not used and is commented out.
     */
/*
    public void deleteAccount(int account_id) throws SQLException {
        //deletes the account with the given account_id
        String sqlDelete = "DELETE * FROM accounts WHERE account_id = ?";
        PreparedStatement prepDelete = conn.prepareStatement(sqlDelete);
        prepDelete.setInt(1,account_id);
        prepDelete.executeUpdate();
    }
 */
}
