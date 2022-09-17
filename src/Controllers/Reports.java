package Controllers;

import DAO.AppointmentDao;
import DAO.ContactDAO;
import DAO.CustomerDao;
import Models.Appointment;
import Models.Contact;
import Models.Customer;
import Models.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import static java.time.LocalTime.now;

public class Reports {
    @FXML TextArea apptTypeReport;
    @FXML TextArea contactScheduleReport;
    @FXML TextArea nextCustApptReport;

    @FXML ComboBox<String> monthCombo;
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * initializes the generate report screen
     * @throws Exception
     */
    public void initialize() throws Exception {
    }

    @FXML
    /**
     * generates a report to count appointments in a given month
     */
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

    /**
     * generates a report to show all appointments for all contacts.
     * @throws Exception
     */
    @FXML public void generateReportTwo() throws Exception {
        StringBuffer contactReport = new StringBuffer();
        for (Contact contact: ContactDAO.getAllContacts()) {
            contactReport.append(contact.getContactName() + ": \n");
            if (AppointmentDao.getAppointmentsForContact(contact.getContactID()).isEmpty()){
                contactReport.append("No Appointments Scheduled" + "\n");

        }
            else {
                for (Appointment appointment : AppointmentDao.getAppointmentsForContact(contact.getContactID())) {
                    contactReport.append("Appointment ID: " + appointment.getAppointmentID() + "\nTitle: " + appointment.getTitle() + "\nStart: "
                            + appointment.getStart() + "\nEnd: " + appointment.getEnd() + "\nCustomer ID: " + appointment.getCustomerID() + "\n \n");
                }
            }
            contactReport.append("\n");
        }
        contactScheduleReport.setText(contactReport.toString());
    }

    /**
     * This generates my custom report which shows the next upcoming appointment for each customer.
     * @throws Exception
     */
    @FXML public void generateReportThree() throws Exception {
        StringBuffer nextCustomerAppointment = new StringBuffer();
        for(Customer customer: CustomerDao.getAllCustomers()) {
            ObservableList<Appointment> customerAppointments = AppointmentDao.getAllAppointmentsForCustomer(customer.getCustomerID());
            Appointment nextAppt = new Appointment();
            nextCustomerAppointment.append("Customer " + customer.getCustomerName() + ": \n");

            if (customerAppointments.size() != 0) {
                ZonedDateTime nextApptStartZDT = ZonedDateTime.now();

                for (Appointment currAppointment : customerAppointments) {
                    if (nextAppt.getAppointmentID() != 0) {
                        LocalDateTime nextApptStartLDT = LocalDateTime.parse(nextAppt.getStart(), DateTimeFormatter.ofPattern(DATE_FORMAT));
                        nextApptStartZDT = nextApptStartLDT.atZone(ZoneId.of("UTC"));
                    }
                    LocalDateTime currApptStartLDT = LocalDateTime.parse(currAppointment.getStart(), DateTimeFormatter.ofPattern(DATE_FORMAT));
                    ZonedDateTime currApptStartZDT = currApptStartLDT.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
                    LocalDateTime currApptEndLDT = LocalDateTime.parse(currAppointment.getEnd(), DateTimeFormatter.ofPattern(DATE_FORMAT));
                    ZonedDateTime currApptEndZDT = currApptEndLDT.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
                    ZonedDateTime now = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC"));
                    if ((nextAppt.getAppointmentID() == 0 && currApptStartZDT.isAfter(now)) || (currApptEndZDT.isBefore(nextApptStartZDT) && currApptStartZDT.isAfter(now))) {
                        nextAppt = currAppointment;
                    }
                }
            }
            if (nextAppt.getAppointmentID() != 0) {
                    nextCustomerAppointment.append("Appointment ID: " + nextAppt.getAppointmentID() + "\nTitle: " + nextAppt.getTitle() + "\nStart: "
                            + nextAppt.getStart() + "\nEnd: " + nextAppt.getEnd() + "\nCustomer ID: " + nextAppt.getCustomerID() + "\n \n");

            } else {
                nextCustomerAppointment.append("No Future Appointments \n \n");
            }

        }
            nextCustApptReport.setText(nextCustomerAppointment.toString());
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
