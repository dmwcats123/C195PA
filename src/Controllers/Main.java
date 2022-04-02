package Controllers;

import DAO.UserDao;
import Models.Database;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;


public class Main extends Application implements Initializable {
    @FXML TextField usernameField;
    @FXML TextField passwordField;
    @FXML Button submit;
    @FXML Label zoneId;
    Locale userLocale = Locale.getDefault();
    private ZoneId userZoneId = ZoneId.systemDefault();;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../Views/Main.fxml"));
            Scene scene = new Scene(root, 1000, 400);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (java.io.IOException exception) {
            System.out.println("IOexception");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resourceBundle = ResourceBundle.getBundle("Resources/login", userLocale);
        usernameField.setPromptText(resourceBundle.getString("usernameField"));
        passwordField.setPromptText(resourceBundle.getString("passwordField"));
        submit.setText(resourceBundle.getString("submit"));
        zoneId.setText(userZoneId.getId());
    }

    @FXML
    public void submitClicked() throws Exception {
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String attemptedUsername = usernameField.getText();
        String attemptedPassword = passwordField.getText();
        if(UserDao.get(attemptedUsername) != null && UserDao.get(attemptedUsername).getPassword().equals(attemptedPassword)) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/Home.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            ResourceBundle resourceBundle = ResourceBundle.getBundle("Resources/login", userLocale);
            alert.setHeaderText(resourceBundle.getString("loginProblemAlertTitle"));
            alert.setContentText(resourceBundle.getString("loginProblemAlertText"));
            Optional<ButtonType> info = alert.showAndWait();
        }
        Database.closeConnection();
    }
}


