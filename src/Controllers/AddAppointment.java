package Controllers;

import DAO.AppointmentDao;
import DAO.ContactDAO;
import Helpers.TimeUtility;
import Models.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;

public class AddAppointment {
    @FXML TextField titleField;
    @FXML TextField descriptionField;
    @FXML TextField locationField;
    @FXML TextField phoneNumberField;
    @FXML ComboBox<String> contactCombo;
    @FXML TextField typeField;
    @FXML DatePicker date;
    @FXML ComboBox<String> startTime;
    @FXML ComboBox<String> endTime;
    @FXML TextField customerID;
    @FXML TextField userID;

    @FXML
    public void initialize() throws Exception {
        populateTimeCombos();
        populateContactCombo();
    }

    public void populateTimeCombos() {
        ObservableList<String> easternTimes = FXCollections.observableArrayList();
        ObservableList<String> localTimes = FXCollections.observableArrayList();
        String[] quarterHours = {"00","15","30","45"};
        for (int i = 8; i < 22; i++) {
            for (int j = 0; j < 4; j++) {
                String time = "";
                if (i < 10) {
                    time = "1999-01-01 0" + i + ":" + quarterHours[j] + ":00";
                } else {
                    time = "1999-01-01 " + i + ":" + quarterHours[j] + ":00";
                }
                easternTimes.add(time);
            }
        }
        for(String easternTime: easternTimes) {
            localTimes.add(TimeUtility.estToLocalTime(easternTime));
        }
        startTime.setItems(localTimes);
        endTime.setItems(localTimes);
    }

    public void populateContactCombo() throws Exception {
        contactCombo.setItems(ContactDAO.getAllContacts());
    }

    public void submitButtonClicked() throws Exception {
        Appointment appointment = new Appointment();
        appointment.setTitle(titleField.getText());
        appointment.setDescription(descriptionField.getText());
        appointment.setLocation(locationField.getText());
        appointment.setType(typeField.getText());
        String start = TimeUtility.localToUTCTime(date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " "
                + startTime.getSelectionModel().getSelectedItem(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        appointment.setStart(start);
        String end = TimeUtility.localToUTCTime(date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
                " " + endTime.getSelectionModel().getSelectedItem(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        appointment.setEnd(end);
        appointment.setCustomerID(Integer.valueOf(customerID.getText()));
        appointment.setUserID(Integer.valueOf(userID.getText()));
        appointment.setContactID(ContactDAO.get(contactCombo.getSelectionModel().getSelectedItem()).getContactID());
        AppointmentDao.add(appointment);
    }
}
