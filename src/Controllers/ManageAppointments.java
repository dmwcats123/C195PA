package Controllers;

import DAO.AppointmentDao;
import Models.Appointment;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ManageAppointments {
    @FXML TableView<Appointment> appointmentTableView;
    @FXML TableColumn<Appointment, Integer> appointmentIDCol;
    @FXML TableColumn<Appointment, String> appointmentTitleCol;
    @FXML TableColumn<Appointment, String> appointmentDescriptionCol;
    @FXML TableColumn<Appointment, String> appointmentLocationCol;
    @FXML TableColumn<Appointment, String> appointmentContactCol;
    @FXML TableColumn<Appointment, String> appointmentTypeCol;
    @FXML TableColumn<Appointment, String> appointmentStartCol;
    @FXML TableColumn<Appointment, String> appointmentEndCol;
    @FXML TableColumn<Appointment, Integer> appointmentCustomerID;
    @FXML TableColumn<Appointment, Integer> appointmentUserID;


    @FXML
    public void initialize() {
        try {
            appointmentIDCol.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointmentID"));
            appointmentTitleCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
            appointmentDescriptionCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
            appointmentLocationCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
            appointmentContactCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("contact"));
            appointmentTypeCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
            appointmentStartCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("start"));
            appointmentEndCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("end"));
            appointmentCustomerID.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customerID"));
            appointmentUserID.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("userID"));

            appointmentTableView.setItems(AppointmentDao.getAllAppointments());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void addAppointmentClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/AddAppointment.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            Stage currentStage= (Stage) appointmentTableView.getScene().getWindow();
            currentStage.close();
        } catch (java.io.IOException exception) {
            System.out.println("io exception");
        }
    }

    @FXML
    public void updateAppointmentClicked() {

    }
    @FXML
    public void deleteAppointmentClicked () {

    }
}
