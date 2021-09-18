package Utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class RelationConnection {

    private static Connection conn;

    private RelationConnection(){
    }

    //Creating a "getRelation" method to create a connection to my DB
    //Also to note- in the future, I will define my own SQLException class
    //This will "handle" the exception rather than throwing it along
    public static Connection getRelation() throws SQLException, IOException {
        if (conn==null){
            Properties props = new Properties();
            FileReader connectionProperties = new FileReader("src/main/resources/connection.properties");
            props.load(connectionProperties);

            //"jdbc:mariadb://hostname:port/databaseName?user=username&password=password"
            String connAddress = "jdbc:mariadb://" +
                    props.getProperty("hostname") + ":" +
                    props.getProperty("port") + "/" +
                    props.getProperty("databaseName") + "?user=" +
                    props.getProperty("user") + "&password=" +
                    props.getProperty("password");

            conn = DriverManager.getConnection(connAddress);
        }
        return conn;
    }
}
