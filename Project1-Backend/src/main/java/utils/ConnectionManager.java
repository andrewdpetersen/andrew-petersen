package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The class ConnectionManager contains a static field, "conn", and one static method,
 * "getConnection". getConnection is a static method in the factory singleton design pattern,
 * which means that we will only have one Connection that is related to the class at any time.
 */
public class ConnectionManager {
    //Connection field, "conn"
    private static Connection conn;

    //Boilerplate, no arg, constructor
    private ConnectionManager() {
    }

    /*
    This is a static method for returning a connection in the factory singleton design pattern
     */
    public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {

        //checks if the "conn" exists
        if(conn == null) {
            Properties props = new Properties();
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            InputStream fileIn = cl.getResourceAsStream("connection.properties");
            props.load(fileIn);
            Class.forName("org.mariadb.jdbc.Driver");


            //reads the properties from connection.properties and loads them
//            Properties props = new Properties();
//            FileReader connectionProperties = new FileReader("src/main/resources/connection.properties");
//            props.load(connectionProperties);

            //This section returns a String with the connection URL, using the
            //connection.properties file

            //"jdbc:mariadb://hostname:port/databaseName?user=username&password=password"
            String connString = "jdbc:mariadb://" +
                    props.getProperty("hostname") + ":" +
                    props.getProperty("port") + "/" +
                    props.getProperty("databaseName") + "?user=" +
                    props.getProperty("user") + "&password=" +
                    props.getProperty("password");

            //Finally, we assign our connection to the reference "conn" and return it.
            conn = DriverManager.getConnection(connString);
        }
        return conn;
    }

}