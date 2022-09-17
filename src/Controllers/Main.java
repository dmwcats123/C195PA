package Controllers;

import DAO.UserDao;
import Helpers.TimeUtility;
import Models.Database;
import Models.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;


public class Main extends Application implements Initializable {
    @FXML TextField usernameField;
    @FXML TextField passwordField;
    @FXML Button submit;
    @FXML Label zoneId;
    public static User currentUser = new User();
    Locale userLocale = Locale.getDefault();
    private ZoneId userZoneId = ZoneId.systemDefault();;

    /**
     * acts as an entry point for the program
     * @param args takes cmd line arguments
     */
    public static void main(String[] args) { launch(args); }

    /**
     * Opens the Main view for the program
     * @param primaryStage is the javafx stage for the program
     */
    @Override public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../Views/Main.fxml"));
            Scene scene = new Scene(root, 1000, 400);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (java.io.IOException exception) {
            System.out.println("IOexception");
        }
    }

    /**
     * initializes the main program and determines what language to use
     * @param url
     * @param resourceBundle holds resources for launching the program in other languages
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        resourceBundle = ResourceBundle.getBundle("Resources/login", userLocale);
        usernameField.setPromptText(resourceBundle.getString("usernameField"));
        passwordField.setPromptText(resourceBundle.getString("passwordField"));
        submit.setText(resourceBundle.getString("submit"));
        zoneId.setText(userZoneId.getId());
    }

    /**
     * attempts to login when the submit is clicked. also tracks login activity
     * @throws Exception
     */
    @FXML public void submitClicked() throws Exception {
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String attemptedUsername = usernameField.getText();
        String attemptedPassword = passwordField.getText();
        if(UserDao.get(attemptedUsername) != null && UserDao.get(attemptedUsername).getPassword().equals(attemptedPassword)) {
            String loginTrackingText = "Login Attempt Success " + attemptedUsername + " " + TimeUtility.localToUTCTime(LocalDateTime.now().toString()) + "\n";
            Files.write(Paths.get("./loginactivity.txt"), loginTrackingText.getBytes());
            currentUser = UserDao.get(attemptedUsername);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/Home.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            Stage currentStage = (Stage) submit.getScene().getWindow();
            currentStage.close();
        } else {
            String loginTrackingText = "Login Attempt Failure " + attemptedUsername + " " + TimeUtility.localToUTCTime(LocalDateTime.now().toString()) + "\n";
            Files.write(Paths.get("./loginactivity.txt"), loginTrackingText.getBytes());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            ResourceBundle resourceBundle = ResourceBundle.getBundle("Resources/login", userLocale);
            alert.setHeaderText(resourceBundle.getString("loginProblemAlertTitle"));
            alert.setContentText(resourceBundle.getString("loginProblemAlertText"));
            Optional<ButtonType> info = alert.showAndWait();
        }
        Database.closeConnection();
    }
}


