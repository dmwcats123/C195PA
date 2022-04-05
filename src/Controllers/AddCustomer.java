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
        newCustomer.setCreateDate(TimeUtility.localToUTCTime(LocalDateTime.now().toString()));
        newCustomer.setCreatedBy(Main.currentUser.getUsername());
        newCustomer.setLastUpdate(TimeUtility.localToUTCTime(LocalDateTime.now().toString()));
        newCustomer.setLastUpdatedBy(Main.currentUser.getUsername());
        newCustomer.setDivisionID(FldDAO.get(fldCombo.getSelectionModel().getSelectedItem()).getDivisionID());
        CustomerDao.add(newCustomer);
        Stage currentStage= (Stage) fldCombo.getScene().getWindow();
        currentStage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/ManageCustomers.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

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
