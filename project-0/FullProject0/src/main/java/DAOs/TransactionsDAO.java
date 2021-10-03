package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Transactions;
import utils.MyArrayList;

/**
 * The TransactionsDAO class is used to access Transactions object data in the database.
 * The TransactionsDAO class uses a Connection object, "conn". It contains a constructor method,
 * an updateTransaction method, and a getAllAccountTransactions method.
 */
public class TransactionsDAO {
    private Connection conn;

    public TransactionsDAO(Connection conn){this.conn = conn;}

    /**
     * The updateTransaction method takes a Transactions object as a parameter. It
     * prepares a PreparedStatement instance and uses the Connection to query the database.
     *
     * The results of this query determine the next step, either inserting a new row of
     * data into the database, or updating a current row in the database.
     */
    public void updateTransaction(Transactions tran) throws SQLException {

        //The next 4 lines create and execute a PreparedStatement, and
        //then assign the result of the query to a ResultSet.
        String checkTransaction = "SELECT * FROM transactions WHERE transaction_id = ?";
        PreparedStatement prepCheckTrans = conn.prepareStatement(checkTransaction);
        prepCheckTrans.setInt(1,tran.getTransaction_id());
        ResultSet tranExists = prepCheckTrans.executeQuery();

        //If the result set is empty, the next if/then block inserts a new row into the database.
        if(!tranExists.next()){
            String newTransaction = "INSERT INTO transactions \n\n" +
                    "(deposit,withdrawal,transfer,transfer_to_account_id,transaction_amount,account_id)\n\n" +
                    "VALUES (?,?,?,?,?,?)";
            PreparedStatement addTransaction = conn.prepareStatement(newTransaction);
            addTransaction.setBoolean(1,tran.isDeposit());
            addTransaction.setBoolean(2,tran.isWithdrawal());
            addTransaction.setBoolean(3,tran.isTransfer());
            addTransaction.setInt(4,tran.getTransfer_to_account_id());
            addTransaction.setFloat(5,tran.getTransaction_amount());
            addTransaction.setInt(6,tran.getAccount_id());
            addTransaction.executeUpdate();
        }

        //If the ResultSet is not empty, the else block updates the corresponding
        //row in the database.
        else{
            String updateDeposit = "UPDATE transactions SET deposit = ?;";
            String updateWithdrawal = "UPDATE transactions SET withdrawal = ?;";
            String updateTransfer = "UPDATE transactions SET transfer = ?;";
            String updateTransferId = "UPDATE transactions SET transfer_to_account_id = ?;";
            String updateAccountId = "UPDATE transactions SET account_id = ?;";

            String updateAll = updateDeposit+updateWithdrawal+updateTransfer+updateTransferId+updateAccountId;
            PreparedStatement prepUpdateAll = conn.prepareStatement(updateAll);
            prepUpdateAll.setBoolean(1,tran.isDeposit());
            prepUpdateAll.setBoolean(2,tran.isWithdrawal());
            prepUpdateAll.setBoolean(3,tran.isTransfer());
            prepUpdateAll.setInt(4,tran.getTransfer_to_account_id());
            prepUpdateAll.setFloat(5,tran.getTransaction_amount());
            prepUpdateAll.setInt(6,tran.getAccount_id());
            prepUpdateAll.executeUpdate();
        }
    }
/*
    public Transactions getTransactionById(int transaction_id) throws SQLException {
        //returns a transaction with the given transaction_id
        String sqlTransaction = "SELECT * FROM transactions WHERE transaction_id = ?";
        PreparedStatement prepTransaction = conn.prepareStatement(sqlTransaction);
        prepTransaction.setInt(1,transaction_id);
        ResultSet getTransaction = prepTransaction.executeQuery();

        Transactions newTransaction = new Transactions();
        newTransaction.setTransaction_id(getTransaction.getInt(1));
        newTransaction.setDeposit(getTransaction.getBoolean(2));
        newTransaction.setWithdrawal(getTransaction.getBoolean(3));
        newTransaction.setTransfer(getTransaction.getBoolean(4));
        newTransaction.setTransfer_to_account_id(getTransaction.getInt(5));
        newTransaction.setTransaction_amount(getTransaction.getFloat(6));
        newTransaction.setAccount_id(getTransaction.getInt(7));
        return newTransaction;
    }
*/

/*
    public MyArrayList<Transactions> getAllTransactions() throws SQLException {
        //returns all transactions
        String sqlAllTrans = "SELECT * FROM transactions";
        PreparedStatement prepAllTrans = conn.prepareStatement(sqlAllTrans);
        ResultSet allTransactions = prepAllTrans.executeQuery();

        MyArrayList<Transactions> transactionList = new MyArrayList<>();
        while(allTransactions.next()){
            Transactions nextTransaction = new Transactions();
            nextTransaction.setTransaction_id(allTransactions.getInt(1));
            nextTransaction.setDeposit(allTransactions.getBoolean(2));
            nextTransaction.setWithdrawal(allTransactions.getBoolean(3));
            nextTransaction.setTransfer(allTransactions.getBoolean(4));
            nextTransaction.setTransfer_to_account_id(allTransactions.getInt(5));
            nextTransaction.setTransaction_amount(allTransactions.getFloat(6));
            nextTransaction.setAccount_id(allTransactions.getInt(7));
            transactionList.add(nextTransaction);
        }

        return transactionList;
    }
*/

    /**
     * The getAllAccountTransactions takes an int as a parameter (the account_id),
     * and returns a MyArrayList of all the Transactions that have the given account_id
     * in the account_id field.
     */
    public MyArrayList<Transactions> getAllAccountTransactions(int account_id) throws SQLException {

        //The next 4 lines create and execute a PreparedStatement, and
        //then assign the result of the query to a ResultSet.
        String sqlAccountTrans = "SELECT * FROM transactions WHERE account_id = ?";
        PreparedStatement prepAccountTrans = conn.prepareStatement(sqlAccountTrans);
        prepAccountTrans.setInt(1,account_id);
        ResultSet allAccountTrans = prepAccountTrans.executeQuery();

        //instantiates a MyArrayList object and assigns it to "accountTransactions".
        MyArrayList<Transactions> accountTransactions = new MyArrayList<>();

        /*
        The while control flow statement runs while there is another row in the
        ResultSet. It instantiates a Transactions object, sets the fields in the object
        with data from the database, and then adds it to "accountTransactions".
         */
        while(allAccountTrans.next()){
            Transactions nextTransaction = new Transactions();
            nextTransaction.setTransaction_id(allAccountTrans.getInt(1));
            nextTransaction.setDeposit(allAccountTrans.getBoolean(2));
            nextTransaction.setWithdrawal(allAccountTrans.getBoolean(3));
            nextTransaction.setTransfer(allAccountTrans.getBoolean(4));
            nextTransaction.setTransfer_to_account_id(allAccountTrans.getInt(5));
            nextTransaction.setTransaction_amount(allAccountTrans.getFloat(6));
            nextTransaction.setAccount_id(allAccountTrans.getInt(7));
            accountTransactions.add(nextTransaction);
        }
        return accountTransactions;
    }
}
