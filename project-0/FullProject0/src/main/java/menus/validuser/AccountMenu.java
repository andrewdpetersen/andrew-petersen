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
import java.util.Scanner;

public class AccountMenu implements CurrencyFormat, BankMenu {
    public void accountMenu(Users user, int account_id) {



        try {
            Connection conn = ConnectionManager.getConnection();
            AccountsDAO accountListDAO = new AccountsDAO(conn);

            Accounts featureAccount = accountListDAO.getAccountById(account_id);

            System.out.println("Account ID: "+ account_id);
            System.out.println("Account Type: " + featureAccount.getAccount_type());
            System.out.println("Balance: $"+ betterConverter(featureAccount.getBalance()));
            System.out.println("User ID: "+featureAccount.getUser_id());
        }
        catch(SQLException | IOException | ParseException e){
            e.printStackTrace();
        }

        Scanner choiceScanner = new Scanner(System.in);
        System.out.println("(1) Deposit funds into this account\n" +
                "(2) Withdraw funds from this account\n" +
                "(3) Transfer funds from this account to a different account\n" +
                "(4) View transaction history\n" +
                "(5) Return to Bank Menu\n" +
                "(6) Logout\n" +
                "Please make a selection to continue:");
        String choice = choiceScanner.nextLine();

        switch(choice){
            case "1":
                //run deposit script
                Scanner depositScanner = new Scanner(System.in);
                System.out.println("How much would you like to deposit?");
                float depositAmount = Float.parseFloat(depositScanner.nextLine());
                if(depositAmount>=0){
                    try{
                        Connection conn = ConnectionManager.getConnection();
                        AccountsDAO accountListDAO = new AccountsDAO(conn);

                        Accounts featureAccount = accountListDAO.getAccountById(account_id);

                        float oldBalance = featureAccount.getBalance();
                        float newBalance = oldBalance+depositAmount;
                        featureAccount.setBalance(newBalance);

                        accountListDAO.updateAccounts(featureAccount);

                        TransactionsDAO updateTransTable = new TransactionsDAO(conn);

                        Transactions withdrawal = new Transactions();
                        withdrawal.setAccount_id(account_id);
                        withdrawal.setWithdrawal(false);
                        withdrawal.setDeposit(true);
                        withdrawal.setTransfer(false);
                        withdrawal.setTransaction_amount(depositAmount);

                        updateTransTable.updateTransaction(withdrawal);


                        new AccountMenu().accountMenu(user,account_id);
                    }
                    catch (SQLException|IOException e){
                        e.printStackTrace();
                    }
                }
                else{
                    System.out.println("You cannot make a negative deposit");
                }
                break;

            case "2":

                //run withdrawal script
                Scanner withdrawalScanner = new Scanner(System.in);
                System.out.println("How much would you like to withdraw?");
                float withdrawalAmount = Float.parseFloat(withdrawalScanner.nextLine());

                //verifying the withdrawal is not negative
                if(withdrawalAmount>=0) {

                    //execution of the withdrawal, updating the
                    try {
                        Connection conn = ConnectionManager.getConnection();
                        AccountsDAO accountListDAO = new AccountsDAO(conn);

                        Accounts featureAccount = accountListDAO.getAccountById(account_id);

                        float oldBalance = featureAccount.getBalance();

                        //verifying that we don't overdraft an account
                        if(withdrawalAmount>oldBalance){
                            System.out.println("You cannot withdraw more than the account balance!");
                        }

                        else {

                            float newBalance = oldBalance - withdrawalAmount;
                            featureAccount.setBalance(newBalance);

                            accountListDAO.updateAccounts(featureAccount);



                            TransactionsDAO updateTransTable = new TransactionsDAO(conn);

                            Transactions withdrawal = new Transactions();
                            withdrawal.setAccount_id(account_id);
                            withdrawal.setWithdrawal(true);
                            withdrawal.setDeposit(false);
                            withdrawal.setTransfer(false);
                            withdrawal.setTransaction_amount(withdrawalAmount);

                            updateTransTable.updateTransaction(withdrawal);

                        }

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

                //run transfer script
                Scanner transferScanner = new Scanner(System.in);
                System.out.println("How much would you like to transfer?");
                float transferAmount = Float.parseFloat(transferScanner.nextLine());
                try {
                    if (transferAmount >= 0) {
                        //find the account you wish to transfer to and set up the transfer
                        Connection conn = ConnectionManager.getConnection();
                        AccountsDAO transferFinder = new AccountsDAO(conn);

                        Accounts fromAccount = transferFinder.getAccountById(account_id);

                        System.out.println("What is the account ID of the account you " +
                                "want to transfer the funds into:");
                        int toAccountId = Integer.parseInt(transferScanner.nextLine());

                        Accounts toAccount = transferFinder.getAccountById(toAccountId);

                        //do the transfer

                        if (transferAmount <= fromAccount.getBalance()) {
                            float oldFromBalance = fromAccount.getBalance();
                            float newFromBalance = oldFromBalance - transferAmount;
                            float oldToBalance = toAccount.getBalance();
                            float newToBalance = oldToBalance + transferAmount;

                            toAccount.setBalance(newToBalance);
                            transferFinder.updateAccounts(toAccount);

                            fromAccount.setBalance(newFromBalance);
                            transferFinder.updateAccounts(fromAccount);

                            TransactionsDAO updateTransTable = new TransactionsDAO(conn);

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


                        } else {
                            System.out.println("You cannot transfer more funds than you have");
                        }

                    } else {
                        System.out.println("You cannot make a negative transfer");
                    }
                }catch(SQLException|IOException e){
                    e.printStackTrace();
                }
                break;
            case "4":

                //view transaction history
                try{
                    Connection conn = ConnectionManager.getConnection();
                    TransactionsDAO dao = new TransactionsDAO(conn);

                    MyArrayList<Transactions> transactionHistory =
                            dao.getAllAccountTransactions(account_id);

                    int i =0;
                    while(i<transactionHistory.size()){
                        if(transactionHistory.get(i)!=null){
                            new PrintOut().printOut(transactionHistory.get(i));
                        }
                        i++;
                    }

                    new AccountMenu().accountMenu(user,account_id);

                }
                catch (SQLException | IOException | ParseException e){
                    e.printStackTrace();
                }




                break;
            case "5":
                bankMenu(user);
                break;
            case "6":
                new OuterMenu().OuterMenu();
                break;
        }
    }
}
