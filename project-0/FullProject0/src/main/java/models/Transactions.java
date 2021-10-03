package models;

/**
 * The Accounts class is a POJO, with 7 fields and Getters and Setters
 *
 * models.Users class has 7 fields: transaction_id, deposit, withdrawal, transfer,
 * transfer_to_account_id, transaction_amount, account_id
 */
public class Transactions {
    private int transaction_id;
    private boolean deposit;
    private boolean withdrawal;
    private boolean transfer;
    private int transfer_to_account_id;
    private float transaction_amount;
    private int account_id;

    //Getter and Setter for field: transaction_id
    public int getTransaction_id() {
        return transaction_id;
    }
    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    //Getter and Setter for field: deposit
    public boolean isDeposit() {
        return deposit;
    }
    public void setDeposit(boolean deposit) {
        this.deposit = deposit;
    }

    //Getter and Setter for field: withdrawal
    public boolean isWithdrawal() {
        return withdrawal;
    }
    public void setWithdrawal(boolean withdrawal) {
        this.withdrawal = withdrawal;
    }

    //Getter and Setter for field: transfer
    public boolean isTransfer() {
        return transfer;
    }
    public void setTransfer(boolean transfer) {
        this.transfer = transfer;
    }

    //Getter and Setter for field: transfer_to_account_id
    public int getTransfer_to_account_id() {
        return transfer_to_account_id;
    }
    public void setTransfer_to_account_id(int transfer_to_account_id) {
        this.transfer_to_account_id = transfer_to_account_id;
    }

    //Getter and Setter for field: transaction_amount
    public float getTransaction_amount(){return transaction_amount;}
    public void setTransaction_amount(float transaction_amount){
        this.transaction_amount = transaction_amount;
    }

    //Getter and Setter for field: account_id
    public int getAccount_id() {
        return account_id;
    }
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
}
