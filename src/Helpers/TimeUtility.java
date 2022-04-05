package Helpers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtility {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public TimeUtility() {}

    public static String localToUTCTime(String time) {
        LocalDateTime ldt = LocalDateTime.parse(time);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime utczdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        return utczdt.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
