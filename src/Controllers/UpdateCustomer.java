package Controllers;

import DAO.CustomerDao;
import DAO.FldDAO;
import Helpers.TimeUtility;
import Models.Customer;
import Models.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;


public class UpdateCustomer {
    @FXML ComboBox<String> countryCombo;
    @FXML ComboBox<String> fldCombo;
    @FXML TextField nameField;
    @FXML TextField addressField;
    @FXML TextField postalCodeField;
    @FXML TextField phoneNumberField;
    @FXML TextField idField;
    Customer customer = new Customer();

    @FXML public void initialize(Customer customerToUpdate) throws Exception {
        idField.setText(Integer.toString(customerToUpdate.getCustomerID()));
        nameField.setText(customerToUpdate.getCustomerName());
        addressField.setText(customerToUpdate.getCustomerAddress());
        postalCodeField.setText(customerToUpdate.getCustomerPostalCode());
        phoneNumberField.setText(customerToUpdate.getCustomerPhone());
        fldCombo.setValue(customerToUpdate.getDivision());
        countryCombo.setValue(customerToUpdate.getCountry());

        customer.setCustomerID(customerToUpdate.getCustomerID());
        customer.setCustomerName(customerToUpdate.getCustomerName());
        customer.setCustomerAddress(customerToUpdate.getCustomerAddress());
        customer.setCustomerPostalCode(customerToUpdate.getCustomerPostalCode());
        customer.setCustomerPhone(customerToUpdate.getCustomerPhone());
        customer.setCreatedBy(customerToUpdate.getCreatedBy());
        customer.setCreateDate(customerToUpdate.getCreateDate());
        customer.setLastUpdate(customerToUpdate.getLastUpdate());
        customer.setLastUpdatedBy(customerToUpdate.getLastUpdatedBy());
        customer.setDivisionID(customerToUpdate.getDivisionID());

        fillCountryComboBox();
        fillFldComboBox();
    }
    @FXML public void countryComboClicked() {
        fillFldComboBox();
    }

    public void fillCountryComboBox() {
        ObservableList<String> countries = FXCollections.observableArrayList();
        try {
            Database database = new Database();
            Connection connection = Database.makeConnection();
            Statement statement =  connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT country FROM countries");
            while (result.next()) {
                countries.add(result.getString("country"));
            }
            countryCombo.setItems(countries);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }

    }

    public void fillFldComboBox() {
        ObservableList<String> fld = FXCollections.observableArrayList();

        try {
            Connection connection = Database.makeConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM countries WHERE Country = ?");
            statement.setObject(1, countryCombo.getValue());
            ResultSet result = statement.executeQuery();
            result.next();
            String country_ID = result.getString("Country_ID");
            PreparedStatement divisionStatement = connection.prepareStatement("SELECT Division FROM first_level_divisions WHERE Country_ID = ?");
            divisionStatement.setObject(1, country_ID);
            ResultSet divisionResult = divisionStatement.executeQuery();
            divisionResult = divisionStatement.executeQuery();
            while (divisionResult.next()) {
                fld.add(divisionResult.getString("Division"));
            }
            fldCombo.setItems(fld);
        } catch (Exception e) {
            System.out.println("Exception " + e);
        }
    }
    @FXML
    public void submitButtonClicked() throws Exception {
        customer.setCustomerID(Integer.valueOf(idField.getText()));
        customer.setCustomerName(nameField.getText());
        customer.setCustomerAddress(addressField.getText());
        customer.setCustomerPostalCode(postalCodeField.getText());
        customer.setCustomerPhone(phoneNumberField.getText());
        customer.setCreateDate(customer.getCreateDate());
        customer.setCreatedBy(customer.getCreatedBy());
        customer.setLastUpdate(TimeUtility.localToUTCTime(LocalDateTime.now().toString()));
        customer.setLastUpdatedBy(Main.currentUser.getUsername());
        customer.setDivisionID(FldDAO.get(fldCombo.getSelectionModel().getSelectedItem()).getDivisionID());
        CustomerDao.update(customer);

        Stage currentStage = (Stage) phoneNumberField.getScene().getWindow();
        currentStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/ManageCustomers.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }
}
