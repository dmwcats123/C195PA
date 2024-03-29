package DAO;

import Helpers.TimeUtility;
import Models.Appointment;
import Models.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;

/**
 * AppointmentDao is used to interact with appointments in the database
 */
public class AppointmentDao {

    /**
     * Finds and returns an appointment from the database
     * @param appointmentID is the paramater that is matched to the database
     * @return the appointment with the given appointmentID
     * @throws Exception
     */
    public static Appointment get(int appointmentID) throws Exception {
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "select * FROM appointments WHERE Appointment_ID  = " + appointmentID;
        ResultSet result = statement.executeQuery(sqlStatement);
        Appointment appointmentResult = new Appointment();
        while (result.next()) {
            appointmentResult.setAppointmentID(result.getInt("Appointment_ID"));
            appointmentResult.setTitle(result.getString("Title"));
            appointmentResult.setDescription(result.getString("Description"));
            appointmentResult.setLocation(result.getString("Location"));
            appointmentResult.setType(result.getString("Type"));
            appointmentResult.setStart(result.getString("Start"));
            appointmentResult.setEnd(result.getString("End"));
            appointmentResult.setCreatedBy(result.getString("Created_By"));
            appointmentResult.setCreateDate(result.getString("Create_Date"));
            appointmentResult.setLastUpdate(result.getString("Last_Update"));
            appointmentResult.setLastUpdatedBy(result.getString("Last_Updated_By"));
            appointmentResult.setCustomerID(result.getInt("Customer_ID"));
            appointmentResult.setContactID(result.getInt("Contact_ID"));
            appointmentResult.setUserID(result.getInt("User_ID"));

            Database.closeConnection();
            return appointmentResult;
        }
        Database.closeConnection();
        return null;
    }

