package Controllers;

import DAO.CustomerDao;
import Models.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    @FXML
    public void initialize() {
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
    @FXML
    public void addCustomerClicked() {

    }

    @FXML
    public void updateCustomerClicked() {

    }

    @FXML
    public void deleteCustomerClicked() {

    }
}
