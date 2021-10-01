import DAOs.AccountsDAO;
import DAOs.UsersDAO;
import menus.OuterMenu;
import models.Accounts;
import models.Users;
import utils.ConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Driver {
    public static void main(String[] args){
        new OuterMenu().OuterMenu();
    }
}
