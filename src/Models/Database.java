package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    //variables used to connect to database
    private static final String databaseName = "client_schedule";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + databaseName;
    private static final String username = "sqlUser";
    private static final String password = "Passw0rd!";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    public static Connection conn;

    /**
     * creates the database connection
     * @return information about the connection/server
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws Exception
     */
    public static Connection makeConnection() throws ClassNotFoundException, SQLException, Exception{
        Class.forName(driver);
        conn = DriverManager.getConnection(DB_URL, username, password);
        System.out.println("Connection successful.");
        return conn;
    }

    /**
     * closes the database connection
      * @throws SQLException
     */
    public static void closeConnection() throws SQLException{
        conn.close();
        System.out.println("Connection closed.");
    }
}
