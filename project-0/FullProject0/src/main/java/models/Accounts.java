package models;

/**
 * The Accounts class is a POJO, with 4 fields and Getters and Setters
 *
 * models.Users class has 4 fields: account_id, account_type, balance, user_id
 */
public class Accounts {
    private int account_id;
    private String account_type;
    private float balance;
    private int user_id;

    //Getter and Setter for field: account_id
    public int getAccount_id() {
        return account_id;
    }
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    //Getter and Setter for field: account_type
    public String getAccount_type() {
        return account_type;
    }
    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    //Getter and Setter for field: balance
    public float getBalance() {
        return balance;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }

    //Getter and Setter for field: user_id
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
