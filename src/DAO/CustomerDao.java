package DAO;

import Helpers.TimeUtility;
import Models.Customer;
import Models.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;

/**
 * CustomerDao is used to interact with customers in the database
 */
public class CustomerDao {

    /**
     * get a customer from the database with the specified customer ID
     * @param customerID the customer ID of the customer to be retrieved from the database
     * @return the customer with the specified customer ID
     * @throws Exception
     */
    public static Customer get(int customerID) throws Exception {
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "select * FROM customers WHERE customer_ID  = " + customerID;
        ResultSet result = statement.executeQuery(sqlStatement);
        Customer customerResult = new Customer();
        while (result.next()) {
            customerResult.setCustomerID(result.getInt("Customer_ID"));
            customerResult.setCustomerName(result.getString("Customer_Name"));
            customerResult.setCustomerAddress(result.getString("Address"));
            customerResult.setCustomerPostalCode(result.getString("Postal_Code"));
            customerResult.setCustomerPhone(result.getString("Phone"));
            customerResult.setCreateDate(result.getString("Create_Date"));
            customerResult.setCreatedBy(result.getString("Created_By"));
            customerResult.setLastUpdate(result.getString("Last_Update"));
            customerResult.setLastUpdatedBy(result.getString("Last_Updated_By"));
            customerResult.setDivisionID(result.getInt("Division_ID"));
            return customerResult;
        }
        Database.closeConnection();
        return null;
    }

    /**
     * retrieves all customers from the database
     * @return an observablelist of all customers in the database.
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Customer> getAllCustomers() throws SQLException, Exception{
        ObservableList<Customer> allCustomers= FXCollections.observableArrayList();
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "select * FROM customers";
        ResultSet result = statement.executeQuery(sqlStatement);
        while(result.next()){
            Customer customerResult= new Customer(result.getInt("Customer_ID"), result.getString("Customer_Name"),
                    result.getString("Address"), result.getString("Postal_Code"), result.getString("Phone"),
                    TimeUtility.utcToLocalTime(result.getString("Create_Date")), result.getString("Created_By"),
                    TimeUtility.utcToLocalTime(result.getString("Last_Update")), result.getString("Last_Updated_By"), result.getInt("Division_ID"));
            allCustomers.add(customerResult);

        }
        Database.closeConnection();
        return allCustomers;
    }

    /**
     * deletes a customer from the database with a specified ID
     * @param customerID the ID of the customer to be deleted.
     * @throws Exception
     */
    public static void delete(int customerID) throws Exception {
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "delete FROM customers where Customer_ID = " + customerID;

         if (statement.executeUpdate(sqlStatement) > 0 ) {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("Success");
             alert.setContentText("The record was deleted");
             alert.showAndWait();
         } else {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("Failure");
             alert.setContentText("The record was not deleted");
             alert.showAndWait();
         }
    }

    /**
     * adds a customer to the database
     * @param customer the customer to be added to the database
     * @throws Exception
     */
    public static void add(Customer customer) throws Exception {
        Connection connection = Database.makeConnection();
        PreparedStatement ps = connection.prepareStatement("insert into customers (Customer_Name, Address, Postal_Code," +
                "Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?,?,?,?,?,?,?,?,?)");
        ps.setString(1, customer.getCustomerName());
        ps.setString(2, customer.getCustomerAddress());
        ps.setString(3, customer.getCustomerPostalCode());
        ps.setString(4, customer.getCustomerPhone());
        ps.setString(5, customer.getCreateDate());
        ps.setString(6, customer.getCreatedBy());
        ps.setString(7, customer.getLastUpdate());
        ps.setString(8, customer.getLastUpdatedBy());
        ps.setInt(9,customer.getDivisionID());

        ps.executeUpdate();
        Database.closeConnection();
    }

    /**
     * updates a customer in the database with new information
     * @param customer the customer to be updated.
     * @throws Exception
     */
    public static void update(Customer customer) throws Exception {
        Connection connection = Database.makeConnection();
        PreparedStatement ps = connection.prepareStatement("update customers SET Customer_Name = ?, Address = ?, " +
                "Postal_Code = ?, Phone = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?");

        ps.setString(1, customer.getCustomerName());
        ps.setString(2, customer.getCustomerAddress());
        ps.setString(3, customer.getCustomerPostalCode());
        ps.setString(4, customer.getCustomerPhone());
        ps.setString(5, customer.getCreateDate());
        ps.setString(6, customer.getCreatedBy());
        ps.setString(7, customer.getLastUpdate());
        ps.setString(8, customer.getLastUpdatedBy());
        ps.setInt(9, customer.getDivisionID());
        ps.setInt(10, customer.getCustomerID());

        int update = ps.executeUpdate();
        Database.closeConnection();
    }

}
