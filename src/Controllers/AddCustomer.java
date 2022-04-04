package Controllers;

import DAO.FldDAO;
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
import java.sql.Statement;
import java.util.Calendar;

public class AddCustomer {
    @FXML ComboBox<String> countryCombo;
    @FXML TextField nameField;
    @FXML TextField addressField;
    @FXML TextField postalCodeField;
    @FXML TextField phoneNumberField;
    @FXML ComboBox<String> fldCombo;


    public void initialize() {
        fillCountryComboBox();
    }

    @FXML public void submitButtonClicked() throws Exception {
        Customer newCustomer = new Customer();
        newCustomer.setCustomerName(nameField.getText());
        newCustomer.setCustomerAddress(addressField.getText());
        newCustomer.setCustomerPostalCode(postalCodeField.getText());
        newCustomer.setCustomerPhone(phoneNumberField.getText());
        newCustomer.setCreateDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        newCustomer.setCreatedBy(Main.currentUser.getUsername());
        newCustomer.setLastUpdatedBy(Main.currentUser.getUsername());
        newCustomer.setDivisionID(FldDAO.get(fldCombo.getSelectionModel().toString()).getDivisionID()); // need to implement fldDAO with a get method given string, in order to get the division ID.
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
            PreparedStatement statement =  connection.prepareStatement("SELECT * FROM countries WHERE Country = ?");
            statement.setObject(1, countryCombo.getValue());
            ResultSet result = statement.executeQuery();
            result.next();
            String country_ID = result.getString("Country_ID");
            PreparedStatement divisionStatement =  connection.prepareStatement("SELECT Division FROM first_level_divisions WHERE Country_ID = ?");
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
}
