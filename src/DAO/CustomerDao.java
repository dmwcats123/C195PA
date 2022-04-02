package DAO;

import Models.Customer;
import Models.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CustomerDao {

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

    public static ObservableList<Customer> getAllCustomers() throws SQLException, Exception{
        ObservableList<Customer> allCustomers= FXCollections.observableArrayList();
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "select * FROM customers";
        ResultSet result = statement.executeQuery(sqlStatement);
        while(result.next()){
            Customer customerResult= new Customer(result.getInt("Customer_ID"), result.getString("Customer_Name"),
                    result.getString("Address"), result.getString("Postal_Code"), result.getString("Phone"),
                    result.getString("Create_Date"), result.getString("Created_By"),
                    result.getString("Last_Update"), result.getString("Last_Updated_By"), result.getInt("Division_ID"));
            allCustomers.add(customerResult);

        }
        Database.closeConnection();
        return allCustomers;
    }

    public static void delete(int customerID) throws Exception {
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "delete * FROM customers where Customer_ID = " + customerID;
        statement.executeUpdate(sqlStatement);
    }

    public static void add(Customer customer) throws Exception {
        Connection connection = Database.makeConnection();
        PreparedStatement ps = connection.prepareStatement("insert into customers (Customer_ID, Customer_Name, Address, Postal_Code," +
                "Phone, Create_Date, Created_By, Last_Update, Last_Updated_By) VALUES (?,?,?,?,?,?,?,?,?)");
        ps.setString(1, customer.getCustomerName());
        ps.setString(2, customer.getCustomerAddress());
        ps.setString(3, customer.getCustomerPostalCode());
        ps.setString(4, customer.getCustomerPhone());
        ps.setString(5, customer.getCreateDate());
        ps.setString(6, customer.getCreatedBy());
        ps.setString(7, customer.getLastUpdate());
        ps.setString(8, customer.getLastUpdatedBy());

        ps.executeUpdate();
    }

    public static void update(Customer customer) throws Exception {
        Connection connection = Database.makeConnection();
        PreparedStatement ps = connection.prepareStatement("update customers SET Customer_Name = ?, Customer_Address = ?, " +
                "Postal_Code = ?, Phone = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ? WHERE Customer_ID = ?");

        ps.setString(1, customer.getCustomerName());
        ps.setString(2, customer.getCustomerAddress());
        ps.setString(3, customer.getCustomerPostalCode());
        ps.setString(4, customer.getCustomerPhone());
        ps.setString(5, customer.getCreateDate());
        ps.setString(6, customer.getCreatedBy());
        ps.setString(7, customer.getLastUpdate());
        ps.setString(8, customer.getLastUpdatedBy());
        ps.setInt(9, customer.getCustomerID());

        ps.executeUpdate();
        Database.closeConnection();
    }

}
