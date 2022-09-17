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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import static Helpers.TimeUtility.utcToLocalTime;

/**
 * update appointment is the controller for the update appointment view and is used to update information about an
 * appointment that already exists in the database
 */
public class UpdateAppointment {
    @FXML TextField idField;
    @FXML TextField titleField;
    @FXML TextField descriptionField;
    @FXML TextField locationField;
    @FXML TextField typeField;
    @FXML DatePicker date;
    @FXML TextField startTime;
    @FXML TextField endTime;
    @FXML TextField customerID;
    @FXML TextField userID;
    @FXML ComboBox<String> contactCombo;
    Appointment appointment = new Appointment();

    /**
     * initializes the update appointment controller/view and sets view values to the appointments original values.
     * @param appointmentToUpdate is the appointment that as selected when update appointment was clicked on the
     *                            home screen.
     * @throws Exception
     */
    @FXML public void initialize(Appointment appointmentToUpdate) throws Exception {
        appointmentToUpdate = AppointmentDao.get(appointmentToUpdate.getAppointmentID());

        idField.setText(Integer.toString(appointmentToUpdate.getAppointmentID()));
        titleField.setText(appointmentToUpdate.getTitle());
        descriptionField.setText(appointmentToUpdate.getDescription());
        locationField.setText(appointmentToUpdate.getLocation());
        typeField.setText(appointmentToUpdate.getType());
        date.setValue(LocalDate.parse(appointmentToUpdate.getStart(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        startTime.setText(LocalDateTime.parse(utcToLocalTime(appointmentToUpdate.getStart()), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).format(DateTimeFormatter.ofPattern("HH:mm")));
        endTime.setText(LocalDateTime.parse(utcToLocalTime(appointmentToUpdate.getEnd()), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).format(DateTimeFormatter.ofPattern("HH:mm")));
        userID.setText(String.valueOf(appointmentToUpdate.getUserID()));
        customerID.setText(String.valueOf(appointmentToUpdate.getCustomerID()));
        contactCombo.setValue(ContactDAO.get(appointmentToUpdate.getContactID()).getContactName());

        appointment.setAppointmentID(appointmentToUpdate.getAppointmentID());
        appointment.setTitle(appointmentToUpdate.getTitle());
        appointment.setDescription(appointmentToUpdate.getDescription());
        appointment.setLocation(appointmentToUpdate.getLocation());
        appointment.setType(appointmentToUpdate.getType());
        appointment.setStart(appointmentToUpdate.getStart());
        appointment.setEnd(appointmentToUpdate.getEnd());
        appointment.setCreatedBy(appointmentToUpdate.getCreatedBy());
        appointment.setCreateDate(appointmentToUpdate.getCreateDate());
        appointment.setLastUpdate(appointmentToUpdate.getLastUpdate());
        appointment.setLastUpdatedBy(appointmentToUpdate.getLastUpdatedBy());
        appointment.setUserID(appointmentToUpdate.getUserID());
        appointment.setCustomerID(appointmentToUpdate.getCustomerID());

        populateContactCombo();
    }

    /**
     * populates the combo box to choose a contact with all contacts in the database
     * @throws Exception
     */
    public void populateContactCombo() throws Exception {
        ObservableList<String> contactNames = FXCollections.observableArrayList();
        for (Contact contact: ContactDAO.getAllContacts()) {
            contactNames.add(contact.getContactName());
        }
        contactCombo.setItems(contactNames);    }

    /**
     * updates the customers information in the database when submit is clicked.
     * @throws Exception
     */
    public void submitButtonClicked() throws Exception {
        try {
            appointment.setAppointmentID(Integer.parseInt(idField.getText()));
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
            appointment.setCustomerID(Integer.parseInt(customerID.getText()));
            appointment.setUserID(Integer.parseInt(userID.getText()));
            Contact contact = ContactDAO.get(contactCombo.getSelectionModel().getSelectedItem());
            appointment.setContact(contact.getContactName());
            appointment.setContactID(contact.getContactID());
            appointment.setCreatedBy(appointment.getCreatedBy());
            appointment.setLastUpdatedBy(Main.currentUser.getUsername());
            appointment.setCreateDate(appointment.getCreateDate());
            appointment.setLastUpdate(TimeUtility.localToUTCTime(LocalDateTime.now().toString()));

            if((TimeUtility.verifyAppointmentInBusinessHours(appointment) && TimeUtility.verifyCustomerAppointmentsDontOverlap(appointment))) {
                AppointmentDao.update(appointment);
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