    /**
     * retrives and returns all appointments from the database
     * @return an observable list of all appointments in the database
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException, Exception{
        ObservableList<Appointment> allAppointments= FXCollections.observableArrayList();
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "select * FROM appointments";
        ResultSet result = statement.executeQuery(sqlStatement);
        while(result.next()){
            Appointment appointmentResult = new Appointment(result.getInt("Appointment_ID"), result.getString("Title"),
                    result.getString("Description"), result.getString("Location"), result.getString("Type"),
                    TimeUtility.utcToLocalTime(result.getString("Start")), TimeUtility.utcToLocalTime(result.getString("End")),
                    result.getString("Create_Date"), result.getString("Created_By"),
                    result.getString("Last_Update"), result.getString("Last_Updated_By"),
                    result.getInt("Customer_ID"), result.getInt("User_ID"), result.getInt("Contact_ID"));
            appointmentResult.setContact(ContactDAO.get(appointmentResult.getContactID()).getContactName());
            allAppointments.add(appointmentResult);

        }
        Database.closeConnection();
        return allAppointments;
    }

    /**
     * Finds all appointments from the database for a specific customer
     * @param customerID the customer ID of the customer for which the appointments are found
     * @return an observable list of the customers appointments
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Appointment> getAllAppointmentsForCustomer(int customerID) throws SQLException, Exception{
        ObservableList<Appointment> allAppointments= FXCollections.observableArrayList();
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "select * FROM appointments WHERE Customer_ID = " + customerID;
        ResultSet result = statement.executeQuery(sqlStatement);
        while(result.next()){
            Appointment appointmentResult = new Appointment(result.getInt("Appointment_ID"), result.getString("Title"),
                    result.getString("Description"), result.getString("Location"), result.getString("Type"),
                    TimeUtility.utcToLocalTime(result.getString("Start")), TimeUtility.utcToLocalTime(result.getString("End")),
                    result.getString("Create_Date"), result.getString("Created_By"),
                    result.getString("Last_Update"), result.getString("Last_Updated_By"),
                    result.getInt("Customer_ID"), result.getInt("User_ID"), result.getInt("Contact_ID"));
            appointmentResult.setContact(ContactDAO.get(appointmentResult.getContactID()).getContactName());
            allAppointments.add(appointmentResult);

        }
        Database.closeConnection();
        return allAppointments;
    }

    /**
     * gets all appointments for the next month.
     * @return observable list of appointments in the next month.
     */
    public static ObservableList<Appointment> getMonthAppointments () {
        ObservableList<Appointment> monthlyAppts = FXCollections.observableArrayList();
        Appointment appointmentResult;
        LocalDate now = LocalDate.now();
        LocalDate oneMonth = LocalDate.now().plusMonths(1);
        try {
            Connection connection = Database.makeConnection();
            Statement statement =  connection.createStatement();
            String sqlStatement= "SELECT * FROM appointments WHERE start >= '" + now + "' AND start <= '" + oneMonth + "'\n" +
                    "ORDER BY start";
            ResultSet result = statement.executeQuery(sqlStatement);
            while(result.next()) {
                appointmentResult = new Appointment(result.getInt("Appointment_ID"), result.getString("Title"),
                        result.getString("Description"), result.getString("Location"), result.getString("Type"),
                        TimeUtility.utcToLocalTime(result.getString("Start")), TimeUtility.utcToLocalTime(result.getString("End")),
                        result.getString("Create_Date"), result.getString("Created_By"),
                        result.getString("Last_Update"), result.getString("Last_Updated_By"),
                        result.getInt("Customer_ID"), result.getInt("User_ID"), result.getInt("Contact_ID"));
                appointmentResult.setContact(ContactDAO.get(appointmentResult.getContactID()).getContactName());
                monthlyAppts.add(appointmentResult);
            }
            Database.closeConnection();
            return monthlyAppts;
        }
        catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            return null;
        }
    }


    /**
     * gets all appointments for the next week.
     * @return observable list of appointments in the next week.
     */
    public static ObservableList<Appointment> getWeekAppointments () {
        ObservableList<Appointment> weekAppts = FXCollections.observableArrayList();
        Appointment appointment;
        LocalDate now = LocalDate.now();
        LocalDate oneWeek = LocalDate.now().plusDays(7);
        try {
            Connection connection = Database.makeConnection();
            Statement statement =  connection.createStatement();
            String sqlStatement= "SELECT * FROM appointments WHERE start >= '" + now + "' AND start <= '" + oneWeek + "'\n" +
                    "ORDER BY start";
            ResultSet result = statement.executeQuery(sqlStatement);
            while(result.next()) {
                appointment = new Appointment(result.getInt("Appointment_ID"), result.getString("Title"),
                        result.getString("Description"), result.getString("Location"), result.getString("Type"),
                        TimeUtility.utcToLocalTime(result.getString("Start")), TimeUtility.utcToLocalTime(result.getString("End")),
                        result.getString("Create_Date"), result.getString("Created_By"),
                        result.getString("Last_Update"), result.getString("Last_Updated_By"),
                        result.getInt("Customer_ID"), result.getInt("User_ID"), result.getInt("Contact_ID"));
                weekAppts.add(appointment);
            }
            Database.closeConnection();
            return weekAppts;
        }
        catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            return null;
        }
    }

    /**
     * finds all appointments for a specified user in the database
     * @param userID the userid of the users appointments to be returned
     * @return an observable list of appointments for the specified user
     */
    public static ObservableList<Appointment> getAppointmentsForUser(int userID) {
        ObservableList<Appointment> userAppts = FXCollections.observableArrayList();
        Appointment appointment;
        try {
            Connection connection = Database.makeConnection();
            Statement statement =  connection.createStatement();
            String sqlStatement= "SELECT * FROM appointments WHERE User_ID = " + userID;
            ResultSet result = statement.executeQuery(sqlStatement);
            while(result.next()) {
                appointment = new Appointment(result.getInt("Appointment_ID"), result.getString("Title"),
                        result.getString("Description"), result.getString("Location"), result.getString("Type"),
                        TimeUtility.utcToLocalTime(result.getString("Start")), TimeUtility.utcToLocalTime(result.getString("End")),
                        result.getString("Create_Date"), result.getString("Created_By"),
                        result.getString("Last_Update"), result.getString("Last_Updated_By"),
                        result.getInt("Customer_ID"), result.getInt("User_ID"), result.getInt("Contact_ID"));
                userAppts.add(appointment);
            }
            Database.closeConnection();
            return userAppts;
        }
        catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            return null;
        }
    }


    /**
     * finds all appointments for a specified contact in the database
     * @param contactID the contact id of the contact whose appointments are to be returned.
     * @return an observable list of appointments for specified contact.
     */
    public static ObservableList<Appointment> getAppointmentsForContact(int contactID) {
        ObservableList<Appointment> contactAppts = FXCollections.observableArrayList();
        Appointment appointment;
        try {
            Connection connection = Database.makeConnection();
            Statement statement =  connection.createStatement();
            String sqlStatement= "SELECT * FROM appointments WHERE Contact_ID = " + contactID;
            ResultSet result = statement.executeQuery(sqlStatement);
            while(result.next()) {
                appointment = new Appointment(result.getInt("Appointment_ID"), result.getString("Title"),
                        result.getString("Description"), result.getString("Location"), result.getString("Type"),
                        TimeUtility.utcToLocalTime(result.getString("Start")), TimeUtility.utcToLocalTime(result.getString("End")),
                        result.getString("Create_Date"), result.getString("Created_By"),
                        result.getString("Last_Update"), result.getString("Last_Updated_By"),
                        result.getInt("Customer_ID"), result.getInt("User_ID"), result.getInt("Contact_ID"));
                contactAppts.add(appointment);
            }
            Database.closeConnection();
            return contactAppts;
        }
        catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            return null;
        }
    }

    /**
     * deletes an appointment from the database
     * @param appointmentID specifies the appointment to be deleted
     * @throws Exception
     */
    public static void delete(int appointmentID) throws Exception {
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "delete FROM appointments where Appointment_ID = " + appointmentID;
        if (statement.executeUpdate(sqlStatement) > 0 ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Success");
            alert.setContentText("The record was deleted");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Failure");
            alert.setContentText("The record was not deleted");
            alert.showAndWait();
        }
    }

    /**
     * adds an appointment to the database
     * @param appointment to be added to database
     * @throws Exception
     */
    public static void add(Appointment appointment) throws Exception {
        Connection connection = Database.makeConnection();
        PreparedStatement ps = connection.prepareStatement("insert into appointments (Title, Description, Location," +
                "Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, appointment.getTitle());
        ps.setString(2, appointment.getDescription());
        ps.setString(3, appointment.getLocation());
        ps.setString(4, appointment.getType());
        ps.setString(5, appointment.getStart());
        ps.setString(6, appointment.getEnd());
        ps.setString(7, appointment.getCreateDate());
        ps.setString(8, appointment.getCreatedBy());
        ps.setString(9, appointment.getLastUpdate());
        ps.setString(10, appointment.getLastUpdatedBy());
        ps.setInt(11, appointment.getCustomerID());
        ps.setInt(12, appointment.getUserID());
        ps.setInt(13, appointment.getContactID());

        ps.executeUpdate();
    }

    /**
     * updates an appointment with new information
     * @param appointment to be updated
     * @throws Exception
     */
    public static void update(Appointment appointment) throws Exception {
        Connection connection = Database.makeConnection();
        PreparedStatement ps = connection.prepareStatement("update appointments SET Title = ?, Description = ?, " +
                "Location = ?, Type = ?, Start = ?, End = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Contact_ID = ?, Customer_ID = ?, User_ID = ? WHERE Appointment_ID = ?");

        ps.setString(1, appointment.getTitle());
        ps.setString(2, appointment.getDescription());
        ps.setString(3, appointment.getLocation());
        ps.setString(4, appointment.getType());
        ps.setString(5, appointment.getStart());
        ps.setString(6, appointment.getEnd());
        ps.setString(7, appointment.getCreateDate());
        ps.setString(8, appointment.getCreatedBy());
        ps.setString(9, appointment.getLastUpdate());
        ps.setString(10, appointment.getLastUpdatedBy());
        ps.setInt(11, appointment.getContactID());
        ps.setInt(12, appointment.getCustomerID());
        ps.setInt(13, appointment.getUserID());
        ps.setInt(14, appointment.getAppointmentID());


        ps.executeUpdate();
        Database.closeConnection();
    }
}
