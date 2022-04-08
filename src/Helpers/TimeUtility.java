package Helpers;

import Models.Appointment;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtility {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String TIME_FORMAT = "HH:mm:ss";


    public TimeUtility() {}

    public static String localToUTCTime(String time) {
        LocalDateTime ldt = LocalDateTime.parse(time);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime utczdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        return utczdt.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static String localToUTCTime(String time, DateTimeFormatter dateTimeFormatter) {
        LocalDateTime ldt = LocalDateTime.parse(time, dateTimeFormatter);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime utczdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        return utczdt.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static String utcToLocalTime(String time) {
        LocalDateTime ldt = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(DATE_FORMAT));
        ZonedDateTime utczdt = ldt.atZone(ZoneId.of("UTC"));
        ZonedDateTime zdt = utczdt.withZoneSameInstant(ZoneId.systemDefault());
        return zdt.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static String estToLocalTime(String time) {
        LocalDateTime ldt = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(DATE_FORMAT));
        ZonedDateTime estzdt = ldt.atZone(ZoneId.of("US/Eastern"));
        ZonedDateTime zdt = estzdt.withZoneSameInstant(ZoneId.systemDefault());
        return zdt.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

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
}
