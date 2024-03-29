package Controllers;

import DAO.CustomerDao;
import Models.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Manage customers controller is used for the manage customers view and controls the table of customer information and
 * allowing access to add, update, and delete customer records.
 */
public class ManageCustomers {
    @FXML TableView<Customer> customerTableView;
    @FXML TableColumn<Customer,Integer> customerIDCol;
    @FXML TableColumn<Customer,String> customerNameCol;
    @FXML TableColumn<Customer,String> customerAddressCol;
    @FXML TableColumn<Customer,String> customerPhoneCol;
    @FXML TableColumn<Customer,String> customerPostalCodeCol;
    @FXML TableColumn<Customer,String> customerCreateDateCol;
    @FXML TableColumn<Customer,String> customerCreatedByCol;
    @FXML TableColumn<Customer,String> customerLastUpdateCol;
    @FXML TableColumn<Customer,String> customerLastUpdatedByCol;
    @FXML TableColumn<Customer,String> customerDivisionCol;


    /**
     * Initalizes the manage customers view/controller. Sets customer table values.
     */
    @FXML public void initialize() {
        try {
            customerIDCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerID"));
            customerNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
            customerAddressCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerAddress"));
            customerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerPostalCode"));
            customerPhoneCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerPhone"));
            customerCreateDateCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("createDate"));
            customerCreatedByCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("createdBy"));
            customerLastUpdateCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastUpdate"));
            customerLastUpdatedByCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastUpdatedBy"));
            customerDivisionCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("division"));

            customerTableView.setItems(CustomerDao.getAllCustomers());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Launches the add customer view when add customer is clicked
     */
    @FXML public void addCustomerClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/AddCustomer.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            Stage currentStage= (Stage) customerTableView.getScene().getWindow();
            currentStage.close();
        } catch (java.io.IOException exception) {
            System.out.println("io exception");
        }
    }

    /**
     * Launches the update customer view when update customer is clicked
     */
    @FXML public void updateCustomerClicked() {
        try {
            Customer customerToUpdate = customerTableView.getSelectionModel().getSelectedItem();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/UpdateCustomer.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            UpdateCustomer updateCustomerController = fxmlLoader.getController();
            updateCustomerController.initialize(customerToUpdate);
            stage.setScene(new Scene(root1));
            stage.show();
            Stage currentStage= (Stage) customerTableView.getScene().getWindow();
            currentStage.close();
        } catch (java.io.IOException exception) {
            System.out.println("io exception");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * deletes the currently selected customer from the table
     * @throws Exception
     */
    @FXML public void deleteCustomerClicked() throws Exception {
        CustomerDao.delete(customerTableView.getSelectionModel().getSelectedItem().getCustomerID());
        customerTableView.setItems(CustomerDao.getAllCustomers());
    }
}
