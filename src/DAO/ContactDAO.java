package DAO;

import Models.Contact;
import Models.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ContactDAO {
    public static Contact get(int contactID) throws Exception {
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "select * FROM contacts WHERE contact_ID  = " + contactID;
        ResultSet result = statement.executeQuery(sqlStatement);
        Contact contactResult = new Contact();
        while (result.next()) {
            contactResult.setContactID(result.getInt("Contact_ID"));
            contactResult.setContactName(result.getString("Contact_Name"));
            contactResult.setEmail(result.getString("Email"));

            return contactResult;
        }
        Database.closeConnection();
        return null;
    }
}
