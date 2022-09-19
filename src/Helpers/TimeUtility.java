package Helpers;

import DAO.AppointmentDao;
import Models.Appointment;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TimeUtility holds helper methods to convert between time zones and format times and dates
 */
public class TimeUtility {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String TIME_FORMAT = "HH:mm:ss";


    public TimeUtility() {}

    /**
     * Converts a local time to a UTC time
     * @param time a string representing a local time
     * @return a string representing utc time
     */
    public static String localToUTCTime(String time) {
        LocalDateTime ldt = LocalDateTime.parse(time);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime utczdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        return utczdt.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * converts a local time to a UTC time given a specified format
     * @param time a string representing local time
     * @param dateTimeFormatter the format of the local time
     * @return the utc time
     */
    public static String localToUTCTime(String time, DateTimeFormatter dateTimeFormatter) {
        LocalDateTime ldt = LocalDateTime.parse(time, dateTimeFormatter);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime utczdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        return utczdt.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * converts utc time to local time
     * @param time a string representing utc time
     * @return local time
     */
    public static String utcToLocalTime(String time) {
        LocalDateTime ldt = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(DATE_FORMAT));
        ZonedDateTime utczdt = ldt.atZone(ZoneId.of("UTC"));
        ZonedDateTime zdt = utczdt.withZoneSameInstant(ZoneId.systemDefault());
        return zdt.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }


    /**
     * this verifies appointments are during business hours of 8am-10pm EST time
     * @param appointment is the appointment to check time validity
     * @return true if the appointment is during business hours, false if it is outside business hours.
     */
    public static boolean verifyAppointmentInBusinessHours(Appointment appointment) {
        LocalDateTime apptStartLDT = LocalDateTime.parse(appointment.getStart(), DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDateTime apptEndLDT = LocalDateTime.parse(appointment.getEnd(), DateTimeFormatter.ofPattern(DATE_FORMAT));

        ZonedDateTime apptStartUTCZDT = apptStartLDT.atZone(ZoneId.of("UTC"));
        ZonedDateTime apptEndUTCZDT = apptEndLDT.atZone(ZoneId.of("UTC"));

        ZonedDateTime apptStartESTZDT = apptStartUTCZDT.withZoneSameInstant(ZoneId.of("US/Eastern"));
        ZonedDateTime apptEndESTZDT = apptEndUTCZDT.withZoneSameInstant(ZoneId.of("US/Eastern"));

        int startHour = apptStartESTZDT.getHour();
        int endHour = apptEndESTZDT.getHour();
        return startHour >= 8 && endHour <= 22;
    }

    /**
     * veries that an appointment doesn't overlap with any other appointments for the same customer.
     * @param newAppointment is the appointment to check for overlap
     * @return true if appointments dont overlap, false if the appointment overlaps any other appointment for the customer.
     * @throws Exception
     */
    public static boolean verifyCustomerAppointmentsDontOverlap(Appointment newAppointment) throws Exception {
        ObservableList<Appointment> customerAppointments = AppointmentDao.getAllAppointmentsForCustomer(newAppointment.getCustomerID());
        LocalDateTime newApptStartLDT = LocalDateTime.parse(newAppointment.getStart(), DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDateTime newApptEndLDT = LocalDateTime.parse(newAppointment.getEnd(), DateTimeFormatter.ofPattern(DATE_FORMAT));
        ZonedDateTime newApptStartZDT = newApptStartLDT.atZone(ZoneId.of("UTC"));
        ZonedDateTime newApptEndZDT = newApptEndLDT.atZone(ZoneId.of("UTC"));

        for(Appointment currAppointment: customerAppointments) {
            LocalDateTime currApptStartLDT = LocalDateTime.parse(currAppointment.getStart(), DateTimeFormatter.ofPattern(DATE_FORMAT));
            LocalDateTime currApptEndLDT = LocalDateTime.parse(currAppointment.getEnd(), DateTimeFormatter.ofPattern(DATE_FORMAT));

            ZonedDateTime currApptStartZDT = currApptStartLDT.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
            ZonedDateTime currApptEndZDT = currApptEndLDT.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
            if (currAppointment.getAppointmentID() != newAppointment.getAppointmentID()) {
                if (currApptStartZDT.isAfter(newApptStartZDT) && currApptStartZDT.isBefore(newApptEndZDT)) {
                    return false;
                } else if (currApptEndZDT.isAfter(newApptStartZDT) && currApptEndZDT.isBefore(newApptEndZDT)) {
                    return false;
                } else if (newApptStartZDT.isAfter(currApptStartZDT) && newApptStartZDT.isBefore(currApptEndZDT)) {
                    return false;
                } else if (newApptEndZDT.isAfter(currApptStartZDT) && newApptEndZDT.isBefore(currApptEndZDT)) {
                    return false;
                } else if (newApptEndZDT.isEqual(currApptEndZDT)) {
                    return false;
                }
            }
        }
        return true;
    }
}
