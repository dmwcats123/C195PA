package DAO;

import Models.Contact;
import Models.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ContactDao is used to interact with contacts in the database
 */
public class ContactDAO {
    /**
     * finds a contact in the database with a specific ID
     * @param contactID the contact id of the contact to retrieve
     * @return contact with the specified contact ID.
     * @throws Exception
     */
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

    /**
     * gets a contact given a specified contact name from the database
     * @param contact the name of the contact to retrieve from the database
     * @return the contact with the specified name
     * @throws Exception
     */
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

    /**
     * Gets all contacts in the database
     * @return an observable list of all contacts in the database
     * @throws SQLException
     * @throws Exception
     */
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
