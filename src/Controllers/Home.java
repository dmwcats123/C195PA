package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Home {
    @FXML
    public void addCustomerClicked() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/AddCustomer.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (java.io.IOException exception) {
            System.out.println("io exception");
        }

    }

    @FXML
    public void updateCustomerClicked() {
    }

    @FXML
    public void deleteCustomerClicked() {
    }

    @FXML
    public void addAppointmentClicked() {
    }

    @FXML
    public void deleteAppointmentClicked() {
    }

    @FXML
    public void updateAppointmentClicked() {
    }
}
