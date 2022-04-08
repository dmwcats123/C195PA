package Controllers;

import DAO.AppointmentDao;
import DAO.ContactDAO;
import Helpers.TimeUtility;
import Models.Appointment;
import Models.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class AddAppointment {
    @FXML TextField titleField;
    @FXML TextField descriptionField;
    @FXML TextField locationField;

    @FXML ComboBox<String> contactCombo;
    @FXML TextField typeField;
    @FXML DatePicker date;
    @FXML TextField startTime;
    @FXML TextField endTime;
    @FXML TextField customerID;
    @FXML TextField userID;

    @FXML
    public void initialize() throws Exception {
        populateContactCombo();
    }

    public void populateContactCombo() throws Exception {
        ObservableList<String> contactNames = FXCollections.observableArrayList();
        for (Contact contact: ContactDAO.getAllContacts()) {
            contactNames.add(contact.getContactName());
        }
        contactCombo.setItems(contactNames);
    }

    public void submitButtonClicked() throws Exception {
        try {
            Appointment appointment = new Appointment();
            appointment.setTitle(titleField.getText());
            appointment.setDescription(descriptionField.getText());
            appointment.setLocation(locationField.getText());
            appointment.setType(typeField.getText());
            String start = TimeUtility.localToUTCTime(date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " "
                    + startTime.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            appointment.setStart(start);
            String end = TimeUtility.localToUTCTime(date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
                    " " + endTime.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            appointment.setEnd(end);
            appointment.setCustomerID(Integer.valueOf(customerID.getText()));
            appointment.setUserID(Integer.valueOf(userID.getText()));
            appointment.setContactID(ContactDAO.get(contactCombo.getSelectionModel().getSelectedItem()).getContactID());
            appointment.setCreatedBy(Main.currentUser.getUsername());
            appointment.setLastUpdatedBy(Main.currentUser.getUsername());
            appointment.setCreateDate(TimeUtility.localToUTCTime(LocalDateTime.now().toString()));
            appointment.setLastUpdate(TimeUtility.localToUTCTime(LocalDateTime.now().toString()));
            if(TimeUtility.verifyAppointmentInBusinessHours(appointment) && TimeUtility.verifyCustomerAppointmentsDontOverlap(appointment)) {
                AppointmentDao.add(appointment);
                Stage currentStage= (Stage) startTime.getScene().getWindow();
                currentStage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/ManageAppointments.fxml"));
                Parent root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("That Time is Bad!");
                alert.setContentText("Appointment must be between 8am and 10pm eastern time, and customers can't have overlapping appointments.");
                Optional<ButtonType> info = alert.showAndWait();
            }


        } catch(DateTimeParseException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("DateTime Exception!");
            alert.setContentText("Start and end times must be in format HH:mm");
            Optional<ButtonType> info = alert.showAndWait();
        }
    }
}
