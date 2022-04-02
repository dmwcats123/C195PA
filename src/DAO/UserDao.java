package DAO;

import Models.Database;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class UserDao {

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

    public static void delete(int userID) throws Exception {
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "delete * FROM users where User_ID = " + userID;
        statement.executeUpdate(sqlStatement);
    }

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

    public static void update(User user) throws Exception {
        Connection connection = Database.makeConnection();
        PreparedStatement ps = connection.prepareStatement("update user SET User_Name = ?, Password = ?, Create_Date = ?, Created_By = ?," +
                " Last_Update = ?, Last_Updated_By = ?) VALUES (?,?,?,?,?,?)");
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getCreateDate());
        ps.setString(4, user.getCreatedBy());
        ps.setString(5, user.getLastUpdate());
        ps.setString(6, user.getLastUpdatedBy());

        ps.executeUpdate();
        Database.closeConnection();
    }


}
