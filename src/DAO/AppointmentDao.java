package DAO;

import Models.Appointment;
import Models.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class AppointmentDao {

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

    public static ObservableList<Appointment> getAllAppointments() throws SQLException, Exception{
        ObservableList<Appointment> allAppointments= FXCollections.observableArrayList();
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "select * FROM appointments";
        ResultSet result = statement.executeQuery(sqlStatement);
        while(result.next()){
            Appointment appointmentResult = new Appointment(result.getInt("Appointment_ID"), result.getString("Title"),
                    result.getString("Description"), result.getString("Location"), result.getString("Type"),
                    result.getString("Start"), result.getString("End"),
                    result.getString("Create_Date"), result.getString("Created_By"),
                    result.getString("Last_Update"), result.getString("Last_Updated_By"),
                    result.getInt("Customer_ID"), result.getInt("User_ID"), result.getInt("Contact_ID"));
            allAppointments.add(appointmentResult);

        }
        Database.closeConnection();
        return allAppointments;
    }

    public static void delete(int appointmentID) throws Exception {
        Connection connection = Database.makeConnection();
        Statement statement =  connection.createStatement();
        String sqlStatement= "delete * FROM appointments where Appointment_ID = " + appointmentID;
        statement.executeUpdate(sqlStatement);
    }

    public static void add(Appointment appointment) throws Exception {
        Connection connection = Database.makeConnection();
        PreparedStatement ps = connection.prepareStatement("insert into appointments (Title, Description, Location," +
                "Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By) VALUES (?,?,?,?,?,?,?,?,?,?)");
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

        ps.executeUpdate();
    }

    public static void update(Appointment appointment) throws Exception {
        Connection connection = Database.makeConnection();
        PreparedStatement ps = connection.prepareStatement("update appointments SET Title = ?, Description = ?, " +
                "Location = ?, Type = ?, Start = ?, End = ?, Create_Date = ?, Created_By = ?, Last_Update = ?, Last_Updated_By = ? WHERE Appointment_ID = ?");

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
        ps.setInt(11, appointment.getAppointmentID());


        ps.executeUpdate();
        Database.closeConnection();
    }
}