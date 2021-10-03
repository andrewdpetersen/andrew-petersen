package models;

/**
 * The Users class is a POJO, with 6 fields and Getters and Setters
 *
 * models.Users class has 6 fields: user_id, username, password,email, first_name, last_name
 */

public class Users {
    private int user_id;
    private String username;
    private String password;
    private String email;
    private String first_name;
    private String last_name;

    //Getter and Setter for field: user_id
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    //Getter and Setter for field: username
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    //Getter and Setter for field: password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //Getter and Setter for field: email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    //Getter and Setter for field: first_name
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    //Getter and Setter for field: last_name
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}

