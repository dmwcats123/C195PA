package DAO;

import Models.Database;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * UserDao is used to interact with users in the database
 */
public class UserDao {

    /**
     * gets a user from the database with a specified user ID
     * @param userID the userID of the user to be retrieved from the database
     * @return the user with the specified ID
     * @throws Exception
     */
    public static User get(int userID) throws Exception {
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "select * FROM users WHERE user_ID  = " + userID;
        ResultSet result = statement.executeQuery(sqlStatement);
        User userResult = new User();
        while (result.next()) {
            userResult.setUserID(result.getInt("User_ID"));
            userResult.setUsername(result.getString("User_Name"));
            userResult.setPassword(result.getString("Password"));
            userResult.setCreateDate(result.getString("Create_Date"));
            userResult.setCreatedBy(result.getString("Created_By"));
            userResult.setLastUpdate(result.getString("Last_Update"));
            userResult.setLastUpdatedBy(result.getString("Last_Updated_By"));
            return userResult;
        }
        Database.closeConnection();
        return null;
    }

    /**
     * retrieves a user from the database with a specified username
     * @param username the username of the user to be retrieved
     * @return the user with the specified username
     * @throws Exception
     */
    public static User get(String username) throws Exception {
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "select * FROM users WHERE User_Name = '" + username + "'";
        ResultSet result = statement.executeQuery(sqlStatement);
        User userResult = new User();
        while (result.next()) {
            userResult.setUserID(result.getInt("User_ID"));
            userResult.setUsername(result.getString("User_Name"));
            userResult.setPassword(result.getString("Password"));
            userResult.setCreateDate(result.getString("Create_Date"));
            userResult.setCreatedBy(result.getString("Created_By"));
            userResult.setLastUpdate(result.getString("Last_Update"));
            userResult.setLastUpdatedBy(result.getString("Last_Updated_By"));
            Database.closeConnection();
            return userResult;
        }
        Database.closeConnection();
        return null;
    }

    /**
     * retrieves all users from the database
     * @return an observable list of all users in the database
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<User> getAllUsers() throws SQLException, Exception{
        ObservableList<User> allUsers= FXCollections.observableArrayList();
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "select * FROM users";
        ResultSet result = statement.executeQuery(sqlStatement);
        while(result.next()){
            User userResult= new User(result.getInt("User_ID"), result.getString("User_Name"),
                    result.getString("Password"), result.getString("Create_Date"), result.getString("Created_By"),
                    result.getString("Last_Update"), result.getString("Last_Updated_By"));
            allUsers.add(userResult);

        }
        Database.closeConnection();
        return allUsers;
    }

    /**
     * deletes a user with a specified userID from the database
     * @param userID the userID of the user to be deleted
     * @throws Exception
     */
    public static void delete(int userID) throws Exception {
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "delete * FROM users where User_ID = " + userID;
        statement.executeUpdate(sqlStatement);
    }

    /**
     * adds a user to the database
     * @param user to be added to the database
     * @throws Exception
     */
    public static void add(User user) throws Exception {
        Connection connection = Database.makeConnection();
        PreparedStatement ps = connection.prepareStatement("insert into users (User_Name, Password, Create_Date, Created_By," +
                " Last_Update, Last_Updated_By) VALUES (?,?,?,?,?,?,?)");
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getCreateDate());
        ps.setString(4, user.getCreatedBy());
        ps.setString(5, user.getLastUpdate());
        ps.setString(6, user.getLastUpdatedBy());

        ps.executeUpdate();
    }

    /**
     * updates a user in the database with new information
     * @param user the user to be updated
     * @throws Exception
     */
    public static void update(User user) throws Exception {
        Connection connection = Database.makeConnection();
        PreparedStatement ps = connection.prepareStatement("update users SET User_Name = ?, Password = ?, Create_Date = ?, Created_By = ?," +
                " Last_Update = ?, Last_Updated_By = ? WHERE User_ID = ?");
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getCreateDate());
        ps.setString(4, user.getCreatedBy());
        ps.setString(5, user.getLastUpdate());
        ps.setString(6, user.getLastUpdatedBy());
        ps.setInt(7, user.getUserID());


        ps.executeUpdate();
        Database.closeConnection();
    }


}
