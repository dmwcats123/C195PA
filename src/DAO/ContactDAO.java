package DAO;

import Models.Contact;
import Models.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public static Contact get(String contact) throws Exception {
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "select * FROM contacts WHERE Contact_Name  = '" + contact + "'";
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

    public static ObservableList<Contact> getAllContacts() throws SQLException, Exception{
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "select * FROM contacts";
        ResultSet result = statement.executeQuery(sqlStatement);
        while(result.next()){
            Contact contact = new Contact();
            contact.setContactID(result.getInt("Contact_ID"));
            contact.setContactName(result.getString("Contact_Name"));
            contact.setEmail(result.getString("Email"));
            allContacts.add(contact);
        }
        Database.closeConnection();
        return allContacts;
    }
}
