package Controllers;

import DAO.AppointmentDao;
import DAO.ContactDAO;
import Models.Appointment;
import Models.Contact;
import Models.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Reports {
    @FXML TextArea apptTypeReport;
    @FXML TextArea contactScheduleReport;
    @FXML ComboBox<String> monthCombo;

    public void initialize() throws Exception {
        populateMonthCombo();
    }

    @FXML
    public void generateReportOne() throws Exception {
        HashMap<String, String> dates = createDateMap();
        LocalDateTime startMonth = LocalDateTime.parse("2022-" + dates.get(monthCombo.getSelectionModel().getSelectedItem()) + "-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endMonth = LocalDateTime.parse("2022-" + dates.get(monthCombo.getSelectionModel().getSelectedItem()) + "-31 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String sqlStatement = "SELECT type, count(*) as 'Number' " +
                "FROM appointments " +
                "WHERE start>='" + startMonth + "' AND start<'" + endMonth + "' " +
                "GROUP BY type";


        Connection connection = Database.makeConnection();
        ResultSet result = connection.createStatement().executeQuery(sqlStatement);
        StringBuffer appointmentType = new StringBuffer();
        while (result.next()) {

            appointmentType.append(String.format("%s ", result.getString("type")));
            appointmentType.append(String.format(" %d\n",result.getInt("Number")));

        }
        connection.close();

        apptTypeReport.setText(appointmentType.toString());
    }

    @FXML
    public void generateReportTwo() throws Exception {
        StringBuffer contactReport = new StringBuffer();
        for (Contact contact: ContactDAO.getAllContacts()) {
            contactReport.append(contact.getContactName() + "\n");
            for (Appointment appointment: AppointmentDao.getAppointmentsForContact(contact.getContactID())) {
                contactReport.append("Appointment ID: " + appointment.getAppointmentID() + " Title: " + appointment.getTitle() + " Start: "
                        + appointment.getStart() + " End: " + appointment.getEnd() + " Customer ID: " + appointment.getCustomerID() + "\n");
            }
        }
        contactScheduleReport.setText(contactReport.toString());
    }


    public void populateMonthCombo() {
        ObservableList<String> months = FXCollections.observableArrayList();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
        monthCombo.setItems(months);
    }

    public HashMap<String, String> createDateMap() {
        HashMap<String, String> dates = new HashMap<>();
        dates.put("January", "01");
        dates.put("February", "02");
        dates.put("March", "03");
        dates.put("April" , "04");
        dates.put("May" , "05");
        dates.put("June" , "06");
        dates.put("July", "07");
        dates.put("August", "08");
        dates.put("September", "09");
        dates.put("October", "10");
        dates.put("November", "11");
        dates.put("December", "12");
        return dates;
    }
}
