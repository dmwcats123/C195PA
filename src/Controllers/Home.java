package Controllers;

import DAO.AppointmentDao;
import Models.Appointment;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Home {

    public void initialize() {
        ObservableList<Appointment> userAppointments =  AppointmentDao.getAppointmentsForUser(Main.currentUser.getUserID());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime fifteenMinutes = now.plusMinutes(15);
        ZonedDateTime zdtNow = now.atZone(ZoneId.systemDefault());
        ZonedDateTime zdtFifteen = fifteenMinutes.atZone(ZoneId.systemDefault());
        ZonedDateTime utcNow = zdtNow.withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime utcFifteen = zdtFifteen.withZoneSameInstant(ZoneId.of("UTC"));
        boolean apptFound = false;
        if (userAppointments != null && !userAppointments.isEmpty()) {
            for (Appointment appointment: userAppointments) {
                LocalDateTime apptStart = LocalDateTime.parse(appointment.getStart(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                ZonedDateTime zdtApptStart = apptStart.atZone(ZoneId.systemDefault());
                ZonedDateTime utcApptStart = zdtApptStart.withZoneSameInstant(ZoneId.of("UTC"));
                if(utcApptStart.isAfter(utcNow) && utcApptStart.isBefore(utcFifteen)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("You have an appointment coming up!");
                    alert.setContentText("ID: " + Integer.toString(appointment.getAppointmentID()) + "Start Time: " + appointment.getStart());
                    Optional<ButtonType> info = alert.showAndWait();
                    apptFound = true;
                }
            }
        }

        if(!apptFound) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("You have no appointments");
            alert.setContentText("Have a good day!");
            Optional<ButtonType> info = alert.showAndWait();
        }
    }
    @FXML
    public void manageCustomersClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/ManageCustomers.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (java.io.IOException exception) {
            System.out.println("io exception");
        }

    }

    @FXML
    public void manageAppointmentsClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/ManageAppointments.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (java.io.IOException exception) {
            System.out.println("io exception");
        }
    }

    @FXML
    public void generateReportsClicked() {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/Reports.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        Reports reportsController = fxmlLoader.getController();
        reportsController.initialize();
        stage.setScene(new Scene(root1));
        stage.show();
        } catch (java.io.IOException exception) {
            System.out.println("io exception");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
