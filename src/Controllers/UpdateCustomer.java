package Controllers;

import DAO.CustomerDao;
import DAO.FldDAO;
import Helpers.TimeUtility;
import Models.Customer;
import Models.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;


public class UpdateCustomer {
    @FXML ComboBox<String> countryCombo;
    @FXML ComboBox<String> fldCombo;
    @FXML TextField nameField;
    @FXML TextField addressField;
    @FXML TextField postalCodeField;
    @FXML TextField phoneNumberField;


    @FXML public void initialize(Customer customerToUpdate) throws Exception {
        nameField.setText(customerToUpdate.getCustomerName());
        addressField.setText(customerToUpdate.getCustomerAddress());
        postalCodeField.setText(customerToUpdate.getCustomerPostalCode());
        phoneNumberField.setText(customerToUpdate.getCustomerPhone());
        fldCombo.setValue(customerToUpdate.getDivision());
        countryCombo.setValue(customerToUpdate.getCountry());
    }
    @FXML public void countryComboClicked() {
        fillFldComboBox();
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
        Customer customer = new Customer();
        customer.setCustomerName(nameField.getText());
        customer.setCustomerAddress(addressField.getText());
        customer.setCustomerPostalCode(postalCodeField.getText());
        customer.setCustomerPhone(phoneNumberField.getText());
        customer.setCreateDate(TimeUtility.localToUTCTime(LocalDateTime.now().toString()));
        customer.setCreatedBy(Main.currentUser.getUsername());
        customer.setLastUpdate(TimeUtility.localToUTCTime(LocalDateTime.now().toString()));
        customer.setLastUpdatedBy(Main.currentUser.getUsername());
        customer.setDivisionID(FldDAO.get(fldCombo.getSelectionModel().getSelectedItem()).getDivisionID());
        CustomerDao.update(customer);
    }
}
