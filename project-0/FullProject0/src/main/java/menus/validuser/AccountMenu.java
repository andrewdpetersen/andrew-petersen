package menus.validuser;

import DAOs.AccountsDAO;
import DAOs.TransactionsDAO;
import menus.OuterMenu;
import models.Accounts;
import models.Transactions;
import models.Users;
import utils.ConnectionManager;
import utils.MyArrayList;
import utils.PrintOut;
import utils.formatValidation.CurrencyFormat;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * The AccountMenu class contains one method, "accountMenu", that takes two
 * parameters, a Users object, "user", and an int, "account_id". It prints
 * information as an Accounts object from the database to the console. Then it
 * requests input from the console. Using that input, it performs various functions
 * regarding transactions on the given account.
 */
public class AccountMenu implements CurrencyFormat, BankMenu, PrintOut {

    /**
     * The accountMenu method takes two parameters, a Users object, "user", and an int,
     * "account_id". It prints information as an Accounts object from the database to
     * the console. Then it requests input from the console. Using that input, it performs
     * various functions regarding transactions on the given account.
     */
    public void accountMenu(Users user, int account_id) {

        //This block prints information to the console using the account_id.
        try {
            Connection conn = ConnectionManager.getConnection();
            AccountsDAO accountListDAO = new AccountsDAO(conn);

            Accounts featureAccount = accountListDAO.getAccountById(account_id);

            printOut(featureAccount);
            //System.out.println("Account ID: "+ account_id);
            //System.out.println("Account Type: " + featureAccount.getAccount_type());
            //System.out.println("Balance: $"+ betterConverter(featureAccount.getBalance()));
            //System.out.println("User ID: "+featureAccount.getUser_id());
        }
        catch(SQLException | IOException | ParseException e){
            e.printStackTrace();
        }

        //This section asks for input from the console, and assigns it to "choice".
        String choice = caller("(1) Deposit funds into this account\n" +
                "(2) Withdraw funds from this account\n" +
                "(3) Transfer funds from this account to a different account\n" +
                "(4) View transaction history\n" +
                "(5) Return to Bank Menu\n" +
                "(6) Logout\n" +
                "Please make a selection to continue:");

        //The control flow statement takes the console input assigned to "choice".
        switch(choice){
            case "1":

                //This block asks for console input and assigns it to depositAmount.
                float depositAmount = Float.parseFloat(caller("How much would you like to deposit?"));

                //validation to make sure there are no negative deposits
                if(depositAmount>=0){
                    try{
                        Connection conn = ConnectionManager.getConnection();
                        AccountsDAO accountListDAO = new AccountsDAO(conn);
                        Accounts featureAccount = accountListDAO.getAccountById(account_id);

                        // Sets the balance in the Accounts object using the old balance
                        // and the deposit amount.
                        float oldBalance = featureAccount.getBalance();
                        float newBalance = oldBalance+depositAmount;
                        featureAccount.setBalance(newBalance);
                        accountListDAO.updateAccounts(featureAccount);


                        TransactionsDAO updateTransTable = new TransactionsDAO(conn);

                        //Sets up an instance of the Transactions class
                        Transactions withdrawal = new Transactions();
                        withdrawal.setAccount_id(account_id);
                        withdrawal.setWithdrawal(false);
                        withdrawal.setDeposit(true);
                        withdrawal.setTransfer(false);
                        withdrawal.setTransaction_amount(depositAmount);

                        //Updates the transactions table
                        updateTransTable.updateTransaction(withdrawal);

                        //runs the "accountMenu" method
                        new AccountMenu().accountMenu(user,account_id);
                    }
                    catch (SQLException|IOException e){
                        e.printStackTrace();
                    }
                }

                //prints a message if there is a negative deposit
                else{
                    System.out.println("Your deposit must be a positive number");
                }
                break;

            case "2":

                //This block asks for console input and assigns it to withdrawalAmount.
                float withdrawalAmount = Float.parseFloat(caller("How much would you like to withdraw?"));

                //verifying the withdrawal is not negative
                if(withdrawalAmount>=0) {

                    try {
                        Connection conn = ConnectionManager.getConnection();
                        AccountsDAO accountListDAO = new AccountsDAO(conn);
                        Accounts featureAccount = accountListDAO.getAccountById(account_id);

                        //Getting the current balance before a withdrawal occurs
                        float oldBalance = featureAccount.getBalance();

                        //verifying that we don't overdraft an account
                        if(withdrawalAmount>oldBalance){
                            System.out.println("You cannot withdraw more than the account balance!");
                        }

                        else {

                            // Sets the balance in the Accounts object using the old balance
                            // and the withdrawal amount.
                            float newBalance = oldBalance - withdrawalAmount;
                            featureAccount.setBalance(newBalance);
                            accountListDAO.updateAccounts(featureAccount);

                            TransactionsDAO updateTransTable = new TransactionsDAO(conn);

                            //Sets up an instance of the Transactions class
                            Transactions withdrawal = new Transactions();
                            withdrawal.setAccount_id(account_id);
                            withdrawal.setWithdrawal(true);
                            withdrawal.setDeposit(false);
                            withdrawal.setTransfer(false);
                            withdrawal.setTransaction_amount(withdrawalAmount);

                            //Updates the transactions table
                            updateTransTable.updateTransaction(withdrawal);

                        }

                        //runs the "accountMenu" method
                        new AccountMenu().accountMenu(user,account_id);

                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    }
                }
                //if the user tries to make a negative withdrawal, they get a message
                else{
                    System.out.println("You cannot make a negative withdrawal");
                }
                break;

            case "3":

                //Asks for console input and assigns it to transferAmount
                float transferAmount = Float.parseFloat(caller("How much would you like to transfer?"));

                try {
                    //verifies that the transfer amount is not negative
                    if (transferAmount >= 0) {

                        Connection conn = ConnectionManager.getConnection();
                        AccountsDAO transferFinder = new AccountsDAO(conn);

                        //Queries the database and assigns the Accounts instance to fromAccount
                        Accounts fromAccount = transferFinder.getAccountById(account_id);

                        //Asks for console input for the account the transfer is going into.
                        int toAccountId = Integer.parseInt(caller("What is the account ID of the account you " +
                                "want to transfer the funds into:"));

                        //Queries the database and assigns the Accounts instance to toAccount
                        Accounts toAccount = transferFinder.getAccountById(toAccountId);

                        //Executes the transfer and updates the database with the new balances
                        if (transferAmount <= fromAccount.getBalance()) {
                            float oldFromBalance = fromAccount.getBalance();
                            float newFromBalance = oldFromBalance - transferAmount;
                            float oldToBalance = toAccount.getBalance();
                            float newToBalance = oldToBalance + transferAmount;

                            //updates the accounts table in the database
                            toAccount.setBalance(newToBalance);
                            transferFinder.updateAccounts(toAccount);
                            fromAccount.setBalance(newFromBalance);
                            transferFinder.updateAccounts(fromAccount);

                            TransactionsDAO updateTransTable = new TransactionsDAO(conn);

                            //This section creates two instances of Transactions objects
                            //and updates the transactions table in the database.
                            Transactions transferFrom = new Transactions();
                            transferFrom.setAccount_id(account_id);
                            transferFrom.setWithdrawal(false);
                            transferFrom.setDeposit(false);
                            transferFrom.setTransfer(true);
                            transferFrom.setTransfer_to_account_id(toAccountId);
                            transferFrom.setTransaction_amount(transferAmount);
                            updateTransTable.updateTransaction(transferFrom);

                            Transactions transferTo = new Transactions();
                            transferTo.setAccount_id(toAccountId);
                            transferTo.setDeposit(false);
                            transferTo.setWithdrawal(false);
                            transferTo.setTransfer(true);
                            transferTo.setTransfer_to_account_id(toAccountId);
                            transferTo.setTransaction_amount(transferAmount);
                            updateTransTable.updateTransaction(transferTo);

                            //This else statement runs if the input is greater than the balance
                        } else {
                            System.out.println("You cannot transfer more funds than you have");
                        }

                        //This else statement runs if the input is negative.
                    } else {
                        System.out.println("You cannot make a negative transfer");
                    }

                    //runs the "accountMenu" method
                    new AccountMenu().accountMenu(user,account_id);

                }catch(SQLException|IOException e){
                    e.printStackTrace();
                }
                break;

            case "4":

                //This block will print the transaction history of the given
                //account to the console.
                try{
                    Connection conn = ConnectionManager.getConnection();
                    TransactionsDAO dao = new TransactionsDAO(conn);

                    //Instantiates a MyArrayList that contains Transactions objects.
                    //Assigns the rows of the ResultSet from the dao to "transactionHistory".
                    MyArrayList<Transactions> transactionHistory =
                            dao.getAllAccountTransactions(account_id);

                    //This block prints out each Transactions instance from
                    //the "transactionHistory" collection.
                    int i =0;
                    while(i<transactionHistory.size()){
                        if(transactionHistory.get(i)!=null){
                            printOut(transactionHistory.get(i));
                        }
                        i++;
                    }

                    //runs the "accountMenu" method
                    new AccountMenu().accountMenu(user,account_id);

                }
                catch (SQLException | IOException | ParseException e){
                    e.printStackTrace();
                }
                break;
            case "5":

                //runs the bankMenu method
                bankMenu(user);
                break;
            case "6":

                //runs the OuterMenu method
                new OuterMenu().OuterMenu(false);
                break;
            default:
                System.out.println("Please make a valid selection");
                break;
        }
    }
}
